package com.zssq.vo;

import java.io.Serializable;

/**
 * 
 * @ClassName: RelationQualityVO
 * @Description: 精华参数
 * @author ZKZ
 * @date 2017年3月16日
 *
 */
public class RelationQualityVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userCode; // 人员编号
	private String teamCode; // 班组编号
	private String orgCode; // 门户编号
	private String qualityUserCode; // 精华所属人员编号
	private Long queryTime; // 首次查询时间
	private Byte subjectClass; // 应用类型：1.微博；2.博客；
	private Integer showSize; // 显示条数
	private String subjectCode; // 应用编号
	private String recCode; // 推荐编号
	private Long modifyTime; // 修改时间
	private Byte actionClass; // 操作类型：（精华排序）1.上移；2.下移;（是否置顶）1.置顶；2.取消置顶;
	private String qualityCode; // 精华编号
	private Integer qualitySeqNum; // 排序号
	private Long beginTime; // 推荐开始时间
	private Long endTime; // 推荐结束时间
	private String subjectTitle;// 标题
	

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
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

	public String getQualityUserCode() {
		return qualityUserCode;
	}

	public void setQualityUserCode(String qualityUserCode) {
		this.qualityUserCode = qualityUserCode;
	}

	public Long getQueryTime() {
		return queryTime;
	}

	public void setQueryTime(Long queryTime) {
		this.queryTime = queryTime;
	}

	public Byte getSubjectClass() {
		return subjectClass;
	}

	public void setSubjectClass(Byte subjectClass) {
		this.subjectClass = subjectClass;
	}

	public Integer getShowSize() {
		return showSize;
	}

	public void setShowSize(Integer showSize) {
		this.showSize = showSize;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getRecCode() {
		return recCode;
	}

	public void setRecCode(String recCode) {
		this.recCode = recCode;
	}

	public Long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Byte getActionClass() {
		return actionClass;
	}

	public void setActionClass(Byte actionClass) {
		this.actionClass = actionClass;
	}

	public String getQualityCode() {
		return qualityCode;
	}

	public void setQualityCode(String qualityCode) {
		this.qualityCode = qualityCode;
	}

	public Integer getQualitySeqNum() {
		return qualitySeqNum;
	}

	public void setQualitySeqNum(Integer qualitySeqNum) {
		this.qualitySeqNum = qualitySeqNum;
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

	public String getSubjectTitle() {
		return subjectTitle;
	}

	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}

}
