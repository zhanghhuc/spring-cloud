package com.zssq.dao.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HonorComment implements Serializable{
	
	private static final long serialVersionUID = 396735052558757971L;

	private Long id;

    private String honorAwardCommentCode;

    private Byte isDisable;

    private Byte isHide;

    private Byte isDelete;

    private String tenantCode;

    private String orgCode;

    private Byte orgLevel;

    private Long createTime;

    private Long modifyTime;

    private String remark;

    private String awardRecordCode;

    private String commenterCode;

    private Integer praiseCount;

    private Integer replyCount;

    private String content;
    
    //点赞人code
    private String admirerCode; 
    
    //加载更多条数
    private Integer pageSize;
    // 回复列表
    private List<HonorCommentReply> replyList = new ArrayList<>();
    
    public List<HonorCommentReply> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<HonorCommentReply> replyList) {
		this.replyList = replyList;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getAdmirerCode() {
		return admirerCode;
	}

	public void setAdmirerCode(String admirerCode) {
		this.admirerCode = admirerCode;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHonorAwardCommentCode() {
        return honorAwardCommentCode;
    }

    public void setHonorAwardCommentCode(String honorAwardCommentCode) {
        this.honorAwardCommentCode = honorAwardCommentCode == null ? null : honorAwardCommentCode.trim();
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

    public String getAwardRecordCode() {
        return awardRecordCode;
    }

    public void setAwardRecordCode(String awardRecordCode) {
        this.awardRecordCode = awardRecordCode == null ? null : awardRecordCode.trim();
    }

    public String getCommenterCode() {
        return commenterCode;
    }

    public void setCommenterCode(String commenterCode) {
        this.commenterCode = commenterCode == null ? null : commenterCode.trim();
    }

    public Integer getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(Integer praiseCount) {
        this.praiseCount = praiseCount;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}