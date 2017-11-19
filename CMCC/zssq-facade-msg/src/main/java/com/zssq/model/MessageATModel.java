package com.zssq.model;

import java.io.Serializable;

public class MessageATModel implements Serializable{

	private String userCode; //查询用户code
	private Integer pageSize;
	private Integer pageNo;
	
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
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
	
	
	
	
	
}
