package tech.ibit.mybatis.learning.common.tools;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.junit.Test;
import tech.ibit.mybatis.learning.common.tools.entity.Person;

import java.util.Arrays;


/**
 * ObjectFactoryTest
 *
 * @author IBIT程序猿
 */
public class ObjectFactoryTest {



    @Test
    public void testCreate() {

        ObjectFactory objectFactory = new DefaultObjectFactory();
        Person person = objectFactory.create(Person.class);

        System.out.println(person.getPersonName());


        person = objectFactory.create(Person.class,
                Arrays.asList(Integer.class, String.class, String.class),
                Arrays.asList(1, "小ben马", "小ben马"));

        System.out.println(person.getPersonName());

    }
}
