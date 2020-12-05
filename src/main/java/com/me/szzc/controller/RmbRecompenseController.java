package com.me.szzc.controller;

import com.alibaba.fastjson.JSONObject;
import com.me.szzc.aspect.SysLog;
import com.me.szzc.constant.SystemArgsConstant;
import com.me.szzc.enums.CompensateTypeEnum;
import com.me.szzc.enums.GovernmentEnum;
import com.me.szzc.enums.ModuleConstont;
import com.me.szzc.enums.ProtocolEnum;
import com.me.szzc.pojo.entity.*;
import com.me.szzc.pojo.vo.RmbRecompenseVO;
import com.me.szzc.pojo.vo.SettleAccountsVO;
import com.me.szzc.utils.DateHelper;
import com.me.szzc.utils.ObjTransMapUtils;
import com.me.szzc.utils.WordUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 货币补偿协议
 */
@Controller
public class RmbRecompenseController extends BaseController {

    @RequestMapping("ssadmin/RmbRecompense/add")
    @SysLog(code = ModuleConstont.PROTOCOL_OPERATION, method = "新增货币补偿协议")
    public ModelAndView saveRmbRecompense (RmbRecompense rmbRecompense, Adjudication adjudication,
                                           TradeHouse tradeHouse, HttpServletRequest request)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");

        //判断结算单是否存在
        SettleAccounts settleAccounts = settleAccountsService.getByHouseOwnerAddr(rmbRecompense.getHouseOwner(), rmbRecompense.getAddress());
        if (settleAccounts == null) {
            modelAndView.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
            modelAndView.addObject(MESSAGE_KEY, "根据被征收人姓名、地址查找结算单失败，请按照流程，先添加被征收人、地址的结算单。");
            return modelAndView;
        }

        if(!settleAccounts.getCompensateType().equals(CompensateTypeEnum.RMB_TYPE.getCode())){
            modelAndView.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
            modelAndView.addObject(MESSAGE_KEY, "不能增加货币补偿协议，结算单中选择的类型是：产权调换协议，请前后保持一致。");
            return modelAndView;
        }

        //判断是否存在
        RmbRecompense entity = rmbRecompenseService.getByHouseOwnerAddr(rmbRecompense.getHouseOwner(), rmbRecompense.getAddress());
        if (entity != null) {
            modelAndView.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
            modelAndView.addObject(MESSAGE_KEY, getExistsOnlyMsg(rmbRecompense.getHouseOwner(), rmbRecompense.getAddress()));
            return modelAndView;
        }

        if(adjudication != null && StringUtils.isNoneBlank(adjudication.getAdjuLetter())){
            rmbRecompense.setAdjudicationJson(JSONObject.toJSONString(adjudication));
        }

        if (rmbRecompense.getIsTradeHouse() != null && rmbRecompense.getIsTradeHouse() && tradeHouse != null
                && (StringUtils.isNoneBlank(tradeHouse.getCoveredArea()) ||
                StringUtils.isNoneBlank(tradeHouse.getBuySerialNumber()) ||
                StringUtils.isNoneBlank(tradeHouse.getTransferRmb()) ||
                StringUtils.isNoneBlank(tradeHouse.getDifference()) ||
                StringUtils.isNoneBlank(tradeHouse.getUpperDifference()))) {
            rmbRecompense.setTradeHouseJson(JSONObject.toJSONString(tradeHouse));
        }

        //创建人
        Long userId = getAdminSession(request).getFid();
        rmbRecompense.setCreateUserId(userId);
        rmbRecompense.setModifiedUserId(userId);
        //征收公司
        rmbRecompense.setCompany(rmbRecompense.getAgencyCompany());

