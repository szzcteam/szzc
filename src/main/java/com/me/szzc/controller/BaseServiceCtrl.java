package com.me.szzc.controller;

import com.me.szzc.init.ConstantMap;
import com.me.szzc.service.AdminService;
import com.me.szzc.service.RoleService;
import com.me.szzc.service.SecurityService;
import com.me.szzc.service.WebBaseInfoService;
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

}
