package com.me.szzc.service;

import com.me.szzc.dao.FroleMapper;
import com.me.szzc.pojo.entity.Frole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class RoleService {
	@Autowired
	private FroleMapper froleMapper;

	public Frole findById(int id) {
		Frole role = this.froleMapper.selectByPrimaryKey(id);
		return role;
	}

	public void saveObj(Frole obj) {
		this.froleMapper.insert(obj);
	}

	public void deleteObj(int id) {
		this.froleMapper.delete(id);
	}

	public void updateObj(Frole obj) {
		this.froleMapper.updateByPrimaryKey(obj);
	}

	public List<Frole> findByProperty(String name, Object value) {
		//TODO 待处理
		System.out.println("直接返回空");
		return null;
//		return this.froleMapper.findByProperty(name, value);
	}

	public List<Frole> findAll() {
		return this.froleMapper.selectAll();
	}

	public List<Frole> list(int firstResult, int maxResults,
			String filter,boolean isFY) {
		//TODO  待处理
		return this.froleMapper.selectAll();
	}
}