package com.zssq.dao.pojo;

import com.zssq.pojo.BasePage;

public class SysOrgInfo extends BasePage {
	
	private static final long serialVersionUID = 1L;

	private Long id;

	/** 编码 */
    private String sysOrgCode;
    
    /** 上级编码 */
    private String parentCode;
    
    /** 所属行政组织编码 */
    private String manOrgCode;
    
    /** SMAP 同步原始编码 */
    private String srcCode;

    /** 名称 */
    private String sysOrgName;

    /** 全名 */
    private String sysOrgFullname;
    
    /** 组织类别 */
    private String sysOrgType;
    
    /** 组织排序 */
    private String sysOrgOrder;

    /** 组织状态 */
    private Byte isEnable;
    
    /** 租户编码 */
    private String saasTenantCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSysOrgCode() {
        return sysOrgCode;
    }

    public void setSysOrgCode(String sysOrgCode) {
        this.sysOrgCode = sysOrgCode == null ? null : sysOrgCode.trim();
    }

	public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }

    public String getManOrgCode() {
		return manOrgCode;
	}

	public void setManOrgCode(String manOrgCode) {
		this.manOrgCode = manOrgCode;
	}

	public String getSysOrgName() {
        return sysOrgName;
    }

    public void setSysOrgName(String sysOrgName) {
        this.sysOrgName = sysOrgName == null ? null : sysOrgName.trim();
    }

    public String getSysOrgFullname() {
        return sysOrgFullname;
    }

    public void setSysOrgFullname(String sysOrgFullname) {
        this.sysOrgFullname = sysOrgFullname == null ? null : sysOrgFullname.trim();
    }

	public String getSysOrgOrder() {
        return sysOrgOrder;
    }

    public void setSysOrgOrder(String sysOrgOrder) {
        this.sysOrgOrder = sysOrgOrder;
    }

    public Byte getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Byte isEnable) {
        this.isEnable = isEnable;
    }

	public String getSrcCode() {
		return srcCode;
	}

	public void setSrcCode(String srcCode) {
		this.srcCode = srcCode;
	}

	public String getSysOrgType() {
		return sysOrgType;
	}

	public void setSysOrgType(String sysOrgType) {
		this.sysOrgType = sysOrgType;
	}

	public String getSaasTenantCode() {
		return saasTenantCode;
	}

	public void setSaasTenantCode(String saasTenantCode) {
		this.saasTenantCode = saasTenantCode;
	}
}