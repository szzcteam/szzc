package com.me.szzc.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luwei
 * @date 2019/5/29
 */
public enum SigningStatusEnum {

    NOT_SIGNED(0, "未签约"),
    COMPLETE(1, "签约完成"),

    ;


    private static final Map<Integer, String> descMap = new HashMap<>();

    static {
        for(SigningStatusEnum e : SigningStatusEnum.values()) {
            descMap.put(e.getCode(), e.getDesc());
        }
    }

    private int code;

    private String desc;


    SigningStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static String getDesc(int code) {
        return descMap.get(code);
    }


    public static Map<Integer, String> getDescMap() {
        return descMap;
    }}
