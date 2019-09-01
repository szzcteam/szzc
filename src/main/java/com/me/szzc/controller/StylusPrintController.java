package com.me.szzc.controller;

import com.me.szzc.service.StylusPrintService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 针式打印controller
 * Created by bbfang on 2019/8/12.
 */
@Slf4j
@Controller
@RequestMapping("/ssadmin/stylusPrint")
public class StylusPrintController {
    @Autowired
    private StylusPrintService stylusPrintService;

    /**
     * 房屋征收补偿计算表打印(A4)
     *
     * @param id
     * @return
     */
    @RequestMapping("/settleAccounts")
    @ResponseBody
    public ModelAndView houseExpropriationCompensationPrint(Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        Boolean status = null;
        try {
            status = stylusPrintService.houseExpropriationCompensationPrint(id);
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.addObject("statusCode", 300);
            modelAndView.addObject("message", "打印失败" + e.toString());
            return modelAndView;
        }
        if (status) {
            modelAndView.addObject("statusCode", 200);
            modelAndView.addObject("message", "打印成功");
        } else {
            modelAndView.addObject("statusCode", 300);
            modelAndView.addObject("message", "打印失败");
        }
        return modelAndView;
    }

    @RequestMapping("/settleAccounts-print")
    @ResponseBody
    public List<Map<String, Object>> printSettleAccount(Long id) throws Exception {
        List<Map<String, Object>> dataList = stylusPrintService.settleAccountsPrint(id);
        return dataList;
    }

    @RequestMapping("/rmbRecompense-print")
    @ResponseBody
    public List<Map<String, Object>> printRmbRecompense(Long id) throws Exception {
        List<Map<String, Object>> dataList = stylusPrintService.rmbRecompensePrint(id);
        return dataList;
    }

    @RequestMapping("/houseSwap-print")
    @ResponseBody
    public List<Map<String, Object>> printHouseSwap(Long id) throws Exception {
        List<Map<String, Object>> dataList = stylusPrintService.houseSwapPrint(id);
        return dataList;
    }
}
