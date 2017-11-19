package com.zssq.auth.vo;

import org.hibernate.validator.constraints.NotBlank;

public class LeadersVo {
	
	@NotBlank(message = "{empty.message}")
	private String deputyApp;
	
	private String userCode;

	public String getDeputyApp() {
		return deputyApp;
	}

	public void setDeputyApp(String deputyApp) {
		this.deputyApp = deputyApp;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

}
