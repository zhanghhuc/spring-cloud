package com.zssq.model;

import java.io.Serializable;

/**
 * 
 * @ClassName: RelationSubjectResourceModel  
 * @Description: 内容资源参数  
 * @author ZKZ  
 * @date 2017年4月20日  
 *
 */
public class RelationSubjectResourceModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String subjectResCode; // 资源编号
	private String resUrl; // 资源链接
	private Byte resClass; // 资源类型

	
	public String getSubjectResCode() {
		return subjectResCode;
	}

	public void setSubjectResCode(String subjectResCode) {
		this.subjectResCode = subjectResCode;
	}

	public String getResUrl() {
		return resUrl;
	}

	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}

	public Byte getResClass() {
		return resClass;
	}

	public void setResClass(Byte resClass) {
		this.resClass = resClass;
	}

}
