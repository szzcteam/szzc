package com.me.szzc.service;

import com.me.szzc.dao.FsystemargsMapper;
import com.me.szzc.dao.FwebbaseinfoMapper;
import com.me.szzc.pojo.entity.Fsystemargs;
import com.me.szzc.pojo.entity.Fwebbaseinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FrontSystemArgsService {

	@Autowired
	private FsystemargsMapper fsystemargsMapper ;
	@Autowired
	private FwebbaseinfoMapper fwebbaseinfoMapper ;
	
	public String getSystemArgs(Fsystemargs fsystemargs){
		String value = null ;
		List<Fsystemargs> list = this.fsystemargsMapper.selectByProperty(fsystemargs) ;
		if(list.size()>0){
			value = list.get(0).getFvalue() ;
		}
		return value ;
	}
	
	public Map<String, String> findAllMap(){
		Map<String, String> map = new HashMap<String, String>() ;
		List<Fsystemargs> list = this.fsystemargsMapper.selectAll() ;
		for (Fsystemargs fsystemargs : list) {
			map.put(fsystemargs.getFkey(), fsystemargs.getFvalue()) ;
		}
		return map ;
	}
	
	public Fwebbaseinfo findFwebbaseinfoById(Long id){
		return this.fwebbaseinfoMapper.selectByPrimaryKey(id) ;
	}
}
