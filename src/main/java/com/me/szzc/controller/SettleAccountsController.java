package com.me.szzc.controller;

import com.me.szzc.aspect.SysLog;
import com.me.szzc.enums.ModuleConstont;
import com.me.szzc.enums.ProtocolEnum;
import com.me.szzc.enums.SigningStatusEnum;
import com.me.szzc.pojo.entity.SettleAccounts;
import com.me.szzc.pojo.vo.SettleAccountsVO;
import com.me.szzc.utils.DateHelper;
import com.me.szzc.utils.ObjTransMapUtils;
import com.me.szzc.utils.WordUtils;
import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 房屋征收补偿资金结算单
 */

@Controller
public class SettleAccountsController extends BaseController {

    @RequestMapping("ssadmin/settleAccounts/add")
    @SysLog(code = ModuleConstont.PROTOCOL_OPERATION, method = "新增结算单")
    public ModelAndView saveSettleAccounts(SettleAccounts settleAccounts, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");

        //判断是否存在
        SettleAccounts entity = settleAccountsService.getByHouseOwnerAddr(settleAccounts.getHouseOwner(), settleAccounts.getAddress());
        if (entity != null) {
            modelAndView.addObject("statusCode", 300);
            modelAndView.addObject("message", getOwnerOnlyMsg(settleAccounts.getHouseOwner(), settleAccounts.getAddress()));
            return modelAndView;
        }

        //创建人
        Long userId = getAdminSession(request).getFid();
        settleAccounts.setCreateUserId(userId);
        settleAccounts.setModifiedUserId(userId);
        //条件判断

        BigDecimal moveHous = new BigDecimal(2000);
        if(settleAccounts.getMoveHouseFee()!=null&&settleAccounts.getMoveHouseFee().compareTo(moveHous)==0){
            settleAccounts.setCalcRmbCompensate("");
            settleAccounts.setRmbCompensate(null);
            settleAccounts.setCalcRmbMoveReward("");
            settleAccounts.setRmbMoveReward(null);
        }

        String str = "true";  //settleAccountsTerm(settleAccounts);
        if(str.equals("true")){
            this.settleAccountsService.add(settleAccounts);
            modelAndView.addObject("statusCode", 200);
            modelAndView.addObject("message", "新增成功");
            modelAndView.addObject("callbackType", "closeCurrent");
            return modelAndView;
        }else{
            modelAndView.addObject("statusCode", 300);
            modelAndView.addObject("message", "数据校验失败"+str);
            modelAndView.addObject("callbackType", "closeCurrent");
            return modelAndView;
        }


    }

    @RequestMapping("ssadmin/settleAccounts/delete")
    @SysLog(code = ModuleConstont.PROTOCOL_OPERATION, method = "删除结算单")
    public ModelAndView deleteSettleAccounts(String idMore, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        //条件判断
        String[] idArr = idMore.split(",");
        Long id = Long.valueOf(idArr[0]);
        SettleAccounts settleAccounts = this.settleAccountsService.getById(id);

        if(settleAccounts == null){
            modelAndView.addObject("statusCode",200);
            modelAndView.addObject("message","用户未签订此协议");
            return modelAndView;
        }

        if(settleAccounts.getDeleted()) {
            modelAndView.addObject("statusCode", 300);
            modelAndView.addObject("message", "数据已删除，请核查后再操作");
            return modelAndView;
        }

        //修改人
        Long userId = getAdminSession(request).getFid();
        settleAccounts.setModifiedUserId(userId);
        this.settleAccountsService.delete(settleAccounts);
        modelAndView.addObject("statusCode",200);
        modelAndView.addObject("message","删除成功");
        return modelAndView;
    }


