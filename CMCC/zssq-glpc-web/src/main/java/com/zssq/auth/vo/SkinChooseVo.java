package com.zssq.auth.vo;

import org.hibernate.validator.constraints.NotBlank;

public class SkinChooseVo {
	
	@NotBlank
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
