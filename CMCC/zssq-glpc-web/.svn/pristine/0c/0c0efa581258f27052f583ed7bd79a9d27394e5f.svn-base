package com.zssq.msg.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;

public class BoardMsgVO {

	@NotBlank(message = "{empty.message}")
	private String orgCode;
	
	@IntType(expression = ">0")
	private String pageSize;
	
	@IntType(expression = ">=0")
	private String pageNo;

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
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
