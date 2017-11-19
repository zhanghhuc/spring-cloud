package com.zssq.auth.vo;

import org.hibernate.validator.constraints.NotBlank;

public class DeputInfoVo {

	@NotBlank( message = "{empty.message}")
	private String deputyCode;

	public String getDeputyCode() {
		return deputyCode;
	}

	public void setDeputyCode(String deputyCode) {
		this.deputyCode = deputyCode;
	}
}
