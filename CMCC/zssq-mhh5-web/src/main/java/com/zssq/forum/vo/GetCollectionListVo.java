package com.zssq.forum.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;
/**
 * 查询我的收藏列表输入参数对象
 * @ClassName GetCollectionListVo
 * @Description 
 * @author liurong
 * @date 2017年5月26日 下午3:44:35
 * @version 1.0
 * @since JDK 1.7
 */
public class GetCollectionListVo {
	
	@IntType(expression = ">0")
	private String pageSize;

	@IntType(required = false, expression = ">0")
	private String id;

	@NotBlank(message = "{empty.message}")
	private String userCode;

	@NotBlank(message = "{empty.message}")
	private String token;

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
