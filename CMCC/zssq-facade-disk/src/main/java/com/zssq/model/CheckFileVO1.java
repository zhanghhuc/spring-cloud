package com.zssq.model;

import java.io.Serializable;

/**
 * 
 * @ClassName: CheckFileVO1  
 * @Description: 暂时不用  
 * @author YDB  
 * @date 2017年4月15日  
 *
 */

public class CheckFileVO1 implements Serializable {
	
	
	//校验文件夹的位置
	private String thisFolderParentCode;;
	
	//校验文件code
	private String fileCode;
	//校验文件父级Code
	private String fileParentCode;
	
	//文件名称 
	private String fileName;
	
	
	
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getThisFolderParentCode() {
		return thisFolderParentCode;
	}
	public void setThisFolderParentCode(String thisFolderParentCode) {
		this.thisFolderParentCode = thisFolderParentCode;
	}
	public String getFileCode() {
		return fileCode;
	}
	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}
	public String getFileParentCode() {
		return fileParentCode;
	}
	public void setFileParentCode(String fileParentCode) {
		this.fileParentCode = fileParentCode;
	}
	
	

}
