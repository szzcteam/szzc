package com.me.szzc.controller;

import com.me.szzc.pojo.entity.RoomChange;
import com.me.szzc.pojo.vo.ResultVo;
import com.me.szzc.service.RoomChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 换房管理controller
 * Created by bbfang on 2019/7/27.
 */
@Controller
@RequestMapping("/roomChange")
public class RoomChangeController {
    @Autowired
    private RoomChangeService roomChangeService;

    /**
     * @param file excle文件
     * @return
     */
    @RequestMapping("/importExcel")
    @ResponseBody
    public ModelAndView importExcel(@RequestParam(value = "file", required = false) MultipartFile file) {
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

    /**
     * @param pageSize 每页条数
     * @param pageNum  查询页数
     * @param name     片区项目名称
     * @param district 所属片区
     * @return
     */
    @RequestMapping("/queryPage")
    @ResponseBody
    public ModelAndView queryPage(Integer pageSize, Integer pageNum, String name, String district) {
        ModelAndView modelAndView = new ModelAndView();
        Map<String, Object> map = roomChangeService.queryPage(pageSize, pageNum, name, district);
        modelAndView.addObject("statusCode", 200);
        modelAndView.addObject("message", "查询成功");
        modelAndView.addObject("roomChanges", map);
        return modelAndView;
    }

    @RequestMapping("/deleteRoomChangeById")
    @ResponseBody
    public ModelAndView deleteRoomChange(Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        Integer integer = roomChangeService.deleteRoomChange(id);
        if (integer > 0) {
            modelAndView.addObject("statusCode", 200);
            modelAndView.addObject("message", "删除成功");
        } else {
            modelAndView.addObject("statusCode", 300);
            modelAndView.addObject("message", "删除失败");
        }
        return modelAndView;
    }

    @RequestMapping("/getRoomChangeById")
    @ResponseBody
    public ModelAndView getRoomChangeById(Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        RoomChange roomChange = roomChangeService.getRoomChangeById(id);
        if(roomChange != null) {
            modelAndView.addObject("roomChange", roomChange);
        }
        modelAndView.addObject("statusCode", 200);
        modelAndView.addObject("message", "查询成功");
        return modelAndView;
    }

}
