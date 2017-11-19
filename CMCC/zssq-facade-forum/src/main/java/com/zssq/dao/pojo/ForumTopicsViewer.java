package com.zssq.dao.pojo;

import java.io.Serializable;

public class ForumTopicsViewer implements Serializable {
	private static final long serialVersionUID = 5139186527011056547L;

	private Long id;

    private String forumTopicsViewerCode;

    private Byte isDisable;

    private Byte isDelete;

    private String tenantCode;

    private String orgCode;

    private Byte orgLevel;

    private Long createTime;

    private Long modifyTime;

    private String remark;

    private String forumTopicsCode;

    private String viewerCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getForumTopicsViewerCode() {
        return forumTopicsViewerCode;
    }

    public void setForumTopicsViewerCode(String forumTopicsViewerCode) {
        this.forumTopicsViewerCode = forumTopicsViewerCode == null ? null : forumTopicsViewerCode.trim();
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

    public String getForumTopicsCode() {
        return forumTopicsCode;
    }

    public void setForumTopicsCode(String forumTopicsCode) {
        this.forumTopicsCode = forumTopicsCode == null ? null : forumTopicsCode.trim();
    }

    public String getViewerCode() {
        return viewerCode;
    }

    public void setViewerCode(String viewerCode) {
        this.viewerCode = viewerCode == null ? null : viewerCode.trim();
    }
}