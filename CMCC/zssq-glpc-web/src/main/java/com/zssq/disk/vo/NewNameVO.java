package com.zssq.disk.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class NewNameVO implements Serializable{

	@NotBlank(message = "{empty.message}")
	private String newName;
	
	@NotBlank(message = "{empty.message}")
	private	String fileCode;
	
	private String suffix;
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	@NotBlank(message = "{empty.message}")
	private String parentCode;
	
	
	

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public String getFileCode() {
		return fileCode;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

}