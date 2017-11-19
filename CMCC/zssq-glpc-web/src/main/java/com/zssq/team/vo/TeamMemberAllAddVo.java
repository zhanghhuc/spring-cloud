package com.zssq.team.vo;

import org.hibernate.validator.constraints.NotBlank;

public class TeamMemberAllAddVo {

	@NotBlank(message = "{empty.message}")
	private String teamCode;
	
	/** json字符串[{'userCode':'xxxx@hq.cmcc','isLeader','1'},{}] */
	@NotBlank(message = "{empty.message}")
	private String teamMember;
	
	@NotBlank(message = "{empty.message}")
	private String userCode;

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getTeamMember() {
		return teamMember;
	}

	public void setTeamMember(String teamMember) {
		this.teamMember = teamMember;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
}
