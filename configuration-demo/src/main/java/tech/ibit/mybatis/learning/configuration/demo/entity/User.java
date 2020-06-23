package tech.ibit.mybatis.learning.configuration.demo.entity;

import tech.ibit.mybatis.learning.configuration.demo.type.UserType;

/**
 * User
 *
 * @author IBIT程序猿
 */
public class User {

    private Integer userId;
    private String username;
    private UserType userType;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
