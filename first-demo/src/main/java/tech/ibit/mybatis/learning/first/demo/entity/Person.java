package tech.ibit.mybatis.learning.first.demo.entity;

/**
 * Person
 *
 * @author IBIT程序猿
 */
public class Person {

    /**
     * Person ID
     */
    private Integer personId;

    /**
     * 名称
     */
    private String personName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 抬头
     */
    private String title;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 公司id
     */
    private Integer companyId;


    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}
