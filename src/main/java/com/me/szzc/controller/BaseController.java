package com.me.szzc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author luwei
 * @date 2019-01-25
 */
public class BaseController extends BaseServiceCtrl{



    public static String getIpAddr(HttpServletRequest request) {
        try {
            String ip = request.getHeader("X-Forwarded-For");
            try {
                if(ip != null && ip.trim().length() >0){
                    return ip.split(",")[0];
                }
            } catch (Exception e) {}

            try {
                ip = request.getHeader("X-Real-IP");
                if ((ip != null && ip.trim().length() >0) && (!"unKnown".equalsIgnoreCase(ip))) {
                    return ip;
                }
            } catch (Exception e) {}

            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("http_client_ip");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            // 如果是多级代理，那么取第一个ip为客户ip
            if (ip != null && ip.indexOf(",") != -1) {
                ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
            }
            return ip;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return request.getRemoteAddr();
        }
    }


    public HttpSession getSession(HttpServletRequest request){
        return request.getSession() ;
    }

}
