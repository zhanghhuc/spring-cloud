package com.zssq.auth.vo;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 封装接口请求参数，适用于 SysOrgController.topTree
 * 
 * @since  JDK 1.7
 * @author 赵翊
 */
public class StaticTreeVo {

	/** 用户编码 */
	private String userCode;
	
	/** 租户编码 */
	@NotBlank(message = "{empty.message}")
	private String saasTenantCode;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getSaasTenantCode() {
		return saasTenantCode;
	}

	public void setSaasTenantCode(String saasTenantCode) {
		this.saasTenantCode = saasTenantCode;
	}
}
