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
		List<Fsecurity> list = this.fsecurityMapper.list(firstResult, maxResults, filter, isFY);
		if(list == null || list.size() == 0) {
			return list;
		}

		for(Fsecurity s : list) {
			s.setFsecurity(this.fsecurityMapper.selectByPrimaryKey(s.getFparentid()));
		}
		return list;
	}

	public Integer getAllCount(String filter) {
		return this.fsecurityMapper.getAllCount(filter);
	}
}