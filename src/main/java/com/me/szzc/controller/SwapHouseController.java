package com.me.szzc.controller;

import com.me.szzc.aspect.SysLog;
import com.me.szzc.constant.SystemArgsConstant;
import com.me.szzc.enums.ModuleConstont;
import com.me.szzc.enums.ProtocolEnum;
import com.me.szzc.pojo.entity.SwapHouse;
import com.me.szzc.service.SwapHouseService;
import com.me.szzc.utils.DateHelper;
import com.me.szzc.utils.ObjTransMapUtils;
import com.me.szzc.utils.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
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
    public ModelAndView addSwapHouse(SwapHouse swapHouse, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
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
    public ModelAndView updateSwapHouse(SwapHouse swapHouse, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        //修改人
        Long userId = getAdminSession(request).getFid();
        swapHouse.setModifiedUserId(userId);
        this.swapHouseService.updateSwapHouse(swapHouse);
        modelAndView.addObject("statusCode", 200);
        modelAndView.addObject("message", "修改成功");
        modelAndView.addObject("callbackType", "closeCurrent");
        return modelAndView;
    }

    @RequestMapping("/ssadmin/deleteSwapHouse")
    @SysLog(code = ModuleConstont.PROTOCOL_OPERATION, method = "删除产权调换协议")
    public ModelAndView deleteSwapHouse(String houseOwner, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        SwapHouse swapHouse = this.swapHouseService.selectSwapHouseByHouseOwner(houseOwner);
        if(swapHouse == null) {
            modelAndView.addObject("statusCode", 300);
            modelAndView.addObject("message", "参数错误，查询不到产权调换协议");
            return modelAndView;
        }

        if(swapHouse.getDeleted()) {
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
    public ModelAndView selectSwapHouseByHouseOwner(String houseOwner, String url) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(url);
        SwapHouse swapHouse = this.swapHouseService.selectSwapHouseByHouseOwner(houseOwner);
        if(swapHouse != null) {
            modelAndView.addObject("swapHouse", swapHouse);
        }
        modelAndView.addObject("statusCode", 200);
        modelAndView.addObject("message", "查询成功");
        return modelAndView;
    }

    @RequestMapping("/ssadmin/exportSwapHouse")
    public ModelAndView exportSwapHouse(@RequestParam String houseOwner, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        //SwapHouse swapHouse = this.swapHouseService.selectByPrimaryKey(id);
        SwapHouse swapHouse = this.swapHouseService.selectSwapHouseByHouseOwner(houseOwner);
        Map<String, String> map = ObjTransMapUtils.obj2Map(swapHouse);
        //协议书名称
        String protocol = ProtocolEnum.HOUSING_PROPERTY_ECHANGE_AGREEMENT.getCode();
        //yyyyMMddHHmmssSSS的时间格式
        String date = DateHelper.date2String(new Date(), DateHelper.DateFormatType.YearMonthDay_HourMinuteSecond_MESC);
        WordUtils.exportMillCertificateWord
                (request, response, map, protocol + "_" + houseOwner + "_" + date, "HousingPropertyEchangeAgreement.ftl");
        modelAndView.addObject("statusCode", 200);
        modelAndView.addObject("message", "导出成功");
        modelAndView.addObject("callbackType", "closeCurrent");
        return modelAndView;
    }
}
