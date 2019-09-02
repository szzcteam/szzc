package com.me.szzc.pojo.vo;

import com.me.szzc.constant.Constant;
import com.me.szzc.pojo.entity.SettleAccounts;
import com.me.szzc.utils.BigDecimalUtil;
import com.me.szzc.utils.DateHelper;
import com.me.szzc.utils.NumberToCapitalizedUtils;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.math.BigDecimal;

/**
 * 结算单页面数据-字符串格式
 * @author luwei
 * @date 2019/3/29
 */
@Data
public class SettleAccountsVO {

    private Long id;

    /**
     * 协议编号
     */
    private String cardNo;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 被征收人
     */
    private String houseOwner;

    /**
     * 被征收人身份证号
     */
    private String identityNo;

    /**
     * 结算方式：0货币补偿、1产权交换
     */
    private Integer compensateType;

    /**
     * 承租人
     */
    private String lessee;

    /**
     * 被征收人电话
     */
    private String phone;

    /**
     * 承租人电话
     */
    private String lesseePhone;

    /**
     * 用途
     */
    private String useing;

    /**
     * 建筑面积-有证面积
     */
    private String certifiedArea;

    /**
     * 未登记合法面积
     */
    private String noRegisterLegalArea;

    /**
     * 历史遗留面积
     */
    private String historyLegacyArea;

    /**
     * 房屋建筑面积
     */
    private String checkInArea;

    /**
     * 未经登记的面积（未登记合法面积+历史遗留面积）
     */
    private String noCheckinArea;

    /**
     * 套内面积
     */
    private String inArea;

    /**
     * 房屋评估单价
     */
    private String assessPrice;

    /**
     * 被征收房屋地址
     */
    private String address;

    /**
     * 被征收房屋价值补偿计算公式
     */
    private String calcValueCompensate;

    /**
     * 被征收房屋价值补偿金额
     */
    private String valueCompensate;

    /**
     * 被征收房屋价值补偿备注
     */
    private String valueCompensateBz;

    /**
     * 未经登记的合法建筑计算公式
     */
    private String calcNoRegisterLegal;

    /**
     * 未经登记的合法建筑金额
     */
    private String noRegisterLegal;

    /**
     * 未经登记的合法建备注
     */
    private String noRegisterLegalBz;

    /**
     * 历史遗留无证房计算公式
     */
    private String calcHistoryLegacy;

    /**
     * 历史遗留无证房金额
     */
    private String historyLegacy;

    /**
     * 历史遗留无证房备注
     */
    private String historyLegacyBz;

    /**
     * 装修折旧补偿
     */
    private String calcDecorationCompensate;

    /**
     * 装修折旧补偿金额
     */
    private String decorationCompensate;

    /**
     * 装修折旧补偿金额备注
     */
    private String decorationCompensateBz;

    /**
     * 搬家费计算公式
     */
    private String calcMoveHouseFee;

    /**
     * 搬家费
     */
    private String moveHouseFee;

    /**
     * 搬家费备注
     */
    private String moveHouseFeeBz;

    /**
     * 临时安置补偿费计算公式
     */
    private String calcInterimFee;

    /**
     * 临时安置补偿费
     */
    private String interimFee;

    /**
     * 临时安置补偿费备注
     */
    private String interimFeeBz;

    /**
     * 水表迁移费计算公式
     */
    private String calcMoveWaterMeterFee;

    /**
     * 水表迁移费
     */
    private String moveWaterMeterFee;

    /**
     * 水表迁移费备注
     */
    private String moveWaterMeterFeeBz;

    /**
     * 电表迁移费计算公式
     */
    private String calcMoveAmmeterFee;

    /**
     * 电表迁移费
     */
    private String moveAmmeterFee;

    /**
     * 电表迁移费备注
     */
    private String moveAmmeterFeeBz;

    /**
     * 空调移机费计算公式
     */
    private String calcMoveAirConditioningFee;

    /**
     * 空调移机费
     */
    private String moveAirConditioningFee;

    /**
     * 空调移机费备注
     */
    private String moveAirConditioningFeeBz;

    /**
     * 热水器拆装费计算
     */
    private String calcHotWaterCompensate;

    /**
     * 热水器拆装费
     */
    private String hotWaterCompensate;

    /**
     * 热水器拆装费备注
     */
    private String hotWaterCompensateBz;

    /**
     * 管道燃气拆装费计算公式
     */
    private String calcGasFee;

    /**
     * 管道燃气拆装费
     */
    private String gasFee;

    /**
     * 管道燃气拆装费备注
     */
    private String gasFeeBz;

    /**
     * 构建物补偿计算公式
     */
    private String calcStructureCompensate;

    /**
     * 构建物补偿费
     */
    private String structureCompensate;

