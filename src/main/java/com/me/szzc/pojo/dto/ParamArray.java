package com.me.szzc.pojo.dto;

import com.me.szzc.pojo.entity.Fadmin;
import com.me.szzc.pojo.entity.Fwebbaseinfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ParamArray {
	private Fadmin fadmin;
	private MultipartFile filedata;
	private Fwebbaseinfo fwebbaseinfo;
//	private Fuser fuser ;



}
