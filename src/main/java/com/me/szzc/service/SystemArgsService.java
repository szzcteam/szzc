package com.me.szzc.service;

import com.me.szzc.dao.FsystemargsMapper;
import com.me.szzc.pojo.entity.Fsystemargs;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SystemArgsService {
	@Autowired
	private FsystemargsMapper fsystemargsMapper;

	public Fsystemargs findById(Long id) {
		return this.fsystemargsMapper.selectByPrimaryKey(id);
	}

	public void saveObj(Fsystemargs obj) {
		obj.setFtype(1);
		this.fsystemargsMapper.insertSelective(obj);
	}

	public void deleteObj(Long id) {
		this.fsystemargsMapper.deleteByPrimaryKey(id);
	}

	public void updateObj(Fsystemargs obj) {
		this.fsystemargsMapper.updateByPrimaryKeySelective(obj);
	}

	public List<Fsystemargs> findByProperty(Fsystemargs record) {
		return this.fsystemargsMapper.selectByProperty(record);
	}

	public List<Fsystemargs> findAll() {
		return this.fsystemargsMapper.selectAll();
	}

	public List<Fsystemargs> list(int firstResult, int maxResults,
			String filter,boolean isFY) {
		return this.fsystemargsMapper.list(firstResult, maxResults, filter, isFY);
	}


	public Integer getAllCount(String filter) {
		return this.fsystemargsMapper.getAllCount(filter);
	}

	public String getValue(String key){
		String ret = this.fsystemargsMapper.selectValue(key) ;
		if(StringUtils.isBlank(ret)) {
			ret = "";
		}
		return ret ;
	}


	public List<Fsystemargs> selectByLike(String keyword) {
		return this.fsystemargsMapper.selectByLike(keyword);
	}
}