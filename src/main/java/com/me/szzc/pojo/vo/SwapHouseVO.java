package com.me.szzc.pojo.vo;

import com.alibaba.fastjson.JSONObject;
import com.me.szzc.constant.Constant;
import com.me.szzc.enums.GovernmentEnum;
import com.me.szzc.pojo.entity.Adjudication;
import com.me.szzc.pojo.entity.SwapHouse;
import com.me.szzc.utils.BigDecimalUtil;
import com.me.szzc.utils.NumberToCapitalizedUtils;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * 产权调换预览数据-字符串格式
 * @author luwei
 * @date 2019/8/26
 */
@Setter
@Getter
public class SwapHouseVO {

    private Long id;

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
    private String proportion;

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
    private String residenceArea;

    /**
     * 证载房屋用于经营的面积
     */
    private String valueCompensateBusinessArea;

    /**
     * 证载房屋住宅改为门面补助系数
     */
    private String valueCompensateRate;

    /**
     * 经营面积
     */
    private String operateArea;

    /**
     * 办公面积
     */
    private String officeArea;

    /**
     * 生产面积
     */
    private String produceArea;

    /**
     * 其他面积
     */
    private String otherArea;

    /**
     * 未登记面积
     */
    private String noCheckArea;

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
    private String noRegisterProportion;

    /**
     * 未登记房屋经营面积
     */
    private String noRegisterBusinessArea;

    /**
     * 未登记房屋住宅改为门面补助系数
     */
    private String noRegisterRate;

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
    private String historyProportion;

    /**
     * 历史遗留房屋门面经营面积
     */
    private String historyBusinessArea;

    /**
     * 历史遗留房屋门面补助系数
     */
    private String historyRate;

    /**
     * 有证房屋补偿金额
     */
    private String valueCompensate;

    /**
     * 未登记的合法建筑补偿金额
     */
    private String noRegisterLegal;

    /**
     * 历史遗留无证房屋补偿金额
     */
    private String historyLegacy;

    /**
     * 装修补偿每平米单价
     */
    private String decorationCompensateUnitPrice;

    /**
     * 装饰装修补偿金额
     */
    private String decorationCompensate;

    /**
     * 构建物补偿
     */
    private String structureCompensate;

    /**
     * 电话移机费
     */
    private String movePhoneFee;

    /**
     * 有线电视复装费
     */
    private String tvFee;

    /**
     * 电表迁移费
     */
    private String moveAmmeterFee;

    /**
     * 水表迁移费
     */
    private String moveWaterMeterFee;

    /**
     * 宽带网补偿
     */
    private String wifiFee;

    /**
     * 空调移机费
     */
    private String moveAirConditioningFee;

    /**
     * 太阳能热水器移机费
     */
    private String solarHeater;

    /**
     * 其他热水器移机费
     */
    private String otherHeater;

    /**
     * 管道燃气拆装费
     */
    private String gasFee;

    /**
     * 热水器拆装补偿
     */
    private String hotWaterCompensate;

    /**
     * 附属设施小计
     */
    private String subtotal;

    /**
     * 搬家费
     */
    private String moveHouseFee;

    /**
     * 过渡面积
     */
    private String interimArea;

    /**
     * 过渡单价
     */
    private String interimPrice;

    /**
     * 过渡月数
     */
    private Integer interimMonth;

    /**
     * 过渡费
     */
    private String interimFee;

    /**
     * 保底补偿金额
     */
    private String guarantee;

    /**
     * 停产停业损失补偿
     */
    private String suspendBusinessFee;

    /**
     * 结构设计内封闭阳台补偿费
     */
    private String structureBalcony;

    /**
     * 外挑搭建补偿费
     */
    private String structureBuild;

    /**
     * 暗楼补偿费
     */
    private String structureDark;

    /**
     * 夹层补偿费
     */
    private String structureMezzanine;

    /**
     * 顶楼搭建补偿费
     */
    private String structureRoof;

    /**
     * 构建物其他补偿费用
     */
    private String affiliatedOther;

    /**
     * 低保补助
     */
    private String basicLivingSubsidy;

    /**
     * 残疾补助
     */
    private String disabilitySubsidy;

    /**
     * 重症
     */
    private String diseaseSubsidy;

