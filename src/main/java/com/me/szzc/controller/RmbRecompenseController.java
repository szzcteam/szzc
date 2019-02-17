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
public class RmbRecompenseController extends BaseController {

    @RequestMapping("ssadmin/RmbRecompense/add")
    public ModelAndView saveRmbRecompense (@RequestBody RmbRecompense rmbRecompense)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/rmbRecompense");
        this.rmbRecompenseService.add(rmbRecompense);
        modelAndView.addObject("statusCode",200);
        modelAndView.addObject("message","新增成功");
        modelAndView.addObject("callbackType","closeCurrent");
        return modelAndView;
    }

    @RequestMapping("ssadmin/RmbRecompense/detele")
    public ModelAndView dateleRmbRecompense (@RequestBody RmbRecompense rmbRecompense)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/rmbRecompense");
        this.rmbRecompenseService.detele(rmbRecompense);
        modelAndView.addObject("statusCode",200);
        modelAndView.addObject("message","删除成功");
        modelAndView.addObject("callbackType","closeCurrent");
        return modelAndView;
    }

    @RequestMapping("ssadmin/RmbRecompense/update")
    public ModelAndView updateRmbRecompense (@RequestBody RmbRecompense rmbRecompense)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/rmbRecompense");
        this.rmbRecompenseService.update(rmbRecompense);
        modelAndView.addObject("statusCode",200);
        modelAndView.addObject("message","删除成功");
        modelAndView.addObject("callbackType","closeCurrent");
        return modelAndView;
    }

    @RequestMapping("ssadmin/RmbRecompense/query")
    public ModelAndView queryRmbRecompense (@RequestBody RmbRecompense rmbRecompense)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/rmbRecompense");
        this.rmbRecompenseService.query(rmbRecompense);
        modelAndView.addObject("statusCode",200);
        modelAndView.addObject("message","删除成功");
        modelAndView.addObject("callbackType","closeCurrent");
        return modelAndView;
    }
}
