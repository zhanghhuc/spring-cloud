package com.zssq.activity.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

/**
 * 
 * @ClassName: JoinActivityVo
 * @Description: 参加活动
 * @author liuzhijie
 * @date 2017年4月24日
 *
 */
public class JoinActivityVo implements Serializable {
 
	private static final long serialVersionUID = 6980553881881242511L;

	@NotBlank(message = "{empty.message}")
	private String activityCode;//活动CODE
	
	@EnumType(allow = {"1","2","3"})
	private String fileType;//文件类型：1-文档文件；2-图片；3-视频
	
	@NotBlank(message = "{empty.message}")
	private String fileName;//文件名
	
	@NotBlank(message = "{empty.message}")
	private String fileUrl;//文件存储地址
	
	@NotBlank(message = "{empty.message}")
	private String userCode;//当前登录用户唯一标识CODE
	
	@NotBlank(message = "{empty.message}")
	private String token;//Token令牌

	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
