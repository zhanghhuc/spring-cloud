package com.zssq.disk.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class DelFileVO implements Serializable{
	
	@NotBlank(message = "{empty.message}")
	private String fileCode;
	
	@NotBlank(message = "{empty.message}")
	private String userCode;

	public String getFileCode() {
		return fileCode;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}


	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	
	
}