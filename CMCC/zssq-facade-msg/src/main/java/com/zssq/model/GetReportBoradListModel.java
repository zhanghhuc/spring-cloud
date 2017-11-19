package com.zssq.model;

public class GetReportBoradListModel {

	private Integer pageSize;
	
	private Integer pageNo;

	private String userCode;
	
	private String boradCode;
	
	
	public String getBoradCode() {
		return boradCode;
	}

	public void setBoradCode(String boradCode) {
		this.boradCode = boradCode;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	
}