package com.zssq.auth.vo;

import org.hibernate.validator.constraints.NotBlank;

public class UserMenuAddVo {

	@NotBlank(message = "{empty.message}")
	private String code;
	
	@NotBlank(message = "{empty.message}")
	private String menuCodes;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMenuCodes() {
		return menuCodes;
	}

	public void setMenuCodes(String menuCodes) {
		this.menuCodes = menuCodes;
	}
}
