package com.zssq.auth.vo;

import org.hibernate.validator.constraints.NotBlank;

public class SmapOrgAddVo {

	/** SMAP同步到的组织编码 */
	@NotBlank(message = "{empty.message}")
	private String smapOrgCode;
	
	/** 清洗后内部系统使用的组织编码，将向该组织下添加下级组织 */
	@NotBlank(message = "{empty.message}")
	private String parentOrgCode;
	
	/** 是否为行政组织；0：否；1：是 */
	@NotBlank(message = "{empty.message}")
	private String isManager;

	public String getSmapOrgCode() {
		return smapOrgCode;
	}

	public void setSmapOrgCode(String smapOrgCode) {
		this.smapOrgCode = smapOrgCode;
	}

	public String getParentOrgCode() {
		return parentOrgCode;
	}

	public void setParentOrgCode(String parentOrgCode) {
		this.parentOrgCode = parentOrgCode;
	}

	public String getIsManager() {
		return isManager;
	}

	public void setIsManager(String isManager) {
		this.isManager = isManager;
	}
}
