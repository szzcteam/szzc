package com.me.szzc.controller;

import com.me.szzc.enums.ProtocolEnum;
import com.me.szzc.pojo.entity.Notice;
import com.me.szzc.pojo.entity.SwapHouse;
import com.me.szzc.utils.DateHelper;
import com.me.szzc.utils.ObjTransMapUtils;
import com.me.szzc.utils.WordUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 房屋征收补偿资金结算通知单
 */
@Controller
public class NoticeController extends BaseController {

    @RequestMapping("/ssadmin/notice/add")
    public ModelAndView saveNotice(Notice notice) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/notice") ;

        this.noticeService.add(notice);
        modelAndView.addObject("statusCode",200);
        modelAndView.addObject("message","新增成功");
        modelAndView.addObject("callbackType","closeCurrent");
        return modelAndView;
    }

    @RequestMapping("/ssadmin/notice/detele")
    public ModelAndView deteleNotice(Notice notice)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/notice") ;
        Boolean notices = this.noticeService.queryName(notice.getHouseOwner());
        if(notices){
            this.noticeService.detele(notice);
            modelAndView.addObject("statusCode",200);
            modelAndView.addObject("message","删除成功");
            modelAndView.addObject("callbackType","closeCurrent");

        }else{
            modelAndView.addObject("statusCode",200);
            modelAndView.addObject("message","用户未签订此协议");
            modelAndView.addObject("callbackType","closeCurrent");
        }
        return modelAndView;
    }

    @RequestMapping("ssadmin/notice/update")
    public ModelAndView updateNotice(Notice notice)throws Exception{
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
    public ModelAndView queryNotice(Notice notice)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/notice") ;
        //条件判断

        Notice notice1 = this.noticeService.selectByHouseOwner(notice.getHouseOwner());
        if(notice1 != null) {
            modelAndView.addObject("swapHouse", notice1);
        }
        modelAndView.addObject("statusCode",200);
        modelAndView.addObject("message","查询成功");
        return modelAndView;
    }

    @RequestMapping("/ssadmin/exportNotice")
    public ModelAndView exportNotice(@RequestParam String houseOwner, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        //根据人名获取产权协议书数据
        Notice notice = this.noticeService.selectByHouseOwner(houseOwner);
        //对象转map
        Map<String, String> map = ObjTransMapUtils.obj2Map(notice);
        //协议书名称(枚举保存维护)
        String protocol = ProtocolEnum.NOTICE_AGREEMENT.getCode();
        //yyyyMMddHHmmssSSS的时间格式
        String date = DateHelper.date2String(new Date(), DateHelper.DateFormatType.YearMonthDay_HourMinuteSecond_MESC);
        //文件导出
        WordUtils.exportMillCertificateWord
                (request, response, map, protocol + "_" + houseOwner + "_" + date, "NoticeAgreement.ftl");
        modelAndView.addObject("statusCode", 200);
        modelAndView.addObject("message", "导出成功");
        modelAndView.addObject("callbackType", "closeCurrent");
        return modelAndView;
    }
}
