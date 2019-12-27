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
    XCH("B001002", "西城壕项目"),

    HZJRC("C001", "华中金融城"),
    DZRGS("C001001", "电车二公司南侧地块项目"),



    ;

    private String code;

    private String name;

    private static final Map<String, String> map = new HashMap<>();
    private static final Map<String, String> projectMap = new HashMap<>();


    static {
        for(GovernmentEnum governmentEnum : GovernmentEnum.values()){
            map.put(governmentEnum.getCode(), governmentEnum.getName());

            if(governmentEnum.getCode().length() > 4){
                projectMap.put(governmentEnum.getCode(), governmentEnum.getName());
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
        String desc = map.get(code);
        return desc;
    }

    public static Map<String, String> getMap() {
        return map;
    }

    public static Map<String, String> getProjectMap() {
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
