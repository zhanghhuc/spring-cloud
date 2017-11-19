package com.zssq.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.constants.VoteConstants;
import com.zssq.dao.mapper.VoteCommentMapper;
import com.zssq.dao.mapper.VoteCommentReplyMapper;
import com.zssq.dao.mapper.VoteInfoMapper;
import com.zssq.dao.mapper.VoteJoinAuthMapper;
import com.zssq.dao.mapper.VoteJoinMapper;
import com.zssq.dao.mapper.VoteOptionsMapper;
import com.zssq.dao.mapper.VoteReviewMapper;
import com.zssq.dao.mapper.VoteUserActionsMapper;
import com.zssq.dao.pojo.GetVoteInfoEntity;
import com.zssq.dao.pojo.VoteComment;
import com.zssq.dao.pojo.VoteCommentReply;
import com.zssq.dao.pojo.VoteDetailModel;
import com.zssq.dao.pojo.VoteInfo;
import com.zssq.dao.pojo.VoteInfoExample;
import com.zssq.dao.pojo.VoteJoin;
import com.zssq.dao.pojo.VoteJoinAuth;
import com.zssq.dao.pojo.VoteJoinAuthExample;
import com.zssq.dao.pojo.VoteJoinExample;
import com.zssq.dao.pojo.VoteOptions;
import com.zssq.dao.pojo.VoteOptionsExample;
import com.zssq.dao.pojo.VoteReview;
import com.zssq.dao.pojo.VoteUserActions;
import com.zssq.dao.pojo.VoteUserActionsExample;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.IVoteAuthService;
import com.zssq.service.IVoteManageService;
import com.zssq.service.IVoteService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.DateUtils;
import com.zssq.utils.PageInfo;
import com.zssq.utils.StringTools;


/**
 * @ClassName VoteManageServiceImpl
 * @Description 投票服务管理端 接口实现
 * @author LiuYunLong
 * @date 2017年4月5日 下午4:39:46
 * @version 1.0
 * @since JDK 1.7
 */
@Service("voteManageService")
public class VoteManageServiceImpl implements IVoteManageService{

	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private VoteInfoMapper voteInfoMapper;
	
	@Autowired
	private VoteCommentMapper voteCommentMapper;
	
	@Autowired
	private VoteCommentReplyMapper voteCommentReplyMapper;	
	
	@Autowired
	private VoteOptionsMapper voteOptionMapper;
	
	@Autowired
	private VoteUserActionsMapper voteUserActionsMapper;
	
	@Autowired
	private IVoteAuthService voteAuthService;
	
	@Autowired
	private IVoteService voteService;
	
	@Autowired
	private VoteJoinAuthMapper voteJoinAuthMapper;
	
	@Autowired
	private VoteJoinMapper voteJoinMapper;
	
	@Autowired
	private VoteReviewMapper voteReviewMapper;
	
	@Override
	public String insertVoteInfo(VoteInfo voteInfo, List<VoteOptions> options, VoteJoinAuth joinAuth)
			throws BusinessException {
		return voteService.insertVoteInfo(voteInfo, options, joinAuth);
	}

