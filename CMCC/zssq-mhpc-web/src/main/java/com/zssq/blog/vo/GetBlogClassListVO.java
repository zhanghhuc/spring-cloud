package com.zssq.blog.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

/**
 * 
 * @ClassName: GetBlogClassListVO
 * @Description: 查询博客分类列表参数
 * @author ZKZ
 * @date 2017年3月21日
 *
 */
public class GetBlogClassListVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@EnumType(allow = { "1", "2" })
	private String classDepend; // 从属关系：1.个人；2.班组
	private String classUserCode = ""; // 分类创建人编号
	private String teamCode = ""; // 班组编号
	@NotBlank(message = "{empty.message}")
	private String userCode; // 当前登录用户编号
	@NotBlank(message = "{empty.message}")
	private String token; // TOKEN

	
	public String getClassDepend() {
		return classDepend;
	}

	public void setClassDepend(String classDepend) {
		this.classDepend = classDepend;
	}

	public String getClassUserCode() {
		return classUserCode;
	}

	public void setClassUserCode(String classUserCode) {
		this.classUserCode = classUserCode;
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
