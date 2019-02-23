package com.me.szzc.controller;

import com.me.szzc.constant.SystemArgsConstant;
import com.me.szzc.enums.ProtocolEnum;
import com.me.szzc.pojo.entity.RmbRecompense;
import com.me.szzc.pojo.entity.SwapHouse;
import com.me.szzc.utils.DateHelper;
import com.me.szzc.utils.ObjTransMapUtils;
import com.me.szzc.utils.WordUtils;
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
        this.rmbRecompenseService.add(rmbRecompense);
        modelAndView.addObject("statusCode",200);
        modelAndView.addObject("message","新增成功");
        modelAndView.addObject("callbackType","closeCurrent");
        return modelAndView;
    }

    @RequestMapping("ssadmin/RmbRecompense/detele")
    public ModelAndView dateleRmbRecompense (String houseOwner, HttpServletRequest request)throws Exception{
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
        this.rmbRecompenseService.detele(rmbRecompense);
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
        this.rmbRecompenseService.update(rmbRecompense);
        modelAndView.addObject("statusCode",200);
        modelAndView.addObject("message","修改成功");
        modelAndView.addObject("callbackType","closeCurrent");
        return modelAndView;
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
        //对象转map
        Map<String, String> map = ObjTransMapUtils.obj2Map(rmbRecompense);
        //协议书名称(枚举保存维护)
        String protocol = ProtocolEnum.RMB_RECOMPENSE_AGREEMENT.getCode();
        //yyyyMMddHHmmssSSS的时间格式
        String date = DateHelper.date2String(new Date(), DateHelper.DateFormatType.YearMonthDay_HourMinuteSecond_MESC);
        //文件导出
        WordUtils.exportMillCertificateWord
                (request, response, map, protocol + "_" + houseOwner + "_" + date, "RmbRecompenseAgreement.ftl");
        modelAndView.addObject("statusCode", 200);
        modelAndView.addObject("message", "导出成功");
        modelAndView.addObject("callbackType", "closeCurrent");
        return modelAndView;
    }
}
