package com.me.szzc.pojo.entity;

public class Area {
    /**
     * 
     */
    private Long id;

    /**
     * 片区名称
     */
    private String name;

    /**
     * 状态：0启用、1禁用
     */
    private Integer status;

    /**
     * 逻辑删除标识
     */
    private Byte deleted;

    /**
     * 
     * @return id 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 片区名称
     * @return name 片区名称
     */
    public String getName() {
        return name;
    }

    /**
     * 片区名称
     * @param name 片区名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 状态：0启用、1禁用
     * @return status 状态：0启用、1禁用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态：0启用、1禁用
     * @param status 状态：0启用、1禁用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 逻辑删除标识
     * @return deleted 逻辑删除标识
     */
    public Byte getDeleted() {
        return deleted;
    }

    /**
     * 逻辑删除标识
     * @param deleted 逻辑删除标识
     */
    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }
}