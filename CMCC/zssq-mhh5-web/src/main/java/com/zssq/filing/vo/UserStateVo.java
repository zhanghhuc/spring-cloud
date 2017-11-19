package com.zssq.filing.vo;

import org.hibernate.validator.constraints.NotBlank;

public class UserStateVo {
	
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	@NotBlank(message = "{empty.message}")
	private String otherCode;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getOtherCode() {
		return otherCode;
	}

	public void setOtherCode(String otherCode) {
		this.otherCode = otherCode;
	}
	
}
