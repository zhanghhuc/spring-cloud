package com.zssq.vo;

import java.io.Serializable;
/**
 * 
 * @ClassName: RepositoryShareVo  
 * @Description: 知识库分享  
 * @author sry  
 * @date 2017年4月28日  
 *
 */
public class RepositoryShareVo implements Serializable{

	private static final long serialVersionUID = -5640682673955384502L;
	
	private String knowledgeCode;//知识编号
	
	private String userCode;//用户编号

	public String getKnowledgeCode() {
		return knowledgeCode;
	}

	public void setKnowledgeCode(String knowledgeCode) {
		this.knowledgeCode = knowledgeCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	
}
