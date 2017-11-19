package com.zssq.dao.mapper;

import java.util.Map;

import com.zssq.dao.pojo.BlogSubscribe;

/**
 * 
 * @ClassName: BlogSubscribeMapper  
 * @Description: 订阅  
 * @author ZKZ  
 * @date 2017年3月27日  
 *
 */
public interface BlogSubscribeMapper {

	/**
	 * 
	 * @Title: selectCount  
	 * @Description: 查询个数
	 * @param paramMap
	 * @return: int    返回类型
	 */
	int selectCount(Map<String, Object> paramMap);

	/**
	 * 
	 * @Title: insert  
	 * @Description: 保存订阅信息
	 * @param blogSubscribe
	 * @return: int    返回类型
	 */
    int insert(BlogSubscribe blogSubscribe);

    /**
     * 
     * @Title: delete  
     * @Description: 取消订阅
     * @param paramMap
     * @return: int    返回类型
     */
	int delete(Map<String, Object> paramMap);

}