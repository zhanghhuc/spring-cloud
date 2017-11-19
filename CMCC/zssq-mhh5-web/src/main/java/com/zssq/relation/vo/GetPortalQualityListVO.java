package com.zssq.relation.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;
import com.zssq.annotation.validation.IntType;
import com.zssq.annotation.validation.LongType;

/**
 * 
 * @ClassName: GetPortalQualityListVO
 * @Description: 查询门户精华列表参数
 * @author ZKZ
 * @date 2017年6月16日
 *
 */
public class GetPortalQualityListVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "{empty.message}")
	private String orgCode;// 门户编号
	@EnumType(allow = { "1", "2" }, required = false)
	private String subjectClass;// 1.微博；2.博客；空：全部
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


	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getSubjectClass() {
		return subjectClass;
	}

	public void setSubjectClass(String subjectClass) {
		this.subjectClass = subjectClass;
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
