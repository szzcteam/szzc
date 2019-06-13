package com.me.szzc.pojo.vo;

import com.me.szzc.pojo.entity.SettleAccounts;
import com.me.szzc.utils.BigDecimalUtil;
import lombok.Data;
import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.lang.StringUtils;

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

    private String cardNo;

    private String projectName;

    private String houseOwner;

    private String lessee;

    private String phone;

    private String lesseePhone;

    private String useing;

    private String checkInArea;

    private String inArea;

    private String assessPrice;

    private String address;

    private String calcValueCompensate;

    private String valueCompensate;

    private String valueCompensateBz;

    private String calcDecorationCompensate;

    private String decorationCompensate;

    private String decorationCompensateBz;

    private String calcMoveHouseFee;

    private String moveHouseFee;

    private String moveHouseFeeBz;

    private String calcInterimFee;

    private String interimFee;

    private String interimFeeBz;

    private String calcMoveWaterMeterFee;

    private String moveWaterMeterFee;

    private String moveWaterMeterFeeBz;

    private String calcMoveAmmeterFee;

    private String moveAmmeterFee;

    private String moveAmmeterFeeBz;

    private String calcMoveAirConditioningFee;

    private String moveAirConditioningFee;

    private String moveAirConditioningFeeBz;

    private String calcHotWaterCompensate;

    private String hotWaterCompensate;

    private String hotWaterCompensateBz;

    private String calcGasFee;

    private String gasFee;

    private String gasFeeBz;

    private String calcStructureCompensate;

    private String structureCompensate;

    private String structureCompensateBz;

    private String affiliatedOtherDesc;

    private String calcAffiliatedOther;

    private String affiliatedOther;

    private String affiliatedOtherBz;

    private String calcNoCheckCompensate;

    private String noCheckCompensate;

    private String noCheckCompensateBz;

    private String calcRmbCompensate;

    private String rmbCompensate;

    private String rmbCompensateBz;

    private String calcLifeCompensate;

    private String lifeCompensate;

    private String lifeCompensateBz;

    private String calcChangeCompensate;

    private String changeCompensate;

    private String changeCompensateBz;

    private String calcBuildingAreaFee;

    private String buildingAreaFee;

    private String buildingAreaFeeBz;

    private String calcSuspendBusinessFee;

    private String suspendBusinessFee;

    private String suspendBusinessFeeBz;

    private String calcNoMoveCompensate;

    private String noMoveCompensate;

    private String noMoveCompensateBz;

    private String calcMoveReward;

    private String moveReward;

    private String moveRewardBz;

    private String calcRmbMoveReward;

    private String rmbMoveReward;

    private String rmbMoveRewardBz;

    private String calcSmallAreaReward;

    private String smallAreaReward;

    private String smallAreaRewardBz;

    private String otherDesc;

    private String calcOther;

    private String otherRmb;

    private String otherBz;

    private String calcSumCompensate;

    private String sumCompensate;

    private String sumCompensateBz;

    private String changeArea;

    private String calcChangeArea;

    private String calcm;

    private String price;

    private String houseMoney;

    private String calcDeduction;

    private String deduction;

    private String deductionBz;

    private String calcPayTotal;

    private String payTotal;

    private String payTotalBz;

    private String receiveTotal;

    private String payMoney;

    private String receiveMoney;

    public static SettleAccountsVO parse(SettleAccounts entity) throws Exception{
        SettleAccountsVO vo = new SettleAccountsVO();
        if(entity == null) {
            return vo;
        }

        //公式计算对象
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine se = manager.getEngineByName("js");

        vo.setId(entity.getId());
        vo.setCardNo(entity.getCardNo());
        vo.setProjectName(entity.getProjectName());
        vo.setHouseOwner(entity.getHouseOwner());
        vo.setLessee(entity.getLessee());
        vo.setPhone(entity.getPhone());
        vo.setLesseePhone(entity.getLesseePhone());
        vo.setUseing(entity.getUseing());
        vo.setChangeArea(BigDecimalUtil.stripTrailingZeros(entity.getCheckInArea()));
        vo.setInArea(BigDecimalUtil.stripTrailingZeros(entity.getInArea()));
        vo.setAssessPrice(BigDecimalUtil.stripTrailingZeros(entity.getAssessPrice()));
        vo.setAddress(entity.getAddress());
        vo.setCalcValueCompensate(entity.getCalcValueCompensate());
        vo.setValueCompensate(BigDecimalUtil.stripTrailingZeros(entity.getValueCompensate()));
        vo.setValueCompensateBz(entity.getValueCompensateBz());
        vo.setCalcDecorationCompensate(entity.getCalcDecorationCompensate());
        vo.setDecorationCompensate(BigDecimalUtil.stripTrailingZeros(entity.getDecorationCompensate()));
        vo.setDecorationCompensateBz(entity.getDecorationCompensateBz());

        //搬迁补偿
        String calcMoveHouseFee = entity.getCalcMoveHouseFee();
        if (StringUtils.isNotBlank(calcMoveHouseFee)) {
            vo.setCalcMoveHouseFee(calcMoveHouseFee);
           /* if(calcMoveHouseFee.equals("1000")) {
                vo.setCalcMoveHouseFee("货币补偿");
            }else if(calcMoveHouseFee.equals("2000")) {
                vo.setCalcMoveHouseFee("产权调换");
            }*/
        }

        vo.setMoveHouseFee(BigDecimalUtil.stripTrailingZeros(entity.getMoveHouseFee()));
        vo.setMoveHouseFeeBz(entity.getMoveHouseFeeBz());

        vo.setCalcInterimFee(entity.getCalcInterimFee());
        vo.setInterimFee(BigDecimalUtil.stripTrailingZeros(entity.getInterimFee()));
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
                    txt += num1 + " 主表 ";
                }
                String num2 = arr[1].substring(0, arr[1].indexOf("*"));
                if(Integer.valueOf(num2) >0 ) {
                    txt += num2 + " 副表";
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

        vo.setCalcHotWaterCompensate(entity.getCalcHotWaterCompensate());
        vo.setHotWaterCompensate(BigDecimalUtil.stripTrailingZeros(entity.getHotWaterCompensate()));
        vo.setHotWaterCompensateBz(entity.getHotWaterCompensateBz());

        vo.setCalcGasFee(entity.getCalcGasFee());
        vo.setGasFee(BigDecimalUtil.stripTrailingZeros(entity.getGasFee()));
        vo.setGasFeeBz(entity.getGasFeeBz());

        //构建物补偿
        String calcstructurecompensate = entity.getCalcStructureCompensate();
        String structureTxt = "";
        if (StringUtils.isNotBlank(calcstructurecompensate) && !calcstructurecompensate.equals("0")) {
            //新版本，灶台+暗楼
            if (calcstructurecompensate.indexOf("+") != -1) {
                String[] overArr = calcstructurecompensate.split("\\+");
                String zt = overArr[0];
                if (zt.indexOf("0*") == -1) {
                    String[] numArr = zt.split("\\*");
                    structureTxt += "无烟灶台" + numArr[0] + "个 "+(Integer)se.eval(zt)+ "元 ";
                }

                String al = overArr[1];
                if(al.indexOf("0*") == -1) {
                    String[] numArr = al.split("\\*");
                    long alMoney = Math.round(Double.valueOf(se.eval(al).toString()));
                    structureTxt += "暗楼" + numArr[0] + ",单价" + numArr[1] + ",金额:" + alMoney + "元";
                }
            } else{
                //老版本，灶台、暗楼二选一，不方便区分，公式是怎么样，就显示怎么样
                structureTxt = calcstructurecompensate;
            }

        }
        vo.setCalcStructureCompensate(structureTxt);
        vo.setStructureCompensate(BigDecimalUtil.stripTrailingZeros(entity.getStructureCompensate()));
        vo.setStructureCompensateBz(entity.getStructureCompensateBz());

        vo.setAffiliatedOtherDesc(entity.getAffiliatedOtherDesc());
        vo.setCalcAffiliatedOther(entity.getCalcAffiliatedOther());
        vo.setAffiliatedOther(BigDecimalUtil.stripTrailingZeros(entity.getAffiliatedOther()));
        vo.setAffiliatedOtherBz(entity.getAffiliatedOtherBz());
        if(entity.getNoCheckCompensate()!=null&&entity.getNoCheckCompensate().compareTo(BigDecimal.ZERO)>0){
            vo.setCalcNoCheckCompensate(entity.getCalcNoCheckCompensate());
            vo.setNoCheckCompensate(BigDecimalUtil.stripTrailingZeros(entity.getNoCheckCompensate()));
        }else{
            vo.setCalcNoCheckCompensate("");
            vo.setNoCheckCompensate("");
        }
        /*vo.setCalcNoCheckCompensate(entity.getCalcNoCheckCompensate());
        vo.setNoCheckCompensate(BigDecimalUtil.stripTrailingZeros(entity.getNoCheckCompensate()));*/
        vo.setNoCheckCompensateBz(entity.getNoCheckCompensateBz());
        if(entity.getRmbCompensate()!=null&&entity.getRmbCompensate().compareTo(BigDecimal.ZERO)>0){
            vo.setCalcRmbCompensate(entity.getCalcRmbCompensate());
            vo.setRmbCompensate(BigDecimalUtil.stripTrailingZeros(entity.getRmbCompensate()));
        }else{
            vo.setCalcRmbCompensate("");
            vo.setRmbCompensate("");
        }
        /*vo.setCalcRmbCompensate(entity.getCalcRmbCompensate());
        vo.setRmbCompensate(BigDecimalUtil.stripTrailingZeros(entity.getRmbCompensate()));*/
        vo.setRmbCompensateBz(entity.getRmbCompensateBz());

        //生活困难补助
        String calcLifeCompensate = entity.getCalcLifeCompensate();
        if(StringUtils.isNotBlank(calcLifeCompensate)) {
            String[] arr = calcLifeCompensate.split("\\+");
            String txt = "";
            if (Integer.valueOf(arr[0]) > 0) {
                txt+=arr[0] + " 重症 ";
            }
            if (Integer.valueOf(arr[1]) > 0) {
                txt+=arr[1] + " 残疾 ";
            }
            if (Integer.valueOf(arr[2]) > 0) {
                txt+=arr[2] + " 低保 ";
            }
            vo.setCalcLifeCompensate(txt);
        }

        vo.setLifeCompensate(BigDecimalUtil.stripTrailingZeros(entity.getLifeCompensate()));
        vo.setLifeCompensateBz(entity.getLifeCompensateBz());

        if(entity.getChangeCompensate()!=null&&entity.getChangeCompensate().compareTo(BigDecimal.ZERO)>0){
            vo.setCalcChangeCompensate(entity.getCalcChangeCompensate());
            vo.setChangeCompensate(BigDecimalUtil.stripTrailingZeros(entity.getChangeCompensate()));
        }else{
            vo.setCalcChangeCompensate("");
            vo.setChangeCompensate("");
        }

        /*vo.setCalcChangeCompensate(entity.getCalcChangeCompensate());
        vo.setChangeCompensate(BigDecimalUtil.stripTrailingZeros(entity.getChangeCompensate()));*/
        vo.setChangeCompensateBz(entity.getChangeCompensateBz());

        vo.setCalcBuildingAreaFee(entity.getCalcBuildingAreaFee());
        vo.setBuildingAreaFee(BigDecimalUtil.stripTrailingZeros(entity.getBuildingAreaFee()));
        vo.setBuildingAreaFeeBz(entity.getBuildingAreaFeeBz());

        vo.setCalcSuspendBusinessFee(entity.getCalcSuspendBusinessFee());
        vo.setSuspendBusinessFee(BigDecimalUtil.stripTrailingZeros(entity.getSuspendBusinessFee()));
        vo.setSuspendBusinessFeeBz(entity.getSuspendBusinessFeeBz());


        vo.setCalcNoMoveCompensate(entity.getCalcNoMoveCompensate());
        vo.setNoMoveCompensate(BigDecimalUtil.stripTrailingZeros(entity.getNoMoveCompensate()));
        vo.setNoMoveCompensateBz(entity.getNoMoveCompensateBz());


        //搬迁奖励
        String calcMoveReward = entity.getCalcMoveReward();
        if(StringUtils.isNotBlank(calcMoveReward)) {
            vo.setCalcMoveReward(calcMoveReward);
           /* if(calcMoveReward.equals("40000")) {
                vo.setCalcMoveReward("第一奖励期 " + calcMoveReward);
            }else if(calcMoveReward.equals("15000")) {
                vo.setCalcMoveReward("第二奖励期 " + calcMoveReward);
            }else if(calcMoveReward.equals("5000")) {
                vo.setCalcMoveReward("第三奖励期 " + calcMoveReward);
            }*/
        }
        vo.setMoveReward(BigDecimalUtil.stripTrailingZeros(entity.getMoveReward()));
        vo.setMoveRewardBz(entity.getMoveRewardBz());

        if(entity.getRmbMoveReward()!=null&&entity.getRmbMoveReward().compareTo(BigDecimal.ZERO)>0){
            vo.setCalcRmbMoveReward(entity.getCalcRmbMoveReward());
            vo.setRmbMoveReward(BigDecimalUtil.stripTrailingZeros(entity.getRmbMoveReward()));
        }else{
            vo.setCalcRmbMoveReward("");
            vo.setRmbMoveReward("");
        }

        /*vo.setCalcRmbMoveReward(entity.getCalcRmbMoveReward());
        vo.setRmbMoveReward(BigDecimalUtil.stripTrailingZeros(entity.getRmbMoveReward()));*/
        vo.setRmbMoveRewardBz(entity.getRmbMoveRewardBz());

        vo.setCalcSmallAreaReward(BigDecimalUtil.stripTrailingZeros(entity.getSmallAreaReward()));
        vo.setSmallAreaReward(BigDecimalUtil.stripTrailingZeros(entity.getSmallAreaReward()));
        vo.setSmallAreaRewardBz(entity.getSmallAreaRewardBz());

        vo.setOtherDesc(entity.getOtherDesc());
        vo.setCalcOther(entity.getCalcOther());
        vo.setOtherRmb(BigDecimalUtil.stripTrailingZeros(entity.getOtherRmb()));
        vo.setOtherBz(entity.getOtherBz());

        vo.setCalcSumCompensate(entity.getCalcSumCompensate());
        vo.setSumCompensate(BigDecimalUtil.stripTrailingZeros(entity.getSumCompensate()));
        vo.setSumCompensateBz(entity.getSumCompensateBz());

        vo.setChangeArea(BigDecimalUtil.stripTrailingZeros(entity.getCheckInArea()));
        vo.setCalcChangeArea(entity.getCalcChangeArea());

        vo.setCalcm(BigDecimalUtil.stripTrailingZeros(entity.getCalcm()));
        vo.setPrice(BigDecimalUtil.stripTrailingZeros(entity.getPrice()));
        vo.setHouseMoney(BigDecimalUtil.stripTrailingZeros(entity.getHouseMoney()));

        vo.setCalcDeduction(entity.getCalcDeduction());
        vo.setDeduction(BigDecimalUtil.stripTrailingZeros(entity.getDeduction()));
        vo.setDeductionBz(entity.getDeductionBz());

        vo.setCalcPayTotal(entity.getCalcPayTotal());
        vo.setPayTotal(BigDecimalUtil.stripTrailingZeros(entity.getPayTotal()));
        vo.setPayTotalBz(entity.getPayTotalBz());
        vo.setReceiveTotal(BigDecimalUtil.stripTrailingZeros(entity.getReceiveTotal()));
        vo.setPayMoney(entity.getPayMoney());
        vo.setReceiveMoney(entity.getReceiveMoney());
        return vo;
    }


    
}
