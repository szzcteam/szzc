package com.me.szzc.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.me.szzc.enums.ChooseStatusEnum;
import com.me.szzc.utils.DateHelper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 房源导出
 * Created by bbfang on 2019/7/27.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomChangeExport extends BaseRowModel implements Serializable {

    /**
     * 项目名称
     */
    @ExcelProperty(value = "房源项目", index = 1)
    private String name;

    /**
     * 房号
     */
    @ExcelProperty(value = "房号", index = 2)
    private String number;

    /**
     * 面积
     */
    @ExcelProperty(value = "面积", index = 3)
    private String area;

    /**
     * 单价
     */
    @ExcelProperty(value = "单价", index = 4)
    private String unitPrice;

    /**
     * 总价
     */
    @ExcelProperty(value = "总价", index = 5)
    private String totalPrice;

    /**
     * 分配房源
     */
    @ExcelProperty(value = "分配征收项目", index = 6)
    private String assignedProject;

    /**
     * 房源平台
     */
    @ExcelProperty(value = "提供房源平台", index = 7)
    private String housingPlatform;

    /**
     * 点房人
     */
    @ExcelProperty(value = "点房人", index = 8)
    private String choosePeople;

    /**
     * 点房时间
     */
    private Date chooseDate;

    /**
     * 点房时间
     */
    @ExcelProperty(value = "点房时间", index = 9)
    private String chooseDateString;

    /**
     * 代办公司
     */
    @ExcelProperty(value = "代办公司", index = 10)
    private String commissionCompany;

    /**
     * 状态(0-空置 1-已签 2-预留)
     */
    private Integer status;

    /**
     * 状态(0-空置 1-已签 2-预留)
     */
    @ExcelProperty(value = "状态", index = 11)
    private String statusName;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注", index = 12)
    private String remark;

}
