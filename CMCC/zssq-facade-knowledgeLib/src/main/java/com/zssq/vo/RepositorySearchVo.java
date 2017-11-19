package com.zssq.vo;

import java.io.Serializable;

/**
 * 
 * @ClassName: RepositorySearchVo  
 * @Description: 知识库搜索  
 * @author sry  
 * @date 2017年5月2日  
 *
 */

public class RepositorySearchVo implements Serializable{

	private static final long serialVersionUID = 7877474934860943596L;
	private String orgCode;//组织编号
	private String Keywords;//关键词
	private String userCode;//用户编号
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getKeywords() {
		return Keywords;
	}
	public void setKeywords(String keywords) {
		Keywords = keywords;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	
}
