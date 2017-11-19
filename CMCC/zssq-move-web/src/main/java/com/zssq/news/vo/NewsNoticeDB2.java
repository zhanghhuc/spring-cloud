package com.zssq.news.vo;

import java.io.Serializable;
import java.util.Date;

public class NewsNoticeDB2 implements Serializable {
    private static final long serialVersionUID = -1606166264275829882L;

    private String noticeCode;

    private String noticeTitle;

    private String noticeContentHtml;

    private String noticeFileUrl ;
    private Integer noticeStatus;

    private String noticeOperatorCode;
    private String noticeCreatorCode;

    private Date createTime;

    private Date modifyTime;


    public String getNoticeCode() {
        return noticeCode;
    }

    public void setNoticeCode(String noticeCode) {
        this.noticeCode = noticeCode;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContentHtml() {
        return noticeContentHtml;
    }

    public void setNoticeContentHtml(String noticeContentHtml) {
        this.noticeContentHtml = noticeContentHtml;
    }

    public String getNoticeFileUrl() {
        return noticeFileUrl;
    }

    public void setNoticeFileUrl(String noticeFileUrl) {
        this.noticeFileUrl = noticeFileUrl;
    }

    public Integer getNoticeStatus() {
        return noticeStatus;
    }

    public void setNoticeStatus(Integer noticeStatus) {
        this.noticeStatus = noticeStatus;
    }

    public String getNoticeOperatorCode() {
        return noticeOperatorCode;
    }

    public void setNoticeOperatorCode(String noticeOperatorCode) {
        this.noticeOperatorCode = noticeOperatorCode;
    }

    public String getNoticeCreatorCode() {
        return noticeCreatorCode;
    }

    public void setNoticeCreatorCode(String noticeCreatorCode) {
        this.noticeCreatorCode = noticeCreatorCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}