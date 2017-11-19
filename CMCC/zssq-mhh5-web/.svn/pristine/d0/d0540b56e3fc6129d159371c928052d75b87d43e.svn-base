
package com.zssq.vote.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @ClassName CommentingVo
 * @Description 发表评论传入对象
 * @author LiuYunLong
 * @date 2017年3月30日 下午3:51:25
 * @version 1.0
 * @since JDK 1.7
 */
public class CommentingVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "{empty.message}")
	private String voteInfoCode; // 投票信息唯一标识CODE
	
	@NotBlank(message = "{empty.message}")
	private String content; // 评论内容
	
	@NotBlank(message = "{empty.message}")
	private String userCode; // 当前登录用户唯一标识CODE
	
	@NotBlank(message = "{empty.message}")
	private String token; // Token令牌

	public String getVoteInfoCode() {
		return voteInfoCode;
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
