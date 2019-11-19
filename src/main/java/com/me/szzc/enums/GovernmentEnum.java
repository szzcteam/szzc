package com.me.szzc.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 政府部门
 * @author luwei
 * @date 2019/11/18
 */
public enum GovernmentEnum {

    BJSWQ("A001",  "滨江商务区"),
    MLJ("A001001", "明伦街项目"),

    GCC("B001",  "古城区"),
    ZYC("B001001", "紫阳村项目"),

    ;

    private String code;

    private String name;

    private static final Map<String, GovernmentEnum> map = new HashMap<>();
    private static final Map<String, GovernmentEnum> projectMap = new HashMap<>();


    static {
        for(GovernmentEnum governmentEnum : GovernmentEnum.values()){
            map.put(governmentEnum.getCode(), governmentEnum);

            if(governmentEnum.getCode().length() > 4){
                projectMap.put(governmentEnum.getCode(), governmentEnum);
            }
        }
    }

    GovernmentEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static String getNameByCode(String code) {
        String desc = "";
        GovernmentEnum governmentEnum = map.get(code);
        if(governmentEnum != null){
            desc = governmentEnum.getName();
        }
        return desc;
    }

    public static Map<String, GovernmentEnum> getMap() {
        return map;
    }

    public static Map<String, GovernmentEnum> getProjectMap() {
        return projectMap;
    }

    public static List<Map<String, String>> queryAll() {
        List<Map<String, String>> list = new ArrayList<>();
        for (GovernmentEnum t : GovernmentEnum.values()) {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("code", t.getCode());
            tempMap.put("test", t.getName());
            list.add(tempMap);
        }
        return list;
    }
}