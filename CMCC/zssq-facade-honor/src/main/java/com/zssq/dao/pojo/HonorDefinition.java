package com.zssq.dao.pojo;

import java.io.Serializable;

public class HonorDefinition implements Serializable{
	
	private static final long serialVersionUID = -1941711494447337513L;

	private Long id;

    private String honorDefinitionCode;

    private Byte isDisable;

    private Byte isDelete;

    private String tenantCode;

    private String orgCode;

    private Byte orgLevel;

    private Long createTime;

    private Long modifyTime;

    private String remark;

    private String honorName;

    private Byte honorType;

    private String honorUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHonorDefinitionCode() {
        return honorDefinitionCode;
    }

    public void setHonorDefinitionCode(String honorDefinitionCode) {
        this.honorDefinitionCode = honorDefinitionCode == null ? null : honorDefinitionCode.trim();
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

    public String getHonorName() {
        return honorName;
    }

    public void setHonorName(String honorName) {
        this.honorName = honorName == null ? null : honorName.trim();
    }

    public Byte getHonorType() {
        return honorType;
    }

    public void setHonorType(Byte honorType) {
        this.honorType = honorType;
    }

    public String getHonorUrl() {
        return honorUrl;
    }

    public void setHonorUrl(String honorUrl) {
        this.honorUrl = honorUrl == null ? null : honorUrl.trim();
    }
}