package com.zssq.dao.pojo;

import com.zssq.pojo.BasePage;

public class StatisticApp extends BasePage {

	private static final long serialVersionUID = 1L;

	private String orgCode;
	
    private Integer news = 0;

    private Integer microblog = 0;

    private Integer blog = 0;

    private Integer activity = 0;

    private Integer vote = 0;

    private Integer networkDisk = 0;

    private Integer repository = 0;

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Integer getNews() {
		return news;
	}

	public void setNews(Integer news) {
		this.news = news;
	}

	public Integer getMicroblog() {
		return microblog;
	}

	public void setMicroblog(Integer microblog) {
		this.microblog = microblog;
	}

	public Integer getBlog() {
		return blog;
	}

	public void setBlog(Integer blog) {
		this.blog = blog;
	}

	public Integer getActivity() {
		return activity;
	}

	public void setActivity(Integer activity) {
		this.activity = activity;
	}

	public Integer getVote() {
		return vote;
	}

	public void setVote(Integer vote) {
		this.vote = vote;
	}

	public Integer getNetworkDisk() {
		return networkDisk;
	}

	public void setNetworkDisk(Integer networkDisk) {
		this.networkDisk = networkDisk;
	}

	public Integer getRepository() {
		return repository;
	}

	public void setRepository(Integer repository) {
		this.repository = repository;
	}


    
    
}