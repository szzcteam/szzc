package com.me.szzc.controller;

import com.me.szzc.init.ConstantMap;
import com.me.szzc.service.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author luwei
 * @date 2019-01-25
 */
public class BaseServiceCtrl {


    @Autowired
    protected ConstantMap constantMap ;

    @Autowired
    AdminService adminService;

    @Autowired
    WebBaseInfoService webBaseInfoService;

    @Autowired
    RoleService roleService;

    @Autowired
    SecurityService securityService;

    @Autowired
    RoleSecurityService roleSecurityService;

    @Autowired
    SysLogService sysLogService;

    @Autowired
    SystemArgsService systemArgsService;

}
