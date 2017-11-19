package com.zssq.auth.vo;

import com.zssq.annotation.validation.IntType;

public class GetUserByUserNameVo {
	private String userName;
	@IntType(expression = ">=0")
	private String pageNo;
	@IntType(expression = ">0")
	private String pageSize;
	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
