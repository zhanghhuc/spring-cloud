package com.zssq.team.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;

public class ReceiveTeamVo {

	@IntType(expression = ">0")
	private String pageSize;
	
	@IntType(expression = ">=0")
	private String pageNo;
	
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	/** 班组名称 */
	private String teamName;
	
	/** 组织机构编码 */
	private String orgCode;
	
	/** 班组类型  1:1号 0:普通 */
	private String teamType;
	
	/** 开始日期 */
	private String startDate;
	
	/** 结束日期 */
	private String endDate;
	
	/** 班组长名称 */
	private String leaderName;

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

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getTeamType() {
		return teamType;
	}

	public void setTeamType(String teamType) {
		this.teamType = teamType;
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

}
