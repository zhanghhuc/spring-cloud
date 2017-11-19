package com.zssq.auth.vo;

import org.hibernate.validator.constraints.NotBlank;

public class SubOrgVo {

	@NotBlank(message = "{empty.message}")
	private String orgCode;

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
}
