package com.zssq.team.vo;

import com.zssq.annotation.validation.IntType;

public class SearchVo {
	
	private String teamName;

	@IntType(expression = ">0")
	private String pageSize;
	
	@IntType(expression = ">=0")
	private String pageNo;
	
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

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

}
