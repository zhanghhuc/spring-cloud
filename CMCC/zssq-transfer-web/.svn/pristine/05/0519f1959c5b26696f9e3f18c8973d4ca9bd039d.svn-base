package com.zssq.mblog.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
    * @ClassName: BeseEntity  
    * @Description: 基本实体对象  
    * @author Mr.B  
    * @date 2017年3月15日  
    *
 */
public class BaseEntity implements Serializable{
    /**  
    * @Fields serialVersionUID
    */  
	private static final long serialVersionUID = 1L;
	
	/** 主键 **/
	private Long id;
	
	/** 创建时间 **/
	private Long createTime = new Date().getTime();

	/** 修改时间 **/
	private Long modifyTime = new Date().getTime();
	
	/** 备注 **/
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? "" :remark.trim();
	}
	
	
}
