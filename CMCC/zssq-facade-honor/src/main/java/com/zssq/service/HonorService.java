package com.zssq.service;

import java.util.List;

import com.zssq.dao.pojo.HonorAwardRecord;
import com.zssq.dao.pojo.HonorComment;
import com.zssq.dao.pojo.HonorCommentReply;
import com.zssq.dao.pojo.HonorDefinition;
import com.zssq.dao.pojo.HonorPraise;
import com.zssq.exceptions.BusinessException;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;

/**
 * @ClassName HonorService
 * @Description 荣誉管理模块接口
 * @author LiuYunLong
 * @date 2017年3月17日 下午3:34:24
 * @version 1.0
 * @since JDK 1.7
 */
public interface HonorService {
	/**
	 * @Function getHonorList
	 * @Description 获取荣誉列表
	 * @return PageBean 分页对象
	 * @throws BusinessException
	 */
	public PageBean getHonorList(PageParam pageParam,HonorDefinition honorDefinition) throws BusinessException;

	/**
	 * @Function updateHonor
	 * @Description 更新荣誉
	 * @param honorDefinition 荣誉对象
	 * @return 
	 * @throws BusinessException
	 */
	public void updateHonor(HonorDefinition honorDefinition) throws BusinessException;

	/**
	 * @Function addHonor
	 * @Description 添加荣誉
	 * @param honorDefinition 荣誉对象
	 * @return honorCode 荣誉Code
	 * @throws BusinessException
	 */
	public String addHonor(HonorDefinition honorDefinition) throws BusinessException;

	/**
	 * @Function addAwardingHonor
	 * @Description 颁发荣誉
	 * @param honorAwardRecord 荣誉授予记录对象
	 * @return awardCode  荣誉授予记录Code
	 * @throws BusinessException
	 */
	public List<String> addAwardingHonor(HonorAwardRecord honorAwardRecord) throws BusinessException;

	/**
	 * @Function getAwardRecordList
	 * @Description 查询荣誉颁发记录列表
	 * @param pageParam 分页参数
	 * @param honorAwardRecord 荣誉颁发记录对象
	 * @return PageBean 分页对象
	 * @throws BusinessException
	 */
	public PageBean getAwardRecordList(PageParam pageParam, HonorAwardRecord honorAwardRecord) throws BusinessException;

	/**
	 * @Function updateAwardStatus
	 * @Description 撤销荣誉
	 * @param awardCode 荣誉授予记录Code
	 * @param tenantCode 租户Code
	 * @param userCode   用户Code
	 * @return true:成功 false:失败
	 * @throws BusinessException
	 */
	public boolean updateAwardStatus(String awardCode, String tenantCode, String userCode) throws BusinessException;

	/**
	 * @Function getAwardWall
	 * @Description 查询荣誉墙
	 * @param pageParam 分页参数
	 * @param honorAwardRecord 荣誉颁发记录对象
	 * @return PageBean 分页对象
	 * @throws BusinessException
	 */
	public PageBean getAwardWall(PageParam pageParam, HonorAwardRecord honorAwardRecord) throws BusinessException;
	/**
	 * @Function updatePraiseStatus
	 * @Description 点赞/取消点赞操作
	 * @param honorPraise
	 * @return praiseCount
	 * @throws BusinessException
	 */
	public int updatePraiseStatus(HonorPraise honorPraise)throws BusinessException;

	/**
	 * @Function getCommentListWithPage
	 * @Description 查询评论列表 -- 分页
	 * @param pageParam 分页参数
	 * @param honorComment 评论对象
	 * @return PageBean 分页对象
	 * @throws BusinessException
	 */
	public PageBean getCommentList(HonorComment honorComment)throws BusinessException;

	/**
	 * @Function getCommentReplyList
	 * @Description 查询回复列表
	 * @param pageParam 分页参数
	 * @param honorCommentReply 荣誉评论回复对象
	 * @return PageBean 分页对象
	 * @throws BusinessException
	 */
	public PageBean getCommentReplyList(HonorCommentReply honorCommentReply)throws BusinessException;

	/**
	 * @Function addComment
	 * @Description 发表评论
	 * @param honorComment 评论对象
	 * @return commentCode 评论Code
	 * @throws BusinessException
	 */
	public String addComment(HonorComment honorComment)throws BusinessException;

