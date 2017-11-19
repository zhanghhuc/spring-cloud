package com.zssq.team.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

public class TeamMemberAddVo {

	@NotBlank(message = "{empty.message}")
	private String teamCode;
	
	@NotBlank(message = "{empty.message}")
	private String userCodes;
	
	@EnumType(allow={"1","0"})
	private String isLeader;
	
	private String userCode;
	
	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getUserCodes() {
		return userCodes;
	}

	public void setUserCodes(String userCodes) {
		this.userCodes = userCodes;
	}

	public String getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(String isLeader) {
		this.isLeader = isLeader;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

}
