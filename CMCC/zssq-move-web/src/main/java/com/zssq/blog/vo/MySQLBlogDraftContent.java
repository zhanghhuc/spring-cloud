package com.zssq.blog.vo;

import java.io.Serializable;

/**
 * 
 * @ClassName: MySQLBlogDraftContent  
 * @Description: 博客草稿正文  
 * @author ZKZ  
 * @date 2017年3月24日  
 *
 */
public class MySQLBlogDraftContent implements Serializable {
        
	private static final long serialVersionUID = 1L;
	
	private Long id = 0L;
    private String draftContentCode = "";
    private String tenantCode = "";
    private String orgCode = "";
    private Long createTime = 0L;
    private Long modifyTime = 0L;
    private String remark = "";
    private String draftCode = "";
    private String draftContentInfo = "";

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDraftContentCode() {
        return draftContentCode;
    }

    public void setDraftContentCode(String draftContentCode) {
        this.draftContentCode = draftContentCode == null ? null : draftContentCode.trim();
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

    public String getDraftContentInfo() {
        return draftContentInfo;
    }

    public void setDraftContentInfo(String draftContentInfo) {
        this.draftContentInfo = draftContentInfo == null ? null : draftContentInfo.trim();
    }
}