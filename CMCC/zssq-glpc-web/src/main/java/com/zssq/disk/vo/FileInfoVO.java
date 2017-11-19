package com.zssq.disk.vo;

import org.hibernate.validator.constraints.NotBlank;

public class FileInfoVO {
	@NotBlank(message = "{empty.message}")
	private String fileCode;

	public String getFileCode() {
		return fileCode;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}
	
	
	
}
