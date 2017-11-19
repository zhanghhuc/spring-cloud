package com.zssq.dao.pojo;

import java.io.Serializable;

/**
 * 
 * @ClassName: SequenceDeputy  
 * @Description: 代发序列实体  
 * @author CaiZhaohui  
 * @date 2017年5月23日  
 *
 */
public class SequenceDeputy implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** 自增主键 */
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
