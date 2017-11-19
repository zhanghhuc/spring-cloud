package com.zssq.model;

import java.io.Serializable;

public class ReportFileVO implements Serializable{
	
	private String fileCode;
	
	private Long editTime;
	
	private String editUserCode;
	
	private String reportType;

	public String getFileCode() {
		return fileCode;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}
	

	public Long getEditTime() {
		return editTime;
	}

	public void setEditTime(Long editTime) {
		this.editTime = editTime;
	}

	public String getEditUserCode() {
		return editUserCode;
	}

	public void setEditUserCode(String editUserCode) {
		this.editUserCode = editUserCode;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	} 
	
}