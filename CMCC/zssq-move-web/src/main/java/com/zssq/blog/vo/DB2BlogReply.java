package com.zssq.blog.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * @ClassName: DB2BlogReply
 * @Description: 博客评论
 * @author ZKZ
 * @date 2017年5月19日
 *
 */
public class DB2BlogReply implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer blogId;
	private Integer userId;
	private Integer parentsId;
	private Integer toReplyUserId;
	private Timestamp createTime;
	private Timestamp updateTime;
	private String replyContent;
	private Integer flagReply;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBlogId() {
		return blogId;
	}

	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getParentsId() {
		return parentsId;
	}

	public void setParentsId(Integer parentsId) {
		this.parentsId = parentsId;
	}

	public Integer getToReplyUserId() {
		return toReplyUserId;
	}

	public void setToReplyUserId(Integer toReplyUserId) {
		this.toReplyUserId = toReplyUserId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Integer getFlagReply() {
		return flagReply;
	}

	public void setFlagReply(Integer flagReply) {
		this.flagReply = flagReply;
	}

}
