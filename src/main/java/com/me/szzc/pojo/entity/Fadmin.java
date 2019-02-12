package com.me.szzc.pojo.entity;


import com.me.szzc.enums.AdminStatusEnum;
import com.me.szzc.utils.DateHelper;
import lombok.Data;

import java.util.Date;

/**
 * Fadmin entity. @author MyEclipse Persistence Tools
 */
@Data
public class Fadmin implements java.io.Serializable {

	// Fields

	/**状态描述**/
	private String fstatus_s;

	private Integer fid;

	private Integer fstatus;

	private Integer froleid;

	/**角色名称**/
	private String froleName;

	private String fname;

	private String fpassword;

	private Date fcreatetime;

	private String createTimeStr;

	private String fgoogleauthenticator;

	private String fgoogleurl;

	private Boolean fgooglebind;

	private Boolean fopengooglevalidate;

	private Boolean fgooglevalidate;

	private Integer version;

	private String salt;

	private String ftelephone;

	public String getFstatus_s() {
		if (this.fstatus == AdminStatusEnum.FORBBIN_VALUE) {
			this.setFstatus_s(AdminStatusEnum
					.getEnumString(AdminStatusEnum.FORBBIN_VALUE));
		} else {
			this.setFstatus_s(AdminStatusEnum
					.getEnumString(AdminStatusEnum.NORMAL_VALUE));
		}
		return fstatus_s;
	}

	public void setFstatus_s(String fstatus_s) {
		this.fstatus_s = fstatus_s;
	}

	public String getCreateTimeStr() {
		return DateHelper.date2String(this.fcreatetime, DateHelper.DateFormatType.YearMonthDay_HourMinuteSecond);
	}
}