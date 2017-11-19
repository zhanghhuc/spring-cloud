package com.zssq.team.vo;

import org.hibernate.validator.constraints.NotBlank;

public class TeamMemberDelVo {

	/** 班组成员唯一标识 */
	@NotBlank(message = "{empty.message}")
	private String teamMemberCode;
	
	@NotBlank(message = "{empty.message}")
	private String userCode;

	public String getTeamMemberCode() {
		return teamMemberCode;
	}

	public void setTeamMemberCode(String teamMemberCode) {
		this.teamMemberCode = teamMemberCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
}
