package com.me.szzc.service;

import com.me.szzc.dao.FsecurityMapper;
import com.me.szzc.pojo.entity.Fsecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityService {

	@Autowired
	private FsecurityMapper fsecurityMapper;

	public Fsecurity findById(Long id) {
		return this.fsecurityMapper.selectByPrimaryKey(id);
	}

	public void saveObj(Fsecurity obj) {
		this.fsecurityMapper.insert(obj);
	}

	public void deleteObj(Long id) {
		this.fsecurityMapper.deleteByPrimaryKey(id);
	}

	public void updateObj(Fsecurity obj) {
		this.fsecurityMapper.updateByPrimaryKey(obj);
	}

	public List<Fsecurity> findByProperty(Fsecurity obj) {
		return this.fsecurityMapper.selectByProperty(obj);
	}

	public List<Fsecurity> findAll() {
		return this.fsecurityMapper.selectAll();
	}

	public List<Fsecurity> list(int firstResult, int maxResults,
			String filter,boolean isFY) {
		return this.fsecurityMapper.list(firstResult, maxResults, filter, isFY);
	}

	public Integer getAllCount(String filter) {
		return this.fsecurityMapper.getAllCount(filter);
	}
}