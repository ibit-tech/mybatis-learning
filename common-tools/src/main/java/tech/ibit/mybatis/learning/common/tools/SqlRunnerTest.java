package tech.ibit.mybatis.learning.common.tools;

import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.jdbc.SqlRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tech.ibit.mybatis.learning.commonlib.utils.DbUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * SqlRunner 例子
 *
 * @author IBIT程序猿
 */
public class SqlRunnerTest {

    private Connection connection = null;

    @Before
    public void init() throws SQLException, IOException {
        connection = DbUtils.init("jdbc:hsqldb:mem:mybatis-test", "sa", "");
    }

    @After
    public void destroy() throws SQLException {
        DbUtils.close(connection);
    }

    @Test
    public void testSelectOne() throws SQLException {
        SqlRunner sqlRunner = new SqlRunner(connection);
        String sql = new SQL()
                .SELECT("*")
                .FROM("person")
                .WHERE("person_id = ?")
                .toString();

        Map<String, Object> resultMap = sqlRunner.selectOne(sql, 1);
        System.out.println(resultMap);
    }

    @Test
    public void testSelectAll() throws SQLException {
        SqlRunner sqlRunner = new SqlRunner(connection);
        String sql = new SQL()
                .SELECT("*")
                .FROM("person")
                .WHERE("company_id = ?")
                .toString();

        List<Map<String, Object>> results = sqlRunner.selectAll(sql, 1);
        System.out.println(results);
    }


    @Test
    public void testSelectAll2() throws SQLException {
        SqlRunner sqlRunner = new SqlRunner(connection);
        String sql = new SQL()
                .SELECT("*")
                .FROM("person")
                .WHERE("company_id = ?")
                .OFFSET_ROWS(1)
                .FETCH_FIRST_ROWS_ONLY(2)
                .toString();

        List<Map<String, Object>> results = sqlRunner.selectAll(sql, 1);
        System.out.println(results);
    }

    @Test
    public void testDelete() throws SQLException {
        SqlRunner sqlRunner = new SqlRunner(connection);
        String deleteUserSql = new SQL()
                .DELETE_FROM("person")
                .WHERE("person_id = ?")
                .toString();
        sqlRunner.delete(deleteUserSql, 1);

        System.out.println("查询\"person_id=1\": " + getPersonById(1));
    }

    @Test
    public void testUpdate() throws SQLException {
        SqlRunner sqlRunner = new SqlRunner(connection);
        String updateUserSql = new SQL()
                .UPDATE("person")
                .SET("nick_name = ?")
                .WHERE("person_id = ?")
                .toString();
        sqlRunner.update(updateUserSql, "三哥", 1);

        System.out.println("查询\"person_id=1\": " + getPersonById(1));
    }

    @Test
    public void testInsert() throws SQLException {
        SqlRunner sqlRunner = new SqlRunner(connection);
        String insertUserSql = new SQL()
                .INSERT_INTO("person")
                .INTO_COLUMNS("person_name, nick_name, title, age, company_id")
                .INTO_VALUES("?,?,?,?,?")
                .toString();
        sqlRunner.setUseGeneratedKeySupport(true);
        int result = sqlRunner.insert(insertUserSql, "久九", "小九", "产品助理", 20, 1);

        System.out.println("插入对象: " + getPersonById(result));
    }

    private Map<String, Object> getPersonById(Integer personId) throws SQLException {
        SqlRunner sqlRunner = new SqlRunner(connection);
        String sql = new SQL()
                .SELECT("*")
                .FROM("person")
                .WHERE("person_id = ?")
                .toString();

        List<Map<String, Object>> results = sqlRunner.selectAll(sql, personId);
        return results.isEmpty() ? null : results.get(0);
    }


}
