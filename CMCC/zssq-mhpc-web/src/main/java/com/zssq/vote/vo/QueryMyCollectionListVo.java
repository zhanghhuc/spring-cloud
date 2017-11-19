package com.zssq.vote.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;

/**
 * @ClassName QueryMyCollectionListVo
 * @Description 获取我的收藏列表
 * @author LiuYunLong
 * @date 2017年5月22日 下午6:49:15
 * @version 1.0
 * @since JDK 1.7
 */
public class QueryMyCollectionListVo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@IntType(required = false,expression = ">=0")
	private String id;
	
	@IntType(required = true,expression = ">0")
	private String pageSize;
	
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	@NotBlank(message = "{empty.message}")
	private String token;

	public String getId() {
		return id;
	}

	public String getPageSize() {
		return pageSize;
	}

	public String getUserCode() {
		return userCode;
	}

	public String getToken() {
		return token;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
