package com.zssq.disk.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;

/**
 * 
 * @ClassName: OpenFolderListVO  
 * @Description: 打开文件夹  
 * @author YDB  
 * @date 2017年4月17日  
 *
 */
public class OpenFolderListVO {
	
/*	@NotBlank(message = "{empty.message}")
	private String orgCode;*/
	
	@NotBlank(message = "{empty.message}")
	private String fileCode;
	
	@IntType(expression = ">0")
	private String pageSize;
	
	@IntType(expression = ">=0")
	private String pageNo;

	
	

	


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

	public String getFileCode() {
		return fileCode;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}

	

}
