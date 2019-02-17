package com.me.szzc.controller;

import com.me.szzc.aspect.SysLog;
import com.me.szzc.enums.ModuleConstont;
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
    public ModelAndView addSwapHouse( SwapHouse swapHouse, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        Long userId = getAdminSession(request).getFid();
        swapHouse.setCreateUserId(userId);
        swapHouse.setModifiedUserId(userId);
        this.swapHouseService.addSwapHouse(swapHouse);
        modelAndView.addObject("statusCode", 200);
        modelAndView.addObject("message", "新增成功");
        modelAndView.addObject("callbackType", "closeCurrent");
        return modelAndView;
    }

    @RequestMapping("/ssadmin/exportSwapHouse")
    public ModelAndView exportSwapHouse(@RequestParam Long id, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        SwapHouse swapHouse = this.swapHouseService.selectByPrimaryKey(id);
        Map<String, String> map = ObjTransMapUtils.obj2Map(swapHouse);
        String s = DateHelper.date2String(new Date(), DateHelper.DateFormatType.YearMonthDay_HourMinuteSecond_MESC);
        try {
            WordUtils.exportMillCertificateWord(request,response,map,s,"HousingPropertyEchangeAgreement.ftl");
        } catch (IOException e) {
            e.printStackTrace();
        }
        modelAndView.addObject("statusCode", 200);
        modelAndView.addObject("message", "导出成功");
        modelAndView.addObject("callbackType", "closeCurrent");
        return modelAndView;
    }
}
