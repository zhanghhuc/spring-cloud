package com.zssq.mblog.dao.mapper;

import java.util.List;

import com.zssq.mblog.pojo.MblogComment;

/**
 * 
    * @ClassName: MblogCommentMapper  
    * @Description: 微博评论Mapper  
    * @author Mr.B  
    * @date 2017年3月20日  
    *
 */
public interface MblogCommentMapper {
	/**
     * 
        * @Title: batchInsert  
        * @Description: 批量插入
        * @param list
    	* @return int    返回类型
     */
    int batchInsert(List<MblogComment> list);
}