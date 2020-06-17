package tech.ibit.mybatis.learning.common.tools;

import org.apache.ibatis.jdbc.SQL;
import org.junit.Assert;
import org.junit.Test;

/**
 * SQL Example
 *
 * @author IBIT程序猿
 */
public class SqlTest {

    @Test
    public void testSelect() {

        // 普通查询
        String orgSql = "SELECT P.PERSON_ID, P.PERSON_NAME, P.NICK_NAME, "
                + "P.TITLE, P.AGE, C.COMPANY_NAME, C.CITY\n"
                + "FROM PERSON P\n"
                + "LEFT OUTER JOIN COMPANY C ON C.COMPANY_ID = P.COMPANY_ID\n"
                + "WHERE (P.AGE > ? AND C.CITY = ?) \n"
                + "OR (P.AGE < ?)\n"
                + "ORDER BY C.CITY, C.COMPANY_NAME LIMIT 10 OFFSET 10";

        String newSql = new SQL()
                .SELECT("P.PERSON_ID", "P.PERSON_NAME", "P.NICK_NAME")
                .SELECT("P.TITLE", "P.AGE", "C.COMPANY_NAME", "C.CITY")
                .FROM("PERSON P")
                .LEFT_OUTER_JOIN("COMPANY C ON C.COMPANY_ID = P.COMPANY_ID")
                .WHERE("P.AGE > ?")
                .WHERE("C.CITY = ?")
                .OR()
                .WHERE("P.AGE < ?")
                .ORDER_BY("C.CITY", "C.COMPANY_NAME")
                .OFFSET(10)
                .LIMIT(10)
                .ADD_ROW()
                .toString();
        Assert.assertEquals(orgSql, newSql);

        // 聚合函数
        orgSql = "SELECT MAX(P.AGE) MAX_AGE, C.COMPANY_NAME, C.CITY\n"
                + "FROM PERSON P\n"
                + "LEFT OUTER JOIN COMPANY C ON C.COMPANY_ID = P.COMPANY_ID\n"
                + "WHERE (C.CITY IN(?, ?))\n"
                + "GROUP BY C.COMPANY_NAME, C.CITY\n"
                + "HAVING (MAX_AGE > ? AND C.CITY = ?) \n"
                + "OR (MAX_AGE < ?)\n"
                + "ORDER BY C.CITY, C.COMPANY_NAME";

        newSql = new SQL()
                .SELECT("MAX(P.AGE) MAX_AGE")
                .SELECT("C.COMPANY_NAME", "C.CITY")
                .FROM("PERSON P")
                .LEFT_OUTER_JOIN("COMPANY C ON C.COMPANY_ID = P.COMPANY_ID")
                .WHERE("C.CITY IN(?, ?)")
                .GROUP_BY("C.COMPANY_NAME", "C.CITY")
                .HAVING("MAX_AGE > ?")
                .HAVING("C.CITY = ?")
                .OR()
                .HAVING("MAX_AGE < ?")
                .ORDER_BY("C.CITY", "C.COMPANY_NAME")
                .toString();
        Assert.assertEquals(orgSql, newSql);

    }


    @Test
    public void testSelectOffsetRow() {

        // 普通查询
        String orgSql = "SELECT P.PERSON_ID, P.PERSON_NAME, P.NICK_NAME, "
                + "P.TITLE, P.AGE, C.COMPANY_NAME, C.CITY\n"
                + "FROM PERSON P\n"
                + "LEFT OUTER JOIN COMPANY C ON C.COMPANY_ID = P.COMPANY_ID\n"
                + "WHERE (P.AGE > ? AND C.CITY = ?) \n"
                + "OR (P.AGE < ?)\n"
                + "ORDER BY C.CITY, C.COMPANY_NAME OFFSET 10 ROWS FETCH FIRST 1 ROWS ONLY";

        String newSql = new SQL()
                .SELECT("P.PERSON_ID", "P.PERSON_NAME", "P.NICK_NAME")
                .SELECT("P.TITLE", "P.AGE", "C.COMPANY_NAME", "C.CITY")
                .FROM("PERSON P")
                .LEFT_OUTER_JOIN("COMPANY C ON C.COMPANY_ID = P.COMPANY_ID")
                .WHERE("P.AGE > ?")
                .WHERE("C.CITY = ?")
                .OR()
                .WHERE("P.AGE < ?")
                .ORDER_BY("C.CITY", "C.COMPANY_NAME")
                .OFFSET_ROWS(10)
                .FETCH_FIRST_ROWS_ONLY(1)
                .ADD_ROW()
                .toString();
        Assert.assertEquals(orgSql, newSql);

    }


