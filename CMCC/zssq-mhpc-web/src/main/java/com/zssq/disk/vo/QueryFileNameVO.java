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
public class QueryFileNameVO implements Serializable{

	
	@NotBlank(message = "{empty.message}")
	private String fileName;
	
	@NotBlank(message = "{empty.message}")
	private String orgCode;
	
	
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
