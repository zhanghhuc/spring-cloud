package com.zssq.msg.vo;

import org.hibernate.validator.constraints.NotBlank;

public class DeleteATVO {
	
	@NotBlank(message = "{empty.message}")
	private String atCode;

	public String getAtCode() {
		return atCode;
	}

	public void setAtCode(String atCode) {
		this.atCode = atCode;
	}
	
	
	

}
