package com.zssq.news.dao.pojo;

import com.zssq.news.model.NewsCommentModel;

import java.io.Serializable;
import java.util.List;

public class NewsInfoContent  implements Serializable {
    private static final long serialVersionUID = -1606166264275829882L;
    private Long id;

    private String newsCode;

    private String infoTitle;

    private String infoContentText;

    private String infoContentHtml;

    private String fileUrl ;

    private Integer infoStatus;

    private String infoOperatorCode;

    private String infoCreatorCode;

    private Integer orgLevel;

    private String orgCode;

    private Long infoUpdateTime;

    private Integer isTop;

    private Integer infoSort ;
    private Integer isHidden = 0 ;
    private Integer isShield = 0;
    private Integer isArchived = 0;

    private Integer infoGoodCount;

    private Integer infoCommentCount;
    private Integer isDelete;

    private Long createTime;

    private Long modifyTime;
    private Long publishTime;

    private Long topTime;

    private String remark;

    private String tenantCode;
    //收藏数量
    private int collectNumber ;
    //转发数量
    private Integer transmitNumber ;
    //是否收藏
    private Integer isCollect ;
    //是否点赞
    private Integer isPraise ;

    private List<NewsInfoUpload> newsInfoUploadList ;

    private List<NewsCommentModel> newsCommentModels ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getInfoContentText() {
        return infoContentText;
    }

    public void setInfoContentText(String infoContentText) {
        this.infoContentText = infoContentText;
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

    public Integer getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(Integer orgLevel) {
        this.orgLevel = orgLevel;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Long getInfoUpdateTime() {
        return infoUpdateTime;
    }

    public void setInfoUpdateTime(Long infoUpdateTime) {
        this.infoUpdateTime = infoUpdateTime;
    }

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    public Integer getInfoSort() {
        return infoSort;
    }

    public void setInfoSort(Integer infoSort) {
        this.infoSort = infoSort;
    }

    public Integer getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Integer isHidden) {
        this.isHidden = isHidden;
    }

    public Integer getIsShield() {
        return isShield;
    }

    public void setIsShield(Integer isShield) {
        this.isShield = isShield;
    }

    public Integer getIsArchived() {
        return isArchived;
    }

    public void setIsArchived(Integer isArchived) {
        this.isArchived = isArchived;
    }

    public Integer getInfoGoodCount() {
        return infoGoodCount;
    }

    public void setInfoGoodCount(Integer infoGoodCount) {
        this.infoGoodCount = infoGoodCount;
    }

    public Integer getInfoCommentCount() {
        return infoCommentCount;
    }

    public void setInfoCommentCount(Integer infoCommentCount) {
        this.infoCommentCount = infoCommentCount;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
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

    public Long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Long publishTime) {
        this.publishTime = publishTime;
    }

    public Long getTopTime() {
        return topTime;
    }

    public void setTopTime(Long topTime) {
        this.topTime = topTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public int getCollectNumber() {
        return collectNumber;
    }

    public void setCollectNumber(int collectNumber) {
        this.collectNumber = collectNumber;
    }

    public Integer getTransmitNumber() {
        return transmitNumber;
    }

    public void setTransmitNumber(Integer transmitNumber) {
        this.transmitNumber = transmitNumber;
    }

    public Integer getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(Integer isCollect) {
        this.isCollect = isCollect;
    }

    public Integer getIsPraise() {
        return isPraise;
    }

    public void setIsPraise(Integer isPraise) {
        this.isPraise = isPraise;
    }

    public List<NewsInfoUpload> getNewsInfoUploadList() {
        return newsInfoUploadList;
    }

    public void setNewsInfoUploadList(List<NewsInfoUpload> newsInfoUploadList) {
        this.newsInfoUploadList = newsInfoUploadList;
    }

    public List<NewsCommentModel> getNewsCommentModels() {
        return newsCommentModels;
    }

    public void setNewsCommentModels(List<NewsCommentModel> newsCommentModels) {
        this.newsCommentModels = newsCommentModels;
    }
}