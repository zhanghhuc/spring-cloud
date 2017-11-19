package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.dao.model.BlogDraftModel;
import com.zssq.dao.pojo.BlogDraft;

/**
 * 
 * @ClassName: BlogDraftMapper  
 * @Description: 博客草稿  
 * @author ZKZ  
 * @date 2017年3月22日  
 *
 */
public interface BlogDraftMapper {

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
     * @Title: list  
     * @Description: 查询草稿列表 - 分页
     * @param paramMap
     * @return: List<BlogDraft>    返回类型
     */
    List<BlogDraft> list(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: select  
     * @Description: 查询草稿详情
     * @param paramMap
     * @return: BlogDraftModel    返回类型
     */
    BlogDraftModel select(Map<String, Object> paramMap);
    
	/**
	 * 
	 * @Title: insert  
	 * @Description: 保存草稿信息
	 * @param blogDraft
	 * @return: int    返回类型
	 */
    int insert(BlogDraft blogDraft);
    
    /**
     * 
     * @Title: update  
     * @Description: 更新草稿信息
     * @param paramMap
     * @return: int    返回类型
     */
    int update(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: delete  
     * @Description: 删除草稿
     * @param paramMap
     * @return: int    返回类型
     */
    int delete(Map<String, Object> paramMap);
}