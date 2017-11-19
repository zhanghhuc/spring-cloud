package com.zssq.vo;

import java.io.Serializable;
/**
 * 
 * @ClassName: RepositoryInfoVo  
 * @Description: 知识库vo  
 * @author guoyang  
 * @date 2017年5月2日  
 *
 */
public class EditKnowledgeLibForForInGLVO implements Serializable{

	private static final long serialVersionUID = -9134506275308539372L;
	private String orgCode;
	private String repositoryCode;
	private String repositoryTitle;
	private String repositorySummary;
	private String userCode;
	private String token;
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

	
	
}