    /**
     * 失独
     */
    private String noChild;

    /**
     * 烈士家庭补助
     */
    private String martyr;

    /**
     * 生活困难补助
     */
    private String lifeCompensate;

    /**
     * 住改商补助
     */
    private String changeCompensate;

    /**
     * 建筑面积补助
     */
    private String buildingAreaFee;

    /**
     * 产权调换时的补偿货币金额
     */
    private String rmbCompensate;

    /**
     * 小户型住房困难补助
     */
    private String smallAreaReward;

    /**
     * 按期签约搬迁奖励
     */
    private String moveReward;

    /**
     * 其他费用
     */
    private String otherFee;

    /**
     * 总计人民币
     */
    private String sumRbm;

    /**
     * 大写人民币
     */
    private String upperRmb;


    /**
     * 审计N天后，甲方付款给乙方
     */
    private Integer afterDayAudit;

    /**
     * 应付-元
     **/
    private String payParm1;
    /**
     * 应付-拾
     **/
    private String payParm2;
    /**
     * 应付-佰
     **/
    private String payParm3;
    /**
     * 应付-仟
     **/
    private String payParm4;
    /**
     * 应付-万
     **/
    private String payParm5;
    /**
     * 应付-拾万
     **/
    private String payParm6;
    /**
     * 应付-佰万
     **/
    private String payParm7;
    /**
     * 应付-仟万
     **/
    private String payParm8;

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
    private String totalPrice;

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
    private String transferRmb;

    /**
     * 差额：征收补偿款总额大于预购房款
     */
    private String difference;

    /**
     * 大写差额
     */
    private String upperDifference;

    /**
     * 差额：收补偿总额小于预购房款
     */
    private String lessDifference;

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
     * 字诀信息
     */
    private String adjudicationJson;

    /**
     * 是否承租人标识
     */
    private Boolean isLesseeFlag;

    /**
     * 片区ID
     */
    private Long areaId;

    /**
     * 项目code
     */
    private String projectCode;

    /**创建时间**/
    private Date createDate;

    /**乙方名称**/
    private String twoHouseOwner;

    /**乙方身份证号**/
    private String twoIdentityNo;

    /**丙方名称**/
    private String thireHouseOwner;

    /**丙方身份证号**/
    private String thireIdentityNo;

    /**
     * 政府征诀字：年
     */
    private String govYear;

    /**
     * 政府征诀字：月
     */
    private String govMonth;

    /**
     * 政府征诀字：日
     */
    private String govDay;

    /**
     * 政府征诀字：文件年号
     */
    private String adjuLetter;

    /**
     * 政府征诀字：文件字号
     */
    private String adjuNum;

    /**
     * 政府公告：年
     */
    private String noticeYear;

    /**
     * 政府公告：月
     */
    private String noticeMonth;

    /**
     * 政府公告：日
     */
    private String noticeDay;


