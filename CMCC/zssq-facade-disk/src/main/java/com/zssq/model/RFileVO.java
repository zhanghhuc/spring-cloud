package com.zssq.model;

import java.io.Serializable;

public class RFileVO implements Serializable{

	private String fileCode;
	
	private String orgCode;
	
	private String editUserCode;
	
	private Long 	editTime;
	


	
	
	public String getEditUserCode() {
		return editUserCode;
	}

	public void setEditUserCode(String editUserCode) {
		this.editUserCode = editUserCode;
	}

	public Long getEditTime() {
		return editTime;
	}

	public void setEditTime(Long editTime) {
		this.editTime = editTime;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getFileCode() {
		return fileCode;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}

	
	
}
