package com.zssq.dao.mapper;

import java.util.Map;

import com.zssq.dao.pojo.BlogContent;

/**
 * 
 * @ClassName: BlogContentMapper  
 * @Description: 博客正文  
 * @author ZKZ  
 * @date 2017年4月5日  
 *
 */
public interface BlogContentMapper {

	/**
	 * 
	 * @Title: insert  
	 * @Description: 保存正文信息
	 * @param blogContent
	 * @return: int    返回类型
	 */
    int insert(BlogContent blogContent);
    
    /**
     * 
     * @Title: copyInsert  
     * @Description: 复制正文内容
     * @param paramMap
     * @return: int    返回类型
     */
    int copyInsert(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: update  
     * @Description: 修改正文信息
     * @param paramMap
     * @return: int    返回类型
     */
    int update(Map<String, Object> paramMap);

}