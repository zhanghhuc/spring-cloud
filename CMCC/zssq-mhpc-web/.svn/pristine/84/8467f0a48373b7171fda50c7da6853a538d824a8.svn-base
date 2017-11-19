package com.zssq.honor.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

/**
 * @ClassName AwardingHonorVo
 * @Description 颁发荣誉输入对象
 * @author LiuYunLong
 * @date 2017年3月21日 下午3:11:14
 * @version 1.0
 * @since JDK 1.7
 */
public class AwardingHonorVo implements Serializable{

	private static final long serialVersionUID = 432423423432L;
	
	@NotBlank(message = "{empty.message}")
	private String honorCode;  //荣誉CODE
	
	@NotBlank(message = "{empty.message}")
	private String honorName;  //荣誉名称
	
	@NotBlank(message = "{empty.message}")
	private String honorUrl;  //荣誉图片URL
	
	private String agentCode;  //代发人CODE
	
	@NotBlank(message = "{empty.message}")
	private String awarderCode;  //颁发人CODE
	
	@EnumType(required = false,allow = {"1","2"})
	private String honoreeType;  //领奖对象类型：
	
	@NotBlank(message = "{empty.message}")
	private String honoreeCode;  //领奖对象CODE
	
	@NotBlank(message = "{empty.message}")
	private String awardReason;  //授予原因
	
	@NotBlank(message = "{empty.message}")
	private String userCode;  //用户CODE
	
	@NotBlank(message = "{empty.message}")
	private String token;  //Token令牌

	public String getHonorCode() {
		return honorCode;
	}

	public String getHonorName() {
		return honorName;
	}

	public String getHonorUrl() {
		return honorUrl;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public String getAwarderCode() {
		return awarderCode;
	}

	public String getHonoreeType() {
		return honoreeType;
	}

	public String getHonoreeCode() {
		return honoreeCode;
	}

	public String getAwardReason() {
		return awardReason;
	}

	public String getUserCode() {
		return userCode;
	}

	public String getToken() {
		return token;
	}

	public void setHonorCode(String honorCode) {
		this.honorCode = honorCode;
	}

	public void setHonorName(String honorName) {
		this.honorName = honorName;
	}

	public void setHonorUrl(String honorUrl) {
		this.honorUrl = honorUrl;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public void setAwarderCode(String awarderCode) {
		this.awarderCode = awarderCode;
	}

	public void setHonoreeType(String honoreeType) {
		this.honoreeType = honoreeType;
	}

	public void setHonoreeCode(String honoreeCode) {
		this.honoreeCode = honoreeCode;
	}

	public void setAwardReason(String awardReason) {
		this.awardReason = awardReason;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
