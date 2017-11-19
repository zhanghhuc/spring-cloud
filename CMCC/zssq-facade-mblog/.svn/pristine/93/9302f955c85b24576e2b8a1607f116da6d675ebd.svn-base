package com.zssq.qo.sync;

import java.io.Serializable;

/**
 * 
    * @ClassName: MblogNumVo  
    * @Description: 微博数目的更新 VO ： 微博：转发，收藏，评论，点赞； 评论：回复，点赞； 回复：点赞  
    * @author Mr.B  
    * @date 2017年3月30日  
    *
 */
public class MblogNumVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** 动作类型： 2：收藏，3：评论，4：回复：5：点赞 **/
	private Byte actionType;
	/** 数目基数  加：1 减：-1 **/
	private Integer baseNum;
	/** 微博CODED **/
	private String mblogCode; 
	/** 评论CODE **/
	private String commentCode;
	/** 回复CODE **/
	private String replyCode;
	

	public String getCommentCode() {
		return commentCode;
	}
	public void setCommentCode(String commentCode) {
		this.commentCode = commentCode;
	}
	public String getMblogCode() {
		return mblogCode;
	}
	public void setMblogCode(String mblogCode) {
		this.mblogCode = mblogCode;
	}
	public String getReplyCode() {
		return replyCode;
	}
	public void setReplyCode(String replyCode) {
		this.replyCode = replyCode;
	}
	public Byte getActionType() {
		return actionType;
	}
	public void setActionType(Byte actionType) {
		this.actionType = actionType;
	}
	public Integer getBaseNum() {
		return baseNum;
	}
	public void setBaseNum(Integer baseNum) {
		this.baseNum = baseNum;
	}
	
	
	
}
