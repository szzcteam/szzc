package com.me.szzc.pojo.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
public class SettleAccounts {
    private Long id;

    private String cardNo;

    private String projectName;

    private String houseOwner;

    private String lessee;

    private String phone;

    private String lesseePhone;

    private String useing;

    private BigDecimal checkInArea;

    private BigDecimal inArea;

    private BigDecimal assessPrice;

    private String address;

    private String calcValueCompensate;

    private BigDecimal valueCompensate;

    private String valueCompensateBz;

    private String calcDecorationCompensate;

    private BigDecimal decorationCompensate;

    private String decorationCompensateBz;

    private String calcMoveHouseFee;

    private BigDecimal moveHouseFee;

    private String moveHouseFeeBz;

    private String calcInterimFee;

    private BigDecimal interimFee;

    private String interimFeeBz;

    private String calcMoveWaterMeterFee;

    private BigDecimal moveWaterMeterFee;

    private String moveWaterMeterFeeBz;

    private String calcMoveAmmeterFee;

    private BigDecimal moveAmmeterFee;

    private String moveAmmeterFeeBz;

    private String calcMoveAirConditioningFee;

    private BigDecimal moveAirConditioningFee;

    private String moveAirConditioningFeeBz;

    private String calcHotWaterCompensate;

    private BigDecimal hotWaterCompensate;

    private String hotWaterCompensateBz;

    private String calcGasFee;

    private String gasFee;

    private String gasFeeBz;

    private String calcStructureCompensate;

    private BigDecimal structureCompensate;

    private String structureCompensateBz;

    private String affiliatedOtherDesc;

    private String calcAffiliatedOther;

    private BigDecimal affiliatedOther;

    private String affiliatedOtherBz;

    private String calcNoCheckCompensate;

    private BigDecimal noCheckCompensate;

    private String noCheckCompensateBz;

    private String calcRmbCompensate;

    private BigDecimal rmbCompensate;

    private String rmbCompensateBz;

    private String calcLifeCompensate;

    private BigDecimal lifeCompensate;

    private String lifeCompensateBz;

    private String calcChangeCompensate;

    private BigDecimal changeCompensate;

    private String changeCompensateBz;

    private String calcBuildingAreaFee;

    private BigDecimal buildingAreaFee;

    private String buildingAreaFeeBz;

    private String calcSuspendBusinessFee;

    private BigDecimal suspendBusinessFee;

    private String suspendBusinessFeeBz;

    private String calcNoMoveCompensate;

    private BigDecimal noMoveCompensate;

    private String noMoveCompensateBz;

    private String calcMoveReward;

    private BigDecimal moveReward;

    private String moveRewardBz;

    private String calcRmbMoveReward;

    private BigDecimal rmbMoveReward;

    private String rmbMoveRewardBz;

    private String calcSmallAreaReward;

    private BigDecimal smallAreaReward;

    private String smallAreaRewardBz;

    private String otherDesc;

    private String calcOther;

    private BigDecimal otherRmb;

    private String otherBz;

    private String calcSumCompensate;

    private BigDecimal sumCompensate;

    private String sumCompensateBz;

    private BigDecimal changeArea;

    private String calcChangeArea;

    private BigDecimal calcm;

    private BigDecimal price;

    private BigDecimal houseMoney;

    private String calcDeduction;

    private BigDecimal deduction;

    private String deductionBz;

    private String calcPayTotal;

    private BigDecimal payTotal;

    private String payTotalBz;

    private BigDecimal receiveTotal;

    private String payMoney;

    private String receiveMoney;

    private Date createDate;

    private Long createUserId;

    private Date modifiedDate;

    private Long modifiedUserId;

    private Boolean deleted;
}