	@Override
	public PageInfo selectVoteList(VoteInfo info, PageInfo page) throws BusinessException {
		List<VoteInfo> list = new ArrayList<VoteInfo>();
		try {
			VoteInfoExample example = new VoteInfoExample();
			VoteInfoExample.Criteria criteria = example.createCriteria();
			
			criteria.andIsDeleteEqualTo(VoteConstants.NO);
			criteria.andIsDisableEqualTo(VoteConstants.NO);
			criteria.andTenantCodeEqualTo(info.getTenantCode());
			criteria.andOrgCodeEqualTo(info.getOrgCode());//相同的组织机构
			criteria.andSponsorTypeEqualTo(VoteConstants.SPONSOR_TYPE_MANAGER);//管理员发起
			//发起人code
			if(StringTools.isNotEmpty(info.getSponsorCode())){
				criteria.andSponsorCodeEqualTo(info.getSponsorCode());
			}
			//投票状态
			boolean flag = true;
			if (info.getStatusList() != null && info.getStatusList().size()>0) {
				criteria.andVoteStatusIn(info.getStatusList());
				Integer status = 2;
				if(info.getStatusList().size() == 1 && status.equals(info.getStatusList().get(0))){
					//获取审核中的投票按提交审核时间倒序
					example.setOrderByClause("modify_time desc");
					flag = false;
				}
			}
			//是否多选
			if(info.getIsMultiOption() != null){
				criteria.andIsMultiOptionEqualTo(info.getIsMultiOption());
			}
			//是否允许评论
			if(info.getIsEnableComment() != null){
				criteria.andIsEnableCommentEqualTo(info.getIsEnableComment());
			}
			//投票主题
			if(StringTools.isNotEmpty(info.getTitle())){
				criteria.andTitleLike("%"+info.getTitle()+"%");
			}
			
			int count = voteInfoMapper.countByExample(example);
			
			if(flag){
				example.setOrderByClause("id desc");
			}
			if (count > 0) {
				// 分页
				example.setLimitStart(page.getStartRow());
				example.setLimitEnd(page.getPerPageSize());
				list = voteInfoMapper.selectByExample(example);
			}
			page.setTotalItem(count);
			page.setList(list);
			return page;
		} catch (Exception e) {
			log.error("VoteManageServiceImpl.selectVoteList", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@Override
	public VoteInfo selectVoteInfo(VoteInfo info) throws BusinessException {
		try {
			//查询投票和权限信息
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("isDelete", VoteConstants.NO);
			map.put("isDisable", VoteConstants.NO);
			map.put("isHide", VoteConstants.NO);
			map.put("tenantCode", info.getTenantCode());
			map.put("code", info.getCode());
			List<VoteInfo> list = voteInfoMapper.selectVoteInfoAndAuth(map);
			if(list.size()<1){
				throw BusinessException.build("VOTE_18002", "投票信息");
			}
			VoteInfo vote = list.get(0);
			
			//查询投票选项信息
			VoteOptionsExample example = new VoteOptionsExample();
			VoteOptionsExample.Criteria criteria= example.createCriteria();
			
			criteria.andIsDeleteEqualTo(VoteConstants.NO);
			criteria.andIsDisableEqualTo(VoteConstants.NO);
			criteria.andTenantCodeEqualTo(info.getTenantCode());
			//投票信息code
			criteria.andVoteInfoCodeEqualTo(vote.getCode());
			
			List<VoteOptions> options = voteOptionMapper.selectByExample(example);
			
			vote.setOptions(options);
			
			return vote;
		} catch (BusinessException e) {
			log.error("VoteManageServiceImpl.selectVoteInfo", e);
			throw e;
		} catch (Exception e) {
			log.error("VoteManageServiceImpl.selectVoteInfo", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@Override
	public String insertVoteReview(VoteReview review) throws BusinessException {
		
		try {
			Long currentTime = DateUtils.getFormatDateLong();
			String reviewCode = UUIDHelper.getUUID();
			
			//1.修改投票状态
			VoteInfoExample example = new VoteInfoExample();
			VoteInfoExample.Criteria criteria = example.createCriteria();
			
			criteria.andIsDeleteEqualTo(VoteConstants.NO);
			criteria.andIsDisableEqualTo(VoteConstants.NO);
			criteria.andTenantCodeEqualTo(review.getTenantCode());
			//投票信息code
			criteria.andCodeEqualTo(review.getVoteInfoCode());
			
			List<VoteInfo> list = voteInfoMapper.selectByExample(example);
			if(list.size()	< 1){
				//投票信息不存在
				throw BusinessException.build("VOTE_18002", "投票信息");
			}
			VoteInfo voteInfo = list.get(0);
			if(!VoteConstants.STATUS_2.equals(voteInfo.getVoteStatus())){
				//投票不在审批中，不能进行审批操作
				throw BusinessException.build("VOTE_18010");
			}
			VoteInfo info = new VoteInfo();
			info.setModifyTime(currentTime);
			if(VoteConstants.REVIEW_PASS.equals(review.getReviewResult())){
				info.setVoteStatus(VoteConstants.STATUS_4);
			}
			if(VoteConstants.REVIEW_BACK.equals(review.getReviewResult())){
				info.setVoteStatus(VoteConstants.STATUS_3);
			}
			voteInfoMapper.updateByExampleSelective(info, example);
			
			//2.添加审核记录
			review.setCode(reviewCode);
			review.setCreateTime(currentTime);
			review.setModifyTime(currentTime);
			
			voteReviewMapper.insertSelective(review);
			
			return reviewCode;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("VoteManageServiceImpl.insertVoteReview", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	@Override
	public boolean updateVoteInfoStatus(VoteInfo voteInfo) throws BusinessException {
		try{
			VoteInfo vote = getVoteInfoByCode(voteInfo.getCode(), voteInfo.getTenantCode());
			if (vote != null) {
				if(vote.getSponsorCode().equals(voteInfo.getSponsorCode())){
					if (VoteConstants.STATUS_5.equals(vote.getVoteStatus())) {
						// 投票已经结束
						throw BusinessException.build("VOTE_18003");
					}
					VoteInfoExample example = new VoteInfoExample();
					VoteInfoExample.Criteria criteria = example.createCriteria();
					//封装查询条件
					criteria.andCodeEqualTo(voteInfo.getCode());
					criteria.andTenantCodeEqualTo(voteInfo.getTenantCode());
					criteria.andIsDeleteEqualTo(VoteConstants.NO);
					criteria.andIsDisableEqualTo(VoteConstants.NO);
					//发起人
					criteria.andSponsorCodeEqualTo(voteInfo.getSponsorCode()); 
					
					voteInfo.setVoteStatus(VoteConstants.STATUS_5);
					voteInfo.setModifyTime(DateUtils.getFormatDateLong());
					voteInfo.setEndTime(DateUtils.getFormatDateLong());
					return voteInfoMapper.updateByExampleSelective(voteInfo, example) >= 0 ? true :false;
				}else{
					// 您不是此投票的发起人，无权终止此投票。
					throw BusinessException.build("VOTE_18007");
				}
			}else{
				// 不存在该条投票记录
				throw BusinessException.build("VOTE_18002", "投票信息");
			}
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("VoteManageServiceImpl.updateVoteInfoStatus", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	
	@Override
	public PageInfo getVoteCommentFixedList(VoteComment voteComment) throws BusinessException {
		PageInfo pageInfo = new PageInfo();
		try{
			/** 1、查询投票 是否能被评论 */
			VoteInfo voteInfo = getVoteInfoByCode(voteComment.getVoteInfoCode(),voteComment.getTenantCode()); 
			if(null != voteInfo){
				Byte isEnableComment = voteInfo.getIsEnableComment();
				if(isEnableComment.equals(VoteConstants.NO)){
					// 该投票不允许评论
					throw BusinessException.build("VOTE_18006");
				}
				/** 2、查询总评论数 */
				pageInfo.setTotalItem(voteInfo.getCommentNum());
				/** 3、查询列表集合 */
				Map<String,Object> paramMap = new HashMap<>();
				paramMap.put("voteInfoCode", voteComment.getVoteInfoCode());
				paramMap.put("code", voteComment.getCode());
				paramMap.put("infoType", VoteConstants.INFO_TYPE_2);
				paramMap.put("tenantCode", voteComment.getTenantCode());
				paramMap.put("admirerCode", voteComment.getAdmirerCode());
				paramMap.put("id", voteComment.getId());
				paramMap.put("limitStart", 0);
				paramMap.put("limitEnd", Integer.valueOf(voteComment.getPageSize()));
				List<VoteComment> commentList = voteCommentMapper.getCommentListForLocation(paramMap);
				pageInfo.setList(commentList);
			}else{
				// 投票信息不存在
				throw BusinessException.build("VOTE_18002", "投票信息");
			}
			return pageInfo;
		} catch(BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("VoteManageServiceImpl.getVoteCommentFixedList", e);
			throw BusinessException.build("COMMON_400");
		}
	
	}

	@Override
	public PageInfo getVoteReplyFixedList(VoteCommentReply voteCommentReply) throws BusinessException {
		PageInfo pageInfo = new PageInfo();
		int surplusCount = 0;
		try{
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("voteInfoCode", voteCommentReply.getVoteInfoCode());
			paramMap.put("commentCode", voteCommentReply.getCommentCode());
			paramMap.put("code", voteCommentReply.getCode());
			paramMap.put("infoType", VoteConstants.INFO_TYPE_3);
			paramMap.put("tenantCode", voteCommentReply.getTenantCode());
			paramMap.put("admirerCode", voteCommentReply.getAdmirerCode());
			paramMap.put("id", voteCommentReply.getId());
			paramMap.put("limitStart", 0);
			paramMap.put("limitEnd", Integer.valueOf(voteCommentReply.getPageSize()));
			/** 1、查询评论回复列表 */
			List<VoteCommentReply> commentList = voteCommentReplyMapper.getCommentReplyListForLocation(paramMap);
			if(commentList.size() > 0){
				paramMap.put("id", commentList.get(commentList.size() - 1).getId());
				/** 2、查询评论回复 剩余记录数 */
				surplusCount = voteCommentReplyMapper.getCommentReplySurplusCountForLocation(paramMap);
			}
			
			pageInfo.setList(commentList);
			pageInfo.setTotalItem(surplusCount);
			return pageInfo;
		}catch(Exception e){
			log.error("VoteManageServiceImpl.getVoteReplyFixedList", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	

	@Override
	public String updateVoteInfo(VoteInfo voteInfo, List<VoteOptions> options, VoteJoinAuth joinAuth)
			throws BusinessException {
		try {
			VoteInfo vote = getVoteInfoByCode(voteInfo.getCode(), voteInfo.getTenantCode());
			if(null != vote){
				if(vote.getSponsorCode().equals(voteInfo.getSponsorCode())){
					VoteInfoExample example = new VoteInfoExample();
					VoteInfoExample.Criteria criteria = example.createCriteria();
					Long currentTime = DateUtils.getFormatDateLong();
					//投票时间不能小于当前时间
					if( voteInfo.getEndTime() < currentTime){
						throw BusinessException.build("VOTE_18009");
					}
					String infoCode = voteInfo.getCode();
					
					//过滤条件
					criteria.andCodeEqualTo(infoCode);
					criteria.andSponsorCodeEqualTo(voteInfo.getSponsorCode());
					criteria.andTenantCodeEqualTo(voteInfo.getTenantCode());
					criteria.andIsDeleteEqualTo(VoteConstants.NO);
					criteria.andIsDisableEqualTo(VoteConstants.NO);
					
					voteInfo.setModifyTime(currentTime);
					/** 1、更新投票主表信息 */
					int count = voteInfoMapper.updateByExampleSelective(voteInfo, example);
					
					if(count > 0){
						/** 2、删除投票选项信息 */
						Map<String,Object> paramMap = new HashMap<>();
						paramMap.put("voteInfoCode", infoCode);
						paramMap.put("tenantCode", voteInfo.getTenantCode());
						voteOptionMapper.deleteOptions(paramMap);
						
						/** 3、插入选项数据*/
						if(options.size()>0){
							for (VoteOptions o : options) {
								//设置投票选项信息
								o.setCode(UUIDHelper.getUUID());
								o.setCreateTime(currentTime);
								o.setModifyTime(currentTime);
								o.setVoteInfoCode(infoCode);
							}
							voteOptionMapper.batchInsertOptions(options);
						}
						
						/** 4、删除权限数据*/
						voteJoinAuthMapper.deleteAuthInfo(paramMap);
						joinAuth.setVoteInfoCode(voteInfo.getCode());
						/** 5、插入权限数据*/
						voteAuthService.insertAuthInfo(joinAuth);
						
						/** 6、如果是个人发起的投票则更新该信息*/
						if (VoteConstants.SPONSOR_TYPE_PERSON.equals(voteInfo.getSponsorType())) {
							VoteUserActionsExample actionExample = new VoteUserActionsExample();
							VoteUserActionsExample.Criteria actionCriteria = actionExample.createCriteria();
							
							actionCriteria.andVoteInfoCodeEqualTo(infoCode);
							actionCriteria.andTenantCodeEqualTo(voteInfo.getTenantCode());
							actionCriteria.andUserCodeEqualTo(voteInfo.getSponsorCode());
							actionCriteria.andIsDeleteEqualTo(VoteConstants.NO);
							actionCriteria.andIsDisableEqualTo(VoteConstants.NO);
							
							VoteUserActions action = new VoteUserActions();
							action.setModifyTime(currentTime);
							action.setTitle(voteInfo.getTitle());
							voteUserActionsMapper.updateByExampleSelective(action, actionExample);
						}
					}
					return infoCode;
				}else{
					// 您不是此投票的发起人，无权更新该投票。
					throw BusinessException.build("VOTE_18007");
				}
			}else{
				// 投票信息不存在
				throw BusinessException.build("VOTE_18002", "投票信息");
			}
		} catch(BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("VoteManageServiceImpl.updateVoteInfo", e);
			throw BusinessException.build("COMMON_400");
		}
		
	}
	

	@Override
	public VoteDetailModel selectVoteInfoDetail(GetVoteInfoEntity entity) throws BusinessException {
		VoteDetailModel model = new VoteDetailModel();
		try {
			/**1.查询投票主详情信息*/
			VoteInfo vote = voteInfoMapper.selectVoteDetail(entity.getTenantCode(), entity.getUserCode(), entity.getVoteInfoCode());
			if (vote == null) {
				return model;
			}
			model.setVoteInfo(vote);
			
			/**2.查询投票选项信息*/
			VoteOptionsExample optionsExample = new VoteOptionsExample();
			VoteOptionsExample.Criteria optionsCriteria = optionsExample.createCriteria();
			
			optionsCriteria.andIsDeleteEqualTo(VoteConstants.NO);
			optionsCriteria.andIsDisableEqualTo(VoteConstants.NO);
			optionsCriteria.andVoteInfoCodeEqualTo(entity.getVoteInfoCode());
			// 按照选项序号正序排列
			optionsExample.setOrderByClause("order_number asc");
			
			List<VoteOptions> optionsList = voteOptionMapper.selectByExample(optionsExample);
			model.setOptions(optionsList);
			
			/**3.统计投票选项总被选择人数*/
			model.setTotalSelectedNum(voteOptionMapper.countTotalSelectedNum(entity.getVoteInfoCode()));
			
			/**4.查询用户参与记录*/
			VoteJoinExample joinExample = new VoteJoinExample();
			VoteJoinExample.Criteria joinCriteria = joinExample.createCriteria();
			
			joinCriteria.andIsDeleteEqualTo(VoteConstants.NO);
			joinCriteria.andIsDisableEqualTo(VoteConstants.NO);
			joinCriteria.andJoinUserCodeEqualTo(entity.getUserCode());
			joinCriteria.andVoteInfoCodeEqualTo(entity.getVoteInfoCode());
			joinExample.setOrderByClause("id desc");
			
			List<VoteJoin> joinList = voteJoinMapper.selectByExample(joinExample);
			VoteJoin voteJoin = null;
			if (joinList.size() > 0) {
				voteJoin = joinList.get(0);
			}
			model.setVoteJoin(voteJoin);
			
			return model;
		} catch (Exception e) {
			log.error("VoteServiceImpl.selectVoteInfoDetail", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	/**
	 * @Function getVoteInfoByCode
	 * @Description 根据Code查询投票信息
	 * @param code
	 * @param tenantCode
	 * @return
	 * @throws BusinessException
	 */
	public VoteInfo getVoteInfoByCode(String code,String tenantCode)throws BusinessException{
	    VoteInfo voteInfo = null;
	    try{
	    	VoteInfoExample example = new VoteInfoExample();
			VoteInfoExample.Criteria criteria = example.createCriteria();
			criteria.andCodeEqualTo(code);
			criteria.andTenantCodeEqualTo(tenantCode);
			criteria.andIsDeleteEqualTo(VoteConstants.NO);
			criteria.andIsDisableEqualTo(VoteConstants.NO);
			criteria.andIsHideEqualTo(VoteConstants.NO);
			List<VoteInfo> list = voteInfoMapper.selectByExample(example); 
			if(list.size() > 0){
				voteInfo = list.get(0);
			}
	    	return voteInfo;
	    }catch(Exception e){
			log.error("VoteManageServiceImpl.getVoteInfoByCode", e);
			throw BusinessException.build("COMMON_400");
		}
	    
	}

	@Override
	public void updateSubmitOrNotExamine(String tenantCode, String orgCode, String voteInfoCode, String operateType)
			throws BusinessException {
		try {
			VoteInfoExample example = new VoteInfoExample();
			VoteInfoExample.Criteria criteria = example.createCriteria();
			
			criteria.andIsDeleteEqualTo(VoteConstants.NO);
			criteria.andIsDisableEqualTo(VoteConstants.NO);
			criteria.andIsHideEqualTo(VoteConstants.NO);
			criteria.andTenantCodeEqualTo(tenantCode);
			criteria.andOrgCodeEqualTo(orgCode);
			criteria.andCodeEqualTo(voteInfoCode);
			
			VoteInfo record = new VoteInfo();
			record.setModifyTime(DateUtils.getFormatDateLong());
			
			// 1-提交审核（状态由1-草稿  变更为  2-审批中 ）
			if (VoteConstants.SUBMIT_EXAMINE.equals(operateType)) {
				criteria.andVoteStatusEqualTo(VoteConstants.STATUS_1);
				record.setVoteStatus(VoteConstants.STATUS_2);
			}
			// 2-撤销审核 （状态由2-审批中  变更为   1-草稿）
			if (VoteConstants.REVOKE_EXAMINE.equals(operateType)) {
				criteria.andVoteStatusEqualTo(VoteConstants.STATUS_2);
				record.setVoteStatus(VoteConstants.STATUS_1);
			}
			
			voteInfoMapper.updateByExampleSelective(record, example);
		} catch (Exception e) {
			log.error("VoteManageServiceImpl.updateSubmitOrNotExamine", e);
			throw BusinessException.build("COMMON_400");
		}
		
	}

	@Override
	public VoteJoinAuth selectVoteJoinAuth(String voteInfoCode) throws BusinessException {
		VoteJoinAuth auth = null;
		try {
			VoteJoinAuthExample example = new VoteJoinAuthExample();
			VoteJoinAuthExample.Criteria criteria = example.createCriteria();
			
			criteria.andIsDeleteEqualTo(VoteConstants.NO);
			criteria.andIsDisableEqualTo(VoteConstants.NO);
			criteria.andVoteInfoCodeEqualTo(voteInfoCode);
			
			example.setLimitStart(0);
			example.setLimitEnd(1);
			example.setOrderByClause("id desc");
			
			List<VoteJoinAuth> list = voteJoinAuthMapper.selectByExample(example);
			if (list.size() > 0) {
				auth = list.get(0);
			}
		} catch (Exception e) {
			log.error("VoteManageServiceImpl.selectRangeName", e);
			throw BusinessException.build("COMMON_400");
		}
		return auth;
	}

	@Override
	public PageInfo selectVoteListForMonitor(String tenantCode, String orgCode, PageInfo page)
			throws BusinessException {
		try {
			VoteInfoExample example = new VoteInfoExample();
			VoteInfoExample.Criteria criteria = example.createCriteria();
			
			criteria.andIsDeleteEqualTo(VoteConstants.NO);
			criteria.andIsDisableEqualTo(VoteConstants.NO);
			criteria.andIsHideEqualTo(VoteConstants.NO);
			criteria.andTenantCodeEqualTo(tenantCode);
			criteria.andOrgCodeEqualTo(orgCode);
			
			List<Byte> values = new ArrayList<Byte>();
			values.add(VoteConstants.STATUS_4);
			values.add(VoteConstants.STATUS_5);
			criteria.andVoteStatusIn(values);//投票状态是进行中或已结束
			
			example.setOrderByClause("id desc");
			
			int count = voteInfoMapper.countByExample(example);
			List<VoteInfo> list = new ArrayList<VoteInfo>();
			if(count>0){
				// 分页
				example.setLimitStart(page.getStartRow());
				example.setLimitEnd(page.getPerPageSize());
				list = voteInfoMapper.selectByExample(example);
			}
			page.setTotalItem(count);
			page.setList(list);
			return page;
		} catch (Exception e) {
			log.error("VoteManageServiceImpl.selectVoteListForMonitor", e);
			throw BusinessException.build("COMMON_400");
		}
	}


}
