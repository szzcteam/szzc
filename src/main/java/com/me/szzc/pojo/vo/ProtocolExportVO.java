package com.me.szzc.pojo.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 协议导出
 * @author luwei
 * @date 2019/9/7
 */
@Data
public class ProtocolExportVO extends BaseRowModel implements Comparable<ProtocolExportVO>, Serializable {

    @ExcelProperty(value ={ "房屋征收补偿明细表", "序号", "序号", "序号"}, index = 0)
    private Integer index;

    /**
     * 协议编号
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "协议编号", "协议编号", "协议编号"}, index = 1)
    private String cardNo;

    @ExcelProperty(value = {"房屋征收补偿明细表", "片区", "片区", "片区"}, index = 2)
    private String areaName;


    /**
     * 被征收人
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "被征收人姓名", "被征收人姓名", "被征收人姓名"}, index = 3)
    private String houseOwner;

    @ExcelProperty(value = {"房屋征收补偿明细表", "签约状态", "签约状态", "签约状态"}, index = 4)
    private String signingStatusDesc;

    @ExcelProperty(value = {"房屋征收补偿明细表", "签约时间", "签约时间", "签约时间"}, index = 5)
    private String signingDateStr;

    @ExcelProperty(value = {"房屋征收补偿明细表", "协议类型", "协议类型", "协议类型"}, index = 6)
    private String protocolType;

    /**
     * 房屋地址
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "座落地址", "座落地址", "座落地址"}, index = 7)
    private String address;

    /**
     * 身份证号
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "身份证号", "身份证号", "身份证号"}, index = 8)
    private String identityNo;

    @ExcelProperty(value = {"房屋征收补偿明细表", "联系电话", "联系电话", "联系电话"}, index = 9)
    private String phone;

    /**
     * 房屋所属权证号
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "房产证号", "房产证号", "房产证号"}, index = 10)
    private String houseOwnerNumber;

    /**
     * 国有土地使用权证号
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "土地证号", "土地证号", "土地证号"}, index = 11)
    private String publicOwnerNumber;

    /**
     * 房屋权属份额
     */
    @ExcelProperty(value = {"房屋征收补偿明细表","房屋权属份额", "房屋权属份额", "房屋权属份额"}, index = 12)
    private String proportion;

    /**
     * 房屋用途
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "房屋用途", "房屋用途", "房屋用途"}, index = 13)
    private String useing;


    /**有证建筑面积**/
    @ExcelProperty(value = {"房屋征收补偿明细表", "有证房屋","建筑面积㎡", "建筑面积㎡"}, index = 14)
    private String certifiedArea;

    /**
     * 证载房屋评估单价
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "有证房屋", "评估单价", "评估单价"}, index = 15)
    private String assessPrice;

    /**
     * 有证房屋补偿金额
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "有证房屋", "房屋价值补偿", "房屋价值补偿"}, index = 16)
    private String valueCompensate;

    /**
     * 未登记的合法建筑面积
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "未经登记合法建筑", "建筑面积㎡", "建筑面积㎡"}, index = 17)
    private String noRegisterLegalArea;

    /**
     * 未登记房屋评估单价
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "未经登记合法建筑", "评估单价", "评估单价"}, index = 18)
    private String noRegisterAssessPrice;


    /**
     * 未登记的合法建筑补偿金额
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "未经登记合法建筑", "房屋价值补偿", "房屋价值补偿"}, index = 19)
    private String noRegisterLegal;


    /**
     * 历史遗留面积
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "历史遗留无证房", "建筑面积㎡", "建筑面积㎡"}, index = 20)
    private String historyLegacyArea;

    /**
     * 历史遗留房屋评估单价
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "历史遗留无证房", "评估单价", "评估单价"}, index = 21)
    private String historyAssessPrice;


    /**
     * 历史遗留无证房屋补偿金额
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "历史遗留无证房", "房屋价值补偿", "房屋价值补偿"}, index = 22)
    private String historyLegacy;

    /**
     * 装饰装修补偿金额
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "装修单价", "装修单价", "装修单价"}, index = 23)
    private String decorationCompensateUnitPrice;


    /**
     * 装饰装修补偿金额
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "装修折旧补偿", "装修折旧补偿", "装修折旧补偿"}, index = 24)
    private String decorationCompensate;


    /**
     * 搬家费
     */
    @ExcelProperty(value = {"房屋征收补偿明细表","搬迁补偿(搬家费)", "搬迁补偿(搬家费)", "搬迁补偿(搬家费)"}, index = 25)
    private String moveHouseFee;

