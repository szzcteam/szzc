package com.me.szzc.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by bbfang on 2019/8/24.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomChangeVo implements Serializable {
    /**
     * id
     */
    private Long id;

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
     * 点房时间
     */
    private String chooseDate;

    /**
     * 开始时间
     */
    private String startDate;


    /**
     * 结束时间
     */
    private String endDate;

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

    /**
     * 项目权限
     */
    private List<String> itemCodeList;

    /**
     * 2019/12/17 bbfang 新增字段
     * 备注
     */
    private String remark;

    private String idcard;

    private String auditFirm;

}
