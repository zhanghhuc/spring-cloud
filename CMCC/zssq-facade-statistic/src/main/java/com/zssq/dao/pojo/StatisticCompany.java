package com.zssq.dao.pojo;

import com.zssq.pojo.BasePage;

public class StatisticCompany extends BasePage {

	private static final long serialVersionUID = 1L;

	private String orgCode;
	
	private String orgName;
	
    private Integer visitCount = 0;

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}




    
    
}