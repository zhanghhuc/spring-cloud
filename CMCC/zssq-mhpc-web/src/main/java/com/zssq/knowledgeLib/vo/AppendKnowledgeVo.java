package com.zssq.knowledgeLib.vo;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @ClassName: AppendKnowledgeVo  
 * @Description: 追加知识  
 * @author sry  
 * @date 2017年5月2日  
 *
 */
public class AppendKnowledgeVo {
	@NotBlank(message = "{empty.message}")
	private String knowledgeCode;
	/*@NotBlank(message = "{empty.message}")*/
	private String appendContent;
	@NotBlank(message = "{empty.message}")
	private String userCode;
	@NotBlank(message = "{empty.message}")
	private String token;
	/*@NotBlank(message = "{empty.message}")*/
	private String appendDigest;
	
	public String getKnowledgeCode() {
		return knowledgeCode;
	}
	public void setKnowledgeCode(String knowledgeCode) {
		this.knowledgeCode = knowledgeCode;
	}
	public String getAppendContent() {
		return appendContent;
	}
	public void setAppendContent(String appendContent) {
		this.appendContent = appendContent;
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
	public String getAppendDigest() {
		return appendDigest;
	}
	public void setAppendDigest(String appendDigest) {
		this.appendDigest = appendDigest;
	}
	
	
}
