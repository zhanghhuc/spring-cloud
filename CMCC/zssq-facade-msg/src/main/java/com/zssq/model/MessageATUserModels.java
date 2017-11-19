package com.zssq.model;

import java.io.Serializable;

/**
 * 
 * @ClassName: MessageATModels  
 * @Description: AT用户对象model
 * @author YDB  
 * @date 2017年4月5日  
 *
 */
public class MessageATUserModels implements Serializable{
	
	
	private String userCode;
	
	private String orgCode;
	
	private String tenantCode;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}


	
	

}
