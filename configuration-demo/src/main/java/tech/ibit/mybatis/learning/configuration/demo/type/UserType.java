package tech.ibit.mybatis.learning.configuration.demo.type;

/**
 * UserType
 *
 * @author IBIT程序猿
 */
public enum UserType implements CommonEnum {

    /**
     * 普通
     */
    NORMAL(0),

    /**
     * vip
     */
    VIP(1),
    ;

    private int value;

    UserType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }


}
