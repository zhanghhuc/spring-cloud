package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.dao.pojo.BlogAttach;

/**
 * 
 * @ClassName: BlogAttachMapper  
 * @Description: 博客附件  
 * @author ZKZ  
 * @date 2017年4月6日  
 *
 */
public interface BlogAttachMapper {
	
	/**
	 * 
	 * @Title: list  
	 * @Description: 查询附件列表 - 不分页
	 * @param paramMap
	 * @return: List<BlogAttach>    返回类型
	 */
	List<BlogAttach> listAll(Map<String, Object> paramMap);
	
	/**
     * 
     * @Title: batchInsert  
     * @Description: 批量插入附件信息
     * @param blogAttachList
     * @return: int    返回类型
     */
    int batchInsert(List<BlogAttach> blogAttachList);
    
    /**
     * 
     * @Title: delete  
     * @Description: 删除附件
     * @param blogCode
     * @return: int    返回类型
     */
    int delete(String blogCode);

    /**
     * 
     * @Title: updateData  
     * @Description: 修改数量
     * @param paramMap
     * @return: int    返回类型
     */
	int updateData(Map<String, Object> paramMap);

}