package com.zssq.vote.vo;

import org.hibernate.validator.constraints.NotBlank;
/**
 * 投票选项输入参数
 * @ClassName Option
 * @Description 
 * @author liurong
 * @date 2017年3月31日 下午4:39:05
 * @version 1.0
 * @since JDK 1.7
 */
public class Option {

	@NotBlank(message = "{empty.message}")
	private String orderNum;
	
	@NotBlank(message = "{empty.message}")
	private String content;
	
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
