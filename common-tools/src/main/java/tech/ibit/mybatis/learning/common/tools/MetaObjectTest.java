package tech.ibit.mybatis.learning.common.tools;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.junit.Test;
import tech.ibit.mybatis.learning.common.tools.entity.Company;
import tech.ibit.mybatis.learning.common.tools.entity.Person;
import tech.ibit.mybatis.learning.common.tools.entity.User;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * MetaObject 例子
 *
 * @author IBIT程序猿
 */
public class MetaObjectTest {

    @Test
    public void testMetaObject() {

        Company company = new Company(1, "IBIT程序猿联盟", "深圳");

        Person person1 = new Person() {{
            setPersonId(1);
            setPersonName("小ben马");
            setNickName("小ben马");
            setTitle("后端开发");
            setAge(29);
            setCompany(company);
        }};

        Person person2 = new Person() {{
            setPersonId(2);
            setPersonName("IBIT程序猿");
            setNickName("IBIT程序猿");
            setTitle("后端开发");
            setAge(29);
            setCompany(company);
        }};

        company.setPersons(Arrays.asList(person1, person2));

        MetaObject metaObject = SystemMetaObject.forObject(company);

        // 获取值
        assertEquals("小ben马", metaObject.getValue("persons[0].personName"));
        assertEquals("IBIT程序猿", metaObject.getValue("persons[1].personName"));

        // 设置值
        metaObject.setValue("persons[0].personName", "小马");
        assertEquals("小马", metaObject.getValue("persons[0].personName"));
        assertEquals("小马", person1.getPersonName());

        // 判断是否有getter方法
        assertTrue(metaObject.hasGetter("companyName"));
        assertTrue(metaObject.hasGetter("persons[0].personName"));
        assertFalse(metaObject.hasGetter("companyName2"));

        // 判断是否有setter方法
        assertTrue(metaObject.hasSetter("companyName"));
        assertTrue(metaObject.hasGetter("persons[0].personName"));
        assertFalse(metaObject.hasGetter("companyName2"));

        System.out.println(Arrays.asList(metaObject.getGetterNames()));
        System.out.println(Arrays.asList(metaObject.getSetterNames()));


        System.out.println(metaObject.getGetterType("companyName"));
        System.out.println(metaObject.getSetterType("companyName"));
    }

    @Test
    public void testMetaObject2() {
        User user = new User(1, "IBIT程序猿");

        MetaObject metaObject = SystemMetaObject.forObject(user);

        // 只有属性，没有对应方法
        assertTrue(metaObject.hasGetter("userId"));
        assertEquals(1, metaObject.getValue("userId"));
        assertEquals("IBIT程序猿", metaObject.getValue("username"));

        // 只有方法，没有对应属性
        assertTrue(metaObject.hasGetter("userId1"));
        assertEquals(1, metaObject.getValue("userId1"));
        assertEquals("IBIT程序猿", metaObject.getValue("username1"));

        // 只有属性，没有对应方法
        assertTrue(metaObject.hasSetter("username"));
        metaObject.setValue("username", "IBIT");
        assertEquals("IBIT", user.getUsername1());
        assertEquals("IBIT", metaObject.getValue("username"));

        // 只有方法，没有对应属性
        assertTrue(metaObject.hasSetter("username1"));
        metaObject.setValue("username1", "程序猿");
        assertEquals("程序猿", user.getUsername1());
        assertEquals("程序猿", metaObject.getValue("username1"));

        System.out.println(Arrays.asList(metaObject.getGetterNames()));
        System.out.println(Arrays.asList(metaObject.getSetterNames()));

    }
}
