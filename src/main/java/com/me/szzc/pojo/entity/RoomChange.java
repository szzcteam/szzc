package com.me.szzc.pojo.entity;

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
 * 选房类
 * Created by bbfang on 2019/7/27.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomChange extends BaseRowModel implements Serializable {

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
     * 主键自增长id
     */
    private Long id;

    /**
     * 栋
     */
    private String ridgepole;

    /**
     * 单元
     */
    private String unit;

    /**
     * 楼层
     */
    private String floor;

    /**
     * 号
     */
    private String mark;

    /**
     * 点房人
     */
    private String choosePeople;

    /**
     * 状态(0-空置 1-已签 2-预留)
     */
    private Integer status;

    private String statusDesc;

    /**
     * 代办公司
     */
    private String commissionCompany;

    /**
     * 点房时间
     */
    private Date chooseDate;

    /**
     * 项目编码
     */
    private String itemCode;

    /**
     * TODO 2019/12/17 bbfang 新增字段
     * 备注
     */
    private String remark;

    /**点房时间-字符串格式**/
    private String chooseDateStr;

    public String getStatusDesc() {
        if(status != null){
            statusDesc = ChooseStatusEnum.getDescMap().get(status);
        }
        return statusDesc;
    }

    public String getChooseDateStr() {
        if(chooseDate == null){
            return "";
        }
        return DateHelper.date2String(chooseDate, DateHelper.DateFormatType.YearMonthDay_HourMinuteSecond);
    }
}