    /**
     * 构建物补偿费备注
     */
    private String structureCompensateBz;

    /**
     * 结构内阳台计算公式
     */
    private String calcStructureBalcony;

    /**
     * 结构内阳台金额
     */
    private String structureBalcony;

    /**
     * 结构内阳台备注
     */
    private String structureBalconyBz;

    /**
     * 外挑搭建计算公式
     */
    private String calcStructureBuild;

    /**
     * 外挑搭建金额
     */
    private String structureBuild;

    /**
     * 外挑搭建备注
     */
    private String structureBuildBz;

    /**
     * 暗楼
     */
    private String calcStructureDark;

    /**
     * 暗楼金额
     */
    private String structureDark;

    /**
     * 暗楼备注
     */
    private String structureDarkBz;

    /**
     * 夹层
     */
    private String calcStructureMezzanine;

    /**
     * 夹层金额
     */
    private String structureMezzanine;

    /**
     * 夹层备注
     */
    private String structureMezzanineBz;

    /**
     * 楼顶搭建
     */
    private String calcStructureRoof;

    /**
     * 楼顶搭建金额
     */
    private String structureRoof;

    /**
     * 楼顶搭建备注
     */
    private String structureRoofBz;

    /**
     * 其他描述
     */
    private String affiliatedOtherDesc;

    /**
     * 附属其他计算
     */
    private String calcAffiliatedOther;

    /**
     * 附属金额
     */
    private String affiliatedOther;

    /**
     * 附属其他备注
     */
    private String affiliatedOtherBz;

    /**
     * 未登记房屋补偿计算公式
     */
    private String calcNoCheckCompensate;

    /**
     * 未登记房屋补偿
     */
    private String noCheckCompensate;

    /**
     * 未登记房屋补偿备注
     */
    private String noCheckCompensateBz;

    /**
     * 货币补偿补助计算公式
     */
    private String calcRmbCompensate;

    /**
     * 货币补偿
     */
    private String rmbCompensate;

    /**
     * 货币补助备注
     */
    private String rmbCompensateBz;

    /**
     * 生活困难补助计算公式
     */
    private String calcLifeCompensate;

    /**
     * 生活困难补助
     */
    private String lifeCompensate;

    /**
     * 生活困难补助备注
     */
    private String lifeCompensateBz;

    /**
     * 住改商补助计算公式
     */
    private String calcChangeCompensate;

    /**
     * 住改商补助
     */
    private String changeCompensate;

    /**
     * 住改商补助备注
     */
    private String changeCompensateBz;

    /**
     * 建筑面积补助计算公式
     */
    private String calcBuildingAreaFee;

    /**
     * 建筑面积补助
     */
    private String buildingAreaFee;

    /**
     * 建筑面积补助
     */
    private String buildingAreaFeeBz;

    /**
     * 停产停业损失补助
     */
    private String calcSuspendBusinessFee;

    /**
     * 停产停业损失补助
     */
    private String suspendBusinessFee;

    /**
     * 停产停业损失补助备注
     */
    private String suspendBusinessFeeBz;

    /**
     * 不可移动设备设施补助计算公式
     */
    private String calcNoMoveCompensate;

    /**
     * 不可移动设备设施补助
     */
    private String noMoveCompensate;

    /**
     * 不可移动设备设施补助备注
     */
    private String noMoveCompensateBz;

    /**
     * 搬迁奖励计算公式
     */
    private String calcMoveReward;

    /**
     * 搬迁奖励
     */
    private String moveReward;

    /**
     * 搬迁奖励备注
     */
    private String moveRewardBz;

    /**
     * 货币搬迁奖励计算公式
     */
    private String calcRmbMoveReward;

    /**
     * 货币搬迁奖励
     */
    private String rmbMoveReward;

    /**
     * 货币搬迁奖励备注
     */
    private String rmbMoveRewardBz;

    /**
     * 小面积住房搬迁奖励计算公式
     */
    private String calcSmallAreaReward;

    /**
     * 小面积住房搬迁奖励
     */
    private String smallAreaReward;

    /**
     * 小面积住房搬迁奖励备注
     */
    private String smallAreaRewardBz;

    /**
     * 保底补偿计算公式
     */
    private String calcGuarantee;

    /**
     * 保底补偿金额
     */
    private String guarantee;

    /**
     * 保底补偿备注
     */
    private String guaranteeBz;

    /**
     * 其他描述
     */
    private String otherDesc;

    /**
     * 其他计算公式
     */
    private String calcOther;

    /**
     * 其他补偿rmb金额
     */
    private String otherRmb;

    /**
     * 其他备注
     */
    private String otherBz;

    /**
     * 征收房屋补偿合计计算公式
     */
    private String calcSumCompensate;

    /**
     * 合计补偿
     */
    private String sumCompensate;

