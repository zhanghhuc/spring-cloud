package com.zssq.vote.vo;

import org.hibernate.validator.constraints.NotBlank;

public class GetVoteInfoVo {

	@NotBlank(message = "{empty.message}")
	private String voteInfoCode;
	
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	@NotBlank(message = "{empty.message}")
	private String token;

	public String getVoteInfoCode() {
		return voteInfoCode;
	}

	public void setVoteInfoCode(String voteInfoCode) {
		this.voteInfoCode = voteInfoCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
