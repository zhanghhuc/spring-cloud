package com.zssq.forum.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class GetTopicDetailVo implements Serializable{

	private static final long serialVersionUID = -8805062750611369329L;
	
	@NotBlank(message = "{empty.message}")
	private String topicCode;
	
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	@NotBlank(message = "{empty.message}")
	private String token;
	public String getTopicCode() {
		return topicCode;
	}
	public void setTopicCode(String topicCode) {
		this.topicCode = topicCode;
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
