package com.zssq.model;

import java.io.Serializable;

public class FileListAllVO implements Serializable{

	private String fileCode;
	
	private String parentCode;

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
	
}