package com.zssq.dao.mapper;

import com.zssq.dao.pojo.SequenceDeputy;

/**
 * 
 * @ClassName: SequenceDeputyMapper  
 * @Description: 代发序列映射器  
 * @author CaiZhaohui  
 * @date 2017年5月23日  
 *
 */
public interface SequenceDeputyMapper {
	
	/**
	 * 
	 * @Title: insert  
	 * @Description: 添加代发序列
	 * @param @param record
	 * @param @return    参数  
	 * @return int    返回类型  
	 * @throws
	 */
	int insert(SequenceDeputy record);

}
