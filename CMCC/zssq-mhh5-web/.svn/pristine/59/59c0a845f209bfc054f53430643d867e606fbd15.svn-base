package com.zssq.team.vo;

import org.hibernate.validator.constraints.NotBlank;

public class MessageAddVo {

	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	@NotBlank(message = "{empty.message}")
	private String teamCode;
	
	/** 留言内容 */
	@NotBlank(message = "{empty.message}")
	private String message;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}
	
}
