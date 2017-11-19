
package com.zssq.activity.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

/**
 * 删除评论/回复输入参数对象
 * @ClassName DeletingMyInfoVo
 * @Description 
 * @author liurong
 * @date 2017年4月21日 下午3:51:58
 * @version 1.0
 * @since JDK 1.7
 */
public class DeletingMyInfoVo {

	@NotBlank(message = "{empty.message}")
	private String infoCode; // 待删除CODE
	
	@EnumType(allow = { "2", "3" })
	private String infoType; // 待删除信息类型：2-评论 3-回复
	
	@NotBlank(message = "{empty.message}")
	private String userCode; // 当前登录用户唯一标识CODE
	
	@NotBlank(message = "{empty.message}")
	private String token; // Token令牌

	public String getInfoCode() {
		return infoCode;
	}

	public void setInfoCode(String infoCode) {
		this.infoCode = infoCode;
	}

	public String getInfoType() {
		return infoType;
	}

	public void setInfoType(String infoType) {
		this.infoType = infoType;
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
