package com.zssq.filing.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

public class SensitiveWordAddVo {
	
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	@NotBlank(message = "{empty.message}")
	private String token;
	
	@EnumType(allow = {"0","1"})
	private String sensitiveType;
	
	@NotBlank(message = "{empty.message}")
	private String word;

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

	public String getSensitiveType() {
		return sensitiveType;
	}

	public void setSensitiveType(String sensitiveType) {
		this.sensitiveType = sensitiveType;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
	
	
}
