package com.zssq.dao.pojo;

import java.io.Serializable;

public class ForumCommentReply implements Serializable {
	private static final long serialVersionUID = -6896790968665174998L;

	private Long id;

    private String forumCommentReplyCode;

    private Byte isDisable;

    private Byte isHide;

    private Byte isDelete;

    private String tenantCode;

    private String orgCode;

    private Byte orgLevel;

    private Long createTime;

    private Long modifyTime;

    private String remark;

    private String replierCode;

    private String questionerCode;

    private String topicCode;

    private String topicFollowCode;

    private String commentCode;

    private String content;

    //自定义字段
    private Integer pageSize;
    
    public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getForumCommentReplyCode() {
        return forumCommentReplyCode;
    }

    public void setForumCommentReplyCode(String forumCommentReplyCode) {
        this.forumCommentReplyCode = forumCommentReplyCode == null ? null : forumCommentReplyCode.trim();
    }

    public Byte getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(Byte isDisable) {
        this.isDisable = isDisable;
    }

    public Byte getIsHide() {
        return isHide;
    }

    public void setIsHide(Byte isHide) {
        this.isHide = isHide;
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

    public String getReplierCode() {
        return replierCode;
    }

    public void setReplierCode(String replierCode) {
        this.replierCode = replierCode == null ? null : replierCode.trim();
    }

    public String getQuestionerCode() {
        return questionerCode;
    }

    public void setQuestionerCode(String questionerCode) {
        this.questionerCode = questionerCode == null ? null : questionerCode.trim();
    }

    public String getTopicCode() {
        return topicCode;
    }

    public void setTopicCode(String topicCode) {
        this.topicCode = topicCode == null ? null : topicCode.trim();
    }

    public String getTopicFollowCode() {
        return topicFollowCode;
    }

    public void setTopicFollowCode(String topicFollowCode) {
        this.topicFollowCode = topicFollowCode == null ? null : topicFollowCode.trim();
    }

    public String getCommentCode() {
        return commentCode;
    }

    public void setCommentCode(String commentCode) {
        this.commentCode = commentCode == null ? null : commentCode.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}