package com.me.szzc.pojo.entity;

import lombok.Data;

import java.util.List;

@Data
public class Area {

    /***主键id**/
    private Long id;

    /**名称**/
    private String name;

    /**状态：0启用、1禁用**/
    private Integer status;

    /**逻辑删除标识**/
    private Boolean deleted;

}
