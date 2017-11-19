package com.zssq.dao.pojo;

import java.io.Serializable;
import java.util.List;

public class SaasTenantInfo implements Serializable {
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String saasTenantCode;
    
    private List<String> saasTenantCodes;

    private String saasTenantName;

    private Long createTime;

    private Byte isEnable;
    
    private String sysAdminAccount;
    
    public List<String> getSaasTenantCodes() {
		return saasTenantCodes;
	}

	public void setSaasTenantCodes(List<String> saasTenantCodes) {
		this.saasTenantCodes = saasTenantCodes;
	}

	public String getSysAdminAccount() {
		return sysAdminAccount;
	}

	public void setSysAdminAccount(String sysAdminAccount) {
		this.sysAdminAccount = sysAdminAccount;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSaasTenantCode() {
        return saasTenantCode;
    }

    public void setSaasTenantCode(String saasTenantCode) {
        this.saasTenantCode = saasTenantCode == null ? null : saasTenantCode.trim();
    }

    public String getSaasTenantName() {
        return saasTenantName;
    }

    public void setSaasTenantName(String saasTenantName) {
        this.saasTenantName = saasTenantName == null ? null : saasTenantName.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Byte getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Byte isEnable) {
        this.isEnable = isEnable;
    }
}