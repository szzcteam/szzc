package com.me.szzc.pojo.entity;

import com.me.szzc.enums.AreaStatusEnum;
import lombok.Data;

@Data
public class Area {
    /**
     * 主键id
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

    /**状态描述**/
    private String statusDesc;

    /**
     * 逻辑删除标识
     */
    private Boolean deleted;

    public String getStatusDesc() {
        if(this.status == AreaStatusEnum.ENABLE.getCode()) {
            statusDesc = AreaStatusEnum.ENABLE.getDesc();
        }else{
            statusDesc = AreaStatusEnum.DISABLE.getDesc();
        }
        return statusDesc;
    }
}