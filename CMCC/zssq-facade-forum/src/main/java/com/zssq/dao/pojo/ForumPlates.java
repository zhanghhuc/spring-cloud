package com.zssq.dao.pojo;

import java.io.Serializable;

public class ForumPlates implements Serializable {
	private static final long serialVersionUID = -1788342281929041383L;

	private Long id;

    private String forumPlatesCode;

    private Byte isDisable;

    private Byte isDelete;

    private String tenantCode;

    private String orgCode;

    private Byte orgLevel;

    private Long createTime;

    private Long modifyTime;

    private String remark;

    private String platesName;

    private String belongCode;

    private Byte allSend;

    private Byte allReply;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getForumPlatesCode() {
        return forumPlatesCode;
    }

    public void setForumPlatesCode(String forumPlatesCode) {
        this.forumPlatesCode = forumPlatesCode == null ? null : forumPlatesCode.trim();
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

    public String getPlatesName() {
        return platesName;
    }

    public void setPlatesName(String platesName) {
        this.platesName = platesName == null ? null : platesName.trim();
    }

    public String getBelongCode() {
        return belongCode;
    }

    public void setBelongCode(String belongCode) {
        this.belongCode = belongCode == null ? null : belongCode.trim();
    }

    public Byte getAllSend() {
        return allSend;
    }

    public void setAllSend(Byte allSend) {
        this.allSend = allSend;
    }

    public Byte getAllReply() {
        return allReply;
    }

    public void setAllReply(Byte allReply) {
        this.allReply = allReply;
    }
}