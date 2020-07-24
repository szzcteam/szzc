package com.me.szzc.pojo.entity;

import lombok.Data;

import java.beans.Transient;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class SettleAccounts {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 编报单位，x片区
     */
    private Long areaId;

    /**
     * 协议编号
     */
    private String cardNo;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 被征收人
     */
    private String houseOwner;

    /**
     * 被征收人身份证号
     */
    private String identityNo;

    /**
     * 结算方式：0货币补偿、1产权交换
     */
    private Integer compensateType;

    /**
     * 承租人
     */
    private String lessee;

    /**
     * 被征收人电话
     */
    private String phone;

    /**
     * 承租人电话
     */
    private String lesseePhone;

    /**
     * 用途
     */
    private String useing;

    /**
     * 建筑面积-有证面积
     */
    private BigDecimal certifiedArea;

    /**
     * 未登记合法面积
     */
    private BigDecimal noRegisterLegalArea;

    /**
     * 历史遗留面积
     */
    private BigDecimal historyLegacyArea;

    /**
     * 房屋建筑面积
     */
    private BigDecimal checkInArea;

    /**
     * 未经登记的面积（未登记合法面积+历史遗留面积）
     */
    private BigDecimal noCheckinArea;

    /**
     * 套内面积
     */
    private BigDecimal inArea;

    /**
     * 房屋评估单价
     */
    private String assessPrice;

    /**
     * 被征收房屋地址
     */
    private String address;

    /**
     * 选择产权调换时，选择的新房名称
     */
    private String newHouseName;

    /**
     * 被征收房屋价值补偿计算公式
     */
    private String calcValueCompensate;

    /**
     * 被征收房屋价值补偿金额
     */
    private BigDecimal valueCompensate;

    /**
     * 被征收房屋价值补偿备注
     */
    private String valueCompensateBz;

    /**
     * 未经登记的合法建筑计算公式
     */
    private String calcNoRegisterLegal;

    /**
     * 未经登记的合法建筑金额
     */
    private BigDecimal noRegisterLegal;

    /**
     * 未经登记的合法建备注
     */
    private String noRegisterLegalBz;

    /**
     * 历史遗留无证房计算公式
     */
    private String calcHistoryLegacy;

    /**
     * 历史遗留无证房金额
     */
    private BigDecimal historyLegacy;

    /**
     * 历史遗留无证房备注
     */
    private String historyLegacyBz;

    /**
     * 装修折旧补偿
     */
    private String calcDecorationCompensate;

    /**
     * 装修折旧补偿金额
     */
    private BigDecimal decorationCompensate;

    /**
     * 装修折旧补偿金额备注
     */
    private String decorationCompensateBz;

    /**
     * 搬家费计算公式
     */
    private String calcMoveHouseFee;

    /**
     * 搬家费
     */
    private BigDecimal moveHouseFee;

    /**
     * 搬家费备注
     */
    private String moveHouseFeeBz;

    /**
     * 临时安置补偿费计算公式
     */
    private String calcInterimFee;

    /**
     * 临时安置补偿费
     */
    private BigDecimal interimFee;

    /**
     * 临时安置补偿费备注
     */
    private String interimFeeBz;

    /**
     * 水表迁移费计算公式
     */
    private String calcMoveWaterMeterFee;

    /**
     * 水表迁移费
     */
    private BigDecimal moveWaterMeterFee;

    /**
     * 水表迁移费备注
     */
    private String moveWaterMeterFeeBz;

    /**
     * 电表迁移费计算公式
     */
    private String calcMoveAmmeterFee;

    /**
     * 电表迁移费
     */
    private BigDecimal moveAmmeterFee;

    /**
     * 电表迁移费备注
     */
    private String moveAmmeterFeeBz;

    /**
     * 空调移机费计算公式
     */
    private String calcMoveAirConditioningFee;

    /**
     * 空调移机费
     */
    private BigDecimal moveAirConditioningFee;

    /**
     * 空调移机费备注
     */
    private String moveAirConditioningFeeBz;

    /**
     * 热水器拆装费计算
     */
    private String calcHotWaterCompensate;

    /**
     * 热水器拆装费
     */
    private BigDecimal hotWaterCompensate;

    /**
     * 热水器拆装费备注
     */
    private String hotWaterCompensateBz;

    /**
     * 管道燃气拆装费计算公式
     */
    private String calcGasFee;

    /**
     * 管道燃气拆装费
     */
    private BigDecimal gasFee;

    /**
     * 管道燃气拆装费备注
     */
    private String gasFeeBz;

    /**
     * 构建物补偿计算公式
     */
    private String calcStructureCompensate;

    /**
     * 构建物补偿费
     */
    private BigDecimal structureCompensate;

    /**
     * 构建物补偿费备注
     */
    private String structureCompensateBz;

    /**
     * 结构内阳台计算公式
     */
    private String calcStructureBalcony;

    /**
     * 结构内阳台金额
     */
    private BigDecimal structureBalcony;

    /**
     * 结构内阳台备注
     */
    private String structureBalconyBz;

    /**
     * 外挑搭建计算公式
     */
    private String calcStructureBuild;

    /**
     * 外挑搭建金额
     */
    private BigDecimal structureBuild;

    /**
     * 外挑搭建备注
     */
    private String structureBuildBz;

    /**
     * 暗楼
     */
    private String calcStructureDark;

    /**
     * 暗楼金额
     */
    private BigDecimal structureDark;

    /**
     * 暗楼备注
     */
    private String structureDarkBz;

    /**
     * 夹层
     */
    private String calcStructureMezzanine;

    /**
     * 夹层金额
     */
    private BigDecimal structureMezzanine;

    /**
     * 夹层备注
     */
    private String structureMezzanineBz;

    /**
     * 楼顶搭建
     */
    private String calcStructureRoof;

    /**
     * 楼顶搭建金额
     */
    private BigDecimal structureRoof;

    /**
     * 楼顶搭建备注
     */
    private String structureRoofBz;

    /**
     * 其他描述
     */
    private String affiliatedOtherDesc;

    /**
     * 附属其他计算
     */
    private String calcAffiliatedOther;

    /**
     * 附属金额
     */
    private BigDecimal affiliatedOther;

    /**
     * 附属其他备注
     */
    private String affiliatedOtherBz;

    /**
     * 未登记房屋补偿计算公式
     */
    private String calcNoCheckCompensate;

    /**
     * 未登记房屋补偿
     */
    private BigDecimal noCheckCompensate;

    /**
     * 未登记房屋补偿备注
     */
    private String noCheckCompensateBz;

    /**
     * 货币补偿补助计算公式
     */
    private String calcRmbCompensate;

    /**
     * 货币补偿
     */
    private BigDecimal rmbCompensate;

    /**
     * 货币补助备注
     */
    private String rmbCompensateBz;

    /**
     * 生活困难补助计算公式
     */
    private String calcLifeCompensate;

    /**
     * 生活困难补助
     */
    private BigDecimal lifeCompensate;

    /**
     * 生活困难补助备注
     */
    private String lifeCompensateBz;

    /**
     * 住改商补助计算公式
     */
    private String calcChangeCompensate;

    /**
     * 住改商补助
     */
    private BigDecimal changeCompensate;

    /**
     * 住改商补助备注
     */
    private String changeCompensateBz;

    /**
     * 建筑面积补助计算公式
     */
    private String calcBuildingAreaFee;

    /**
     * 建筑面积补助
     */
    private BigDecimal buildingAreaFee;

    /**
     * 建筑面积补助
     */
    private String buildingAreaFeeBz;

    /**
     * 停产停业损失补助
     */
    private String calcSuspendBusinessFee;

    /**
     * 停产停业损失补助
     */
    private BigDecimal suspendBusinessFee;

    /**
     * 停产停业损失补助备注
     */
    private String suspendBusinessFeeBz;

    /**
     * 不可移动设备设施补助计算公式
     */
    private String calcNoMoveCompensate;

    /**
     * 不可移动设备设施补助
     */
    private BigDecimal noMoveCompensate;

    /**
     * 不可移动设备设施补助备注
     */
    private String noMoveCompensateBz;

    /**
     * 搬迁奖励计算公式
     */
    private String calcMoveReward;

    /**
     * 搬迁奖励
     */
    private BigDecimal moveReward;

    /**
     * 搬迁奖励备注
     */
    private String moveRewardBz;

    /**
     * 货币搬迁奖励计算公式
     */
    private String calcRmbMoveReward;

    /**
     * 货币搬迁奖励
     */
    private BigDecimal rmbMoveReward;

    /**
     * 货币搬迁奖励备注
     */
    private String rmbMoveRewardBz;

    /**
     * 小面积住房搬迁奖励计算公式
     */
    private String calcSmallAreaReward;

    /**
     * 小面积住房搬迁奖励
     */
    private BigDecimal smallAreaReward;

    /**
     * 小面积住房搬迁奖励备注
     */
    private String smallAreaRewardBz;

    /**
     * 保底补偿计算公式
     */
    private String calcGuarantee;

    /**
     * 保底补偿金额
     */
    private BigDecimal guarantee;

    /**
     * 保底补偿备注
     */
    private String guaranteeBz;

    /**
     * 其他描述
     */
    private String otherDesc;

    /**
     * 其他计算公式
     */
    private String calcOther;

    /**
     * 其他补偿rmb金额
     */
    private BigDecimal otherRmb;

    /**
     * 其他备注
     */
    private String otherBz;

    /**
     * 征收房屋补偿合计计算公式
     */
    private String calcSumCompensate;

    /**
     * 合计补偿
     */
    private BigDecimal sumCompensate;

    /**
     * 补偿合计备注
     */
    private String sumCompensateBz;

    /**
     * 产权调换建筑面积
     */
    private BigDecimal changeArea;

    /**
     * 产权调换建筑面积计算公式
     */
    private String calcChangeArea;

    /**
     * 产权调换，其中参与计算面积
     */
    private BigDecimal calcm;

    /**
     * 房屋置换金额
     */
    private BigDecimal price;

    /**
     * 房屋置换金额
     */
    private BigDecimal houseMoney;

    /**
     * 房屋置换金额备注
     */
    private String houseMoneyBz;

    /**
     * 计算已抵扣安置房款
     */
    private String calcDeduction;

    /**
     * 已抵扣安置房款
     */
    private BigDecimal deduction;

    /**
     * 已抵扣安置房款备注
     */
    private String deductionBz;

    /**
     * 交换房单价1
     */
    private String swapPrice1;

    /**
     * 交换房单价2
     */
    private String swapPrice2;

    /**
     * 交换房单价3
     */
    private String swapPrice3;

    /**
     * 交换房单价4
     */
    private String swapPrice4;

    /**
     * 交换房单价5
     */
    private String swapPrice5;

    /**
     * 交换房面积1
     */
    private String swapArea1;

    /**
     * 交换房面积2
     */
    private String swapArea2;

    /**
     * 交换房面积3
     */
    private String swapArea3;

    /**
     * 交换房面积4
     */
    private String swapArea4;

    /**
     * 交换房面积5
     */
    private String swapArea5;

    /**
     * 交换房金额1
     */
    private String swapMoney1;

    /**
     * 交换房金额2
     */
    private String swapMoney2;

    /**
     * 交换房金额3
     */
    private String swapMoney3;

    /**
     * 交换房金额4
     */
    private String swapMoney4;

    /**
     * 交换房金额5
     */
    private String swapMoney5;

    /**
     * 应付合计
     */
    private BigDecimal payTotal;

    /**
     * 应收合计
     */
    private BigDecimal receiveTotal;

    /**
     * 应付合计大写元
     */
    private String payMoney;

    /**
     * 应收合计大写元
     */
    private String receiveMoney;

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
     * 修改人员id
     */
    private Long modifiedUserId;

    /**
     * 逻辑删除
     */
    private Boolean deleted;

    /**
     * 签约状态：0未签约、1签约完成
     */
    private Integer signingStatus;

    /**
     * 决字信息，结算单数据库忽略该字段
     */
    private Adjudication adjudication;

    /**签约时间**/
    private Date signingDate;




}