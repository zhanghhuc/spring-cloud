package com.zssq.complaint.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;
import com.zssq.annotation.validation.IntType;

public class GetPeopleList implements Serializable{
	
	private static final long serialVersionUID = 2952019800622419136L;

	private String complainantName;
	
	@EnumType(required=false ,allow={"1","2","3","4","5"})
	private String reason;
	
	@NotBlank(message = "{empty.message}")
	private String complaintCode;
	
	@IntType(expression=">0")
	private String pageSize;
	
	@IntType(expression=">=0")
	private String pageNo;
	
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	@NotBlank(message = "{empty.message}")
	private String token;

	public String getComplainantName() {
		return complainantName;
	}

	public void setComplainantName(String complainantName) {
		this.complainantName = complainantName;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getComplaintCode() {
		return complaintCode;
	}

	public void setComplaintCode(String complaintCode) {
		this.complaintCode = complaintCode;
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

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
