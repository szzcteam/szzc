package com.me.szzc.controller;

import com.me.szzc.aspect.SysLog;
import com.me.szzc.enums.AreaStatusEnum;
import com.me.szzc.enums.ModuleConstont;
import com.me.szzc.pojo.dto.AreaRoleSelDTO;
import com.me.szzc.pojo.entity.Area;
import com.me.szzc.pojo.entity.AreaRole;
import com.me.szzc.pojo.entity.Frole;
import com.me.szzc.utils.DateHelper;
import com.me.szzc.utils.Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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


    @RequestMapping("initAdd")
    public ModelAndView initAdd(String url) {
        ModelAndView view = new ModelAndView();
        view.setViewName(url);
        //查询所有的角色
        List<Frole> roleList = roleService.findAll();
        view.addObject("roleList", roleList);
        return view;
    }


    @RequestMapping("add")
    @SysLog(code = ModuleConstont.AREA_OPERATION, method = "新增片区")
    public ModelAndView add(String roleIds, String name,HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        view.setViewName("ssadmin/comm/ajaxDone");
        if (StringUtils.isBlank(name)) {
            view.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
            view.addObject(MESSAGE_KEY, "片区名称不能为空");
            return view;
        }

        name = name.trim();

        if (StringUtils.isBlank(roleIds)) {
            view.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
            view.addObject(MESSAGE_KEY, "片区权限不能为空");
            return view;
        }

        //判断name是否存在，必须唯一
        Area existsEntity = areaService.getByName(name);
        if (existsEntity != null) {
            view.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
            view.addObject(MESSAGE_KEY, "片区名称已存在，请核对后再操作");
            return view;
        }

        //创建人
        Long userId = getAdminSession(request).getFid();
        int resul = areaService.insert(name, roleIds, userId);
        if(resul <= 0 ) {
            view.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
            view.addObject(MESSAGE_KEY, "操作失败");
            return view;
        }else{
            view.addObject(STATUS_CODE_KEY, SUCCESS_CODE_NUM);
            view.addObject(MESSAGE_KEY, "片区新增成功");
        }

        view.addObject("callbackType","closeCurrent");
        return view;
    }


    @RequestMapping("updateStatus")
    @SysLog(code = ModuleConstont.AREA_OPERATION, method = "修改片区状态")
    public ModelAndView updateStatus(Long id, Integer status, HttpServletRequest request){
        ModelAndView view = new ModelAndView();
        view.setViewName("ssadmin/comm/ajaxDone");

        Area area = areaService.getById(id);
        if(area == null) {
            view.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
            view.addObject(MESSAGE_KEY, "记录不存在");
            return view;
        }

        if(status ==  area.getStatus()) {
            if(status == AreaStatusEnum.ENABLE.getCode()){
                view.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
                view.addObject(MESSAGE_KEY, "不可重复启用");
                return view;
            }else{
                view.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
                view.addObject(MESSAGE_KEY, "不可重复禁用");
                return view;
            }
        }

        area.setStatus(status);
        area.setModifiedDate(DateHelper.getTimestamp());

        Long userId = getAdminSession(request).getFid();
        area.setModifiedUserId(userId);
        areaService.updateStatus(area);


        view.addObject(STATUS_CODE_KEY, SUCCESS_CODE_NUM);
        view.addObject(MESSAGE_KEY, "操作成功");
        return view;
    }

    @RequestMapping("delete")
    @SysLog(code = ModuleConstont.AREA_OPERATION, method = "删除片区")
    public ModelAndView delete(Long id, HttpServletRequest request){
        ModelAndView view = new ModelAndView();
        view.setViewName("ssadmin/comm/ajaxDone");

        Area area = areaService.getById(id);
        if(area == null) {
            view.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
            view.addObject(MESSAGE_KEY, "记录不存在");
            return view;
        }

        areaService.delete(area);

        view.addObject(STATUS_CODE_KEY, SUCCESS_CODE_NUM);
        view.addObject(MESSAGE_KEY, "删除成功");
        return view;
    }


    @RequestMapping("initUpdate")
    public ModelAndView initUpdate(String url, Long id) {
        ModelAndView view = new ModelAndView();
        view.setViewName(url);
        //查询所有的角色
        List<Frole> roleList = roleService.findAll();
        //查询该片区关联的角色
        List<AreaRole> areaRoleList = areaService.areaRoleListByAreaId(id);
        List<AreaRoleSelDTO> dtoList = new ArrayList<>();
        StringBuilder roleIdsSb = new StringBuilder();
        for(Frole role : roleList) {
            AreaRoleSelDTO dto  = new AreaRoleSelDTO();
            dto.setFid(role.getFid());
            dto.setFname(role.getFname());
            if(areaRoleList != null && !areaRoleList.isEmpty()){
                for(AreaRole areaRole : areaRoleList) {
                    if(role.getFid().equals(areaRole.getRoleId())) {
                        dto.setSelFlag(true);
                        roleIdsSb.append(role.getFid()+",");
                        break;
                    }
                }
            }

            dtoList.add(dto);
        }

        String roleIds = roleIdsSb.toString();
        if(StringUtils.isNotBlank(roleIds) && roleIds.substring(roleIdsSb.length()-1, roleIdsSb.length()).equals(",")) {
            roleIds = roleIds.substring(0, roleIds.length()-1);
            view.addObject("roleIds", roleIds);
        }
        //查询片区
        Area area = areaService.getById(id);
        view.addObject("area", area);
        view.addObject("roleList", dtoList);
        return view;
    }

    @RequestMapping("update")
    public ModelAndView update(String roleIds, String name, Long id, HttpServletRequest request){
        ModelAndView view = new ModelAndView();
        view.setViewName("ssadmin/comm/ajaxDone");
        name = name.trim();

        if (StringUtils.isBlank(roleIds)) {
            view.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
            view.addObject(MESSAGE_KEY, "片区权限不能为空");
            return view;
        }

        Integer existsNum = areaService.existsByUpdateName(name, id);
        if(existsNum > 0) {
            view.addObject(STATUS_CODE_KEY, ERROR_CODE_NUM);
            view.addObject(MESSAGE_KEY, "该片区已存在，不能重复添加");
            return view;
        }
        Long userId = getAdminSession(request).getFid();
        areaService.update(roleIds, name, id, userId);

        view.addObject(STATUS_CODE_KEY, SUCCESS_CODE_NUM);
        view.addObject(MESSAGE_KEY, "修改成功");
        view.addObject("callbackType","closeCurrent");
        return view;
    }

}
