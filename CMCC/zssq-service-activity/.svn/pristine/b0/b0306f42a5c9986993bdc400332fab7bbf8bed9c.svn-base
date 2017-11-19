package com.zssq.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.zssq.constants.ActivityConstants;
import com.zssq.constants.VoteConstants;
import com.zssq.dao.mapper.ActivityCollectionMapper;
import com.zssq.dao.mapper.ActivityCommentMapper;
import com.zssq.dao.mapper.ActivityCommentReplyMapper;
import com.zssq.dao.mapper.ActivityInfoMapper;
import com.zssq.dao.mapper.ActivityPraiseMapper;
import com.zssq.dao.pojo.ActivityCollection;
import com.zssq.dao.pojo.ActivityCollectionExample;
import com.zssq.dao.pojo.ActivityComment;
import com.zssq.dao.pojo.ActivityCommentExample;
import com.zssq.dao.pojo.ActivityCommentReply;
import com.zssq.dao.pojo.ActivityCommentReplyExample;
import com.zssq.dao.pojo.ActivityInfo;
import com.zssq.dao.pojo.ActivityInfoWithBLOBs;
import com.zssq.dao.pojo.ActivityPraise;
import com.zssq.dao.pojo.ActivityPraiseExample;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.CommonService;
import com.zssq.service.IActivityGeneralService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.DateUtils;
import com.zssq.utils.PageInfo;

@Service("activitiGeneralServiceImpl")
public class ActivitiGeneralServiceImpl extends CommonService implements IActivityGeneralService {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private ActivityInfoMapper activityInfoMapper;
	@Autowired
	private ActivityCommentMapper activityCommentMapper;
	@Autowired
	private ActivityCommentReplyMapper activityCommentReplyMapper;
	@Autowired
	private ActivityCollectionMapper activityCollectionMapper;
	@Autowired
	private ActivityPraiseMapper activityPraiseMapper;

