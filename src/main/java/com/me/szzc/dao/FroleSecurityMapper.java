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
    int deleteByRoleId(Long roleId);

    int deleteById(Long fid);

    FroleSecurity findById(Long fid);
    
    List<FroleSecurity> selectByRoleId(Long roleId);

    List<FroleSecurity> findAll();

    List<FroleSecurity> findByProperty(FroleSecurity froleSecurity);
    
    
}