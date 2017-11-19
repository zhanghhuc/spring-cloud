package com.zssq.model;

import java.io.Serializable;

/**
 * 
 * @ClassName: MessagePraiseModel  
 * @Description: 消息点赞Model
 * @author YDB  
 * @date 2017年3月28日  
 *
 */
public class MessagePraiseAddModel  implements Serializable{

	//被点赞人用户code
	private String 	userCode;
	//点赞人用户code
	private String 	praiseUserCode;
	//点赞类型：1微博,2微博-评论,3微博回复,4博客,5博客-评论,6博客-回复,7投票,8投票-评论,9投票-回复
	private Integer	praiseType;
	//点赞人的租户code
	private String  tenantCode;
	//原文code
	private String 	originalCode;
	//点赞人的公司编码
	private String	orgCode;
	//显示内容
	private String 	content;
	
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getPraiseUserCode() {
		return praiseUserCode;
	}
	public void setPraiseUserCode(String praiseUserCode) {
		this.praiseUserCode = praiseUserCode;
	}
	public Integer getPraiseType() {
		return praiseType;
	}
	public void setPraiseType(Integer praiseType) {
		this.praiseType = praiseType;
	}
	public String getTenantCode() {
		return tenantCode;
	}
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}
	public String getOriginalCode() {
		return originalCode;
	}
	public void setOriginalCode(String originalCode) {
		this.originalCode = originalCode;
	}
	
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
