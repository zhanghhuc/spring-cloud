package com.zssq.forum.vo;

import org.hibernate.validator.constraints.NotBlank;
/**
 * 回复评论（发表回复）输入参数对象
 * @ClassName SendReplyVo
 * @Description 
 * @author liurong
 * @date 2017年5月27日 上午10:56:50
 * @version 1.0
 * @since JDK 1.7
 */
public class SendReplyVo {
	
	@NotBlank(message = "{empty.message}")
	private String topicCode;
	
	@NotBlank(message = "{empty.message}")
	private String followTopicCode;
	
	@NotBlank(message = "{empty.message}")
	private String commentCode;
	
	@NotBlank(message = "{empty.message}")
	private String questionerCode;
	
	@NotBlank(message = "{empty.message}")
	private String replyContent;
	
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

	public String getCommentCode() {
		return commentCode;
	}

	public void setCommentCode(String commentCode) {
		this.commentCode = commentCode;
	}

	public String getQuestionerCode() {
		return questionerCode;
	}

	public void setQuestionerCode(String questionerCode) {
		this.questionerCode = questionerCode;
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
