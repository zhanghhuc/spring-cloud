package com.zssq.qo;

import java.io.Serializable;

/**
 * 
    * @ClassName: CommentInfoQO  
    * @Description: 评论操作信息  
    * @author Mr.B  
    * @date 2017年3月17日  
    *
 */
public class CommentInfoQO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** 评论人 **/
	private String myUserCode = "";
	/** 被评论微博 **/
	private String mblogCode = "";
	/** 评论内容 **/
	private String content = "";
	/** atUser列表 **/
	/*private List<UserInfoQO> atUserCodes = new ArrayList<UserInfoQO>();*/
	/** 评论CODE **/
	private String commentCode;
	/** 每页条数 **/
	private Integer pageSize = null;
	/** 页码 **/
	private Integer pageNo = null;
	/** 分割数据 **/
	private Long lineTime;
	/** 是否点赞 **/
	private Byte isPraise = 0;
	/** 组织CODE **/
	private String orgCode = "";
	/** 租户 **/
	private String tenantCode = "";
	
	public Integer getLimitStart() {
		if(null != pageNo && null != pageSize && pageSize > 0){
			return pageNo*pageSize;
		}
		return null;
	}
	public Integer getLimitSize() {
		if(null != pageSize && pageSize > 0){
			return pageSize;
		}
		return null;
		
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getTenantCode() {
		return tenantCode;
	}
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}
	
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Long getLineTime() {
		return lineTime;
	}
	public void setLineTime(Long lineTime) {
		this.lineTime = lineTime;
	}
	public Byte getIsPraise() {
		return isPraise;
	}
	public void setIsPraise(Byte isPraise) {
		this.isPraise = isPraise;
	}
	public String getCommentCode() {
		return commentCode;
	}
	public void setCommentCode(String commentCode) {
		this.commentCode = commentCode;
	}
	public String getMyUserCode() {
		return myUserCode;
	}
	public void setMyUserCode(String myUserCode) {
		this.myUserCode = myUserCode;
	}
	public String getMblogCode() {
		return mblogCode;
	}
	public void setMblogCode(String mblogCode) {
		this.mblogCode = mblogCode;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
