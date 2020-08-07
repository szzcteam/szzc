package com.me.szzc.enums;

/**
 * 打印类型枚举
 * Created by bbfang on 2019/8/12.
 */
public enum PrintTypeEnum {

    LENGTHWAYS(0, "纵向打印"),
    CROSSWISE(1, "横向打印");

    private int code;

    private String name;

    PrintTypeEnum(int code, String name) {
        this.name = name;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}