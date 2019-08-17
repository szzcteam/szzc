package com.me.szzc.pojo.entity;

import java.util.List;

public class Farea {

    private  int id;

    private  String areaName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    private List list;
}
