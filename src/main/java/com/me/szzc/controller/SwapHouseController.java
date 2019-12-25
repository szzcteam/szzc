package com.me.szzc.controller;

import com.alibaba.fastjson.JSONObject;
import com.me.szzc.aspect.SysLog;
import com.me.szzc.constant.SystemArgsConstant;
import com.me.szzc.enums.CompensateTypeEnum;
import com.me.szzc.enums.ModuleConstont;
import com.me.szzc.enums.ProtocolEnum;
import com.me.szzc.pojo.entity.*;
import com.me.szzc.pojo.vo.RmbRecompenseVO;
import com.me.szzc.pojo.vo.SwapHouseVO;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 产权调换协议
 *
 * @author luwei
 * @date 2019-02-16
 */
@Controller
public class SwapHouseController extends BaseController {

    @RequestMapping("/ssadmin/addSwapHouse")
    @SysLog(code = ModuleConstont.PROTOCOL_OPERATION, method = "新增产权调换协议")
    public ModelAndView addSwapHouse(SwapHouse swapHouse, Adjudication adjudication,  HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        //判断结算单是否存在
        SettleAccounts settleAccounts = settleAccountsService.getByHouseOwnerAddr(swapHouse.getHouseOwner(), swapHouse.getAddress());
        if (settleAccounts == null) {
            modelAndView.addObject("statusCode", 300);
            modelAndView.addObject("message", "根据被征收人姓名、地址查找结算单失败，请按照流程，先添加被征收人、地址的结算单。");
            return modelAndView;
        }

        if(!settleAccounts.getCompensateType().equals(CompensateTypeEnum.SWAP_TYPE.getCode())){
            modelAndView.addObject("statusCode", 300);
            modelAndView.addObject("message", "不能增加调换协议，结算单中选择的类型是：货币补偿协议，请前后保持一致。");
            return modelAndView;
        }

        //判断是否存在
        SwapHouse entity = swapHouseService.getByHouseOwnerAddr(swapHouse.getHouseOwner(), swapHouse.getAddress());
        if (entity != null) {
            modelAndView.addObject("statusCode", 300);
            modelAndView.addObject("message", getExistsOnlyMsg(swapHouse.getHouseOwner(), swapHouse.getAddress()));
            return modelAndView;
        }

        //将决字信息转换成json串
        if(adjudication != null && StringUtils.isNoneBlank(adjudication.getAdjuLetter())){
            swapHouse.setAdjudicationJson(JSONObject.toJSONString(adjudication));
        }

        //创建人
        Long userId = getAdminSession(request).getFid();
        swapHouse.setCreateUserId(userId);
        swapHouse.setModifiedUserId(userId);
        //征收公司
        swapHouse.setCompany(constantMap.getValue(SystemArgsConstant.COMPANY));
        this.swapHouseService.addSwapHouse(swapHouse);
        modelAndView.addObject("statusCode", 200);
        modelAndView.addObject("message", "新增成功");
        modelAndView.addObject("callbackType", "closeCurrent");
        return modelAndView;
    }

    @RequestMapping("/ssadmin/updateSwapHouse")
    @SysLog(code = ModuleConstont.PROTOCOL_OPERATION, method = "修改产权调换协议")
    public ModelAndView updateSwapHouse(SwapHouse swapHouse,  Adjudication adjudication, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        //防止修改后，名字、地址错乱
        SettleAccounts settleAccounts = settleAccountsService.getByHouseOwnerAddr(swapHouse.getHouseOwner(), swapHouse.getAddress());
        if (settleAccounts == null) {
            modelAndView.addObject("statusCode", 300);
            modelAndView.addObject("message", "根据被征收人姓名、地址查找结算单失败，请核对后再操作。");
            return modelAndView;
        }

        //修改人
        Long userId = getAdminSession(request).getFid();
        swapHouse.setModifiedUserId(userId);
        if(adjudication != null && StringUtils.isNoneBlank(adjudication.getAdjuLetter())){
            swapHouse.setAdjudicationJson(JSONObject.toJSONString(adjudication));
        }
        this.swapHouseService.updateSwapHouse(swapHouse);
        modelAndView.addObject("statusCode", 200);
        modelAndView.addObject("message", "修改成功");
        modelAndView.addObject("callbackType", "closeCurrent");
        return modelAndView;
    }

