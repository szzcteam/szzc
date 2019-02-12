package com.me.szzc.init;

import com.me.szzc.service.AdminService;
import com.me.szzc.service.FrontSystemArgsService;
import com.me.szzc.utils.CustomizedPropertyConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

public class ConstantMap {
	private static final Logger log = LoggerFactory.getLogger(ConstantMap.class);
	@Autowired
	private FrontSystemArgsService frontSystemArgsService ;

	@Autowired
	private AdminService adminService;

	private static Map<String, Object> map = new HashMap<String, Object>() ;

	public void init(){
		log.info("Init SystemArgs ==> ConstantMap.") ;
		Map<String, String> tMap = this.frontSystemArgsService.findAllMap() ;
		for (Map.Entry<String, String> entry : tMap.entrySet()) {
			this.put(entry.getKey(), entry.getValue()) ;
		}

		map.put("Domain", CustomizedPropertyConfigurer.getContextProperty("Domain"));

		map.put("webinfo", this.frontSystemArgsService.findFwebbaseinfoById(1L)) ;
	}

	public Map<String, Object> getMap(){
		return this.map ;
	}
	
	public synchronized void put(String key,Object value){
		log.info("ConstantMap put key:"+key+",value:"+value+".") ;
		map.put(key, value) ;
	}


	
	public static Object get(String key){
		return map.get(key) ;
	}
	
	public static String getString(String key){
		return (String)map.get(key) ;
	}
}
