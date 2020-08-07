package com.me.szzc.pojo.vo;

import lombok.Data;

/**
 * 协议金额统计-返回到前端显示
 * @author luwei
 * @date 2020/5/21
 */
@Data
public class ProtocolCountMoneyVO {

    /**项目code**/
    private String projectCode;

    /**项目名称**/
    private String projectName;

    /**片区名称**/
    private String name;

    private String valueCompensate;

    /**被征收房屋价值补偿金额=有证+历史+未登记**/
    private String valueCompensateMoney;

    /**未经登记的合法建筑金额**/
    private String noRegisterLegal;

    /**历史遗留无证房金额**/
    private String historyLegacy;

    /**装修折旧补偿金额**/
    private String decorationCompensate;

    /**搬家费**/
    private String moveHouseFee;

    /**临时安置补偿费**/
    private String interimFee;

    /**水表迁移费**/
    private String moveWaterMeterFee;
    /**电表迁移费**/
    private String moveAmmeterFee;
    /**空调移机费**/
    private String moveAirConditioningFee;
    /**热水器拆装费**/
    private String hotWaterCompensate;
    /**管道燃气拆装费**/
    private String gasFee;

    /**构建物补偿费=5大构建+其它**/
    private String structureCompensate;
    /**停产停业损失补助**/
    private String suspendBusinessFee;
    /**结构内阳台金额**/
    private String structureBalcony;
    /**外挑搭建金额**/
    private String structureBuild;
    /**暗楼金额**/
    private String structureDark;
    /**夹层金额**/
    private String structureMezzanine;
    /**楼顶搭建金额**/
    private String structureRoof;
    /**附属金额**/
    private String affiliatedOther;
    /**住改商补助**/
    private String changeCompensate;
    /**货币补偿**/
    private String rmbCompensate;
    /**小面积住房搬迁奖励**/
    private String smallAreaReward;
    /**生活困难补助**/
    private String lifeCompensate;
    /**搬迁奖励**/
    private String moveReward;
    /**保底补偿金额**/
    private String guarantee;
    /**建筑面积补助**/
    private String buildingAreaFee;
    /**其他补偿rmb金额**/
    private String otherRmb;

}
