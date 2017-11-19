package com.zssq.disk.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;

/**
 * 
 * @ClassName: DiskManListVO  
 * @Description: 主列表  
 * @author YDB  
 * @date 2017年4月17日  
 *
 */
public class DiskManListVO implements Serializable{

	@NotBlank(message = "{empty.message}")
	private String orgCode;

	@IntType(expression = ">0")
	private String pageSize;
	
	@IntType(expression = ">=0")
	private String pageNo;

	
	private String fileName;
	
	
	
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
}
