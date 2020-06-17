package tech.ibit.mybatis.learning.common.tools;

import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaClass;
import org.apache.ibatis.reflection.invoker.Invoker;
import org.junit.Test;
import tech.ibit.mybatis.learning.common.tools.entity.Person;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * MetaClass 例子
 *
 * @author IBIT程序猿
 */
public class MetaClassTest {

    @Test
    public void testMetaClass() {

        MetaClass metaClass = MetaClass.forClass(Person.class, new DefaultReflectorFactory());

        // 获取所有有Getter方法的属性名
        String[] getterNames = metaClass.getGetterNames();

        System.out.println(Arrays.asList(getterNames));

        // 是否有默认构造方法
        System.out.println("是否有默认构造方法：" + metaClass.hasDefaultConstructor());

        // 某属性是否有对应的Getter/Setter方法
        System.out.println("personName属性是否有对应的Getter方法：" + metaClass.hasGetter("personName"));
        System.out.println("personName属性是否有对应的Setter方法：" + metaClass.hasSetter("personName"));

        System.out.println("personName属性类型：" + metaClass.getGetterType("personName"));

        Person person = new Person() {{
            setPersonId(1);
            setPersonName("小ben马");
            setNickName("小ben马");
            setTitle("后端开发");
            setAge(29);
        }};

        // 获取属性Getter方法
        Invoker invoker = metaClass.getGetInvoker("personName");
        try {
            // 通过Invoker对象调用Getter方法获取属性值
            Object personName = invoker.invoke(person, null);
            System.out.println(personName);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        // 获取属性Setter方法
        invoker = metaClass.getSetInvoker("personName");
        try {
            // 通过Invoker对象调用Getter方法获取属性值
            invoker.invoke(person, new String[]{"小马"});
            System.out.println(person.getPersonName());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
