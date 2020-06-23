package tech.ibit.mybatis.learning.configuration.demo.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tech.ibit.mybatis.learning.configuration.demo.entity.Person;

import java.util.List;

/**
 * PersonMapper
 *
 * @author IBIT程序猿
 */
public interface PersonMapper {


    /**
     * 通过主键获取Person
     *
     * @param personId personId
     * @return Person
     */
    @Select("select * from person where person_id=#{personId,jdbcType=INTEGER}")
    Person getPersonById(@Param("personId") Integer personId);


    /**
     * 列举所有Person
     *
     * @return Person列表
     */
    List<Person> listAllPersons();

}
