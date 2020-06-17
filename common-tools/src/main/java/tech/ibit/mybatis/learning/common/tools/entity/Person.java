package tech.ibit.mybatis.learning.common.tools.entity;

/**
 * Person
 *
 * @author IBIT程序猿
 * @version 2.0
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
    private int age;

    /**
     * 公司
     */
    private Company company;


    public Person() {
    }

    public Person(Integer personId, String personName, String nickName) {
        this.personId = personId;
        this.personName = personName;
        this.nickName = nickName;
    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
