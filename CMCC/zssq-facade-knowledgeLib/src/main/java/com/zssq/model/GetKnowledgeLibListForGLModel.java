package com.zssq.model;

import java.io.Serializable;
/**
 * 
 * @ClassName: GetKnowledgeLibListForGLModel  
 * @Description: 管理端知识库  
 * @author sry  
 * @date 2017年6月19日  
 *
 */
public class GetKnowledgeLibListForGLModel implements Serializable {
	
	private static final long serialVersionUID = 2670843916235809932L;
	
    private Long id;

    private String repositoryCode;

    private Long createTime;

    private String repositoryTitle;

    private String repositorySummary;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRepositoryCode() {
		return repositoryCode;
	}

	public void setRepositoryCode(String repositoryCode) {
		this.repositoryCode = repositoryCode;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
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


}