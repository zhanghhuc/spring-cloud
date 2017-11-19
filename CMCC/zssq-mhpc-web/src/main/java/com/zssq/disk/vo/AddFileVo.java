package com.zssq.disk.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class AddFileVo implements Serializable{
	
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	@NotBlank(message = "{empty.message}")
	private String orgCode;
	
	@NotBlank(message = "{empty.message}")
	private String fileName;
	
	@NotBlank(message = "{empty.message}")
	private String fileSuffix;
	
	@NotBlank(message = "{empty.message}")
	private String parentCode;
	
	@NotBlank(message = "{empty.message}")
	private String fileUrl;
	
	@NotBlank(message = "{empty.message}")
	private String fileSize;
	

	

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSuffix() {
		return fileSuffix;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	
}