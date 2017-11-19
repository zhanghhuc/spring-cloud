package com.zssq.disk.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class MoveVO implements Serializable{

	
	@NotBlank(message = "{empty.message}")
	private String fileCode;
	
	@NotBlank(message = "{empty.message}")
	private String parentCode;
	
	@NotBlank(message = "{empty.message}")
	private String newFolderCode;
	
	@NotBlank(message = "{empty.message}")
	private String fileName;
	
	private String fileSuffix;
	
	@NotBlank(message = "{empty.message}")
	private String userCode;

	public String getFileCode() {
		return fileCode;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getNewFolderCode() {
		return newFolderCode;
	}

	public void setNewFolderCode(String newFolderCode) {
		this.newFolderCode = newFolderCode;
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

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
}