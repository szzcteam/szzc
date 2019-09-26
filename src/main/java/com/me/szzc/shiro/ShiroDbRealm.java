package com.me.szzc.shiro;


import com.me.szzc.enums.AdminStatusEnum;
import com.me.szzc.log.LOG;
import com.me.szzc.pojo.entity.Fadmin;
import com.me.szzc.pojo.entity.Frole;
import com.me.szzc.pojo.entity.FroleSecurity;
import com.me.szzc.service.AdminService;
import com.me.szzc.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ShiroDbRealm extends AuthorizingRealm {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;


    //会话超时时间设为1小时
    private final int sessionTimeOut = 1*60*60*1000;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
    	 SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    	if(SecurityUtils.getSubject().getSession().getAttribute("permissions") != null){
    		info = (SimpleAuthorizationInfo) SecurityUtils.getSubject().getSession().getAttribute("permissions");
    	}else{
    		 //获取当前登录的用户名
            String name = (String) super.getAvailablePrincipal(principals);
            List<String> permissions = new ArrayList<String>();
            permissions.add("xxxxxxx");
            List<Fadmin> all = adminService.findByName( name);
            if(all != null && all.size() >0){
            	Fadmin admin = all.get(0);
            	if(admin.getFroleid() != null){
            	    //查询角色
                    Long roleId = admin.getFroleid();
                    Frole role = roleService.findById(roleId);
            		String roleName = "";
            		if(role != null && StringUtils.isNotBlank(role.getFname())) {
            		    roleName = role.getFname();
                    }
                    //给当前用户设置角色
                    info.addRole(roleName);
                    Set<FroleSecurity> set = role.getFroleSecurities(); //admin.getFrole().getFroleSecurities();
                    for (FroleSecurity froleSecurity : set) {
                    	permissions.add(froleSecurity.getFsecurity().getFurl());
    				}
            	}
            }else{
                throw new AuthorizationException();
            }
            //给当前用户设置权限
            info.addStringPermissions(permissions); 
            SecurityUtils.getSubject().getSession().setTimeout(sessionTimeOut);//session失效时间
            SecurityUtils.getSubject().getSession().setAttribute("permissions", info);
    	}
        return info;
    }

    /**
     *  认证回调函数,登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        Fadmin fadmin = new Fadmin();
		fadmin.setFname(token.getUsername());
		fadmin.setFpassword(new String(token.getPassword()));
		List<Fadmin> all = null;
		try {
			all = this.adminService.login(fadmin);
		} catch (Exception e) {
			throw new AuthenticationException("登陆异常！");
		}
        if (all != null && all.size() >0) {
            if(all.get(0).getFstatus() == AdminStatusEnum.FORBBIN_VALUE){
            	throw new AuthenticationException("管理员已禁用！");
            }
            Session session = SecurityUtils.getSubject().getSession();
            session.setTimeout(sessionTimeOut); //session失效时间
            session.setAttribute("login_admin", all.get(0));
            LOG.d(this, "===================SecurityUtils-sessionId: " + session.getId());

            return new SimpleAuthenticationInfo(all.get(0).getFname(),all.get(0).getFpassword(), all.get(0).getFname());
        } else {
        	throw new AuthenticationException("错误的用户名或密码！");
        }
    }
    
}