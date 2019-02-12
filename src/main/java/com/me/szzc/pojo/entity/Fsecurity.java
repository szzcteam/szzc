package com.me.szzc.pojo.entity;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * Fsecurity entity. @author MyEclipse Persistence Tools
 */
@Data
public class Fsecurity implements java.io.Serializable {

	// Fields

	private Long fid;
	private Fsecurity fsecurity;
	private String fdescription;
	private String fname;
	private Long fparentid;
	private Integer fpriority;
	private String furl;

	//角色id
	private Integer roleId = 0;


	private Set<Fsecurity> fsecurities = new HashSet<Fsecurity>(0);
	private Set<FroleSecurity> froleSecurities = new HashSet<FroleSecurity>(0);

	// Constructors





}