package com.zssq.forum.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;

public class FixedFollowTopicVo {
	
	@NotBlank(message = "{empty.message}")
	private String topicCode;
	
	@NotBlank(message = "{empty.message}")
	private String followTopicCode;
	
	@IntType(expression=">0")
	private String pageSize;
	
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

	public String getFollowTopicCode() {
		return followTopicCode;
	}

	public void setFollowTopicCode(String followTopicCode) {
		this.followTopicCode = followTopicCode;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
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
