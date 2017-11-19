package com.zssq.dao.vo;

import com.zssq.pojo.BasePage;

public class SysOrgInfoVo extends BasePage{

	private static final long serialVersionUID = 1L;
	//	sysOrgCode
//	parentCode
//	srcCode
//	sysOrgName
//	sysOrgFullname
//	sysOrgType
//	sysOrgOrder
//	isEnable
	//组织机构编码
	private String sysOrgCode;
	//上级组织编码
	private String parentCode;
	//原始组织编号
	private String srcCode;
	//组织机构名称
	private String sysOrgName;
	//组织机构全称
	private String sysOrgFullname;
	//组织类别编号
	private String sysOrgType;
	//组织机构行政排序
	private String sysOrgOrder;
	//是否启用
	private String isEnable;
	public String getSysOrgCode() {
		return sysOrgCode;
	}
	public void setSysOrgCode(String sysOrgCode) {
		this.sysOrgCode = sysOrgCode;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getSrcCode() {
		return srcCode;
	}
	public void setSrcCode(String srcCode) {
		this.srcCode = srcCode;
	}
	public String getSysOrgName() {
		return sysOrgName;
	}
	public void setSysOrgName(String sysOrgName) {
		this.sysOrgName = sysOrgName;
	}
	public String getSysOrgFullname() {
		return sysOrgFullname;
	}
	public void setSysOrgFullname(String sysOrgFullname) {
		this.sysOrgFullname = sysOrgFullname;
	}
	public String getSysOrgType() {
		return sysOrgType;
	}
	public void setSysOrgType(String sysOrgType) {
		this.sysOrgType = sysOrgType;
	}
	public String getSysOrgOrder() {
		return sysOrgOrder;
	}
	public void setSysOrgOrder(String sysOrgOrder) {
		this.sysOrgOrder = sysOrgOrder;
	}
	public String getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}
	
	

}
