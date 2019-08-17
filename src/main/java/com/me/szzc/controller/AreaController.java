package com.me.szzc.controller;

import com.me.szzc.pojo.entity.Farea;
import com.me.szzc.pojo.entity.FareaRode;
import com.me.szzc.pojo.entity.Frole;
import com.me.szzc.service.AreaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AreaController extends BaseController {

    @RequestMapping("/ssadmin/queryRole")
    //初始化新增
    public ModelAndView areaRole(HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<Frole> listRole = this.roleService.areaList();
        Map<Long, String> map = new HashMap<Long, String>();
        for (int i = 0; i <listRole.size(); i++) {
            Long key = listRole.get(i).getFid();
            String nameRole = listRole.get(i).getFname();
            map.put(key,nameRole);
        }
        modelAndView.addObject("areaRole", map);
        modelAndView.addObject("statusCode",200);
        return modelAndView;
    }

    @RequestMapping("/ssadmin/saveArea")
    public ModelAndView saveArea(Farea farea ,HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        if(StringUtils.isNotEmpty(farea.getAreaName())){
            return null;
        }
        if(farea.getList().size()<=0){
            return null;
        }
        this.areaService.addArea(farea);
        LinkedList<FareaRode> listAreaRede = new LinkedList<>();
        for(int i = 0;i<farea.getList().size();i++){
            FareaRode fareaRode = new FareaRode();
            fareaRode.setPid(farea.getId());
            Long rodeId= (Long) farea.getList().get(i);
            fareaRode.setRodeId(rodeId);
            this.fareaRodeService.addfareaRode(fareaRode);
        }
        return modelAndView;
    }

    @RequestMapping("/ssadmin/queryAreaRode")
    //初始化修改
    public ModelAndView queryAreaRole(int pid , HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        List<FareaRode> listRodeId = this.fareaRodeService.queryFareaRode(pid);
        List<Frole> listRole = this.roleService.findAll();

        modelAndView.addObject("listRodeId", listRodeId);
        modelAndView.addObject("roleList", "roleList");
        return modelAndView;

    }


    @RequestMapping("/ssadmin/updateAreaRode")
    //初始化修改
    public ModelAndView updateAreaRole(Farea farea  , HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        if(StringUtils.isNotEmpty(farea.getAreaName())){
            return null;
        }
        if(farea.getList().size()<=0){
            return null;
        }
        this.areaService.addArea(farea);
        LinkedList<FareaRode> listAreaRede = new LinkedList<>();
        for(int i = 0;i<farea.getList().size();i++){
            FareaRode fareaRode = new FareaRode();
            fareaRode.setPid(farea.getId());
            Long rodeId= (Long) farea.getList().get(i);
            fareaRode.setRodeId(rodeId);
            this.fareaRodeService.addfareaRode(fareaRode);
        }
        return modelAndView;


    }



}
