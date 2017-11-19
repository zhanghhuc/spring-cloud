package com.zssq.relation.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;
/**
 * 
 * @ClassName: GetTeamShowHotListVO  
 * @Description: 获取班组展示列表vo  
 * @author sry  
 * @date 2017年4月19日  
 *
 */
import com.zssq.annotation.validation.LongType;

/**
 * @ClassName GetTeamShowHotListVO
 * @Description 班组展示中热点列表
 * @author LXW
 * @date 2017年6月12日 下午3:12:27
 * @version 1.0
 * @since JDK 1.7
 */
public class GetTeamShowHotListVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@NotBlank(message = "{empty.message}")
	private String teamCode;	//班组编号
	@IntType(expression = ">0")
	private String pageSize;	//每页条数
	@IntType(expression = ">=0")
	private String pageNo;		//页码，从0开始
	@LongType(expression = ">0")
	private String queryTime;	//首次查询时间，前端生成传入
	@NotBlank(message = "{empty.message}")
	private String userCode;	//当前登录用户的userCode
	@NotBlank(message = "{empty.message}")
	private String token;		//TOKEN

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
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
