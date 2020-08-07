package com.me.szzc.enums;

/**
 * 打印表枚举类
 * Created by bbfang on 2019/8/12.
 */
public enum PrintTableEnum {

    HOUSE_EXPROPRIATION_COMPENSATION(1, "结算单"),
    HOUSE_RMB_RECOMPENSE(2, "货币补偿"),
    HOUSE_SWAP(3, "产权调换");

    private int code;

    private String name;

    PrintTableEnum(int code, String name) {
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



