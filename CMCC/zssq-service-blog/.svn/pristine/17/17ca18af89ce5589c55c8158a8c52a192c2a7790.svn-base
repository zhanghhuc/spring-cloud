package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.dao.model.BlogCommentModel;
import com.zssq.dao.pojo.BlogComment;

/**
 * 
 * @ClassName: BlogCommentMapper  
 * @Description: 博客评论  
 * @author ZKZ  
 * @date 2017年3月24日  
 *
 */
public interface BlogCommentMapper {
    
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
     * @Description: 查询评论列表 - 分页
     * @param paramMap
     * @return: List<BlogCommentModel>    返回类型
     */
    List<BlogCommentModel> list(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: select  
     * @Description: 获取评论信息
     * @param paramMap
     * @return: BlogCommentModel    返回类型
     */
    BlogCommentModel select(Map<String, Object> paramMap);

    /**
     * 
     * @Title: getCommentAllInfo  
     * @Description: 获取评论详情（所有）
     * @param paramMap
     * @return: BlogCommentModel    返回类型
     */
	BlogCommentModel getCommentAllInfo(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: insert  
     * @Description: 保存评论信息
     * @param blogComment
     * @return: int    返回类型
     */
    int insert(BlogComment blogComment);
    
    /**
     * 
     * @Title: delete  
     * @Description: 删除评论
     * @param paramMap
     * @return: int    返回类型
     */
    int delete(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: shield  
     * @Description: 屏蔽评论
     * @param paramMap
     * @return: int    返回类型
     */
    int shield(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: queryCommentCurrentPageNum  
     * @Description: 获取当前记录 所在的页码
     * @param paramsMap
     * @return    参数  
     * @return: int    返回类型
     */
	int queryCommentCurrentPageNum(Map<String, Object> paramsMap);

    /**
     * 
     * @Title: updateData  
     * @Description: 修改数量
     * @param paramMap
     * @return: int    返回类型
     */
	int updateData(Map<String, Object> paramMap);

}