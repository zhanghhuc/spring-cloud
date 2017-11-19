package com.zssq.forum.vo;

import org.hibernate.validator.constraints.NotBlank;
/**
 * 回复跟帖（发表评论）输入参数对象
 * @ClassName SendCommentVo
 * @Description 
 * @author liurong
 * @date 2017年5月27日 上午10:53:34
 * @version 1.0
 * @since JDK 1.7
 */
public class SendCommentVo {

	@NotBlank(message = "{empty.message}")
	private String topicCode;
	
	@NotBlank(message = "{empty.message}")
	private String followTopicCode;
	
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
