package com.me.szzc.controller;

import com.me.szzc.aspect.SysLog;
import com.me.szzc.enums.AdminStatusEnum;
import com.me.szzc.enums.ModuleConstont;
import com.me.szzc.pojo.entity.Fadmin;
import com.me.szzc.pojo.entity.Frole;
import com.me.szzc.utils.Utils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController extends BaseController {

	private int numPerPage = Utils.getNumPerPage();
	
	@RequestMapping("/ssadmin/adminList")
	public ModelAndView Index(HttpServletRequest request) throws Exception{
		ModelAndView modelAndView = new ModelAndView() ;
		modelAndView.setViewName("ssadmin/adminList") ;
		int currentPage = 1;
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
			filter.append(orderDirection+" ");
		}else{
			filter.append("asc ");
		}
		List<Fadmin> list = this.adminService.list((currentPage - 1) * numPerPage, numPerPage, filter + "", true);
		modelAndView.addObject("adminList", list);
		modelAndView.addObject("numPerPage", numPerPage);
		modelAndView.addObject("currentPage", currentPage);
		modelAndView.addObject("rel", "adminList");
		modelAndView.addObject("totalCount",this.adminService.getAllCount( filter+""));
		return modelAndView ;
	}
	
	@RequestMapping("ssadmin/goAdminJSP")
	public ModelAndView goAdminJSP( HttpServletRequest request) throws Exception{
		String url = request.getParameter("url");
		ModelAndView modelAndView = new ModelAndView() ;
		modelAndView.setViewName(url) ;
		if(request.getParameter("uid") != null){
			Long fid = Long.parseLong(request.getParameter("uid"));
			Fadmin admin = this.adminService.findById(fid);
			modelAndView.addObject("fadmin", admin);
		}
		if(request.getSession().getAttribute("login_admin") != null){
			Fadmin admin = (Fadmin)request.getSession().getAttribute("login_admin");
			modelAndView.addObject("login_admin", admin);
		}
		
		List<Frole> roleList = this.roleService.findAll();
		Map<Long, String> map = new HashMap();
		for (Frole frole : roleList) {
			map.put(frole.getFid(),frole.getFname());
		}
		modelAndView.addObject("roleMap", map);
		return modelAndView;
	}
	
	@RequestMapping("ssadmin/saveAdmin")
	@SysLog(code = ModuleConstont.SYSTEM_OPERATION, method = "新增管理员")
	public ModelAndView saveAdmin(@RequestParam String fname,
                                  @RequestParam String fpassword,
                                  @RequestParam Long roleId,
                                  @RequestParam String fTelephone,
								  HttpServletRequest request) throws Exception{
		ModelAndView modelAndView = new ModelAndView() ;
		modelAndView.setViewName("ssadmin/comm/ajaxDone") ;
		List<Fadmin> all = this.adminService.findByName(fname);
		if(all != null && all.size() >0){
			modelAndView.addObject("statusCode",300);
			modelAndView.addObject("message","管理员登陆名已存在！");
			return modelAndView;
		}
		if(StringUtils.isEmpty(fname) || StringUtils.isEmpty(fpassword)
				|| StringUtils.isEmpty(fTelephone)){
			modelAndView.addObject("statusCode",300);
			modelAndView.addObject("message","请求参数不正确！");
			return modelAndView;
		}
		Fadmin fadmin = new Fadmin();
		fadmin.setFname(fname);

		fadmin.setFtelephone(fTelephone);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
		String dateString = sdf.format(new java.util.Date());
		fadmin.setFcreatetime(Timestamp.valueOf(dateString));
		fadmin.setSalt(Utils.getUUID());
		fadmin.setFpassword(Utils.MD5(fpassword,fadmin.getSalt()));
		fadmin.setFstatus(AdminStatusEnum.NORMAL_VALUE);
		fadmin.setFroleid(roleId);
		this.adminService.saveObj(fadmin);
//		Fadmin sessionAdmin = (Fadmin)request.getSession().getAttribute("login_admin");
//		this.adminService.updateAdminlog(sessionAdmin, getIpAddr(request), LogTypeEnum.Admin_ADD);
		
		modelAndView.addObject("statusCode",200);
		modelAndView.addObject("message","新增成功");
		modelAndView.addObject("callbackType","closeCurrent");
		return modelAndView;
	}
	
	@RequestMapping("ssadmin/forbbinAdmin")
	@SysLog(code = ModuleConstont.SYSTEM_OPERATION, method = "修改管理员禁用状态")
	public ModelAndView forbbinAdmin(HttpServletRequest request) throws Exception{
		ModelAndView modelAndView = new ModelAndView() ;
		modelAndView.setViewName("ssadmin/comm/ajaxDone") ;
		Long fid = Long.parseLong(request.getParameter("uid"));
		Fadmin sessionAdmin = (Fadmin)request.getSession().getAttribute("login_admin");
		if(fid.equals( sessionAdmin.getFid())){
			modelAndView.addObject("statusCode",300);
			modelAndView.addObject("message","不允许禁用当前登陆的管理员！");
			return modelAndView;
		}
		Fadmin admin = this.adminService.findById(fid);
		int status = Integer.parseInt(request.getParameter("status"));
		if(status == 1){
			admin.setFstatus(AdminStatusEnum.FORBBIN_VALUE);
		}else if (status == 2){
			admin.setFstatus(AdminStatusEnum.NORMAL_VALUE);
		}
		this.adminService.updateObj(admin);
//		this.adminService.updateAdminlog(sessionAdmin, getIpAddr(request), LogTypeEnum.Admin_UPDATE);
		
		modelAndView.addObject("statusCode",200);
		if (status == AdminStatusEnum.FORBBIN_VALUE) {
			modelAndView.addObject("message", "禁用成功");
		} else if (status == AdminStatusEnum.NORMAL_VALUE) {
			modelAndView.addObject("message", "解除禁用成功");
		}
		return modelAndView;
	}
	
	@RequestMapping("ssadmin/updateAdmin")
	@SysLog(code = ModuleConstont.SYSTEM_OPERATION, method = "修改管理员密码")
	public ModelAndView updateAdmin(HttpServletRequest request) throws Exception{
		ModelAndView modelAndView = new ModelAndView() ;
		Long fid = Long.parseLong(request.getParameter("fadmin.fid"));
		Fadmin fadmin = this.adminService.findById(fid);
		Fadmin sessionAdmin = (Fadmin)request.getSession().getAttribute("login_admin");
		if(fid == sessionAdmin.getFid()){
			modelAndView.setViewName("ssadmin/comm/ajaxDone") ;
			modelAndView.addObject("statusCode",300);
			modelAndView.addObject("message","不允许修改当前登陆的管理员密码！");
			return modelAndView;
		}
		String passWord = request.getParameter("fadmin.fpassword");
		fadmin.setFpassword(Utils.MD5(passWord,fadmin.getSalt()));
	
		this.adminService.updateObj(fadmin);
//        this.adminService.updateAdminlog(sessionAdmin, getIpAddr(request), LogTypeEnum.Admin_UPDATE);
		modelAndView.setViewName("ssadmin/comm/ajaxDone") ;
		modelAndView.addObject("statusCode",200);
		modelAndView.addObject("message","修改密码成功");
		modelAndView.addObject("callbackType","closeCurrent");
		return modelAndView;
	}
	
	@RequestMapping("ssadmin/updateAdminPassword")
	@SysLog(code = ModuleConstont.SYSTEM_OPERATION, method = "修改密码")
	public ModelAndView updateAdminPassword(HttpServletRequest request) throws Exception{
		ModelAndView modelAndView = new ModelAndView() ;
		Long fid = Long.parseLong(request.getParameter("fadmin.fid"));
		Fadmin fadmin = this.adminService.findById(fid);
		String truePassword = fadmin.getFpassword();
		String newPassWord = request.getParameter("fadmin.fpassword");
		String oldPassword = request.getParameter("oldPassword");
		String newPasswordMD5 = Utils.MD5(newPassWord,fadmin.getSalt());
		String oldPasswordMD5 = Utils.MD5(oldPassword,fadmin.getSalt());
        if(!truePassword.equals(oldPasswordMD5)){
    		modelAndView.setViewName("ssadmin/comm/ajaxDone") ;
    		modelAndView.addObject("statusCode",300);
    		modelAndView.addObject("message","原密码输入有误，请重新输入");
    		return modelAndView;
        }
        fadmin.setFpassword(newPasswordMD5);
		this.adminService.updateObj(fadmin);
		/*Fadmin sessionAdmin = (Fadmin)request.getSession().getAttribute("login_admin");
		this.adminService.updateAdminlog(sessionAdmin, getIpAddr(request), LogTypeEnum.Admin_UPDATE);*/
		modelAndView.setViewName("ssadmin/comm/ajaxDone") ;
		modelAndView.addObject("statusCode",200);
		modelAndView.addObject("message","修改密码成功");
		modelAndView.addObject("callbackType","closeCurrent");
		return modelAndView;
	}

	@RequestMapping("ssadmin/loginOut")
	public ModelAndView loginOut() throws Exception{
		ModelAndView modelAndView = new ModelAndView() ;
		Subject admin = SecurityUtils.getSubject();
		admin.getSession().removeAttribute("permissions");
		admin.logout();
		modelAndView.setViewName("ssadmin/login");
		return modelAndView;
	}
	
	
	@RequestMapping("ssadmin/updateAdminRole")
	@SysLog(code = ModuleConstont.SYSTEM_OPERATION, method = "修改管理员角色")
	public ModelAndView updateAdminRole(HttpServletRequest request) throws Exception{
		ModelAndView modelAndView = new ModelAndView() ;
		Long fid = Long.parseLong(request.getParameter("fadmin.fid"));
		Fadmin fadmin = this.adminService.findById(fid);
		if(fadmin.getFname().equals("admin")){
			modelAndView.setViewName("ssadmin/comm/ajaxDone") ;
			modelAndView.addObject("statusCode",300);
			modelAndView.addObject("message","超级管理员角色不允许修改！");
			return modelAndView;
		}
		
		Long roleId = Long.parseLong(request.getParameter("frole.fid"));
		fadmin.setFroleid(roleId);
		this.adminService.updateObj(fadmin);
		
		/*Fadmin sessionAdmin = (Fadmin)request.getSession().getAttribute("login_admin");
		this.adminService.updateAdminlog(sessionAdmin, getIpAddr(request), LogTypeEnum.Admin_ROLE);*/

		modelAndView.setViewName("ssadmin/comm/ajaxDone") ;
		modelAndView.addObject("statusCode",200);
		modelAndView.addObject("message","修改成功");
		modelAndView.addObject("callbackType","closeCurrent");
		return modelAndView;
	}
	
}
