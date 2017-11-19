package com.zssq.filing.vo;

import org.hibernate.validator.constraints.NotBlank;

public class AddBlackVo {

	@NotBlank(message = "{empty.message}")
	private String userCode;

	@NotBlank(message = "{empty.message}")
	private String blackUserCode;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getBlackUserCode() {
		return blackUserCode;
	}

	public void setBlackUserCode(String blackUserCode) {
		this.blackUserCode = blackUserCode;
	}
	
}