    /**
     * 补偿合计备注
     */
    private String sumCompensateBz;

    /**
     * 产权调换建筑面积
     */
    private String changeArea;

    /**
     * 产权调换建筑面积计算公式
     */
    private String calcChangeArea;

    /**
     * 产权调换，其中参与计算面积
     */
    private String calcm;

    /**
     * 房屋置换金额
     */
    private String price;

    /**
     * 房屋置换金额
     */
    private String houseMoney;

    /**
     * 房屋置换金额备注
     */
    private String houseMoneyBz;

    /**
     * 计算已抵扣安置房款
     */
    private String calcDeduction;

    /**
     * 已抵扣安置房款
     */
    private String deduction;

    /**
     * 已抵扣安置房款备注
     */
    private String deductionBz;

    /**
     * 交换房单价
     */
    private String swapPrice;

    /**
     * 交换房单价1
     */
    private String swapPrice1;

    /**
     * 交换房单价2
     */
    private String swapPrice2;

    /**
     * 交换房单价3
     */
    private String swapPrice3;

    /**
     * 交换房单价4
     */
    private String swapPrice4;

    /**
     * 交换房单价5
     */
    private String swapPrice5;

    /**
     * 交换房面积
     */
    private String swapArea;

    /**
     * 交换房面积1
     */
    private String swapArea1;

    /**
     * 交换房面积2
     */
    private String swapArea2;

    /**
     * 交换房面积3
     */
    private String swapArea3;

    /**
     * 交换房面积4
     */
    private String swapArea4;

    /**
     * 交换房面积5
     */
    private String swapArea5;

    /**
     * 交换房金额
     */
    private String swapMoney;

    /**
     * 交换房金额1
     */
    private String swapMoney1;

    /**
     * 交换房金额2
     */
    private String swapMoney2;

    /**
     * 交换房金额3
     */
    private String swapMoney3;

    /**
     * 交换房金额4
     */
    private String swapMoney4;

    /**
     * 交换房金额5
     */
    private String swapMoney5;

    /**
     * 应付合计
     */
    private String payTotal;
    

    /**
     * 应付合计大写元
     */
    private String payMoney;

    /**应付-元**/
    private String payParm1;
    /**应付-拾**/
    private String payParm2;
    /**应付-佰**/
    private String payParm3;
    /**应付-仟**/
    private String payParm4;
    /**应付-万**/
    private String payParm5;
    /**应付-拾万**/
    private String payParm6;
    /**应付-佰万**/
    private String payParm7;
    /**应付-仟万**/
    private String payParm8;

    /**创建日期**/
    private String createDate;

   

