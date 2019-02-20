package com.me.szzc.pojo.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
public class Notice {
    private Long id;

    private String cardNo;

    private String projectName;

    private String years;

    private String months;

    private String days;

    private String houseOwner;

    private String lessee;

    private String phone;

    private String lesseePhone;

    private String useing;

    private String checkInArea;

    private BigDecimal proportion;

    private BigDecimal assessPrice;

    private Integer identityNo;

    private String address;

    private String calcValueCompensate;

    private BigDecimal valueCompensate;

    private String valueCompensateBz;

    private String calcDecorationCompensate;

    private BigDecimal decorationCompensate;

    private String decorationCompensateBz;

    private String calcStructureCompensate;

    private BigDecimal structureCompensate;

    private String structureCompensateBz;

    private String calcMoveAmmeterFee;

    private BigDecimal moveAmmeterFee;

    private String moveAmmeterFeeBz;

    private String calcMoveWaterMeterFee;

    private BigDecimal moveWaterMeterFee;

    private String moveWaterMeterFeeBz;

    private String calcMoveAirConditioningFee;

    private BigDecimal moveAirConditioningFee;

    private String moveAirConditioningFeeBz;

    private String calcGasFee;

    private BigDecimal gasFee;

    private String gasFeeBz;

    private String calcHotWaterCompensate;

    private BigDecimal hotWaterCompensate;

    private String hotWaterCompensateBz;

    private String affiliated1Desc;

    private String calcAffiliated1Desc;

    private BigDecimal affiliated1Rmb;

    private String affiliated1Bz;

    private String affiliated2Desc;

    private String calcAffiliated2Desc;

    private BigDecimal affiliated2Rmb;

    private String affiliated2Bz;

    private String affiliated3Desc;

    private String calcAffiliated3Desc;

    private BigDecimal affiliated3Rmb;

    private String affiliated3Bz;

    private String calcMoveHouseFee;

    private BigDecimal moveHouseFee;

    private String moveHouseFeeBz;

    private String calcInterimFee;

    private BigDecimal interimFee;

    private String interimFeeBz;

    private String calcSuspendBusinessFee;

    private BigDecimal suspendBusinessFee;

    private String suspendBusinessFeeBz;

    private String calcRmbCompensate;

    private BigDecimal rmbCompensate;

    private String rmbCompensateBz;

    private String calcLifeCompensate;

    private BigDecimal lifeCompensate;

    private String lifeCompensateBz;

    private String calcChangeCompensate;

    private BigDecimal changeCompensate;

    private String changeCompensateBz;

    private String calcMoveReward;

    private BigDecimal moveReward;

    private String moveRewardBz;

    private String calcCloseBalcony;

    private BigDecimal closeBalcony;

    private String closeBalconyBz;

    private String calcNoCheckCompensate;

    private BigDecimal noCheckCompensate;

    private String noCheckCompensateBz;

    private String calcOther;

    private BigDecimal otherRmb;

    private String otherBz;

    private String calcPayTotal;

    private BigDecimal payTotal;

    private String payTotalBz;

    private BigDecimal changeArea;

    private String calcChangeRmb;

    private BigDecimal changeRmb;

    private String changeRmbBz;

    private BigDecimal realReceiveTotal;

    private BigDecimal realPayTotal;

    private String realPayMoney1;

    private String realPayMoney2;

    private String realPayMoney3;

    private String realPayMoney4;

    private String realPayMoney5;

    private String realPayMoney6;

    private String realPayMoney7;

    private String realPayMoney8;

    private Date createDate;

    private Long createUserId;

    private Date modifiedDate;

    private Long modifiedUserId;

    private Boolean deleted;
}