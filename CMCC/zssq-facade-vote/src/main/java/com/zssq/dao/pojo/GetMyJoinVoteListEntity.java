package com.zssq.dao.pojo;

import java.io.Serializable;

/**
 * 查询我的参与记录时传递参数实体
 * @author POWER
 *
 */
public class GetMyJoinVoteListEntity implements Serializable {

	private static final long serialVersionUID = -7035973692649361820L;

	private String voteStatus;
	
	private Integer id;
	
	private Integer pageSize;
	
	private String tenantCode;
	
	private String userCode;

	public String getVoteStatus() {
		return voteStatus;
	}

	public void setVoteStatus(String voteStatus) {
		this.voteStatus = voteStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}
	
}
