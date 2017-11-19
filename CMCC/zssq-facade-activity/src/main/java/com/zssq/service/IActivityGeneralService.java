package com.zssq.service;

import java.util.List;

import com.zssq.dao.pojo.ActivityCollection;
import com.zssq.dao.pojo.ActivityComment;
import com.zssq.dao.pojo.ActivityCommentReply;
import com.zssq.dao.pojo.ActivityInfo;
import com.zssq.dao.pojo.ActivityInfoWithBLOBs;
import com.zssq.exceptions.BusinessException;
import com.zssq.utils.PageInfo;

/**
 * 活动模块通用业务（评论，回复，点赞，收藏）
 * @ClassName IActivityCommonService
 * @Description 
 * @author liurong
 * @date 2017年4月21日 下午2:09:54
 * @version 1.0
 * @since JDK 1.7
 */
public interface IActivityGeneralService {
	
	/**
	 * 查询评论列表
	 * @Function getCommentList
	 * @Description 
	 * @param comment
	 * @return
	 * @throws BusinessException
	 */
	PageInfo getCommentList(ActivityComment comment) throws BusinessException;
	/**
	 * 查询评论的回复列表
	 * @Function getCommentReplyList
	 * @Description 
	 * @param commentReply
	 * @return
	 * @throws BusinessException
	 */
	PageInfo getCommentReplyList(ActivityCommentReply commentReply)throws BusinessException;
	/**
	 * 新增评论信息
	 * @Function addComment
	 * @Description 
	 * @param comment
	 * @return commentCode  评论code
	 * @throws BusinessException
	 */
	ActivityComment addComment(ActivityComment comment)throws BusinessException;
	/**
	 * 新增回复信息
	 * @Function addReply
	 * @Description 
	 * @param commentReply
	 * @return replyCode 回复CODE
	 * @throws BusinessException
	 */
	ActivityCommentReply addReply(ActivityCommentReply commentReply) throws BusinessException;
	/**
	 * 收藏活动
	 * @Function insertCollection
	 * @Description 
	 * @param collection
	 * @return 最新的活动收藏数
	 * @throws BusinessException
	 */
	int insertCollection(ActivityCollection collection) throws BusinessException;
	/**
	 * 取消收藏活动
	 * @Function deleteCollection
	 * @Description 
	 * @param collection
	 * @return 最新的活动收藏数
	 * @throws BusinessException
	 */
	int deleteCollection(ActivityCollection collection) throws BusinessException;
	/**
	 * 点赞活动
	 * @Function insertPraiseOfActivity
	 * @Description 
	 * @param tenantCode   租户标识
	 * @param orgCode      组织机构编码
	 * @param admirerCode  点赞人CODE
	 * @param activityCode 活动CODE
	 * @return 最新的活动点赞数
	 * @throws BusinessException
	 */
	int insertPraiseOfActivity(String tenantCode, String orgCode, String admirerCode, String activityCode) throws BusinessException;
	/**
	 * 取消点赞活动
	 * @Function deletePraiseOfActivity
	 * @Description 
	 * @param admirerCode   点赞人CODE
	 * @param activityCode  活动CODE
	 * @return 最新的活动点赞数
	 * @throws BusinessException
	 */
	int deletePraiseOfActivity(String admirerCode, String activityCode) throws BusinessException;
	/**
	 * 点赞评论
	 * @Function insertPraiseOfComment
	 * @Description 
	 * @param tenantCode   租户标识
	 * @param orgCode      组织机构编码
	 * @param admirerCode  点赞人CODE
	 * @param commentCode  评论CODE
	 * @return 最新的评论点赞数
	 * @throws BusinessException
	 */
	int insertPraiseOfComment(String tenantCode, String orgCode, String admirerCode, String commentCode) throws BusinessException;
	/**
	 * 取消点赞评论
	 * @Function deletePraiseOfComment
	 * @Description 
	 * @param admirerCode   点赞人CODE
	 * @param commentCode   评论CODE
	 * @return 最新的评论点赞数
	 * @throws BusinessException
	 */
	int deletePraiseOfComment(String admirerCode, String commentCode) throws BusinessException;
	/**
	 * 点赞回复
	 * @Function insertPraiseOfReply
	 * @Description 
	 * @param tenantCode   租户标识
	 * @param orgCode      组织机构编码
	 * @param admirerCode  点赞人CODE
	 * @param replyCode    回复CODE
	 * @return 最新的回复点赞数
	 * @throws BusinessException
	 */
	int insertPraiseOfReply(String tenantCode, String orgCode, String admirerCode, String replyCode) throws BusinessException;
	/**
	 * 取消点赞回复
	 * @Function deletePraiseOfReply
	 * @Description 
	 * @param admirerCode   点赞人CODE
	 * @param replyCode     回复CODE
	 * @return 最新的回复点赞数
	 * @throws BusinessException
	 */
	int deletePraiseOfReply(String admirerCode, String replyCode) throws BusinessException;
	/**
	 * 删除评论
	 * @Function deleteCommentByCode
	 * @Description 
	 * @param tenantCode   租户标识
	 * @param userCode     用户CODE
	 * @param commentCode  评论CODE
	 * @throws BusinessException
	 */
	void deleteCommentByCode(String tenantCode, String userCode, String commentCode) throws BusinessException;
	/**
	 * 删除回复
	 * @Function deleteReplyByCode
	 * @Description 
	 * @param tenantCode  租户标识
	 * @param userCode    用户CODE
	 * @param replyCode   回复CODE
	 * @throws BusinessException
	 */
	void deleteReplyByCode(String tenantCode, String userCode, String replyCode) throws BusinessException;
	/**
	 * 增加  count个分享数
	 * @Function updateIncreaseShareNumByCode
	 * @Description 
	 * @param code      投票主表CODE
	 * @param count     变量
	 * @throws BusinessException
	 */
	void updateIncreaseShareNumByCode(String code, int count) throws BusinessException;
	
	/**
	 * 
	 * @Title: getActivityComment
	 * @Description: 查询评论详情
	 * @param code 评论code
	 * @return
	 * @throws BusinessException
	 */
	ActivityComment getActivityComment(String code) throws BusinessException;
	
	/**
	 * 
	 * @Title: getActivityCommentReply
	 * @Description: 查询回复详情
	 * @param code 回复code
	 * @return
	 * @throws BusinessException
	 */
	ActivityCommentReply getActivityCommentReply(String code) throws BusinessException;
	
	/**
	 * 
	 * @Title: getActivityCollectionList
	 * @Description: 查询我的收藏活动列表
	 * @param userCode
	 * @param tenantCode
	 * @param page
	 * @return
	 * @throws BusinessException
	 */
	List<ActivityInfoWithBLOBs> getActivityCollectionList(String userCode, String tenantCode, PageInfo page) throws BusinessException;
	
	/**
	 * 
	 * @Title: getActivityCollectionList
	 * @Description: h5端查询我的收藏活动列表
	 * @param userCode
	 * @param tenantCode
	 * @param page
	 * @return
	 * @throws BusinessException
	 */
	List<ActivityInfo> getActivityCollectionListForH5(String userCode, String tenantCode, PageInfo page) throws BusinessException;
}
 