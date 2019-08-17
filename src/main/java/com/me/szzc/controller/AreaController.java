package com.me.szzc.controller;

import com.me.szzc.pojo.entity.Area;
import com.me.szzc.pojo.entity.AreaRole;
import com.me.szzc.pojo.entity.Frole;
import com.me.szzc.utils.StringUtils;
import com.me.szzc.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 片区管理
 */
@Controller
@RequestMapping(value = "/ssadmin/area")
public class AreaController extends BaseController {

    @RequestMapping("/queryPage")
    public ModelAndView queryPage(HttpServletRequest request, String name) {
        ModelAndView view = new ModelAndView();
        if (!com.me.szzc.utils.StringUtils.isNullOrEmpty(name)) {
            name = name.trim();
            if (name.length() > 0) {
                view.addObject("name", name);
            }
        }
        int currentPage = 1;
        if (request.getParameter("pageNum") != null) {
            currentPage = Integer.parseInt(request.getParameter("pageNum"));
        }
        view.setViewName("ssadmin/areaList");
        Map<String, Object> map = areaService.queryPage(Utils.getNumPerPage(), currentPage, name);
        view.addObject("areaList", map.get("datas"));
        view.addObject("numPerPage", Utils.getNumPerPage());
        view.addObject("currentPage", currentPage);
        view.addObject("rel", "area");
        view.addObject("totalCount", map.get("total"));
        return view;
    }



}
