package com.zssq.vote.pojo;

import java.io.Serializable;

public class CountResult implements Serializable{

	private static final long serialVersionUID = -8410286511275642762L;
	private Integer count;
	private String code;
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
