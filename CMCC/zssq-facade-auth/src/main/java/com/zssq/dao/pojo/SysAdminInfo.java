package com.zssq.dao.pojo;

import java.io.Serializable;
import java.util.List;

public class SysAdminInfo implements Serializable {

	private static final long serialVersionUID = 883424698252874677L;
	
	private Long id;
	private String adminAccount;
	private String adminPass;
	private Byte adminType;
	private String tenantCode;
	private Long createTime;
	private Long modifyTime;
	private Byte isEnable;
	private List<String> tenantCodes;
	
	private String token;
	private List<String> permissions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdminAccount() {
		return adminAccount;
	}

	public void setAdminAccount(String adminAccount) {
		this.adminAccount = adminAccount;
	}

	public String getAdminPass() {
		return adminPass;
	}

	public void setAdminPass(String adminPass) {
		this.adminPass = adminPass;
	}

	public Byte getAdminType() {
		return adminType;
	}

	public void setAdminType(Byte adminType) {
		this.adminType = adminType;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Byte getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Byte isEnable) {
		this.isEnable = isEnable;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	public List<String> getTenantCodes() {
		return tenantCodes;
	}

	public void setTenantCodes(List<String> tenantCodes) {
		this.tenantCodes = tenantCodes;
	}
}
