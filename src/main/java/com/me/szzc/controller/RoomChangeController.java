package com.me.szzc.controller;

import com.alibaba.fastjson.JSONObject;
import com.me.szzc.pojo.entity.RoomChange;
import com.me.szzc.pojo.vo.ResultVo;
import com.me.szzc.service.RoomChangeService;
import com.me.szzc.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 换房管理controller
 * Created by bbfang on 2019/7/27.
 */
@Controller
@RequestMapping("/ssadmin/roomChange")
public class RoomChangeController {
    @Autowired
    private RoomChangeService roomChangeService;

    /**
     * @param file excle文件
     * @return
     */
    @RequestMapping("/importExcel")
    @ResponseBody
    public JSONObject importExcel(@RequestParam(value = "file", required = false) MultipartFile file) {
        JSONObject jsonObject = new JSONObject();
        ResultVo resultVo = roomChangeService.importExcle(file);
        if (resultVo.getCode() == 0) {
            jsonObject.put("statusCode", 200);
        } else {
            jsonObject.put("statusCode", resultVo.getCode());
            jsonObject.put("message", resultVo.getMsg());
        }

        return jsonObject;
    }

    /**
     * @param keywords     片区项目名称
     * @param district 所属片区
     * @return
     */
    @RequestMapping("/queryPage")
    public ModelAndView queryPage(HttpServletRequest request, String keywords, String district) {
        ModelAndView view = new ModelAndView();

        if(keywords != null && keywords.trim().length() >0){
            view.addObject("keywords", keywords);
        }
        int currentPage = 1;
        if (request.getParameter("pageNum") != null) {
            currentPage = Integer.parseInt(request.getParameter("pageNum"));
        }
        view.setViewName("ssadmin/roomChangeList");
        Map<String, Object> map = roomChangeService.queryPage(Utils.getNumPerPage(), currentPage, keywords, district);
        view.addObject("roomList", map.get("datas"));
        view.addObject("numPerPage", Utils.getNumPerPage());
        view.addObject("currentPage", currentPage);
        view.addObject("rel", "roomChange");
        view.addObject("totalCount", map.get("total"));
        return view;
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
