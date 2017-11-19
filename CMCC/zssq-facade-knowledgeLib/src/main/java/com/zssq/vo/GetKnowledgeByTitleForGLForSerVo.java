package com.zssq.vo;

import java.io.Serializable;

/**
 * 
 * @ClassName: GetKnowledgeByTitleForGLForSerVo  
 * @Description: 知识vo  
 * @author guoyang  
 * @date 2017年5月3日  
 *
 */

public class GetKnowledgeByTitleForGLForSerVo implements Serializable{

	private static final long serialVersionUID = 7877474934860943596L;
	private String orgCode;//组织编号
	private String knowledgeCode;
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getKnowledgeCode() {
		return knowledgeCode;
	}
	public void setKnowledgeCode(String knowledgeCode) {
		this.knowledgeCode = knowledgeCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
