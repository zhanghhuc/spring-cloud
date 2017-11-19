package com.zssq.relation.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

/**
 * 
 * @ClassName: ShowFrontTeamUserDynamicVO
 * @Description: 班组首页展示/取消首页展示动态参数
 * @author ZKZ
 * @date 2017年4月11日
 *
 */
public class ShowFrontTeamUserDynamicVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "{empty.message}")
	private String dynamicCode; // 动态编号
	@NotBlank(message = "{empty.message}")
	private String subjectCode; // 内容编号
	@NotBlank(message = "{empty.message}")
	private String teamCode; // 班组编号
	@EnumType(allow = { "1", "2" })
	private String actionClass; // 操作类型：1.首页展示；2.取消首页展示
	@NotBlank(message = "{empty.message}")
	private String userCode; // 当前登录用户编号
	@NotBlank(message = "{empty.message}")
	private String token; // TOKEN

	
	public String getDynamicCode() {
		return dynamicCode;
	}

	public void setDynamicCode(String dynamicCode) {
		this.dynamicCode = dynamicCode;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
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