        String str = "true"; //rmbRecompenseTerm(rmbRecompense);
        if (str.equals("true")) {
            this.rmbRecompenseService.add(rmbRecompense);
            modelAndView.addObject(STATUS_CODE_KEY, SUCCESS_CODE_NUM);
            modelAndView.addObject(MESSAGE_KEY, "新增成功");
            modelAndView.addObject("callbackType", "closeCurrent");
            return modelAndView;
        } else {
            modelAndView.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
            modelAndView.addObject(MESSAGE_KEY, "数据校验错误" + str);
            modelAndView.addObject("callbackType", "closeCurrent");
            return modelAndView;
        }

    }

    @RequestMapping("ssadmin/RmbRecompense/delete")
    @SysLog(code = ModuleConstont.PROTOCOL_OPERATION, method = "删除货币补偿协议")
    public ModelAndView deleteRmbRecompense (String idMore, HttpServletRequest request)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        //获取条件
        String[] idArr = idMore.split(",");
        Long id = Long.valueOf(idArr[1]);
        RmbRecompense rmbRecompense =  this.rmbRecompenseService.getById(id);
        if(rmbRecompense ==  null){
            modelAndView.addObject(STATUS_CODE_KEY,ERROR_CODE_NUM);
            modelAndView.addObject(MESSAGE_KEY,"查询不到货币补偿协议");
            return modelAndView;
        }
        if(rmbRecompense.getDeleted()) {
            modelAndView.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
            modelAndView.addObject(MESSAGE_KEY, "数据已删除，请核查后再操作");
            return modelAndView;
        }
        //修改人
        Long userId = getAdminSession(request).getFid();
        rmbRecompense.setModifiedUserId(userId);
        this.rmbRecompenseService.delete(rmbRecompense);
        modelAndView.addObject(STATUS_CODE_KEY,SUCCESS_CODE_NUM);
        modelAndView.addObject(MESSAGE_KEY,"删除成功");
        return modelAndView;
    }

    @RequestMapping("ssadmin/RmbRecompense/update")
    @SysLog(code = ModuleConstont.PROTOCOL_OPERATION, method = "修改货币补偿协议")
    public ModelAndView updateRmbRecompense (RmbRecompense rmbRecompense,Adjudication adjudication,
                                             TradeHouse tradeHouse, HttpServletRequest request)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        //防止修改后，名字、地址错乱
        SettleAccounts settleAccounts = settleAccountsService.getByHouseOwnerAddr(rmbRecompense.getHouseOwner(), rmbRecompense.getAddress());
        if (settleAccounts == null) {
            modelAndView.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
            modelAndView.addObject(MESSAGE_KEY, "根据被征收人姓名、地址查找结算单失败，请按照流程，先添加被征收人、地址的结算单。");
            return modelAndView;
        }
        //查询货币补偿是否已存在修改后协议
        RmbRecompense rmbRecompense1 = rmbRecompenseService.getByHouseOwnerAddrID(rmbRecompense.getHouseOwner(), rmbRecompense.getAddress(),rmbRecompense.getId());
        if(rmbRecompense1 != null){
            modelAndView.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
            modelAndView.addObject(MESSAGE_KEY, "根据被征收人姓名、地址检测到货币补偿协议已存在，协议必须唯一，请先删除原协议，再操作");
            return modelAndView;
        }

        //修改人
        Long userId = getAdminSession(request).getFid();
        rmbRecompense.setModifiedUserId(userId);
        if(adjudication != null && StringUtils.isNoneBlank(adjudication.getAdjuLetter())){
            rmbRecompense.setAdjudicationJson(JSONObject.toJSONString(adjudication));
        }

        if (rmbRecompense.getIsTradeHouse() != null && rmbRecompense.getIsTradeHouse() && tradeHouse != null
                && (StringUtils.isNoneBlank(tradeHouse.getCoveredArea()) ||
                StringUtils.isNoneBlank(tradeHouse.getBuySerialNumber()) ||
                StringUtils.isNoneBlank(tradeHouse.getTransferRmb()) ||
                StringUtils.isNoneBlank(tradeHouse.getDifference()) ||
                StringUtils.isNoneBlank(tradeHouse.getUpperDifference()))) {
            rmbRecompense.setTradeHouseJson(JSONObject.toJSONString(tradeHouse));
        }

        String str = "true";  //rmbRecompenseTerm(rmbRecompense);
        if(str.equals("true")){
            this.rmbRecompenseService.update(rmbRecompense);
            modelAndView.addObject(STATUS_CODE_KEY,SUCCESS_CODE_NUM);
            modelAndView.addObject(MESSAGE_KEY,"修改成功");
            modelAndView.addObject("callbackType","closeCurrent");
            return modelAndView;
        }else{
            this.rmbRecompenseService.update(rmbRecompense);
            modelAndView.addObject(STATUS_CODE_KEY,SUCCESS_CODE_NUM);
            modelAndView.addObject(MESSAGE_KEY,"数据校验失败"+str);
            modelAndView.addObject("callbackType","closeCurrent");
            return modelAndView;
        }


    }

    @RequestMapping("ssadmin/RmbRecompense/query")
    public ModelAndView queryRmbRecompense (String idMore, String url, HttpServletRequest request)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(url);
        //获取条件
        String[] idArr = idMore.split(",");
        Long id = Long.valueOf(idArr[1]);
        RmbRecompense recompense = this.rmbRecompenseService.getById(id);
        if(recompense != null) {
            //诀字信息
            if (StringUtils.isNotBlank(recompense.getAdjudicationJson())) {
                Adjudication adjudication = JSONObject.parseObject(recompense.getAdjudicationJson(), Adjudication.class);
                modelAndView.addObject("adjudication", adjudication);
            } else {
                //为了兼容历史数据而处理
                modelAndView.addObject("adjudication", Adjudication.getDefaultAdju());
            }

            //申购房屋信息
            if(StringUtils.isNoneBlank(recompense.getTradeHouseJson())){
                TradeHouse tradeHouse = JSONObject.parseObject(recompense.getTradeHouseJson(), TradeHouse.class);
                modelAndView.addObject("tradeHouse", tradeHouse);
            }


            modelAndView.addObject("rmbRecom", recompense);

            Area area = areaService.getById(recompense.getAreaId());
            //电二南侧项目独立
            if (area != null && (area.getProjectCode().equals(GovernmentEnum.DZRGS.getCode()) || area.getProjectCode().equals(GovernmentEnum.JYQC.getCode()))) {
                modelAndView.setViewName(url + "_drnc");
            }
        }

        //获取用户管理的片区
        Long userId = getAdminSession(request).getFid();
        List<Area> areaList = getUserEnableArea(userId);
        modelAndView.addObject("areaList", areaList);

        modelAndView.addObject(STATUS_CODE_KEY, SUCCESS_CODE_NUM);
        modelAndView.addObject(MESSAGE_KEY, "查询成功");
        return modelAndView;
    }

    @RequestMapping("/ssadmin/exportRmbRecompense")
    public ModelAndView exportRmbRecompense(@RequestParam String idMore, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        //获取条件
        String[] idArr = idMore.split(",");
        Long id = Long.valueOf(idArr[1]);
        //根据人名获取产权协议书数据
        RmbRecompense rmbRecompense = this.rmbRecompenseService.getById(id);
        if(rmbRecompense == null) {
            rmbRecompense = new RmbRecompense();
        }
        //对象转map
        Map<String, String> map = ObjTransMapUtils.obj2Map(rmbRecompense);
        //协议书名称(枚举保存维护)
        String protocol = ProtocolEnum.RMB_RECOMPENSE_AGREEMENT.getCode();
        //yyyyMMddHHmmssSSS的时间格式
        String date = DateHelper.date2String(new Date(), DateHelper.DateFormatType.YearMonthDay_HourMinuteSecond_MESC);
        String fileName = protocol + "_" + rmbRecompense.getHouseOwner() + "_" + date + ".doc";
        //文件导出
        WordUtils.exportMillCertificateWord
                (response, map, fileName, "RmbRecompenseAgreement.ftl");
        modelAndView.addObject(STATUS_CODE_KEY, SUCCESS_CODE_NUM);
        modelAndView.addObject(MESSAGE_KEY, "导出成功");
        modelAndView.addObject("callbackType", "closeCurrent");
        return modelAndView;
    }


    @RequestMapping("ssadmin/RmbRecompense/preview")
    public ModelAndView preview(Long id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        String url = "ssadmin/detailRmbRecompense";
        modelAndView.setViewName(url);
        RmbRecompense entity = this.rmbRecompenseService.getById(id);
        if(entity != null) {
            Area area = areaService.getById(entity.getAreaId());
            entity.setProjectCode(area.getProjectCode());
            RmbRecompenseVO vo = RmbRecompenseVO.parse(entity);
            modelAndView.addObject("rmbRecom", vo);
            //电二南侧项目独立
            if (area != null && area.getProjectCode().equals(GovernmentEnum.DZRGS.getCode())) {
                modelAndView.setViewName(url + "_drnc");
            }
        }
        return modelAndView;
    }

    //条件判断
    public String rmbRecompenseTerm(RmbRecompense rmbRecompense){

        if(StringUtils.isEmpty(rmbRecompense.getCardNo())){
            return "协议编号不能为空";
        }

        if(StringUtils.isEmpty(rmbRecompense.getMngOffice())){
            return "征收部门不能为空";
        }

        if(StringUtils.isEmpty(rmbRecompense.getHouseOwner())){
            return "被征收人不能为空";
        }

        if(StringUtils.isEmpty(rmbRecompense.getRecompensePlan())){
            return "补偿方案不能为空";
        }

        if(StringUtils.isEmpty(rmbRecompense.getAddress())){
            return "房屋地址不能为空";
        }

        if(StringUtils.isEmpty(rmbRecompense.getHouseOwnerNumber())){
            return "房屋所有权证号不能为空";
        }

        if(StringUtils.isEmpty(rmbRecompense.getPublicOwnerNumber())){
            return "国有土地使用权不能为空";
        }

        if(rmbRecompense.getProportion().compareTo(BigDecimal.ZERO)<=0){
            return "房屋权属份额不能小于0";
        }

        if(StringUtils.isEmpty(rmbRecompense.getUseing())){
            return "房屋用途不能为空";
        }

        //判断其他项是否为空，是赋值为0
        if(rmbRecompense.getResidenceArea()==(null)){
            rmbRecompense.setResidenceArea(BigDecimal.valueOf(0));
        }

        if(rmbRecompense.getOperateArea()==null){
            rmbRecompense.setOperateArea(BigDecimal.valueOf(0));
        }

        if(rmbRecompense.getOfficeArea()==(null)){
            rmbRecompense.setOfficeArea(BigDecimal.valueOf(0));
        }

        if(rmbRecompense.getProduceArea()==(null)){
            rmbRecompense.setProduceArea(BigDecimal.valueOf(0));
        }
        if(rmbRecompense.getOtherArea()==(null)){
            rmbRecompense.setOtherArea(BigDecimal.valueOf(0));
        }
        BigDecimal sum=rmbRecompense.getResidenceArea()
                .add(rmbRecompense.getOperateArea())
                .add(rmbRecompense.getOfficeArea())
                .add(rmbRecompense.getProduceArea())
                .add(rmbRecompense.getOtherArea());



       /* if(rmbRecompense.getNoCheckArea().compareTo(BigDecimal.ZERO)<0){
            return "未登记面积不能小于0";
        }
*/
        if(rmbRecompense.getValueCompensate().compareTo(BigDecimal.ZERO)<=0){
            return "房屋补偿金额要大于0";
        }

        if(rmbRecompense.getDecorationCompensate().compareTo(BigDecimal.ZERO)<=0){
            return "室内装修补偿要大于0";
        }

        /*if(rmbRecompense.getStructureCompensate().compareTo(BigDecimal.ZERO)<0){
            return "构建物补偿不能小于0";
        };

        if(rmbRecompense.getMovePhoneFee().compareTo(BigDecimal.ZERO)<0){
            return "电话移机费不能小于0";
        }
        if(rmbRecompense.getTvFee().compareTo(BigDecimal.ZERO)<0){
            return "有线电视迁移费不能小于0";
        }

        if(rmbRecompense.getMoveAmmeterFee().compareTo(BigDecimal.ZERO)<0){
            return "电表迁移费不能小于0";
        }

        if(rmbRecompense.getMoveWaterMeterFee().compareTo(BigDecimal.ZERO)<0){
            return "水表迁移费不能小于0";
        }
        if(rmbRecompense.getWifiFee().compareTo(BigDecimal.ZERO)<0){
            return "宽带网补费不能小于0";
        }
        if(rmbRecompense.getMoveAirConditioningFee().compareTo(BigDecimal.ZERO)<0){
            return "空调移机费补偿不能小于0";
        }
        if(rmbRecompense.getGasFee().compareTo(BigDecimal.ZERO)<0){
            return "管道拆迁费不能小于0";
        }
        if(rmbRecompense.getHotWaterCompensate().compareTo(BigDecimal.ZERO)<0){
            return "热水器拆迁补偿费不能小于0";
        }*/

        if(rmbRecompense.getMoveHouseFee().compareTo(BigDecimal.ZERO)<=0){
            return "搬家费补偿大于0";
        }




        if(rmbRecompense.getSuspendBusinessFee()==null){
            rmbRecompense.setSuspendBusinessFee(BigDecimal.valueOf(0));
        }

        if(rmbRecompense.getRmbCompensate()==null){
            rmbRecompense.setRmbCompensate(BigDecimal.valueOf(0));
        }

        if(rmbRecompense.getLifeCompensate()==null){
            rmbRecompense.setLifeCompensate(BigDecimal.valueOf(0));
        }
        if(rmbRecompense.getChangeCompensate()==null){
            rmbRecompense.setChangeCompensate(BigDecimal.valueOf(0));
        }
       if(rmbRecompense.getSubtotal()==null){
           rmbRecompense.setSubtotal(BigDecimal.valueOf(0));
       }

        if(rmbRecompense.getMoveReward().compareTo(BigDecimal.ZERO)<0){
            return "搬迁奖励不能小于0";
        }

        if(rmbRecompense.getOtherFee()==null){
           rmbRecompense.setOtherFee(BigDecimal.valueOf(0));
        }

       /* BigDecimal sum1 =  rmbRecompense.getValueCompensate()
                .add(rmbRecompense.getDecorationCompensate())
                .add(rmbRecompense.getSubtotal())
                .add(rmbRecompense.getMoveHouseFee())
                .add(rmbRecompense.getInterimFee())
                .add(rmbRecompense.getSuspendBusinessFee())
                .add(rmbRecompense.getRmbCompensate())
                .add(rmbRecompense.getLifeCompensate())
                .add(rmbRecompense.getChangeCompensate())
                .add(rmbRecompense.getMoveReward())
                .add(rmbRecompense.getOtherFee());

        if(sum1.compareTo(BigDecimal.ZERO)>0){
            if( rmbRecompense.getSumRbm().compareTo(sum1)!=0){
                return "以上1-13项统计错误";
            }
        }*/
        return "true";
    }
}
