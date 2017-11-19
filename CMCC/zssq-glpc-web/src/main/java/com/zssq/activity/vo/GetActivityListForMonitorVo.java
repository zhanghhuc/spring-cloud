package com.zssq.activity.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;

/**
 * 
 * @ClassName: GetActivityListForMonitorVo
 * @Description: 内容监控-查询活动列表
 * @author liuzhijie
 * @date 2017年5月5日
 *
 */
public class GetActivityListForMonitorVo implements Serializable {

	private static final long serialVersionUID = -8092324006049599406L;

	@IntType(expression=">=0")
	private String pageNo;//查询起始页码，从0开始
	
	@IntType(expression=">0")
	private String pageSize;//每页显示行数
	
	@NotBlank(message = "{empty.message}")
	private String userCode;//用户CODE
	
	@NotBlank(message = "{empty.message}")
	private String token;//Token令牌

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
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
