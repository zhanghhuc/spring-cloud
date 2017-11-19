package com.zssq.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.zssq.dao.pojo.ActivityHistory;
import com.zssq.dao.pojo.ActivityHistoryExample;

public interface ActivityHistoryMapper {
    int countByExample(ActivityHistoryExample example);

    int deleteByExample(ActivityHistoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivityHistory record);

    int insertSelective(ActivityHistory record);

    List<ActivityHistory> selectByExample(ActivityHistoryExample example);

    ActivityHistory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ActivityHistory record, @Param("example") ActivityHistoryExample example);

    int updateByExample(@Param("record") ActivityHistory record, @Param("example") ActivityHistoryExample example);

    int updateByPrimaryKeySelective(ActivityHistory record);

    int updateByPrimaryKey(ActivityHistory record);
    
    /**
     * 
     * @Title: insertHistoryList
     * @Description: 批量插入历程记录
     * @param historyList
     * @return
     */
    int insertHistoryList(List<ActivityHistory> historyList);
}