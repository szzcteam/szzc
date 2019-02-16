package com.me.szzc.controller;

import com.me.szzc.pojo.entity.Notice;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * 房屋征收补偿协议
 */
@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseController {

    @RequestMapping("ssadmin/add")
    public ModelAndView saveNotice(@RequestBody Notice notice) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        //条件判断

        this.noticeService.add(notice);
        modelAndView.addObject("statusCode",200);
        modelAndView.addObject("message","新增成功");
        modelAndView.addObject("callbackType","closeCurrent");
        return modelAndView;
    }

    @RequestMapping("ssadmin/detele")
    public ModelAndView deteleNotice(@RequestBody Notice notice)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        //条件判断

        this.noticeService.detele(notice);
        modelAndView.addObject("statusCode",200);
        modelAndView.addObject("message","删除成功");
        modelAndView.addObject("callbackType","closeCurrent");
        return modelAndView;
    }

    @RequestMapping("ssadmin/update")
    public ModelAndView updateNotice(@RequestBody Notice notice)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        //条件判断

        this.noticeService.update(notice);
        modelAndView.addObject("statusCode",200);
        modelAndView.addObject("message","修改成功");
        modelAndView.addObject("callbackType","closeCurrent");
        return modelAndView;
    }

    @RequestMapping("ssadmin/query")
    public ModelAndView queryNotice(@RequestBody Notice notice)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        //条件判断

        this.noticeService.query(notice);
        modelAndView.addObject("statusCode",200);
        modelAndView.addObject("message","查询成功");
        modelAndView.addObject("callbackType","closeCurrent");
        return modelAndView;
    }

}
