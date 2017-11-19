package com.zssq.auth.vo;

import org.hibernate.validator.constraints.NotBlank;

public class DeputSaveVo {

	@NotBlank(message = "{empty.message}")
	private String leaderCode;
	
	@NotBlank(message = "{empty.message}")
	private String staffCode;
	
	@NotBlank(message = "{empty.message}")
	private String apps;
	
	private String userCode;

	public String getLeaderCode() {
		return leaderCode;
	}

	public void setLeaderCode(String leaderCode) {
		this.leaderCode = leaderCode;
	}

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public String getApps() {
		return apps;
	}

	public void setApps(String apps) {
		this.apps = apps;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
}
