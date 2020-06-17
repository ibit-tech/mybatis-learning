package tech.ibit.mybatis.learning.common.tools.factory;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import tech.ibit.mybatis.learning.common.tools.entity.Person;

import java.util.Properties;
import java.util.UUID;

/**
 * MetaClass 例子
 *
 * @author IBIT程序猿
 */
public class PersonObjectFactory extends DefaultObjectFactory {

    /**
     * 自定义配置
     */
    private Properties properties;

    @Override
    public Object create(Class type) {
        if (type.equals(Person.class)) {
            Person person = (Person) super.create(type);
            person.setNickName(UUID.randomUUID().toString());
            return person;
        }
        return super.create(type);
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
