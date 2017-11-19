package com.zssq.blog.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

/**
 * 
 * @ClassName: LikeReplyVO  
 * @Description: 点赞/取消点赞回复参数
 * @author ZKZ  
 * @date 2017年3月28日  
 *
 */
public class LikeReplyVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "{empty.message}")
	private String replyCode; // 回复编号
	@EnumType(allow = { "1", "2" })
	private String actionClass; // 操作类型：1.点赞；2.取消点赞
	@NotBlank(message = "{empty.message}")
	private String userCode; // 当前登录用户编号
	@NotBlank(message = "{empty.message}")
	private String token; // TOKEN
	

	public String getReplyCode() {
		return replyCode;
	}

	public void setReplyCode(String replyCode) {
		this.replyCode = replyCode;
	}

	public String getActionClass() {
		return actionClass;
	}

	public void setActionClass(String actionClass) {
		this.actionClass = actionClass;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
