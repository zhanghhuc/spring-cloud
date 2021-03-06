package com.zssq.relation.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;
import com.zssq.annotation.validation.LongType;

/**
 * @ClassName GetUserShareListVO
 * @Description 个人分享列表
 * @author LXW
 * @date 2017年6月9日 上午10:42:51
 * @version 1.0
 * @since JDK 1.7
 */
public class GetUserShareListVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@IntType(expression = ">0")
	private String pageSize;// 每页条数
	@IntType(expression = ">=0")
	private String pageNo;// 页码，从0开始
	@LongType(expression = ">0")
	private String queryTime;// 首次查询时间，前端生成传入
	@NotBlank(message = "{empty.message}")
	private String userCode;// 当前登录用户的userCode
	@NotBlank(message = "{empty.message}")
	private String token;// TOKEN
	

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getQueryTime() {
		return queryTime;
	}

	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
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
