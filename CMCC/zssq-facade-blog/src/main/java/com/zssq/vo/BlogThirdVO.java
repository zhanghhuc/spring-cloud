package com.zssq.vo;

import java.io.Serializable;

/**
 * 
 * @ClassName: BlogThirdVO
 * @Description: 博客
 * @author ZKZ
 * @date 2017年3月21日
 *
 */
public class BlogThirdVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String subjectCode; // 内容编号
	private Byte subjectType; // 内容类型：1：应用；2：评论；3：回复
	private String upSubjectCode; // 上级内容编号
	private Byte actionClass; // 操作类型：1：屏蔽；2.取消
	private Long modifyTime; // 修改时间
	private Byte subClass; // 被订阅类型：1.个人；2.班组
	private String subUserCode; // 被订阅人编号
	
	private Byte blogDepend; // 博客从属关系
	private String teamCode; // 班组编号
	private String userCode; // 人员编号
	private Integer showSize; // 查询条数

	
	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public Byte getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(Byte subjectType) {
		this.subjectType = subjectType;
	}

	public String getUpSubjectCode() {
		return upSubjectCode;
	}

	public void setUpSubjectCode(String upSubjectCode) {
		this.upSubjectCode = upSubjectCode;
	}

	public Byte getActionClass() {
		return actionClass;
	}

	public void setActionClass(Byte actionClass) {
		this.actionClass = actionClass;
	}

	public Long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Byte getSubClass() {
		return subClass;
	}

	public void setSubClass(Byte subClass) {
		this.subClass = subClass;
	}

	public String getSubUserCode() {
		return subUserCode;
	}

	public void setSubUserCode(String subUserCode) {
		this.subUserCode = subUserCode;
	}

	public Byte getBlogDepend() {
		return blogDepend;
	}

	public void setBlogDepend(Byte blogDepend) {
		this.blogDepend = blogDepend;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Integer getShowSize() {
		return showSize;
	}

	public void setShowSize(Integer showSize) {
		this.showSize = showSize;
	}

}
