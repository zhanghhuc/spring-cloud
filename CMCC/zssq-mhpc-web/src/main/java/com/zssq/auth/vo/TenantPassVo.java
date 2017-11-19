package com.zssq.auth.vo;

import org.hibernate.validator.constraints.NotBlank;

public class TenantPassVo {

//	@NotBlank(message = "{empty.message}")
	private String account;
	
	@NotBlank(message = "{empty.message}")
	private String passWord;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
}
