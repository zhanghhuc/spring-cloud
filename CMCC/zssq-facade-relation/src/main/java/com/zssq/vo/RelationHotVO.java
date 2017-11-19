package com.zssq.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @ClassName: RelationHotVO
 * @Description: 热点参数
 * @author ZKZ
 * @date 2017年3月16日
 *
 */
public class RelationHotVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String hotCode; // 热点编号
	private Byte hotStatus; // 热点状态：1.展示中；2.隐藏；---不传数据为展示全部
	private Byte actionClass; // 操作类型：1.隐藏；2.显示
	private Byte hotHideDepend; // 热点隐藏从属关系：1.班组；2.门户
	private String subjectTitle; // 标题内容
	private Byte subjectClass; // 应用类型：1.微博；2.博客；3.投票；4.活动；5.新闻公告；
	private Long beginTime; // 发布开始时间
	private Long endTime; // 发布结束时间
	
	private String teamCode; // 班组编号
	private String orgCode; // 门户编号
	private Integer showSize; // 查询条数
	private Integer pageNo; // 查询条数
	private Integer pageSize; // 查询条数
	private Long queryTime; // 首次查询时间
	
	private String tenantCode;//租户编号
	
	private List<String> userCodeList;	// 成员列表
	
	
	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private String userCode;//登陆用户code	
	public String getHotCode() {
		return hotCode;
	}

	public void setHotCode(String hotCode) {
		this.hotCode = hotCode;
	}

	public Byte getHotStatus() {
		return hotStatus;
	}

	public void setHotStatus(Byte hotStatus) {
		this.hotStatus = hotStatus;
	}

	public Byte getHotHideDepend() {
		return hotHideDepend;
	}

	public void setHotHideDepend(Byte hotHideDepend) {
		this.hotHideDepend = hotHideDepend;
	}

	public Byte getActionClass() {
		return actionClass;
	}

	public void setActionClass(Byte actionClass) {
		this.actionClass = actionClass;
	}

	public String getSubjectTitle() {
		return subjectTitle;
	}

	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}

	public Byte getSubjectClass() {
		return subjectClass;
	}

	public void setSubjectClass(Byte subjectClass) {
		this.subjectClass = subjectClass;
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

	public List<String> getUserCodeList() {
		return userCodeList;
	}

	public void setUserCodeList(List<String> userCodeList) {
		this.userCodeList = userCodeList;
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

	public Long getQueryTime() {
		return queryTime;
	}

	public void setQueryTime(Long queryTime) {
		this.queryTime = queryTime;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	
}
