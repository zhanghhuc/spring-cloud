package com.zssq.model;

import java.io.Serializable;

public class MoveFileVO  implements Serializable{
	
	private String fileCode;
	
	private String parentCode;
	
	private String newFolderCode;

	private String fileName;
	
	private String fileSuffix;
	
	private String userCode;
	
	private Long editTime;
	
	
	
	
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Long getEditTime() {
		return editTime;
	}

	public void setEditTime(Long editTime) {
		this.editTime = editTime;
	}

	public String getFileSuffix() {
		return fileSuffix;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

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
	
}