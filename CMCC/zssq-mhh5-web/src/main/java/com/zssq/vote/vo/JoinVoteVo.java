package com.zssq.vote.vo;

import org.hibernate.validator.constraints.NotBlank;

public class JoinVoteVo {

	@NotBlank(message = "{empty.message}")
	private String voteInfoCode;
	
	@NotBlank(message = "{empty.message}")
	private String optionsCode;
	
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
	public String getOptionsCode() {
		return optionsCode;
	}
	public void setOptionsCode(String optionsCode) {
		this.optionsCode = optionsCode;
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
