package com.zssq.news.vo;

import java.io.Serializable;

public class NewsNoticeMySql implements Serializable {
    private static final long serialVersionUID = -1606166264275829882L;
    private Long id;

    private String noticeCode;

    private String noticeTitle;

    private String noticeContentText;

    private String noticeContentHtml;

    private String noticeFileUrl ;
    private Integer noticeStatus;

    private String noticeOperatorCode;
    private String noticeCreatorCode;

    private Integer orgLevel;

    private String orgCode;

    private String noticeRemark;

    private Integer isTop;

    private Integer noticeSort;

    private Integer isDelete ;
    private Integer isHidden ;

    private Long createTime;

    private Long modifyTime;

    private String remark;

    private String tenantCode;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoticeCode() {
        return noticeCode;
    }

    public void setNoticeCode(String noticeCode) {
        this.noticeCode = noticeCode == null ? null : noticeCode.trim();
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle == null ? null : noticeTitle.trim();
    }

    public String getNoticeContentText() {
        return noticeContentText;
    }

    public void setNoticeContentText(String noticeContentText) {
        this.noticeContentText = noticeContentText == null ? null : noticeContentText.trim();
    }

    public String getNoticeContentHtml() {
        return noticeContentHtml;
    }

    public void setNoticeContentHtml(String noticeContentHtml) {
        this.noticeContentHtml = noticeContentHtml == null ? null : noticeContentHtml.trim();
    }

    public String getNoticeFileUrl() {
        return noticeFileUrl;
    }

    public void setNoticeFileUrl(String noticeFileUrl) {
        this.noticeFileUrl = noticeFileUrl;
    }

    public String getNoticeOperatorCode() {
        return noticeOperatorCode;
    }

    public void setNoticeOperatorCode(String noticeOperatorCode) {
        this.noticeOperatorCode = noticeOperatorCode == null ? null : noticeOperatorCode.trim();
    }

    public String getNoticeCreatorCode() {
        return noticeCreatorCode;
    }

    public void setNoticeCreatorCode(String noticeCreatorCode) {
        this.noticeCreatorCode = noticeCreatorCode;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getNoticeRemark() {
        return noticeRemark;
    }

    public void setNoticeRemark(String noticeRemark) {
        this.noticeRemark = noticeRemark;
    }

    public Integer getNoticeStatus() {
        return noticeStatus;
    }

    public void setNoticeStatus(Integer noticeStatus) {
        this.noticeStatus = noticeStatus;
    }

    public Integer getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(Integer orgLevel) {
        this.orgLevel = orgLevel;
    }

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    public Integer getNoticeSort() {
        return noticeSort;
    }

    public void setNoticeSort(Integer noticeSort) {
        this.noticeSort = noticeSort;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Integer isHidden) {
        this.isHidden = isHidden;
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

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode == null ? null : tenantCode.trim();
    }
}