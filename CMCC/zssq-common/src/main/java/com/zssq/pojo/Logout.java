package com.zssq.pojo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class Logout implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "{empty.message}")
	private String userCode;
	@NotBlank(message = "{empty.message}")
	private String app;
	
	private String token;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

	


	
	

}
