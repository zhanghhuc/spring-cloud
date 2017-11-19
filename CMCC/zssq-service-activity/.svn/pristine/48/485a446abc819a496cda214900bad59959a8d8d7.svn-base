package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zssq.dao.pojo.ActivityTemplate;
import com.zssq.dao.pojo.ActivityTemplateExample;

public interface ActivityTemplateMapper {
    int countByExample(ActivityTemplateExample example);

    int deleteByExample(ActivityTemplateExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivityTemplate record);

    int insertSelective(ActivityTemplate record);

    List<ActivityTemplate> selectByExample(ActivityTemplateExample example);

    ActivityTemplate selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ActivityTemplate record, @Param("example") ActivityTemplateExample example);

    int updateByExample(@Param("record") ActivityTemplate record, @Param("example") ActivityTemplateExample example);

    int updateByPrimaryKeySelective(ActivityTemplate record);

    int updateByPrimaryKey(ActivityTemplate record);

    /**
     * 活动模板使用次数加一
     * @param map
     */
	void updateUseNum(Map<String, Object> map);
}