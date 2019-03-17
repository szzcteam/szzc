package com.me.szzc.controller.front;

import com.me.szzc.controller.BaseController;
import com.me.szzc.pojo.entity.SettleAccounts;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 前端模拟结算
 * @author luwei
 * @date 2019-03-18
 */
@Controller
public class FrontSettleAccountsController extends BaseController {


    @RequestMapping("front/simulate")
    public ModelAndView querySettleAccounts(String area) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("front/simulateSettleAccounts") ;
        //初始化水电空调参数
        initWaterAmmerParam(modelAndView);
        return modelAndView;
    }

}
