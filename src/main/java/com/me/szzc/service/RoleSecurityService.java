package com.me.szzc.service;

import com.me.szzc.dao.FroleSecurityMapper;
import com.me.szzc.pojo.entity.FroleSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class RoleSecurityService {
	@Autowired
	private FroleSecurityMapper froleSecurityMapper;

	public FroleSecurity findById(int id) {
		return this.froleSecurityMapper.findById(id);
	}

	public void saveObj(FroleSecurity obj) {
		List<FroleSecurity> list = new ArrayList<>();
		list.add(obj);
		this.froleSecurityMapper.insert(list);
	}

	public void deleteObj(int id) {
		this.froleSecurityMapper.deleteById(id);


	}

	public void updateObj(FroleSecurity obj) {
		//TODO 待完成
//		this.froleSecurityMapper.attachDirty(obj);
	}

	public List<FroleSecurity> findByProperty(FroleSecurity froleSecurity) {
		return this.froleSecurityMapper.findByProperty(froleSecurity);
	}

	public List<FroleSecurity> findAll() {
		return this.froleSecurityMapper.findAll();
	}

	public List<FroleSecurity> list(int firstResult, int maxResults,
			String filter,boolean isFY) {
		// TODO 待分页
		return this.froleSecurityMapper.findAll();
	}
	
	public void deleteByRoleId(int roleId) {
		this.froleSecurityMapper.deleteByRoleId(roleId);
	}
}