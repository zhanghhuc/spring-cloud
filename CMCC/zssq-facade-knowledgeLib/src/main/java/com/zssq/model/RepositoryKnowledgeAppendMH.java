package com.zssq.model;

import java.io.Serializable;
/**
 * 
 * @ClassName: RepositoryKnowledgeAppendMH  
 * @Description: 知识追加  
 * @author sry  
 * @date 2017年5月2日  
 *
 */
public class RepositoryKnowledgeAppendMH implements Serializable{

	private static final long serialVersionUID = 5345076263216676759L;
	
	private String appendCode;//追加编号
	private Long createTime;//创建时间
	private String appendContent;//追加内容
	private Byte isSelfAppend;//是否自己追加
	private String userCode;//用户编号
	private String userName;//用户名称
	private String userPhotoUrl;//用户头像
	private String orgCode;//组织code
	private String orgName;//组织名称
	private Integer praiseNum;//点赞数
	private Byte isPraised;//当前用户是否点赞
	private String appendDigest;//追加摘要
	public String getAppendCode() {
		return appendCode;
	}
	public void setAppendCode(String appendCode) {
		this.appendCode = appendCode;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public String getAppendContent() {
		return appendContent;
	}
	public void setAppendContent(String appendContent) {
		this.appendContent = appendContent;
	}
	public Byte getIsSelfAppend() {
		return isSelfAppend;
	}
	public void setIsSelfAppend(Byte isSelfAppend) {
		this.isSelfAppend = isSelfAppend;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhotoUrl() {
		return userPhotoUrl;
	}
	public void setUserPhotoUrl(String userPhotoUrl) {
		this.userPhotoUrl = userPhotoUrl;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public Integer getPraiseNum() {
		return praiseNum;
	}
	public void setPraiseNum(Integer praiseNum) {
		this.praiseNum = praiseNum;
	}
	public Byte getIsPraised() {
		return isPraised;
	}
	public void setIsPraised(Byte isPraised) {
		this.isPraised = isPraised;
	}
	public String getAppendDigest() {
		return appendDigest;
	}
	public void setAppendDigest(String appendDigest) {
		this.appendDigest = appendDigest;
	}
	
	

}
