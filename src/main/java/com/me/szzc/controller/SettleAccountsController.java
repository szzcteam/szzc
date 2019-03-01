package com.me.szzc.controller;

import com.me.szzc.enums.ProtocolEnum;
import com.me.szzc.pojo.entity.RmbRecompense;
import com.me.szzc.pojo.entity.SettleAccounts;
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
 * 房屋征收补偿资金结算单
 */

@Controller
public class SettleAccountsController extends BaseController {

    @RequestMapping("ssadmin/settleAccounts/add")
    public ModelAndView saveSettleAccounts(SettleAccounts settleAccounts, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        //创建人
        Long userId = getAdminSession(request).getFid();
        settleAccounts.setCreateUserId(userId);
        settleAccounts.setModifiedUserId(userId);
        //条件判断
        this.settleAccountsService.add(settleAccounts);
        modelAndView.addObject("statusCode", 200);
        modelAndView.addObject("message", "新增成功");
        modelAndView.addObject("callbackType", "closeCurrent");
        return modelAndView;
    }

    @RequestMapping("ssadmin/settleAccounts/delete")
    public ModelAndView deleteSettleAccounts(String houseOwner, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        //条件判断
        SettleAccounts settleAccounts = this.settleAccountsService.selectByHouseOwner(houseOwner);

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
        return modelAndView;
    }

    @RequestMapping("ssadmin/settleAccounts/update")
    public ModelAndView updateSettleAccounts( SettleAccounts settleAccounts) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        //条件判断

        this.settleAccountsService.update(settleAccounts);
        modelAndView.addObject("statusCode", 200);
        modelAndView.addObject("message","修改成功");
        modelAndView.addObject("callbackType","closeCurrent");
        return modelAndView;
    }

    @RequestMapping("ssadmin/settleAccounts/query")
    public ModelAndView querySettleAccounts(String houseOwner, String url) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(url) ;
        //条件判断
        SettleAccounts settleAccounts = this.settleAccountsService.selectByHouseOwner(houseOwner);
        if(settleAccounts !=null){
            modelAndView.addObject("settleAccounts", settleAccounts);
        }
        modelAndView.addObject("statusCode",200);
        modelAndView.addObject("message","查询成功");
        return modelAndView;
    }

    @RequestMapping("/ssadmin/exportSettleAccounts")
    public ModelAndView exportSettleAccounts(@RequestParam String houseOwner, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        //根据人名获取产权协议书数据
        SettleAccounts settleAccounts = this.settleAccountsService.selectByHouseOwner(houseOwner);
        //对象转map
        Map<String, String> map = ObjTransMapUtils.obj2Map(settleAccounts);
        //协议书名称(枚举保存维护)
        String protocol = ProtocolEnum.SETTLE_ACCOUNTS_AGREEMENT.getCode();
        //yyyyMMddHHmmssSSS的时间格式
        String date = DateHelper.date2String(new Date(), DateHelper.DateFormatType.YearMonthDay_HourMinuteSecond_MESC);
        String fileName = protocol + "_" + houseOwner + "_" + date + ".doc";
        //文件导出
        WordUtils.exportMillCertificateWord
                (response, map, fileName, "SettleAccountsAgreement.ftl");
        modelAndView.addObject("statusCode", 200);
        modelAndView.addObject("message", "导出成功");
        modelAndView.addObject("callbackType", "closeCurrent");
        return modelAndView;
    }

}
