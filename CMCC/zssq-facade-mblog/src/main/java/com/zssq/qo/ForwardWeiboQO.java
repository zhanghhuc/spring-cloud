package com.zssq.qo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.zssq.dao.pojo.MblogInfo;

/**
 * 
    * @ClassName: MblogInfoForwardVo  
    * @Description: 转发微博信息  
    * @author Mr.B  
    * @date 2017年3月23日  
    *
 */
public class ForwardWeiboQO implements Serializable{

	private static final long serialVersionUID = 1L;

	/** 微博CODE **/
	private String mblogCode = "";
	/** 发布人 **/
	private String userCode = "";
	/** 组织CODE **/
	private String orgCode = "";
	/** 内容 **/
	private String content = "";
	/** 标题 **/
	private String title = "";
	/** 摘要 **/
	private String summary = "";
	/** at用户列表 **/
	private List<UserInfoQO> atUserCodes = new ArrayList<UserInfoQO>();
	/** 租户 **/
	private String tenatCode = "";
	/** 被转发微博来源CODE **/
	private String sourceMblogCode = "";
	/** 被转发微博来源用户CODE **/
	private String sourceUserCode = "";
    /** 动态CODE **/
	private String dynamicCode = "";
	/** 被转发微博CODE **/
	private String forMblogCode = "";  
	/** 被转发微博发布人CODE **/
	private String forUserCode = "";  
	/** 时间戳 **/
	private Long timeSign;
	/** 来源微博信息 **/
	private MblogInfo sourceMblogInfo;
	
	
	public MblogInfo getSourceMblogInfo() {
		return sourceMblogInfo;
	}
	public void setSourceMblogInfo(MblogInfo sourceMblogInfo) {
		this.sourceMblogInfo = sourceMblogInfo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getTimeSign() {
		return timeSign;
	}
	public void setTimeSign(Long timeSign) {
		this.timeSign = timeSign;
	}
	public String getForMblogCode() {
		return forMblogCode;
	}
	public void setForMblogCode(String forMblogCode) {
		this.forMblogCode = forMblogCode;
	}
	public String getForUserCode() {
		return forUserCode;
	}
	public void setForUserCode(String forUserCode) {
		this.forUserCode = forUserCode;
	}
	public String getDynamicCode() {
		return dynamicCode;
	}
	public void setDynamicCode(String dynamicCode) {
		this.dynamicCode = dynamicCode;
	}
	public String getMblogCode() {
		return mblogCode;
	}
	public void setMblogCode(String mblogCode) {
		this.mblogCode = mblogCode;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
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
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public List<UserInfoQO> getAtUserCodes() {
		return atUserCodes;
	}
	public void setAtUserCodes(List<UserInfoQO> atUserCodes) {
		this.atUserCodes = atUserCodes;
	}
	public String getTenatCode() {
		return tenatCode;
	}
	public void setTenatCode(String tenatCode) {
		this.tenatCode = tenatCode;
	}
	public String getSourceMblogCode() {
		return sourceMblogCode;
	}
	public void setSourceMblogCode(String sourceMblogCode) {
		this.sourceMblogCode = sourceMblogCode;
	}
	public String getSourceUserCode() {
		return sourceUserCode;
	}
	public void setSourceUserCode(String sourceUserCode) {
		this.sourceUserCode = sourceUserCode;
	}
}
