package com.zssq.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.zssq.dao.pojo.ActivityResource;
import com.zssq.dao.pojo.ActivityResourceExample;

public interface ActivityResourceMapper {
    int countByExample(ActivityResourceExample example);

    int deleteByExample(ActivityResourceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivityResource record);

    int insertSelective(ActivityResource record);

    List<ActivityResource> selectByExample(ActivityResourceExample example);

    ActivityResource selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ActivityResource record, @Param("example") ActivityResourceExample example);

    int updateByExample(@Param("record") ActivityResource record, @Param("example") ActivityResourceExample example);

    int updateByPrimaryKeySelective(ActivityResource record);

    int updateByPrimaryKey(ActivityResource record);

    /**
     * 批量插入活动资源
     * @param resourceList
     */
	void batchInsertResource(List<ActivityResource> resourceList);
}