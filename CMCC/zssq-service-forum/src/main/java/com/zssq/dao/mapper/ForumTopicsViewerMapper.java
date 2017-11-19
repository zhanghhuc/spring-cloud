package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zssq.dao.pojo.ForumTopicsViewer;
import com.zssq.dao.pojo.ForumTopicsViewerExample;

public interface ForumTopicsViewerMapper {
    int countByExample(ForumTopicsViewerExample example);

    int deleteByExample(ForumTopicsViewerExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ForumTopicsViewer record);

    int insertSelective(ForumTopicsViewer record);

    List<ForumTopicsViewer> selectByExample(ForumTopicsViewerExample example);

    ForumTopicsViewer selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ForumTopicsViewer record, @Param("example") ForumTopicsViewerExample example);

    int updateByExample(@Param("record") ForumTopicsViewer record, @Param("example") ForumTopicsViewerExample example);

    int updateByPrimaryKeySelective(ForumTopicsViewer record);

    int updateByPrimaryKey(ForumTopicsViewer record);

    /**
     * @Function updateViewCount
     * @Description 更新主贴浏览数
     * @param topicCode
     * @param tenantCode
     */
	void updateViewCount(Map<String,Object> paramMap);
}