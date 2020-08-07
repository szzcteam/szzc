package com.me.szzc.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luwei
 * @date 2019/8/27
 */
public enum  CompensateTypeEnum {

    RMB_TYPE(0, "货币补偿", "货币"),
    SWAP_TYPE(1, "产权交换",  "交换"),

    ;


    private static final Map<Integer, String> descMap = new HashMap<>();

    static {
        for(CompensateTypeEnum e : CompensateTypeEnum.values()) {
            descMap.put(e.getCode(), e.getDesc());
        }
    }

    private int code;

    private String desc;

    private String shortName;


    CompensateTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    CompensateTypeEnum(int code, String desc, String shortName) {
        this.code = code;
        this.desc = desc;
        this.shortName = shortName;
    }


    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public String getShortName() {
        return shortName;
    }

    public static String getDesc(int code) {
        return descMap.get(code);
    }


    public static Map<Integer, String> getDescMap() {
        return descMap;
    }


}
