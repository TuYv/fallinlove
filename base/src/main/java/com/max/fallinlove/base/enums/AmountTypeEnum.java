package com.max.fallinlove.base.enums;

/**
 * @author Rick.Tu
 * @program fallinlove
 * @description 金额类型
 * @create 2023-07-06 17:43
 **/
public enum AmountTypeEnum {

    INCOME("1", "收入"),
    SPEND("0", "支出"),
        ;

    private String type;
    private String desc;

    AmountTypeEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
