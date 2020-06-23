package tech.ibit.mybatis.learning.configuration.demo;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import tech.ibit.mybatis.learning.commonlib.utils.DbUtils;
import tech.ibit.mybatis.learning.configuration.demo.entity.Person;
import tech.ibit.mybatis.learning.configuration.demo.entity.User;
import tech.ibit.mybatis.learning.configuration.demo.mapper.PersonMapper;
import tech.ibit.mybatis.learning.configuration.demo.mapper.UserMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Configuration 例子
 *
 * @author IBIT程序猿
 */
public class ConfigurationTest {

    @Before
    public void initData() throws IOException, SQLException {
        try (Connection connection = DbUtils.init("jdbc:hsqldb:mem:mybatis-test", "sa", "")) {
            // do nothing
        }
    }


    @Test
    public void testProperties() {
        test("mybatis-config.xml");
    }


    @Test
    public void testProperties2() {

        // 读取xm文件
        try (Reader reader = Resources.getResourceAsReader("mybatis-config2.xml")) {

            Properties properties = Resources.getResourceAsProperties("db.properties");

            // 通过SqlSessionFactoryBuilder的build()方法创建SqlSessionFactory实例
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, properties);

            // 调用openSession()方法创建SqlSession实例
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {

                // 获取UserMapper代理对象
                PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
                Person person = personMapper.getPersonById(1);
                System.out.println(JSON.toJSONString(person));

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testProperties3() {

        test("mybatis-config3.xml");

    }

    @Test
    public void testProperties4() {

        test("mybatis-config4.xml");

    }

    @Test
    public void testProperties5() {

        // 读取xm文件
        try (InputStream inputStream = Resources.getResourceAsStream("mybatis-config5.xml")) {

            // 通过SqlSessionFactoryBuilder的build()方法创建SqlSessionFactory实例
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // 调用openSession()方法创建SqlSession实例
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {

                // 获取UserMapper代理对象
                UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
                User user = userMapper.getUserById(1);
                System.out.println(JSON.toJSONString(user));

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void test(String configPath) {
        // 读取xm文件
        try (InputStream inputStream = Resources.getResourceAsStream(configPath)) {

            // 通过SqlSessionFactoryBuilder的build()方法创建SqlSessionFactory实例
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // 调用openSession()方法创建SqlSession实例
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {

                // 获取UserMapper代理对象
                PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
                Person person = personMapper.getPersonById(1);
                System.out.println(JSON.toJSONString(person));

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
