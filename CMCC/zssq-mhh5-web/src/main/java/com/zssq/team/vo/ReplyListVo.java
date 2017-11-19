package com.zssq.team.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;

public class ReplyListVo {

	/** 留言唯一标识 */
	@NotBlank(message = "{empty.message}")
	private String mesCode;
	
	/** 上一条回复id */
	@NotBlank(message = "{empty.message}")
	private String lastId;
	
	/** 数量 */
	@IntType(expression = ">0")
	private String count;
	
	private String userCode;

	public String getMesCode() {
		return mesCode;
	}

	public void setMesCode(String mesCode) {
		this.mesCode = mesCode;
	}

	public String getLastId() {
		return lastId;
	}

	public void setLastId(String lastId) {
		this.lastId = lastId;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
}
