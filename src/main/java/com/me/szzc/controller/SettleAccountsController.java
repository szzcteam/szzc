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
    public ModelAndView saveSettleAccounts(SettleAccounts settleAccounts) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/settleAccounts");
        //条件判断
        this.settleAccountsService.add(settleAccounts);
        modelAndView.addObject("statusCode", 200);
        modelAndView.addObject("message", "新增成功");
        modelAndView.addObject("callbackType", "closeCurrent");
        return modelAndView;
    }

    @RequestMapping("ssadmin/settleAccounts/detele")
    public ModelAndView deteleSettleAccounts(SettleAccounts settleAccounts) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/settleAccounts");
        //条件判断
        settleAccounts = this.settleAccountsService.selectByHouseOwner(settleAccounts.getHouseOwner());
        if(settleAccounts!=null){
            this.settleAccountsService.update(settleAccounts);
            modelAndView.addObject("statusCode", 200);
            modelAndView.addObject("message", "用户不存在");
            modelAndView.addObject("callbackType", "closeCurrent");
        }
        return modelAndView;
    }

    @RequestMapping("ssadmin/settleAccounts/update")
    public ModelAndView updateSettleAccounts(@RequestBody SettleAccounts settleAccounts) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/settleAccounts");
        //条件判断

        this.settleAccountsService.update(settleAccounts);
        modelAndView.addObject("statusCode", 200);
        modelAndView.addObject("message", "修改");
        modelAndView.addObject("callbackType", "closeCurrent");
        return modelAndView;
    }

    @RequestMapping("ssadmin/settleAccounts/query")
    public ModelAndView querySettleAccounts(@RequestBody SettleAccounts settleAccounts) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/settleAccounts");
        //条件判断

        this.settleAccountsService.query(settleAccounts);
        modelAndView.addObject("statusCode", 200);
        modelAndView.addObject("message", "查询");
        modelAndView.addObject("callbackType", "closeCurrent");
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
        //文件导出
        WordUtils.exportMillCertificateWord
                (request, response, map, protocol + "_" + houseOwner + "_" + date, "SettleAccountsAgreement.ftl");
        modelAndView.addObject("statusCode", 200);
        modelAndView.addObject("message", "导出成功");
        modelAndView.addObject("callbackType", "closeCurrent");
        return modelAndView;
    }

}
