package com.zssq.dao.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ForumTopics implements Serializable {
	private static final long serialVersionUID = -890425304337454306L;

	private Long id;

    private String forumTopicsCode;

    private Byte isDisable;

    private Byte isHide;

    private Byte isDelete;

    private String tenantCode;

    private String orgCode;

    private Byte orgLevel;

    private Long createTime;

    private Long modifyTime;

    private String remark;

    private String forumPlatesCode;

    private String postmanCode;

    private String subject;

    private Byte isTop;

    private Byte isBest;

    private Integer viewCount;

    private Integer replyCount;

    private Long lastReplyTime;

    private String content;
    
    private Integer pageSize;
    
    /******以下为自定义添加属性******/
    private String belongCode;

    private Integer pageNo;
    
    private ForumPlatesMember member;
    
    private Integer rowId;
    // 跟帖列表
    private List<ForumTopicsFollow> followList = new ArrayList<>();
    
    public List<ForumTopicsFollow> getFollowList() {
		return followList;
	}

	public void setFollowList(List<ForumTopicsFollow> followList) {
		this.followList = followList;
	}

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public ForumPlatesMember getMember() {
		return member;
	}

	public void setMember(ForumPlatesMember member) {
		this.member = member;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

    public String getBelongCode() {
		return belongCode;
	}

	public void setBelongCode(String belongCode) {
		this.belongCode = belongCode;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getForumTopicsCode() {
        return forumTopicsCode;
    }

    public void setForumTopicsCode(String forumTopicsCode) {
        this.forumTopicsCode = forumTopicsCode == null ? null : forumTopicsCode.trim();
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

    public String getForumPlatesCode() {
        return forumPlatesCode;
    }

    public void setForumPlatesCode(String forumPlatesCode) {
        this.forumPlatesCode = forumPlatesCode == null ? null : forumPlatesCode.trim();
    }

    public String getPostmanCode() {
        return postmanCode;
    }

    public void setPostmanCode(String postmanCode) {
        this.postmanCode = postmanCode == null ? null : postmanCode.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public Byte getIsTop() {
        return isTop;
    }

    public void setIsTop(Byte isTop) {
        this.isTop = isTop;
    }

    public Byte getIsBest() {
        return isBest;
    }

    public void setIsBest(Byte isBest) {
        this.isBest = isBest;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public Long getLastReplyTime() {
        return lastReplyTime;
    }

    public void setLastReplyTime(Long lastReplyTime) {
        this.lastReplyTime = lastReplyTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}