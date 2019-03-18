package com.me.szzc.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author luwei
 * @date 2019-03-17
 */
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
                //后台
                if(uri.startsWith("/ssadmin/2bcf8d4e-e2aa-11e6-bddf-005056ab18e8.html")
                        ||uri.startsWith("/ssadmin/95afee23-e2ab-11e6-bddf-005056ab18e8.html")
                        || req.getSession().getAttribute("login_admin")!=null){
                    chain.doFilter(request, response) ;
                }else{
                    resp.sendRedirect("/") ;
                }
                return ;
            }else if(uri.startsWith("/front/")){
                //当前系统前端，是不需要登陆的
                chain.doFilter(request, response) ;
            }
        }
    }

    @Override
    public void destroy() {

    }
}