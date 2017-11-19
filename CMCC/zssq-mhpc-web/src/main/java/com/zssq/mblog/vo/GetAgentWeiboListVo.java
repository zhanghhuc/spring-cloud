package com.zssq.mblog.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;
import com.zssq.annotation.validation.LongType;

/**
 * 
    * @ClassName: GetAgentWeiboListVo  
    * @Description: 获取代发微博列表条件VO  
    * @author Mr.B  
    * @date 2017年3月22日  
    *
 */
public class GetAgentWeiboListVo {

	@NotBlank(message = "{empty.message}")
	private String userCode; // 用户CODE
	@NotBlank(message = "{empty.message}")
	private String token; // TOKEN
	@IntType(expression = ">=0") 
	private String pageNo; // 页码 从0开始
	@IntType(expression = ">0")
	private String pageSize; // 每页条数
	@LongType(expression = ">0")
	private String currentTime; // 时间分割线
	@NotBlank(message = "{empty.message}")
	private String leaderUserCode;
	
	
	public String getLeaderUserCode() {
		return leaderUserCode;
	}
	public void setLeaderUserCode(String leaderUserCode) {
		this.leaderUserCode = leaderUserCode;
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
	public String getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}
	
	
	
}
