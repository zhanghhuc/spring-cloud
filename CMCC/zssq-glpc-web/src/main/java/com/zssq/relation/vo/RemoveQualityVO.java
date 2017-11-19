package com.zssq.relation.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @ClassName RemoveQualityVO
 * @Description 取消置精
 * @author LXW
 * @date 2017年6月12日 上午11:39:21
 * @version 1.0
 * @since JDK 1.7
 */
public class RemoveQualityVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "{empty.message}")
	private String recCode; // 推荐编号
	@NotBlank(message = "{empty.message}")
	private String qualityCode; // 精华编号
	@NotBlank(message = "{empty.message}")
	private String menuCode; // 菜单编号
	@NotBlank(message = "{empty.message}")
	private String userCode; // 用户CODE
	@NotBlank(message = "{empty.message}")
	private String token; // token

	
	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getRecCode() {
		return recCode;
	}

	public void setRecCode(String recCode) {
		this.recCode = recCode;
	}

	public String getQualityCode() {
		return qualityCode;
	}

	public void setQualityCode(String qualityCode) {
		this.qualityCode = qualityCode;
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
