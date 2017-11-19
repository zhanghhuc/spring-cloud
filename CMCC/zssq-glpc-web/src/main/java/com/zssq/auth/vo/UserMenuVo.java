package com.zssq.auth.vo;

import org.hibernate.validator.constraints.NotBlank;

public class UserMenuVo {
	
	@NotBlank(message = "{empty.message}")
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