    @RequestMapping("ssadmin/settleAccounts/signing")
    @SysLog(code = ModuleConstont.PROTOCOL_OPERATION, method = "签约")
    public ModelAndView signing(String idMore, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        //条件判断
        String[] idArr = idMore.split(",");
        Long id = Long.valueOf(idArr[0]);
        SettleAccounts settleAccounts = this.settleAccountsService.getById(id);

        if(settleAccounts == null){
            modelAndView.addObject("statusCode",200);
            modelAndView.addObject("message","用户未签订此协议");
            return modelAndView;
        }

        if(settleAccounts.getSigningStatus().intValue() == SigningStatusEnum.COMPLETE.getCode()) {
            modelAndView.addObject("statusCode",200);
            modelAndView.addObject("message", "状态为：" + SigningStatusEnum.COMPLETE.getDesc() + "，请勿重复操作");
            return modelAndView;
        }

        if(settleAccounts.getDeleted()) {
            modelAndView.addObject("statusCode", 300);
            modelAndView.addObject("message", "数据已删除，请核查后再操作");
            return modelAndView;
        }

        //修改人
        Long userId = getAdminSession(request).getFid();
        settleAccounts.setModifiedUserId(userId);
        settleAccounts.setModifiedDate(DateHelper.getTimestamp());
        settleAccounts.setSigningStatus(SigningStatusEnum.COMPLETE.getCode());
        this.settleAccountsService.changeSignStatus(settleAccounts);

        modelAndView.addObject("statusCode",200);
        modelAndView.addObject("message","签约成功");
        return modelAndView;
    }