    @RequestMapping("/ssadmin/deleteSwapHouse")
    @SysLog(code = ModuleConstont.PROTOCOL_OPERATION, method = "删除产权调换协议")
    public ModelAndView deleteSwapHouse(String idMore, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        //获取条件
        String[] idArr = idMore.split(",");
        Long id = Long.valueOf(idArr[2]);
        SwapHouse swapHouse = this.swapHouseService.getById(id);
        if (swapHouse == null) {
            modelAndView.addObject("statusCode", 300);
            modelAndView.addObject("message", "查询不到产权调换协议");
            return modelAndView;
        }

        if (swapHouse.getDeleted()) {
            modelAndView.addObject("statusCode", 300);
            modelAndView.addObject("message", "数据已删除，请核查后再操作");
            return modelAndView;
        }

        //修改人
        Long userId = getAdminSession(request).getFid();
        swapHouse.setModifiedUserId(userId);
        this.swapHouseService.delete(swapHouse);
        modelAndView.addObject("statusCode", 200);
        modelAndView.addObject("message", "删除成功");
        return modelAndView;
    }


    @RequestMapping("/ssadmin/selectSwapHouseByHouseOwner")
    public ModelAndView selectSwapHouseByHouseOwner(String idMore, String url, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(url);
        //获取条件
        String[] idArr = idMore.split(",");
        Long id = Long.valueOf(idArr[2]);
        SwapHouse swapHouse = this.swapHouseService.getById(id);
        if (swapHouse != null) {
            if (StringUtils.isNotBlank(swapHouse.getAdjudicationJson())) {
                Adjudication adjudication = JSONObject.parseObject(swapHouse.getAdjudicationJson(), Adjudication.class);
                modelAndView.addObject("adjudication", adjudication);
            } else {
                //为了兼容历史数据而处理
                modelAndView.addObject("adjudication", Adjudication.getDefaultAdju());
            }
            modelAndView.addObject("swapHouse", swapHouse);
        }
        //获取用户管理的片区
        Long userId = getAdminSession(request).getFid();
        List<Area> areaList = getUserEnableArea(userId);
        modelAndView.addObject("areaList", areaList);

        modelAndView.addObject("statusCode", 200);
        modelAndView.addObject("message", "查询成功");
        return modelAndView;
    }

    @RequestMapping("/ssadmin/exportSwapHouse")
    public ModelAndView exportSwapHouse(@RequestParam String idMore, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        //获取条件
        String[] idArr = idMore.split(",");
        Long id = Long.valueOf(idArr[2]);
        //根据人名获取产权协议书数据
        SwapHouse swapHouse = this.swapHouseService.getById(id);
        if (swapHouse == null) {
            swapHouse = new SwapHouse();
        }
        //对象转map
        Map<String, String> map = ObjTransMapUtils.obj2Map(swapHouse);
        //协议书名称(枚举保存维护)
        String protocol = ProtocolEnum.HOUSING_PROPERTY_ECHANGE_AGREEMENT.getCode();
        //yyyyMMddHHmmssSSS的时间格式
        String date = DateHelper.date2String(new Date(), DateHelper.DateFormatType.YearMonthDay_HourMinuteSecond_MESC);
        String fileName = protocol + "_" + swapHouse.getHouseOwner() + "_" + date + ".doc";
        //文件导出
        WordUtils.exportMillCertificateWord
                (response, map, fileName, "HousingPropertyEchangeAgreement.ftl");

        modelAndView.addObject("statusCode", 200);
        modelAndView.addObject("message", "导出成功");
        return modelAndView;
    }

    @RequestMapping("ssadmin/swapHouse/preview")
    public ModelAndView preview(Long id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/detailSwapHouse");
        SwapHouse entity = this.swapHouseService.getById(id);
        if (entity != null) {
            Area area = areaService.getById(entity.getAreaId());
            entity.setProjectCode(area.getProjectCode());
            SwapHouseVO vo = SwapHouseVO.parse(entity);
            modelAndView.addObject("swapHouse", vo);
        }
        return modelAndView;
    }
}
