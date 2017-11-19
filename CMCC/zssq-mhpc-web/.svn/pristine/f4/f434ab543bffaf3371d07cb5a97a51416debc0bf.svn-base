package com.zssq.blog.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @ClassName: ForwardUserBlogVO
 * @Description: 转发博客参数
 * @author ZKZ
 * @date 2017年3月23日
 *
 */
public class ForwardUserBlogVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "{empty.message}")
	private String blogCode; // 博客编号
	@NotBlank(message = "{empty.message}")
	private String classCode; // 分类编号
	private String blogTags = ""; // 标签
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

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getBlogTags() {
		return blogTags;
	}

	public void setBlogTags(String blogTags) {
		this.blogTags = blogTags;
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
