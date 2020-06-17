package tech.ibit.mybatis.learning.common.tools;

import org.apache.ibatis.executor.loader.ProxyFactory;
import org.apache.ibatis.executor.loader.ResultLoaderMap;
import org.apache.ibatis.executor.loader.cglib.CglibProxyFactory;
import org.apache.ibatis.executor.loader.javassist.JavassistProxyFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.session.Configuration;
import org.junit.Test;
import tech.ibit.mybatis.learning.common.tools.entity.Person;

import java.util.Arrays;


/**
 * ProxyFactory 例子
 *
 * @author IBIT程序猿
 */
public class ProxyFactoryTest {


    @Test
    public void testJavassistProxyFactory() {
        // 创建ProxyFactory对象
        ProxyFactory proxyFactory = new JavassistProxyFactory();
        Person person = new Person(1, "小ben马", "小ben马");
        ObjectFactory objectFactory = new DefaultObjectFactory();

        // 调用ProxyFactory对象的createProxy（）方法创建代理对象
        Object proxyOrder = proxyFactory.createProxy(
                person,
                new ResultLoaderMap(),
                new Configuration(),
                objectFactory,
                Arrays.asList(Integer.class, String.class, String.class),
                Arrays.asList(person.getPersonId(), person.getPersonName(), person.getNickName())
        );
        System.out.println(proxyOrder.getClass());
        System.out.println(((Person) proxyOrder).getPersonName());
    }


    @Test
    public void testCglibProxyFactory() {
        // 创建ProxyFactory对象
        ProxyFactory proxyFactory = new CglibProxyFactory();
        Person person = new Person(1, "小ben马", "小ben马");
        ObjectFactory objectFactory = new DefaultObjectFactory();

        // 调用ProxyFactory对象的createProxy（）方法创建代理对象
        Object proxyOrder = proxyFactory.createProxy(
                person,
                new ResultLoaderMap(),
                new Configuration(),
                objectFactory,
                Arrays.asList(Integer.class, String.class, String.class),
                Arrays.asList(person.getPersonId(), person.getPersonName(), person.getNickName())
        );
        System.out.println(proxyOrder.getClass());
        System.out.println(((Person) proxyOrder).getPersonName());
    }
}
