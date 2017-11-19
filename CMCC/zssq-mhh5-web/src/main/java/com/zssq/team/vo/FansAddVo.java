package com.zssq.team.vo;

import org.hibernate.validator.constraints.NotBlank;

public class FansAddVo {

	@NotBlank(message = "{empty.message}")
	private String teamCode;
	
	@NotBlank(message = "{empty.message}")
	private String userCode;

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
	
}
