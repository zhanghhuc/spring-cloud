package com.zssq.auth.vo;

import org.hibernate.validator.constraints.NotBlank;

public class SaasPassVo {
	
	@NotBlank(message = "{empty.message}")
	private String oldPass;
	
	@NotBlank(message = "{empty.message}")
	private String newPass;
	
//	@NotBlank(message = "{empty.message}")
	private String saasAccount;

	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public String getSaasAccount() {
		return saasAccount;
	}

	public void setSaasAccount(String saasAccount) {
		this.saasAccount = saasAccount;
	}
	
}
