package com.me.szzc.pojo.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SwapHouse {
    private Long id;

    private String cardNo;

    private String mngOffice;

    private String company;

    private String houseOwner;

    private String recompensePlan;

    private String address;

    private String houseOwnerNumber;

    private String publicOwnerNumber;

    private BigDecimal proportion;

    private String useing;

    private BigDecimal checkInArea;

    private BigDecimal residenceArea;

    private BigDecimal operateArea;

    private BigDecimal officeArea;

    private BigDecimal produceArea;

    private BigDecimal otherArea;

    private BigDecimal noCheckArea;

    private BigDecimal valueCompensate;

    private BigDecimal decorationCompensate;

    private BigDecimal structureCompensate;

    private BigDecimal movePhoneFee;

    private BigDecimal tvFee;

    private BigDecimal moveAmmeterFee;

    private BigDecimal moveWaterMeterFee;

    private BigDecimal wifiFee;

    private BigDecimal moveAirConditioningFee;

    private BigDecimal gasFee;

    private BigDecimal hotWaterCompensate;

    private BigDecimal subtotal;

    private BigDecimal moveHouseFee;

    private BigDecimal interimPrice;

    private Integer interimMonth;

    private BigDecimal interimFee;

    private BigDecimal suspendBusinessFee;

    private BigDecimal lifeCompensate;

    private BigDecimal changeCompensate;

    private BigDecimal moveReward;

    private BigDecimal closeBalcony;

    private BigDecimal noCheckCompensate;

    private BigDecimal otherFee;

    private BigDecimal sumRbm;

    private String upperRmb;

    private String newHouseAddress;

    private String seat;

    private Integer unit;

    private String houseNumber;

    private BigDecimal coveredArea;

    private BigDecimal price;

    private BigDecimal totalPrice;

    private String upperTotalPrice;

    private String years;

    private String months;

    private BigDecimal difference;

    private String upperDifference;

    private BigDecimal lessDifference;

    private String upperLessDifference;

    private Integer moveMonth;

    private String otherTermsOne;

    private Date createDate;

    private Long createUserId;

    private Date modifiedDate;

    private Long modifiedUserId;

}