package com.zssq.msg.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;

/**
 * 
 * @ClassName: DeleteBoardVO  
 * @Description: 删除留言  
 * @author YDB  
 * @date 2017年3月28日  
 *
 */
public class DeleteBoardVO {

	@NotBlank(message = "{empty.message}")
	private String messageBoardCode;
	

	public String getMessageBoardCode() {
		return messageBoardCode;
	}

	public void setMessageBoardCode(String messageBoardCode) {
		this.messageBoardCode = messageBoardCode;
	}
	
}