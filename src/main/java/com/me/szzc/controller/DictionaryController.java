package com.me.szzc.controller;

import com.me.szzc.pojo.entity.RoomChange;
import com.me.szzc.service.DictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 字典数据controller
 * Created by bbfang on 2019/5/13.
 */
@Slf4j
@Controller
@RequestMapping("/ssadmin/dictionary")
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 获取点房状态枚举
     *
     * @return
     */
    @RequestMapping("/getChooseRoomStatusEnum")
    public ModelAndView getChooseRoomStatusEnum() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/comm/ajaxDone");
        List<Map<String, Object>> list = dictionaryService.getChooseRoomStatusEnum();
        modelAndView.addObject("statusCode", 200);
        modelAndView.addObject("message", "获取点房状态枚举成功");
        modelAndView.addObject("enumList", list);
        return modelAndView;
    }
}
