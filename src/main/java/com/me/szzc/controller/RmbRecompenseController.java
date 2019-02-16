package com.me.szzc.controller;

import com.me.szzc.pojo.entity.RmbRecompense;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 货币补偿协议
 */
@Controller
@RequestMapping("/RmbRecompense")
public class RmbRecompenseController extends BaseController {

    @RequestMapping("ssadmin/add")
    public ModelAndView saveRmbRecompense (@RequestBody RmbRecompense rmbRecompense)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        this.rmbRecompenseService.add(rmbRecompense);
        modelAndView.addObject("statusCode",200);
        modelAndView.addObject("message","新增成功");
        modelAndView.addObject("callbackType","closeCurrent");
        return modelAndView;
    }

    @RequestMapping("ssadmin/detele")
    public ModelAndView dateleRmbRecompense (@RequestBody RmbRecompense rmbRecompense)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        this.rmbRecompenseService.detele(rmbRecompense);
        modelAndView.addObject("statusCode",200);
        modelAndView.addObject("message","删除成功");
        modelAndView.addObject("callbackType","closeCurrent");
        return modelAndView;
    }

    @RequestMapping("ssadmin/update")
    public ModelAndView updateRmbRecompense (@RequestBody RmbRecompense rmbRecompense)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        this.rmbRecompenseService.update(rmbRecompense);
        modelAndView.addObject("statusCode",200);
        modelAndView.addObject("message","删除成功");
        modelAndView.addObject("callbackType","closeCurrent");
        return modelAndView;
    }

    @RequestMapping("ssadmin/query")
    public ModelAndView queryRmbRecompense (@RequestBody RmbRecompense rmbRecompense)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        this.rmbRecompenseService.query(rmbRecompense);
        modelAndView.addObject("statusCode",200);
        modelAndView.addObject("message","删除成功");
        modelAndView.addObject("callbackType","closeCurrent");
        return modelAndView;
    }
}
