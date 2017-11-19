package com.zssq.auth.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

public class UserManageIsLeaderVo {
	
	@NotBlank(message = "{empty.message}")
	private String userCodes;
	
	@EnumType(allow = {"0","1"})
	private String isLeader;

	public String getUserCodes() {
		return userCodes;
	}

	public void setUserCodes(String userCodes) {
		this.userCodes = userCodes;
	}

	public String getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(String isLeader) {
		this.isLeader = isLeader;
	}

}
