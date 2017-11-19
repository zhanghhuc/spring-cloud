package com.zssq.news.model;


import java.io.Serializable;

/**
 * @author SharlaCheung
 * @ClassName: NewsQuery
 * @Description: 新闻查询类
 * @date 2017年04月02日
 */
public class NewsRssQuery implements Serializable{
    private static final long serialVersionUID = -1606166264275829881L;

    private String orgCode ;
    private Integer pageSize;
    private Integer limitStart;

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitStart(Integer limitStart) {
        this.limitStart = limitStart;
    }


}
