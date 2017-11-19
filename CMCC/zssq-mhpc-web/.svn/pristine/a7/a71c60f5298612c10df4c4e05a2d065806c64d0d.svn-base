package com.zssq.forum.vo;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 回复主帖（发表跟帖）输入参数对象
 * @ClassName SendTopicFollowVo
 * @Description 
 * @author liurong
 * @date 2017年3月20日 下午2:39:47
 * @version 1.0
 * @since JDK 1.7
 */
public class SendTopicFollowVo {
	
	@NotBlank(message = "{empty.message}")
	private String topicCode;// 主帖CODE

	@NotBlank(message = "{empty.message}")
	private String replyContent;// 回复内容

	@NotBlank(message = "{empty.message}")
	private String userCode;// 用户CODE

	@NotBlank(message = "{empty.message}")
	private String token;// Token令牌

	public String getTopicCode() {
		return topicCode;
	}

	public void setTopicCode(String topicCode) {
		this.topicCode = topicCode;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
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
