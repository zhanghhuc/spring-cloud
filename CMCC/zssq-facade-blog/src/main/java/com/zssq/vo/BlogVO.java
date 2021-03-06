package com.zssq.vo;

import java.io.Serializable;

/**
 * 
 * @ClassName: BlogVO
 * @Description: 博客
 * @author ZKZ
 * @date 2017年3月21日
 *
 */
public class BlogVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String blogCode; // 博客编号
	private String classCode; // 分类编号
	private String userCode; // 博客发布人编号
	private String teamCode; // 班组编号
	private String agentUserCode; // 代发人编号
	private Byte blogDepend; // 从属关系：1.个人；2.班组
	private Long queryTime; // 首次查询时间
	private String blogTags; // 标签
	private Long modifyTime; // 修改时间
	
	private String blogTitle; // 标题
	private String blogLogo; // 图片链接
	private String blogDigest; // 摘要
	private String contentInfo; // 正文信息
	private Long blogPlanPublishTime; // 定时发布时间
	
	private String loginUserCode; // 当前登录用户编号
	
	private Byte blogIsShield;//是否屏蔽：0.否；1.是；
	private String orgCode;//当前用户所在的组织
	
	
	public String getBlogCode() {
		return blogCode;
	}

	public void setBlogCode(String blogCode) {
		this.blogCode = blogCode;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getAgentUserCode() {
		return agentUserCode;
	}

	public void setAgentUserCode(String agentUserCode) {
		this.agentUserCode = agentUserCode;
	}

	public Byte getBlogDepend() {
		return blogDepend;
	}

	public void setBlogDepend(Byte blogDepend) {
		this.blogDepend = blogDepend;
	}

	public Long getQueryTime() {
		return queryTime;
	}

	public void setQueryTime(Long queryTime) {
		this.queryTime = queryTime;
	}

	public String getBlogTags() {
		return blogTags;
	}

	public void setBlogTags(String blogTags) {
		this.blogTags = blogTags;
	}

	public Long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
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

	public String getBlogLogo() {
		return blogLogo;
	}

	public void setBlogLogo(String blogLogo) {
		this.blogLogo = blogLogo;
	}

	public String getBlogDigest() {
		return blogDigest;
	}

	public void setBlogDigest(String blogDigest) {
		this.blogDigest = blogDigest;
	}

	public String getContentInfo() {
		return contentInfo;
	}

	public void setContentInfo(String contentInfo) {
		this.contentInfo = contentInfo;
	}

	public Long getBlogPlanPublishTime() {
		return blogPlanPublishTime;
	}

	public void setBlogPlanPublishTime(Long blogPlanPublishTime) {
		this.blogPlanPublishTime = blogPlanPublishTime;
	}

	public String getLoginUserCode() {
		return loginUserCode;
	}

	public void setLoginUserCode(String loginUserCode) {
		this.loginUserCode = loginUserCode;
	}

	public Byte getBlogIsShield() {
		return blogIsShield;
	}

	public void setBlogIsShield(Byte blogIsShield) {
		this.blogIsShield = blogIsShield;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
}
