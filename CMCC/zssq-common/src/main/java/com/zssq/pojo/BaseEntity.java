package com.zssq.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
    * @ClassName: BaseEntity  
    * @Description: 各实体公用属性 .  
    * @author Mr.K  
    * @date 2016年3月15日  
    *
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** 启用/禁用 缺省0：启用 **/
	private Integer isDisable = 0; 

	/** 是否删除 缺省0：是不删除 **/
	private Integer isDelete = 0; 

	/** 回滚 缺省0：不是回滚 **/
	private Integer isActive = 0; 

	/** 创建时间 **/
	private Long createTime = (new Date()).getTime(); 

	/** 时间戳 **/
	private Long timeSign = (new Date()).getTime(); ;

	public synchronized Integer getIsDisable() {
		return isDisable;
	}

	public synchronized void setIsDisable(Integer isDisable) {
		this.isDisable = isDisable;
	}

	public synchronized Integer getIsDelete() {
		return isDelete;
	}

	public synchronized void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public synchronized Integer getIsActive() {
		return isActive;
	}

	public synchronized void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public synchronized Long getCreateTime() {
		return createTime;
	}

	public synchronized void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public synchronized Long getTimeSign() {
		return timeSign;
	}

	public synchronized void setTimeSign(Long timeSign) {
		this.timeSign = timeSign;
	}
	
	
	
}
