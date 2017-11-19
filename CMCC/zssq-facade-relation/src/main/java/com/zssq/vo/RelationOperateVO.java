package com.zssq.vo;

import java.io.Serializable;

/**
 * 
 * @ClassName: RelationOperateVO  
 * @Description: 操作  
 * @author ZKZ  
 * @date 2017年3月18日  
 *
 */
public class RelationOperateVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String subjectCode; // 内容编号
	private String userCode; // 操作人编号
	

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

}
