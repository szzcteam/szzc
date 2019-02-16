package com.me.szzc.controller;


import com.me.szzc.aspect.SysLog;
import com.me.szzc.enums.FilePostfixEnum;
import com.me.szzc.enums.FileUploadTypeEnum;
import com.me.szzc.enums.ModuleConstont;
import com.me.szzc.pojo.entity.Fsystemargs;
import com.me.szzc.utils.fileUpload.client.AliyunFileUploadUtils;
import com.me.szzc.utils.fileUpload.facade.AliyunFileUploadFacade;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
public class SystemArgsController extends BaseController {

	// 每页显示多少条数据
	private int numPerPage = 50;

	@RequestMapping("/ssadmin/systemArgsList")
	public ModelAndView Index(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("ssadmin/systemArgsList");
		// 当前页
		int currentPage = 1;
		// 搜索关键字
		String keyWord = request.getParameter("keywords");
		String orderField = request.getParameter("orderField");
		String orderDirection = request.getParameter("orderDirection");
		if (request.getParameter("pageNum") != null) {
			currentPage = Integer.parseInt(request.getParameter("pageNum"));
		}
		StringBuffer filter = new StringBuffer();
		filter.append("where 1=1 ");
		if (keyWord != null && keyWord.trim().length() > 0) {
			filter.append("and fkey like '%"+keyWord+"%' ");
			modelAndView.addObject("keywords", keyWord);
		}

		List<Fsystemargs> list = this.systemArgsService.list((currentPage - 1)
				* numPerPage, numPerPage, filter+"", true);
		modelAndView.addObject("systemArgsList", list);
		modelAndView.addObject("numPerPage", numPerPage);
		modelAndView.addObject("rel", "systemArgsList");
		modelAndView.addObject("currentPage", currentPage);
		// 总数量
		modelAndView.addObject("totalCount",
				this.systemArgsService.getAllCount(filter+""));
		return modelAndView;
	}


	@RequestMapping("/ssadmin/operateArgsList")
	public ModelAndView Index1(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("ssadmin/operateArgsList");
		// 当前页
		int currentPage = 1;
		// 搜索关键字
		String keyWord = request.getParameter("keywords");
		String orderField = request.getParameter("orderField");
		String orderDirection = request.getParameter("orderDirection");
		if (request.getParameter("pageNum") != null) {
			currentPage = Integer.parseInt(request.getParameter("pageNum"));
		}
		StringBuffer filter = new StringBuffer();
		filter.append("where type=1 ");
		if (keyWord != null && keyWord.trim().length() > 0) {
			filter.append("and fkey like '%"+keyWord+"%' ");
			modelAndView.addObject("keywords", keyWord);
		}
		List<Fsystemargs> list = this.systemArgsService.list((currentPage - 1)
				* numPerPage, numPerPage, filter+"", true);
		modelAndView.addObject("systemArgsList", list);
		modelAndView.addObject("numPerPage", numPerPage);
		modelAndView.addObject("rel", "systemArgsList");
		modelAndView.addObject("currentPage", currentPage);
		// 总数量
		modelAndView.addObject("totalCount",
				this.systemArgsService.getAllCount( filter+""));
		return modelAndView;
	}

	@RequestMapping("ssadmin/goSystemArgsJSP")
	public ModelAndView goSystemArgsJSP(HttpServletRequest request) throws Exception {
		String url = request.getParameter("url");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(url);
		if (request.getParameter("uid") != null) {
			Long fid = Long.parseLong(request.getParameter("uid"));
			Fsystemargs systemargs = this.systemArgsService.findById(fid);
			modelAndView.addObject("systemargs", systemargs);
		}
		if(request.getParameter("fileType") != null){
			modelAndView.addObject("fileType", request.getParameter("fileType"));
		}
		if(request.getParameter("maxSize") != null){
			modelAndView.addObject("maxSize", request.getParameter("maxSize"));
		}
		return modelAndView;
	}

	@RequestMapping("ssadmin/saveSystemArgs")
	public ModelAndView saveSystemArgs(
			@RequestParam(required=false) CommonsMultipartFile filedata,
			@RequestParam(required=true) String key,
			@RequestParam(required=false) String value,
			@RequestParam(required=false) String desc,
			@RequestParam(required=false) String furl
			) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("ssadmin/comm/ajaxDone");
		
		Fsystemargs systemArgs = new Fsystemargs();
		systemArgs.setFkey(key);
		systemArgs.setFvalue(value);
		systemArgs.setFdescription(desc);
		systemArgs.setFurl(furl);
		String fpictureUrl = "";
		boolean isTrue = false;
		if (filedata != null && !filedata.isEmpty()) {
			AliyunFileUploadFacade aliyunUtil = AliyunFileUploadUtils.getFileUploadObject();
			if (!aliyunUtil.checkFileType(filedata, FilePostfixEnum.IMG2)) {
				modelAndView.addObject("statusCode", 300);
				modelAndView.addObject("message", "非jpg、png文件格式");
				return modelAndView;
			}

			//文件上传
			fpictureUrl = aliyunUtil.fileUpload(filedata, FileUploadTypeEnum.SYSARGS.getConfigReq());
			if (StringUtils.isNotBlank(fpictureUrl)) {
				isTrue = true;
			}
		}
		if(isTrue){
			systemArgs.setFvalue(fpictureUrl);
		}
		if(!isTrue && (value==null || value.trim().length()==0)){
			modelAndView.addObject("statusCode", 300);
			modelAndView.addObject("message", "参数值或图片链接不能全为空");
			return modelAndView;
		}
		 
		this.systemArgsService.saveObj(systemArgs);

		constantMap.put(systemArgs.getFkey(), systemArgs.getFvalue());
		
		modelAndView.addObject("statusCode", 200);
		modelAndView.addObject("message", "新增成功");
		modelAndView.addObject("callbackType", "closeCurrent");
		return modelAndView;
	}

    /**
	 * 修改系统参数
	 * @param key
	 * @param value
	 * @param fid
	 * @param desc
	 * @return
     * @throws Exception
     */
	@RequestMapping("ssadmin/updateSystemArgs")
	@SysLog(code = ModuleConstont.SYSTEM_OPERATION, method = "修改系统参数")
	public ModelAndView updateSystemArgs(
			HttpServletRequest request,
			@RequestParam(required=true) String key,
			@RequestParam(required=true) String value,
			@RequestParam(required=true) Long fid,
			@RequestParam(required=false) String desc,
			@RequestParam(required=false) String furl
			) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("ssadmin/comm/ajaxDone");
		Fsystemargs systemArgs = this.systemArgsService.findById(fid);
//		systemArgs.setFkey(key);
		systemArgs.setFvalue(value);
		systemArgs.setFdescription(desc);
		systemArgs.setFurl(furl);
		if(value==null || value.trim().length()==0){
			modelAndView.addObject("statusCode", 300);
			modelAndView.addObject("message", "参数值或图片链接不能全为空");
			return modelAndView;
		}
		
		this.systemArgsService.updateObj(systemArgs);
		
		constantMap.put(systemArgs.getFkey(), systemArgs.getFvalue());

		modelAndView.addObject("statusCode", 200);
		modelAndView.addObject("message", "更新成功");
		modelAndView.addObject("callbackType", "closeCurrent");
		return modelAndView;
	}


}
