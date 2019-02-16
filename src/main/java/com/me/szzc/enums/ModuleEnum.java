package com.me.szzc.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zh on 16-10-20.
 */
public enum ModuleEnum {

    PROTOCOL_OPERATION(ModuleConstont.PROTOCOL_OPERATION, "协议管理"),
    SYSTEM_OPERATION(ModuleConstont.SYSTEM_OPERATION,"系统管理");

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    ModuleEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private static Map<String, String> map = new HashMap<String, String>();

    static {
        for (ModuleEnum moduleEnum : ModuleEnum.values()) {
            map.put( moduleEnum.code,moduleEnum.desc);
        }
    }

    public static Map<String, String> getMap() {
        return map;
    }

    public static void setMap(Map<String, String> map) {
        ModuleEnum.map = map;
    }

    public static List<String> getAllDesc(){
        List<String> allDesc = new ArrayList<String>();
        for (ModuleEnum moduleEnum : ModuleEnum.values()) {
            allDesc.add(moduleEnum.desc);
        }
        return allDesc;
    }

}
