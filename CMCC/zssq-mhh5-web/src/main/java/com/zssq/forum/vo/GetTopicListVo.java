package com.zssq.forum.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;

/**
 * 查询主帖列表输入参数对象
 * @ClassName GetTopicListVo
 * @Description 
 * @author liurong
 * @date 2017年5月26日 下午2:22:53
 * @version 1.0
 * @since JDK 1.7
 */
public class GetTopicListVo {
	
	@NotBlank(message = "{empty.message}")
	private String belongCode;// 论坛所属CODE（这里传入班组CODE即可）

	@IntType(expression = ">0")
	private String pageSize;// 每页显示行数

	@IntType(required = false, expression = ">0")
	private String rowId;// 列表最后一条数据所在行的ID， 第一次查询传""

	@NotBlank(message = "{empty.message}")
	private String userCode;// 用户CODE

	@NotBlank(message = "{empty.message}")
	private String token;// Token令牌

	public String getBelongCode() {
		return belongCode;
	}

	public void setBelongCode(String belongCode) {
		this.belongCode = belongCode;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
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
