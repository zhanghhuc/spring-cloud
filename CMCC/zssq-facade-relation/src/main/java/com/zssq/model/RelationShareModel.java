package com.zssq.model;

import java.io.Serializable;

/**
 * 
 * @ClassName: RelationDynamicModel
 * @Description: 动态详情参数
 * @author ZKZ
 * @date 2017年3月17日
 *
 */
public class RelationShareModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String shareCode; // 分享编号
	private String dynamicCode; // 动态编号
	private Byte subjectClass; // 内容类型
	private String subjectCode; // 内容编号
	private String userCode; // 人员编号
	private Byte subjectDepend; // 内容从属关系
	private String teamCode; // 班组编号
	private Byte subjectSource; // 内容来源
	private String subjectTitle; // 内容标题
	private String subjectDigest; // 内容摘要
	private String subjectUrl; // 内容链接
	

	public String getShareCode() {
		return shareCode;
	}

	public void setShareCode(String shareCode) {
		this.shareCode = shareCode;
	}

	public String getDynamicCode() {
		return dynamicCode;
	}

	public void setDynamicCode(String dynamicCode) {
		this.dynamicCode = dynamicCode;
	}

	public Byte getSubjectClass() {
		return subjectClass;
	}

	public void setSubjectClass(Byte subjectClass) {
		this.subjectClass = subjectClass;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Byte getSubjectDepend() {
		return subjectDepend;
	}

	public void setSubjectDepend(Byte subjectDepend) {
		this.subjectDepend = subjectDepend;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public Byte getSubjectSource() {
		return subjectSource;
	}

	public void setSubjectSource(Byte subjectSource) {
		this.subjectSource = subjectSource;
	}

	public String getSubjectTitle() {
		return subjectTitle;
	}

	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}

	public String getSubjectDigest() {
		return subjectDigest;
	}

	public void setSubjectDigest(String subjectDigest) {
		this.subjectDigest = subjectDigest;
	}

	public String getSubjectUrl() {
		return subjectUrl;
	}

	public void setSubjectUrl(String subjectUrl) {
		this.subjectUrl = subjectUrl;
	}

}
