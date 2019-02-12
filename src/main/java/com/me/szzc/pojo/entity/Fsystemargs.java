package com.me.szzc.pojo.entity;

import com.me.szzc.utils.HTMLSpirit;
import lombok.Data;

/**
 * Fsystemargs entity. @author MyEclipse Persistence Tools
 */
@Data
public class Fsystemargs implements java.io.Serializable {

	// Fields

	private Long fid;
	private String fkey;
	private String fvalue;
	private String fvalue_s;
	private String fdescription;
	private int version;
	private int ftype;
	private String furl;

	// Constructors

	/** default constructor */
	public Fsystemargs() {
	}


	public String getFvalue_s() {
		return HTMLSpirit.delHTMLTag(getFvalue());
	}


	public boolean getBoolean() {
		boolean flag = false;
		String value = this.getFvalue();
		if (value != null && "true".equalsIgnoreCase(value.trim())) {
			flag = true;
		}
		return flag;
	}

	public int getInteger() {
		int flag = 0;
		String value = this.getFvalue();
		if (value != null) {
			try {
				flag = Integer.parseInt(value.trim());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return flag;
	}

}