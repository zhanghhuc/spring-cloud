package com.zssq.model;

import java.io.Serializable;

public class RelationHotListModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String hotCode;	//热点编号
	
	//private Integer hotStatus;	//热点状态：1.展示中；2.隐藏
	
	private String subjectCode;	//内容编号
	
	private Integer subjectClass;	//内容类型：1.微博；2.博客；3.投票；4.活动；5.新闻公告；
	
	private String userCode;	//内容发布人编号
	
	private String userName;	//内容发布人名称
	
	private String orgCode;		//内容发布人所属公司编号
	
	private String orgName;		//内容发布人所属公司名称
	
	private Long subjectPublishTime;	//内容发布时间
	
	private String subjectTitle;	//内容标题
	
	private Integer likeNum;	//点赞量
	
	private Integer commentNum;	//评论量
	
	private Integer collectNum;	//收藏量
	
	private Integer subjectDepend;//内容从属关系
	
	private String teamCode;// 班组 编号

	public String getHotCode() {
		return hotCode;
	}

	public void setHotCode(String hotCode) {
		this.hotCode = hotCode;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public Integer getSubjectClass() {
		return subjectClass;
	}

	public void setSubjectClass(Integer subjectClass) {
		this.subjectClass = subjectClass;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSubjectTitle() {
		return subjectTitle;
	}

	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}

	public Integer getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(Integer likeNum) {
		this.likeNum = likeNum;
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public Integer getCollectNum() {
		return collectNum;
	}

	public void setCollectNum(Integer collectNum) {
		this.collectNum = collectNum;
	}

	public Long getSubjectPublishTime() {
		return subjectPublishTime;
	}

	public void setSubjectPublishTime(Long subjectPublishTime) {
		this.subjectPublishTime = subjectPublishTime;
	}

	public Integer getSubjectDepend() {
		return subjectDepend;
	}

	public void setSubjectDepend(Integer subjectDepend) {
		this.subjectDepend = subjectDepend;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

}
