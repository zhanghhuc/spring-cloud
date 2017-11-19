package com.zssq.honor.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

/**
 * @ClassName UpdateHonorVo
 * @Description 更新荣誉输入对象
 * @author LiuYunLong
 * @date 2017年3月21日 下午3:39:12
 * @version 1.0
 * @since JDK 1.7
 */
public class UpdateHonorVo implements Serializable{
	
	private static final long serialVersionUID = -883400763006546571L;
	
	@NotBlank(message = "{empty.message}")
	private String honorCode;  //荣誉CODE
	
	@NotBlank(message = "{empty.message}")
	private String honorName;  //荣誉名称
	
	@EnumType(required = true,allow = {"1","2"})
	private String honorType;  //荣誉类型
	
	@NotBlank(message = "{empty.message}")
	private String honorUrl;  //荣誉图片URL
	
	private String remark;  //描述
	
	@NotBlank(message = "{empty.message}")
	private String userCode;  //用户CODE
	
	@NotBlank(message = "{empty.message}")
	private String token;  //Token令牌
	
	public String getUserCode() {
		return userCode;
	}

	public String getToken() {
		return token;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getHonorCode() {
		return honorCode;
	}

	public String getHonorName() {
		return honorName;
	}

	public String getHonorType() {
		return honorType;
	}

	public String getHonorUrl() {
		return honorUrl;
	}

	public String getRemark() {
		return remark;
	}

	public void setHonorCode(String honorCode) {
		this.honorCode = honorCode;
	}

	public void setHonorName(String honorName) {
		this.honorName = honorName;
	}

	public void setHonorType(String honorType) {
		this.honorType = honorType;
	}

	public void setHonorUrl(String honorUrl) {
		this.honorUrl = honorUrl;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
