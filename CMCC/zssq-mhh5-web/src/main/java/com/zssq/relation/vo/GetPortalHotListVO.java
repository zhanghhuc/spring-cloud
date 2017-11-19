package com.zssq.relation.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;
import com.zssq.annotation.validation.LongType;

/**
 * @ClassName GetPortalHotListVO
 * @Description 获取门户热点列表
 * @author LXW
 * @date 2017年6月12日 上午10:07:10
 * @version 1.0
 * @since JDK 1.7
 */
public class GetPortalHotListVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@NotBlank(message = "{empty.message}")
	private String orgCode;	//门户编号
	@LongType(expression = ">0")
	private String queryTime;//首次查询时间
	@IntType(expression = ">0")
	private String pageSize;//显示条数
	@IntType(expression = ">=0")
	private String pageNo;//显示条数
	@NotBlank(message = "{empty.message}")
	private String userCode;//当前登录用户的userCode
	@NotBlank(message = "{empty.message}")
	private String token;	//TOKEN

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}


	public String getQueryTime() {
		return queryTime;
	}

	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
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