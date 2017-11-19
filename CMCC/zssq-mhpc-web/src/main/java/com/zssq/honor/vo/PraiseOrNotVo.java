package com.zssq.honor.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

/**
 * @ClassName PraiseOrNotVo
 * @Description 点赞/取消点赞输入对象
 * @author LiuYunLong
 * @date 2017年3月21日 下午3:23:58
 * @version 1.0
 * @since JDK 1.7
 */
public class PraiseOrNotVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "{empty.message}") //被点赞信息CODE
	private String infoCode;
	
	@EnumType(required = true,allow = {"1","2","3"})
	private String infoType;  //被点赞信息类型
	
	@EnumType(required = true,allow = {"0","1"})
	private String operating; //操作类型
	
	@NotBlank(message = "{empty.message}")
	private String userCode; //用户CODE
	
	@NotBlank(message = "{empty.message}")
	private String token; //Token令牌

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
