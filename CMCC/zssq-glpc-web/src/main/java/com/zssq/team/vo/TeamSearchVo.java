package com.zssq.team.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;

public class TeamSearchVo {

	/** 每页记录数 */
	@IntType(expression = ">0")
	private String pageSize;
	
	/** 页码 */
	@IntType(expression = ">=0")
	private String pageNo;
	
	/** 行政组织编号 */
	@NotBlank(message = "{empty.message}")
	private String orgCode;
	
	/** 班组名称 */
	private String teamName;
	
	/** 开始时间 */
	private String startDate;
	
	/** 结束时间 */
	private String endDate;
	
	/** 班组长名称 */
	private String leaderName;
	
	/** 班组类型 */
	private String teamType;

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public String getTeamType() {
		return teamType;
	}

	public void setTeamType(String teamType) {
		this.teamType = teamType;
	}

}
