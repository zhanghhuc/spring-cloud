package com.zssq.msg.vo;

import org.hibernate.validator.constraints.NotBlank;
/**
 * 
 * @ClassName: DelPariseVO  
 * @Description: 点赞删除  
 * @author YDB  
 * @date 2017年4月1日  
 *
 */
public class DeleteSystemVO {
	
	@NotBlank(message = "{empty.message}")
	private String msgCode;
	@NotBlank(message = "{empty.message}")
	private String userCode;

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	
	

}
