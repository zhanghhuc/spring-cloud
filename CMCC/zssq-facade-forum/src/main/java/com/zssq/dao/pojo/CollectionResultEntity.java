package com.zssq.dao.pojo;

import java.io.Serializable;

public class CollectionResultEntity implements Serializable{
	
	private static final long serialVersionUID = 7480296818046625372L;
	//收藏id
	private String id;
	//收藏code
	private String collectionCode;
	//收藏信息创建时间
	private String cCreateTime;
	//收藏人code
	private String cMemberCode;
	//主贴code
	private String cTopicCode;
	//主贴创建时间
	private String tCreateTime;
	//主贴所在论坛code
	private String tPlateCode;
	//发帖人code
	private String tPostManCode;
	//主贴内容
	private String content;
	// 主帖标题
	private String subject;
	// 论坛所属CODE
	private String belongCode;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCollectionCode() {
		return collectionCode;
	}
	public void setCollectionCode(String collectionCode) {
		this.collectionCode = collectionCode;
	}
	public String getcCreateTime() {
		return cCreateTime;
	}
	public void setcCreateTime(String cCreateTime) {
		this.cCreateTime = cCreateTime;
	}
	public String getcMemberCode() {
		return cMemberCode;
	}
	public void setcMemberCode(String cMemberCode) {
		this.cMemberCode = cMemberCode;
	}
	public String getcTopicCode() {
		return cTopicCode;
	}
	public void setcTopicCode(String cTopicCode) {
		this.cTopicCode = cTopicCode;
	}
	public String gettCreateTime() {
		return tCreateTime;
	}
	public void settCreateTime(String tCreateTime) {
		this.tCreateTime = tCreateTime;
	}
	public String gettPlateCode() {
		return tPlateCode;
	}
	public void settPlateCode(String tPlateCode) {
		this.tPlateCode = tPlateCode;
	}
	public String gettPostManCode() {
		return tPostManCode;
	}
	public void settPostManCode(String tPostManCode) {
		this.tPostManCode = tPostManCode;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBelongCode() {
		return belongCode;
	}
	public void setBelongCode(String belongCode) {
		this.belongCode = belongCode;
	}
}
