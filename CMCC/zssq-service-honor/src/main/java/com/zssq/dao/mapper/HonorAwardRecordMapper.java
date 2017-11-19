package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.zssq.dao.pojo.HonorAwardRecord;
import com.zssq.dao.pojo.HonorAwardRecordExample;

@Repository
public interface HonorAwardRecordMapper {
  public  int countByExample(HonorAwardRecordExample example);

    int deleteByExample(HonorAwardRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HonorAwardRecord record);

    int insertSelective(HonorAwardRecord record);

    List<HonorAwardRecord> selectByExample(HonorAwardRecordExample example);

    HonorAwardRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HonorAwardRecord record, @Param("example") HonorAwardRecordExample example);

    int updateByExample(@Param("record") HonorAwardRecord record, @Param("example") HonorAwardRecordExample example);

    int updateByPrimaryKeySelective(HonorAwardRecord record);

    int updateByPrimaryKey(HonorAwardRecord record);

    /**
     * @Function batchInsert
     * @Description 批量插入授予荣誉数据
     * @param paramMap
     */
	void batchInsert(Map<String, Object> paramMap);

	/**
	 * @Function selectHonorCountForWall
	 * @Description 查询被授予荣誉记录数
	 * @param paramMap
	 * @return
	 */
	int selectHonorCountForWall(Map<String, Object> paramMap);

	/**
	 * @Function selectHonorlistForWall
	 * @Description 查询荣誉墙
	 * @param paramMap
	 * @return
	 */
	List<HonorAwardRecord> selectHonorlistForWall(Map<String, Object> paramMap);

	/**
	 * @Function updatePraiseCount
	 * @Description 更新点赞荣誉时点赞数的方法
	 * @param paramMap
	 */
	int updatePraiseCountByHR(Map<String, Object> paramMap);

	/**
	 * @Function updateDecreaseReplyCount
	 * @Description 删除评论时减少评论数
	 * @param paramMap
	 * @return
	 */
	int updateDecreaseCommentCountByComment(Map<String, Object> paramMap);

	/**
	 * @Function getAwardRecordDetail
	 * @Description 查询荣誉授予记录详情
	 * @param paramMap           过滤条件
	 * @return HonorAwardRecord  荣誉参与记录对象
	 */
	HonorAwardRecord getAwardRecordDetail(Map<String, Object> paramMap);

	/**
	 * @Function updateHonorRecordForComplaintByComment
	 * @Description 删除或撤销删除评论时 更新评论数
	 * @param paramMap
	 */
	void updateHonorRecordForComplaintByComment(Map<String, Object> paramMap);

	/**
	 * @Function updateIncreaseCommentCount
	 * @Description 发表评论时增加评论数
	 * @param paramMap
	 */
	void updateIncreaseCommentCount(Map<String, Object> paramMap);

	/**
	 * @Function selectHonorlistForH5Wall
	 * @Description H5查询荣誉墙
	 * @param paramMap
	 * @return List<HonorAwardRecord>
	 */
	public List<HonorAwardRecord> selectHonorlistForH5Wall(Map<String, Object> paramMap);
	
	
	
	
}