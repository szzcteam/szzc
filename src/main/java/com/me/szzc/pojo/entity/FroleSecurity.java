package com.me.szzc.pojo.entity;

// default package


import lombok.Data;

/**
 * FroleSecurity entity. @author MyEclipse Persistence Tools
 */
@Data
public class FroleSecurity implements java.io.Serializable {

	// Fields

	private Integer fid;

	/**fsecurityid 对应的菜单资源详情**/
	private Fsecurity fsecurity;
	private Frole frole;

	private Long fsecurityid;

	private Long froleid;

	// Constructors

	/** default constructor */
	public FroleSecurity() {
	}

	/** full constructor */
	public FroleSecurity(Fsecurity fsecurity, Frole frole) {
		this.fsecurity = fsecurity;
		this.frole = frole;
	}


}