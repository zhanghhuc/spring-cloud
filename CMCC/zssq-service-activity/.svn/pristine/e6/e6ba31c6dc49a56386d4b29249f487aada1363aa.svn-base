package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zssq.dao.pojo.ActivityPrize;
import com.zssq.dao.pojo.ActivityPrizeExample;

public interface ActivityPrizeMapper {
    int countByExample(ActivityPrizeExample example);

    int deleteByExample(ActivityPrizeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivityPrize record);

    int insertSelective(ActivityPrize record);

    List<ActivityPrize> selectByExampleWithBLOBs(ActivityPrizeExample example);

    List<ActivityPrize> selectByExample(ActivityPrizeExample example);

    ActivityPrize selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ActivityPrize record, @Param("example") ActivityPrizeExample example);

    int updateByExampleWithBLOBs(@Param("record") ActivityPrize record, @Param("example") ActivityPrizeExample example);

    int updateByExample(@Param("record") ActivityPrize record, @Param("example") ActivityPrizeExample example);

    int updateByPrimaryKeySelective(ActivityPrize record);

    int updateByPrimaryKeyWithBLOBs(ActivityPrize record);

    int updateByPrimaryKey(ActivityPrize record);

    /**
     * 批量添加活动奖项数据
     * @param prizeList
     * @return
     */
	int batchInsertPrize(List<ActivityPrize> prizeList);

	/**
	 * @Function getActivityPrizeInfoForAward
	 * @Description 获取用于颁发奖励所用的活动奖项信息
	 * @param paramMap
	 * @return
	 */
	ActivityPrize getActivityPrizeInfoForAward(Map<String, Object> paramMap);
}