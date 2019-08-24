package com.me.szzc.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by bbfang on 2019/8/24.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomChangeVo implements Serializable {

    /**
     * 片区项目名称
     */
    private String name;
    /**
     * 房号
     */
    private String number;
    /**
     * 点房人
     */
    private String choosePeople;
    /**
     * 分配征收项目(片区)
     */
    private String assignedProject;
    /**
     * 提供房源平台
     */
    private String housingPlatform;
    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private Date startDate;
    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private Date endDate;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 最小面积
     */
    private String minArea;
    /**
     * 最大面积
     */
    private String maxArea;
    /**
     * 代办公司
     */
    private String commissionCompany;
    private Integer pageSize;
    private Integer pageNum;
    private Integer start;

}
