package com.me.szzc.controller;

import com.me.szzc.pojo.entity.SwapHouse;
import com.me.szzc.service.SwapHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 产权调换协议
 *
 * @author luwei
 * @date 2019-02-16
 */
@Controller
@RequestMapping("/swapHouse")
public class SwapHouseController extends BaseController {

    @RequestMapping("/add")
    public ModelAndView Index(@RequestBody SwapHouse request) {
        ModelAndView modelAndView = new ModelAndView();
        this.swapHouseService.add(request);
        modelAndView.addObject("statusCode",200);
        modelAndView.addObject("message","新增成功");
        modelAndView.addObject("callbackType","closeCurrent");
        return modelAndView;
    }
}
