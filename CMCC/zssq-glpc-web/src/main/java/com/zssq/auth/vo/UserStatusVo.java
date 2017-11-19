package com.zssq.auth.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

public class UserStatusVo {
	
	@NotBlank(message = "{empty.message}")
	private String userCodes;
	
	@EnumType(allow = {"0","1"})
	private String userStatus;

	public String getUserCodes() {
		return userCodes;
	}

	public void setUserCodes(String userCodes) {
		this.userCodes = userCodes;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

}
