package com.me.szzc.controller;

import com.me.szzc.pojo.dto.ParamArray;
import com.me.szzc.pojo.entity.Fwebbaseinfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class WebBaseInfoController extends BaseController {

	
	@RequestMapping("/ssadmin/webBaseInfoList")
	public ModelAndView index() throws Exception{
		ModelAndView modelAndView = new ModelAndView() ;
		modelAndView.setViewName("ssadmin/webBaseInfo") ;
		List<Fwebbaseinfo> webBaseList = this.webBaseInfoService.selectAll();
		if(webBaseList.size() >0){
			modelAndView.addObject("webBaseInfo",(Fwebbaseinfo)webBaseList.get(0));
		}
		return modelAndView ;
	}
	
	@RequestMapping("/ssadmin/saveOrUpdateWebInfo")
	public ModelAndView saveOrUpdateWebInfo(ParamArray param, HttpServletRequest request) throws Exception{
		Fwebbaseinfo fwebbaseinfo = param.getFwebbaseinfo();
		Fwebbaseinfo newInfo = null;
		if(request.getParameter("fwebbaseinfo.fid") != null){
			Long fid = Long.parseLong(request.getParameter("fwebbaseinfo.fid"));
			newInfo = this.webBaseInfoService.selectByPrimaryKey(fid);
			newInfo.setFbeianInfo(fwebbaseinfo.getFbeianInfo());
			newInfo.setFcopyRights(fwebbaseinfo.getFcopyRights());
			newInfo.setFdescription(fwebbaseinfo.getFdescription());
			newInfo.setFkeywords(fwebbaseinfo.getFkeywords());
			newInfo.setFsystemMail(fwebbaseinfo.getFsystemMail());
			newInfo.setFtitle(fwebbaseinfo.getFtitle());
			this.webBaseInfoService.updateObj(newInfo);
		}else{
			newInfo = new Fwebbaseinfo();
			newInfo.setFbeianInfo(fwebbaseinfo.getFbeianInfo());
			newInfo.setFcopyRights(fwebbaseinfo.getFcopyRights());
			newInfo.setFdescription(fwebbaseinfo.getFdescription());
			newInfo.setFkeywords(fwebbaseinfo.getFkeywords());
			newInfo.setFsystemMail(fwebbaseinfo.getFsystemMail());
			newInfo.setFtitle(fwebbaseinfo.getFtitle());
			this.webBaseInfoService.saveObj(newInfo);
		}
		
		constantMap.put("webinfo", fwebbaseinfo);
		
		ModelAndView modelAndView = new ModelAndView() ;
		modelAndView.setViewName("ssadmin/comm/ajaxDone") ;
		modelAndView.addObject("statusCode",200);
		modelAndView.addObject("message","保存成功");
		modelAndView.addObject("callbackType","closeCurrent");
		
		return modelAndView ;
	}

}
