package com.me.szzc.pojo;

import com.alibaba.excel.annotation.ExcelIgnore;
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
    @ExcelProperty(value = "房源项目", index = 0)
    private String name;

    /**
     * 房号
     */
    @ExcelProperty(value = "房号", index = 1)
    private String number;

    /**
     * 面积
     */
    @ExcelProperty(value = "预测面积", index = 2)
    private String area;

    /**
     * 实测面积
     */
    @ExcelProperty(value = "实测面积", index = 3)
    private String realArea;

    /**
     * 单价
     */
    @ExcelProperty(value = "单价", index = 4)
    private String unitPrice;

    /**
     * 预测总价
     */
    @ExcelProperty(value = "预测总价", index = 5)
    private String totalPrice;

    /**
     * 预测总价
     */
    @ExcelProperty(value = "实测总价", index = 6)
    private String realTotalPrice;

    /**
     * 分配房源
     */
    @ExcelProperty(value = "分配征收项目", index = 7)
    private String assignedProject;

    /**
     * 房源平台
     */
    @ExcelProperty(value = "提供房源平台", index = 8)
    private String housingPlatform;

    /**
     * 点房人
     */
    @ExcelProperty(value = "点房人", index = 9)
    private String choosePeople;

    /**
     * 身份证号码
     */
    @ExcelProperty(value = "身份证号码", index = 10)
    private String idcard;

    /**
     * 点房时间
     */
    @ExcelProperty(value = "点房时间", index = 11)
    private String chooseDateString;

    /**
     * 代办公司
     */
    @ExcelProperty(value = "代办公司", index = 12)
    private String commissionCompany;

    /**
     * 审计公司
     */
    @ExcelProperty(value = "审计公司", index = 13)
    private String auditFirm;

    /**
     * 状态(0-空置 1-已签 2-预留)
     */
    @ExcelProperty(value = "状态", index = 14)
    private String statusName;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注", index = 15)
    private String remark;

    /**
     * 状态(0-空置 1-已签 2-预留)
     */
    @ExcelIgnore
    private Integer status;

    /**
     * 点房时间
     */
    @ExcelIgnore
    private Date chooseDate;

}
