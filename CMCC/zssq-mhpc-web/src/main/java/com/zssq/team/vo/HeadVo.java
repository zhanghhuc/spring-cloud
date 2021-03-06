package com.zssq.team.vo;

import org.hibernate.validator.constraints.NotBlank;

public class HeadVo {
	
	@NotBlank(message = "{empty.message}")
	private String teamCode;
	
	/** 头像 */
	@NotBlank(message = "{empty.message}")
	private String head;

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}
}
