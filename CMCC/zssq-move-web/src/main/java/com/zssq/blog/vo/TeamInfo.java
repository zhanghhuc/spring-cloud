package com.zssq.blog.vo;

import java.io.Serializable;

/**
 * 
 * @ClassName: TeamInfo  
 * @Description: 班组信息  
 * @author ZKZ  
 * @date 2017年5月24日  
 *
 */
public class TeamInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String teamCode;
	private String orgCode;
	private String tenantCode;
	

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

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

}
