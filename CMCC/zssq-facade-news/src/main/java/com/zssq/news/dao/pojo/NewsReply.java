package com.zssq.news.dao.pojo;

import java.io.Serializable;

public class NewsReply  implements Serializable {
    private static final long serialVersionUID = -1606166264275829882L;
    private Long id;

    private String replyCode;

    private String tenantCode;

    private Long createTime;

    private Long modifyTime;

    private String remark;

    private String newsCode;

    private String commentCode;

    private String userCode;

    private String orgCode;

    private Integer orgLevel;

    private String toReplyUserCode;

    private String toReplyOrgCode;

    private Integer toReplyOrgLevel;

    private String replyContent;

    private Integer replyIsDelete;

    private Integer replyIsShield;

    private Integer replyLikeNum;
    private Integer isPraise;

    private Integer replyReportNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReplyCode() {
        return replyCode;
    }

    public void setReplyCode(String replyCode) {
        this.replyCode = replyCode == null ? null : replyCode.trim();
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode == null ? null : tenantCode.trim();
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

    public String getNewsCode() {
        return newsCode;
    }

    public void setNewsCode(String newsCode) {
        this.newsCode = newsCode == null ? null : newsCode.trim();
    }

    public String getCommentCode() {
        return commentCode;
    }

    public void setCommentCode(String commentCode) {
        this.commentCode = commentCode == null ? null : commentCode.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public Integer getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(Integer orgLevel) {
        this.orgLevel = orgLevel;
    }

    public String getToReplyUserCode() {
        return toReplyUserCode;
    }

    public void setToReplyUserCode(String toReplyUserCode) {
        this.toReplyUserCode = toReplyUserCode == null ? null : toReplyUserCode.trim();
    }

    public String getToReplyOrgCode() {
        return toReplyOrgCode;
    }

    public void setToReplyOrgCode(String toReplyOrgCode) {
        this.toReplyOrgCode = toReplyOrgCode == null ? null : toReplyOrgCode.trim();
    }

    public Integer getToReplyOrgLevel() {
        return toReplyOrgLevel;
    }

    public void setToReplyOrgLevel(Integer toReplyOrgLevel) {
        this.toReplyOrgLevel = toReplyOrgLevel;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent == null ? null : replyContent.trim();
    }

    public Integer getReplyIsDelete() {
        return replyIsDelete;
    }

    public void setReplyIsDelete(Integer replyIsDelete) {
        this.replyIsDelete = replyIsDelete;
    }

    public Integer getReplyIsShield() {
        return replyIsShield;
    }

    public void setReplyIsShield(Integer replyIsShield) {
        this.replyIsShield = replyIsShield;
    }

    public Integer getReplyLikeNum() {
        return replyLikeNum;
    }

    public void setReplyLikeNum(Integer replyLikeNum) {
        this.replyLikeNum = replyLikeNum;
    }

    public Integer getIsPraise() {
        return isPraise;
    }

    public void setIsPraise(Integer isPraise) {
        this.isPraise = isPraise;
    }

    public Integer getReplyReportNum() {
        return replyReportNum;
    }

    public void setReplyReportNum(Integer replyReportNum) {
        this.replyReportNum = replyReportNum;
    }
}