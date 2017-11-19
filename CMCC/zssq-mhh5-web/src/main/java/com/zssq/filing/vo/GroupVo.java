package com.zssq.filing.vo;

import org.hibernate.validator.constraints.NotBlank;

public class GroupVo {

	@NotBlank(message = "{empty.message}")
	private String groupName;
	
	private String userCode;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
}
