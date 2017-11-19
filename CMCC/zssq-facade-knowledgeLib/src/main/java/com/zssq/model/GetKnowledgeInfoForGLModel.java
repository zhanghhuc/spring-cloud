package com.zssq.model;

import java.io.Serializable;
/**
 * 
 * @ClassName: GetKnowledgeInfoForGLModel  
 * @Description: 知识正文model  
 * @author guoyang  
 * @date 2017年4月28日  
 *
 */
public class GetKnowledgeInfoForGLModel implements Serializable{
	private static final long serialVersionUID = 2670843916235809931L;
	
    private Long id;

    private String contentCode;


    private String contentInfo;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getContentCode() {
		return contentCode;
	}


	public void setContentCode(String contentCode) {
		this.contentCode = contentCode;
	}


	public String getContentInfo() {
		return contentInfo;
	}


	public void setContentInfo(String contentInfo) {
		this.contentInfo = contentInfo;
	}

}
