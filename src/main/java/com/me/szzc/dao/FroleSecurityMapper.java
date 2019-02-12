package com.me.szzc.dao;


import com.me.szzc.pojo.entity.FroleSecurity;

import java.util.List;

public interface FroleSecurityMapper {

	/**
	 * 添加角色的权限信息
	 */
    int insert(List<FroleSecurity> record);

    /**
     * 删除角色的权限信息
     */
    int deleteByRoleId(Integer roleId);

    int deleteById(Integer fid);

    FroleSecurity findById(Integer fid);
    
    List<FroleSecurity> selectByRoleId(Integer roleId);

    List<FroleSecurity> findAll();

    List<FroleSecurity> findByProperty(FroleSecurity froleSecurity);
    
    
}