	/**
	 * @Function addCommentReply
	 * @Description 发表回复
	 * @param honorCommentReply 评论回复对象
	 * @return replyCode 回复Code
	 * @throws BusinessException
	 */
	public String addCommentReply(HonorCommentReply honorCommentReply)throws BusinessException;

	/**
	 * @Function deleteComment
	 * @Description 删除评论
	 * @param honorComment 评论对象
	 * @throws BusinessException
	 */
	public void deleteComment(HonorComment honorComment)throws BusinessException;

	/**
	 * @Function deleteCommentReply
	 * @Description 删除评论回复
	 * @param honorCommentReply 评论回复对象
	 * @throws BusinessException
	 */
	public void deleteCommentReply(HonorCommentReply honorCommentReply)throws BusinessException;

	/**
	 * @Function getCommentListForLocation
	 * @Description 查询评论列表 --定位
	 * @param honorComment 评论对象
	 * @return PageBean 返回实体
	 * @throws BusinessException
	 */
	public PageBean getCommentListForLocation(HonorComment honorComment)throws BusinessException;
	
	/**
	 * @Function getCommentReplyListForLocation
	 * @Description 查询评论回复列表 -- 定位
	 * @param honorCommentReply 评论回复对象
	 * @return PageBean 返回实体
	 * @throws BusinessException
	 */
	public PageBean getCommentReplyListForLocation(HonorCommentReply honorCommentReply)throws BusinessException;
	
	/**
	 * @Function getAwardRecordDetail
	 * @Description 查询荣誉授予记录详情
	 * @param honorAwardRecord  荣誉授予记录对象
	 * @return HonorAwardRecord 荣誉授予记录对象
	 * @throws BusinessException
	 */
	public HonorAwardRecord getAwardRecordDetail(HonorAwardRecord honorAwardRecord)throws BusinessException;
	
	
	
	/*=================================================================================================*/
	/**
	 * @Function getCommentListWithPage
	 * @Description 查询评论列表 -- 分页
	 * @param pageParam 分页参数
	 * @param honorComment 评论对象
	 * @return PageBean 分页对象
	 * @throws BusinessException
	 */
	public PageBean getCommentListWithPage(PageParam pageParam, HonorComment honorComment)throws BusinessException;

	/**
	 * @Function getCommentReplyListWithPage
	 * @Description 查询回复列表
	 * @param pageParam 分页参数
	 * @param honorCommentReply 荣誉评论回复对象
	 * @return PageBean 分页对象
	 * @throws BusinessException
	 */
	public PageBean getCommentReplyListWithPage(PageParam pageParam, HonorCommentReply honorCommentReply)throws BusinessException;

	/**
	 * @Function deleteAwardRecord
	 * @Description 发布动态失败后 授予记录数据回滚
	 * @param tenantCode
	 * @param code
	 */
	public void deleteAwardRecord(String tenantCode, String code)throws BusinessException;

	/**
	 * @Function getAwardHonorRecordInfoByCode
	 * @Description 根据荣誉授予记录Code 查询荣誉授予记录详情
	 * @param code
	 * @param tenantCode
	 * @return
	 * @throws BusinessException
	 */
	public HonorAwardRecord getAwardHonorRecordInfoByCode(String code, String tenantCode)throws BusinessException;

	/**
	 * @Function getHonorInfo
	 * @Description 获取荣誉详情
	 * @param honorDefinition
	 * @return
	 * @throws BusinessException
	 */
	public HonorDefinition getHonorInfo(HonorDefinition honorDefinition) throws BusinessException;

	/**
	 * @Function getCommentInfo
	 * @Description 获取荣誉评论详情
	 * @param commentCode
	 * @param tenantCode
	 * @return
	 * @throws BusinessException
	 */
	public HonorComment getCommentInfo(String commentCode, String tenantCode)throws BusinessException;

	/**
	 * @Function getCommentReplyInfo
	 * @Description 获取评论回复详情
	 * @param replyCode
	 * @param tenantCode
	 * @return
	 * @throws BusinessException
	 */
	public HonorCommentReply getCommentReplyInfo(String replyCode, String tenantCode)throws BusinessException;

	/**
	 * @Function getAwardWallForH5
	 * @Description H5查询荣誉墙
	 * @param honorAwardRecord
	 * @return
	 * @throws BusinessException
	 */
	public List<HonorAwardRecord> getAwardWallForH5(HonorAwardRecord honorAwardRecord)throws BusinessException;

	
	/*=================================================================================================*/




	
}
