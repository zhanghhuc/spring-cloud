package com.zssq.auth.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

public class TenantEnableVo {

	@NotBlank(message = "{empty.message}")
	private String tenantList;
	
	@EnumType(allow = {"0","1"})
	private String isEnable;

	public String getTenantList() {
		return tenantList;
	}

	public void setTenantList(String tenantList) {
		this.tenantList = tenantList;
	}

	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}
	
}
