package com.zssq.msg.vo;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @ClassName: DelCommemt  
 * @Description: 删除评论  
 * @author YDB  
 * @date 2017年4月1日  
 *
 */
public class DeleteCommemtVO {
	
	@NotBlank(message = "{empty.message}")
	private String msgCode;

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

}