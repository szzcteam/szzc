package com.me.szzc.service;

import com.me.szzc.dao.FadminMapper;
import com.me.szzc.dao.FroleMapper;
import com.me.szzc.pojo.entity.Fadmin;
import com.me.szzc.pojo.entity.Frole;
import com.me.szzc.utils.Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdminService {
	@Autowired
	private FadminMapper fadminMapper;

	@Autowired
	private FroleMapper froleMapper;

	public Fadmin findById(Long id) {
		return this.fadminMapper.selectByPrimaryKey(id);
	}

	public void saveObj(Fadmin obj) {
		this.fadminMapper.insert(obj);
	}

	public void deleteObj(Long id) {
		this.fadminMapper.deleteByPrimaryKey(id);
	}

	public void updateObj(Fadmin obj) {
		this.fadminMapper.updateByPrimaryKeySelective(obj);
	}

	public List<Fadmin> findByName(String name) {
		Fadmin fadmin = new Fadmin();
		fadmin.setFname(name);
		List<Fadmin> all = this.fadminMapper.findByProperty(fadmin);
		return all;
	}

	public List<Fadmin> findByProperty(Fadmin fadmin) {
		List<Fadmin> all = this.fadminMapper.findByProperty(fadmin);
//		for (Fadmin fadmin : all) {
//			if(fadmin.getFrole() != null){
				//TODO  原懒加载，待修改
//				fadmin.getFrole().getFname();
//			}
//		}
		return all;
	}

	public List<Fadmin> findAll() {
		return this.fadminMapper.findAll();
	}

	public List<Fadmin> list(int firstResult, int maxResults,
			String filter,boolean isFY) {
		List<Fadmin> list =  fadminMapper.list(firstResult, maxResults, filter, isFY);
		if(list != null && list.size() >0) {
			for(Fadmin admin : list) {
				if(admin.getFroleid() == null) {
					continue;
				}
				Frole role = froleMapper.selectByPrimaryKey(admin.getFroleid());
				if(role == null || StringUtils.isBlank(role.getFname())) {
					continue;
				}

				admin.setFroleName(role.getFname());
			}
		}
		return list;
	}
	

	
/*	public boolean updateDatabase(String ip,String port,String dataBase,String name,String password,String dir,String fileName){
		boolean flag = this.fadminDAO.backDatabase(ip,port,dataBase,name,password,dir,fileName);
		return flag;
	}*/
	
	public List<Fadmin> login(Fadmin fadmin) throws Exception{
		return this.fadminMapper.login(fadmin);
	}
	
	public int getAllCount(String filter){
		return this.fadminMapper.getAllCount(filter);
	}
	
	/*public double getSQLValue(String filter){
		return this.fadminDAO.getSQLValue(filter);
	}
	
	public Map getSQLValue1(String filter){
		return this.fadminDAO.getSQLValue1(filter);
	}
	
	public double getSQLValue2(String filter){
		return this.fadminDAO.getSQLValue2(filter);
	}
	
	public void updateSQL(String filter){
		this.fadminDAO.updateSQL(filter);
	}*/
}