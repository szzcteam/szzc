package com.me.szzc.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author luwei
 * @date 2019-03-17
 */
@Slf4j
public class UrlRedirectFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response ;
        String uri = req.getRequestURI().toLowerCase().trim() ;

        //不接受任何jsp请求
        if(uri.endsWith(".jsp")){
            return ;
        }

        //下载控件的，可以直接访问
        if(uri.indexOf("lodop_assets") != -1 ){
            chain.doFilter(request, response) ;
            return ;
        }

        //微信小程序请求，可以直接访问
        if (uri.indexOf("/wx/") != -1) {
            chain.doFilter(request, response);
            return;
        }

        //只拦截.html结尾的请求
        if(!uri.endsWith(".html")){
            chain.doFilter(request, response) ;
            return ;
        }


        //免登陆可访问
        if(	uri.startsWith("/testa.html")){
            chain.doFilter(request, response) ;
            return ;
        }else{
            //需要登陆后才能访问
            if(uri.startsWith("/ssadmin/")){
                //后台登录页面、登录请求，或者是登录状态的，才可自由访问后台资源
                if(uri.startsWith("/ssadmin/2bcf8d4e-e2aa-11e6-bddf-005056ab18e8.html")
                        ||uri.startsWith("/ssadmin/95afee23-e2ab-11e6-bddf-005056ab18e8.html")
                        || req.getSession().getAttribute("login_admin")!=null){
                    chain.doFilter(request, response) ;
                }else{
                    resp.sendRedirect("/") ;
                }
                return ;
            }else if(uri.startsWith("/front/")){
                //当前系统前端，是不需要登陆的，直接释放即可
                chain.doFilter(request, response) ;
            }
        }
    }

    @Override
    public void destroy() {

    }
}
