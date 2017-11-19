package com.zssq.msg.vo;

import org.hibernate.validator.constraints.NotBlank;

public class DelPrivateLetterVO {

	
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	@NotBlank(message = "{empty.message}")
	private String letterUserCode;

	
	
	public String getLetterUserCode() {
		return letterUserCode;
	}

	public void setLetterUserCode(String letterUserCode) {
		this.letterUserCode = letterUserCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	
	
}
