package com.zssq.dao.pojo;

import java.io.Serializable;

public class VoteCommentReply implements Serializable {
	private static final long serialVersionUID = 4875878501209025716L;

	private Long id;

    private String code;

    private Byte isDisable;

    private Byte isDelete;

    private Byte isHide;

    private String tenantCode;

    private String orgCode;

    private Byte orgLevel;

    private Long createTime;

    private Long modifyTime;

    private String remark;

    private String voteInfoCode;

    private String commentCode;

    private String replierCode;

    private String questionerCode;

    private Integer praiseCount;

    private String content;
    
    //加载更多 条数
    private int pageSize;
    
    //点赞人
    private String admirerCode;
    
    public int getPageSize() {
		return pageSize;
	}

	public String getAdmirerCode() {
		return admirerCode;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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

    public Byte getIsHide() {
        return isHide;
    }

    public void setIsHide(Byte isHide) {
        this.isHide = isHide;
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

    public String getVoteInfoCode() {
        return voteInfoCode;
    }

    public void setVoteInfoCode(String voteInfoCode) {
        this.voteInfoCode = voteInfoCode == null ? null : voteInfoCode.trim();
    }

    public String getCommentCode() {
        return commentCode;
    }

    public void setCommentCode(String commentCode) {
        this.commentCode = commentCode == null ? null : commentCode.trim();
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

    public Integer getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(Integer praiseCount) {
        this.praiseCount = praiseCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}