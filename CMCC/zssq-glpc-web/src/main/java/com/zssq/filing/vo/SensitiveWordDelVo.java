package com.zssq.filing.vo;

import org.hibernate.validator.constraints.NotBlank;

public class SensitiveWordDelVo {
	
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	@NotBlank(message = "{empty.message}")
	private String token;
	
	@NotBlank(message = "{empty.message}")
	private String sensitiveCode;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSensitiveCode() {
		return sensitiveCode;
	}

	public void setSensitiveCode(String sensitiveCode) {
		this.sensitiveCode = sensitiveCode;
	}
	
	
}
