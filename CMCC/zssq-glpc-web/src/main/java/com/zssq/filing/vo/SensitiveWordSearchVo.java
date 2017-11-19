package com.zssq.filing.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;
import com.zssq.annotation.validation.IntType;

public class SensitiveWordSearchVo {

	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	@NotBlank(message = "{empty.message}")
	private String token;
	
	@EnumType(allow = {"0","1"})
	private String sensitiveType;
	
	private String word;
	
	@IntType(expression = ">=0")
	private String pageNo;
	
	@IntType(expression = ">=0")
	private String pageSize;

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

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	
}