    @RequestMapping("ssadmin/settleAccounts/update")
    @SysLog(code = ModuleConstont.PROTOCOL_OPERATION, method = "修改结算单")
    public ModelAndView updateSettleAccounts( SettleAccounts settleAccounts) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        //条件判断
        String str = "true";  //settleAccountsTerm(settleAccounts);
        if(str.equals("true")){
            this.settleAccountsService.update(settleAccounts);
            modelAndView.addObject("statusCode", 200);
            modelAndView.addObject("message","修改成功");
            modelAndView.addObject("callbackType","closeCurrent");
            return modelAndView;
        }else{
            modelAndView.addObject("statusCode", 300);
            modelAndView.addObject("message","数据校验失败"+str);
            modelAndView.addObject("callbackType","closeCurrent");
            return modelAndView;
        }
    }

    @RequestMapping("ssadmin/settleAccounts/query")
    public ModelAndView querySettleAccounts(String idMore, String url) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(url) ;
        //条件判断
        String[] idArr = idMore.split(",");
        Long id = Long.valueOf(idArr[0]);
        SettleAccounts settleAccounts = this.settleAccountsService.getById(id);
        if(settleAccounts !=null){
            modelAndView.addObject("settleAccounts", settleAccounts);
        }
        //初始化水电空调参数
        initWaterAmmerParam(modelAndView);
        modelAndView.addObject("statusCode",200);
        modelAndView.addObject("message","查询成功");
        return modelAndView;
    }

    @RequestMapping("/ssadmin/exportSettleAccounts")
    public ModelAndView exportSettleAccounts(@RequestParam String idMore, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        //获取条件
        String[] idArr = idMore.split(",");
        Long id = Long.valueOf(idArr[0]);
        //根据人名获取产权协议书数据
        SettleAccounts settleAccounts = this.settleAccountsService.getById(id);
        SettleAccountsVO vo =  SettleAccountsVO.parse(settleAccounts);
        //对象转map
        Map<String, String> map = ObjTransMapUtils.obj2Map(vo);
        //协议书名称(枚举保存维护)
        String protocol = ProtocolEnum.SETTLE_ACCOUNTS_AGREEMENT.getCode();
        //yyyyMMddHHmmssSSS的时间格式
        String date = DateHelper.date2String(new Date(), DateHelper.DateFormatType.YearMonthDay_HourMinuteSecond_MESC);
        String fileName = protocol + "_" + settleAccounts.getHouseOwner() + "_" + date + ".doc";
        //文件导出
        WordUtils.exportMillCertificateWord
                (response, map, fileName, "SettleAccountsAgreement.ftl");
        modelAndView.addObject("statusCode", 200);
        modelAndView.addObject("message", "导出成功");
        modelAndView.addObject("callbackType", "closeCurrent");
        return modelAndView;
    }


    @RequestMapping("ssadmin/settleAccounts/detail")
    @ResponseBody
    public SettleAccounts detail(String houseOwner, String address) throws Exception {
        SettleAccounts settleAccounts = this.settleAccountsService.getByHouseOwnerAddr(houseOwner, address);
        return settleAccounts;
    }


    //条件判断
    public String  settleAccountsTerm(SettleAccounts settleAccounts){

        //条件判断
        if (StringUtils.isEmpty(settleAccounts.getProjectName())) {
            return "项目名称未填";//项目名称
        }
        if (StringUtils.isEmpty(settleAccounts.getCardNo())) {
            return "编号未填";//编号
        }
        if (StringUtils.isEmpty(settleAccounts.getHouseOwner())) {
            return "被征收人未填";//被征收人
        }
        if (StringUtils.isEmpty(settleAccounts.getLessee())) {
            return "承租人未填";//承租人
        }
        if (StringUtils.isEmpty(settleAccounts.getPhone())) {
            return "电话未填";//电话
        }
        if (StringUtils.isEmpty(settleAccounts.getLesseePhone())) {
            return "承租人电话未填";//承租人电话
        }
        if (StringUtils.isEmpty(settleAccounts.getUseing())) {
            return "房屋用途未填";//房屋用途
        }
        if (settleAccounts.getCheckInArea()==null || settleAccounts.getCheckInArea().compareTo(BigDecimal.ZERO)<=0) {
            return "建筑面积未填";//建筑面积
        }
        if (settleAccounts.getInArea()==null || settleAccounts.getInArea().compareTo(BigDecimal.ZERO)<=0) {
            return "内套面积未填";//内套面积
        }
        if (settleAccounts.getAssessPrice()==null || settleAccounts.getAssessPrice().compareTo(BigDecimal.ZERO)<=0) {
            return "房屋估价未填";//房屋估价
        }
        if (StringUtils.isEmpty(settleAccounts.getAddress())) {
            return "房房地址未填";//房房地址
        }
        if(settleAccounts.getValueCompensate()==null||settleAccounts.getValueCompensate().compareTo(BigDecimal.ZERO)<=0){
            return "被征收房屋价值补偿金额未填";//被征收房屋价值补偿金额
        }
        if(StringUtils.isEmpty((settleAccounts.getCalcValueCompensate()))){
            return "房屋征收补偿计算公式未填";//房屋征收补偿计算公式
        }else{
            JexlEngine jexlEngine = new JexlBuilder().create();
            JexlExpression jexlExpression = jexlEngine.createExpression(settleAccounts.getCalcValueCompensate());
            Object evaluate = jexlExpression.evaluate(null);
            BigDecimal money = (BigDecimal)evaluate;
            money =  money.setScale(2,BigDecimal.ROUND_HALF_DOWN);
            BigDecimal money1 = settleAccounts.getValueCompensate().setScale(2,BigDecimal.ROUND_HALF_DOWN);
            if(money.compareTo(money1)!=0){
                return "房屋征收补偿公式计算价格和填写金额不一样";//计算价格和填写金额不一样
            }
        }



        if(settleAccounts.getDecorationCompensate()==null||settleAccounts.getDecorationCompensate().compareTo(BigDecimal.ZERO)<=0){
            return "装修折旧补偿金额未填";//装修折旧补偿金额
        }else{
            JexlEngine jexlEngine = new JexlBuilder().create();
            JexlExpression jexlExpression = jexlEngine.createExpression(settleAccounts.getCalcDecorationCompensate());
            Object evaluate = jexlExpression.evaluate(null);
            BigDecimal money = (BigDecimal)evaluate;
            money =  money.setScale(2,BigDecimal.ROUND_HALF_DOWN);
            BigDecimal money1 = settleAccounts.getDecorationCompensate().setScale(2,BigDecimal.ROUND_HALF_DOWN);
            if(money.compareTo(money1)!=0){
                return "装修折旧补偿金额公式计算价格和填写金额不一样";//计算价格和填写金额不一样
            }
        }


        if(StringUtils.isEmpty(settleAccounts.getCalcMoveAmmeterFee())){
            return "搬家费补偿公式为空";//搬家费为空
        }else{
            JexlEngine jexlEngine = new JexlBuilder().create();
            JexlExpression jexlExpression = jexlEngine.createExpression(settleAccounts.getCalcMoveAmmeterFee());
            Object evaluate = jexlExpression.evaluate(null);
            BigDecimal money = (BigDecimal)evaluate;
            money =  money.setScale(2,BigDecimal.ROUND_HALF_DOWN);
            BigDecimal money1 = settleAccounts.getMoveHouseFee().setScale(2,BigDecimal.ROUND_HALF_DOWN);
            if(money.compareTo(money1)!=0){
                return "搬家费补偿公式计算价格和填写金额不一样";//计算价格和填写金额不一样
            }
        }

        if(StringUtils.isEmpty(settleAccounts.getCalcInterimFee())) {
            return "临时安置计算公式为空";//临时安置计算公式
        }else {
            JexlEngine jexlEngine = new JexlBuilder().create();
            JexlExpression jexlExpression = jexlEngine.createExpression(settleAccounts.getCalcInterimFee());
            Object evaluate = jexlExpression.evaluate(null);
            BigDecimal money = (BigDecimal)evaluate;
            money =  money.setScale(2,BigDecimal.ROUND_HALF_DOWN);
            BigDecimal money1 = settleAccounts.getInterimFee().setScale(2,BigDecimal.ROUND_HALF_DOWN);
            if(money.compareTo(money1)!=0){
                return "临时安置计算价格和填写金额不一样";//临时安置计算价格和填写金额不一样
            }
        }
        if(StringUtils.isEmpty(settleAccounts.getCalcMoveWaterMeterFee())){
            return "水表计算公式为空";//水表计算公式

        }else{
            JexlEngine jexlEngine = new JexlBuilder().create();
            JexlExpression jexlExpression = jexlEngine.createExpression(settleAccounts.getCalcMoveWaterMeterFee());
            Object evaluate = jexlExpression.evaluate(null);
            BigDecimal money = (BigDecimal)evaluate;
            money =  money.setScale(2,BigDecimal.ROUND_HALF_DOWN);
            BigDecimal money1 = settleAccounts.getMoveWaterMeterFee().setScale(2,BigDecimal.ROUND_HALF_DOWN);
            if(money.compareTo(money1)!=0){
                return "水表补偿金额填写错误";//水表
            }
        }

        if(StringUtils.isEmpty(settleAccounts.getCalcMoveAmmeterFee())){
            return "电表计算公式为空";
        }else{
            JexlEngine jexlEngine = new JexlBuilder().create();
            JexlExpression jexlExpression = jexlEngine.createExpression(settleAccounts.getCalcMoveAmmeterFee());
            Object evaluate = jexlExpression.evaluate(null);
            BigDecimal money = (BigDecimal)evaluate;
            money =  money.setScale(2,BigDecimal.ROUND_HALF_DOWN);
            BigDecimal money1 = settleAccounts.getMoveAmmeterFee().setScale(2,BigDecimal.ROUND_HALF_DOWN);
            if(money.compareTo(money1)!=0){
                return "电表补偿金额填写错误";//电表
            }
        }

        if(StringUtils.isEmpty(settleAccounts.getCalcMoveAirConditioningFee())){
            return "空调迁移补偿公式为空";//空调迁移
        }else{
            JexlEngine jexlEngine = new JexlBuilder().create();
            JexlExpression jexlExpression = jexlEngine.createExpression(settleAccounts.getCalcMoveAirConditioningFee());
            Object evaluate = jexlExpression.evaluate(null);
            BigDecimal money = (BigDecimal)evaluate;
            money =  money.setScale(2,BigDecimal.ROUND_HALF_DOWN);
            BigDecimal money1 = settleAccounts.getMoveAirConditioningFee().setScale(2,BigDecimal.ROUND_HALF_DOWN);
            if(money.compareTo(money1)!=0){
                return "空调补偿金额填写错误";//空调
            }

        }

        if(StringUtils.isEmpty(settleAccounts.getCalcHotWaterCompensate())){
            return "热水器补偿公式为空";//热水器拆迁费
        }else{

            JexlEngine jexlEngine = new JexlBuilder().create();
            JexlExpression jexlExpression = jexlEngine.createExpression(settleAccounts.getCalcHotWaterCompensate());
            Object evaluate = jexlExpression.evaluate(null);
            BigDecimal money = (BigDecimal)evaluate;
            money =  money.setScale(2,BigDecimal.ROUND_HALF_DOWN);
            BigDecimal money1 = settleAccounts.getHotWaterCompensate().setScale(2,BigDecimal.ROUND_HALF_DOWN);
            if(money.compareTo(money1)!=0){
                return "热水器补偿金额填写错误";//热水器拆迁费
            }

        }

        if(StringUtils.isEmpty(settleAccounts.getCalcGasFee())){
            return "管道煤气补偿公式为空";//管道煤气拆迁
        }else{
            JexlEngine jexlEngine = new JexlBuilder().create();
            JexlExpression jexlExpression = jexlEngine.createExpression(settleAccounts.getCalcGasFee());
            Object evaluate = jexlExpression.evaluate(null);
            BigDecimal money = (BigDecimal)evaluate;
            money =  money.setScale(2,BigDecimal.ROUND_HALF_DOWN);
            BigDecimal money1 = settleAccounts.getGasFee().setScale(2,BigDecimal.ROUND_HALF_DOWN);
            if(money.compareTo(money1)!=0){
                return "管道煤气补偿金额填写错误";//管道煤气拆迁费
            }
        }

        if(StringUtils.isEmpty(settleAccounts.getCalcStructureCompensate())){
            return "构建物补偿公式为空";//构建物补偿
        }else{
            JexlEngine jexlEngine = new JexlBuilder().create();
            JexlExpression jexlExpression = jexlEngine.createExpression(settleAccounts.getCalcStructureCompensate());
            Object evaluate = jexlExpression.evaluate(null);
            BigDecimal money = (BigDecimal)evaluate;
            money =  money.setScale(2,BigDecimal.ROUND_HALF_DOWN);
            BigDecimal money1 = settleAccounts.getStructureCompensate().setScale(2,BigDecimal.ROUND_HALF_DOWN);
            if(money.compareTo(money1)!=0){
                return "构建物补偿金额填写为空";//构建物补偿
            }
        }

        if(StringUtils.isEmpty(settleAccounts.getAffiliatedOtherDesc())){
            return "其他补偿公式为空";//其他
        }else{
            JexlEngine jexlEngine = new JexlBuilder().create();
            JexlExpression jexlExpression = jexlEngine.createExpression(settleAccounts.getAffiliatedOtherDesc());
            Object evaluate = jexlExpression.evaluate(null);
            BigDecimal money = (BigDecimal)evaluate;
            money =  money.setScale(2,BigDecimal.ROUND_HALF_DOWN);
            BigDecimal money1 = settleAccounts.getAffiliatedOther().setScale(2,BigDecimal.ROUND_HALF_DOWN);
            if(money.compareTo(money1)!=0){
                return "其他补偿金额填写错误";//其他
            }
        }


        if(StringUtils.isEmpty(settleAccounts.getCalcNoCheckCompensate())){
            return "未登记房屋建筑补偿公式错误";//未登记建筑房屋补偿
        }else{
            JexlEngine jexlEngine = new JexlBuilder().create();
            JexlExpression jexlExpression = jexlEngine.createExpression(settleAccounts.getCalcNoCheckCompensate());
            Object evaluate = jexlExpression.evaluate(null);
            BigDecimal money = (BigDecimal)evaluate;
            money =  money.setScale(2,BigDecimal.ROUND_HALF_DOWN);
            BigDecimal money1 = settleAccounts.getNoCheckCompensate().setScale(2,BigDecimal.ROUND_HALF_DOWN);
            if(money.compareTo(money1)!=0){
                return "未登记房屋补偿金额填写错误";//未登记建筑房屋补偿
            }
        }

        if(StringUtils.isEmpty(settleAccounts.getCalcRmbCompensate())){
            return  "货币补偿补助公式未填";//货币补偿补助
        }else{

            JexlEngine jexlEngine = new JexlBuilder().create();
            JexlExpression jexlExpression = jexlEngine.createExpression(settleAccounts.getCalcRmbCompensate());
            Object evaluate = jexlExpression.evaluate(null);
            BigDecimal money = (BigDecimal)evaluate;
            money =  money.setScale(2,BigDecimal.ROUND_HALF_DOWN);
            BigDecimal money1 = settleAccounts.getRmbCompensate().setScale(2,BigDecimal.ROUND_HALF_DOWN);
            if(money.compareTo(money1)!=0){
                return "货币补偿补助金额填写错误";//货币补偿补助
            }
        }

        if(StringUtils.isEmpty(settleAccounts.getCalcLifeCompensate())){
            return "生活困难补助公式为空";//生活困难补助
        }else{
            JexlEngine jexlEngine = new JexlBuilder().create();
            JexlExpression jexlExpression = jexlEngine.createExpression(settleAccounts.getCalcLifeCompensate());
            Object evaluate = jexlExpression.evaluate(null);
            BigDecimal money = (BigDecimal)evaluate;
            money =  money.setScale(2,BigDecimal.ROUND_HALF_DOWN);
            BigDecimal money1 = settleAccounts.getLifeCompensate().setScale(2,BigDecimal.ROUND_HALF_DOWN);
            if(money.compareTo(money1)!=0){
                return "生活困难补助金额填写错误";//生活困难补助
            }
        }

        if(StringUtils.isEmpty(settleAccounts.getCalcChangeCompensate())){
            return "住改商补助公式为空";//住改商补助
        }else{

            JexlEngine jexlEngine = new JexlBuilder().create();
            JexlExpression jexlExpression = jexlEngine.createExpression(settleAccounts.getCalcChangeCompensate());
            Object evaluate = jexlExpression.evaluate(null);
            BigDecimal money = (BigDecimal)evaluate;
            money =  money.setScale(2,BigDecimal.ROUND_HALF_DOWN);
            BigDecimal money1 = settleAccounts.getChangeCompensate().setScale(2,BigDecimal.ROUND_HALF_DOWN);
            if(money.compareTo(money1)!=0){
                return "住改商填写金额错误";//住改商补助
            }
        }

        if(StringUtils.isEmpty(settleAccounts.getCalcBuildingAreaFee())){
            return "建筑面积补助公式为空";//建筑面积补助
        }else{
            JexlEngine jexlEngine = new JexlBuilder().create();
            JexlExpression jexlExpression = jexlEngine.createExpression(settleAccounts.getCalcBuildingAreaFee());
            Object evaluate = jexlExpression.evaluate(null);
            BigDecimal money = (BigDecimal)evaluate;
            money =  money.setScale(2,BigDecimal.ROUND_HALF_DOWN);
            BigDecimal money1 = settleAccounts.getBuildingAreaFee().setScale(2,BigDecimal.ROUND_HALF_DOWN);
            if(money.compareTo(money1)!=0){
                return "建筑面积补助金额错误";//建筑面积补助
            }
        }

        if(StringUtils.isEmpty(settleAccounts.getCalcSuspendBusinessFee())){
            return "停产停业补助公式为空";//停产停业补助
        }else{
            JexlEngine jexlEngine = new JexlBuilder().create();
            JexlExpression jexlExpression = jexlEngine.createExpression(settleAccounts.getCalcSuspendBusinessFee());
            Object evaluate = jexlExpression.evaluate(null);
            BigDecimal money = (BigDecimal)evaluate;
            money =  money.setScale(2,BigDecimal.ROUND_HALF_DOWN);
            BigDecimal money1 = settleAccounts.getSuspendBusinessFee().setScale(2,BigDecimal.ROUND_HALF_DOWN);
            if(money.compareTo(money1)!=0){
                return "停产停业补助金额为空";
            }
        }

        if(StringUtils.isEmpty(settleAccounts.getCalcNoMoveCompensate())){
            return "不可移动设备公式为空";//不可移动设备
        }else{
            JexlEngine jexlEngine = new JexlBuilder().create();
            JexlExpression jexlExpression = jexlEngine.createExpression(settleAccounts.getCalcNoMoveCompensate());
            Object evaluate = jexlExpression.evaluate(null);
            BigDecimal money = (BigDecimal)evaluate;
            money =  money.setScale(2,BigDecimal.ROUND_HALF_DOWN);
            BigDecimal money1 = settleAccounts.getNoMoveCompensate().setScale(2,BigDecimal.ROUND_HALF_DOWN);
            if(money.compareTo(money1)!=0){
                return "不可移动设备金额错误";
            }
        }

        if(StringUtils.isEmpty(settleAccounts.getCalcMoveReward())){
            return "搬迁补偿公式错误";//搬迁补偿
        }else{
            JexlEngine jexlEngine = new JexlBuilder().create();
            JexlExpression jexlExpression = jexlEngine.createExpression(settleAccounts.getCalcMoveReward());
            Object evaluate = jexlExpression.evaluate(null);
            BigDecimal money = (BigDecimal)evaluate;
            money =  money.setScale(2,BigDecimal.ROUND_HALF_DOWN);
            BigDecimal money1 = settleAccounts.getMoveReward().setScale(2,BigDecimal.ROUND_HALF_DOWN);
            if(money.compareTo(money1)!=0){
                return "搬迁补偿金额错误";//
            }
        }

        if(StringUtils.isEmpty(settleAccounts.getCalcRmbMoveReward())){
            return "货币搬迁补偿公式为空";//货币搬迁补偿
        }else{
            JexlEngine jexlEngine = new JexlBuilder().create();
            JexlExpression jexlExpression = jexlEngine.createExpression(settleAccounts.getCalcRmbMoveReward());
            Object evaluate = jexlExpression.evaluate(null);
            BigDecimal money = (BigDecimal)evaluate;
            money =  money.setScale(2,BigDecimal.ROUND_HALF_DOWN);
            BigDecimal money1 = settleAccounts.getRmbMoveReward().setScale(2,BigDecimal.ROUND_HALF_DOWN);
            if(money.compareTo(money1)!=0){
                return "货币搬迁补偿金额为空";
            }
        }

        if(StringUtils.isEmpty(settleAccounts.getCalcSmallAreaReward())){
            return "小面积搬迁奖励公式为空";//小面积搬迁奖励
        }else{
            JexlEngine jexlEngine = new JexlBuilder().create();
            JexlExpression jexlExpression = jexlEngine.createExpression(settleAccounts.getCalcSmallAreaReward());
            Object evaluate = jexlExpression.evaluate(null);
            BigDecimal money = (BigDecimal)evaluate;
            money =  money.setScale(2,BigDecimal.ROUND_HALF_DOWN);
            BigDecimal money1 = settleAccounts.getSmallAreaReward().setScale(2,BigDecimal.ROUND_HALF_DOWN);
            if(money.compareTo(money1)!=0){
                return "小面积搬迁奖励金额错误";
            }
        }

        if(StringUtils.isEmpty(settleAccounts.getCalcOther())){
            return "其他补偿公式为空";//其他
        }else{
            JexlEngine jexlEngine = new JexlBuilder().create();
            JexlExpression jexlExpression = jexlEngine.createExpression(settleAccounts.getCalcOther());
            Object evaluate = jexlExpression.evaluate(null);
            BigDecimal money = (BigDecimal)evaluate;
            money =  money.setScale(2,BigDecimal.ROUND_HALF_DOWN);
            BigDecimal money1 = settleAccounts.getOtherRmb().setScale(2,BigDecimal.ROUND_HALF_DOWN);
            if(money.compareTo(money1)!=0){
                return "其他补偿金额错误";
            }
        }

        if(StringUtils.isEmpty(settleAccounts.getCalcSumCompensate())){
            return "合计公式为空";//合计
        }else{
            JexlEngine jexlEngine = new JexlBuilder().create();
            JexlExpression jexlExpression = jexlEngine.createExpression(settleAccounts.getCalcSumCompensate());
            Object evaluate = jexlExpression.evaluate(null);
            BigDecimal money = (BigDecimal)evaluate;
            money =  money.setScale(2,BigDecimal.ROUND_HALF_DOWN);
            BigDecimal money1 = settleAccounts.getSumCompensate().setScale(2,BigDecimal.ROUND_HALF_DOWN);
            if(money.compareTo(money1)!=0){
                return "合计补偿金额错误";
            }
        }

        if(settleAccounts.getChangeArea().compareTo(BigDecimal.ZERO)<=0){
            return "建筑面积填写错误";//建筑面积
        }

        if(settleAccounts.getCalcm().compareTo(BigDecimal.ZERO)<=0){
            return "参与计算建筑面积错误";//参与计算建筑面积
        }

        if(settleAccounts.getPrice().compareTo(BigDecimal.ZERO)<=0){
            return "参与置换差价填写错误";//参与置换差价
        }


        if(StringUtils.isEmpty(settleAccounts.getCalcChangeArea())) {
            return "生活困难补助公式为空";

        }else{
            JexlEngine jexlEngine = new JexlBuilder().create();
            JexlExpression jexlExpression = jexlEngine.createExpression(settleAccounts.getCalcChangeArea());
            Object evaluate = jexlExpression.evaluate(null);
            BigDecimal money = (BigDecimal)evaluate;
            money =  money.setScale(2,BigDecimal.ROUND_HALF_DOWN);
            BigDecimal money1 = settleAccounts.getHouseMoney().setScale(2,BigDecimal.ROUND_HALF_DOWN);
            if(money.compareTo(money1)!=0){
                return "生活困难补助金额错误";//生活困难补助
            }
        }

        if(StringUtils.isEmpty(settleAccounts.getCalcDeduction())){
            return "抵扣安置房款公式为空";//抵扣安置房款
        }else{
            JexlEngine jexlEngine = new JexlBuilder().create();
            JexlExpression jexlExpression = jexlEngine.createExpression(settleAccounts.getCalcDeduction());
            Object evaluate = jexlExpression.evaluate(null);
            BigDecimal money = (BigDecimal)evaluate;
            money =  money.setScale(2,BigDecimal.ROUND_HALF_DOWN);
            BigDecimal money1 = settleAccounts.getDeduction().setScale(2,BigDecimal.ROUND_HALF_DOWN);
            if(money.compareTo(money1)!=0){
                return "抵扣安置房款金额错误";
            }
        }

        if(settleAccounts.getPayTotal().compareTo(BigDecimal.ZERO)==0){
            if(settleAccounts.getReceiveTotal().compareTo(BigDecimal.ZERO)<=0){
                return "应收金额填写错误";
            }
            if(StringUtils.isEmpty(settleAccounts.getReceiveMoney())){
                return "应收大写金额不能为空";//应收不能为空
            }
        }

        if(settleAccounts.getReceiveTotal().compareTo(BigDecimal.ZERO)==0){
            if(settleAccounts.getPayTotal().compareTo(BigDecimal.ZERO)<=0){
                return "应支付金额填写错误";
            }
            if(StringUtils.isEmpty(settleAccounts.getPayMoney())){
                return "应支付大写金额不能为空";//应支付不能为空
            }
        }
        return "true";
    }

}
