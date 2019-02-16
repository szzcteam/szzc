package com.me.szzc.pojo.entity;

import java.math.BigDecimal;
import java.util.Date;

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

    private String use;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years == null ? null : years.trim();
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months == null ? null : months.trim();
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days == null ? null : days.trim();
    }

    public String getHouseOwner() {
        return houseOwner;
    }

    public void setHouseOwner(String houseOwner) {
        this.houseOwner = houseOwner == null ? null : houseOwner.trim();
    }

    public String getLessee() {
        return lessee;
    }

    public void setLessee(String lessee) {
        this.lessee = lessee == null ? null : lessee.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getLesseePhone() {
        return lesseePhone;
    }

    public void setLesseePhone(String lesseePhone) {
        this.lesseePhone = lesseePhone == null ? null : lesseePhone.trim();
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use == null ? null : use.trim();
    }

    public String getCheckInArea() {
        return checkInArea;
    }

    public void setCheckInArea(String checkInArea) {
        this.checkInArea = checkInArea == null ? null : checkInArea.trim();
    }

    public BigDecimal getProportion() {
        return proportion;
    }

    public void setProportion(BigDecimal proportion) {
        this.proportion = proportion;
    }

    public BigDecimal getAssessPrice() {
        return assessPrice;
    }

    public void setAssessPrice(BigDecimal assessPrice) {
        this.assessPrice = assessPrice;
    }

    public Integer getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(Integer identityNo) {
        this.identityNo = identityNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getCalcValueCompensate() {
        return calcValueCompensate;
    }

    public void setCalcValueCompensate(String calcValueCompensate) {
        this.calcValueCompensate = calcValueCompensate == null ? null : calcValueCompensate.trim();
    }

    public BigDecimal getValueCompensate() {
        return valueCompensate;
    }

    public void setValueCompensate(BigDecimal valueCompensate) {
        this.valueCompensate = valueCompensate;
    }

    public String getValueCompensateBz() {
        return valueCompensateBz;
    }

    public void setValueCompensateBz(String valueCompensateBz) {
        this.valueCompensateBz = valueCompensateBz == null ? null : valueCompensateBz.trim();
    }

    public String getCalcDecorationCompensate() {
        return calcDecorationCompensate;
    }

    public void setCalcDecorationCompensate(String calcDecorationCompensate) {
        this.calcDecorationCompensate = calcDecorationCompensate == null ? null : calcDecorationCompensate.trim();
    }

    public BigDecimal getDecorationCompensate() {
        return decorationCompensate;
    }

    public void setDecorationCompensate(BigDecimal decorationCompensate) {
        this.decorationCompensate = decorationCompensate;
    }

    public String getDecorationCompensateBz() {
        return decorationCompensateBz;
    }

    public void setDecorationCompensateBz(String decorationCompensateBz) {
        this.decorationCompensateBz = decorationCompensateBz == null ? null : decorationCompensateBz.trim();
    }

    public String getCalcStructureCompensate() {
        return calcStructureCompensate;
    }

    public void setCalcStructureCompensate(String calcStructureCompensate) {
        this.calcStructureCompensate = calcStructureCompensate == null ? null : calcStructureCompensate.trim();
    }

    public BigDecimal getStructureCompensate() {
        return structureCompensate;
    }

    public void setStructureCompensate(BigDecimal structureCompensate) {
        this.structureCompensate = structureCompensate;
    }

    public String getStructureCompensateBz() {
        return structureCompensateBz;
    }

    public void setStructureCompensateBz(String structureCompensateBz) {
        this.structureCompensateBz = structureCompensateBz == null ? null : structureCompensateBz.trim();
    }

    public String getCalcMoveAmmeterFee() {
        return calcMoveAmmeterFee;
    }

    public void setCalcMoveAmmeterFee(String calcMoveAmmeterFee) {
        this.calcMoveAmmeterFee = calcMoveAmmeterFee == null ? null : calcMoveAmmeterFee.trim();
    }

    public BigDecimal getMoveAmmeterFee() {
        return moveAmmeterFee;
    }

    public void setMoveAmmeterFee(BigDecimal moveAmmeterFee) {
        this.moveAmmeterFee = moveAmmeterFee;
    }

    public String getMoveAmmeterFeeBz() {
        return moveAmmeterFeeBz;
    }

    public void setMoveAmmeterFeeBz(String moveAmmeterFeeBz) {
        this.moveAmmeterFeeBz = moveAmmeterFeeBz == null ? null : moveAmmeterFeeBz.trim();
    }

    public String getCalcMoveWaterMeterFee() {
        return calcMoveWaterMeterFee;
    }

    public void setCalcMoveWaterMeterFee(String calcMoveWaterMeterFee) {
        this.calcMoveWaterMeterFee = calcMoveWaterMeterFee == null ? null : calcMoveWaterMeterFee.trim();
    }

    public BigDecimal getMoveWaterMeterFee() {
        return moveWaterMeterFee;
    }

    public void setMoveWaterMeterFee(BigDecimal moveWaterMeterFee) {
        this.moveWaterMeterFee = moveWaterMeterFee;
    }

    public String getMoveWaterMeterFeeBz() {
        return moveWaterMeterFeeBz;
    }

    public void setMoveWaterMeterFeeBz(String moveWaterMeterFeeBz) {
        this.moveWaterMeterFeeBz = moveWaterMeterFeeBz == null ? null : moveWaterMeterFeeBz.trim();
    }

    public String getCalcMoveAirConditioningFee() {
        return calcMoveAirConditioningFee;
    }

    public void setCalcMoveAirConditioningFee(String calcMoveAirConditioningFee) {
        this.calcMoveAirConditioningFee = calcMoveAirConditioningFee == null ? null : calcMoveAirConditioningFee.trim();
    }

    public BigDecimal getMoveAirConditioningFee() {
        return moveAirConditioningFee;
    }

    public void setMoveAirConditioningFee(BigDecimal moveAirConditioningFee) {
        this.moveAirConditioningFee = moveAirConditioningFee;
    }

    public String getMoveAirConditioningFeeBz() {
        return moveAirConditioningFeeBz;
    }

    public void setMoveAirConditioningFeeBz(String moveAirConditioningFeeBz) {
        this.moveAirConditioningFeeBz = moveAirConditioningFeeBz == null ? null : moveAirConditioningFeeBz.trim();
    }

    public String getCalcGasFee() {
        return calcGasFee;
    }

    public void setCalcGasFee(String calcGasFee) {
        this.calcGasFee = calcGasFee == null ? null : calcGasFee.trim();
    }

    public BigDecimal getGasFee() {
        return gasFee;
    }

    public void setGasFee(BigDecimal gasFee) {
        this.gasFee = gasFee;
    }

    public String getGasFeeBz() {
        return gasFeeBz;
    }

    public void setGasFeeBz(String gasFeeBz) {
        this.gasFeeBz = gasFeeBz == null ? null : gasFeeBz.trim();
    }

    public String getCalcHotWaterCompensate() {
        return calcHotWaterCompensate;
    }

    public void setCalcHotWaterCompensate(String calcHotWaterCompensate) {
        this.calcHotWaterCompensate = calcHotWaterCompensate == null ? null : calcHotWaterCompensate.trim();
    }

    public BigDecimal getHotWaterCompensate() {
        return hotWaterCompensate;
    }

    public void setHotWaterCompensate(BigDecimal hotWaterCompensate) {
        this.hotWaterCompensate = hotWaterCompensate;
    }

    public String getHotWaterCompensateBz() {
        return hotWaterCompensateBz;
    }

    public void setHotWaterCompensateBz(String hotWaterCompensateBz) {
        this.hotWaterCompensateBz = hotWaterCompensateBz == null ? null : hotWaterCompensateBz.trim();
    }

    public String getAffiliated1Desc() {
        return affiliated1Desc;
    }

    public void setAffiliated1Desc(String affiliated1Desc) {
        this.affiliated1Desc = affiliated1Desc == null ? null : affiliated1Desc.trim();
    }

    public String getCalcAffiliated1Desc() {
        return calcAffiliated1Desc;
    }

    public void setCalcAffiliated1Desc(String calcAffiliated1Desc) {
        this.calcAffiliated1Desc = calcAffiliated1Desc == null ? null : calcAffiliated1Desc.trim();
    }

    public BigDecimal getAffiliated1Rmb() {
        return affiliated1Rmb;
    }

    public void setAffiliated1Rmb(BigDecimal affiliated1Rmb) {
        this.affiliated1Rmb = affiliated1Rmb;
    }

    public String getAffiliated1Bz() {
        return affiliated1Bz;
    }

    public void setAffiliated1Bz(String affiliated1Bz) {
        this.affiliated1Bz = affiliated1Bz == null ? null : affiliated1Bz.trim();
    }

    public String getAffiliated2Desc() {
        return affiliated2Desc;
    }

    public void setAffiliated2Desc(String affiliated2Desc) {
        this.affiliated2Desc = affiliated2Desc == null ? null : affiliated2Desc.trim();
    }

    public String getCalcAffiliated2Desc() {
        return calcAffiliated2Desc;
    }

    public void setCalcAffiliated2Desc(String calcAffiliated2Desc) {
        this.calcAffiliated2Desc = calcAffiliated2Desc == null ? null : calcAffiliated2Desc.trim();
    }

    public BigDecimal getAffiliated2Rmb() {
        return affiliated2Rmb;
    }

    public void setAffiliated2Rmb(BigDecimal affiliated2Rmb) {
        this.affiliated2Rmb = affiliated2Rmb;
    }

    public String getAffiliated2Bz() {
        return affiliated2Bz;
    }

    public void setAffiliated2Bz(String affiliated2Bz) {
        this.affiliated2Bz = affiliated2Bz == null ? null : affiliated2Bz.trim();
    }

    public String getAffiliated3Desc() {
        return affiliated3Desc;
    }

    public void setAffiliated3Desc(String affiliated3Desc) {
        this.affiliated3Desc = affiliated3Desc == null ? null : affiliated3Desc.trim();
    }

    public String getCalcAffiliated3Desc() {
        return calcAffiliated3Desc;
    }

    public void setCalcAffiliated3Desc(String calcAffiliated3Desc) {
        this.calcAffiliated3Desc = calcAffiliated3Desc == null ? null : calcAffiliated3Desc.trim();
    }

    public BigDecimal getAffiliated3Rmb() {
        return affiliated3Rmb;
    }

    public void setAffiliated3Rmb(BigDecimal affiliated3Rmb) {
        this.affiliated3Rmb = affiliated3Rmb;
    }

    public String getAffiliated3Bz() {
        return affiliated3Bz;
    }

    public void setAffiliated3Bz(String affiliated3Bz) {
        this.affiliated3Bz = affiliated3Bz == null ? null : affiliated3Bz.trim();
    }

    public String getCalcMoveHouseFee() {
        return calcMoveHouseFee;
    }

    public void setCalcMoveHouseFee(String calcMoveHouseFee) {
        this.calcMoveHouseFee = calcMoveHouseFee == null ? null : calcMoveHouseFee.trim();
    }

    public BigDecimal getMoveHouseFee() {
        return moveHouseFee;
    }

    public void setMoveHouseFee(BigDecimal moveHouseFee) {
        this.moveHouseFee = moveHouseFee;
    }

    public String getMoveHouseFeeBz() {
        return moveHouseFeeBz;
    }

    public void setMoveHouseFeeBz(String moveHouseFeeBz) {
        this.moveHouseFeeBz = moveHouseFeeBz == null ? null : moveHouseFeeBz.trim();
    }

    public String getCalcInterimFee() {
        return calcInterimFee;
    }

    public void setCalcInterimFee(String calcInterimFee) {
        this.calcInterimFee = calcInterimFee == null ? null : calcInterimFee.trim();
    }

    public BigDecimal getInterimFee() {
        return interimFee;
    }

    public void setInterimFee(BigDecimal interimFee) {
        this.interimFee = interimFee;
    }

    public String getInterimFeeBz() {
        return interimFeeBz;
    }

    public void setInterimFeeBz(String interimFeeBz) {
        this.interimFeeBz = interimFeeBz == null ? null : interimFeeBz.trim();
    }

    public String getCalcSuspendBusinessFee() {
        return calcSuspendBusinessFee;
    }

    public void setCalcSuspendBusinessFee(String calcSuspendBusinessFee) {
        this.calcSuspendBusinessFee = calcSuspendBusinessFee == null ? null : calcSuspendBusinessFee.trim();
    }

    public BigDecimal getSuspendBusinessFee() {
        return suspendBusinessFee;
    }

    public void setSuspendBusinessFee(BigDecimal suspendBusinessFee) {
        this.suspendBusinessFee = suspendBusinessFee;
    }

    public String getSuspendBusinessFeeBz() {
        return suspendBusinessFeeBz;
    }

    public void setSuspendBusinessFeeBz(String suspendBusinessFeeBz) {
        this.suspendBusinessFeeBz = suspendBusinessFeeBz == null ? null : suspendBusinessFeeBz.trim();
    }

    public String getCalcRmbCompensate() {
        return calcRmbCompensate;
    }

    public void setCalcRmbCompensate(String calcRmbCompensate) {
        this.calcRmbCompensate = calcRmbCompensate == null ? null : calcRmbCompensate.trim();
    }

    public BigDecimal getRmbCompensate() {
        return rmbCompensate;
    }

    public void setRmbCompensate(BigDecimal rmbCompensate) {
        this.rmbCompensate = rmbCompensate;
    }

    public String getRmbCompensateBz() {
        return rmbCompensateBz;
    }

    public void setRmbCompensateBz(String rmbCompensateBz) {
        this.rmbCompensateBz = rmbCompensateBz == null ? null : rmbCompensateBz.trim();
    }

    public String getCalcLifeCompensate() {
        return calcLifeCompensate;
    }

    public void setCalcLifeCompensate(String calcLifeCompensate) {
        this.calcLifeCompensate = calcLifeCompensate == null ? null : calcLifeCompensate.trim();
    }

    public BigDecimal getLifeCompensate() {
        return lifeCompensate;
    }

    public void setLifeCompensate(BigDecimal lifeCompensate) {
        this.lifeCompensate = lifeCompensate;
    }

    public String getLifeCompensateBz() {
        return lifeCompensateBz;
    }

    public void setLifeCompensateBz(String lifeCompensateBz) {
        this.lifeCompensateBz = lifeCompensateBz == null ? null : lifeCompensateBz.trim();
    }

    public String getCalcChangeCompensate() {
        return calcChangeCompensate;
    }

    public void setCalcChangeCompensate(String calcChangeCompensate) {
        this.calcChangeCompensate = calcChangeCompensate == null ? null : calcChangeCompensate.trim();
    }

    public BigDecimal getChangeCompensate() {
        return changeCompensate;
    }

    public void setChangeCompensate(BigDecimal changeCompensate) {
        this.changeCompensate = changeCompensate;
    }

    public String getChangeCompensateBz() {
        return changeCompensateBz;
    }

    public void setChangeCompensateBz(String changeCompensateBz) {
        this.changeCompensateBz = changeCompensateBz == null ? null : changeCompensateBz.trim();
    }

    public String getCalcMoveReward() {
        return calcMoveReward;
    }

    public void setCalcMoveReward(String calcMoveReward) {
        this.calcMoveReward = calcMoveReward == null ? null : calcMoveReward.trim();
    }

    public BigDecimal getMoveReward() {
        return moveReward;
    }

    public void setMoveReward(BigDecimal moveReward) {
        this.moveReward = moveReward;
    }

    public String getMoveRewardBz() {
        return moveRewardBz;
    }

    public void setMoveRewardBz(String moveRewardBz) {
        this.moveRewardBz = moveRewardBz == null ? null : moveRewardBz.trim();
    }

    public String getCalcCloseBalcony() {
        return calcCloseBalcony;
    }

    public void setCalcCloseBalcony(String calcCloseBalcony) {
        this.calcCloseBalcony = calcCloseBalcony == null ? null : calcCloseBalcony.trim();
    }

    public BigDecimal getCloseBalcony() {
        return closeBalcony;
    }

    public void setCloseBalcony(BigDecimal closeBalcony) {
        this.closeBalcony = closeBalcony;
    }

    public String getCloseBalconyBz() {
        return closeBalconyBz;
    }

    public void setCloseBalconyBz(String closeBalconyBz) {
        this.closeBalconyBz = closeBalconyBz == null ? null : closeBalconyBz.trim();
    }

    public String getCalcNoCheckCompensate() {
        return calcNoCheckCompensate;
    }

    public void setCalcNoCheckCompensate(String calcNoCheckCompensate) {
        this.calcNoCheckCompensate = calcNoCheckCompensate == null ? null : calcNoCheckCompensate.trim();
    }

    public BigDecimal getNoCheckCompensate() {
        return noCheckCompensate;
    }

    public void setNoCheckCompensate(BigDecimal noCheckCompensate) {
        this.noCheckCompensate = noCheckCompensate;
    }

    public String getNoCheckCompensateBz() {
        return noCheckCompensateBz;
    }

    public void setNoCheckCompensateBz(String noCheckCompensateBz) {
        this.noCheckCompensateBz = noCheckCompensateBz == null ? null : noCheckCompensateBz.trim();
    }

    public String getCalcOther() {
        return calcOther;
    }

    public void setCalcOther(String calcOther) {
        this.calcOther = calcOther == null ? null : calcOther.trim();
    }

    public BigDecimal getOtherRmb() {
        return otherRmb;
    }

    public void setOtherRmb(BigDecimal otherRmb) {
        this.otherRmb = otherRmb;
    }

    public String getOtherBz() {
        return otherBz;
    }

    public void setOtherBz(String otherBz) {
        this.otherBz = otherBz == null ? null : otherBz.trim();
    }

    public String getCalcPayTotal() {
        return calcPayTotal;
    }

    public void setCalcPayTotal(String calcPayTotal) {
        this.calcPayTotal = calcPayTotal == null ? null : calcPayTotal.trim();
    }

    public BigDecimal getPayTotal() {
        return payTotal;
    }

    public void setPayTotal(BigDecimal payTotal) {
        this.payTotal = payTotal;
    }

    public String getPayTotalBz() {
        return payTotalBz;
    }

    public void setPayTotalBz(String payTotalBz) {
        this.payTotalBz = payTotalBz == null ? null : payTotalBz.trim();
    }

    public BigDecimal getChangeArea() {
        return changeArea;
    }

    public void setChangeArea(BigDecimal changeArea) {
        this.changeArea = changeArea;
    }

    public String getCalcChangeRmb() {
        return calcChangeRmb;
    }

    public void setCalcChangeRmb(String calcChangeRmb) {
        this.calcChangeRmb = calcChangeRmb == null ? null : calcChangeRmb.trim();
    }

    public BigDecimal getChangeRmb() {
        return changeRmb;
    }

    public void setChangeRmb(BigDecimal changeRmb) {
        this.changeRmb = changeRmb;
    }

    public String getChangeRmbBz() {
        return changeRmbBz;
    }

    public void setChangeRmbBz(String changeRmbBz) {
        this.changeRmbBz = changeRmbBz == null ? null : changeRmbBz.trim();
    }

    public BigDecimal getRealReceiveTotal() {
        return realReceiveTotal;
    }

    public void setRealReceiveTotal(BigDecimal realReceiveTotal) {
        this.realReceiveTotal = realReceiveTotal;
    }

    public BigDecimal getRealPayTotal() {
        return realPayTotal;
    }

    public void setRealPayTotal(BigDecimal realPayTotal) {
        this.realPayTotal = realPayTotal;
    }

    public String getRealPayMoney1() {
        return realPayMoney1;
    }

    public void setRealPayMoney1(String realPayMoney1) {
        this.realPayMoney1 = realPayMoney1 == null ? null : realPayMoney1.trim();
    }

    public String getRealPayMoney2() {
        return realPayMoney2;
    }

    public void setRealPayMoney2(String realPayMoney2) {
        this.realPayMoney2 = realPayMoney2 == null ? null : realPayMoney2.trim();
    }

    public String getRealPayMoney3() {
        return realPayMoney3;
    }

    public void setRealPayMoney3(String realPayMoney3) {
        this.realPayMoney3 = realPayMoney3 == null ? null : realPayMoney3.trim();
    }

    public String getRealPayMoney4() {
        return realPayMoney4;
    }

    public void setRealPayMoney4(String realPayMoney4) {
        this.realPayMoney4 = realPayMoney4 == null ? null : realPayMoney4.trim();
    }

    public String getRealPayMoney5() {
        return realPayMoney5;
    }

    public void setRealPayMoney5(String realPayMoney5) {
        this.realPayMoney5 = realPayMoney5 == null ? null : realPayMoney5.trim();
    }

    public String getRealPayMoney6() {
        return realPayMoney6;
    }

    public void setRealPayMoney6(String realPayMoney6) {
        this.realPayMoney6 = realPayMoney6 == null ? null : realPayMoney6.trim();
    }

    public String getRealPayMoney7() {
        return realPayMoney7;
    }

    public void setRealPayMoney7(String realPayMoney7) {
        this.realPayMoney7 = realPayMoney7 == null ? null : realPayMoney7.trim();
    }

    public String getRealPayMoney8() {
        return realPayMoney8;
    }

    public void setRealPayMoney8(String realPayMoney8) {
        this.realPayMoney8 = realPayMoney8 == null ? null : realPayMoney8.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getModifiedUserId() {
        return modifiedUserId;
    }

    public void setModifiedUserId(Long modifiedUserId) {
        this.modifiedUserId = modifiedUserId;
    }
}