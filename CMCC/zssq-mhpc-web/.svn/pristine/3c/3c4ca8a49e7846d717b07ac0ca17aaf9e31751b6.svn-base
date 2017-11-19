package com.zssq.vote.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @ClassName ReplyingVo
 * @Description 发表回复传入对象
 * @author LiuYunLong
 * @date 2017年3月30日 下午3:54:43
 * @version 1.0
 * @since JDK 1.7
 */
public class ReplyingVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "{empty.message}")
	private String voteInfoCode;  // 投票信息唯一标识CODE
	
	@NotBlank(message = "{empty.message}")
	private String commentCode;  // 评论CODE
	
	@NotBlank(message = "{empty.message}")
	private String questionerCode;  // 被回复人CODE
	
	@NotBlank(message = "{empty.message}")
	private String content;  // 评论内容
	
	@NotBlank(message = "{empty.message}")
	private String userCode;  // 当前登录用户唯一标识CODE
	
	@NotBlank(message = "{empty.message}")
	private String token;  // Token令牌

	public String getVoteInfoCode() {
		return voteInfoCode;
	}

	public String getCommentCode() {
		return commentCode;
	}

	public String getQuestionerCode() {
		return questionerCode;
	}

	public String getContent() {
		return content;
	}

	public String getUserCode() {
		return userCode;
	}

	public String getToken() {
		return token;
	}

	public void setVoteInfoCode(String voteInfoCode) {
		this.voteInfoCode = voteInfoCode;
	}

	public void setCommentCode(String commentCode) {
		this.commentCode = commentCode;
	}

	public void setQuestionerCode(String questionerCode) {
		this.questionerCode = questionerCode;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
