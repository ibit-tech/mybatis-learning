package tech.ibit.mybatis.learning.common.tools.entity;


import java.util.List;

/**
 * Company
 *
 * @author IBIT程序猿
 */
public class Company {

    /**
     * 公司id
     */
    private Integer companyId;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 人
     */
    private List<Person> persons;


    public Company(Integer companyId, String companyName, String city) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.city = city;
    }


    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
