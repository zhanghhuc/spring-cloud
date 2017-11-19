package com.zssq.dao.mapper;

import java.util.Map;

import com.zssq.dao.pojo.BlogDraftContent;

/**
 * 
 * @ClassName: BlogDraftContentMapper  
 * @Description: 博客草稿正文  
 * @author ZKZ  
 * @date 2017年3月22日  
 *
 */
public interface BlogDraftContentMapper {

	/**
	 * 
	 * @Title: insert  
	 * @Description: 保存正文信息
	 * @param blogDraftContent
	 * @return: int    返回类型
	 */
    int insert(BlogDraftContent blogDraftContent);
    
    /**
     * 
     * @Title: update  
     * @Description: 更新正文信息
     * @param paramMap
     * @return: int    返回类型
     */
    int update(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: delete  
     * @Description: 删除正文
     * @param draftCode
     * @return: int    返回类型
     */
    int delete(String draftCode);

}