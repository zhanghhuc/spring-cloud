package com.zssq.team.vo;

import org.hibernate.validator.constraints.NotBlank;

public class ReplyAddVo {

	/** 留言唯一标识 */
	@NotBlank(message = "{empty.message}")
	private String mesCode;
	
	/** 原始内容 */
	@NotBlank(message = "{empty.message}")
	private String originalContent;
	
	/** 回复内容 */
	@NotBlank(message = "{empty.message}")
	private String reply;
	
	/** 回复人 */
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	/** 被回复人 */
	@NotBlank(message = "{empty.message}")
	private String replyUserCode;
	
	/** 班组编码 */
	@NotBlank(message = "{empty.message}")
	private String teamCode;

	public String getMesCode() {
		return mesCode;
	}

	public void setMesCode(String mesCode) {
		this.mesCode = mesCode;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getReplyUserCode() {
		return replyUserCode;
	}

	public void setReplyUserCode(String replyUserCode) {
		this.replyUserCode = replyUserCode;
	}

	public String getOriginalContent() {
		return originalContent;
	}

	public void setOriginalContent(String originalContent) {
		this.originalContent = originalContent;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}
	
}
