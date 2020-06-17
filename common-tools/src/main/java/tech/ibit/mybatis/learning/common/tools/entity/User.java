package tech.ibit.mybatis.learning.common.tools.entity;

/**
 * User
 *
 * @author IBIT程序猿
 */
public class User {

    /**
     * Person ID
     */
    private Integer userId;

    /**
     * 名称
     */
    private String username;


    public User(Integer userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public Integer getUserId1() {
        return userId;
    }

    public void setUserId1(Integer userId) {
        this.userId = userId;
    }

    public String getUsername1() {
        return username;
    }

    public void setUsername1(String username) {
        this.username = username;
    }
}
