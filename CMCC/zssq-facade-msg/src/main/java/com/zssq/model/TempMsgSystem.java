package com.zssq.model;

import java.io.Serializable;

/** * 
 * @ClassName: TempMsgSystem  
 * @Description: 添加用户系统消息临时Model  
 * @author YDB  
 * @date 2017年4月8日  
 *
 */
public class TempMsgSystem implements Serializable{
	
	private String code;
	
	private Long creatTime;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Long creatTime) {
		this.creatTime = creatTime;
	}
	
	
	

}
