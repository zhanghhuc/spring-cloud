package com.zssq.msg.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;

public class GetBoradReportVO {
	
	@NotBlank(message = "{empty.message}")
	private String boradCode;
	
	@IntType(expression = ">0")
	private String pageSize;
	
	@IntType(expression = ">=0")
	private String pageNo;
	
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getBoradCode() {
		return boradCode;
	}

	public void setBoradCode(String boradCode) {
		this.boradCode = boradCode;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	

}