package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zssq.dao.pojo.CollectionResultEntity;
import com.zssq.dao.pojo.ForumCollection;
import com.zssq.dao.pojo.ForumCollectionExample;

public interface ForumCollectionMapper {
    int countByExample(ForumCollectionExample example);

    int deleteByExample(ForumCollectionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ForumCollection record);

    int insertSelective(ForumCollection record);

    List<ForumCollection> selectByExample(ForumCollectionExample example);

    ForumCollection selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ForumCollection record, @Param("example") ForumCollectionExample example);

    int updateByExample(@Param("record") ForumCollection record, @Param("example") ForumCollectionExample example);

    int updateByPrimaryKeySelective(ForumCollection record);

    int updateByPrimaryKey(ForumCollection record);
    
    int countCollectionList(Map<String,Object> paramMap);
    
    List<CollectionResultEntity> selectCollectionList(Map<String,Object> paramMap);
}