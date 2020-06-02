package com.me.szzc.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by luwei on 16-8-23.
 */
public class RequestEncodingFilter implements Filter {

    public static final Logger LOGGER = LoggerFactory.getLogger(RequestEncodingFilter.class);

    public static final String LOGIN_JSP = "/jsp/login/login.jsp";

    //小程序的appID
    public static final String APP_ID = "wx092abc62c110061e";

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpServletResponse response2 = (HttpServletResponse) response;
        //判断是否有跨站点请求伪造
        String basePath = request1.getContextPath();
        String referer = request1.getHeader("Referer");

        if (referer != null && !referer.contains(APP_ID) && !referer.trim().contains(request1.getServerName()) ) {
            LOGGER.error("违规操作将被终止，记录并保留追溯权利！, referer:{}, serverName:{}", referer, request1.getServerName());
            response2.sendRedirect(request1.getContextPath() + LOGIN_JSP);
            return;
        }

        //判断是否有注入攻击字符
        String inj = checkInputChar(request1);
        if (!inj.equals("")) {
            LOGGER.error("非法请求,参数含有注入攻击字符");
            //含注入字符，跳转到登陆界面
            response2.sendRedirect(basePath + LOGIN_JSP);
            return;
        } else {
            //传递控制到下一个过滤器
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    /**
     * 判断request中是否含有注入攻击字符
     *
     * @param request
     * @return
     */
    public String checkInputChar(ServletRequest request) {
        Enumeration e = request.getParameterNames();
        String attributeName;
        String attributeValues[];
        String inj = "";

        while (e.hasMoreElements()) {
            attributeName = (String) e.nextElement();
            //不对密码信息进行过滤，一般密码中可以包含特殊字符
            if (attributeName.equalsIgnoreCase("userPassword")) {
                continue;
            }

            attributeValues = request.getParameterValues(attributeName);
            for (int i = 0; i < attributeValues.length; i++) {
                if (attributeValues[i] == null || attributeValues[i].equals("")) {
                    continue;
                }

                inj = checkChar(attributeValues[i]);
                if (!inj.equals("")) {
                    return inj;
                }
            }
        }

        return inj;
    }


    /**
     * 判断字符串中是否含有注入攻击字符
     * 常见特殊字符 "\" ) \' * % < >";
     * @param str
     * @return
     */
    public String checkChar(String str) {
        String inj_str = " ) \' * % < >";
        String inj_strArr[] = inj_str.split(" ");
        for (int i = 0; i < inj_strArr.length; i++) {
            if (str.indexOf(inj_strArr[i]) >= 0) {
                return inj_strArr[i];
            }
        }
        return "";
    }


}
