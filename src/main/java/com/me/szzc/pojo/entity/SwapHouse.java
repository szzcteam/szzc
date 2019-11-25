package com.me.szzc.pojo.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SwapHouse {
    /**
     * 
     */
    private Long id;

    /**
     * 编报单位，x片区
     */
    private Long areaId;

    /***
     * 片区所属项目
     */
    private String projectCode;

    /**
     * 协议编号
     */
    private String cardNo;

    /**
     * 征收部门
     */
    private String mngOffice;

    /**
     * 代办单位
     */
    private String agencyCompany;

    /**
     * 实施公司
     */
    private String company;

    /**
     * 被征收人
     */
    private String houseOwner;

    /**
     * 身份证号
     */
    private String identityNo;

    /**
     * 补偿计划
     */
    private String recompensePlan;

    /**
     * 房屋地址
     */
    private String address;

    /**
     * 证载建筑面积
     */
    private String certifiedArea;

    /**
     * 房屋所属权证号
     */
    private String houseOwnerNumber;

    /**
     * 国有土地使用权证号
     */
    private String publicOwnerNumber;

    /**
     * 房屋权属份额
     */
    private BigDecimal proportion;

    /**
     * 房屋用途
     */
    private String useing;

    /**
     * 证载房屋评估单价
     */
    private String assessPrice;

    /**
     * 登记建筑面积
     */
    private String checkInArea;

    /**
     * 住宅面积
     */
    private BigDecimal residenceArea;

    /**
     * 证载房屋用于经营的面积
     */
    private BigDecimal valueCompensateBusinessArea;

    /**
     * 证载房屋住宅改为门面补助系数
     */
    private BigDecimal valueCompensateRate;

    /**
     * 经营面积
     */
    private BigDecimal operateArea;

    /**
     * 办公面积
     */
    private BigDecimal officeArea;

    /**
     * 生产面积
     */
    private BigDecimal produceArea;

    /**
     * 其他面积
     */
    private BigDecimal otherArea;

    /**
     * 未登记面积
     */
    private BigDecimal noCheckArea;

    /**
     * 未登记的合法建筑面积
     */
    private String noRegisterLegalArea;

    /**
     * 未登记合法建筑房屋用途
     */
    private String noRegisterUseing;

    /**
     * 未登记房屋评估单价
     */
    private String noRegisterAssessPrice;

    /**
     * 未登记房屋补偿比例
     */
    private BigDecimal noRegisterProportion;

    /**
     * 未登记房屋经营面积
     */
    private BigDecimal noRegisterBusinessArea;

    /**
     * 未登记房屋住宅改为门面补助系数
     */
    private BigDecimal noRegisterRate;

    /**
     * 历史遗留面积
     */
    private String historyLegacyArea;

    /**
     * 历史房屋实际用途
     */
    private String historyUseing;

    /**
     * 历史遗留房屋评估单价
     */
    private String historyAssessPrice;

    /**
     * 历史遗留房屋补偿比例
     */
    private BigDecimal historyProportion;

    /**
     * 历史遗留房屋门面经营面积
     */
    private BigDecimal historyBusinessArea;

    /**
     * 历史遗留房屋门面补助系数
     */
    private BigDecimal historyRate;

    /**
     * 有证房屋补偿金额
     */
    private BigDecimal valueCompensate;

    /**
     * 未登记的合法建筑补偿金额
     */
    private BigDecimal noRegisterLegal;

    /**
     * 历史遗留无证房屋补偿金额
     */
    private BigDecimal historyLegacy;

    /**
     * 装修补偿每平米单价
     */
    private BigDecimal decorationCompensateUnitPrice;

    /**
     * 装饰装修补偿金额
     */
    private BigDecimal decorationCompensate;

    /**
     * 构建物补偿
     */
    private BigDecimal structureCompensate;

    /**
     * 电话移机费
     */
    private BigDecimal movePhoneFee;

    /**
     * 有线电视复装费
     */
    private BigDecimal tvFee;

    /**
     * 电表迁移费
     */
    private BigDecimal moveAmmeterFee;

    /**
     * 水表迁移费
     */
    private BigDecimal moveWaterMeterFee;

    /**
     * 宽带网补偿
     */
    private BigDecimal wifiFee;

    /**
     * 空调移机费
     */
    private BigDecimal moveAirConditioningFee;

    /**
     * 太阳能热水器移机费
     */
    private BigDecimal solarHeater;

    /**
     * 其他热水器移机费
     */
    private BigDecimal otherHeater;

    /**
     * 管道燃气拆装费
     */
    private BigDecimal gasFee;

    /**
     * 热水器拆装补偿
     */
    private BigDecimal hotWaterCompensate;

    /**
     * 附属设施小计
     */
    private BigDecimal subtotal;

    /**
     * 搬家费
     */
    private BigDecimal moveHouseFee;

    /**
     * 过渡面积
     */
    private BigDecimal interimArea;

    /**
     * 过渡单价
     */
    private BigDecimal interimPrice;

    /**
     * 过渡月数
     */
    private Integer interimMonth;

    /**
     * 过渡费
     */
    private BigDecimal interimFee;

    /**
     * 保底补偿金额
     */
    private BigDecimal guarantee;

    /**
     * 停产停业损失补偿
     */
    private BigDecimal suspendBusinessFee;

    /**
     * 结构设计内封闭阳台补偿费
     */
    private BigDecimal structureBalcony;

    /**
     * 外挑搭建补偿费
     */
    private BigDecimal structureBuild;

    /**
     * 暗楼补偿费
     */
    private BigDecimal structureDark;

    /**
     * 夹层补偿费
     */
    private BigDecimal structureMezzanine;

    /**
     * 顶楼搭建补偿费
     */
    private BigDecimal structureRoof;

    /**
     * 构建物其他补偿费用
     */
    private BigDecimal affiliatedOther;

    /**
     * 低保补助
     */
    private BigDecimal basicLivingSubsidy;

    /**
     * 残疾补助
     */
    private BigDecimal disabilitySubsidy;

    /**
     * 重症
     */
    private BigDecimal diseaseSubsidy;

    /**
     * 失独
     */
    private BigDecimal noChild;

    /**
     * 烈士家庭补助
     */
    private BigDecimal martyr;

    /**
     * 生活困难补助
     */
    private BigDecimal lifeCompensate;

    /**
     * 住改商补助
     */
    private BigDecimal changeCompensate;

    /**
     * 建筑面积补助
     */
    private BigDecimal buildingAreaFee;

    /**
     * 产权调换时的补偿货币金额
     */
    private BigDecimal rmbCompensate;

    /**
     * 小户型住房困难补助
     */
    private BigDecimal smallAreaReward;

    /**
     * 按期签约搬迁奖励
     */
    private BigDecimal moveReward;

    /**
     * 其他费用
     */
    private BigDecimal otherFee;

    /**
     * 总计人民币
     */
    private BigDecimal sumRbm;

    /**
     * 大写人民币
     */
    private String upperRmb;

    /**
     * 限N天搬迁
     */
    private Integer beforeDay;

    /**
     * 新房地址
     */
    private String newHouseAddress;

    /**
     * 楼栋
     */
    private String seat;

    /**
     * 单元
     */
    private String unit;

    /**
     * 楼层
     */
    private String floors;

    /**
     * 房号
     */
    private String houseNumber;

    /**
     * 该房屋建筑面积
     */
    private String coveredArea;

    /**
     * 单价
     */
    private String price;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 总价大写
     */
    private String upperTotalPrice;

    /**
     * 交房年份
     */
    private String years;

    /**
     * 交房月份
     */
    private String months;

    /**
     * 乙方同意将N元由甲方支付给调换房建设单位
     */
    private BigDecimal transferRmb;

    /**
     * 差额：征收补偿款总额大于预购房款
     */
    private BigDecimal difference;

    /**
     * 大写差额
     */
    private String upperDifference;

    /**
     * 差额：收补偿总额小于预购房款
     */
    private BigDecimal lessDifference;

    /**
     * 大写差额
     */
    private String upperLessDifference;

    /**
     * 过渡几个月
     */
    private Integer moveMonth;

    /**
     * 其他约定1
     */
    private String otherTermsOne;

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

    /**
     * 修改人id
     */
    private Long modifiedUserId;

    /**
     * 逻辑删除
     */
    private Boolean deleted;

    /**
     * 字诀信息
     */
    private String adjudicationJson;

    /**
     * 是否承租人标识
     */
    private Boolean isLesseeFlag;

}