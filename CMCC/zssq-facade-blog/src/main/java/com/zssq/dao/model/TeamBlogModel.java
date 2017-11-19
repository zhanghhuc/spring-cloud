package com.zssq.dao.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @ClassName: TeamBlogModel
 * @Description: 班组归档时博客详情
 * @author ZKZ
 * @date 2017年7月12日
 *
 */
public class TeamBlogModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String blogCode;
	private String userCode;
	private Byte blogDepend;
	private String teamCode;
	private String blogTitle;
	private String className;
	private String blogTags;
	private Long blogPublishTime;
	private Integer teamQualityNum;
	private Integer groupQualityNum;
	private Integer provinceQualityNum;
	private Integer cityQualityNum;
	private Integer readNum;
	private Integer likeNum;
	private Integer collectNum;
	private Integer forwardNum;
	private Integer commentNum;
	private Integer shareNum;
	private String contentInfo;
	private List<TeamCommentModel> commentList;
	

	public String getBlogCode() {
		return blogCode;
	}

	public void setBlogCode(String blogCode) {
		this.blogCode = blogCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Byte getBlogDepend() {
		return blogDepend;
	}

	public void setBlogDepend(Byte blogDepend) {
		this.blogDepend = blogDepend;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getBlogTags() {
		return blogTags;
	}

	public void setBlogTags(String blogTags) {
		this.blogTags = blogTags;
	}

	public Long getBlogPublishTime() {
		return blogPublishTime;
	}

	public void setBlogPublishTime(Long blogPublishTime) {
		this.blogPublishTime = blogPublishTime;
	}

	public Integer getTeamQualityNum() {
		return teamQualityNum;
	}

	public void setTeamQualityNum(Integer teamQualityNum) {
		this.teamQualityNum = teamQualityNum;
	}

	public Integer getGroupQualityNum() {
		return groupQualityNum;
	}

	public void setGroupQualityNum(Integer groupQualityNum) {
		this.groupQualityNum = groupQualityNum;
	}

	public Integer getProvinceQualityNum() {
		return provinceQualityNum;
	}

	public void setProvinceQualityNum(Integer provinceQualityNum) {
		this.provinceQualityNum = provinceQualityNum;
	}

	public Integer getCityQualityNum() {
		return cityQualityNum;
	}

	public void setCityQualityNum(Integer cityQualityNum) {
		this.cityQualityNum = cityQualityNum;
	}

	public Integer getReadNum() {
		return readNum;
	}

	public void setReadNum(Integer readNum) {
		this.readNum = readNum;
	}

	public Integer getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(Integer likeNum) {
		this.likeNum = likeNum;
	}

	public Integer getCollectNum() {
		return collectNum;
	}

	public void setCollectNum(Integer collectNum) {
		this.collectNum = collectNum;
	}

	public Integer getForwardNum() {
		return forwardNum;
	}

	public void setForwardNum(Integer forwardNum) {
		this.forwardNum = forwardNum;
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public Integer getShareNum() {
		return shareNum;
	}

	public void setShareNum(Integer shareNum) {
		this.shareNum = shareNum;
	}

	public String getContentInfo() {
		return contentInfo;
	}

	public void setContentInfo(String contentInfo) {
		this.contentInfo = contentInfo;
	}

	public List<TeamCommentModel> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<TeamCommentModel> commentList) {
		this.commentList = commentList;
	}

}
