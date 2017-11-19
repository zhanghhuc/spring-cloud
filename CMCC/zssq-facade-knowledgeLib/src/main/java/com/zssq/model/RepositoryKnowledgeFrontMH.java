package com.zssq.model;

import java.io.Serializable;

/**
 * 
 * @ClassName: RepositoryKnowledgeFrontMH  
 * @Description: 首页热区  
 * @author sry  
 * @date 2017年5月2日  
 *
 */
public class RepositoryKnowledgeFrontMH implements Serializable{
	    
	private static final long serialVersionUID = -9086672644369570054L;
	private String knowledgeCode;//知识编号
	private String knowledgeTitle;//知识标题
	private String knowledgeDigest;//知识摘要
	private String contentCode;//正文编号
	private Long createTime;//创建时间
	private Integer appendNum;//追加条数
	private Integer collectNum;//收藏量
	private Integer shareNum;//分享量
	private Integer praiseNum;//点赞量
	private String userCode;//用户编号
	private String userName;//用户名称
	private String userPhotoUrl;//用户头像
	private String orgCode;//机构编号
	private String orgName;//机构名称
	/**
	 * 展示类型：1，新发布知识
     *     		2，新补充
     *    		3，新追加
	 *	 (时间都取createTime) 
	 */
	private Byte showType;//展示类别
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
	public Byte getShowType() {
		return showType;
	}
	public void setShowType(Byte showType) {
		this.showType = showType;
	}
	
	
}
