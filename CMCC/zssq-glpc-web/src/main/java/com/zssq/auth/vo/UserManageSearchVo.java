package com.zssq.auth.vo;

import com.zssq.annotation.validation.IntType;

public class UserManageSearchVo {

	private String userName;
	
	private String userCodeSearch;
	
	private String phone;
	
	private String userCode;
	
//	@NotBlank(message = "{empty.message}")
//	private String orgCode;
	
	@IntType(expression = ">0")
	private String pageSize;
	
	@IntType(expression = ">=0")
	private String pageNo;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCodeSearch() {
		return userCodeSearch;
	}

	public void setUserCodeSearch(String userCodeSearch) {
		this.userCodeSearch = userCodeSearch;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

//	public String getOrgCode() {
//		return orgCode;
//	}
//
//	public void setOrgCode(String orgCode) {
//		this.orgCode = orgCode;
//	}

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

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
}
