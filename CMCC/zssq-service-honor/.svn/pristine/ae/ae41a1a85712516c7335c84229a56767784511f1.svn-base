package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.zssq.dao.pojo.HonorCommentReply;
import com.zssq.dao.pojo.HonorCommentReplyExample;

@Repository
public interface HonorCommentReplyMapper {
    int countByExample(HonorCommentReplyExample example);

    int deleteByExample(HonorCommentReplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HonorCommentReply record);

    int insertSelective(HonorCommentReply record);

    List<HonorCommentReply> selectByExampleWithBLOBs(HonorCommentReplyExample example);

    List<HonorCommentReply> selectByExample(HonorCommentReplyExample example);

    HonorCommentReply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HonorCommentReply record, @Param("example") HonorCommentReplyExample example);

    int updateByExampleWithBLOBs(@Param("record") HonorCommentReply record, @Param("example") HonorCommentReplyExample example);

    int updateByExample(@Param("record") HonorCommentReply record, @Param("example") HonorCommentReplyExample example);

    int updateByPrimaryKeySelective(HonorCommentReply record);

    int updateByPrimaryKeyWithBLOBs(HonorCommentReply record);

    int updateByPrimaryKey(HonorCommentReply record);

    /**
     * @Function updatePraiseCountIncreaseByReply
     * @Description 更新点赞回复时点赞数的方法
     * @param paramMap
     * @return
     */
	int updatePraiseCountByReply(Map<String, Object> paramMap);

	/**
	 * 
	 * @Function selectCommentReplyCount
	 * @Description 查询回复列表 剩余条数
	 * @param paramMap
	 * @return
	 */
	Integer selectCommentReplyCount(Map<String, Object> paramMap);
	
	/**
	 * @Function selectCommentList
	 * @Description 查询评论回复列表
	 * @param paramMap
	 * @return
	 */
	List<HonorCommentReply> selectCommentReplyList(Map<String, Object> paramMap);

	
	/**
	 * @Function selectCommentReplyCountForLocation
	 * @Description 查询评论回复数 -- 定位 剩余条数
	 * @param paramMap
	 * @return
	 */
	Integer selectCommentReplyCountForLocation(Map<String, Object> paramMap);

	/**
	 * @Function selectCommentReplyListForLocation
	 * @Description 查询评论回复列表 -- 定位
	 * @param paramMap
	 * @return
	 */
	List<HonorCommentReply> selectCommentReplyListForLocation(Map<String, Object> paramMap);
	
	/**
	 * @Function updateHonorReplyForComplaintByReply
	 * @Description 删除或撤销删除回复时 更新回复信息
	 * @param paramMap
	 * @return
	 */
	int updateHonorReplyForComplaintByReply(Map<String, Object> paramMap);
	
	/*=============================================================================================================== */
	/**
	 * @Function selectCommentReplyCountWithPage
	 * @Description 查询评论回复总数 -- 分页
	 * @param paramMap
	 * @return
	 */
	int selectCommentReplyCountWithPage(Map<String, Object> paramMap);
	
	/**
	 * @Function selectCommentReplyListWithPage
	 * @Description 查询评论回复列表 -- 分页
	 * @param paramMap
	 * @return
	 */
	
	List<HonorCommentReply> selectCommentReplyListWithPage(Map<String, Object> paramMap);
	/*=============================================================================================================== */





	

	
}