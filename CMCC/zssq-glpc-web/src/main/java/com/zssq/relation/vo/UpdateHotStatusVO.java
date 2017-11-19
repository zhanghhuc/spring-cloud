package com.zssq.relation.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

/**
 * @ClassName UpdateHotStatusVO
 * @Description 修改精华状态
 * @author LXW
 * @date 2017年6月12日 上午11:41:02
 * @version 1.0
 * @since JDK 1.7
 */
public class UpdateHotStatusVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "{empty.message}")
	private String hotCode;		//热点编号
	@EnumType(allow = { "1", "2" })
	private String actionClass;	//操作类型：1.隐藏；2.显示
	@NotBlank(message = "{empty.message}")
	private String menuCode;	//菜单编号
	@NotBlank(message = "{empty.message}")
	private String userCode;	//当前登录用户的userCode
	@NotBlank(message = "{empty.message}")
	private String token;		//TOKEN

	public String getHotCode() {
		return hotCode;
	}

	public void setHotCode(String hotCode) {
		this.hotCode = hotCode;
	}

	public String getActionClass() {
		return actionClass;
	}

	public void setActionClass(String actionClass) {
		this.actionClass = actionClass;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
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