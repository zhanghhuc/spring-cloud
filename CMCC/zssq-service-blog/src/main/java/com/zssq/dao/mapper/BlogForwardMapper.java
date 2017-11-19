package com.zssq.dao.mapper;

import com.zssq.dao.pojo.BlogForward;

/**
 * 
 * @ClassName: BlogForwardMapper  
 * @Description: 转发  
 * @author ZKZ  
 * @date 2017年3月27日  
 *
 */
public interface BlogForwardMapper {

	/**
	 * 
	 * @Title: insert  
	 * @Description: 保存转发信息
	 * @param blogForward
	 * @return: int    返回类型
	 */
    int insert(BlogForward blogForward);

}