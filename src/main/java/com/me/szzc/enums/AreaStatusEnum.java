package com.me.szzc.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 片区状态
 * @author luwei
 * @date 2019/5/29
 */
public enum AreaStatusEnum {

    ENABLE(0, "启用"),
    DISABLE(1, "禁用"),

    ;


    private static final Map<Integer, String> descMap = new HashMap<>();

    static {
        for(AreaStatusEnum e : AreaStatusEnum.values()) {
            descMap.put(e.getCode(), e.getDesc());
        }
    }

    private int code;

    private String desc;


    AreaStatusEnum(int code, String desc) {
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
