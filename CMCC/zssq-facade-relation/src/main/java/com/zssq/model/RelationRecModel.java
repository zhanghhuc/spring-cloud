package com.zssq.model;

import java.io.Serializable;

/**
 * 
 * @ClassName: RelationRecModel
 * @Description: 推荐详情参数
 * @author ZKZ
 * @date 2017年3月17日
 *
 */
public class RelationRecModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String recCode; // 推荐编号
	private Long createTime; // 创建时间
	private Long modifyTime; // 修改时间
	private String subjectCode; // 内容编号
	private Byte subjectClass; // 内容类型
	private Byte subjectDepend; // 内容从属关系
	private String teamCode; // 班组编号
	private String recUserCode; // 推荐人
	private Long recQualityTime; // 置精时间
	private String qualityCode; // 精华编号
	private String userCode; // 发布人
	private String subjectTitle; // 标题
	private Long subjectPublishTime; // 发布时间
	private Byte qualityIsTop; // 是否置顶
	private Integer qualitySeqNum; // 排序

	
	public String getRecCode() {
		return recCode;
	}

	public void setRecCode(String recCode) {
		this.recCode = recCode;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public Byte getSubjectClass() {
		return subjectClass;
	}

	public void setSubjectClass(Byte subjectClass) {
		this.subjectClass = subjectClass;
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

	public String getRecUserCode() {
		return recUserCode;
	}

	public void setRecUserCode(String recUserCode) {
		this.recUserCode = recUserCode;
	}

	public Long getRecQualityTime() {
		return recQualityTime;
	}

	public void setRecQualityTime(Long recQualityTime) {
		this.recQualityTime = recQualityTime;
	}

	public String getQualityCode() {
		return qualityCode;
	}

	public void setQualityCode(String qualityCode) {
		this.qualityCode = qualityCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getSubjectTitle() {
		return subjectTitle;
	}

	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}

	public Long getSubjectPublishTime() {
		return subjectPublishTime;
	}

	public void setSubjectPublishTime(Long subjectPublishTime) {
		this.subjectPublishTime = subjectPublishTime;
	}

	public Byte getQualityIsTop() {
		return qualityIsTop;
	}

	public void setQualityIsTop(Byte qualityIsTop) {
		this.qualityIsTop = qualityIsTop;
	}

	public Integer getQualitySeqNum() {
		return qualitySeqNum;
	}

	public void setQualitySeqNum(Integer qualitySeqNum) {
		this.qualitySeqNum = qualitySeqNum;
	}

}
