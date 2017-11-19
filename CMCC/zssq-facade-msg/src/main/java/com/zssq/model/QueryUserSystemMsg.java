package com.zssq.model;

import java.io.Serializable;
import java.util.List;



/**
 * 
 * @ClassName: QueryUserSystemMsg  
 * @Description: 查询用户对应的系统消息  
 * @author YDB  
 * @date 2017年4月8日  
 *
 */
public class QueryUserSystemMsg implements Serializable{

	private String userCode;
	
	private List<String> list;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
	
}