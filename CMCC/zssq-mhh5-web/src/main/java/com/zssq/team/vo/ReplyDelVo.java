package com.zssq.team.vo;

import org.hibernate.validator.constraints.NotBlank;

public class ReplyDelVo {

	/** 唯一标识 */
	@NotBlank(message = "{empty.message}")
	private String replyCode;

	public String getReplyCode() {
		return replyCode;
	}

	public void setReplyCode(String replyCode) {
		this.replyCode = replyCode;
	}
	
}
