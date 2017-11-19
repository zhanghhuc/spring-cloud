package com.zssq.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.dao.pojo.ActivityCollection;
import com.zssq.dao.pojo.ActivityComment;
import com.zssq.dao.pojo.ActivityCommentReply;
import com.zssq.dao.pojo.ActivityHistory;
import com.zssq.dao.pojo.ActivityInfo;
import com.zssq.dao.pojo.ActivityInfoWithBLOBs;
import com.zssq.dao.pojo.ActivityJoin;
import com.zssq.dao.pojo.ActivityJoinAdjunct;
import com.zssq.dao.pojo.ActivityJoinAuth;
import com.zssq.dao.pojo.ActivityPrize;
import com.zssq.dao.pojo.ActivityResource;
import com.zssq.dao.pojo.ActivityTemplate;
import com.zssq.dao.pojo.ActivityWinningRecord;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.CommonService;
import com.zssq.service.IActivityAuthService;
import com.zssq.service.IActivityGeneralService;
import com.zssq.service.IActivityManageService;
import com.zssq.service.IActivityModelOneService;
import com.zssq.service.IActivityModelThreeService;
import com.zssq.service.IActivityModelTwoService;
import com.zssq.utils.PageInfo;
@Service("activityManageService")
public class ActivityManageServiceImpl extends CommonService implements IActivityManageService {
	
	@Autowired
	private IActivityModelThreeService activityModelThreeService;
	@Autowired
	private IActivityModelOneService activityModelOneService;
	@Autowired
	private IActivityModelTwoService activityModelTwoService;
	@Autowired
	private IActivityGeneralService activityGeneralService;
	@Autowired
	private IActivityAuthService activityAuthService;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	
	@Override
	public void updateIsWinning(ActivityJoin activityJoin) throws BusinessException {
		try {
			activityModelThreeService.updateIsWinning(activityJoin);
		} catch (Exception e) {
			log.error("ActivityManageServiceImpl.updateIsWinning",e);
			throw e;
		}
		
	}


	@Override
	public List<ActivityPrize> getPrizeList(ActivityPrize activityPrize) throws BusinessException {
		List<ActivityPrize> list = new ArrayList<>();
		try {
			list = activityModelThreeService.getPrizeList(activityPrize);
		} catch (Exception e) {
			log.error("ActivityManageServiceImpl.getPrizeList",e);
			throw e;
		}
		return list;
	}


	@Override
	public void addPrizeForWorks(ActivityWinningRecord activityWinningRecord) throws BusinessException {
		try {
			activityModelThreeService.addPrizeForWorks(activityWinningRecord);
		} catch (Exception e) {
			log.error("ActivityManageServiceImpl.addPrizeForWorks",e);
			throw e;
		}
	}


	@Override
	public PageInfo getActivityCommentFixedList(ActivityComment activityComment) throws BusinessException {
		PageInfo pageInfo = new PageInfo();
		try {
			pageInfo = activityModelThreeService.getActivityCommentFixedList(activityComment);
		} catch (Exception e) {
			log.error("ActivityManageServiceImpl.getActivityCommentFixedList",e);
			throw e;
		}
		return pageInfo;
	}


