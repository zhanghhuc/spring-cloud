package com.zssq.blog.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @ClassName: UpdateTeamBlogClassVO
 * @Description: 修改班组博客分类参数
 * @author ZKZ
 * @date 2017年4月11日
 *
 */
public class UpdateTeamBlogClassVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "{empty.message}")
	private String teamCode; // 班组编号
	@NotBlank(message = "{empty.message}")
	private String blogCode; // 博客编号
	@NotBlank(message = "{empty.message}")
	private String originalClassCode; // 原分类编号
	@NotBlank(message = "{empty.message}")
	private String classCode; // 分类编号
	@NotBlank(message = "{empty.message}")
	private String userCode; // 当前登录用户编号
	@NotBlank(message = "{empty.message}")
	private String token; // TOKEN

	
	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getBlogCode() {
		return blogCode;
	}

	public void setBlogCode(String blogCode) {
		this.blogCode = blogCode;
	}

	public String getOriginalClassCode() {
		return originalClassCode;
	}

	public void setOriginalClassCode(String originalClassCode) {
		this.originalClassCode = originalClassCode;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
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
