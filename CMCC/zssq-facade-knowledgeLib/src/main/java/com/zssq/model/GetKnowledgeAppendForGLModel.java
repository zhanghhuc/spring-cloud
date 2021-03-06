package com.zssq.model;

import java.io.Serializable;

/**
 * 
 * @ClassName: GetKnowledgeAppendForGLModel  
 * @Description: 管理端知识追加  
 * @author sry  
 * @date 2017年6月19日  
 *
 */
public class GetKnowledgeAppendForGLModel implements Serializable {
	
	private static final long serialVersionUID = 2670843916235809930L;
	
    private Long id =  0L;

    private String appendCode;

    private Long createTime;

    private String userCode;

    private Byte isSelfAppend;

    private String appendContent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAppendCode() {
		return appendCode;
	}

	public void setAppendCode(String appendCode) {
		this.appendCode = appendCode;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Byte getIsSelfAppend() {
		return isSelfAppend;
	}

	public void setIsSelfAppend(Byte isSelfAppend) {
		this.isSelfAppend = isSelfAppend;
	}

	public String getAppendContent() {
		return appendContent;
	}

	public void setAppendContent(String appendContent) {
		this.appendContent = appendContent;
	}

    
}