    public static SettleAccountsVO parse(SettleAccounts entity) throws Exception{
        SettleAccountsVO vo = new SettleAccountsVO();
        if(entity == null) {
            return vo;
        }

        vo.setId(entity.getId());
        vo.setCardNo(entity.getCardNo());
        vo.setProjectName(entity.getProjectName());
        vo.setHouseOwner(entity.getHouseOwner());
        vo.setIdentityNo(entity.getIdentityNo());
        vo.setCompensateType(entity.getCompensateType());
        vo.setLessee(entity.getLessee());
        vo.setPhone(entity.getPhone());
        vo.setLesseePhone(entity.getLesseePhone());
        vo.setUseing(entity.getUseing());
        vo.setCheckInArea(BigDecimalUtil.stripTrailingZeros(entity.getCheckInArea()));
        vo.setCertifiedArea(BigDecimalUtil.stripTrailingZeros(entity.getCertifiedArea()));
        vo.setNoRegisterLegalArea(BigDecimalUtil.stripTrailingZeros(entity.getNoRegisterLegalArea()));
        vo.setHistoryLegacyArea(BigDecimalUtil.stripTrailingZeros(entity.getHistoryLegacyArea()));
        vo.setNoCheckinArea(BigDecimalUtil.stripTrailingZeros(entity.getNoCheckinArea()));
        vo.setInArea(BigDecimalUtil.stripTrailingZeros(entity.getInArea()));
        vo.setAssessPrice(BigDecimalUtil.stripTrailingZeros(entity.getAssessPrice()));
        vo.setAddress(entity.getAddress());

        //有证的
        vo.setCalcValueCompensate(entity.getCalcValueCompensate());
        vo.setValueCompensate(BigDecimalUtil.stripTrailingZeros(entity.getValueCompensate()));
        vo.setValueCompensateBz(entity.getValueCompensateBz());

        //未经登记合法
        if(entity.getNoRegisterLegal() != null && entity.getNoRegisterLegal().compareTo(BigDecimal.ZERO) > 0){
            vo.setCalcNoRegisterLegal(entity.getCalcNoRegisterLegal());
            vo.setNoRegisterLegal(BigDecimalUtil.stripTrailingZeros(entity.getNoRegisterLegal()));
        }
        vo.setNoRegisterLegalBz(entity.getNoRegisterLegalBz());

        //历史遗留
        if(entity.getHistoryLegacy() != null && entity.getHistoryLegacy().compareTo(BigDecimal.ZERO) > 0){
            vo.setCalcHistoryLegacy(entity.getCalcHistoryLegacy());
            vo.setHistoryLegacy(BigDecimalUtil.stripTrailingZeros(entity.getHistoryLegacy()));
        }
        vo.setHistoryLegacyBz(entity.getHistoryLegacyBz());

        //装修补偿金额，存在2个公式，其中第一个可能为0*0
        if(entity.getDecorationCompensate() != null && entity.getDecorationCompensate().compareTo(BigDecimal.ZERO) > 0){
            vo.setCalcDecorationCompensate(entity.getCalcDecorationCompensate());
            vo.setDecorationCompensate(BigDecimalUtil.stripTrailingZeros(entity.getDecorationCompensate()));
            vo.setCalcDecorationCompensate(vo.getCalcDecorationCompensate().replace("0*0+", ""));
        }
        vo.setDecorationCompensateBz(entity.getDecorationCompensateBz());

        //搬迁补偿
        String calcMoveHouseFee = entity.getCalcMoveHouseFee();
        if (StringUtils.isNotBlank(calcMoveHouseFee)) {
            vo.setCalcMoveHouseFee(calcMoveHouseFee);
        }

        vo.setMoveHouseFee(BigDecimalUtil.stripTrailingZeros(entity.getMoveHouseFee()));
        vo.setMoveHouseFeeBz(entity.getMoveHouseFeeBz());

        //临时过渡费
        if(entity.getInterimFee() != null && entity.getInterimFee().compareTo(BigDecimal.ZERO) > 0){
            vo.setCalcInterimFee(entity.getCalcInterimFee());
            vo.setInterimFee(BigDecimalUtil.stripTrailingZeros(entity.getInterimFee()));
            vo.setCalcInterimFee(vo.getCalcInterimFee().replace("0*0*0+", ""));
        }
        vo.setInterimFeeBz(entity.getInterimFeeBz());

        //水表
        String calcMoveWaterMeterFee = entity.getCalcMoveWaterMeterFee();
        if(StringUtils.isNotBlank(calcMoveWaterMeterFee)) {
            int water_add_index = calcMoveWaterMeterFee.indexOf("+");
            if(water_add_index != -1) {
                String[] arr = calcMoveWaterMeterFee.split("\\+");
                String txt = "";
                String num1 = arr[0].substring(0, arr[0].indexOf("*"));
                if(Integer.valueOf(num1) > 0) {
                    txt += num1 + " 主表 ";
                }
                String num2 = arr[1].substring(0, arr[1].indexOf("*"));
                if(Integer.valueOf(num2) >0 ) {
                    txt += num2 + " 副表";
                }
                vo.setCalcMoveWaterMeterFee(txt);
            }
        }
        vo.setMoveWaterMeterFee(BigDecimalUtil.stripTrailingZeros(entity.getMoveWaterMeterFee()));
        vo.setMoveWaterMeterFeeBz(entity.getMoveWaterMeterFeeBz());

        //电表
        String calcMoveAmmeterFee = entity.getCalcMoveAmmeterFee();
        if(StringUtils.isNotBlank(calcMoveAmmeterFee)) {
            int ammeter_add_index = calcMoveAmmeterFee.indexOf("+");
            if(ammeter_add_index != -1) {
                String[] arr = calcMoveAmmeterFee.split("\\+");
                String txt = "";
                String num1 = arr[0].substring(0, arr[0].indexOf("*"));
                if(Integer.valueOf(num1) > 0) {
                    txt += num1 + " 民用独表 ";
                }
                String num2 = arr[1].substring(0, arr[1].indexOf("*"));
                if(Integer.valueOf(num2) >0 ) {
                    txt += num2 + " 分表";
                }
                if(arr.length == 4) {
                    String num3 = arr[2].substring(0, arr[2].indexOf("*"));
                    if(Integer.valueOf(num3) >0 ) {
                        txt += num3 + " 三相电表";
                    }
                    String num4 = arr[3].substring(0, arr[3].indexOf("*"));
                    if(Integer.valueOf(num4) >0 ) {
                        txt += num4 + " 分时表";
                    }
                }

                vo.setCalcMoveAmmeterFee(txt);
            }
        }

        vo.setMoveAmmeterFee(BigDecimalUtil.stripTrailingZeros(entity.getMoveAmmeterFee()));
        vo.setMoveAmmeterFeeBz(entity.getMoveAmmeterFeeBz());

        //空调
        String calcMoveAirConditioningFee = entity.getCalcMoveAirConditioningFee();
        if(StringUtils.isNotBlank(calcMoveAirConditioningFee)) {
            int  air_add_index = calcMoveAirConditioningFee.indexOf("+");
            if (air_add_index != -1) {
                String[] airArr = calcMoveAirConditioningFee.split("\\+");
                String txt = "";
                String num1 = airArr[0].substring(0, airArr[0].indexOf("*"));
                if(Integer.valueOf(num1) > 0 ) {
                    txt += num1 + " 窗机 ";
                }
                String num2 = airArr[1].substring(0, airArr[1].indexOf("*"));
                if(Integer.valueOf(num2) > 0 ) {
                    txt += num2 + " 挂机 ";
                }
                String num3 = airArr[2].substring(0, airArr[2].indexOf("*"));
                if(Integer.valueOf(num3) > 0 ) {
                    txt += num3 + " 柜机 ";
                }
                vo.setCalcMoveAirConditioningFee(txt);
            }
        }

        vo.setMoveAirConditioningFee(BigDecimalUtil.stripTrailingZeros(entity.getMoveAirConditioningFee()));
        vo.setMoveAirConditioningFeeBz(entity.getMoveAirConditioningFeeBz());

        //热水器拆装费
        vo.setCalcHotWaterCompensate(entity.getCalcHotWaterCompensate());
        vo.setHotWaterCompensate(BigDecimalUtil.stripTrailingZeros(entity.getHotWaterCompensate()));
        vo.setHotWaterCompensateBz(entity.getHotWaterCompensateBz());

        if (entity.getGasFee() != null && entity.getGasFee().compareTo(BigDecimal.ZERO) > 0) {
            vo.setCalcGasFee(entity.getCalcGasFee());
            vo.setGasFee(BigDecimalUtil.stripTrailingZeros(entity.getGasFee()));
        }
        vo.setGasFeeBz(entity.getGasFeeBz());


        //构建物补偿
        vo.setCalcStructureCompensate(entity.getCalcStructureCompensate());
        vo.setStructureCompensate(BigDecimalUtil.stripTrailingZeros(entity.getStructureCompensate()));
        vo.setStructureCompensateBz(entity.getStructureCompensateBz());

        //5大构建物
        if (entity.getStructureBalcony() != null && entity.getStructureBalcony().compareTo(BigDecimal.ZERO) > 0) {
            vo.setCalcStructureBalcony(entity.getCalcStructureBalcony());
            vo.setStructureBalcony(BigDecimalUtil.stripTrailingZeros(entity.getStructureBalcony()));
        }
        vo.setStructureBalconyBz(entity.getStructureBalconyBz());

        if (entity.getStructureBuild() != null && entity.getStructureBuild().compareTo(BigDecimal.ZERO) > 0) {
            vo.setCalcStructureBuild(entity.getCalcStructureBuild());
            vo.setStructureBuild(BigDecimalUtil.stripTrailingZeros(entity.getStructureBuild()));
        }
        vo.setStructureBuildBz(entity.getStructureBuildBz());

        if (entity.getStructureDark() != null && entity.getStructureDark().compareTo(BigDecimal.ZERO) > 0) {
            vo.setCalcStructureDark(entity.getCalcStructureDark());
            vo.setStructureDark(BigDecimalUtil.stripTrailingZeros(entity.getStructureDark()));
        }
        vo.setStructureDarkBz(entity.getStructureDarkBz());

        if (entity.getStructureMezzanine() != null && entity.getStructureMezzanine().compareTo(BigDecimal.ZERO) > 0) {
            vo.setCalcStructureMezzanine(entity.getCalcStructureMezzanine());
            vo.setStructureMezzanine(BigDecimalUtil.stripTrailingZeros(entity.getStructureMezzanine()));
        }
        vo.setStructureMezzanineBz(entity.getStructureMezzanineBz());

        if (entity.getStructureRoof() != null && entity.getStructureRoof().compareTo(BigDecimal.ZERO) > 0) {
            vo.setCalcStructureRoof(entity.getCalcStructureRoof());
            vo.setStructureRoof(BigDecimalUtil.stripTrailingZeros(entity.getStructureRoof()));
        }
        vo.setStructureRoofBz(entity.getStructureRoofBz());

        //其他描述
        vo.setAffiliatedOtherDesc(entity.getAffiliatedOtherDesc());
        vo.setCalcAffiliatedOther(entity.getCalcAffiliatedOther());
        vo.setAffiliatedOther(BigDecimalUtil.stripTrailingZeros(entity.getAffiliatedOther()));
        vo.setAffiliatedOtherBz(entity.getAffiliatedOtherBz());

        //未登记房屋补偿计算公式
        if(entity.getNoCheckCompensate()!=null&&entity.getNoCheckCompensate().compareTo(BigDecimal.ZERO)>0){
            vo.setCalcNoCheckCompensate(entity.getCalcNoCheckCompensate());
            vo.setNoCheckCompensate(BigDecimalUtil.stripTrailingZeros(entity.getNoCheckCompensate()));
        }
        vo.setNoCheckCompensateBz(entity.getNoCheckCompensateBz());

        //货币补偿补助计算公式
        if (entity.getRmbCompensate() != null && entity.getRmbCompensate().compareTo(BigDecimal.ZERO) > 0) {
            vo.setCalcRmbCompensate(entity.getCalcRmbCompensate());
            vo.setRmbCompensate(BigDecimalUtil.stripTrailingZeros(entity.getRmbCompensate()));
        }
        vo.setRmbCompensateBz(entity.getRmbCompensateBz());

        //生活困难补助
        String calcLifeCompensate = entity.getCalcLifeCompensate();
        if(StringUtils.isNotBlank(calcLifeCompensate)) {
            String[] arr = calcLifeCompensate.split("\\+");
            String txt = "";
            if (Integer.valueOf(arr[0]) > 0) {
                txt+= " 重症 ";
            }
            if (Integer.valueOf(arr[1]) > 0) {
                txt+= " 残疾 ";
            }
            if (Integer.valueOf(arr[2]) > 0) {
                txt+=" 低保 ";
            }
            if(arr.length == 5){
                if (Integer.valueOf(arr[3]) > 0) {
                    txt+=" 烈士家庭 ";
                }
                if (Integer.valueOf(arr[4]) > 0) {
                    txt+=" 失独 ";
                }
            }
            vo.setCalcLifeCompensate(txt.trim());
        }

        vo.setLifeCompensate(BigDecimalUtil.stripTrailingZeros(entity.getLifeCompensate()));
        vo.setLifeCompensateBz(entity.getLifeCompensateBz());

        //住改商补助
        if (entity.getChangeCompensate() != null && entity.getChangeCompensate().compareTo(BigDecimal.ZERO) > 0) {
            vo.setCalcChangeCompensate(entity.getCalcChangeCompensate());
            vo.setChangeCompensate(BigDecimalUtil.stripTrailingZeros(entity.getChangeCompensate()));
            vo.setCalcChangeCompensate(vo.getCalcChangeCompensate().replace("0*(0-0)*0+", ""));
        }
        vo.setChangeCompensateBz(entity.getChangeCompensateBz());

        //建筑面积补助计算公式
        if (entity.getBuildingAreaFee() != null && entity.getBuildingAreaFee().compareTo(BigDecimal.ZERO) > 0) {
            vo.setCalcBuildingAreaFee(entity.getCalcBuildingAreaFee());
            vo.setBuildingAreaFee(BigDecimalUtil.stripTrailingZeros(entity.getBuildingAreaFee()));
        }
        vo.setBuildingAreaFeeBz(entity.getBuildingAreaFeeBz());


        //停产停业损失补助
        if (entity.getSuspendBusinessFee() != null && entity.getSuspendBusinessFee().compareTo(BigDecimal.ZERO) > 0) {
            vo.setCalcSuspendBusinessFee(entity.getCalcSuspendBusinessFee());
            vo.setSuspendBusinessFee(BigDecimalUtil.stripTrailingZeros(entity.getSuspendBusinessFee()));
        }
        vo.setSuspendBusinessFeeBz(entity.getSuspendBusinessFeeBz());

        //不可移动设备设施补助
        vo.setCalcNoMoveCompensate(entity.getCalcNoMoveCompensate());
        vo.setNoMoveCompensate(BigDecimalUtil.stripTrailingZeros(entity.getNoMoveCompensate()));
        vo.setNoMoveCompensateBz(entity.getNoMoveCompensateBz());


        //搬迁奖励
        String calcMoveReward = entity.getCalcMoveReward();
        if (entity.getMoveReward() != null && entity.getMoveReward().compareTo(BigDecimal.ZERO) > 0) {
            vo.setCalcMoveReward(calcMoveReward);
            vo.setMoveReward(BigDecimalUtil.stripTrailingZeros(entity.getMoveReward()));
        }
        vo.setMoveRewardBz(entity.getMoveRewardBz());

        //货币搬迁奖励
        if(entity.getRmbMoveReward()!=null&&entity.getRmbMoveReward().compareTo(BigDecimal.ZERO)>0){
            vo.setCalcRmbMoveReward(entity.getCalcRmbMoveReward());
            vo.setRmbMoveReward(BigDecimalUtil.stripTrailingZeros(entity.getRmbMoveReward()));
        }
        vo.setRmbMoveRewardBz(entity.getRmbMoveRewardBz());

        //小面积住房搬迁奖励
        if(entity.getSmallAreaReward() != null && entity.getSmallAreaReward().compareTo(BigDecimal.ZERO) > 0){
            vo.setCalcSmallAreaReward(entity.getCalcSmallAreaReward());
            vo.setSmallAreaReward(BigDecimalUtil.stripTrailingZeros(entity.getSmallAreaReward()));
        }
        vo.setSmallAreaRewardBz(entity.getSmallAreaRewardBz());

        //保底
        if(entity.getGuarantee() != null && entity.getGuarantee().compareTo(BigDecimal.ZERO) > 0){
            vo.setCalcGuarantee(entity.getCalcGuarantee());
            vo.setGuarantee(BigDecimalUtil.stripTrailingZeros(entity.getGuarantee()));
        }
        vo.setGuaranteeBz(entity.getGuaranteeBz());

        //其他描述
        if(entity.getOtherRmb() != null && entity.getOtherRmb().compareTo(BigDecimal.ZERO) > 0){
            vo.setOtherDesc(entity.getOtherDesc());
            vo.setCalcOther(entity.getCalcOther());
            vo.setOtherRmb(BigDecimalUtil.stripTrailingZeros(entity.getOtherRmb()));
            vo.setOtherBz(entity.getOtherBz());
        }

        //征收房屋补偿合计
        vo.setCalcSumCompensate(entity.getCalcSumCompensate());
        vo.setSumCompensate(BigDecimalUtil.stripTrailingZeros(entity.getSumCompensate()));
        vo.setSumCompensateBz(entity.getSumCompensateBz());

        //产权调换建筑面积
        vo.setChangeArea(BigDecimalUtil.stripTrailingZeros(entity.getCheckInArea()));
        vo.setCalcChangeArea(entity.getCalcChangeArea());

        vo.setCalcm(BigDecimalUtil.stripTrailingZeros(entity.getCalcm()));
        vo.setPrice(BigDecimalUtil.stripTrailingZeros(entity.getPrice()));
        vo.setHouseMoney(BigDecimalUtil.stripTrailingZeros(entity.getHouseMoney()));
        vo.setHouseMoneyBz(entity.getHouseMoneyBz());

        //已抵扣安置房款
        if(entity.getDeduction() != null && entity.getDeduction().compareTo(BigDecimal.ZERO) > 0){
            vo.setDeduction(BigDecimalUtil.stripTrailingZeros(entity.getDeduction()));
        }
        vo.setCalcDeduction(entity.getCalcDeduction());
        vo.setDeductionBz(entity.getDeductionBz());


        //交换房
        if(entity.getSwapPrice1() != null && entity.getSwapPrice1().compareTo(BigDecimal.ZERO) > 0){
            vo.setSwapPrice1(BigDecimalUtil.stripTrailingZeros(entity.getSwapPrice1()));
        }else{
            vo.setSwapPrice1("");
        }

        if(entity.getSwapPrice2() != null && entity.getSwapPrice2().compareTo(BigDecimal.ZERO) > 0){
            vo.setSwapPrice2(BigDecimalUtil.stripTrailingZeros(entity.getSwapPrice2()));
        }else{
            vo.setSwapPrice2("");
        }
        if(entity.getSwapPrice3() != null && entity.getSwapPrice3().compareTo(BigDecimal.ZERO) > 0){
            vo.setSwapPrice3(BigDecimalUtil.stripTrailingZeros(entity.getSwapPrice3()));
        }else{
            vo.setSwapPrice3("");
        }
        if(entity.getSwapPrice4() != null && entity.getSwapPrice4().compareTo(BigDecimal.ZERO) > 0){
            vo.setSwapPrice4(BigDecimalUtil.stripTrailingZeros(entity.getSwapPrice4()));
        }else{
            vo.setSwapPrice4("");
        }
        if(entity.getSwapPrice5() != null && entity.getSwapPrice5().compareTo(BigDecimal.ZERO) > 0){
            vo.setSwapPrice5(BigDecimalUtil.stripTrailingZeros(entity.getSwapPrice5()));
        }else{
            vo.setSwapPrice5("");
        }

        vo.setSwapPrice(vo.getSwapPrice1()+" "+vo.getSwapPrice2()+" "+vo.getSwapPrice3()+" " + vo.getSwapPrice4() + vo.getSwapPrice5());
        vo.setSwapPrice(vo.getSwapPrice().trim());

        if(entity.getSwapArea1() != null && entity.getSwapArea1().compareTo(BigDecimal.ZERO) > 0){
            vo.setSwapArea1(BigDecimalUtil.stripTrailingZeros(entity.getSwapArea1()));
        }else{
            vo.setSwapArea1("");
        }
        if(entity.getSwapArea2() != null && entity.getSwapArea2().compareTo(BigDecimal.ZERO) > 0){
            vo.setSwapArea2(BigDecimalUtil.stripTrailingZeros(entity.getSwapArea2()));
        }else{
            vo.setSwapArea2("");
        }
        if(entity.getSwapArea3() != null && entity.getSwapArea3().compareTo(BigDecimal.ZERO) > 0){
            vo.setSwapArea3(BigDecimalUtil.stripTrailingZeros(entity.getSwapArea3()));
        }else{
            vo.setSwapArea3("");
        }
        if(entity.getSwapArea4() != null && entity.getSwapArea4().compareTo(BigDecimal.ZERO) > 0){
            vo.setSwapArea4(BigDecimalUtil.stripTrailingZeros(entity.getSwapArea4()));
        }else{
            vo.setSwapArea4("");
        }

        if(entity.getSwapArea5() != null && entity.getSwapArea5().compareTo(BigDecimal.ZERO) > 0){
            vo.setSwapArea5(BigDecimalUtil.stripTrailingZeros(entity.getSwapArea5()));
        }else{
            vo.setSwapArea5("");
        }

        vo.setSwapArea(vo.getSwapArea1()+" "+vo.getSwapArea2()+" "+vo.getSwapArea3()+" " + vo.getSwapArea4() + vo.getSwapArea5());
        vo.setSwapArea(vo.getSwapArea().trim());


        vo.setSwapMoney1(entity.getSwapMoney1());
        vo.setSwapMoney2(entity.getSwapMoney2());
        vo.setSwapMoney3(entity.getSwapMoney3());
        vo.setSwapMoney4(entity.getSwapMoney4());
        vo.setSwapMoney5(entity.getSwapMoney5());
        vo.setSwapMoney(vo.getSwapMoney1()+" "+vo.getSwapMoney2()+" "+vo.getSwapMoney3()+" " + vo.getSwapMoney4() + vo.getSwapMoney5());
        vo.setSwapMoney(vo.getSwapMoney().trim());


        vo.setPayTotal(BigDecimalUtil.stripTrailingZeros(entity.getPayTotal()));
        vo.setPayMoney(entity.getPayMoney());
        //金额大写拆分存储
        String tempMoney =  vo.getPayTotal();
        if(tempMoney.length()<8){
            tempMoney = "00000000"+tempMoney;
        }
        tempMoney = tempMoney.substring(tempMoney.length()-8, tempMoney.length());
        vo.setPayParm1(NumberToCapitalizedUtils.CHINESE_NUM_MAP.get(Integer.valueOf(tempMoney.substring(tempMoney.length()-1, tempMoney.length()))));
        vo.setPayParm2(NumberToCapitalizedUtils.CHINESE_NUM_MAP.get(Integer.valueOf(tempMoney.substring(tempMoney.length()-2, tempMoney.length()-1))));
        vo.setPayParm3(NumberToCapitalizedUtils.CHINESE_NUM_MAP.get(Integer.valueOf(tempMoney.substring(tempMoney.length()-3, tempMoney.length()-2))));
        vo.setPayParm4(NumberToCapitalizedUtils.CHINESE_NUM_MAP.get(Integer.valueOf(tempMoney.substring(tempMoney.length()-4, tempMoney.length()-3))));
        vo.setPayParm5(NumberToCapitalizedUtils.CHINESE_NUM_MAP.get(Integer.valueOf(tempMoney.substring(tempMoney.length()-5, tempMoney.length()-4))));
        vo.setPayParm6(NumberToCapitalizedUtils.CHINESE_NUM_MAP.get(Integer.valueOf(tempMoney.substring(tempMoney.length()-6, tempMoney.length()-5))));
        vo.setPayParm7(NumberToCapitalizedUtils.CHINESE_NUM_MAP.get(Integer.valueOf(tempMoney.substring(tempMoney.length()-7, tempMoney.length()-6))));
        vo.setPayParm8(NumberToCapitalizedUtils.CHINESE_NUM_MAP.get(Integer.valueOf(tempMoney.substring(tempMoney.length()-8, tempMoney.length()-7))));

        if(vo.getPayParm8().equals(Constant.CHINESE_ZERO)){
            vo.setPayParm8("");
        }
        if(StringUtils.isBlank(vo.getPayParm8()) && vo.getPayParm7().equals(Constant.CHINESE_ZERO)){
            vo.setPayParm7("");
        }
        if(StringUtils.isAllBlank(vo.getPayParm8(), vo.getPayParm7()) && vo.getPayParm6().equals(Constant.CHINESE_ZERO)){
            vo.setPayParm6("");
        }

        vo.setCreateDate(DateHelper.date2String(entity.getCreateDate(), DateHelper.DateFormatType.YearMonthDay_Chines));

        return vo;
    }


    
}
