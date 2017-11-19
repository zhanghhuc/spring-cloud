package com.zssq.dao.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @ClassName: TeamCommentModel
 * @Description: 班组归档时评论详情
 * @author ZKZ
 * @date 2017年7月12日
 *
 */
public class TeamCommentModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String commentCode;
	private Long createTime;
	private String userCode;
	private String commentContent;
	private Integer commentLikeNum;
	private Integer commentReplyNum;
	private List<TeamReplyModel> replyList;

	
	public String getCommentCode() {
		return commentCode;
	}

	public void setCommentCode(String commentCode) {
		this.commentCode = commentCode;
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

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Integer getCommentLikeNum() {
		return commentLikeNum;
	}

	public void setCommentLikeNum(Integer commentLikeNum) {
		this.commentLikeNum = commentLikeNum;
	}

	public Integer getCommentReplyNum() {
		return commentReplyNum;
	}

	public void setCommentReplyNum(Integer commentReplyNum) {
		this.commentReplyNum = commentReplyNum;
	}

	public List<TeamReplyModel> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<TeamReplyModel> replyList) {
		this.replyList = replyList;
	}

}
