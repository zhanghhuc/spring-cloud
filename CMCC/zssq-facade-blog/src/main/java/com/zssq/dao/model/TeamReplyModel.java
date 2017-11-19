package com.zssq.dao.model;

import java.io.Serializable;

/**
 * 
 * @ClassName: TeamReplyModel
 * @Description: 班组归档时回复详情
 * @author ZKZ
 * @date 2017年7月12日
 *
 */
public class TeamReplyModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String replyCode;
	private Long createTime;
	private String userCode;
	private String toReplyUserCode;
	private String replyContent;
	private Integer replyLikeNum;
	

	public String getReplyCode() {
		return replyCode;
	}

	public void setReplyCode(String replyCode) {
		this.replyCode = replyCode;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getToReplyUserCode() {
		return toReplyUserCode;
	}

	public void setToReplyUserCode(String toReplyUserCode) {
		this.toReplyUserCode = toReplyUserCode;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Integer getReplyLikeNum() {
		return replyLikeNum;
	}

	public void setReplyLikeNum(Integer replyLikeNum) {
		this.replyLikeNum = replyLikeNum;
	}

}
