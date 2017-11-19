package com.zssq.dao.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ForumTopicsFollow implements Serializable {
	private static final long serialVersionUID = -1738192818568582367L;

	private Long id;

    private String forumTopicsFollowCode;

    private Byte isDisable;

    private Byte isHide;

    private Byte isDelete;

    private String tenantCode;

    private String orgCode;

    private Byte orgLevel;

    private Long createTime;

    private Long modifyTime;

    private String remark;

    private String topicCode;

    private String followerCode;

    private Integer followCount;

    private String followContent;

    //自定义字段
    private Integer pageSize;
    // 评论回复列表
    private List<ForumCommentReply> commentReplyList = new ArrayList<>();
    
	public List<ForumCommentReply> getCommentReplyList() {
		return commentReplyList;
	}

	public void setCommentReplyList(List<ForumCommentReply> commentReplyList) {
		this.commentReplyList = commentReplyList;
	}

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

    public String getForumTopicsFollowCode() {
        return forumTopicsFollowCode;
    }

    public void setForumTopicsFollowCode(String forumTopicsFollowCode) {
        this.forumTopicsFollowCode = forumTopicsFollowCode == null ? null : forumTopicsFollowCode.trim();
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

    public String getTopicCode() {
        return topicCode;
    }

    public void setTopicCode(String topicCode) {
        this.topicCode = topicCode == null ? null : topicCode.trim();
    }

    public String getFollowerCode() {
        return followerCode;
    }

    public void setFollowerCode(String followerCode) {
        this.followerCode = followerCode == null ? null : followerCode.trim();
    }

    public Integer getFollowCount() {
        return followCount;
    }

    public void setFollowCount(Integer followCount) {
        this.followCount = followCount;
    }

    public String getFollowContent() {
        return followContent;
    }

    public void setFollowContent(String followContent) {
        this.followContent = followContent == null ? null : followContent.trim();
    }
}