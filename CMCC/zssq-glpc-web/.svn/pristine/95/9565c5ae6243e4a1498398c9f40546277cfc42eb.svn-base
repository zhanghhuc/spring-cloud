package com.zssq.disk.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class CreateVO implements Serializable{
	
	
	@NotBlank(message = "{empty.message}")
	private String newFileName;
	
	@NotBlank(message = "{empty.message}")
	private String parentCode;
	
	@NotBlank(message = "{empty.message}")
	private String userCode;
	


	public String getNewFileName() {
		return newFileName;
	}

	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	
}