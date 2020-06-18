package tech.ibit.mybatis.learning.first.demo;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.logging.log4j.Log4jImpl;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Before;
import org.junit.Test;
import tech.ibit.mybatis.learning.commonlib.utils.DbUtils;
import tech.ibit.mybatis.learning.first.demo.entity.Person;
import tech.ibit.mybatis.learning.first.demo.mapper.PersonMapper;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Mybatis 例子
 *
 * @author IBIT程序猿
 */
public class MybatisTest {


    @Before
    public void initData() throws IOException, SQLException {
        try (Connection connection = DbUtils.init("jdbc:hsqldb:mem:mybatis-test", "sa", "")) {
            // do nothing
        }
    }

    @Test
    public void testGetSqlSessionFactoryWithXml() throws IOException {
        // 读取xm文件
        try (InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml")) {

            // 通过SqlSessionFactoryBuilder的build()方法创建SqlSessionFactory实例
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // 调用openSession()方法创建SqlSession实例
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {

                // 获取UserMapper代理对象
                PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);

                // 通过 SqlSession 示例获取
                Person person = sqlSession.selectOne("tech.ibit.mybatis.learning.first.demo.mapper.PersonMapper.getPersonById", 1);
                System.out.println(JSON.toJSONString(person));

                // 执行注解定义的Sql
                person = personMapper.getPersonById(1);
                System.out.println(JSON.toJSONString(person));



                List<Person> persons = sqlSession.selectList("tech.ibit.mybatis.learning.first.demo.mapper.PersonMapper.listAllPersons");
                System.out.println(JSON.toJSONString(persons));

                // 执行Xml中定义的Sql
                persons = personMapper.listAllPersons();
                System.out.println(JSON.toJSONString(persons));
            }


        }


    }


    @Test
    public void testGetSqlSessionFactoryWithoutXml() {

        // 定义 datasource
        DataSource dataSource = new UnpooledDataSource("org.hsqldb.jdbcDriver", "jdbc:hsqldb:mem:mybatis-test", "sa", "");

        // 事务
        TransactionFactory transactionFactory = new JdbcTransactionFactory();

        Environment environment = new Environment("test", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);

        // settings 相关字段
        configuration.setUseGeneratedKeys(true);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setLogImpl(Log4jImpl.class);

        // 增加mapper
        configuration.addMapper(PersonMapper.class);

        // 通过SqlSessionFactoryBuilder的build()方法创建SqlSessionFactory实例
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        // 调用openSession()方法创建SqlSession实例
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // 获取UserMapper代理对象
            PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);

            // 执行注解定义的Sql
            Person person = personMapper.getPersonById(1);
            System.out.println(JSON.toJSONString(person));


            // 执行Xml中定义的Sql
            List<Person> persons = personMapper.listAllPersons();
            System.out.println(JSON.toJSONString(persons));
        }

    }


    @Test
    public void testSessionManager() throws IOException {
        Reader mybatisConfig = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionManager sqlSessionManager = SqlSessionManager.newInstance(mybatisConfig);
        sqlSessionManager.startManagedSession();
        PersonMapper personMapper = sqlSessionManager.getMapper(PersonMapper.class);
        List<Person> persons = personMapper.listAllPersons();
        System.out.println(JSON.toJSONString(persons));
    }
}
