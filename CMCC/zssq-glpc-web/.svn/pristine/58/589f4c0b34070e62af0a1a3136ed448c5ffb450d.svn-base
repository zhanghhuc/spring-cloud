package com.zssq.vote.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;

/**
 * @ClassName Option
 * @Description 投票选项输入参数
 * @author LiuYunLong
 * @date 2017年4月6日 下午4:39:05
 * @version 1.0
 * @since JDK 1.7
 */
public class Option implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@IntType(expression = ">0")
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
