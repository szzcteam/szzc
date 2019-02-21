package com.me.szzc.controller;

import com.me.szzc.pojo.entity.SettleAccounts;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 房屋征收补偿资金结算单
 */

@Controller
public class SettleAccountsController extends BaseController {


    @RequestMapping("ssadmin/settleAccounts/add")
    public ModelAndView saveSettleAccounts(SettleAccounts settleAccounts) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/settleAccounts");
        //条件判断
        this.settleAccountsService.add(settleAccounts);
        modelAndView.addObject("statusCode",200);
        modelAndView.addObject("message","新增成功");
        modelAndView.addObject("callbackType","closeCurrent");
        return modelAndView;
    }

    @RequestMapping("ssadmin/settleAccounts/detele")
    public ModelAndView deteleSettleAccounts(@RequestBody SettleAccounts settleAccounts) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/settleAccounts");
        //条件判断

        this.settleAccountsService.detele(settleAccounts);
        modelAndView.addObject("statusCode",200);
        modelAndView.addObject("message","删除");
        modelAndView.addObject("callbackType","closeCurrent");
        return modelAndView;
    }

    @RequestMapping("ssadmin/settleAccounts/update")
    public ModelAndView updateSettleAccounts(@RequestBody SettleAccounts settleAccounts) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/settleAccounts");
        //条件判断

        this.settleAccountsService.update(settleAccounts);
        modelAndView.addObject("statusCode",200);
        modelAndView.addObject("message","修改");
        modelAndView.addObject("callbackType","closeCurrent");
        return modelAndView;
    }

    @RequestMapping("ssadmin/settleAccounts/query")
    public ModelAndView querySettleAccounts(@RequestBody SettleAccounts settleAccounts) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/settleAccounts");
        //条件判断

        this.settleAccountsService.query(settleAccounts);
        modelAndView.addObject("statusCode",200);
        modelAndView.addObject("message","查询");
        modelAndView.addObject("callbackType","closeCurrent");
        return modelAndView;
    }


}
