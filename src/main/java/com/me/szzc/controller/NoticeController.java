package com.me.szzc.controller;

import com.me.szzc.pojo.entity.Notice;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * 房屋征收补偿资金结算通知单
 */
@Controller
public class NoticeController extends BaseController {

    @RequestMapping("/ssadmin/notice/add")
    public ModelAndView saveNotice(@RequestBody Notice notice) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/notice") ;
        //条件判断
       /* if(StringUtils.isEmpty(notice.getProjectName())){
            modelAndView.addObject("statusCode",300);
            modelAndView.addObject("message","请求参数不正确");
            modelAndView.addObject("callbackType","closeCurrent");
            return modelAndView;
        }*/
        this.noticeService.add(notice);
        modelAndView.addObject("statusCode",200);
        modelAndView.addObject("message","新增成功");
        modelAndView.addObject("callbackType","closeCurrent");
        return modelAndView;
    }

    @RequestMapping("/ssadmin/notice/detele")
    public ModelAndView deteleNotice(@RequestBody Notice notice)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/notice") ;
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
        modelAndView.setViewName("ssadmin/notice") ;
        //条件判断

        this.noticeService.update(notice);
        modelAndView.addObject("statusCode",200);
        modelAndView.addObject("message","修改成功");
        modelAndView.addObject("callbackType","closeCurrent");
        return modelAndView;
    }

    @RequestMapping("ssadmin/notice/query")
    public ModelAndView queryNotice(@RequestBody Notice notice)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/notice") ;
        //条件判断

        this.noticeService.query(notice);
        modelAndView.addObject("statusCode",200);
        modelAndView.addObject("message","查询成功");
        modelAndView.addObject("callbackType","closeCurrent");
        return modelAndView;
    }

}
