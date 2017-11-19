package com.zssq.msg.vo;

import org.hibernate.validator.constraints.NotBlank;

public class MsgInfoVO {

	@NotBlank(message = "{empty.message}")
	private String msgCode;
	
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}
	
}