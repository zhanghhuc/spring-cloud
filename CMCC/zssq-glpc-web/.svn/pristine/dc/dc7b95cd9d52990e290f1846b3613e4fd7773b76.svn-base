package com.zssq.forum.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;

/**
 * @ClassName GetForumListForMonitorVo
 * @Description 内容监控 - 查询班组论坛列表
 * @author LiuYunLong
 * @date 2017年6月15日 下午2:14:02
 * @version 1.0
 * @since JDK 1.7
 */
public class GetForumListForMonitorVo {

	@IntType(expression=">=0")
	private String pageNo;
	
	@IntType(expression=">0")
	private String pageSize;
	
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	@NotBlank(message = "{empty.message}")
	private String token;
	
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
