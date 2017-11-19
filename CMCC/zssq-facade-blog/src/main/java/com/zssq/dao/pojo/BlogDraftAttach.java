package com.zssq.dao.pojo;

import java.io.Serializable;

/**
 * 
 * @ClassName: BlogDraftAttach  
 * @Description: 博客草稿附件  
 * @author ZKZ  
 * @date 2017年3月24日  
 *
 */
public class BlogDraftAttach implements Serializable {
        
	private static final long serialVersionUID = 1L;
	
	private Long id = 0L;
    private String draftAttachCode = "";
    private String tenantCode = "";
    private String orgCode = "";
    private Long createTime = 0L;
    private Long modifyTime = 0L;
    private String remark = "";
    private String draftCode = "";
    private String draftAttachUrl = "";
    private String draftAttachOldName = "";
    private String draftAttachOldExt = "";
    private String draftAttachNewName = "";
    private String draftAttachNewExt = "";
    private Byte draftAttachClass = 0;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDraftAttachCode() {
        return draftAttachCode;
    }

    public void setDraftAttachCode(String draftAttachCode) {
        this.draftAttachCode = draftAttachCode == null ? null : draftAttachCode.trim();
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

    public String getDraftCode() {
        return draftCode;
    }

    public void setDraftCode(String draftCode) {
        this.draftCode = draftCode == null ? null : draftCode.trim();
    }

    public String getDraftAttachUrl() {
        return draftAttachUrl;
    }

    public void setDraftAttachUrl(String draftAttachUrl) {
        this.draftAttachUrl = draftAttachUrl == null ? null : draftAttachUrl.trim();
    }

    public String getDraftAttachOldName() {
        return draftAttachOldName;
    }

    public void setDraftAttachOldName(String draftAttachOldName) {
        this.draftAttachOldName = draftAttachOldName == null ? null : draftAttachOldName.trim();
    }

    public String getDraftAttachOldExt() {
        return draftAttachOldExt;
    }

    public void setDraftAttachOldExt(String draftAttachOldExt) {
        this.draftAttachOldExt = draftAttachOldExt == null ? null : draftAttachOldExt.trim();
    }

    public String getDraftAttachNewName() {
        return draftAttachNewName;
    }

    public void setDraftAttachNewName(String draftAttachNewName) {
        this.draftAttachNewName = draftAttachNewName == null ? null : draftAttachNewName.trim();
    }

    public String getDraftAttachNewExt() {
        return draftAttachNewExt;
    }

    public void setDraftAttachNewExt(String draftAttachNewExt) {
        this.draftAttachNewExt = draftAttachNewExt == null ? null : draftAttachNewExt.trim();
    }

    public Byte getDraftAttachClass() {
        return draftAttachClass;
    }

    public void setDraftAttachClass(Byte draftAttachClass) {
        this.draftAttachClass = draftAttachClass;
    }
}