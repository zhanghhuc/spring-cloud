package com.zssq.pojo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class Login implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 员工 uid */
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	/** 登录终端标识：mhpc/mhh5 */
	@NotBlank(message = "{empty.message}")
	private String app;

	/** 员工登录密码 */
	private String uidPassword;
	
	/** 身份票据 */
	private String token;
	
	/** 认证方式：static/dynamic */
	private String authType;

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

	public String getUidPassword() {
		return uidPassword;
	}

	public void setUidPassword(String uidPassword) {
		this.uidPassword = uidPassword;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

}
