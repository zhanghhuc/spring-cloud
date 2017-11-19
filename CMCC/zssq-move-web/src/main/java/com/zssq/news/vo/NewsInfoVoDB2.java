package com.zssq.news.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class NewsInfoVoDB2 implements Serializable {
    private static final long serialVersionUID = -1606166264275829882L;

    private String newsCode;

    private String infoTitle;

    private String infoContentHtml;

    private String fileUrl ;

    private Integer infoStatus;
    private Integer isDelete;

    private String infoOperatorCode;

    private String infoCreatorCode;

    private Date createTime;

    private Date modifyTime;


    public String getNewsCode() {
        return newsCode;
    }

    public void setNewsCode(String newsCode) {
        this.newsCode = newsCode;
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle;
    }

    public String getInfoContentHtml() {
        return infoContentHtml;
    }

    public void setInfoContentHtml(String infoContentHtml) {
        this.infoContentHtml = infoContentHtml;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Integer getInfoStatus() {
        return infoStatus;
    }

    public void setInfoStatus(Integer infoStatus) {
        this.infoStatus = infoStatus;
    }

    public String getInfoOperatorCode() {
        return infoOperatorCode;
    }

    public void setInfoOperatorCode(String infoOperatorCode) {
        this.infoOperatorCode = infoOperatorCode;
    }

    public String getInfoCreatorCode() {
        return infoCreatorCode;
    }

    public void setInfoCreatorCode(String infoCreatorCode) {
        this.infoCreatorCode = infoCreatorCode;
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}