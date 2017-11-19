package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zssq.dao.pojo.ForumTopicsFollow;
import com.zssq.dao.pojo.ForumTopicsFollowExample;

public interface ForumTopicsFollowMapper {
    int countByExample(ForumTopicsFollowExample example);

    int deleteByExample(ForumTopicsFollowExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ForumTopicsFollow record);

    int insertSelective(ForumTopicsFollow record);

    List<ForumTopicsFollow> selectByExampleWithBLOBs(ForumTopicsFollowExample example);

    List<ForumTopicsFollow> selectByExample(ForumTopicsFollowExample example);

    ForumTopicsFollow selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ForumTopicsFollow record, @Param("example") ForumTopicsFollowExample example);

    int updateByExampleWithBLOBs(@Param("record") ForumTopicsFollow record, @Param("example") ForumTopicsFollowExample example);

    int updateByExample(@Param("record") ForumTopicsFollow record, @Param("example") ForumTopicsFollowExample example);

    int updateByPrimaryKeySelective(ForumTopicsFollow record);

    int updateByPrimaryKeyWithBLOBs(ForumTopicsFollow record);

    int updateByPrimaryKey(ForumTopicsFollow record);
    /**
     * 更新跟帖回复数
     * @Function updateFollowCount
     * @Description 
     * @param map
     * @return
     */
    int updateFollowCount(Map<String, Object> map);
    
    /**
     * 获得被举报跟帖所在行号
     * @param follow
     * @return
     */
    Integer getFollowTopicRownum(ForumTopicsFollow follow);
    /**
     * 加载更多的方式分页查询
     * @Function queryByPageWithLoadMore
     * @Description 
     * @param follow
     * @return
     */
    List<ForumTopicsFollow> queryByPageWithLoadMore(ForumTopicsFollow follow);
}