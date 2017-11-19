package com.zssq.news.model;


import java.io.Serializable;

/**
 * @author SharlaCheung
 * @ClassName: NewsCommentQuery
 * @Description: 新闻评论查询类
 * @date 2017年04月02日
 */
public class NewsReplyQuery implements Serializable{

    private static final long serialVersionUID = -1606166264275829881L;
    private String newsCode ;
    private String newsCommentCode ;
    private String userCode; //查询用户code
    private Integer pageSize;
    private Integer pageNo;
    private Integer limitStart;
    private String orderByClause ;//排序字段
    private String replyCode ;
    private String excludeReplyCode ;
    private Integer id ;
    private Integer replyIsDelete ;
    private Integer replyIsShield ;

    public String getNewsCode() {
        return newsCode;
    }

    public void setNewsCode(String newsCode) {
        this.newsCode = newsCode;
    }

    public String getNewsCommentCode() {
        return newsCommentCode;
    }

    public void setNewsCommentCode(String newsCommentCode) {
        this.newsCommentCode = newsCommentCode;
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

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitStart(Integer limitStart) {
        this.limitStart = limitStart;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getExcludeReplyCode() {
        return excludeReplyCode;
    }

    public void setExcludeReplyCode(String excludeReplyCode) {
        this.excludeReplyCode = excludeReplyCode;
    }

    public String getReplyCode() {
        return replyCode;
    }

    public void setReplyCode(String replyCode) {
        this.replyCode = replyCode;
    }
}
