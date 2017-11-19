package com.zssq.msg.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;

public class PrivateLetterInfoListVO {

	@NotBlank(message = "{empty.message}")
	private String userCode;
	@IntType(expression = ">0")
	private String pageSize;
	
	@IntType(expression = ">=0")
	private String PageNo;

	@NotBlank(message = "{empty.message}")
	private String letterUserCode;
	
	
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getLetterUserCode() {
		return letterUserCode;
	}
	public void setLetterUserCode(String letterUserCode) {
		this.letterUserCode = letterUserCode;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getPageNo() {
		return PageNo;
	}
	public void setPageNo(String pageNo) {
		PageNo = pageNo;
	}
	
	
	
}
