package com.zssq.dao.mapper;

import java.util.List;

import com.zssq.dao.pojo.BlogDraftAttach;

/**
 * 
 * @ClassName: BlogDraftAttachMapper  
 * @Description: 博客草稿附件  
 * @author ZKZ  
 * @date 2017年3月22日  
 *
 */
public interface BlogDraftAttachMapper {
    
    /**
     * 
     * @Title: batchInsert  
     * @Description: 批量插入附件信息
     * @param blogDraftAttachList
     * @return: int    返回类型
     */
    int batchInsert(List<BlogDraftAttach> blogDraftAttachList);
    
    /**
     * 
     * @Title: delete  
     * @Description: 删除附件
     * @param draftCode
     * @return: int    返回类型
     */
    int delete(String draftCode);
}