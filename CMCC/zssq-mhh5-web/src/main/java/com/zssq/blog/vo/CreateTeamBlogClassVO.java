package com.zssq.blog.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @ClassName: CreateTeamBlogClassVO
 * @Description: 新建班组分类参数
 * @author ZKZ
 * @date 2017年3月21日
 *
 */
public class CreateTeamBlogClassVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Length(min=1, max=20)
	private String className; // 分类名称
	@NotBlank(message = "{empty.message}")
	private String teamCode; // 班组编号
	@NotBlank(message = "{empty.message}")
	private String userCode; // 当前登录用户编号
	@NotBlank(message = "{empty.message}")
	private String token; // TOKEN

	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
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
