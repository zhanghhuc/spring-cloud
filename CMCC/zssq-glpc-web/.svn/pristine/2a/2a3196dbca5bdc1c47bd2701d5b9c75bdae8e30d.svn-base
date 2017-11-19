package com.zssq.vote.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

public class SubmitOrNotExamineVo {

	@NotBlank(message = "{empty.message}")
	private String voteInfoCode;
	
	@EnumType(allow = { "1", "2" })
	private String operateType;
	
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

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
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
