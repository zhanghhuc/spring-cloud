package com.zssq.news.model;


import java.io.Serializable;

/**
 * @author SharlaCheung
 * @ClassName: NewsQuery
 * @Description: 新闻查询类
 * @date 2017年04月02日
 */
public class NoticeQuery implements Serializable{
    private static final long serialVersionUID = -1606166264275829881L;
    private String noticeCode ;
    private String noticeTitle ;
    private String noticeOperatorCode ;//操作人编码
    private String noticeOperatorName ;//操作人姓名
    private String noticeCreatorCode ;//创建人编码
    private long startTime ;
    private long endTime ;

    private String userCode; //查询用户code
    private String orgCode;
    private Integer pageSize;
    private Integer pageNo;
    private Integer limitStart ;
    private Integer noticeStatus ;

    private Integer isHidden ;
    private Integer isDelete ;
    private String orderByClause ;
    private Integer isTop ;


    private Integer sortStart ;
    private Integer sortEnd  ;
    private Integer selfFlag  ;


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

    public String getNoticeOperatorName() {
        return noticeOperatorName;
    }

    public void setNoticeOperatorName(String noticeOperatorName) {
        this.noticeOperatorName = noticeOperatorName;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
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

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitStart(Integer limitStart) {
        this.limitStart = limitStart;
    }


    public Integer getNoticeStatus() {
        return noticeStatus;
    }

    public void setNoticeStatus(Integer noticeStatus) {
        this.noticeStatus = noticeStatus;
    }

    public Integer getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Integer isHidden) {
        this.isHidden = isHidden;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getSortStart() {
        return sortStart;
    }

    public void setSortStart(Integer sortStart) {
        this.sortStart = sortStart;
    }

    public Integer getSortEnd() {
        return sortEnd;
    }

    public void setSortEnd(Integer sortEnd) {
        this.sortEnd = sortEnd;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    public Integer getSelfFlag() {
        return selfFlag;
    }

    public void setSelfFlag(Integer selfFlag) {
        this.selfFlag = selfFlag;
    }
}
