package com.me.szzc.pojo.dto;

import com.me.szzc.enums.GovernmentEnum;
import com.me.szzc.pojo.vo.ProtocolCountMoneyVO;
import com.me.szzc.utils.BigDecimalUtil;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 协议金额统计
 * @author luwei
 * @date 2020/5/21
 */
@Data
public class ProtocolCountMoneyDTO {

    /**项目code**/
    private String projectCode;

    /**项目名称**/
    private String projectName;

    /**片区名称**/
    private String name;

    /**被征收房屋价值补偿金额**/
    private BigDecimal valueCompensate;

    /**未经登记的合法建筑金额**/
    private BigDecimal noRegisterLegal;

    /**历史遗留无证房金额**/
    private BigDecimal historyLegacy;

    /**装修折旧补偿金额**/
    private BigDecimal decorationCompensate;

    /**搬家费**/
    private BigDecimal moveHouseFee;

    /**临时安置补偿费**/
    private BigDecimal interimFee;

    /**水表迁移费**/
    private BigDecimal moveWaterMeterFee;
    /**电表迁移费**/
    private BigDecimal moveAmmeterFee;
    /**空调移机费**/
    private BigDecimal moveAirConditioningFee;
    /**热水器拆装费**/
    private BigDecimal hotWaterCompensate;
    /**管道燃气拆装费**/
    private BigDecimal gasFee;
    /**构建物补偿费**/
    private BigDecimal structureCompensate;
    /**停产停业损失补助**/
    private BigDecimal suspendBusinessFee;
    /**结构内阳台金额**/
    private BigDecimal structureBalcony;
    /**外挑搭建金额**/
    private BigDecimal structureBuild;
    /**暗楼金额**/
    private BigDecimal structureDark;
    /**夹层金额**/
    private BigDecimal structureMezzanine;
    /**楼顶搭建金额**/
    private BigDecimal structureRoof;
    /**附属金额**/
    private BigDecimal affiliatedOther;
    /**住改商补助**/
    private BigDecimal changeCompensate;
    /**货币补偿**/
    private BigDecimal rmbCompensate;
    /**小面积住房搬迁奖励**/
    private BigDecimal smallAreaReward;
    /**生活困难补助**/
    private BigDecimal lifeCompensate;
    /**搬迁奖励**/
    private BigDecimal moveReward;
    /**保底补偿金额**/
    private BigDecimal guarantee;
    /**建筑面积补助**/
    private BigDecimal buildingAreaFee;
    /**其他补偿rmb金额**/
    private BigDecimal otherRmb;

    public ProtocolCountMoneyVO toVo() {
        ProtocolCountMoneyVO vo = new ProtocolCountMoneyVO();
        vo.setProjectCode(this.projectCode);
        vo.setProjectName(GovernmentEnum.getNameByCode(this.projectCode));
        vo.setName(this.name);

        int roundingMode = BigDecimal.ROUND_DOWN;
        int scale = 2;
        vo.setValueCompensate(BigDecimalUtil.stripTrailingZeros(this.valueCompensate.setScale(scale, roundingMode)));
        vo.setNoRegisterLegal(BigDecimalUtil.stripTrailingZeros(this.noRegisterLegal.setScale(scale, roundingMode)));
        vo.setHistoryLegacy(BigDecimalUtil.stripTrailingZeros(this.historyLegacy.setScale(scale, roundingMode)));

        //价值补偿金
        BigDecimal valueCompensateMoney = this.valueCompensate.add(this.noRegisterLegal).add(this.historyLegacy);
        vo.setValueCompensateMoney(BigDecimalUtil.stripTrailingZeros(valueCompensateMoney.setScale(scale,roundingMode)));

        vo.setDecorationCompensate(BigDecimalUtil.stripTrailingZeros(this.decorationCompensate.setScale(scale, roundingMode)));
        vo.setMoveHouseFee(BigDecimalUtil.stripTrailingZeros(this.moveHouseFee.setScale(scale, roundingMode)));
        vo.setInterimFee(BigDecimalUtil.stripTrailingZeros(this.interimFee.setScale(scale, roundingMode)));
        vo.setMoveWaterMeterFee(BigDecimalUtil.stripTrailingZeros(this.moveWaterMeterFee.setScale(scale, roundingMode)));
        vo.setMoveAmmeterFee(BigDecimalUtil.stripTrailingZeros(this.moveAmmeterFee.setScale(scale, roundingMode)));
        vo.setMoveAirConditioningFee(BigDecimalUtil.stripTrailingZeros(this.moveAirConditioningFee.setScale(scale, roundingMode)));
        vo.setHotWaterCompensate(BigDecimalUtil.stripTrailingZeros(this.hotWaterCompensate.setScale(scale, roundingMode)));
        vo.setGasFee(BigDecimalUtil.stripTrailingZeros(this.gasFee.setScale(scale, roundingMode)));

        //构建物补偿-汇总
        BigDecimal structureCompensate = this.structureBalcony.add(this.structureBuild).add(this.structureDark).add(this.structureMezzanine)
                .add(this.structureRoof).add(this.affiliatedOther);

        vo.setStructureCompensate(BigDecimalUtil.stripTrailingZeros(structureCompensate.setScale(scale, roundingMode)));

        vo.setSuspendBusinessFee(BigDecimalUtil.stripTrailingZeros(this.suspendBusinessFee.setScale(scale, roundingMode)));
        vo.setStructureBalcony(BigDecimalUtil.stripTrailingZeros(this.structureBalcony.setScale(scale, roundingMode)));
        vo.setStructureBuild(BigDecimalUtil.stripTrailingZeros(this.structureBuild.setScale(scale, roundingMode)));
        vo.setStructureDark(BigDecimalUtil.stripTrailingZeros(this.structureDark.setScale(scale, roundingMode)));
        vo.setStructureMezzanine(BigDecimalUtil.stripTrailingZeros(this.structureMezzanine.setScale(scale, roundingMode)));
        vo.setStructureRoof(BigDecimalUtil.stripTrailingZeros(this.structureRoof.setScale(scale, roundingMode)));
        vo.setAffiliatedOther(BigDecimalUtil.stripTrailingZeros(this.affiliatedOther.setScale(scale, roundingMode)));
        vo.setChangeCompensate(BigDecimalUtil.stripTrailingZeros(this.changeCompensate.setScale(scale, roundingMode)));
        vo.setRmbCompensate(BigDecimalUtil.stripTrailingZeros(this.rmbCompensate.setScale(scale, roundingMode)));
        vo.setSmallAreaReward(BigDecimalUtil.stripTrailingZeros(this.smallAreaReward.setScale(scale, roundingMode)));
        vo.setLifeCompensate(BigDecimalUtil.stripTrailingZeros(this.lifeCompensate.setScale(scale, roundingMode)));
        vo.setMoveReward(BigDecimalUtil.stripTrailingZeros(this.moveReward.setScale(scale, roundingMode)));
        vo.setGuarantee(BigDecimalUtil.stripTrailingZeros(this.guarantee.setScale(scale, roundingMode)));
        vo.setBuildingAreaFee(BigDecimalUtil.stripTrailingZeros(this.buildingAreaFee.setScale(scale, roundingMode)));
        vo.setOtherRmb(BigDecimalUtil.stripTrailingZeros(this.otherRmb.setScale(scale, roundingMode)));

        return vo;
    }

}
