package com.zssq.msg.vo;

import org.hibernate.validator.constraints.NotBlank;

public class MsgSysNoticeInfoVO {
	
	@NotBlank(message = "{empty.message}")
	private String msgCode;

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}
	
	

}