    @Test
    public void testDynamicSelect() {
        // 普通查询
        String orgSql = "SELECT P.PERSON_ID, P.PERSON_NAME, P.NICK_NAME, "
                + "P.TITLE, P.AGE, C.COMPANY_NAME, C.CITY\n"
                + "FROM PERSON P\n"
                + "LEFT OUTER JOIN COMPANY C ON C.COMPANY_ID = P.COMPANY_ID\n"
                + "WHERE (P.AGE > #{age} AND C.CITY = #{city})\n"
                + "ORDER BY C.CITY, C.COMPANY_NAME";

        String newSql = new SQL()
                .SELECT("P.PERSON_ID", "P.PERSON_NAME", "P.NICK_NAME")
                .SELECT("P.TITLE", "P.AGE", "C.COMPANY_NAME", "C.CITY")
                .FROM("PERSON P")
                .LEFT_OUTER_JOIN("COMPANY C ON C.COMPANY_ID = P.COMPANY_ID")
                .WHERE("P.AGE > #{age}")
                .WHERE("C.CITY = #{city}")
                .ORDER_BY("C.CITY", "C.COMPANY_NAME")
                .toString();
        Assert.assertEquals(orgSql, newSql);
    }

    @Test
    public void testDynamicInsert() {
        String orgSql = "INSERT INTO PERSON\n"
                + " (PERSON_ID, PERSON_NAME, NICK_NAME, TITLE, AGE, COMPANY_ID)\n"
                + "VALUES (#{personId}, #{personName}, #{nickName}, #{title}, #{age}, #{companyId})";

        String newSql = new SQL()
                .INSERT_INTO("PERSON")
                .INTO_COLUMNS("PERSON_ID", "PERSON_NAME", "NICK_NAME", "TITLE", "AGE", "COMPANY_ID")
                .INTO_VALUES("#{personId}", "#{personName}", "#{nickName}", "#{title}", "#{age}", "#{companyId}")
                .toString();
        Assert.assertEquals(orgSql, newSql);
    }

    @Test
    public void testDynamicBatchInsert() {
        String orgSql = "INSERT INTO PERSON\n"
                + " (PERSON_ID, PERSON_NAME, NICK_NAME, TITLE, AGE, COMPANY_ID)\n"
                + "VALUES (?, ?, ?, ?, ?, ?)\n, (?, ?, ?, ?, ?, ?)";

        String newSql = new SQL()
                .INSERT_INTO("PERSON")
                .INTO_COLUMNS("PERSON_ID", "PERSON_NAME", "NICK_NAME", "TITLE", "AGE", "COMPANY_ID")
                .INTO_VALUES("?", "?", "?", "?", "?", "?")
                .ADD_ROW()
                .INTO_VALUES("?", "?", "?", "?", "?", "?")
                .toString();
        Assert.assertEquals(orgSql, newSql);
    }

    @Test
    public void testDynamicDelete() {
        String orgSql = "DELETE FROM PERSON\n"
                + "WHERE (PERSON_ID = #{personId})";

        String newSql = new SQL()
                .DELETE_FROM("PERSON")
                .WHERE("PERSON_ID = #{personId}")
                .toString();
        Assert.assertEquals(orgSql, newSql);
    }


    @Test
    public void testDynamicUpdate() {

        String orgSql = "UPDATE PERSON\n"
                + "SET NICK_NAME = #{nickName}\n"
                + "WHERE (PERSON_ID = #{personId})";

        String newSql = new SQL()
                .UPDATE("PERSON")
                .SET("NICK_NAME = #{nickName}")
                .WHERE("PERSON_ID = #{personId}").toString();
        Assert.assertEquals(orgSql, newSql);
    }
}
