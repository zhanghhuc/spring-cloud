package com.zssq.vo;

import java.io.Serializable;

/**
 * 
 * @ClassName: RelationRecVO
 * @Description: 推荐参数
 * @author ZKZ
 * @date 2017年3月16日
 *
 */
public class RelationRecVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String subjectCode; // 内容编号
	private String orgCode; // 门户编号
	private String recToOrgCode; // 被推荐门户编号
	private Long modifyTime; // 修改时间
	

	private String recCode; // 推荐编号
	private String qualityCode; // 精品编号
	private String subjectTitle; // 标题
	private Byte recStatus; // 推荐状态：1.未处理；2.展示中；3.已忽略；4.已取消
	private Byte subjectClass; // 应用类型：1.微博；2.博客；
	private String recUserCode; // 推荐人编号
	private Long beginTime; // 推荐开始时间
	private Long endTime; // 推荐结束时间

	private String teamCode; // 班组编号
	
	private Integer showSize; // 查询条数
	private Long queryTime; // 首次查询时间
	
	

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getRecToOrgCode() {
		return recToOrgCode;
	}

	public void setRecToOrgCode(String recToOrgCode) {
		this.recToOrgCode = recToOrgCode;
	}

	public Long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getRecCode() {
		return recCode;
	}

	public void setRecCode(String recCode) {
		this.recCode = recCode;
	}

	public String getQualityCode() {
		return qualityCode;
	}

	public void setQualityCode(String qualityCode) {
		this.qualityCode = qualityCode;
	}

	public String getSubjectTitle() {
		return subjectTitle;
	}

	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}

	public Byte getRecStatus() {
		return recStatus;
	}

	public void setRecStatus(Byte recStatus) {
		this.recStatus = recStatus;
	}

	public Byte getSubjectClass() {
		return subjectClass;
	}

	public void setSubjectClass(Byte subjectClass) {
		this.subjectClass = subjectClass;
	}

	public String getRecUserCode() {
		return recUserCode;
	}

	public void setRecUserCode(String recUserCode) {
		this.recUserCode = recUserCode;
	}

	public Long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Long beginTime) {
		this.beginTime = beginTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Integer getShowSize() {
		return showSize;
	}

	public void setShowSize(Integer showSize) {
		this.showSize = showSize;
	}

	public Long getQueryTime() {
		return queryTime;
	}

	public void setQueryTime(Long queryTime) {
		this.queryTime = queryTime;
	}

}
