package com.me.szzc.controller;

import com.me.szzc.aspect.SysLog;
import com.me.szzc.enums.ModuleConstont;
import com.me.szzc.pojo.entity.Frole;
import com.me.szzc.pojo.entity.FroleSecurity;
import com.me.szzc.pojo.entity.Fsecurity;
import com.me.szzc.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class RoleController extends BaseController {


	//每页显示多少条数据
	private int numPerPage = Utils.getNumPerPage();
	
	@RequestMapping("/ssadmin/roleList")
	public ModelAndView roleList(HttpServletRequest request) throws Exception{
		ModelAndView modelAndView = new ModelAndView() ;
		modelAndView.setViewName("ssadmin/roleList") ;
		//当前页
		int currentPage = 1;
		//搜索关键字
		String keyWord = request.getParameter("keywords");
		String orderField = request.getParameter("orderField");
		String orderDirection = request.getParameter("orderDirection");
		if(request.getParameter("pageNum") != null){
			currentPage = Integer.parseInt(request.getParameter("pageNum"));
		}
		StringBuffer filter = new StringBuffer();
		filter.append("where 1=1 ");
		if(keyWord != null && keyWord.trim().length() >0){
			filter.append("and fname like '%"+keyWord+"%' ");
			modelAndView.addObject("keywords", keyWord);
		}
		if(orderField != null && orderField.trim().length() >0){
			filter.append("order by "+orderField+"");
		}else{
			filter.append("order by fid ");
		}
		if(orderDirection != null && orderDirection.trim().length() >0){
			filter.append(orderDirection+"");
		}else{
			filter.append("desc ");
		}
		List<Frole> list = this.roleService.list((currentPage-1)*numPerPage, numPerPage, filter+"",true);
		modelAndView.addObject("roleList", list);
		modelAndView.addObject("numPerPage", numPerPage);
		modelAndView.addObject("currentPage", currentPage);
		modelAndView.addObject("rel", "roleList");
		//总数量
		modelAndView.addObject("totalCount",this.roleService.getAllCount( filter+""));
		return modelAndView ;
	}
	
	@RequestMapping("ssadmin/goRoleJSP")
	public ModelAndView goRoleJSP(HttpServletRequest request) throws Exception{
		String url = request.getParameter("url");
		ModelAndView modelAndView = new ModelAndView() ;
		modelAndView.setViewName(url) ;
		if(request.getParameter("uid") != null){
			Long fid = Long.parseLong(request.getParameter("uid"));
			Fsecurity security = this.securityService.findById(fid);
			modelAndView.addObject("security", security);
		}
		if(request.getParameter("roleId") != null){
			Long roleId = Long.parseLong(request.getParameter("roleId"));
			Frole role = this.roleService.findById(roleId);
			modelAndView.addObject("role", role);
		}
		return modelAndView;
	}
	
	@RequestMapping("ssadmin/saveRole")
	@SysLog(code = ModuleConstont.SYSTEM_OPERATION, method = "新增角色")
	public ModelAndView saveRole(HttpServletRequest request) throws Exception{
		ModelAndView modelAndView = new ModelAndView() ;
		List<Fsecurity> all = this.securityService.findAll();
		Frole role = new Frole();
		role.setFdescription(request.getParameter("fdescription"));
		role.setFname(request.getParameter("fname"));
		//保存角色
		this.roleService.saveObj(role);
		
		for (Fsecurity fsecurity : all) {
			Long fid = fsecurity.getFid();
			String key = "role["+fid+"]";
			String value = request.getParameter(key);
			if(value != null){
				FroleSecurity roleSecurity = new FroleSecurity();
				roleSecurity.setFrole(role);
				roleSecurity.setFroleid(role.getFid());
				roleSecurity.setFsecurity(fsecurity);
                roleSecurity.setFsecurityid(fid);
				//保存角色菜单
				this.roleSecurityService.saveObj(roleSecurity);
			}
		}

		modelAndView.setViewName("ssadmin/comm/ajaxDone") ;
		modelAndView.addObject("statusCode",200);
		modelAndView.addObject("message","保存成功");
		modelAndView.addObject("callbackType","closeCurrent");
		return modelAndView;
	}
	
	@RequestMapping("ssadmin/updateRole")
	@SysLog(code = ModuleConstont.SYSTEM_OPERATION, method = "修改角色")
	public ModelAndView updateRole(HttpServletRequest request) throws Exception{
		ModelAndView modelAndView = new ModelAndView() ;
		List<Fsecurity> all = this.securityService.findAll();
		Long roleId = Long.parseLong(request.getParameter("roleId"));
		Frole role = this.roleService.findById(roleId);
		role.setFdescription(request.getParameter("fdescription"));
		role.setFname(request.getParameter("fname"));
		this.roleService.updateObj(role);
		
		this.roleSecurityService.deleteByRoleId(roleId);
		for (Fsecurity fsecurity : all) {
            Long fid = fsecurity.getFid();
			String key = "role["+fid+"]";
			String value = request.getParameter(key);
			if(value != null){
				FroleSecurity roleSecurity = new FroleSecurity();
				roleSecurity.setFrole(role);
                roleSecurity.setFroleid(role.getFid());
				roleSecurity.setFsecurity(fsecurity);
                roleSecurity.setFsecurityid(fid);
				this.roleSecurityService.saveObj(roleSecurity);
			}
		}

		modelAndView.setViewName("ssadmin/comm/ajaxDone") ;
		modelAndView.addObject("statusCode",200);
		modelAndView.addObject("message","更新成功");
		modelAndView.addObject("callbackType","closeCurrent");
		return modelAndView;
	}
	
}
