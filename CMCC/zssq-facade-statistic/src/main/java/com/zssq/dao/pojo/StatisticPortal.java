package com.zssq.dao.pojo;

import com.zssq.pojo.BasePage;

public class StatisticPortal extends BasePage{
   
	private static final long serialVersionUID = 1L;

	private Long id;

    private String orgCode;

    private Integer homePage = 0;

    private Integer news = 0;

    private Integer activity = 0;

    private Integer marrow = 0;

    private Integer hotspot = 0;

    private Integer dynamic = 0;

    private Integer top = 0;

    private Integer detail = 0;

    private Long createTime;
    
    private Long startTime;
    
    private Long endTime;
    
    private Integer isFlag;
    
    private Long visitCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public Integer getHomePage() {
        return homePage;
    }

    public void setHomePage(Integer homePage) {
        this.homePage = homePage;
    }

    public Integer getNews() {
        return news;
    }

    public void setNews(Integer news) {
        this.news = news;
    }

    public Integer getActivity() {
        return activity;
    }

    public void setActivity(Integer activity) {
        this.activity = activity;
    }

    public Integer getMarrow() {
        return marrow;
    }

    public void setMarrow(Integer marrow) {
        this.marrow = marrow;
    }

    public Integer getHotspot() {
        return hotspot;
    }

    public void setHotspot(Integer hotspot) {
        this.hotspot = hotspot;
    }

    public Integer getDynamic() {
        return dynamic;
    }

    public void setDynamic(Integer dynamic) {
        this.dynamic = dynamic;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Integer getDetail() {
        return detail;
    }

    public void setDetail(Integer detail) {
        this.detail = detail;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
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

	public Integer getIsFlag() {
		return isFlag;
	}

	public void setIsFlag(Integer isFlag) {
		this.isFlag = isFlag;
	}

	public Long getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(Long visitCount) {
		this.visitCount = visitCount;
	}
    
	
	
    
}