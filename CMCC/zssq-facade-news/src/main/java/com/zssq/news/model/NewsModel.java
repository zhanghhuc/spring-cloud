package com.zssq.news.model;

import java.io.Serializable;
/**
 * @author SharlaCheung
 * @ClassName: NewsModel
 * @Description: 新闻增删改参数类
 * @date 2017年04月02日
 */
public class NewsModel implements Serializable {
    private static final long serialVersionUID = -1606166264275829882L;

    private String userCode ;
    private String token ;
    private String infoOperatorCode ;
    private String infoCreatorCode ;
    private int infoOperatorLevel ;
    private String infoOperatorOrg;
    private String infoTitle ;
    private String infoContentText ;
    private String infoContentHtml ;
    private int infoStatus ;
    private Integer isDelete =0 ;
    private String tenantCode ;

    private String newsCode ;
    private int moveType ;
    private int isTop =0 ;
    private int isHidden =0;
    private String infoRemark ;
    private Integer infoSort ;

    private String fileUrl ;

    private Long modifyTime ;
    private Long topTime ;

    private Long startTime;
    private Long endTime;


    private Integer isShield =0;


    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public int getInfoOperatorLevel() {
        return infoOperatorLevel;
    }

    public void setInfoOperatorLevel(int infoOperatorLevel) {
        this.infoOperatorLevel = infoOperatorLevel;
    }

    public String getInfoOperatorOrg() {
        return infoOperatorOrg;
    }

    public void setInfoOperatorOrg(String infoOperatorOrg) {
        this.infoOperatorOrg = infoOperatorOrg;
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

    public int getInfoStatus() {
        return infoStatus;
    }

    public void setInfoStatus(int infoStatus) {
        this.infoStatus = infoStatus;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public String getNewsCode() {
        return newsCode;
    }

    public void setNewsCode(String newsCode) {
        this.newsCode = newsCode;
    }

    public int getMoveType() {
        return moveType;
    }

    public void setMoveType(int moveType) {
        this.moveType = moveType;
    }

    public String getInfoRemark() {
        return infoRemark;
    }

    public void setInfoRemark(String infoRemark) {
        this.infoRemark = infoRemark;
    }

    public int getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(int isHidden) {
        this.isHidden = isHidden;
    }

    public int getIsTop() {
        return isTop;
    }

    public void setIsTop(int isTop) {
        this.isTop = isTop;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getInfoSort() {
        return infoSort;
    }

    public void setInfoSort(Integer infoSort) {
        this.infoSort = infoSort;
    }

    public Integer getIsShield() {
        return isShield;
    }

    public void setIsShield(Integer isShield) {
        this.isShield = isShield;
    }

    public Long getTopTime() {
        return topTime;
    }

    public void setTopTime(Long topTime) {
        this.topTime = topTime;
    }
}
