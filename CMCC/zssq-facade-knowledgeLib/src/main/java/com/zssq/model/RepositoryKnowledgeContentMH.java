package com.zssq.model;

import java.io.Serializable;

/**
 * 
 * @ClassName: KnowledgeContentMH  
 * @Description: 知识正文  
 * @author sry  
 * @date 2017年4月28日  
 *
 */
public class RepositoryKnowledgeContentMH implements Serializable{

	private static final long serialVersionUID = -1331358307415195723L;
	
	private String contentInfo;//知识正文

	public String getContentInfo() {
		return contentInfo;
	}

	public void setContentInfo(String contentInfo) {
		this.contentInfo = contentInfo;
	}
	
	
}
