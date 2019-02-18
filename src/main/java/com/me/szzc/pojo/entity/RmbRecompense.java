package com.me.szzc.pojo.entity;

import java.math.BigDecimal;
import java.util.Date;

public class RmbRecompense {
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

    private BigDecimal rmbCompensate;

    private BigDecimal lifeCompensate;

    private BigDecimal changeCompensate;

    private BigDecimal moveReward;

    private BigDecimal closeBalcony;

    private BigDecimal noCheckCompensate;

    private BigDecimal otherFee;

    private BigDecimal sumRbm;

    private String upperRmb;

    private String otherTermsOne;

    private String otherTermsTwo;

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

    public String getMngOffice() {
        return mngOffice;
    }

    public void setMngOffice(String mngOffice) {
        this.mngOffice = mngOffice == null ? null : mngOffice.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getHouseOwner() {
        return houseOwner;
    }

    public void setHouseOwner(String houseOwner) {
        this.houseOwner = houseOwner == null ? null : houseOwner.trim();
    }

    public String getRecompensePlan() {
        return recompensePlan;
    }

    public void setRecompensePlan(String recompensePlan) {
        this.recompensePlan = recompensePlan == null ? null : recompensePlan.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getHouseOwnerNumber() {
        return houseOwnerNumber;
    }

    public void setHouseOwnerNumber(String houseOwnerNumber) {
        this.houseOwnerNumber = houseOwnerNumber == null ? null : houseOwnerNumber.trim();
    }

    public String getPublicOwnerNumber() {
        return publicOwnerNumber;
    }

    public void setPublicOwnerNumber(String publicOwnerNumber) {
        this.publicOwnerNumber = publicOwnerNumber == null ? null : publicOwnerNumber.trim();
    }

    public BigDecimal getProportion() {
        return proportion;
    }

    public void setProportion(BigDecimal proportion) {
        this.proportion = proportion;
    }

    public String getUseing() {
        return useing;
    }

    public void setUseing(String useing) {
        this.useing = useing == null ? null : useing.trim();
    }

    public BigDecimal getCheckInArea() {
        return checkInArea;
    }

    public void setCheckInArea(BigDecimal checkInArea) {
        this.checkInArea = checkInArea;
    }

    public BigDecimal getResidenceArea() {
        return residenceArea;
    }

    public void setResidenceArea(BigDecimal residenceArea) {
        this.residenceArea = residenceArea;
    }

    public BigDecimal getOperateArea() {
        return operateArea;
    }

    public void setOperateArea(BigDecimal operateArea) {
        this.operateArea = operateArea;
    }

    public BigDecimal getOfficeArea() {
        return officeArea;
    }

    public void setOfficeArea(BigDecimal officeArea) {
        this.officeArea = officeArea;
    }

    public BigDecimal getProduceArea() {
        return produceArea;
    }

    public void setProduceArea(BigDecimal produceArea) {
        this.produceArea = produceArea;
    }

    public BigDecimal getOtherArea() {
        return otherArea;
    }

    public void setOtherArea(BigDecimal otherArea) {
        this.otherArea = otherArea;
    }

    public BigDecimal getNoCheckArea() {
        return noCheckArea;
    }

    public void setNoCheckArea(BigDecimal noCheckArea) {
        this.noCheckArea = noCheckArea;
    }

    public BigDecimal getValueCompensate() {
        return valueCompensate;
    }

    public void setValueCompensate(BigDecimal valueCompensate) {
        this.valueCompensate = valueCompensate;
    }

    public BigDecimal getDecorationCompensate() {
        return decorationCompensate;
    }

    public void setDecorationCompensate(BigDecimal decorationCompensate) {
        this.decorationCompensate = decorationCompensate;
    }

    public BigDecimal getStructureCompensate() {
        return structureCompensate;
    }

    public void setStructureCompensate(BigDecimal structureCompensate) {
        this.structureCompensate = structureCompensate;
    }

    public BigDecimal getMovePhoneFee() {
        return movePhoneFee;
    }

    public void setMovePhoneFee(BigDecimal movePhoneFee) {
        this.movePhoneFee = movePhoneFee;
    }

    public BigDecimal getTvFee() {
        return tvFee;
    }

    public void setTvFee(BigDecimal tvFee) {
        this.tvFee = tvFee;
    }

    public BigDecimal getMoveAmmeterFee() {
        return moveAmmeterFee;
    }

    public void setMoveAmmeterFee(BigDecimal moveAmmeterFee) {
        this.moveAmmeterFee = moveAmmeterFee;
    }

    public BigDecimal getMoveWaterMeterFee() {
        return moveWaterMeterFee;
    }

    public void setMoveWaterMeterFee(BigDecimal moveWaterMeterFee) {
        this.moveWaterMeterFee = moveWaterMeterFee;
    }

    public BigDecimal getWifiFee() {
        return wifiFee;
    }

    public void setWifiFee(BigDecimal wifiFee) {
        this.wifiFee = wifiFee;
    }

    public BigDecimal getMoveAirConditioningFee() {
        return moveAirConditioningFee;
    }

    public void setMoveAirConditioningFee(BigDecimal moveAirConditioningFee) {
        this.moveAirConditioningFee = moveAirConditioningFee;
    }

    public BigDecimal getGasFee() {
        return gasFee;
    }

    public void setGasFee(BigDecimal gasFee) {
        this.gasFee = gasFee;
    }

    public BigDecimal getHotWaterCompensate() {
        return hotWaterCompensate;
    }

    public void setHotWaterCompensate(BigDecimal hotWaterCompensate) {
        this.hotWaterCompensate = hotWaterCompensate;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getMoveHouseFee() {
        return moveHouseFee;
    }

    public void setMoveHouseFee(BigDecimal moveHouseFee) {
        this.moveHouseFee = moveHouseFee;
    }

    public BigDecimal getInterimPrice() {
        return interimPrice;
    }

    public void setInterimPrice(BigDecimal interimPrice) {
        this.interimPrice = interimPrice;
    }

    public Integer getInterimMonth() {
        return interimMonth;
    }

    public void setInterimMonth(Integer interimMonth) {
        this.interimMonth = interimMonth;
    }

    public BigDecimal getInterimFee() {
        return interimFee;
    }

    public void setInterimFee(BigDecimal interimFee) {
        this.interimFee = interimFee;
    }

    public BigDecimal getSuspendBusinessFee() {
        return suspendBusinessFee;
    }

    public void setSuspendBusinessFee(BigDecimal suspendBusinessFee) {
        this.suspendBusinessFee = suspendBusinessFee;
    }

    public BigDecimal getRmbCompensate() {
        return rmbCompensate;
    }

    public void setRmbCompensate(BigDecimal rmbCompensate) {
        this.rmbCompensate = rmbCompensate;
    }

    public BigDecimal getLifeCompensate() {
        return lifeCompensate;
    }

    public void setLifeCompensate(BigDecimal lifeCompensate) {
        this.lifeCompensate = lifeCompensate;
    }

    public BigDecimal getChangeCompensate() {
        return changeCompensate;
    }

    public void setChangeCompensate(BigDecimal changeCompensate) {
        this.changeCompensate = changeCompensate;
    }

    public BigDecimal getMoveReward() {
        return moveReward;
    }

    public void setMoveReward(BigDecimal moveReward) {
        this.moveReward = moveReward;
    }

    public BigDecimal getCloseBalcony() {
        return closeBalcony;
    }

    public void setCloseBalcony(BigDecimal closeBalcony) {
        this.closeBalcony = closeBalcony;
    }

    public BigDecimal getNoCheckCompensate() {
        return noCheckCompensate;
    }

    public void setNoCheckCompensate(BigDecimal noCheckCompensate) {
        this.noCheckCompensate = noCheckCompensate;
    }

    public BigDecimal getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(BigDecimal otherFee) {
        this.otherFee = otherFee;
    }

    public BigDecimal getSumRbm() {
        return sumRbm;
    }

    public void setSumRbm(BigDecimal sumRbm) {
        this.sumRbm = sumRbm;
    }

    public String getUpperRmb() {
        return upperRmb;
    }

    public void setUpperRmb(String upperRmb) {
        this.upperRmb = upperRmb == null ? null : upperRmb.trim();
    }

    public String getOtherTermsOne() {
        return otherTermsOne;
    }

    public void setOtherTermsOne(String otherTermsOne) {
        this.otherTermsOne = otherTermsOne == null ? null : otherTermsOne.trim();
    }

    public String getOtherTermsTwo() {
        return otherTermsTwo;
    }

    public void setOtherTermsTwo(String otherTermsTwo) {
        this.otherTermsTwo = otherTermsTwo == null ? null : otherTermsTwo.trim();
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