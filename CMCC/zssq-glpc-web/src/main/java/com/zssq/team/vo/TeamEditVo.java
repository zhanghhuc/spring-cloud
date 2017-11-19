package com.zssq.team.vo;

import org.hibernate.validator.constraints.NotBlank;

public class TeamEditVo {

	@NotBlank(message = "{empty.message}")
	private String teamCode;
	
	@NotBlank(message = "{empty.message}")
	private String teamName;
	
	@NotBlank(message = "{empty.message}")
	private String teamType;
	
	@NotBlank(message = "{empty.message}")
	private String orgCode;

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

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
}
