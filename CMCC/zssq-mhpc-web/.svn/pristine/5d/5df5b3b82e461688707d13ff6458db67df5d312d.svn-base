package com.zssq.disk.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class CopyVO implements Serializable{

	@NotBlank(message = "{empty.message}")
	private String fileCode;
	
	//新文件目录
	@NotBlank(message = "{empty.message}")
	private String copyFolderCode;
	@NotBlank(message = "{empty.message}")
	private String parentCode;
	@NotBlank(message = "{empty.message}")
	private String fileName;
	
	private String fileSuffix;

	public String getFileCode() {
		return fileCode;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}



	public String getCopyFolderCode() {
		return copyFolderCode;
	}

	public void setCopyFolderCode(String copyFolderCode) {
		this.copyFolderCode = copyFolderCode;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
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
	
}