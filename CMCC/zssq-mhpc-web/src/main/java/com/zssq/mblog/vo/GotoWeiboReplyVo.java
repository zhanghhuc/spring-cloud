package com.zssq.mblog.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;
import com.zssq.annotation.validation.LongType;

/**
 * 
    * @ClassName: GotoWeiboReplyVo  
    * @Description: 定位微博回复条件VO  
    * @author Mr.B  
    * @date 2017年3月22日  
    *
 */
public class GotoWeiboReplyVo {

	@NotBlank(message = "{empty.message}")
	private String userCode; // 用户CODE
	@NotBlank(message = "{empty.message}")
	private String token; // TOKEN
	@NotBlank(message = "{empty.message}")
	private String mblogCode; // 微博CODE
	@IntType(expression = ">0") 
	private String pageSize; // 每页条数
	@IntType(expression = ">=0") 
	private String pageNo; // 每页条数
	@LongType(expression = ">0")
	private String currentTime; // 时间分割线
	@NotBlank(message = "{empty.message}")
	private String commentCode; // 评论CODE
	@NotBlank(message = "{empty.message}")
	private String replyCode; // 回复CODE
	
	
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
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
	public String getMblogCode() {
		return mblogCode;
	}
	public void setMblogCode(String mblogCode) {
		this.mblogCode = mblogCode;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	
	public String getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}
	public String getCommentCode() {
		return commentCode;
	}
	public void setCommentCode(String commentCode) {
		this.commentCode = commentCode;
	}
	public String getReplyCode() {
		return replyCode;
	}
	public void setReplyCode(String replyCode) {
		this.replyCode = replyCode;
	}
	
	
}
