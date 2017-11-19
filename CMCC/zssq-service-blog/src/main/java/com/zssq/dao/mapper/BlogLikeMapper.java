package com.zssq.dao.mapper;

import java.util.Map;

import com.zssq.dao.pojo.BlogLike;

/**
 * 
 * @ClassName: BlogLikeMapper  
 * @Description: 点赞  
 * @author ZKZ  
 * @date 2017年3月27日  
 *
 */
public interface BlogLikeMapper {
	
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
	 * @Description: 点赞
	 * @param blogLike
	 * @return: int    返回类型
	 */
    int insert(BlogLike blogLike);
    
    /**
     * 
     * @Title: delete  
     * @Description: 取消点赞
     * @param paramMap
     * @return: int    返回类型
     */
    int delete(Map<String, Object> paramMap);

}