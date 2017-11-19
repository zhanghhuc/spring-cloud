package com.zssq.msg.vo;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @ClassName: DeleteBoardVO  
 * @Description: 删除留言  
 * @author YDB  
 * @date 2017年3月28日  
 *
 */
public class DeleteBoardReplyVO {

	@NotBlank(message = "{empty.message}")
	private String messageReplyCode;

	public String getMessageReplyCode() {
		return messageReplyCode;
	}

	public void setMessageReplyCode(String messageReplyCode) {
		this.messageReplyCode = messageReplyCode;
	}
}
