package com.zssq.team.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

public class TeamAddVo {

	/** 班组名称 */
	@NotBlank(message = "{empty.message}")
	private String teamName;
	
	/** 班组类型  1:1号 0:普通 */
	@EnumType(allow = {"0","1"})
	private String teamType;
	
	/** 组织机构编码 */
	@NotBlank(message = "{empty.message}")
	private String orgCode;
	
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamType() {
		return teamType;
	}

	public void setTeamType(String teamType) {
		this.teamType = teamType;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
}
