package com.zssq.dao.mapper;

import java.util.Map;

import com.zssq.dao.pojo.RelationLike;

public interface RelationLikeMapper {
    /*int deleteByPrimaryKey(Long id);*/
    /**
     * 
     * @Title: insert  
     * @Description: 插入like use
     * @param record
     * @return    参数  
     * @return: int    返回类型
     */
    int insert(RelationLike record);

  /*  int insertSelective(RelationLike record);

    RelationLike selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RelationLike record);

    int updateByPrimaryKey(RelationLike record);*/
    /**
     * 
     * @Title: delete  
     * @Description: 取消点赞
     * @param paramMap
     * @return    参数  
     * @return: int    返回类型
     */
	int delete(Map<String, Object> paramMap);
}