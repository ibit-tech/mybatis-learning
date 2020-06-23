package tech.ibit.mybatis.learning.configuration.demo.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tech.ibit.mybatis.learning.configuration.demo.entity.User;

/**
 * PersonMapper
 *
 * @author IBIT程序猿
 */
public interface UserMapper {


    /**
     * 通过主键获取Person
     *
     * @param userId personId
     * @return Person
     */
    @Select("select * from user where user_id=#{userId,jdbcType=INTEGER}")
    User getUserById(@Param("userId") Integer userId);


}
