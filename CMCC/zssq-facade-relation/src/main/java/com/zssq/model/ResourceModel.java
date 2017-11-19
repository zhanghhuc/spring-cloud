package com.zssq.model;

import java.io.Serializable;

/**
 * 
 * @ClassName: ResourceModel  
 * @Description: 内容资源参数   
 * @author ZKZ  
 * @date 2017年6月15日  
 *
 */
public class ResourceModel implements Serializable {

	private static final long serialVersionUID = -1052979487267601236L;

	private String resCode; // 资源编号
	private String resUrl; // 资源链接
	private String resClass; // 资源类型
	

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getResUrl() {
		return resUrl;
	}

	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}

	public String getResClass() {
		return resClass;
	}

	public void setResClass(String resClass) {
		this.resClass = resClass;
	}

}
