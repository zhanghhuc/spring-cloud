package com.zssq.mblog.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;
import com.zssq.annotation.validation.LongType;

/**
 * 
    * @ClassName: GetWeiboListVo  
    * @Description: 获取个人空间微博信息条件VO  
    * @author Mr.B  
    * @date 2017年3月22日  
    *
 */
public class GetWeiboListVo {

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
	private String otherCode; // 他人CODE
	
	
	public String getOtherCode() {
		return otherCode;
	}
	public void setOtherCode(String otherCode) {
		this.otherCode = otherCode;
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