package com.zssq.forum.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @ClassName QueryEnpowermentVo
 * @Description 查询某一论坛权限输入参数对象
 * @author LiuYunLong
 * @date 2017年3月21日 下午3:02:28
 * @version 1.0
 * @since JDK 1.7
 */
public class QueryEnpowermentVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "{empty.message}")
	private String belongCode;  //论坛所属CODE（即班组CODE）
	
	@NotBlank(message = "{empty.message}")
	private String userCode;  //用户CODE
	
	@NotBlank(message = "{empty.message}")
	private String token;  //Token令牌

	public String getBelongCode() {
		return belongCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public String getToken() {
		return token;
	}

	public void setBelongCode(String belongCode) {
		this.belongCode = belongCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public void setToken(String token) {
		this.token = token;
	}
	

}
