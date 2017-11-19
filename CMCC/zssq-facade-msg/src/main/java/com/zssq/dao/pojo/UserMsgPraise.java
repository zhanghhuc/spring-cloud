package com.zssq.dao.pojo;

import com.zssq.pojo.BasePage;

public class UserMsgPraise extends BasePage {
    private Long id;

    private String praiseCode;

    private String userCode;

    private String praiseUserCode;

    private Long createTime;

    private Byte praiseType;

    private Byte isDelete;

    private Integer praiseNumber;

    private Integer commentNumber;

    private String originalCode;

    private String tenantCode;

    private String orgCode;
    
    private String content;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPraiseCode() {
        return praiseCode;
    }

    public void setPraiseCode(String praiseCode) {
        this.praiseCode = praiseCode == null ? null : praiseCode.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getPraiseUserCode() {
        return praiseUserCode;
    }

    public void setPraiseUserCode(String praiseUserCode) {
        this.praiseUserCode = praiseUserCode == null ? null : praiseUserCode.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Byte getPraiseType() {
        return praiseType;
    }

    public void setPraiseType(Byte praiseType) {
        this.praiseType = praiseType;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getPraiseNumber() {
        return praiseNumber;
    }

    public void setPraiseNumber(Integer praiseNumber) {
        this.praiseNumber = praiseNumber;
    }

    public Integer getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Integer commentNumber) {
        this.commentNumber = commentNumber;
    }

    public String getOriginalCode() {
        return originalCode;
    }

    public void setOriginalCode(String originalCode) {
        this.originalCode = originalCode == null ? null : originalCode.trim();
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
    
    
}