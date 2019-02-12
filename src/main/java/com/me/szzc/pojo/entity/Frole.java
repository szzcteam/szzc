package com.me.szzc.pojo.entity;
// default package

import lombok.Data;
import java.util.HashSet;
import java.util.Set;

/**
 * Frole entity. @author MyEclipse Persistence Tools
 */
@Data
public class Frole implements java.io.Serializable {

	// Fields

	private Integer fid;
	private String fdescription;
	private String fname;

	private int fstatus;

	private Set<FroleSecurity> froleSecurities = new HashSet<FroleSecurity>(0);
	private Set<Fadmin> fadmins = new HashSet<Fadmin>(0);

	// Constructors

	/** default constructor */
	public Frole() {
	}

	/** minimal constructor */
	public Frole(String fname) {
		this.fname = fname;
	}

	/** full constructor */
	public Frole(String fdescription, String fname,
			Set<FroleSecurity> froleSecurities) {
		this.fdescription = fdescription;
		this.fname = fname;
		this.froleSecurities = froleSecurities;
	}


}