package com.zssq.disk.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @ClassName: FileVO  
 * @Description:   
 * @author YDB  
 * @date 2017年4月14日  
 *
 */
public class GetIndexFileVO implements Serializable{

	@NotBlank(message = "{empty.message}")
	private String pageSize;
	
	@NotBlank(message = "{empty.message}")
	private String orgCode;
	
	
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	
	
}