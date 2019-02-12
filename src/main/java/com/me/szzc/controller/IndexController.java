package com.me.szzc.controller;

import com.me.szzc.pojo.entity.Fadmin;
import com.me.szzc.utils.Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author luwei
 * @date 2019-01-25
 */
@Controller
public class IndexController extends BaseController{

    public static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/ssadmin/2bcf8d4e-e2aa-11e6-bddf-005056ab18e8")
    public ModelAndView login() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/login");
        return modelAndView;
    }

    @RequestMapping("/ssadmin/index")
    public ModelAndView Index(HttpServletRequest request) throws Exception {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ssadmin/index");
        modelAndView.addObject("dateTime", sdf1.format(new Date()));
        modelAndView.addObject("login_admin", request.getSession()
                .getAttribute("login_admin"));
        return modelAndView;
    }



    @RequestMapping("/ssadmin/95afee23-e2ab-11e6-bddf-005056ab18e8")
    public ModelAndView submitLogin(
            HttpServletRequest request,
            @RequestParam(required = true) String name,
            @RequestParam(required = true) String password,
            @RequestParam(required = true) String vcode) throws Exception {

        ModelAndView modelAndView = new ModelAndView();

        if (name == null || "".equals(name.trim()) || password == null
                || "".equals(password.trim()) || vcode == null
                || "".equals(vcode.trim())) {
            modelAndView.setViewName("redirect:/ssadmin/2bcf8d4e-e2aa-11e6-bddf-005056ab18e8.html");
            return modelAndView;
        } else {
            String ip = getIpAddr(request);
			/*int admin_limit = this.frontValidateService.getLimitCount(ip,
					CountLimitTypeEnum.AdminLogin);
			if (admin_limit <= 0) {
				logger.info("请求密码超过限制 ip =" + ip);
				modelAndView.addObject("error", "连续登陆错误5次，为安全起见，禁止登陆半小时！");
				modelAndView.setViewName("/ssadmin/login");
				return modelAndView;
			}*/

            if (!vcode.equalsIgnoreCase((String) getSession(request).getAttribute(
                    "checkcode"))) {
                modelAndView.addObject("error", "验证码错误！");
                modelAndView.setViewName("/ssadmin/login");
                return modelAndView;
            }

            List<Fadmin> admins = this.adminService.findByName(name);
            if(admins == null || admins.size() !=1){
                modelAndView.addObject("error", "管理员不存在！");
                modelAndView.setViewName("/ssadmin/login");
                return modelAndView;
            }
            Subject admin = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(name,
                    Utils.MD5(password,admins.get(0).getSalt()));
            token.setRememberMe(true);
            token.setHost(getIpAddr(request));
            try {
                admin.login(token);

            } catch (Exception e) {
                token.clear();
				/*this.frontValidateService.updateLimitCount(ip,
						CountLimitTypeEnum.AdminLogin);*/
                modelAndView.addObject("error", e.getMessage());
                modelAndView.setViewName("/ssadmin/login");
                return modelAndView;
            }
        }
        modelAndView.setViewName("redirect:/ssadmin/index.html");
        return modelAndView;
    }




}