    /**
     * 过渡费
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "临时安置补偿(过渡费)", "临时安置补偿(过渡费)", "临时安置补偿(过渡费)"}, index = 26)
    private String interimFee;

    /**
     * 保底补偿金额
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "保底补偿", "保底补偿", "保底补偿"}, index = 27)
    private String guarantee;

    /**
     * 停产停业损失补偿
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "停产停业损失补偿","停产停业损失补偿", "停产停业损失补偿"}, index = 28)
    private String suspendBusinessFee;

    /**
     * 水表迁移费
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "附属设施","水表迁移费", "水表迁移费"}, index = 29)
    private String moveWaterMeterFee;

    /**
     * 电表迁移费
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "附属设施", "电表迁移费", "电表迁移费"}, index = 30)
    private String moveAmmeterFee;


    /**
     * 空调移机费
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "附属设施", "空调移机费", "空调移机费"}, index = 31)
    private String moveAirConditioningFee;

    /**
     * 太阳能热水器移机费
     */
    @ExcelIgnore
    private String solarHeater;

    /**
     * 其他热水器移机费
     */
    @ExcelIgnore
    private String otherHeater;

    @ExcelProperty(value = {"房屋征收补偿明细表", "附属设施", "热水器拆装费", "热水器拆装费"}, index = 32)
    private String heater;


    /**
     * 管道燃气拆装费
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "附属设施","管道燃气拆装费","管道燃气拆装费"}, index = 33)
    private String gasFee;

    /**
     * 热水器拆装补偿
     */
    @ExcelIgnore
    private String hotWaterCompensate;



    /**
     * 结构设计内封闭阳台补偿费
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "附属设施", "构筑物补偿", "结构内阳台"}, index = 34)
    private String structureBalcony;

    /**
     * 暗楼补偿费
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "附属设施", "构筑物补偿", "居住暗楼"}, index = 35)
    private String structureDark;

    /**
     * 夹层补偿费
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "附属设施", "构筑物补偿", "夹层（假层）"}, index = 36)
    private String structureMezzanine;

    /**
     * 外挑搭建补偿费
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "附属设施", "构筑物补偿", "外挑搭建"}, index = 37)
    private String structureBuild;

    /**
     * 顶楼搭建补偿费
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "附属设施", "构筑物补偿", "楼顶搭建"}, index = 38)
    private String structureRoof;

    /**
     * 住改商补助
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "住改商补助", "住改商补助", "住改商补助"}, index = 39)
    private String changeCompensate;

    /**
     * 生活困难补助
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "生活困难补助", "生活困难补助", "生活困难补助"}, index = 40)
    private String lifeCompensate;



    /**
     * 产权调换时的补偿货币金额
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "货币补偿补助（或产权调换补偿）", "货币补偿补助（或产权调换补偿）", "货币补偿补助（或产权调换补偿）"}, index = 41)
    private String rmbCompensate;

    /**
     * 小户型住房困难补助
     */
    @ExcelProperty(value = {"房屋征收补偿明细表","小户型住房困难补助","小户型住房困难补助","小户型住房困难补助"}, index = 42)
    private String smallAreaReward;
    /**
     * 建筑面积补助
     */
    @ExcelProperty(value = {"房屋征收补偿明细表","建筑面积补助", "建筑面积补助", "建筑面积补助"}, index = 43)
    private String buildingAreaFee;

    /**
     * 按期签约搬迁奖励
     */
    @ExcelProperty(value = {"房屋征收补偿明细表","征收奖励","征收奖励","征收奖励"}, index = 44)
    private String moveReward;

