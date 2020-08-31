package com.me.szzc.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bbfang on 2019/8/23.
 */
public enum ChooseStatusEnum {

    CHOOSE_STATUS_STATUS_0(0, "空置"),
    CHOOSE_STATUS_STATUS_1(1, "已签"),
    CHOOSE_STATUS_STATUS_2(2, "预留"),
    CHOOSE_STATUS_STATUS_3(3, "作废"),
    CHOOSE_STATUS_STATUS_4(4, "已交房");

    private Integer code;
    private String text;

    private static final Map<Integer, String> descMap = new HashMap<>();

    static {
        for(ChooseStatusEnum e : ChooseStatusEnum.values()) {
            descMap.put(e.getCode(), e.getText());
        }
    }

    ChooseStatusEnum(Integer code, String text) {
        this.text = text;
        this.code = code;
    }

    // 根据text获取code
    public static Integer getCode(String text) {
        for (ChooseStatusEnum t : ChooseStatusEnum.values()) {
            if (t.getText().equals(text)) {
                return t.code;
            }
        }
        return null;
    }

    // 根据code获取text
    public static String getText(Integer code) {
        for (ChooseStatusEnum t : ChooseStatusEnum.values()) {
            if (t.getCode().equals(code)) {
                return t.text;
            }
        }
        return null;
    }

    public static List<Map<String, Object>> queryAll() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (ChooseStatusEnum t : ChooseStatusEnum.values()) {
            Map<String, Object> tempMap = new HashMap<String, Object>();
            tempMap.put("code", t.getCode());
            tempMap.put("test", t.getText());
            list.add(tempMap);
        }
        return list;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static Map<Integer, String> getDescMap() {
        return descMap;
    }}
