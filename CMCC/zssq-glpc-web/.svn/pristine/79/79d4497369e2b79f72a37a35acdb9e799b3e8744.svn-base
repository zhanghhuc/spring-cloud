package com.zssq.knowledgeLib.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class EditKnowledgeLibForGLVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "{empty.message}")
	private String orgCode;		//组织编号
	
	private String repositoryCode;		//知识库code(新建传””串)
	
	@NotBlank(message = "{empty.message}")
	private String repositoryTitle;		//知识库标题（后台需验重）
	
	@NotBlank(message = "{empty.message}")
	private String repositorySummary;		//知识库简介
	
	@NotBlank(message = "{empty.message}")
	private String userCode;		//当前登录用户的userCode
	
	@NotBlank(message = "{empty.message}")
	private String token;		//token

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getRepositoryCode() {
		return repositoryCode;
	}

	public void setRepositoryCode(String repositoryCode) {
		this.repositoryCode = repositoryCode;
	}

	public String getRepositoryTitle() {
		return repositoryTitle;
	}

	public void setRepositoryTitle(String repositoryTitle) {
		this.repositoryTitle = repositoryTitle;
	}

	public String getRepositorySummary() {
		return repositorySummary;
	}

	public void setRepositorySummary(String repositorySummary) {
		this.repositorySummary = repositorySummary;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}