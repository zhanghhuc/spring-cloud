package com.zssq.activity.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

/**
 * 点赞/取消点赞活动、评论、回复输入参数对象
 * @ClassName PraisingOrNotVo
 * @Description 
 * @author liurong
 * @date 2017年4月22日 上午11:10:05
 * @version 1.0
 * @since JDK 1.7
 */
public class PraisingOrNotVo {

	@NotBlank(message = "{empty.message}")
	private String infoCode;  //被点赞信息CODE
	
	@EnumType(allow = {"1","2","3"})
	private String infoType; //被点赞信息类型:
	
	@EnumType(allow = {"0","1"})
	private String operating;  //操作类型
	
	@NotBlank(message = "{empty.message}")
	private String userCode;  //当前登录用户唯一标识CODE
	
	@NotBlank(message = "{empty.message}")
	private String token;  //Token令牌

	public String getInfoCode() {
		return infoCode;
	}

	public String getInfoType() {
		return infoType;
	}

	public String getOperating() {
		return operating;
	}

	public String getUserCode() {
		return userCode;
	}

	public String getToken() {
		return token;
	}

	public void setInfoCode(String infoCode) {
		this.infoCode = infoCode;
	}

	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}

	public void setOperating(String operating) {
		this.operating = operating;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
