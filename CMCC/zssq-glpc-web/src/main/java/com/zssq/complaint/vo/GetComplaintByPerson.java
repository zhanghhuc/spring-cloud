package com.zssq.complaint.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class GetComplaintByPerson implements Serializable{
	
	private static final long serialVersionUID = 8546763528888831658L;
	
	@NotBlank(message = "{empty.message}")
	private String complainantCode;
	
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	@NotBlank(message = "{empty.message}")
	private String token;
	
	public String getComplainantCode() {
		return complainantCode;
	}
	public void setComplainantCode(String complainantCode) {
		this.complainantCode = complainantCode;
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
