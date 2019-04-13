package com.me.szzc.controller;

import com.me.szzc.constant.SystemArgsConstant;
import com.me.szzc.enums.ProtocolEnum;
import com.me.szzc.pojo.entity.RmbRecompense;
import com.me.szzc.pojo.entity.SwapHouse;
import com.me.szzc.utils.DateHelper;
import com.me.szzc.utils.ObjTransMapUtils;
import com.me.szzc.utils.WordUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 货币补偿协议
 */
@Controller
public class RmbRecompenseController extends BaseController {

    @RequestMapping("ssadmin/RmbRecompense/add")
    public ModelAndView saveRmbRecompense ( RmbRecompense rmbRecompense, HttpServletRequest request)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        //创建人
        Long userId = getAdminSession(request).getFid();
        rmbRecompense.setCreateUserId(userId);
        rmbRecompense.setModifiedUserId(userId);
        //征收公司
        rmbRecompense.setCompany(constantMap.getValue(SystemArgsConstant.COMPANY));

        String str = rmbRecompenseTerm(rmbRecompense);
        if(str.equals("true")){
            this.rmbRecompenseService.add(rmbRecompense);
            modelAndView.addObject("statusCode",200);
            modelAndView.addObject("message","新增成功");
            modelAndView.addObject("callbackType","closeCurrent");
            return modelAndView;
        }else{
            this.rmbRecompenseService.add(rmbRecompense);
            modelAndView.addObject("statusCode",300);
            modelAndView.addObject("message","数据校验错误"+str);
            modelAndView.addObject("callbackType","closeCurrent");
            return modelAndView;
        }

    }

    @RequestMapping("ssadmin/RmbRecompense/delete")
    public ModelAndView deleteRmbRecompense (String houseOwner, HttpServletRequest request)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        RmbRecompense rmbRecompense =  this.rmbRecompenseService.selectByHouseOwner(houseOwner);
        if(rmbRecompense ==  null){
            modelAndView.addObject("statusCode",300);
            modelAndView.addObject("message","用户不存在此协议");
            return modelAndView;
        }
        if(rmbRecompense.getDeleted()) {
            modelAndView.addObject("statusCode", 300);
            modelAndView.addObject("message", "数据已删除，请核查后再操作");
            return modelAndView;
        }
        //修改人
        Long userId = getAdminSession(request).getFid();
        rmbRecompense.setModifiedUserId(userId);
        this.rmbRecompenseService.delete(rmbRecompense);
        modelAndView.addObject("statusCode",200);
        modelAndView.addObject("message","删除成功");
        return modelAndView;
    }

    @RequestMapping("ssadmin/RmbRecompense/update")
    public ModelAndView updateRmbRecompense (RmbRecompense rmbRecompense, HttpServletRequest request)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        //修改人
        Long userId = getAdminSession(request).getFid();
        rmbRecompense.setModifiedUserId(userId);

        String str = rmbRecompenseTerm(rmbRecompense);
        if(str.equals("true")){
            this.rmbRecompenseService.update(rmbRecompense);
            modelAndView.addObject("statusCode",200);
            modelAndView.addObject("message","修改成功");
            modelAndView.addObject("callbackType","closeCurrent");
            return modelAndView;
        }else{
            this.rmbRecompenseService.update(rmbRecompense);
            modelAndView.addObject("statusCode",200);
            modelAndView.addObject("message","数据校验失败"+str);
            modelAndView.addObject("callbackType","closeCurrent");
            return modelAndView;
        }


    }

    @RequestMapping("ssadmin/RmbRecompense/query")
    public ModelAndView queryRmbRecompense (String houseOwner, String url)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(url);
        RmbRecompense recompense = this.rmbRecompenseService.selectByHouseOwner(houseOwner);
        if(recompense != null) {
            modelAndView.addObject("rmbRecom", recompense);
        }
        modelAndView.addObject("statusCode", 200);
        modelAndView.addObject("message", "查询成功");
        return modelAndView;
    }

    @RequestMapping("/ssadmin/exportRmbRecompense")
    public ModelAndView exportRmbRecompense(@RequestParam String houseOwner, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        //根据人名获取产权协议书数据
        RmbRecompense rmbRecompense = this.rmbRecompenseService.selectByHouseOwner(houseOwner);
        if(rmbRecompense == null) {
            rmbRecompense = new RmbRecompense();
        }
        //对象转map
        Map<String, String> map = ObjTransMapUtils.obj2Map(rmbRecompense);
        //协议书名称(枚举保存维护)
        String protocol = ProtocolEnum.RMB_RECOMPENSE_AGREEMENT.getCode();
        //yyyyMMddHHmmssSSS的时间格式
        String date = DateHelper.date2String(new Date(), DateHelper.DateFormatType.YearMonthDay_HourMinuteSecond_MESC);
        String fileName = protocol + "_" + houseOwner + "_" + date + ".doc";
        //文件导出
        WordUtils.exportMillCertificateWord
                (response, map, fileName, "RmbRecompenseAgreement.ftl");
        modelAndView.addObject("statusCode", 200);
        modelAndView.addObject("message", "导出成功");
        modelAndView.addObject("callbackType", "closeCurrent");
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

        if(rmbRecompense.getCheckInArea().compareTo(BigDecimal.ZERO)<=0){
            return "登记建筑面积不能为小于0";
        }else{
            if(rmbRecompense.getCheckInArea().compareTo(
                    (rmbRecompense.getResidenceArea()
                            .add(rmbRecompense.getOperateArea())
                            .add(rmbRecompense.getOfficeArea())
                            .add(rmbRecompense.getProduceArea())
                            .add(rmbRecompense.getOtherArea())
                    )
            )!=0
                    ){
                return "登记面积中某一项面积出错";
            }
        }

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




       /* if(rmbRecompense.getSuspendBusinessFee().compareTo(BigDecimal.ZERO)<=0){
            return "停产停业损失补偿大于0";
        }

        if(rmbRecompense.getRmbCompensate().compareTo(BigDecimal.ZERO)<=0){
            return "货币补偿大于0";
        }

        if(rmbRecompense.getLifeCompensate().compareTo(BigDecimal.ZERO)<=0){
            return "生活困难补助大于0";
        }
        if(rmbRecompense.getChangeCompensate().compareTo(BigDecimal.ZERO)<=0){
            return "住改商补助大于0";
        }*/

        if(rmbRecompense.getMoveReward().compareTo(BigDecimal.ZERO)<0){
            return "搬迁奖励不能小于0";
        }
/*
        if(rmbRecompense.getCloseBalcony().compareTo(BigDecimal.ZERO)<0){
            return "封闭阳台补助不能小于0";
        }

        if(rmbRecompense.getNoCheckCompensate().compareTo(BigDecimal.ZERO)<0){
            return "未登记补助不能小于0";
        }

        if(rmbRecompense.getOtherFee().compareTo(BigDecimal.ZERO)<0){
            return "其他补助不能小于0";
        }*/

        if( rmbRecompense.getSumRbm()!= ( rmbRecompense.getValueCompensate()
                .add(rmbRecompense.getDecorationCompensate())
                .add(rmbRecompense.getSubtotal())
                .add(rmbRecompense.getMoveHouseFee())
                .add(rmbRecompense.getInterimFee())
                .add(rmbRecompense.getSuspendBusinessFee())
                .add(rmbRecompense.getRmbCompensate())
                .add(rmbRecompense.getLifeCompensate())
                .add(rmbRecompense.getChangeCompensate())
                .add(rmbRecompense.getMoveReward())
                .add(rmbRecompense.getCloseBalcony())
                .add(rmbRecompense.getNoCheckCompensate())
                .add(rmbRecompense.getOtherFee())
        )
                ){
            return "以上1-13项统计错误";
        }

        return "true";
    }
}
