package com.zssq.activity.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;
import com.zssq.annotation.validation.IntType;

/**
 * 
 * @ClassName: GetActivityJoinListVo  
 * @Description: 查询活动参与记录列表
 * @author liuzhijie  
 * @date 2017年4月24日  
 *
 */
public class GetActivityJoinListVo implements Serializable {


	private static final long serialVersionUID = 6346913342943326179L;

	@NotBlank(message = "{empty.message}")
	private String activityCode;//活动CODE
	
	@EnumType(required=true ,allow={"1","2","3"})
	private String queryType;//查询类型：1-未评分作品 2-已评分作品 3-获奖作品
	
	@IntType(expression=">0")
	private String pageSize;//每页显示行数
	
	@IntType(expression=">=0")
	private String pageNo;//当前页码，从0开始
	
	@NotBlank(message = "{empty.message}")
	private String userCode;//当前登录用户唯一标识CODE
	
	@NotBlank(message = "{empty.message}")
	private String token;//Token令牌

	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
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
