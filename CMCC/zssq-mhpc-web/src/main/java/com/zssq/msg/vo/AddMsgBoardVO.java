package com.zssq.msg.vo;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @ClassName: AddMsgBoardVO  
 * @Description: 添加留言vo  
 * @author YDB  
 * @date 2017年3月27日  
 *
 */
public class AddMsgBoardVO {
	
	//用户code
	@NotBlank(message = "{empty.message}")
	private String userCode;
	//被留言人code  收到留言的人的code
	@NotBlank(message = "{empty.message}")
	private String beMessageUserCode;
	//留言内容
	@NotBlank(message = "{empty.message}")
	private String content;

	
	

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getBeMessageUserCode() {
		return beMessageUserCode;
	}

	public void setBeMessageUserCode(String beMessageUserCode) {
		this.beMessageUserCode = beMessageUserCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	

}
