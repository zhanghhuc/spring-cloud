package com.zssq.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.constants.ActivityConstants;
import com.zssq.dao.mapper.ActivityCommentMapper;
import com.zssq.dao.mapper.ActivityCommentReplyMapper;
import com.zssq.dao.mapper.ActivityInfoMapper;
import com.zssq.dao.pojo.ActivityComment;
import com.zssq.dao.pojo.ActivityCommentExample;
import com.zssq.dao.pojo.ActivityCommentReply;
import com.zssq.dao.pojo.ActivityCommentReplyExample;
import com.zssq.dao.pojo.ActivityInfoExample;
import com.zssq.dao.pojo.ActivityInfoWithBLOBs;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.CommonService;
import com.zssq.service.IActivityThirdService;
import com.zssq.utils.DateUtils;
import com.zssq.utils.StringTools;
@Service("ativityThirdServiceImpl")
public class AtivityThirdServiceImpl extends CommonService implements IActivityThirdService {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private ActivityInfoMapper activityInfoMapper;
	@Autowired
	private ActivityCommentMapper activityCommentMapper;
	@Autowired
	private ActivityCommentReplyMapper activityCommentReplyMapper;
	
	@Override
	public boolean deleteOrNot(String tenantCode, String subjectCode, Byte subjectType, Byte isDelete)
			throws BusinessException {
		int effectCnt = 0;
		try {
			// 校验输入参数
			if (StringTools.isEmpty(tenantCode)) {
				throw BusinessException.build("COMMON_402", "tenantCode");
			}
			if (StringTools.isEmpty(subjectCode)) {
				throw BusinessException.build("COMMON_402", "subjectCode");
			}
			if (subjectType == null || (subjectType != Byte.parseByte(ActivityConstants.INFO_TYPE_1)
					&& subjectType != Byte.parseByte(ActivityConstants.INFO_TYPE_2)
					&& subjectType != Byte.parseByte(ActivityConstants.INFO_TYPE_3))) {
				throw BusinessException.build("COMMON_402", "subjectType");
			}
			if (isDelete == null || (isDelete != ActivityConstants.NO && isDelete != ActivityConstants.YES)) {
				throw BusinessException.build("COMMON_402", "isDelete");
			}
			
			// 判断调用接口的类型
			if (subjectType == Byte.parseByte(ActivityConstants.INFO_TYPE_1)) {
				// 恢复活动信息
				if (isDelete == ActivityConstants.NO) {
					effectCnt = deleteOrNotActivity(tenantCode, Operate.RECOVERY, subjectCode);
				}
				// 删除活动信息
				if (isDelete == ActivityConstants.YES) {
					effectCnt = deleteOrNotActivity(tenantCode, Operate.DELETE, subjectCode);
				}
			}
			if (subjectType == Byte.parseByte(ActivityConstants.INFO_TYPE_2)) {
				// 恢复活动的评论信息
				if (isDelete == ActivityConstants.NO) {
					effectCnt = deleteOrNotComment(tenantCode, Operate.RECOVERY, subjectCode);
				}
				// 删除活动的评论信息
				if (isDelete == ActivityConstants.YES) {
					effectCnt = deleteOrNotComment(tenantCode, Operate.DELETE, subjectCode);
				}
			}
			if (subjectType == Byte.parseByte(ActivityConstants.INFO_TYPE_3)) {
				// 恢复活动的评论回复信息
				if (isDelete == ActivityConstants.NO) {
					effectCnt = deleteOrNotReply(tenantCode, Operate.RECOVERY, subjectCode);
				}
				// 删除活动的评论回复信息
				if (isDelete == ActivityConstants.YES) {
					effectCnt = deleteOrNotReply(tenantCode, Operate.DELETE, subjectCode);
				}
			}
			if (effectCnt > 0) {
				return true;
			} else {
				return false;
			}
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("AtivityThirdServiceImpl.deleteOrNot", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@Override
	public int deleteOrNotActivity(String tenantCode, Operate opt, String activityCode) throws BusinessException {
		try {
			ActivityInfoExample example = new ActivityInfoExample();
			ActivityInfoExample.Criteria criteria = example.createCriteria();

			criteria.andTenantCodeEqualTo(tenantCode);
			criteria.andCodeEqualTo(activityCode);

			ActivityInfoWithBLOBs record = new ActivityInfoWithBLOBs();
			if (opt == Operate.DELETE) {
				criteria.andIsDeleteEqualTo(ActivityConstants.NO);
				record.setIsDelete(ActivityConstants.YES);
			} else if (opt == Operate.RECOVERY) {
				criteria.andIsDeleteEqualTo(ActivityConstants.YES);
				record.setIsDelete(ActivityConstants.NO);
			}
			record.setModifyTime(DateUtils.getFormatDateLong());

			return activityInfoMapper.updateByExampleSelective(record, example);
		} catch (Exception e) {
			log.error("AtivityThirdServiceImpl.deleteOrNotActivity", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@Override
	public int deleteOrNotComment(String tenantCode, Operate opt, String commentCode) throws BusinessException {
		try {
			/** 1、删除/恢复评论 */
			ActivityCommentExample example = new ActivityCommentExample();
			ActivityCommentExample.Criteria criteria = example.createCriteria();
			// 更新条件
			criteria.andTenantCodeEqualTo(tenantCode);
			criteria.andCodeEqualTo(commentCode);
			criteria.andIsDisableEqualTo(ActivityConstants.NO);
			
			ActivityComment comment = new ActivityComment();
			comment.setModifyTime(DateUtils.getFormatDateLong());
			if (opt == Operate.DELETE) {
				criteria.andIsDeleteEqualTo(ActivityConstants.NO);
				comment.setIsDelete(ActivityConstants.YES);
			} else if (opt == Operate.RECOVERY) {
				criteria.andIsDeleteEqualTo(ActivityConstants.YES);
				comment.setIsDelete(ActivityConstants.NO);
			}
			int effectCnt = activityCommentMapper.updateByExampleSelective(comment, example);
			
			if (effectCnt > 0) {
				/** 2、 更新活动主信息表中的评论数 */
				example = new ActivityCommentExample();
				criteria = example.createCriteria();
				criteria.andTenantCodeEqualTo(tenantCode);
				criteria.andCodeEqualTo(commentCode);
				
				List<ActivityComment> list = activityCommentMapper.selectByExample(example);
				if (list.size() > 0) {
					ActivityComment ac = list.get(0);
					if (opt == Operate.DELETE) {
						super.updateCommentNumForActivity(ac.getActivityCode(), Operator.SUB, effectCnt);
					}
					if (opt == Operate.RECOVERY) {
						super.updateCommentNumForActivity(ac.getActivityCode(), Operator.ADD,  effectCnt);
					}
				}
			}
			return effectCnt;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("AtivityThirdServiceImpl.deleteOrNotCommentByCode", e);
			throw BusinessException.build("COMMON_400");
		}

	}

	@Override
	public int deleteOrNotReply(String tenantCode, Operate opt, String replyCode) throws BusinessException {
		try {
			/** 1、删除/恢复 评论回复 */
			ActivityCommentReplyExample example = new ActivityCommentReplyExample();
			ActivityCommentReplyExample.Criteria criteria = example.createCriteria();
			// 更新条件
			criteria.andTenantCodeEqualTo(tenantCode);
			criteria.andCodeEqualTo(replyCode);
			criteria.andIsDisableEqualTo(ActivityConstants.NO);
			
			ActivityCommentReply commentReply = new ActivityCommentReply();
			commentReply.setModifyTime(DateUtils.getFormatDateLong());
			if (opt == Operate.DELETE) {
				criteria.andIsDeleteEqualTo(ActivityConstants.NO);
				commentReply.setIsDelete(ActivityConstants.YES);
			}
			if (opt == Operate.RECOVERY) {
				criteria.andIsDeleteEqualTo(ActivityConstants.YES);
				commentReply.setIsDelete(ActivityConstants.NO);
			}
			int effectCnt = activityCommentReplyMapper.updateByExampleSelective(commentReply, example);
			
			if (effectCnt > 0) {
				/**2.减少/增加评论表中的回复数*/
				example = new ActivityCommentReplyExample();
				criteria = example.createCriteria();
				criteria.andTenantCodeEqualTo(tenantCode);
				criteria.andCodeEqualTo(replyCode);
				List<ActivityCommentReply> list = activityCommentReplyMapper.selectByExample(example);
				if (list.size() > 0) {
					ActivityCommentReply acr = list.get(0);
					
					if (opt == Operate.DELETE) {
						super.updateReplyCountForComment(acr.getCommentCode(), Operator.SUB, effectCnt);
					}
					if (opt == Operate.RECOVERY) {
						super.updateReplyCountForComment(acr.getCommentCode(), Operator.ADD, effectCnt);
					}
				}
			}
			return effectCnt;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("AtivityThirdServiceImpl.deleteOrNotReplyByCode", e);
			throw BusinessException.build("COMMON_400");
		}

	}

	@Override
	public void updateDecreaseShareNumByCode(String code, int count) throws BusinessException {
		try {
			super.updateShareNumForActivity(code, Operator.SUB, count);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("AtivityThirdServiceImpl.updateDecreaseShareNumByCode", e);
			throw BusinessException.build("COMMON_400");
		}
	}

}
