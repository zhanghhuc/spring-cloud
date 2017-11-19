package com.zssq.blog.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @ClassName: PublishUserBlogVO
 * @Description: 发布个人博客参数
 * @author guoyang
 * @date 2017年5月17日
 *
 */
public class PublishUserBlogVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Length(min=1, max=60)
	private String blogTitle; // 标题
	private String blogDigest = ""; // 摘要
	private String contentInfo = ""; // 正文内容
	private String blogLogo = ""; // 图片链接
	@NotBlank(message = "{empty.message}")
	private String userCode; // 当前登录用户编号
	@NotBlank(message = "{empty.message}")
	private String token; // TOKEN
	public String getBlogTitle() {
		return blogTitle;
	}
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
