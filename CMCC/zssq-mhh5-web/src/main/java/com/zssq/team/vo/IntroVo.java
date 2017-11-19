package com.zssq.team.vo;

import org.hibernate.validator.constraints.NotBlank;

public class IntroVo {

	@NotBlank(message = "{empty.message}")
	private String teamCode;
	
	/** 简介 */
	private String intro;

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
}
