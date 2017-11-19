package com.zssq.auth.vo;

import org.hibernate.validator.constraints.NotBlank;

public class UserIntroVo {

	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	private String intro;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
	
}
