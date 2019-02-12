package com.me.szzc.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserInfoFilter implements Filter{

	public static final Logger LOGGER = LoggerFactory.getLogger(UserInfoFilter.class);
	
	private FilterConfig config = null;

	@Override
	public void destroy() {
		config = null;
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request2=(HttpServletRequest)request;
		HttpSession session=request2.getSession();
		HttpServletResponse response2=(HttpServletResponse) response;
		//获取用户
	/*	UserInfoEntity user = (UserInfoEntity) session.getAttribute("user");
		//获取请求信息
		String url = request2.getRequestURI();
		LOGGER.info("请求url信息："+url);
		//不是登陆，不是对外接口，用户信息为空，跳转登陆页面
		if(!url.contains("/login") && !url.contains("external") && user ==  null){
			response2.sendRedirect(request2.getContextPath()+"/jsp/login/login.jsp");
			return;
		}*/
		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	public FilterConfig getConfig() {
		return config;
	}

	public void setConfig(FilterConfig config) {
		this.config = config;
	}
}
