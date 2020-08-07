package com.me.szzc.pojo.entity;

import com.me.szzc.enums.AreaStatusEnum;
import com.me.szzc.utils.DateHelper;
import lombok.Data;

import java.util.Date;

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
     * 项目code
     */
    private String projectCode;

    /**
     * 项目名称
     */
    private String projectName;

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

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 创建人id
     */
    private Long createUserId;

    /**
     * 修改时间
     */
    private Date modifiedDate;

    /**修改时间字符串格式**/
    private String modifiedDateStr;


    /**
     * 修改人员id
     */
    private Long modifiedUserId;

    public String getStatusDesc() {
        if(this.status == AreaStatusEnum.ENABLE.getCode()) {
            statusDesc = AreaStatusEnum.ENABLE.getDesc();
        }else{
            statusDesc = AreaStatusEnum.DISABLE.getDesc();
        }
        return statusDesc;
    }

    public String getModifiedDateStr() {
        modifiedDateStr = DateHelper.date2String(modifiedDate, DateHelper.DateFormatType.YearMonthDay_HourMinuteSecond);
        return modifiedDateStr;
    }
}