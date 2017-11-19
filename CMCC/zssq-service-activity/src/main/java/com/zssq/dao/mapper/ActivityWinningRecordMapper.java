package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zssq.dao.pojo.ActivityWinningRecord;
import com.zssq.dao.pojo.ActivityWinningRecordExample;

public interface ActivityWinningRecordMapper {
    int countByExample(ActivityWinningRecordExample example);

    int deleteByExample(ActivityWinningRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivityWinningRecord record);

    int insertSelective(ActivityWinningRecord record);

    List<ActivityWinningRecord> selectByExample(ActivityWinningRecordExample example);

    ActivityWinningRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ActivityWinningRecord record, @Param("example") ActivityWinningRecordExample example);

    int updateByExample(@Param("record") ActivityWinningRecord record, @Param("example") ActivityWinningRecordExample example);

    int updateByPrimaryKeySelective(ActivityWinningRecord record);

    int updateByPrimaryKey(ActivityWinningRecord record);

    /**
     * @Function delWinningRecord
     * @Description 撤销评奖
     * @param paramMap
     */
	void delWinningRecord(Map<String, Object> paramMap);

	/**
	 * @Function updateAwardStatus
	 * @Description 批量更新奖项授予状态
	 * @param paramMap
	 * @return
	 */
	int updateAwardStatus(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: getAwardeeList  
	 * @Description: 获取获奖人列表
	 * @param paramMap
	 * @return
	 */
	List<ActivityWinningRecord> getAwardeeList(Map<String, Object> paramMap);
}