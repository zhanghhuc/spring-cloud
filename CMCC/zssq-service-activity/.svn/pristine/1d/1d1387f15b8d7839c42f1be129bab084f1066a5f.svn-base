package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zssq.dao.pojo.ActivityInfo;
import com.zssq.dao.pojo.ActivityInfoExample;
import com.zssq.dao.pojo.ActivityInfoWithBLOBs;

public interface ActivityInfoMapper {
    int countByExample(ActivityInfoExample example);

    int deleteByExample(ActivityInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivityInfoWithBLOBs record);

    int insertSelective(ActivityInfoWithBLOBs record);

    List<ActivityInfoWithBLOBs> selectByExampleWithBLOBs(ActivityInfoExample example);

    List<ActivityInfo> selectByExample(ActivityInfoExample example);

    ActivityInfoWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ActivityInfoWithBLOBs record, @Param("example") ActivityInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") ActivityInfoWithBLOBs record, @Param("example") ActivityInfoExample example);

    int updateByExample(@Param("record") ActivityInfo record, @Param("example") ActivityInfoExample example);

    int updateByPrimaryKeySelective(ActivityInfoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ActivityInfoWithBLOBs record);

    int updateByPrimaryKey(ActivityInfo record);

    /**
     * 查询活动的奖项和资源
     * @param map
     * @return
     */
	ActivityInfoWithBLOBs selectPrizeAndResource(Map<String, Object> map);
	/**
	 * 增加/减少评论数
	 * @Function updateCommentNum
	 * @Description 
	 * @param paramMap
	 * @return
	 */
	int updateCommentNum(Map<String, Object> paramMap);
	/**
	 * 增加/减少 收藏数
	 * @Function updateCollectionNum
	 * @Description 
	 * @param paramMap
	 * @return
	 */
	int updateCollectionNum(Map<String, Object> paramMap);
	/**
	 * 增加/减少 点赞数
	 * @Function updatePraiseNum
	 * @Description 
	 * @param paramMap
	 * @return
	 */
	int updatePraiseNum(Map<String, Object> paramMap);
	/**
	 * 增加/减少 分享数
	 * @Function updateShareNum
	 * @Description 
	 * @param paramMap
	 * @return
	 */
	int updateShareNum(Map<String, Object> paramMap);
	
	/**
	 * @Title: updateJoinNum
	 * @Description: 增加/减少 参与数
	 * @param paramMap
	 * @return
	 */
	int updateJoinNum(Map<String, Object> paramMap);
	/**
	 * 查询活动收藏数
	 * @Function queryCollectionNum
	 * @Description 
	 * @param activityCode 活动CODE
	 * @return
	 */
	int queryCollectionNum(String activityCode);
	/**
	 * 查询活动点赞数
	 * @Function queryPraiseNum
	 * @Description 
	 * @param activityCode 活动CODE
	 * @return
	 */
	int queryPraiseNum(String activityCode);
	
	/**
	 * 
	 * @Title: updateActivityListStatus
	 * @Description: 批量更新活动状态
	 * @param paramMap
	 * @return
	 */
	int updateActivityListStatus(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: queryActivityList
	 * @Description:查询活动列表
	 * @param paramMap
	 * @return
	 */
	List<ActivityInfo> queryActivityList(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: queryActivityList
	 * @Description:查询活动列表详情
	 * @param paramMap
	 * @return
	 */
	List<ActivityInfoWithBLOBs> queryActivityListWithBLOBs(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: queryActivityCollectionList
	 * @Description: 查询收藏的活动列表
	 * @param paramMap
	 * @return
	 */
	List<ActivityInfoWithBLOBs> queryActivityCollectionList(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: queryActivityCollectionList
	 * @Description: h5端查询收藏的活动列表
	 * @param paramMap
	 * @return
	 */
	List<ActivityInfo> queryActivityCollectionListForH5(Map<String, Object> paramMap);
}