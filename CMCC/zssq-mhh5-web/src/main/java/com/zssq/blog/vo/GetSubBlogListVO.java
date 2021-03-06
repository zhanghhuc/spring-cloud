package com.zssq.blog.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;
import com.zssq.annotation.validation.LongType;

/**
 * 
 * @ClassName: GetSubBlogListVO
 * @Description: 查询订阅博客列表参数
 * @author ZKZ
 * @date 2017年4月11日
 *
 */
public class GetSubBlogListVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "{empty.message}")
	private String subUserCode; // 订阅人编号
	@IntType(expression = ">0")
	private String pageSize; // 每页条数
	@IntType(expression = ">=0")
	private String pageNo; // 页码
	@LongType(expression = ">0")
	private String queryTime; // 首次查询时间
	@NotBlank(message = "{empty.message}")
	private String userCode; // 当前登录用户编号
	@NotBlank(message = "{empty.message}")
	private String token; // TOKEN
	

	public String getSubUserCode() {
		return subUserCode;
	}

	public void setSubUserCode(String subUserCode) {
		this.subUserCode = subUserCode;
	}

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
