package com.zssq.team.vo;

import org.hibernate.validator.constraints.NotBlank;

public class DelVisitorVo {

	@NotBlank(message = "{empty.message}")
	private String teamCode;
	
	@NotBlank(message = "{empty.message}")
	private String visitCode;

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getVisitCode() {
		return visitCode;
	}

	public void setVisitCode(String visitCode) {
		this.visitCode = visitCode;
	}

}
