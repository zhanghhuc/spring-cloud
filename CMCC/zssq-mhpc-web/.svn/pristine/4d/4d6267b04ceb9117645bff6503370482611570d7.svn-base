package com.zssq.blog.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @ClassName: PublishUserBlogVO
 * @Description: 发布个人博客参数
 * @author ZKZ
 * @date 2017年3月23日
 *
 */
public class PublishUserBlogVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String blogCode = ""; // 博客编号
	@Length(min=1, max=60)
	private String blogTitle; // 标题
	@NotBlank(message = "{empty.message}")
	private String classCode; // 分类编号
	private String oldClassCode; // 原分类编号
	private String blogTags = ""; // 标签
	private String blogPlanPublishTime = ""; // 定时发布时间
	private String blogDigest = ""; // 摘要
	private String contentInfo = ""; // 正文内容
	private String blogLogo = ""; // 图片链接
	private String draftCode = ""; // 草稿编号
	@NotBlank(message = "{empty.message}")
	private String userCode; // 当前登录用户编号
	@NotBlank(message = "{empty.message}")
	private String token; // TOKEN
	

	public String getBlogCode() {
		return blogCode;
	}

	public void setBlogCode(String blogCode) {
		this.blogCode = blogCode;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getOldClassCode() {
		return oldClassCode;
	}

	public void setOldClassCode(String oldClassCode) {
		this.oldClassCode = oldClassCode;
	}

	public String getBlogTags() {
		return blogTags;
	}

	public void setBlogTags(String blogTags) {
		this.blogTags = blogTags;
	}

	public String getBlogPlanPublishTime() {
		return blogPlanPublishTime;
	}

	public void setBlogPlanPublishTime(String blogPlanPublishTime) {
		this.blogPlanPublishTime = blogPlanPublishTime;
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

	public String getBlogLogo() {
		return blogLogo;
	}

	public void setBlogLogo(String blogLogo) {
		this.blogLogo = blogLogo;
	}

	public String getDraftCode() {
		return draftCode;
	}

	public void setDraftCode(String draftCode) {
		this.draftCode = draftCode;
	}

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

}
