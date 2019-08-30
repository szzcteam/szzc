package com.me.szzc.pojo.vo;

import com.me.szzc.pojo.entity.RmbRecompense;
import com.me.szzc.utils.BigDecimalUtil;
import lombok.Getter;
import lombok.Setter;


/**
 * 货币补偿预览数据-字符串格式
 * @author luwei
 * @date 2019/8/26
 */
@Getter
@Setter
public class RmbRecompenseVO {

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
     * 临时过渡面积
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
     * 货币补偿补助
     */
    private String rmbCompensate;

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
     * 限N天搬迁
     */
    private Integer beforeDay;

    /**
     * 其他约定1
     */
    private String otherTermsOne;

    /**
     * 其他约定2
     */
    private String otherTermsTwo;


    public static RmbRecompenseVO parse(RmbRecompense entity) throws Exception{
        RmbRecompenseVO vo = new RmbRecompenseVO();
        if(entity == null){
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
        vo.setCertifiedArea(BigDecimalUtil.stripTrailingZeros(entity.getCertifiedArea()));
        vo.setHouseOwnerNumber(entity.getHouseOwnerNumber());
        vo.setPublicOwnerNumber(entity.getPublicOwnerNumber());
        vo.setProportion(BigDecimalUtil.stripTrailingZeros(entity.getProportion()));
        vo.setUseing(entity.getUseing());
        vo.setAssessPrice(entity.getAssessPrice());
        vo.setCheckInArea(BigDecimalUtil.stripTrailingZeros(entity.getCheckInArea()));
        vo.setResidenceArea(BigDecimalUtil.stripTrailingZeros(entity.getResidenceArea()));
        vo.setValueCompensateBusinessArea(BigDecimalUtil.stripTrailingZeros(entity.getValueCompensateBusinessArea()));
        vo.setValueCompensateRate(BigDecimalUtil.stripTrailingZeros(entity.getValueCompensateRate()));
        vo.setOperateArea(BigDecimalUtil.stripTrailingZeros(entity.getOperateArea()));
        vo.setOfficeArea(BigDecimalUtil.stripTrailingZeros(entity.getOfficeArea()));
        vo.setProduceArea(BigDecimalUtil.stripTrailingZeros(entity.getProduceArea()));
        vo.setOtherArea(BigDecimalUtil.stripTrailingZeros(entity.getOtherArea()));
        vo.setNoCheckArea(BigDecimalUtil.stripTrailingZeros(entity.getNoCheckArea()));

        vo.setNoRegisterLegalArea(BigDecimalUtil.stripTrailingZeros(entity.getNoRegisterLegalArea()));
        vo.setNoRegisterUseing(entity.getNoRegisterUseing());
        vo.setNoRegisterAssessPrice(BigDecimalUtil.stripTrailingZeros(entity.getNoRegisterAssessPrice()));
        vo.setNoRegisterProportion(BigDecimalUtil.stripTrailingZeros(entity.getNoRegisterProportion()));
        vo.setNoRegisterBusinessArea(BigDecimalUtil.stripTrailingZeros(entity.getNoRegisterBusinessArea()));
        vo.setNoRegisterRate(BigDecimalUtil.stripTrailingZeros(entity.getNoRegisterRate()));



        vo.setHistoryLegacyArea(BigDecimalUtil.stripTrailingZeros(entity.getHistoryLegacyArea()));
        vo.setHistoryUseing(entity.getHistoryUseing());
        vo.setHistoryAssessPrice(BigDecimalUtil.stripTrailingZeros(entity.getHistoryAssessPrice()));
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
        vo.setDiseaseSubsidy(BigDecimalUtil.stripTrailingZeros(entity.getDisabilitySubsidy()));
        vo.setNoChild(BigDecimalUtil.stripTrailingZeros(entity.getNoChild()));
        vo.setMartyr(BigDecimalUtil.stripTrailingZeros(entity.getMartyr()));
        vo.setLifeCompensate(BigDecimalUtil.stripTrailingZeros(entity.getLifeCompensate()));
        vo.setChangeCompensate(BigDecimalUtil.stripTrailingZeros(entity.getChangeCompensate()));
        vo.setSmallAreaReward(BigDecimalUtil.stripTrailingZeros(entity.getSmallAreaReward()));
        vo.setMoveReward(BigDecimalUtil.stripTrailingZeros(entity.getMoveReward()));


        vo.setOtherFee(BigDecimalUtil.stripTrailingZeros(entity.getOtherFee()));
        vo.setSumRbm(BigDecimalUtil.stripTrailingZeros(entity.getSumRbm()));
        vo.setUpperRmb(entity.getUpperRmb());
        vo.setBeforeDay(entity.getBeforeDay());
        vo.setOtherTermsOne(entity.getOtherTermsOne());
        vo.setOtherTermsTwo(entity.getOtherTermsTwo());
        return vo;
    }
}
