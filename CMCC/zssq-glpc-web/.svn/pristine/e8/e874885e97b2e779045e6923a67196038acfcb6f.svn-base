package com.zssq.team.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

public class TeamMemberLeaderVo {

	/** 班组成员唯一标识集合 */
	@NotBlank(message = "{empty.message}")
	private String teamMemberCodes;
	
	/** 是否为班组长 */
	@EnumType(allow={"1","0"})
	private String isLeader;
	
	@NotBlank(message = "{empty.message}")
	private String userCode;

	public String getTeamMemberCodes() {
		return teamMemberCodes;
	}

	public void setTeamMemberCodes(String teamMemberCodes) {
		this.teamMemberCodes = teamMemberCodes;
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
