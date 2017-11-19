package com.zssq.dao.pojo;

import java.io.Serializable;

public class HonorPraise implements Serializable{
	
	private static final long serialVersionUID = 6724185356209609970L;

	private Long id;

    private String honorPraiseCode;

    private Byte isDisable;

    private Byte isDelete;

    private String tenantCode;

    private String orgCode;

    private Byte orgLevel;

    private Long createTime;

    private Long modifyTime;

    private String remark;

    private String admirerCode;

    private String infoCode;

    private Byte infoType;
    
    private Byte operating;
    
    public Byte getOperating() {
		return operating;
	}

	public void setOperating(Byte operating) {
		this.operating = operating;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHonorPraiseCode() {
        return honorPraiseCode;
    }

    public void setHonorPraiseCode(String honorPraiseCode) {
        this.honorPraiseCode = honorPraiseCode == null ? null : honorPraiseCode.trim();
    }

    public Byte getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(Byte isDisable) {
        this.isDisable = isDisable;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode == null ? null : tenantCode.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public Byte getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(Byte orgLevel) {
        this.orgLevel = orgLevel;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getAdmirerCode() {
        return admirerCode;
    }

    public void setAdmirerCode(String admirerCode) {
        this.admirerCode = admirerCode == null ? null : admirerCode.trim();
    }

    public String getInfoCode() {
        return infoCode;
    }

    public void setInfoCode(String infoCode) {
        this.infoCode = infoCode == null ? null : infoCode.trim();
    }

    public Byte getInfoType() {
        return infoType;
    }

    public void setInfoType(Byte infoType) {
        this.infoType = infoType;
    }
}