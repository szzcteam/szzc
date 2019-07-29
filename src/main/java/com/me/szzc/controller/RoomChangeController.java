package com.me.szzc.controller;

import com.me.szzc.pojo.vo.ResultVo;
import com.me.szzc.service.RoomChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * 换房管理controller
 * Created by bbfang on 2019/7/27.
 */
@Controller
public class RoomChangeController {
    @Autowired
    private RoomChangeService roomChangeService;

    @RequestMapping("/excel")
    @ResponseBody
    public ModelAndView roleList(@RequestParam(value = "file", required = false) MultipartFile file) {
        ModelAndView modelAndView = new ModelAndView();
        ResultVo resultVo = roomChangeService.importExcle(file);
        if (resultVo.getCode() == 0) {
            modelAndView.addObject("statusCode", 200);
            modelAndView.addObject("message", "修改成功");
            modelAndView.addObject("callbackType", "closeCurrent");
        } else {
            modelAndView.addObject("statusCode", resultVo.getCode());
            modelAndView.addObject("message", resultVo.getMsg());
            modelAndView.addObject("callbackType", "closeCurrent");
        }

        return modelAndView;
    }

}
