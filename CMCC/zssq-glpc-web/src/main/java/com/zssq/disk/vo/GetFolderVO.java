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
public class GetFolderVO implements Serializable{

	@NotBlank(message = "{empty.message}")
	private String fileCode;
	
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	@NotBlank(message = "{empty.message}")
	private String orgCode;
	
	@NotBlank(message = "{empty.message}")
	private String seletedFileCode;
	
	
	
	
	public String getSeletedFileCode() {
		return seletedFileCode;
	}

	public void setSeletedFileCode(String seletedFileCode) {
		this.seletedFileCode = seletedFileCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getFileCode() {
		return fileCode;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}
	
	
	
}
