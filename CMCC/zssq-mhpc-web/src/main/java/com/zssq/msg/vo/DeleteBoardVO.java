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
	@IntType(expression = ">0")
	private int  type;
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getMessageBoardCode() {
		return messageBoardCode;
	}

	public void setMessageBoardCode(String messageBoardCode) {
		this.messageBoardCode = messageBoardCode;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
	
}
