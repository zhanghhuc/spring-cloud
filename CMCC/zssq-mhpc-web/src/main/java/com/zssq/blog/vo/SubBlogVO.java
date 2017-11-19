package com.zssq.blog.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

/**
 * 
 * @ClassName: SubBlogVO
 * @Description: 订阅/取消订阅博客参数
 * @author ZKZ
 * @date 2017年4月11日
 *
 */
public class SubBlogVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@EnumType(allow = { "1", "2" })
	private String subClass; // 订阅类型：1.个人；2.班组
	private String subUserCode = ""; // 被订阅人编号
	private String teamCode = ""; // 被订阅班组编号
	@EnumType(allow = { "1", "2" })
	private String actionClass; // 操作类型：1.点赞；2.取消点赞
	@NotBlank(message = "{empty.message}")
	private String userCode; // 当前登录用户编号
	@NotBlank(message = "{empty.message}")
	private String token; // TOKEN
	

	public String getSubClass() {
		return subClass;
	}

	public void setSubClass(String subClass) {
		this.subClass = subClass;
	}

	public String getSubUserCode() {
		return subUserCode;
	}

	public void setSubUserCode(String subUserCode) {
		this.subUserCode = subUserCode;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getActionClass() {
		return actionClass;
	}

	public void setActionClass(String actionClass) {
		this.actionClass = actionClass;
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
