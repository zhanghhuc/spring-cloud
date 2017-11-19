package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.zssq.dao.pojo.HonorComment;
import com.zssq.dao.pojo.HonorCommentExample;

@Repository
public interface HonorCommentMapper {
    int countByExample(HonorCommentExample example);

    int deleteByExample(HonorCommentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HonorComment record);

    int insertSelective(HonorComment record);

    List<HonorComment> selectByExampleWithBLOBs(HonorCommentExample example);

    List<HonorComment> selectByExample(HonorCommentExample example);

    HonorComment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HonorComment record, @Param("example") HonorCommentExample example);

    int updateByExampleWithBLOBs(@Param("record") HonorComment record, @Param("example") HonorCommentExample example);

    int updateByExample(@Param("record") HonorComment record, @Param("example") HonorCommentExample example);

    int updateByPrimaryKeySelective(HonorComment record);

    int updateByPrimaryKeyWithBLOBs(HonorComment record);

    int updateByPrimaryKey(HonorComment record);
    

	
    /**
     * @Function updatePraiseCountIncreaseByComment
     * @Description 点赞评论时增加点赞数
     * @param paramMap
     * @return
     */
	int updatePraiseCountByComment(Map<String, Object> paramMap);

	/**
	 * @Function selectCommentList
	 * @Description 查询评论列表
	 * @param paramMap
	 * @return
	 */
	List<HonorComment> selectCommentList(Map<String, Object> paramMap);
	
	/**
	 * @Function selectCommentCount
	 * @Description 查询评论总数
	 * @param paramMap
	 * @return
	 */
	Integer selectCommentCount(Map<String, Object> paramMap);

	/**
	 * @Function updateIncreaseReplyCount
	 * @Description 发表回复时 增加评论的回复数
	 * @param paramMap
	 * @return
	 */
	int updateIncreaseReplyCount(Map<String, Object> paramMap);

	/**
	 * @Function updateDecreaseReplyCount
	 * @Description 删除回复时 减少评论的回复数
	 * @param paramMap
	 * @return
	 */
	int updateDecreaseReplyCount(Map<String, Object> paramMap);

	/**
	 * @Function selectCommentListForLocation
	 * @Description 查询评论列表 -- 定位
	 * @param paramMap
	 * @return
	 */
	List<HonorComment> selectCommentListForLocation(Map<String, Object> paramMap);
	
	/**
	 * @Function updateHonorCommentForComplaintByReply
	 * @Description 删除或撤销删除回复时 更新荣誉评论信息
	 * @param paramMap
	 * @return
	 */
	int updateHonorCommentForComplaintByReply(Map<String, Object> paramMap);
	
	/**
	 * @Function updateHonorCommentForComplaintByComment
	 * @Description 删除或撤销删除评论时 更新评论信息
	 * @param paramMap
	 * @return
	 */
	int updateHonorCommentForComplaintByComment(Map<String, Object> paramMap);
    
    
	
	
	/*=============================================================================================================== */
	/**
	 * @Function selectCommentCount
	 * @Description 查询评论数
	 * @param paramMap
	 * @return
	 */
	int selectCommentCountWithPage(Map<String, Object> paramMap);

	/**
	 * @Function selectCommentList
	 * @Description 查询评论列表
	 * @param paramMap
	 * @return
	 */
	List<HonorComment> selectCommentListWithPage(Map<String, Object> paramMap);
	/*=============================================================================================================== */







	
}