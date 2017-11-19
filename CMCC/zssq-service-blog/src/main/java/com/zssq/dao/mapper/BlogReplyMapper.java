package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.dao.model.BlogReplyModel;
import com.zssq.dao.pojo.BlogReply;

/**
 * 
 * @ClassName: BlogReplyMapper  
 * @Description: 博客评论回复  
 * @author ZKZ  
 * @date 2017年3月24日  
 *
 */
public interface BlogReplyMapper {
    
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
     * @Description: 查询回复列表 - 分页
     * @param paramMap
     * @return: List<BlogReplyModel>    返回类型
     */
    List<BlogReplyModel> list(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: select  
     * @Description: 获取回复详情
     * @param paramMap
     * @return: BlogReplyModel    返回类型
     */
    BlogReplyModel select(Map<String, Object> paramMap);

    /**
     * 
     * @Title: getReplyAllInfo  
     * @Description: 获取回复详情（所有）
     * @param paramMap
     * @return: BlogReplyModel    返回类型
     */
	BlogReplyModel getReplyAllInfo(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: insert  
     * @Description: 保存回复信息
     * @param blogReply
     * @return: int    返回类型
     */
    int insert(BlogReply blogReply);
    
    /**
     * 
     * @Title: delete  
     * @Description: 删除回复
     * @param paramMap
     * @return: int    返回类型
     */
    int delete(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: shield  
     * @Description: 屏蔽回复
     * @param paramMap
     * @return: int    返回类型
     */
    int shield(Map<String, Object> paramMap);
    /**
     * 
     * @Title: queryReplyCurrentPageNum  
     * @Description: 回复当前页
     * @param paramsMap
     * @return    参数  
     * @return: int    返回类型
     */
	int queryReplyCurrentPageNum(Map<String, Object> paramsMap);

    /**
     * 
     * @Title: updateData  
     * @Description: 修改数量
     * @param paramMap
     * @return: int    返回类型
     */
	int updateData(Map<String, Object> paramMap);

}