	@Override
	public PageInfo getCommentList(ActivityComment comment) throws BusinessException {
		PageInfo pageInfo = new PageInfo();
		try {
			/**1.查询活动是否开启评论功能*/
			ActivityInfo activityInfo = getActivityInfoByCode(comment.getActivityCode(), comment.getTenantCode());
			if (activityInfo != null) {
				Byte isEnableComment = activityInfo.getEnableComment();
				if (isEnableComment.equals(VoteConstants.NO)) {
					pageInfo.setList(new ArrayList<ActivityComment>());
					pageInfo.setTotalItem(0);
					return pageInfo;
				} else {
					/** 2、查询总评论数 */
					pageInfo.setTotalItem(activityInfo.getCommentNum());
				}
			} else {
				// 活动信息不存在
				throw BusinessException.build("ACTIVITY_23002", "活动信息");
			}
			
			/** 3、查询列表集合 */
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("activityCode", comment.getActivityCode());
			paramMap.put("infoType", ActivityConstants.INFO_TYPE_2);// 类型：评论
			paramMap.put("tenantCode", comment.getTenantCode());
			paramMap.put("admirerCode", comment.getAdmirerCode());
			paramMap.put("id", comment.getId());
			paramMap.put("limitStart", 0);
			paramMap.put("limitEnd", comment.getPageSize());
			
			List<ActivityComment> commentList = activityCommentMapper.getCommentList(paramMap);
			pageInfo.setList(commentList);
			return pageInfo;
		} catch (Exception e) {
			log.error("ActivitiGeneralServiceImpl.getCommentList", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@Override
	public PageInfo getCommentReplyList(ActivityCommentReply commentReply) throws BusinessException {
		PageInfo pageInfo = new PageInfo();
		int surplusCount = 0;
		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("activityCode", commentReply.getActivityCode());
			paramMap.put("commentCode", commentReply.getCommentCode());
			paramMap.put("infoType", ActivityConstants.INFO_TYPE_3);
			paramMap.put("tenantCode", commentReply.getTenantCode());
			paramMap.put("admirerCode", commentReply.getAdmirerCode());
			paramMap.put("id", commentReply.getId());
			paramMap.put("limitStart", 0);
			paramMap.put("limitEnd", commentReply.getPageSize());
			/** 1、查询评论回复列表 */
			List<ActivityCommentReply> replyList = activityCommentReplyMapper.getCommentReplyList(paramMap);
			
			if (replyList.size() > 0) {
				paramMap.put("id", replyList.get(replyList.size() - 1).getId());
				/** 2、查询评论回复 剩余记录数 */
				surplusCount = activityCommentReplyMapper.getCommentReplySurplusCount(paramMap);
			}

			pageInfo.setList(replyList);
			pageInfo.setTotalItem(surplusCount);
			return pageInfo;
		} catch (Exception e) {
			log.error("ActivitiGeneralServiceImpl.getCommentReplyList", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@Override
	public ActivityComment addComment(ActivityComment comment) throws BusinessException {
		try {
			// 获取活动详情
			ActivityInfo activityInfo = getActivityInfoByCode(comment.getActivityCode(), comment.getTenantCode());
			if (activityInfo != null) {
				/** 1、判断该活动是否有评论的权限 */
				Byte isEnableComment = activityInfo.getEnableComment();
				if (isEnableComment.equals(VoteConstants.NO)) {
					// 该活动不允许评论
					throw BusinessException.build("ACTIVITY_23012");
				}
			} else {
				// 活动信息不存在
				throw BusinessException.build("ACTIVITY_23002", "活动信息");
			}
			String commentCode = UUIDHelper.getUUID();
			Long time = DateUtils.getFormatDateLong();
			/** 2、插入评论数据 */
			comment.setCode(commentCode);
			comment.setCreateTime(time);
			comment.setModifyTime(time);
			int result = activityCommentMapper.insertSelective(comment);
			if (result > 0) {
				/** 3、增加活动主信息表中的评论数 */
				updateCommentNumForActivity(comment.getActivityCode(), Operator.ADD, result);
			}
			
			ActivityCommentExample example = new ActivityCommentExample();
			ActivityCommentExample.Criteria criteria = example.createCriteria();
			// 查询条件
			criteria.andIsDeleteEqualTo(VoteConstants.NO);
			criteria.andIsDisableEqualTo(VoteConstants.NO);
			criteria.andCodeEqualTo(commentCode);
			
			List<ActivityComment> list = activityCommentMapper.selectByExampleWithBLOBs(example);
			ActivityComment rcomment = new ActivityComment();
			if(list.size() > 0){
				rcomment = list.get(0);
			}
			return rcomment;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivitiGeneralServiceImpl.addComment", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@Override
	public ActivityCommentReply addReply(ActivityCommentReply commentReply) throws BusinessException {
		try {
			// 获取活动详情
			ActivityInfo activityInfo = getActivityInfoByCode(commentReply.getActivityCode(), commentReply.getTenantCode());
			if (activityInfo != null) {
				/** 1、判断该活动是否有评论的权限 */
				Byte isEnableComment = activityInfo.getEnableComment();
				if (isEnableComment.equals(VoteConstants.NO)) {
					// 该活动不允许评论
					throw BusinessException.build("ACTIVITY_23012");
				}
			} else {
				// 活动信息不存在
				throw BusinessException.build("ACTIVITY_23002", "活动信息");
			}
			String replyCode = UUIDHelper.getUUID();
			Long time = DateUtils.getFormatDateLong();
			/** 2、插入回复数据 */
			commentReply.setCode(replyCode);
			commentReply.setCreateTime(time);
			commentReply.setModifyTime(time);
			int result = activityCommentReplyMapper.insertSelective(commentReply);
			if (result > 0) {
				/** 3、增加评论表中的回复数 */
				updateReplyCountForComment(commentReply.getCommentCode(), Operator.ADD, result);
			}
			
			ActivityCommentReplyExample example = new ActivityCommentReplyExample();
			ActivityCommentReplyExample.Criteria criteria = example.createCriteria();
			// 查询条件
			criteria.andIsDeleteEqualTo(VoteConstants.NO);
			criteria.andIsDisableEqualTo(VoteConstants.NO);
			criteria.andCodeEqualTo(replyCode);
			
			List<ActivityCommentReply> replyList = activityCommentReplyMapper.selectByExampleWithBLOBs(example);
			ActivityCommentReply reply = new ActivityCommentReply();
			if(replyList.size() > 0){
				reply = replyList.get(0);
			}
			return reply;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivitiGeneralServiceImpl.addReply", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@Override
	public int insertCollection(ActivityCollection collection) throws BusinessException {
		try {
			Long time = DateUtils.getFormatDateLong();
			/** 1.添加收藏 */
			String code = UUIDHelper.getUUID();
			collection.setCreateTime(time);
			collection.setModifyTime(time);
			collection.setCode(code);
			int result = 0;
			try {
				result = activityCollectionMapper.insertSelective(collection);
			} catch (DuplicateKeyException e) {
				throw BusinessException.build("ACTIVITY_23016", "收藏");
			}
			
			/** 2.增加活动主信息表收藏数 */
			if (result > 0) {
				super.updateCollectionNumForActivity(collection.getActivityCode(), Operator.ADD, result);
			}
			
			/** 3.查询最新收藏数 */
			return activityInfoMapper.queryCollectionNum(collection.getActivityCode());
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivitiGeneralServiceImpl.insertCollection", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@Override
	public int deleteCollection(ActivityCollection collection) throws BusinessException {
		try {
			/** 1.删除收藏表中的信息 */
			ActivityCollectionExample example = new ActivityCollectionExample();
			ActivityCollectionExample.Criteria criteria = example.createCriteria();
			
			criteria.andTenantCodeEqualTo(collection.getTenantCode());
			criteria.andOrgCodeEqualTo(collection.getOrgCode());
			criteria.andActivityCodeEqualTo(collection.getActivityCode());
			criteria.andCollectorCodeEqualTo(collection.getCollectorCode());
			
			int result = activityCollectionMapper.deleteByExample(example);
			
			/** 2.减少活动主信息表收藏数 */
			if (result > 0) {
				super.updateCollectionNumForActivity(collection.getActivityCode(), Operator.SUB, result);
			} else {
				// 活动收藏记录不存在
				throw BusinessException.build("ACTIVITY_23002", "活动收藏记录");
			}
			
			/** 3.查询最新收藏数 */
			return activityInfoMapper.queryCollectionNum(collection.getActivityCode());
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivitiGeneralServiceImpl.deleteCollection", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@Override
	public int insertPraiseOfActivity(String tenantCode, String orgCode, String admirerCode, String activityCode) throws BusinessException {
		try {
			Long time = DateUtils.getFormatDateLong();
			String code = UUIDHelper.getUUID();
			/** 1.添加活动点赞信息*/
			ActivityPraise praise = new ActivityPraise();
			praise.setCode(code);
			praise.setTenantCode(tenantCode);
			praise.setOrgCode(orgCode);
			praise.setCreateTime(time);
			praise.setModifyTime(time);
			praise.setAdmirerCode(admirerCode);
			praise.setInfoCode(activityCode);
			praise.setInfoType(Byte.parseByte(ActivityConstants.INFO_TYPE_1));
			
			int result = 0;
			try {
				result = activityPraiseMapper.insertSelective(praise);
			} catch (DuplicateKeyException e) {
				throw BusinessException.build("ACTIVITY_23016", "点赞");
			}
			
			/** 2.增加活动主信息表点赞数 */
			if (result > 0) {
				super.updatePraiseNumForActivity(praise.getInfoCode(), Operator.ADD, result);
			}
			
			/** 3.查询最新点赞数 */
			return activityInfoMapper.queryPraiseNum(praise.getInfoCode());
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivitiGeneralServiceImpl.insertPraiseOfActivity", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@Override
	public int deletePraiseOfActivity(String admirerCode, String activityCode) throws BusinessException {
		try {
			/** 1.删除点赞表中的信息 */
			ActivityPraiseExample example = new ActivityPraiseExample();
			ActivityPraiseExample.Criteria criteria = example.createCriteria();
			
			criteria.andAdmirerCodeEqualTo(admirerCode);// 点赞人CODE
			criteria.andInfoCodeEqualTo(activityCode);// 被点赞信息CODE
			criteria.andInfoTypeEqualTo(Byte.parseByte(ActivityConstants.INFO_TYPE_1));// 被点赞信息类型
			
			int result = activityPraiseMapper.deleteByExample(example);
			/** 2.减少活动主信息表点赞数 */
			if (result > 0) {
				super.updatePraiseNumForActivity(activityCode, Operator.SUB, result);
			} else {
				// 活动点赞信息不存在
				throw BusinessException.build("ACTIVITY_23002", "活动点赞信息");
			}
			
			/** 3.查询最新点赞数 */
			return activityInfoMapper.queryPraiseNum(activityCode);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivitiGeneralServiceImpl.deletePraiseOfActivity", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@Override
	public int insertPraiseOfComment(String tenantCode, String orgCode, String admirerCode, String commentCode)
			throws BusinessException {
		try {
			Long time = DateUtils.getFormatDateLong();
			String code = UUIDHelper.getUUID();
			/** 1.添加评论点赞信息*/
			ActivityPraise praise = new ActivityPraise();
			praise.setCode(code);
			praise.setTenantCode(tenantCode);
			praise.setOrgCode(orgCode);
			praise.setCreateTime(time);
			praise.setModifyTime(time);
			praise.setAdmirerCode(admirerCode);
			praise.setInfoCode(commentCode);
			praise.setInfoType(Byte.parseByte(ActivityConstants.INFO_TYPE_2));
			
			int result = 0;
			try {
				result = activityPraiseMapper.insertSelective(praise);
			} catch (DuplicateKeyException e) {
				throw BusinessException.build("ACTIVITY_23016", "点赞");
			}
			/** 2.增加评论表中的点赞数 */
			if (result > 0) {
				super.updatePraiseNumForComment(commentCode, Operator.ADD, result);
			}
			
			/** 3.查询最新的评论点赞数 */
			return activityCommentMapper.queryPraiseNum(commentCode);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivitiGeneralServiceImpl.insertPraiseOfComment", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@Override
	public int deletePraiseOfComment(String admirerCode, String commentCode) throws BusinessException {
		try {
			/** 1.删除点赞表中的信息 */
			ActivityPraiseExample example = new ActivityPraiseExample();
			ActivityPraiseExample.Criteria criteria = example.createCriteria();
			
			criteria.andAdmirerCodeEqualTo(admirerCode);// 点赞人CODE
			criteria.andInfoCodeEqualTo(commentCode);// 被点赞信息CODE
			criteria.andInfoTypeEqualTo(Byte.parseByte(ActivityConstants.INFO_TYPE_2));// 被点赞信息类型
			
			int result = activityPraiseMapper.deleteByExample(example);
			/** 2.减少评论表中的点赞数 */
			if (result > 0) {
				super.updatePraiseNumForComment(commentCode, Operator.SUB, result);
			} else {
				// 活动评论信息不存在
				throw BusinessException.build("ACTIVITY_23002", "活动评论信息");
			}
			
			/** 3.查询最新的评论点赞数 */
			return activityCommentMapper.queryPraiseNum(commentCode);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivitiGeneralServiceImpl.deletePraiseOfComment", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@Override
	public int insertPraiseOfReply(String tenantCode, String orgCode, String admirerCode, String replyCode)
			throws BusinessException {
		try {
			Long time = DateUtils.getFormatDateLong();
			String code = UUIDHelper.getUUID();
			/** 1.添加回复点赞信息*/
			ActivityPraise praise = new ActivityPraise();
			praise.setCode(code);
			praise.setTenantCode(tenantCode);
			praise.setOrgCode(orgCode);
			praise.setCreateTime(time);
			praise.setModifyTime(time);
			praise.setAdmirerCode(admirerCode);
			praise.setInfoCode(replyCode);
			praise.setInfoType(Byte.parseByte(ActivityConstants.INFO_TYPE_3));
			
			int result = 0;
			try {
				result = activityPraiseMapper.insertSelective(praise);
			} catch (DuplicateKeyException e) {
				throw BusinessException.build("ACTIVITY_23016", "点赞");
			}
			
			/** 2.增加回复表中的点赞数 */
			if (result > 0) {
				super.updatePraiseNumForReply(replyCode, Operator.ADD, result);
			}
			
			/** 3.查询最新的回复点赞数 */
			return activityCommentReplyMapper.queryPraiseNum(replyCode);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivitiGeneralServiceImpl.insertPraiseOfReply", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@Override
	public int deletePraiseOfReply(String admirerCode, String replyCode) throws BusinessException {
		try {
			/** 1.删除点赞表中的信息 */
			ActivityPraiseExample example = new ActivityPraiseExample();
			ActivityPraiseExample.Criteria criteria = example.createCriteria();
			
			criteria.andAdmirerCodeEqualTo(admirerCode);// 点赞人CODE
			criteria.andInfoCodeEqualTo(replyCode);// 被点赞信息CODE
			criteria.andInfoTypeEqualTo(Byte.parseByte(ActivityConstants.INFO_TYPE_3));// 被点赞信息类型
			
			int result = activityPraiseMapper.deleteByExample(example);
			
			/** 2.减少回复表中的点赞数 */
			if (result > 0) {
				super.updatePraiseNumForReply(replyCode, Operator.SUB, result);
			} else {
				// 活动回复信息不存在
				throw BusinessException.build("ACTIVITY_23002", "活动回复信息");
			}
			
			/** 3.查询最新的回复点赞数 */
			return activityCommentReplyMapper.queryPraiseNum(replyCode);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivitiGeneralServiceImpl.deletePraiseOfReply", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@Override
	public void deleteCommentByCode(String tenantCode, String userCode, String commentCode) throws BusinessException {
		try {
			/** 1、校验userCode是否为此评论的发布人 */
			ActivityCommentExample example = new ActivityCommentExample();
			ActivityCommentExample.Criteria criteria = example.createCriteria();
			// 查询条件
			criteria.andTenantCodeEqualTo(tenantCode);
			criteria.andCodeEqualTo(commentCode);
			criteria.andIsDeleteEqualTo(VoteConstants.NO);
			criteria.andIsDisableEqualTo(VoteConstants.NO);
			
			List<ActivityComment> list = activityCommentMapper.selectByExample(example);
			if (list.size() > 0) {
				ActivityComment ac = list.get(0);
				if (!ac.getCommenterCode().equals(userCode)) {
					// 您不是此评论或回复的发布人，无权操作。
					throw BusinessException.build("COMMON_403");
				} else {
					/** 2、删除评论 */
					ActivityComment comment = new ActivityComment();
					comment.setModifyTime(DateUtils.getFormatDateLong());
					comment.setIsDelete(VoteConstants.YES);
					
					int delCount = activityCommentMapper.updateByExampleSelective(comment, example);
					if (delCount > 0) {
						/** 3、 减少活动主信息表中的评论数 */
						super.updateCommentNumForActivity(ac.getActivityCode(), Operator.SUB, delCount);
					}
				}
			} else {
				throw BusinessException.build("ACTIVITY_23002", "评论信息");
			}
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivitiGeneralServiceImpl.deleteCommentByCode", e);
			throw BusinessException.build("COMMON_400");
		}
		
	}

	@Override
	public void deleteReplyByCode(String tenantCode, String userCode, String replyCode) throws BusinessException {
		try {
			/** 1、校验userCode是否为此回复的发布人 */
			ActivityCommentReplyExample example = new ActivityCommentReplyExample();
			ActivityCommentReplyExample.Criteria criteria = example.createCriteria();
			// 查询条件
			criteria.andTenantCodeEqualTo(tenantCode);
			criteria.andCodeEqualTo(replyCode);
			criteria.andIsDeleteEqualTo(VoteConstants.NO);
			criteria.andIsDisableEqualTo(VoteConstants.NO);
			
			List<ActivityCommentReply> list = activityCommentReplyMapper.selectByExample(example);
			if (list.size() > 0) {
				ActivityCommentReply acr = list.get(0);
				if (!acr.getReplierCode().equals(userCode)) {
					// 您不是此评论或回复的发布人，无权操作。
					throw BusinessException.build("COMMON_403");
				} else {
					/** 2、删除回复 */
					ActivityCommentReply commentReply = new ActivityCommentReply();
					commentReply.setModifyTime(DateUtils.getFormatDateLong());
					commentReply.setIsDelete(VoteConstants.YES);
					int delCount = activityCommentReplyMapper.updateByExampleSelective(commentReply, example);
					if (delCount > 0) {
						/**3.减少评论表中的回复数 */
						super.updateReplyCountForComment(acr.getCommentCode(), Operator.SUB, delCount);
					}
				}
			} else {
				throw BusinessException.build("ACTIVITY_23002", "回复信息");
			}
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivitiGeneralServiceImpl.deleteReplyByCode", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@Override
	public void updateIncreaseShareNumByCode(String code, int count) throws BusinessException {
		try {
			super.updateShareNumForActivity(code, Operator.ADD, count);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivitiGeneralServiceImpl.updateIncreaseShareNumByCode", e);
			throw BusinessException.build("COMMON_400");
		}
		
	}

	@Override
	public ActivityComment getActivityComment(String code) throws BusinessException {
		try {
			ActivityCommentExample example = new ActivityCommentExample();
			ActivityCommentExample.Criteria criteria = example.createCriteria();
			// 查询条件
			criteria.andIsDeleteEqualTo(VoteConstants.NO);
			criteria.andIsDisableEqualTo(VoteConstants.NO);
			criteria.andCodeEqualTo(code);
			
			List<ActivityComment> list = activityCommentMapper.selectByExampleWithBLOBs(example);
			ActivityComment comment = new ActivityComment();
			if(list.size() > 0){
				comment = list.get(0);
			}
			return comment;
		} catch (Exception e) {
			log.error("ActivitiGeneralServiceImpl.getActivityComment", e);
			throw BusinessException.build("COMMON_400");
		}
		
	}

	@Override
	public ActivityCommentReply getActivityCommentReply(String code) throws BusinessException {
		try {
			ActivityCommentReplyExample example = new ActivityCommentReplyExample();
			ActivityCommentReplyExample.Criteria criteria = example.createCriteria();
			// 查询条件
			criteria.andIsDeleteEqualTo(VoteConstants.NO);
			criteria.andIsDisableEqualTo(VoteConstants.NO);
			criteria.andCodeEqualTo(code);
			
			List<ActivityCommentReply> replyList = activityCommentReplyMapper.selectByExampleWithBLOBs(example);
			ActivityCommentReply reply = new ActivityCommentReply();
			if(replyList.size() > 0){
				reply = replyList.get(0);
			}
			return reply;
		} catch (Exception e) {
			log.error("ActivitiGeneralServiceImpl.getActivityCommentReply", e);
			throw BusinessException.build("COMMON_400");
		}
		
	}

	@Override
	public List<ActivityInfoWithBLOBs> getActivityCollectionList(String userCode, String tenantCode, PageInfo page)
			throws BusinessException {
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("tenantCode", tenantCode);
			paramMap.put("userCode", userCode);
			paramMap.put("id", page.getId());
			paramMap.put("pageSize", page.getPerPageSize());
			
			List<ActivityInfoWithBLOBs> list = activityInfoMapper.queryActivityCollectionList(paramMap);
			return list;
		} catch (Exception e) {
			log.error("ActivitiGeneralServiceImpl.getActivityCollectionList", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@Override
	public List<ActivityInfo> getActivityCollectionListForH5(String userCode, String tenantCode, PageInfo page)
			throws BusinessException {
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("tenantCode", tenantCode);
			paramMap.put("userCode", userCode);
			paramMap.put("id", page.getId());
			paramMap.put("pageSize", page.getPerPageSize());
			
			List<ActivityInfo> list = activityInfoMapper.queryActivityCollectionListForH5(paramMap);
			return list;
		} catch (Exception e) {
			log.error("ActivitiGeneralServiceImpl.getActivityCollectionListForH5", e);
			throw BusinessException.build("COMMON_400");
		}
	}

}
