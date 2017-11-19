package com.zssq.model;

import java.io.Serializable;
/**
 * 
 * @ClassName: RepositoryKnowledgeMH  
 * @Description: 门户知识列表  
 * @author sry  
 * @date 2017年4月28日  
 *
 */
public class RepositoryKnowledgeMH implements Serializable{

	private static final long serialVersionUID = 124179802355536198L;

	private String knowledgeCode;//知识编号
	private String knowledgeTitle;//知识标题
	private String knowledgeDigest;//知识摘要
	private String contentCode;//知识正文编号
	private Long createTime;//创建时间
	private Integer appendNum;//追加数量
	private Integer collectNum;//收藏数量
	private Integer shareNum;//分享数量
	private Integer praiseNum;//点赞数量
	private String userCode;//用户编号
	private String userName;//用户名称
	private String userPhotoUrl;//用户头像
	private String orgCode;//组织编号
	private String orgName;//组织名称
	private Byte isCollect;//是否收藏
	private Byte isPraised;//是否点赞
	
	private String contentInfo;//单条时 返回内容详情
	
	private Byte canOpen;//是否可以展开
	public String getKnowledgeCode() {
		return knowledgeCode;
	}
	public void setKnowledgeCode(String knowledgeCode) {
		this.knowledgeCode = knowledgeCode;
	}
	public String getKnowledgeTitle() {
		return knowledgeTitle;
	}
	public void setKnowledgeTitle(String knowledgeTitle) {
		this.knowledgeTitle = knowledgeTitle;
	}
	public String getKnowledgeDigest() {
		return knowledgeDigest;
	}
	public void setKnowledgeDigest(String knowledgeDigest) {
		this.knowledgeDigest = knowledgeDigest;
	}
	public String getContentCode() {
		return contentCode;
	}
	public void setContentCode(String contentCode) {
		this.contentCode = contentCode;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public Integer getAppendNum() {
		return appendNum;
	}
	public void setAppendNum(Integer appendNum) {
		this.appendNum = appendNum;
	}
	public Integer getCollectNum() {
		return collectNum;
	}
	public void setCollectNum(Integer collectNum) {
		this.collectNum = collectNum;
	}
	public Integer getShareNum() {
		return shareNum;
	}
	public void setShareNum(Integer shareNum) {
		this.shareNum = shareNum;
	}
	public Integer getPraiseNum() {
		return praiseNum;
	}
	public void setPraiseNum(Integer praiseNum) {
		this.praiseNum = praiseNum;
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
	public Byte getIsCollect() {
		return isCollect;
	}
	public void setIsCollect(Byte isCollect) {
		this.isCollect = isCollect;
	}
	public Byte getIsPraised() {
		return isPraised;
	}
	public void setIsPraised(Byte isPraised) {
		this.isPraised = isPraised;
	}
	public String getContentInfo() {
		return contentInfo;
	}
	public void setContentInfo(String contentInfo) {
		this.contentInfo = contentInfo;
	}
	public Byte getCanOpen() {
		return canOpen;
	}
	public void setCanOpen(Byte canOpen) {
		this.canOpen = canOpen;
	}
	
	

}
