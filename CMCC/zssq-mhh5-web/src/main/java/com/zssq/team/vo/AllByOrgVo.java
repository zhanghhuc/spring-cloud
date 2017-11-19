package com.zssq.team.vo;

import org.hibernate.validator.constraints.NotBlank;

public class AllByOrgVo {

	@NotBlank(message = "{empty.message}")
	private String orgCode;

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
}