    /**
     * 其他费用
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "其他", "其他", "其他"}, index = 45)
    private String otherFee;



    /**
     * 新房地址
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "安置房屋情况", "楼盘名称", "楼盘名称"}, index = 46)
    private String newHouseAddress;

    /**
     * 楼栋
     */
    @ExcelIgnore
    private String seat;

    /**
     * 单元
     */
    @ExcelIgnore
    private String unit;

    /**
     * 楼层
     */
    @ExcelIgnore
    private String floors;

    /**
     * 房号
     */
    @ExcelIgnore
    private String houseNumber;

    @ExcelProperty(value = {"房屋征收补偿明细表", "安置房屋情况", "房号", "房号"}, index = 47)
    private String newHouseNumber;

    /**
     * 该房屋建筑面积
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "安置房屋情况", "房屋面积", "房屋面积"}, index = 48)
    private String coveredArea;

    /**
     * 单价
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "安置房屋情况", "房屋单价", "房屋单价"}, index = 49)
    private String price;

    /**
     * 总价
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "安置房屋情况", "房屋总价", "房屋总价"}, index = 50)
    private String totalPrice;

    /**
     * 总价大写
     */
    @ExcelIgnore
    private String upperTotalPrice;

    /**
     * 交房年份
     */
    @ExcelIgnore
    private String years;

    /**
     * 交房月份
     */
    @ExcelIgnore
    private String months;

    /**
     * 乙方同意将N元由甲方支付给调换房建设单位
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "抵扣房款（元）", "抵扣房款（元）", "抵扣房款（元）"}, index = 51)
    private String transferRmb;

    /**
     * 差额：征收补偿款总额大于预购房款
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "实际应付金额（元）", "实际应付金额（元）", "实际应付金额（元）"}, index = 52)
    private String difference;

    /**
     * 大写差额
     */
    @ExcelIgnore
    private String upperDifference;

    /**
     * 差额：收补偿总额小于预购房款
     */
    @ExcelProperty(value = {"房屋征收补偿明细表", "实际应收金额（元）", "实际应收金额（元）", "实际应收金额（元）"}, index = 53)
    private String lessDifference;


    @ExcelProperty(value = {"房屋征收补偿明细表", "其它约定", "其它约定", "其它约定"}, index = 54)
    private String otherTerms;

    /**
     * 大写差额
     */
    @ExcelIgnore
    private String upperLessDifference;

    /**
     * 过渡几个月
     */
    @ExcelIgnore
    private Integer moveMonth;


    /**创建时间**/
    @ExcelIgnore
    public Date createDate;

   /* @ExcelProperty(value = {"房屋征收补偿明细表", "超期过渡费补偿", "面积", "面积"}, index = 53)
    private String expiredArea;

    @ExcelProperty(value = {"房屋征收补偿明细表", "超期过渡费补偿", "单价", "单价"}, index = 54)
    private String expiredPrice;

    @ExcelProperty(value = {"房屋征收补偿明细表", "超期过渡费补偿", "月份", "月份"}, index = 55)
    private String expiredMonth;

    @ExcelProperty(value = {"房屋征收补偿明细表", "超期过渡费补偿", "金额", "金额"}, index = 56)
    private String expiredMoney;

    @ExcelProperty(value = {"房屋征收补偿明细表", "交房结算情况", "实测面积", "实测面积"}, index = 57)
    private String settlementArea;

    @ExcelProperty(value = {"房屋征收补偿明细表", "交房结算情况", "结算单价", "结算单价"}, index = 58)
    private String settlementPrice;

    @ExcelProperty(value = {"房屋征收补偿明细表", "交房结算情况", "应收（应退）房款", "应收（应退）房款"}, index = 59)
    private String settlementMoney;*/


    @Override
    public int compareTo(ProtocolExportVO o) {
        if (this.createDate.compareTo(o.createDate) > 0) {
            return 1;
        } else if (this.createDate.compareTo(o.createDate) < 0) {
            return -1;
        }
        return 0;
    }





}
