package com.me.szzc.pojo.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SettleAccounts {
    private Long id;

    private String cardNo;

    private String houseOwner;

    private String lessee;

    private String phone;

    private String lesseePhone;

    private String use;

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

    private String calcOther;

    private BigDecimal otherRmb;

    private String otherBz;

    private String calcSumCompensate;

    private BigDecimal sumCompensate;

    private String sumCompensateBz;

    private BigDecimal changeArea;

    private BigDecimal calcm;

    private BigDecimal price;

    private BigDecimal deduction;

    private String calcPayTotal;

    private BigDecimal payTotal;

    private String payTotalBz;

    private BigDecimal receiveTotal;

    private String payMoney1;

    private String payMoney2;

    private String payMoney3;

    private String payMoney4;

    private String payMoney5;

    private String payMoney6;

    private String payMoney7;

    private String payMoney8;

    private String receiveMoney1;

    private String receiveMoney2;

    private String receiveMoney3;

    private String receiveMoney4;

    private String receiveMoney5;

    private String receiveMoney6;

    private String receiveMoney7;

    private String receiveMoney8;

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

    public BigDecimal getCheckInArea() {
        return checkInArea;
    }

    public void setCheckInArea(BigDecimal checkInArea) {
        this.checkInArea = checkInArea;
    }

    public BigDecimal getInArea() {
        return inArea;
    }

    public void setInArea(BigDecimal inArea) {
        this.inArea = inArea;
    }

    public BigDecimal getAssessPrice() {
        return assessPrice;
    }

    public void setAssessPrice(BigDecimal assessPrice) {
        this.assessPrice = assessPrice;
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

    public String getCalcGasFee() {
        return calcGasFee;
    }

    public void setCalcGasFee(String calcGasFee) {
        this.calcGasFee = calcGasFee == null ? null : calcGasFee.trim();
    }

    public String getGasFee() {
        return gasFee;
    }

    public void setGasFee(String gasFee) {
        this.gasFee = gasFee == null ? null : gasFee.trim();
    }

    public String getGasFeeBz() {
        return gasFeeBz;
    }

    public void setGasFeeBz(String gasFeeBz) {
        this.gasFeeBz = gasFeeBz == null ? null : gasFeeBz.trim();
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

    public String getCalcAffiliatedOther() {
        return calcAffiliatedOther;
    }

    public void setCalcAffiliatedOther(String calcAffiliatedOther) {
        this.calcAffiliatedOther = calcAffiliatedOther == null ? null : calcAffiliatedOther.trim();
    }

    public BigDecimal getAffiliatedOther() {
        return affiliatedOther;
    }

    public void setAffiliatedOther(BigDecimal affiliatedOther) {
        this.affiliatedOther = affiliatedOther;
    }

    public String getAffiliatedOtherBz() {
        return affiliatedOtherBz;
    }

    public void setAffiliatedOtherBz(String affiliatedOtherBz) {
        this.affiliatedOtherBz = affiliatedOtherBz == null ? null : affiliatedOtherBz.trim();
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

    public String getCalcBuildingAreaFee() {
        return calcBuildingAreaFee;
    }

    public void setCalcBuildingAreaFee(String calcBuildingAreaFee) {
        this.calcBuildingAreaFee = calcBuildingAreaFee == null ? null : calcBuildingAreaFee.trim();
    }

    public BigDecimal getBuildingAreaFee() {
        return buildingAreaFee;
    }

    public void setBuildingAreaFee(BigDecimal buildingAreaFee) {
        this.buildingAreaFee = buildingAreaFee;
    }

    public String getBuildingAreaFeeBz() {
        return buildingAreaFeeBz;
    }

    public void setBuildingAreaFeeBz(String buildingAreaFeeBz) {
        this.buildingAreaFeeBz = buildingAreaFeeBz == null ? null : buildingAreaFeeBz.trim();
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

    public String getCalcNoMoveCompensate() {
        return calcNoMoveCompensate;
    }

    public void setCalcNoMoveCompensate(String calcNoMoveCompensate) {
        this.calcNoMoveCompensate = calcNoMoveCompensate == null ? null : calcNoMoveCompensate.trim();
    }

    public BigDecimal getNoMoveCompensate() {
        return noMoveCompensate;
    }

    public void setNoMoveCompensate(BigDecimal noMoveCompensate) {
        this.noMoveCompensate = noMoveCompensate;
    }

    public String getNoMoveCompensateBz() {
        return noMoveCompensateBz;
    }

    public void setNoMoveCompensateBz(String noMoveCompensateBz) {
        this.noMoveCompensateBz = noMoveCompensateBz == null ? null : noMoveCompensateBz.trim();
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

    public String getCalcRmbMoveReward() {
        return calcRmbMoveReward;
    }

    public void setCalcRmbMoveReward(String calcRmbMoveReward) {
        this.calcRmbMoveReward = calcRmbMoveReward == null ? null : calcRmbMoveReward.trim();
    }

    public BigDecimal getRmbMoveReward() {
        return rmbMoveReward;
    }

    public void setRmbMoveReward(BigDecimal rmbMoveReward) {
        this.rmbMoveReward = rmbMoveReward;
    }

    public String getRmbMoveRewardBz() {
        return rmbMoveRewardBz;
    }

    public void setRmbMoveRewardBz(String rmbMoveRewardBz) {
        this.rmbMoveRewardBz = rmbMoveRewardBz == null ? null : rmbMoveRewardBz.trim();
    }

    public String getCalcSmallAreaReward() {
        return calcSmallAreaReward;
    }

    public void setCalcSmallAreaReward(String calcSmallAreaReward) {
        this.calcSmallAreaReward = calcSmallAreaReward == null ? null : calcSmallAreaReward.trim();
    }

    public BigDecimal getSmallAreaReward() {
        return smallAreaReward;
    }

    public void setSmallAreaReward(BigDecimal smallAreaReward) {
        this.smallAreaReward = smallAreaReward;
    }

    public String getSmallAreaRewardBz() {
        return smallAreaRewardBz;
    }

    public void setSmallAreaRewardBz(String smallAreaRewardBz) {
        this.smallAreaRewardBz = smallAreaRewardBz == null ? null : smallAreaRewardBz.trim();
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

    public String getCalcSumCompensate() {
        return calcSumCompensate;
    }

    public void setCalcSumCompensate(String calcSumCompensate) {
        this.calcSumCompensate = calcSumCompensate == null ? null : calcSumCompensate.trim();
    }

    public BigDecimal getSumCompensate() {
        return sumCompensate;
    }

    public void setSumCompensate(BigDecimal sumCompensate) {
        this.sumCompensate = sumCompensate;
    }

    public String getSumCompensateBz() {
        return sumCompensateBz;
    }

    public void setSumCompensateBz(String sumCompensateBz) {
        this.sumCompensateBz = sumCompensateBz == null ? null : sumCompensateBz.trim();
    }

    public BigDecimal getChangeArea() {
        return changeArea;
    }

    public void setChangeArea(BigDecimal changeArea) {
        this.changeArea = changeArea;
    }

    public BigDecimal getCalcm() {
        return calcm;
    }

    public void setCalcm(BigDecimal calcm) {
        this.calcm = calcm;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDeduction() {
        return deduction;
    }

    public void setDeduction(BigDecimal deduction) {
        this.deduction = deduction;
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

    public BigDecimal getReceiveTotal() {
        return receiveTotal;
    }

    public void setReceiveTotal(BigDecimal receiveTotal) {
        this.receiveTotal = receiveTotal;
    }

    public String getPayMoney1() {
        return payMoney1;
    }

    public void setPayMoney1(String payMoney1) {
        this.payMoney1 = payMoney1 == null ? null : payMoney1.trim();
    }

    public String getPayMoney2() {
        return payMoney2;
    }

    public void setPayMoney2(String payMoney2) {
        this.payMoney2 = payMoney2 == null ? null : payMoney2.trim();
    }

    public String getPayMoney3() {
        return payMoney3;
    }

    public void setPayMoney3(String payMoney3) {
        this.payMoney3 = payMoney3 == null ? null : payMoney3.trim();
    }

    public String getPayMoney4() {
        return payMoney4;
    }

    public void setPayMoney4(String payMoney4) {
        this.payMoney4 = payMoney4 == null ? null : payMoney4.trim();
    }

    public String getPayMoney5() {
        return payMoney5;
    }

    public void setPayMoney5(String payMoney5) {
        this.payMoney5 = payMoney5 == null ? null : payMoney5.trim();
    }

    public String getPayMoney6() {
        return payMoney6;
    }

    public void setPayMoney6(String payMoney6) {
        this.payMoney6 = payMoney6 == null ? null : payMoney6.trim();
    }

    public String getPayMoney7() {
        return payMoney7;
    }

    public void setPayMoney7(String payMoney7) {
        this.payMoney7 = payMoney7 == null ? null : payMoney7.trim();
    }

    public String getPayMoney8() {
        return payMoney8;
    }

    public void setPayMoney8(String payMoney8) {
        this.payMoney8 = payMoney8 == null ? null : payMoney8.trim();
    }

    public String getReceiveMoney1() {
        return receiveMoney1;
    }

    public void setReceiveMoney1(String receiveMoney1) {
        this.receiveMoney1 = receiveMoney1 == null ? null : receiveMoney1.trim();
    }

    public String getReceiveMoney2() {
        return receiveMoney2;
    }

    public void setReceiveMoney2(String receiveMoney2) {
        this.receiveMoney2 = receiveMoney2 == null ? null : receiveMoney2.trim();
    }

    public String getReceiveMoney3() {
        return receiveMoney3;
    }

    public void setReceiveMoney3(String receiveMoney3) {
        this.receiveMoney3 = receiveMoney3 == null ? null : receiveMoney3.trim();
    }

    public String getReceiveMoney4() {
        return receiveMoney4;
    }

    public void setReceiveMoney4(String receiveMoney4) {
        this.receiveMoney4 = receiveMoney4 == null ? null : receiveMoney4.trim();
    }

    public String getReceiveMoney5() {
        return receiveMoney5;
    }

    public void setReceiveMoney5(String receiveMoney5) {
        this.receiveMoney5 = receiveMoney5 == null ? null : receiveMoney5.trim();
    }

    public String getReceiveMoney6() {
        return receiveMoney6;
    }

    public void setReceiveMoney6(String receiveMoney6) {
        this.receiveMoney6 = receiveMoney6 == null ? null : receiveMoney6.trim();
    }

    public String getReceiveMoney7() {
        return receiveMoney7;
    }

    public void setReceiveMoney7(String receiveMoney7) {
        this.receiveMoney7 = receiveMoney7 == null ? null : receiveMoney7.trim();
    }

    public String getReceiveMoney8() {
        return receiveMoney8;
    }

    public void setReceiveMoney8(String receiveMoney8) {
        this.receiveMoney8 = receiveMoney8 == null ? null : receiveMoney8.trim();
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