    public static SwapHouseVO parse(SwapHouse entity) throws Exception{
        SwapHouseVO vo = new SwapHouseVO();
        if(entity == null) {
            return vo;
        }

        vo.setId(entity.getId());
        vo.setCardNo(entity.getCardNo());
        vo.setMngOffice(entity.getMngOffice());
        vo.setAgencyCompany(entity.getAgencyCompany());
        vo.setCompany(entity.getCompany());
        vo.setHouseOwner(entity.getHouseOwner());
        vo.setIdentityNo(entity.getIdentityNo());
        vo.setRecompensePlan(entity.getRecompensePlan());
        vo.setAddress(entity.getAddress());
        vo.setCertifiedArea(entity.getCertifiedArea());
        vo.setHouseOwnerNumber(entity.getHouseOwnerNumber());
        vo.setPublicOwnerNumber(entity.getPublicOwnerNumber());
        vo.setProportion(BigDecimalUtil.stripTrailingZeros(entity.getProportion()));
        vo.setUseing(entity.getUseing());
        vo.setAssessPrice(entity.getAssessPrice());
        vo.setCheckInArea(entity.getCheckInArea());
        vo.setResidenceArea(BigDecimalUtil.stripTrailingZeros(entity.getResidenceArea()));
        vo.setValueCompensateBusinessArea(BigDecimalUtil.stripTrailingZeros(entity.getValueCompensateBusinessArea()));
        vo.setValueCompensateRate(BigDecimalUtil.stripTrailingZeros(entity.getValueCompensateRate()));
        vo.setOperateArea(BigDecimalUtil.stripTrailingZeros(entity.getOperateArea()));
        vo.setOfficeArea(BigDecimalUtil.stripTrailingZeros(entity.getOfficeArea()));
        vo.setProduceArea(BigDecimalUtil.stripTrailingZeros(entity.getProduceArea()));
        vo.setOtherArea(BigDecimalUtil.stripTrailingZeros(entity.getOtherArea()));
        vo.setNoCheckArea(BigDecimalUtil.stripTrailingZeros(entity.getNoCheckArea()));

        vo.setNoRegisterLegalArea(entity.getNoRegisterLegalArea());
        vo.setNoRegisterUseing(entity.getNoRegisterUseing());
        vo.setNoRegisterAssessPrice(entity.getNoRegisterAssessPrice());
        vo.setNoRegisterProportion(BigDecimalUtil.stripTrailingZeros(entity.getNoRegisterProportion()));
        vo.setNoRegisterBusinessArea(BigDecimalUtil.stripTrailingZeros(entity.getNoRegisterBusinessArea()));
        vo.setNoRegisterRate(BigDecimalUtil.stripTrailingZeros(entity.getNoRegisterRate()));



        vo.setHistoryLegacyArea(entity.getHistoryLegacyArea());
        vo.setHistoryUseing(entity.getHistoryUseing());
        vo.setHistoryAssessPrice(entity.getHistoryAssessPrice());
        vo.setHistoryProportion(BigDecimalUtil.stripTrailingZeros(entity.getHistoryProportion()));
        vo.setHistoryBusinessArea(BigDecimalUtil.stripTrailingZeros(entity.getHistoryBusinessArea()));
        vo.setHistoryRate(BigDecimalUtil.stripTrailingZeros(entity.getHistoryRate()));


        vo.setValueCompensate(BigDecimalUtil.stripTrailingZeros(entity.getValueCompensate()));
        vo.setNoRegisterLegal(BigDecimalUtil.stripTrailingZeros(entity.getNoRegisterLegal()));
        vo.setHistoryLegacy(BigDecimalUtil.stripTrailingZeros(entity.getHistoryLegacy()));

        vo.setDecorationCompensateUnitPrice(BigDecimalUtil.stripTrailingZeros(entity.getDecorationCompensateUnitPrice()));
        vo.setDecorationCompensate(BigDecimalUtil.stripTrailingZeros(entity.getDecorationCompensate()));

        vo.setStructureCompensate(BigDecimalUtil.stripTrailingZeros(entity.getStructureCompensate()));
        vo.setMovePhoneFee(BigDecimalUtil.stripTrailingZeros(entity.getMovePhoneFee()));
        vo.setTvFee(BigDecimalUtil.stripTrailingZeros(entity.getTvFee()));
        vo.setMoveAmmeterFee(BigDecimalUtil.stripTrailingZeros(entity.getMoveAmmeterFee()));
        vo.setMoveWaterMeterFee(BigDecimalUtil.stripTrailingZeros(entity.getMoveWaterMeterFee()));

        vo.setWifiFee(BigDecimalUtil.stripTrailingZeros(entity.getWifiFee()));
        vo.setMoveAirConditioningFee(BigDecimalUtil.stripTrailingZeros(entity.getMoveAirConditioningFee()));

        vo.setSolarHeater(BigDecimalUtil.stripTrailingZeros(entity.getSolarHeater()));
        vo.setOtherHeater(BigDecimalUtil.stripTrailingZeros(entity.getOtherHeater()));

        vo.setGasFee(BigDecimalUtil.stripTrailingZeros(entity.getGasFee()));
        vo.setHotWaterCompensate(BigDecimalUtil.stripTrailingZeros(entity.getHotWaterCompensate()));
        vo.setSubtotal(BigDecimalUtil.stripTrailingZeros(entity.getSubtotal()));

        vo.setMoveHouseFee(BigDecimalUtil.stripTrailingZeros(entity.getMoveHouseFee()));
        vo.setInterimArea(BigDecimalUtil.stripTrailingZeros(entity.getInterimArea()));
        vo.setInterimPrice(BigDecimalUtil.stripTrailingZeros(entity.getInterimPrice()));
        vo.setInterimMonth(entity.getInterimMonth());
        vo.setInterimFee(BigDecimalUtil.stripTrailingZeros(entity.getInterimFee()));

        vo.setGuarantee(BigDecimalUtil.stripTrailingZeros(entity.getGuarantee()));
        vo.setSuspendBusinessFee(BigDecimalUtil.stripTrailingZeros(entity.getSuspendBusinessFee()));
        vo.setRmbCompensate(BigDecimalUtil.stripTrailingZeros(entity.getRmbCompensate()));

        vo.setStructureBalcony(BigDecimalUtil.stripTrailingZeros(entity.getStructureBalcony()));
        vo.setStructureBuild(BigDecimalUtil.stripTrailingZeros(entity.getStructureBuild()));
        vo.setStructureDark(BigDecimalUtil.stripTrailingZeros(entity.getStructureDark()));
        vo.setStructureMezzanine(BigDecimalUtil.stripTrailingZeros(entity.getStructureMezzanine()));
        vo.setStructureRoof(BigDecimalUtil.stripTrailingZeros(entity.getStructureRoof()));
        vo.setAffiliatedOther(BigDecimalUtil.stripTrailingZeros(entity.getAffiliatedOther()));

        vo.setBasicLivingSubsidy(BigDecimalUtil.stripTrailingZeros(entity.getBasicLivingSubsidy()));
        vo.setDisabilitySubsidy(BigDecimalUtil.stripTrailingZeros(entity.getDisabilitySubsidy()));
        vo.setDiseaseSubsidy(BigDecimalUtil.stripTrailingZeros(entity.getDiseaseSubsidy()));
        vo.setNoChild(BigDecimalUtil.stripTrailingZeros(entity.getNoChild()));
        vo.setMartyr(BigDecimalUtil.stripTrailingZeros(entity.getMartyr()));
        vo.setLifeCompensate(BigDecimalUtil.stripTrailingZeros(entity.getLifeCompensate()));
        vo.setChangeCompensate(BigDecimalUtil.stripTrailingZeros(entity.getChangeCompensate()));
        vo.setBuildingAreaFee(BigDecimalUtil.stripTrailingZeros(entity.getBuildingAreaFee()));
        vo.setSmallAreaReward(BigDecimalUtil.stripTrailingZeros(entity.getSmallAreaReward()));
        vo.setMoveReward(BigDecimalUtil.stripTrailingZeros(entity.getMoveReward()));
        vo.setOtherFee(BigDecimalUtil.stripTrailingZeros(entity.getOtherFee()));
        vo.setSumRbm(BigDecimalUtil.stripTrailingZeros(entity.getSumRbm()));
        vo.setAfterDayAudit(entity.getAfterDayAudit());
        vo.setUpperRmb(entity.getUpperRmb());
        vo.setBeforeDay(entity.getBeforeDay());
        vo.setOtherTermsOne(entity.getOtherTermsOne());

        vo.setNewHouseAddress(entity.getNewHouseAddress());
        vo.setSeat(entity.getSeat());
        vo.setUnit(entity.getUnit());
        vo.setFloors(entity.getFloors());
        vo.setHouseNumber(entity.getHouseNumber());
        vo.setCoveredArea(entity.getCoveredArea());
        vo.setPrice(entity.getPrice());
        vo.setTotalPrice(BigDecimalUtil.stripTrailingZeros(entity.getTotalPrice()));
        vo.setUpperTotalPrice(entity.getUpperTotalPrice());
        vo.setYears(entity.getYears());
        vo.setMonths(entity.getMonths());
        vo.setTransferRmb(BigDecimalUtil.stripTrailingZeros(entity.getTransferRmb()));
        vo.setDifference(BigDecimalUtil.stripTrailingZeros(entity.getDifference()));
        vo.setUpperDifference(entity.getUpperDifference());

        vo.setLessDifference(BigDecimalUtil.stripTrailingZeros(entity.getLessDifference()));
        vo.setUpperLessDifference(entity.getUpperLessDifference());
        vo.setMoveMonth(entity.getMoveMonth());

        //金额大写拆分存储
        String tempMoney = vo.getSumRbm();
        if (tempMoney.length() < 8) {
            tempMoney = "00000000" + tempMoney;
        }
        tempMoney = tempMoney.substring(tempMoney.length() - 8, tempMoney.length());
        vo.setPayParm1(NumberToCapitalizedUtils.CHINESE_NUM_MAP.get(Integer.valueOf(tempMoney.substring(tempMoney.length() - 1, tempMoney.length()))));
        vo.setPayParm2(NumberToCapitalizedUtils.CHINESE_NUM_MAP.get(Integer.valueOf(tempMoney.substring(tempMoney.length() - 2, tempMoney.length() - 1))));
        vo.setPayParm3(NumberToCapitalizedUtils.CHINESE_NUM_MAP.get(Integer.valueOf(tempMoney.substring(tempMoney.length() - 3, tempMoney.length() - 2))));
        vo.setPayParm4(NumberToCapitalizedUtils.CHINESE_NUM_MAP.get(Integer.valueOf(tempMoney.substring(tempMoney.length() - 4, tempMoney.length() - 3))));
        vo.setPayParm5(NumberToCapitalizedUtils.CHINESE_NUM_MAP.get(Integer.valueOf(tempMoney.substring(tempMoney.length() - 5, tempMoney.length() - 4))));
        vo.setPayParm6(NumberToCapitalizedUtils.CHINESE_NUM_MAP.get(Integer.valueOf(tempMoney.substring(tempMoney.length() - 6, tempMoney.length() - 5))));
        vo.setPayParm7(NumberToCapitalizedUtils.CHINESE_NUM_MAP.get(Integer.valueOf(tempMoney.substring(tempMoney.length() - 7, tempMoney.length() - 6))));
        vo.setPayParm8(NumberToCapitalizedUtils.CHINESE_NUM_MAP.get(Integer.valueOf(tempMoney.substring(tempMoney.length() - 8, tempMoney.length() - 7))));

        if (vo.getPayParm8().equals(Constant.CHINESE_ZERO)) {
            vo.setPayParm8("");
        }
        if (StringUtils.isBlank(vo.getPayParm8()) && vo.getPayParm7().equals(Constant.CHINESE_ZERO)) {
            vo.setPayParm7("");
        }
        if (StringUtils.isAllBlank(vo.getPayParm8(), vo.getPayParm7()) && vo.getPayParm6().equals(Constant.CHINESE_ZERO)) {
            vo.setPayParm6("");
        }

        if(entity.getIsLesseeFlag() == null){
            entity.setIsLesseeFlag(false);
        }
        vo.setIsLesseeFlag(entity.getIsLesseeFlag());
        vo.setCreateDate(entity.getCreateDate());

        Adjudication adjudication = null;
        if(StringUtils.isNotBlank(entity.getAdjudicationJson())){
            adjudication = JSONObject.parseObject(entity.getAdjudicationJson(), Adjudication.class);
        }else{
            adjudication = Adjudication.getDefaultAdju();
        }

        vo.setAreaId(entity.getAreaId());
        vo.setProjectCode(entity.getProjectCode());

        vo.setGovYear(adjudication.getGovYear());
        vo.setGovMonth(adjudication.getGovMonth());
        vo.setGovDay(adjudication.getGovDay());
        vo.setAdjuLetter(adjudication.getAdjuLetter());
        vo.setAdjuNum(adjudication.getAdjuNum());
        vo.setNoticeYear(adjudication.getNoticeYear());
        vo.setNoticeMonth(adjudication.getNoticeMonth());
        vo.setNoticeDay(adjudication.getNoticeDay());

        if(entity.getIsLesseeFlag()){
            vo.setTwoHouseOwner("");
            vo.setTwoIdentityNo("");
            vo.setThireHouseOwner(entity.getHouseOwner());
            vo.setThireIdentityNo(entity.getIdentityNo());
        }else{
            vo.setTwoHouseOwner(entity.getHouseOwner());
            vo.setTwoIdentityNo(entity.getIdentityNo());
            vo.setThireHouseOwner("");
            vo.setThireIdentityNo("");
        }

        return vo;
    }

}
