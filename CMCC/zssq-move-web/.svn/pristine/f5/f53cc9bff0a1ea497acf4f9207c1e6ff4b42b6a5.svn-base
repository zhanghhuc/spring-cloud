package com.zssq.relation.vo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MblogNum implements RowMapper<MblogNum>,Serializable {

	private static final long serialVersionUID = 1L;
	private String subjectCode = "";
	

	private Integer praise_num = 0;//点赞数
	private Integer collect_num = 0;//收藏数
	private Integer commentNum = 0;
    private Integer recommendNum = 0;
    private Integer forwardNum = 0;
    private Integer replyNum = 0;
    private Long createTime = 0L;

    


	


	
	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	

	public Integer getPraise_num() {
		return praise_num;
	}

	public void setPraise_num(Integer praise_num) {
		this.praise_num = praise_num;
	}

	public Integer getCollect_num() {
		return collect_num;
	}

	public void setCollect_num(Integer collect_num) {
		this.collect_num = collect_num;
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public Integer getRecommendNum() {
		return recommendNum;
	}

	public void setRecommendNum(Integer recommendNum) {
		this.recommendNum = recommendNum;
	}

	public Integer getForwardNum() {
		return forwardNum;
	}

	public void setForwardNum(Integer forwardNum) {
		this.forwardNum = forwardNum;
	}

	public Integer getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(Integer replyNum) {
		this.replyNum = replyNum;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}


	@Override
	public MblogNum mapRow(ResultSet rs, int arg1) throws SQLException {
		MblogNum mblogNum = new MblogNum();
		mblogNum.setCreateTime(rs.getLong("create_time"));
		mblogNum.setCollect_num(rs.getInt("collect_num"));
		mblogNum.setCommentNum(rs.getInt("comment_num"));
		mblogNum.setForwardNum(rs.getInt("forward_num"));
		mblogNum.setPraise_num(rs.getInt("praise_num"));
		mblogNum.setReplyNum(rs.getInt("reply_num"));
		mblogNum.setSubjectCode(rs.getString("subjectCode"));
		return null;
	}
   
}
