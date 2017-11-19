package com.zssq.team.vo;

import org.hibernate.validator.constraints.NotBlank;

public class TeamInfoVo {

	@NotBlank(message = "{empty.message}")
	private String teamCode;

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}
	
}
