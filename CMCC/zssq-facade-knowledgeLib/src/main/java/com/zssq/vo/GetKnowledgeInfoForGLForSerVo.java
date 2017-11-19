package com.zssq.vo;

import java.io.Serializable;
/**
 * 
 * @ClassName: RepositoryKnowledgeContentVo  
 * @Description: 知识正文vo  
 * @author sry  
 * @date 2017年4月28日  
 *
 */
public class GetKnowledgeInfoForGLForSerVo implements Serializable{

	    
	private static final long serialVersionUID = 6426158089527289180L;
	
	private String contentCode;//正文编号

	public String getContentCode() {
		return contentCode;
	}

	public void setContentCode(String contentCode) {
		this.contentCode = contentCode;
	}
	
	
}
