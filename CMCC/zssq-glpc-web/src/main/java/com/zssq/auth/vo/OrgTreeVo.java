package com.zssq.auth.vo;

import org.hibernate.validator.constraints.NotBlank;

public class OrgTreeVo {

	@NotBlank(message = "{empty.message}")
	private String userCode;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
}
