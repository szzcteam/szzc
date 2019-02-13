package com.me.szzc.service;

import com.me.szzc.dao.FsystemoperatorlogMapper;
import com.me.szzc.pojo.entity.Fsystemoperatorlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogService {
	@Autowired
	private FsystemoperatorlogMapper fsystemoperatorlogMapper;

	public Fsystemoperatorlog findById(Long id) {
		return this.fsystemoperatorlogMapper.selectByPrimaryKey(id);
	}

	public void saveObj(Fsystemoperatorlog obj) {
		this.fsystemoperatorlogMapper.insert(obj);
	}

	public void deleteObj(Long id) {
		this.fsystemoperatorlogMapper.deleteByPrimaryKey(id);
	}

	public void updateObj(Fsystemoperatorlog obj) {
		this.fsystemoperatorlogMapper.updateByPrimaryKey(obj);
	}

	public List<Fsystemoperatorlog> findByProperty(Fsystemoperatorlog record) {
		return this.fsystemoperatorlogMapper.findByProperty(record);
	}

	public List<Fsystemoperatorlog> findAll() {
		return this.fsystemoperatorlogMapper.selectAll();
	}

	public List<Fsystemoperatorlog> list(int firstResult, int maxResults,
			String filter,boolean isFY) {
		return this.fsystemoperatorlogMapper.list(null);
	}

	public Integer getAllCount(String filter) {
		return this.fsystemoperatorlogMapper.getAllCount(filter);
	}
}