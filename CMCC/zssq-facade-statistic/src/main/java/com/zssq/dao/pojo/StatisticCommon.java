package com.zssq.dao.pojo;

import com.zssq.pojo.BasePage;

public class StatisticCommon extends BasePage {

	private static final long serialVersionUID = 1L;

	private Long id;

    private String orgCode;

    private String teamCode;

    private String teamName;

    private String peopleCode;

    private String peopleName;

    private Integer microblog = 0;

    private Integer blog = 0;

    private Integer post = 0;

    private Integer vote = 0;

    private Integer message = 0;

    private Integer faces = 0;

    private Integer networkDisk = 0;

    private Integer repository = 0;
    
    private Integer news = 0;
    
    private Integer activity = 0;

    private Long createTime;
    
    private Long startTime;
    
    private Long endTime;
    
    private Long visitCount = 0L;
    
    private Integer isFlag; //1:查询班组,2:查询个人

	private Integer isDelete  ;

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
		this.orgCode = orgCode;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getPeopleCode() {
		return peopleCode;
	}

	public void setPeopleCode(String peopleCode) {
		this.peopleCode = peopleCode;
	}

	public String getPeopleName() {
		return peopleName;
	}

	public void setPeopleName(String peopleName) {
		this.peopleName = peopleName;
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

	public Integer getPost() {
		return post;
	}

	public void setPost(Integer post) {
		this.post = post;
	}

	public Integer getVote() {
		return vote;
	}

	public void setVote(Integer vote) {
		this.vote = vote;
	}

	public Integer getMessage() {
		return message;
	}

	public void setMessage(Integer message) {
		this.message = message;
	}

	public Integer getFaces() {
		return faces;
	}

	public void setFaces(Integer faces) {
		this.faces = faces;
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

	public Long getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(Long visitCount) {
		this.visitCount = visitCount;
	}

	public Integer getIsFlag() {
		return isFlag;
	}

	public void setIsFlag(Integer isFlag) {
		this.isFlag = isFlag;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
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
}