package com.zssq.model;

import java.io.Serializable;

public class CopyFileVO  implements Serializable{

	private String fileCode;
	
	//新文件目录
	private String copyFileCode;
	
	private String parentCode;

	private String fileName;
	
	private String fileSuffix;
	
	
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

	public String getCopyFileCode() {
		return copyFileCode;
	}

	public void setCopyFileCode(String copyFileCode) {
		this.copyFileCode = copyFileCode;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	
	
}