	@Override
	public PageInfo getActivityeReplyFixedList(ActivityCommentReply activityCommentReply) throws BusinessException {
		PageInfo pageInfo = new PageInfo();
		try {
			pageInfo = activityModelThreeService.getActivityeReplyFixedList(activityCommentReply);
		} catch (Exception e) {
			log.error("ActivityManageServiceImpl.getActivityeReplyFixedList",e);
			throw e;
		}
		return pageInfo;
	}
	@Override
	public PageInfo selectTemplateList(ActivityTemplate template, PageInfo page) throws BusinessException {

		try {
			return activityModelOneService.selectTemplateList(template, page);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivityManageServiceImpl.selectTemplateList", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	@Override
	public void updateOnOffTemplate(ActivityTemplate template) throws BusinessException {
		try {
			activityModelOneService.updateOnOffTemplate(template);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivityManageServiceImpl.updateOnOffTemplate", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	@Override
	public String insertCreateActivityOne(ActivityInfoWithBLOBs activityInfo, ActivityJoinAuth joinAuth, List<ActivityPrize> prizeList,
			List<ActivityResource> resourceList) throws BusinessException {
		try {
			return activityModelOneService.insertCreateActivityOne(activityInfo, joinAuth, prizeList, resourceList);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivityManageServiceImpl.insertCreateActivityOne", e);
			throw BusinessException.build("COMMON_400");
		}
	}


	@Override
	public PageInfo selectActivityList(ActivityInfo info, PageInfo page,List<Byte> statusList) throws BusinessException {
		try {
			return activityModelOneService.selectActivityList(info, page,statusList);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivityManageServiceImpl.selectActivityList", e);
			throw BusinessException.build("COMMON_400");
		}
	}


	@Override
	public ActivityInfoWithBLOBs selectActivityDetail(ActivityInfo info) throws BusinessException {
		try {
			return activityModelOneService.selectActivityDetail(info);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivityManageServiceImpl.selectActivityDetail", e);
			throw BusinessException.build("COMMON_400");
		}
	}


	@Override
	public void updateActivityOne(ActivityInfoWithBLOBs activityInfo, ActivityJoinAuth joinAuth, List<ActivityPrize> prizeList,
			List<ActivityResource> resourceList) throws BusinessException {
		try {
			activityModelOneService.updateActivityOne(activityInfo, joinAuth, prizeList, resourceList);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivityManageServiceImpl.selectActivityDetail", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@Override
	public void deleteActivity(ActivityInfo info) throws BusinessException {
		try {
			activityModelOneService.deleteActivity(info);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivityManageServiceImpl.deleteActivity", e);
			throw BusinessException.build("COMMON_400");
		}
	}


	@Override
	public void updateSubmitRevoke(ActivityInfoWithBLOBs act, String winningType) throws BusinessException {
		activityModelTwoService.updateSubmitRevoke(act, winningType);
		
	}


	@Override
	public PageInfo getActivityHistoryList(ActivityHistory history, String pageNo, String pageSize)
			throws BusinessException {
		return activityModelTwoService.getActivityHistoryList(history, pageNo, pageSize);
	}


	@Override
	public ActivityInfoWithBLOBs updateExaminingActivity(ActivityInfoWithBLOBs act) throws BusinessException {
		return activityModelTwoService.updateExaminingActivity(act);
	}


	@Override
	public void updateAdvanceOfflineTime(ActivityInfoWithBLOBs act) throws BusinessException {
		activityModelTwoService.updateAdvanceOfflineTime(act);
		
	}


	@Override
	public PageInfo getActivityJoinList(ActivityJoin actJoin, String pageNo, String pageSize, String queryType)
			throws BusinessException {
		return activityModelTwoService.getActivityJoinList(actJoin, pageNo, pageSize, queryType);
	}


	@Override
	public ActivityJoin updateActivityJoin(ActivityJoin join) throws BusinessException {
		return activityModelTwoService.updateActivityJoin(join);
	}


	@Override
	public List<ActivityJoinAdjunct> getActivityJoinAdjunctList(ActivityJoinAdjunct adjunct) throws BusinessException {
		return activityModelTwoService.getActivityJoinAdjunctList(adjunct);
	}


	@Override
	public void updateActivityJoinScore(ActivityJoin join) throws BusinessException {
		activityModelTwoService.updateActivityJoinScore(join);
		
	}

	@Override
	public PageInfo getCommentList(ActivityComment comment) throws BusinessException {
		return activityGeneralService.getCommentList(comment);
	}

	@Override
	public PageInfo getCommentReplyList(ActivityCommentReply commentReply) throws BusinessException {
		return activityGeneralService.getCommentReplyList(commentReply);
	}

	@Override
	public ActivityComment addComment(ActivityComment comment) throws BusinessException {
		return activityGeneralService.addComment(comment);
	}
	
	@Override
	public ActivityInfo getActivityInfoByCode(String tenantCode, String code) throws BusinessException {
		return super.getActivityInfoByCode(code, tenantCode);
	}


	@Override
	public ActivityComment getCommentInfoByCode(String tenantCode, String code) throws BusinessException {
		return super.getCommentByCode(code, tenantCode);
	}


	@Override
	public ActivityCommentReply addReply(ActivityCommentReply commentReply) throws BusinessException {
		return activityGeneralService.addReply(commentReply);
	}
	
	@Override
	public List<ActivityInfo> getPortalActivityList(ActivityInfo activity, String pageSize) throws BusinessException {
		return activityModelTwoService.getPortalActivityList(activity, pageSize);
	}


	@Override
	public ActivityInfoWithBLOBs queryActivityDetail(String activityCode, String activityType,String userCode,String tenantCode)
			throws BusinessException {
		return activityModelTwoService.queryActivityDetail(activityCode, activityType,userCode,tenantCode);
	}


	@Override
	public int insertCollection(ActivityCollection collection) throws BusinessException {
		return activityGeneralService.insertCollection(collection);
	}


	@Override
	public int deleteCollection(ActivityCollection collection) throws BusinessException {
		return activityGeneralService.deleteCollection(collection);
	}


	@Override
	public int insertPraiseOfActivity(String tenantCode, String orgCode, String admirerCode, String activityCode)
			throws BusinessException {
		return activityGeneralService.insertPraiseOfActivity(tenantCode, orgCode, admirerCode, activityCode);
	}


	@Override
	public int deletePraiseOfActivity(String admirerCode, String activityCode) throws BusinessException {
		return activityGeneralService.deletePraiseOfActivity(admirerCode, activityCode);
	}


	@Override
	public int insertPraiseOfComment(String tenantCode, String orgCode, String admirerCode, String commentCode)
			throws BusinessException {
		return activityGeneralService.insertPraiseOfComment(tenantCode, orgCode, admirerCode, commentCode);
	}


	@Override
	public int deletePraiseOfComment(String admirerCode, String commentCode) throws BusinessException {
		return activityGeneralService.deletePraiseOfComment(admirerCode, commentCode);
	}


	@Override
	public int insertPraiseOfReply(String tenantCode, String orgCode, String admirerCode, String replyCode)
			throws BusinessException {
		return activityGeneralService.insertPraiseOfReply(tenantCode, orgCode, admirerCode, replyCode);
	}


	@Override
	public int deletePraiseOfReply(String admirerCode, String replyCode) throws BusinessException {
		return activityGeneralService.deletePraiseOfReply(admirerCode, replyCode);
	}


	@Override
	public void deleteCommentByCode(String tenantCode, String userCode, String commentCode) throws BusinessException {
		activityGeneralService.deleteCommentByCode(tenantCode, userCode, commentCode);
	}


	@Override
	public void deleteReplyByCode(String tenantCode, String userCode, String replyCode) throws BusinessException {
		activityGeneralService.deleteReplyByCode(tenantCode, userCode, replyCode);
	}
	
	@Override
	public List<ActivityInfo> getActivityList(ActivityInfo activity, PageInfo page) throws BusinessException {
		return activityModelTwoService.getActivityList(activity, page);
	}


	@Override
	public void insertJoinActivity(ActivityJoinAdjunct adjunct, ActivityJoin join)
			throws BusinessException {
		activityModelTwoService.insertJoinActivity(adjunct, join);
		
	}


	@Override
	public List<ActivityWinningRecord> getAwardeeList(String activityCode) throws BusinessException {
		return activityModelTwoService.getAwardeeList(activityCode);
	}


	@Override
	public void updateIncreaseShareNumByCode(String code, int count) throws BusinessException {
		activityGeneralService.updateIncreaseShareNumByCode(code, count);
	}



	@Override
	public void insertAuthInfo(ActivityJoinAuth record) throws BusinessException {
		activityAuthService.insertAuthInfo(record);
	}


	@Override
	public void updateAuthInfo(ActivityJoinAuth record) throws BusinessException {
		activityAuthService.updateAuthInfo(record);
	}


	@Override
	public boolean judgeAuthority(String activityCode, String userGCode, String userPCode, String userCCode,
			List<String> userTCodes) throws BusinessException {
		return activityAuthService.judgeAuthority(activityCode, userGCode, userPCode, userCCode, userTCodes);
	}


	@Override
	public ActivityInfo selectActivityByCode(ActivityInfo info) throws BusinessException {
		return activityModelOneService.selectActivityByCode(info);
	}

	@Override
	public void updateAwardStatusWithBatch(String tenantCode, String activityCode, List<String> joinCodes)
			throws BusinessException {
		activityModelThreeService.updateAwardStatusWithBatch(tenantCode, activityCode, joinCodes);
	}


	@Override
	public List<ActivityWinningRecord> getWinningRecordList(String tenantCode, String activityCode,
			List<String> joinCodes) throws BusinessException {
		return activityModelThreeService.getWinningRecordList(tenantCode, activityCode, joinCodes);
	}

	@Override
	public ActivityPrize getJoinPrize(ActivityPrize activityPrize) throws BusinessException {
		return activityModelTwoService.getJoinPrize(activityPrize);
	}


	@Override
	public int insertWinningRecord(ActivityWinningRecord winning) throws BusinessException {
		return activityModelTwoService.insertWinningRecord(winning);
	}


	@Override
	public int updateWinningRecord(ActivityWinningRecord winning) throws BusinessException {
		return activityModelTwoService.updateWinningRecord(winning);
	}


	@Override
	public PageInfo getActivityListForMonitor(String orgCode, String tenantCode, String pageNo, String pageSize)
			throws BusinessException {
		return activityModelTwoService.getActivityListForMonitor(orgCode, tenantCode, pageNo, pageSize);
	}


	@Override
	public ActivityComment getActivityComment(String code) throws BusinessException {
		return activityGeneralService.getActivityComment(code);
	}


	@Override
	public ActivityCommentReply getActivityCommentReply(String code) throws BusinessException {
		return activityGeneralService.getActivityCommentReply(code);
	}


	@Override
	public List<ActivityInfoWithBLOBs> getActivityCollectionList(String userCode, String tenantCode, PageInfo page)
			throws BusinessException {
		return activityGeneralService.getActivityCollectionList(userCode, tenantCode, page);
	}


	@Override
	public List<ActivityInfo> getActivityCollectionListForH5(String userCode, String tenantCode, PageInfo page)
			throws BusinessException {
		return activityGeneralService.getActivityCollectionListForH5(userCode, tenantCode, page);
	}

}
