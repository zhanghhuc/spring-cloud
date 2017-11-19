package com.zssq.forum.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;
/**
 * 删除跟帖/评论及回复 输入参数对象
 * @ClassName DeletingFollowInfoVo
 * @Description 
 * @author liurong
 * @date 2017年4月7日 下午2:44:26
 * @version 1.0
 * @since JDK 1.7
 */
public class DeletingFollowInfoVo {
	
	@NotBlank(message = "{empty.message}")
	private String belongCode;
	
	@NotBlank(message = "{empty.message}")
	private String infoCode;// 待删除信息CODE

	@EnumType(allow = { "1", "2", "3" })
	private String infoType;// 待删除信息类型：1-跟帖 2-评论 3-回复
	
	@NotBlank(message = "{empty.message}")
	private String userCode;  //用户CODE
	
	@NotBlank(message = "{empty.message}")
	private String token;  //Token令牌

	public String getBelongCode() {
		return belongCode;
	}

	public void setBelongCode(String belongCode) {
		this.belongCode = belongCode;
	}

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
