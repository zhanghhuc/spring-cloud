package com.zssq.knowledgeLib.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

/**
 * 
 * @ClassName: PublishKnowledgeVo  
 * @Description: 发布内容  
 * @author sry  
 * @date 2017年5月2日  
 *
 */
public class PublishKnowledgeVo {
	
	@NotBlank(message = "{empty.message}")
	private String repositoryCode;//知识库编号
	@NotBlank(message = "{empty.message}")
	private String knowledgeTitle;//知识标题
	/*@NotBlank(message = "{empty.message}")*/
	private String knowledgeDigest;//知识摘要
	/*@NotBlank(message = "{empty.message}")*/
	private String contentInfo;//知识内容
	@NotBlank(message = "{empty.message}")
	private String userCode;//用户编号
	@NotBlank(message = "{empty.message}")
	private String token;//token
	@EnumType(allow = { "0", "1" })//是否可以展开
	private String canOpen;
	
	public String getRepositoryCode() {
		return repositoryCode;
	}
	public void setRepositoryCode(String repositoryCode) {
		this.repositoryCode = repositoryCode;
	}
	public String getKnowledgeTitle() {
		return knowledgeTitle;
	}
	public void setKnowledgeTitle(String knowledgeTitle) {
		this.knowledgeTitle = knowledgeTitle;
	}
	public String getKnowledgeDigest() {
		return knowledgeDigest;
	}
	public void setKnowledgeDigest(String knowledgeDigest) {
		this.knowledgeDigest = knowledgeDigest;
	}
	public String getContentInfo() {
		return contentInfo;
	}
	public void setContentInfo(String contentInfo) {
		this.contentInfo = contentInfo;
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
	public String getCanOpen() {
		return canOpen;
	}
	public void setCanOpen(String canOpen) {
		this.canOpen = canOpen;
	}
	
	

}
