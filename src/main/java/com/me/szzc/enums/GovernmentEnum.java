package com.me.szzc.enums;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.me.szzc.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 政府部门
 * @author luwei
 * @date 2019/11/18
 */
@Slf4j
public enum GovernmentEnum {

    ROOT("root", "admin"),

    WCQ("wcq", "武昌区",  GovernmentEnum.ROOT.getCode()),
    BJSWQ("A001", "滨江商务区", GovernmentEnum.WCQ.getCode()),
    MLJ("A001001", "明伦街", GovernmentEnum.BJSWQ.getCode()),

    GCC("B001", "古城区", GovernmentEnum.WCQ.getCode()),
    ZYC("B001001", "紫阳村", GovernmentEnum.GCC.getCode()),
    XCH("B001002", "西城壕", GovernmentEnum.GCC.getCode()),
    QNGZ("B001003", "千年古轴", GovernmentEnum.GCC.getCode()),

    HZJRC("C001", "华中金融城", GovernmentEnum.WCQ.getCode()),
    DZRGS("C001001", "电车二公司南侧地块", GovernmentEnum.HZJRC.getCode()),
    JYQC("C001002", "积玉桥C扩大", GovernmentEnum.HZJRC.getCode()),

    WCJSJ("D001", "武昌区建设局", GovernmentEnum.WCQ.getCode()),
    WCEFC("D001001", "武船二分厂宿舍", "武船二分厂宿舍区旧城改建", GovernmentEnum.WCJSJ.getCode()),
    WCQBXFY("D001002", "武昌区建设局备选房源", GovernmentEnum.WCJSJ.getCode()),
    WCQBXFY2("D001003", "武昌区建设局备选房源二", GovernmentEnum.WCJSJ.getCode()),

    QKQ("qkq", "硚口区", GovernmentEnum.ROOT.getCode()),
    ;

    private String code;

    private String name;

    /** 项目完整描述 **/
    private String desc;

    private String parentCode;

    /**项目管理区 一级及项目**/
    private static final Map<String, String> mgtMap = new HashMap<>();

    /**最具体的项目**/
    private static final Map<String, String> projectMap = new HashMap<>();

    private static final Map<String, GovernmentEnum> allNameMap = new HashMap<>();

    //所有
    private static final List<GovernmentEnum> allList = new ArrayList<>();

    static {
        for (GovernmentEnum governmentEnum : GovernmentEnum.values()) {
            if (governmentEnum.getCode().length() >= 4) {
                mgtMap.put(governmentEnum.getCode(), governmentEnum.getName());
            }


            if (governmentEnum.getCode().length() > 4) {
                projectMap.put(governmentEnum.getCode(), governmentEnum.getName());
            }

            allNameMap.put(governmentEnum.getName(), governmentEnum);
            allList.add(governmentEnum);
        }
    }


    GovernmentEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    GovernmentEnum(String code, String name, String parentCode) {
        this.code = code;
        this.name = name;
        this.parentCode = parentCode;
    }

    GovernmentEnum(String code, String name, String desc , String parentCode) {
        this.code = code;
        this.name = name;
        this.desc = desc;
        this.parentCode = parentCode;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getParentCode() {
        return parentCode;
    }

    public static String getNameByCode(String code) {
        String desc = mgtMap.get(code);
        return desc;
    }

    public static String getCodeByName(String name) {
        String code = "";
        for (GovernmentEnum gov : allList) {
            if (gov.getName().equals(name)) {
                code = gov.getCode();
                break;
            }
        }
        return code;
    }

    public static GovernmentEnum getByCode(String code) {
        GovernmentEnum gov = null;
        for (GovernmentEnum government : allList) {
            if (government.getCode().equals(code)) {
                gov = government;
                break;
            }
        }
        if (gov == null) {
            gov = GovernmentEnum.WCEFC;
        }
        return gov;
    }

    public static Map<String, String> getMgtMap() {
        return mgtMap;
    }

    public static Map<String, String> getProjectMap() {
        return projectMap;
    }



    public static void main(String[] args) {
       /* JSONObject object = getScope("admin");
        System.out.println("权限：" + JSONObject.toJSONString(object, true));*/

        List<String> list = getProjectByScope("硚口区");
        System.out.println("项目：" + JSONObject.toJSONString(list, true));
    }

    //根据范围名称，获取管辖权限下的所有项目列表
    public static List<String> getProjectByScope(String scopeName){
        List<String> list = new ArrayList<>();

        GovernmentEnum governmentEnum = allNameMap.get(scopeName);
        if(governmentEnum == null){
            log.error("获取权限信息参数错误, scopeName:{}", scopeName);
            return null;
        }


        JSONArray jsonArray = getChild(governmentEnum.getCode());
        getProejctName(jsonArray, list);
        return list;
    }

    private static void getProejctName(JSONArray jsonArray, List<String> list){
        if(jsonArray == null ||  jsonArray.size() == 0){
            return;
        }
        for(int i=0;i<jsonArray.size();i++){
            JSONObject object = jsonArray.getJSONObject(i);
            if(object.getString("code").length() > 4){
                list.add(object.getString("name"));
            }

            JSONArray arr = object.getJSONArray("child");
            if(arr != null){
                getProejctName(arr, list);
            }
        }
    }


    /**
     * 根据范围名称，获取对应的权限信息
     * @param scopeName  admin 、武昌区、硚口区
     * @return
     */
    public static JSONObject getScope(String scopeName){
        if(StringUtils.isBlank(scopeName)){
            return null;
        }

        GovernmentEnum governmentEnum = allNameMap.get(scopeName);
        if(governmentEnum == null){
            log.error("获取权限信息参数错误, scopeName:{}", scopeName);
            return null;
        }

        JSONObject object = new JSONObject();
        object.put("code", governmentEnum.getCode());
        object.put("name", governmentEnum.getName());

        JSONArray jsonArray = getChild(governmentEnum.getCode());
        if(jsonArray != null && jsonArray.size() > 0 ){
            object.put("child", jsonArray);
        }

        return object;
    }

    /**根据父元素获取所有嵌套的子元素**/
    private static JSONArray getChild(String parentCode) {
        JSONArray arr = new JSONArray();
        for (GovernmentEnum gov : allList) {
            if (gov.getParentCode() == null) {
                continue;
            }
            if (gov.getParentCode().equals(parentCode)) {
                JSONObject object = new JSONObject();
                object.put("code", gov.getCode());
                object.put("name", gov.getName());
                JSONArray child = getChild(gov.getCode());
                if (child != null && child.size() > 0) {
                    object.put("child", child);
                }
                arr.add(object);
            }
        }

        return arr;
    }
}
