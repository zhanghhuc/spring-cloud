package com.zssq.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.zssq.constants.ForumConstants;
import com.zssq.dao.mapper.ForumCollectionMapper;
import com.zssq.dao.mapper.ForumCommentReplyMapper;
import com.zssq.dao.mapper.ForumPlatesMapper;
import com.zssq.dao.mapper.ForumPlatesMemberMapper;
import com.zssq.dao.mapper.ForumTopicsFollowMapper;
import com.zssq.dao.mapper.ForumTopicsMapper;
import com.zssq.dao.pojo.ForumCollectionExample;
import com.zssq.dao.pojo.ForumCommentReply;
import com.zssq.dao.pojo.ForumPlates;
import com.zssq.dao.pojo.ForumPlatesExample;
import com.zssq.dao.pojo.ForumPlatesMember;
import com.zssq.dao.pojo.ForumPlatesMemberExample;
import com.zssq.dao.pojo.ForumTopics;
import com.zssq.dao.pojo.ForumTopicsExample;
import com.zssq.dao.pojo.ForumTopicsExample.Criteria;
import com.zssq.dao.pojo.ForumTopicsFollow;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.CommonService;
import com.zssq.service.IForumManageService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.DateUtils;
import com.zssq.utils.PageInfo;
import com.zssq.utils.StringTools;
/**
 * 论坛管理接口实现
 * @ClassName ForumManageServiceImpl
 * @Description 
 * @author liurong
 * @date 2017年3月23日 上午11:14:54
 * @version 1.0
 * @since JDK 1.7
 */
@Service("forumManageService")
public class ForumManageServiceImpl extends CommonService implements IForumManageService {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private ForumPlatesMapper forumPlatesMapper;
	@Autowired
	private ForumPlatesMemberMapper forumPlatesMemberMapper;
	@Autowired
	private ForumCommentReplyMapper forumCommentReplyMapper;
	@Autowired
	private ForumTopicsFollowMapper forumTopicsFollowMapper;
	@Autowired
	private ForumTopicsMapper forumTopicsMapper;
	@Autowired
	private ForumCollectionMapper forumCollectionMapper;
	
	@Override
	public String insertForumForBelong(String tenantCode, String orgCode, String belongCode, String platesName) throws BusinessException {
		try {
			/**1.校验输入参数*/
			if (StringTools.isEmpty(tenantCode)) {
				throw BusinessException.build("COMMON_402", "tenantCode");
			}
			if (StringTools.isEmpty(orgCode)) {
				throw BusinessException.build("COMMON_402", "orgCode");
			}
			if (StringTools.isEmpty(belongCode)) {
				throw BusinessException.build("COMMON_402", "belongCode");
			}
			if (StringTools.isEmpty(platesName)) {
				throw BusinessException.build("COMMON_402", "platesName");
			}
			
			Long time = DateUtils.getFormatDateLong();
			String plateCode = UUIDHelper.getUUID();
			
			ForumPlates plate = new ForumPlates();
			plate.setForumPlatesCode(plateCode);
			plate.setTenantCode(tenantCode);
			plate.setOrgCode(orgCode);
			plate.setCreateTime(time);
			plate.setModifyTime(time);
			plate.setPlatesName(platesName);
			plate.setBelongCode(belongCode);
			
			forumPlatesMapper.insertSelective(plate);
			
			return plateCode;
		} catch (DuplicateKeyException e) {
			throw BusinessException.build("FORUM_16003");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ForumManageServiceImpl.createForumForBelong", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@Override
	public void deleteForumWithBelong(String tenantCode, String orgCode, String belongCode) throws BusinessException {
		try {
			/**1.校验输入参数*/
			if (StringTools.isEmpty(tenantCode)) {
				throw BusinessException.build("COMMON_402", "tenantCode");
			}
			if (StringTools.isEmpty(orgCode)) {
				throw BusinessException.build("COMMON_402", "orgCode");
			}
			if (StringTools.isEmpty(belongCode)) {
				throw BusinessException.build("COMMON_402", "belongCode");
			}
			
			/**2.查询论坛版块信息*/
			ForumPlates plate = super.queryPlateByBelongCode(belongCode, tenantCode);
			if (plate == null) {
				return;
			}
			
			/**3.逻辑删除论坛版块信息*/
			ForumPlatesExample example = new ForumPlatesExample();
			ForumPlatesExample.Criteria criteria = example.createCriteria();
			
			// 是否删除:0-否
			criteria.andIsDeleteEqualTo(ForumConstants.NO);
			// 租户标识
			criteria.andTenantCodeEqualTo(tenantCode);
			// 组织机构编码
			criteria.andOrgCodeEqualTo(orgCode);
			// 论坛版块CODE
			criteria.andForumPlatesCodeEqualTo(plate.getForumPlatesCode());
			// 板块所属CODE
			criteria.andBelongCodeEqualTo(belongCode);
			// 组织更新字段
			ForumPlates record = new ForumPlates();
			record.setIsDelete(ForumConstants.YES);
			record.setModifyTime(DateUtils.getFormatDateLong());
			
			forumPlatesMapper.updateByExampleSelective(record, example);
			
			/**4.逻辑删除论坛版块下所有用户信息*/
			updateDelMember(tenantCode, orgCode, plate.getForumPlatesCode(), null);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ForumManageServiceImpl.deleteForumWithBelong", e);
			throw BusinessException.build("COMMON_400");
		}
		
	}

	@Override
	public String addOneForumMember(String tenantCode, String orgCode, String belongCode, String memberCode,
			String memberName, Byte isModerator) throws BusinessException {
		try {
			/**1.校验输入参数*/
			if (StringTools.isEmpty(tenantCode)) {
				throw BusinessException.build("COMMON_402", "tenantCode");
			}
			if (StringTools.isEmpty(orgCode)) {
				throw BusinessException.build("COMMON_402", "orgCode");
			}
			if (StringTools.isEmpty(belongCode)) {
				throw BusinessException.build("COMMON_402", "belongCode");
			}
			if (StringTools.isEmpty(memberCode)) {
				throw BusinessException.build("COMMON_402", "memberCode");
			}
			if (StringTools.isEmpty(memberName)) {
				throw BusinessException.build("COMMON_402", "memberName");
			}
			if (isModerator == null || !(isModerator == ForumConstants.NO || isModerator == ForumConstants.YES)) {
				throw BusinessException.build("COMMON_402", "isModerator");
			}
			/**2.校验此belongCode是否已开通论坛版块*/
			ForumPlates plate = super.queryPlateByBelongCode(belongCode, tenantCode);
			if (plate == null) {
				throw BusinessException.build("FORUM_16000", "论坛信息");
			}
			
			/**3.为此论坛版块新增一位用户*/
			String platesMemberCode = UUIDHelper.getUUID();
			Long time = DateUtils.getFormatDateLong();
			ForumPlatesMember member = new ForumPlatesMember();
			member.setForumPlatesMemberCode(platesMemberCode);
			member.setTenantCode(tenantCode);
			member.setOrgCode(orgCode);
			member.setCreateTime(time);
			member.setModifyTime(time);
			member.setForumPlatesCode(plate.getForumPlatesCode());
			member.setMemberCode(memberCode);
			member.setMemberName(memberName);
			member.setIsModerator(isModerator);
			member.setIsReply(plate.getAllReply());
			member.setIsSend(plate.getAllSend());
			
			forumPlatesMemberMapper.insertSelective(member);
			
			return platesMemberCode;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ForumManageServiceImpl.addOneForumMember", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@Override
	public void deleteOneForumMember(String tenantCode, String orgCode, String belongCode, String memberCode) throws BusinessException {
		try {
			/**1.校验输入参数*/
			if (StringTools.isEmpty(tenantCode)) {
				throw BusinessException.build("COMMON_402", "tenantCode");
			}
			if (StringTools.isEmpty(orgCode)) {
				throw BusinessException.build("COMMON_402", "orgCode");
			}
			if (StringTools.isEmpty(belongCode)) {
				throw BusinessException.build("COMMON_402", "belongCode");
			}
			if (StringTools.isEmpty(memberCode)) {
				throw BusinessException.build("COMMON_402", "memberCode");
			}
			
			/**2.查询论坛版块信息*/
			ForumPlates plate = super.queryPlateByBelongCode(belongCode, tenantCode);
			if (plate == null) {
				return;
			}
			
			/**3.逻辑删除此论坛版块用户*/
			updateDelMember(tenantCode, orgCode, plate.getForumPlatesCode(), memberCode);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ForumManageServiceImpl.deleteOneForumMember", e);
			throw BusinessException.build("COMMON_400");
		}
		
	}
	
	@Override
	public void updateModerator(String tenantCode, String orgCode, String belongCode, String memberCode,
			Byte isModerator) throws BusinessException {
		try {
			/**1.校验输入参数*/
			if (StringTools.isEmpty(tenantCode)) {
				throw BusinessException.build("COMMON_402", "tenantCode");
			}
			if (StringTools.isEmpty(orgCode)) {
				throw BusinessException.build("COMMON_402", "orgCode");
			}
			if (StringTools.isEmpty(belongCode)) {
				throw BusinessException.build("COMMON_402", "belongCode");
			}
			if (StringTools.isEmpty(memberCode)) {
				throw BusinessException.build("COMMON_402", "memberCode");
			}
			if (isModerator == null || !(isModerator == ForumConstants.NO || isModerator == ForumConstants.YES)) {
				throw BusinessException.build("COMMON_402", "isModerator");
			}
			
			/**2.查询论坛版块信息*/
			ForumPlates plate = super.queryPlateByBelongCode(belongCode, tenantCode);
			if (plate == null) {
				return;
			}
			
			/**3.更新论坛版块用户信息*/
			ForumPlatesMemberExample example = new ForumPlatesMemberExample();
			ForumPlatesMemberExample.Criteria criteria = example.createCriteria();
			// 是否禁用:0-否
			criteria.andIsDisableEqualTo(ForumConstants.NO);
			// 是否删除:0-否
			criteria.andIsDeleteEqualTo(ForumConstants.NO);
			// 租户标识
			criteria.andTenantCodeEqualTo(tenantCode);
			// 组织机构编码
			criteria.andOrgCodeEqualTo(orgCode);
			// 论坛版块CODE
			criteria.andForumPlatesCodeEqualTo(plate.getForumPlatesCode());
			// 用户CODE
			criteria.andMemberCodeEqualTo(memberCode);
			// 组织更新字段
			ForumPlatesMember record = new ForumPlatesMember();
			record.setIsModerator(isModerator);
			record.setModifyTime(DateUtils.getFormatDateLong());
			
			forumPlatesMemberMapper.updateByExampleSelective(record, example);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ForumManageServiceImpl.updateModerator", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * 逻辑删除论坛用户
	 * @Function deleteMember
	 * @Description 
	 * @param tenantCode 租户标识
	 * @param orgCode    组织机构编码
	 * @param orgLevel   组织机构等级
	 * @param platesCode 论坛版块CODE
	 * @param memberCode 用户CODE
	 * @throws BusinessException
	 */
	private void updateDelMember(String tenantCode, String orgCode, String platesCode, String memberCode) throws BusinessException {
		try {
			ForumPlatesMemberExample memberExample = new ForumPlatesMemberExample();
			ForumPlatesMemberExample.Criteria memberCriteria = memberExample.createCriteria();
			// 是否删除:0-否
			memberCriteria.andIsDeleteEqualTo(ForumConstants.NO);
			// 租户标识
			memberCriteria.andTenantCodeEqualTo(tenantCode);
			// 组织机构编码
			memberCriteria.andOrgCodeEqualTo(orgCode);
			// 论坛版块CODE
			memberCriteria.andForumPlatesCodeEqualTo(platesCode);
			// 用户CODE条件
			if (StringTools.isNotEmpty(memberCode)) {
				memberCriteria.andMemberCodeEqualTo(memberCode);
			}
			// 组织更新字段
			ForumPlatesMember memberRecord = new ForumPlatesMember();
			memberRecord.setIsDelete(ForumConstants.YES);
			memberRecord.setModifyTime(DateUtils.getFormatDateLong());
			
			forumPlatesMemberMapper.updateByExampleSelective(memberRecord, memberExample);
		} catch (Exception e) {
			log.error("ForumManageServiceImpl.updateDelMember", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	@Override
	public PageInfo getComplaintFollowTopic(ForumTopicsFollow follow) throws BusinessException {
		try {
			//获得被举报跟帖所在行号
			Integer rownum = forumTopicsFollowMapper.getFollowTopicRownum(follow);
			if(rownum == null ){
				//跟贴不存在
				throw BusinessException.build("FORUM_16005");
			}
			int pageSize = follow.getPageSize();
			//获取被举报跟帖所在页数
			int pageNo = rownum%pageSize == 0?rownum/pageSize:rownum/pageSize+1;
			
			PageInfo info = new PageInfo(pageNo,pageSize);
			
			info = this.getTopicFollowList(follow, info);

			return info;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ForumServiceImpl.getComplaintFollowTopic", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@SuppressWarnings("unused")
	@Override
	public PageInfo getComplaintReply(ForumCommentReply reply) throws BusinessException {
		
		try {
			//获取举报评论回复所在行号
			Integer rownum = forumCommentReplyMapper.getComplaintReplyRownum(reply);
			if(rownum == null){
				//跟贴评论不存在
				throw BusinessException.build("FORUM_16000","跟贴评论");
			}
			int pageSize = reply.getPageSize();
			//计算被举报信息所在页号
			int pageNo = rownum%pageSize==0?rownum/pageSize:rownum/pageSize+1;
			//查询该页数据
			PageInfo pageInfo = new PageInfo(pageNo, pageSize);
			pageInfo = this.getTopicFollowReplyList(reply, pageInfo);
			return pageInfo;
		
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ForumServiceImpl.getComplaintReply", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	@Override
	public ForumTopics selectTopicInfo(String tenantCode, String topicCode, String... params) throws BusinessException {
		ForumTopics forumTopics = new ForumTopics();
		try {
			ForumTopicsExample example = new ForumTopicsExample();
			Criteria criteria = example.createCriteria();
			
			// 是否禁用:0-否
			criteria.andIsDisableEqualTo(ForumConstants.NO);
			// 是否删除:0-否
			criteria.andIsDeleteEqualTo(ForumConstants.NO);
			// 租户标识
			criteria.andTenantCodeEqualTo(tenantCode);
			// 主贴code
			criteria.andForumTopicsCodeEqualTo(topicCode);
			
			List<ForumTopics> list = forumTopicsMapper.selectByExampleWithBLOBs(example);
			
			if (list.size() > 0) {
				forumTopics = list.get(0);
				// 满足条件则：新增浏览记录，主帖浏览数+1
				if (params != null && params.length == 3) {
					saveVisitRecords(tenantCode, params[0], params[2], topicCode);
				}
			}
			return forumTopics;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ForumServiceImpl.selectTopicInfo", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	@Override
	public ForumPlatesMember queryUserForumAuthority(String tenantCode, String platesCode, String userCode) throws BusinessException {
		ForumPlatesMember authority = new ForumPlatesMember();
		try {
			ForumPlatesMemberExample example = new ForumPlatesMemberExample();
			ForumPlatesMemberExample.Criteria criteria = example.createCriteria();
			
			// 是否禁用:0-否
			criteria.andIsDisableEqualTo(ForumConstants.NO);
			// 是否删除:0-否
			criteria.andIsDeleteEqualTo(ForumConstants.NO);
			// 租户标识
			criteria.andTenantCodeEqualTo(tenantCode);
			// 成员code
			criteria.andMemberCodeEqualTo(userCode);
			// 论坛code
			criteria.andForumPlatesCodeEqualTo(platesCode);
			
			List<ForumPlatesMember> list = forumPlatesMemberMapper.selectByExample(example);
			
			if (list.size() > 0) {
				authority = list.get(0);
				if (authority.getIsModerator() == ForumConstants.YES) {
					authority.setCanbeDelete(ForumConstants.YES);
					authority.setCanbeBest(ForumConstants.YES);
					authority.setCanbeTop(ForumConstants.YES);
					authority.setIsReply(ForumConstants.YES);
					authority.setIsSend(ForumConstants.YES);
				} else {
					authority.setCanbeDelete(ForumConstants.NO);
					authority.setCanbeBest(ForumConstants.NO);
					authority.setCanbeTop(ForumConstants.NO);
				}
			} else {
				authority.setCanbeDelete(ForumConstants.NO);
				authority.setCanbeBest(ForumConstants.NO);
				authority.setCanbeTop(ForumConstants.NO);
				authority.setIsReply(ForumConstants.NO);
				authority.setIsSend(ForumConstants.NO);
			}
			return authority;
		} catch (Exception e) {
			log.error("ForumServiceImpl.queryUserForumAuthority", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	@Override
	public boolean isCollectionTopic(String tenantCode, String userCode, String forumTopicsCode) throws BusinessException {
		try {
			ForumCollectionExample example = new ForumCollectionExample();
			ForumCollectionExample.Criteria criteria = example.createCriteria();
			
			// 是否禁用:0-否
			criteria.andIsDisableEqualTo(ForumConstants.NO);
			// 是否删除:0-否
			criteria.andIsDeleteEqualTo(ForumConstants.NO);
			// 租户标识
			criteria.andTenantCodeEqualTo(tenantCode);
			//用户code
			criteria.andMemberCodeEqualTo(userCode);
			//主贴code
			criteria.andTopicCodeEqualTo(forumTopicsCode);
			
			int count = forumCollectionMapper.countByExample(example);
			if (count == 0) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			log.error("ForumServiceImpl.isCollectionTopic", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@Override
	public PageInfo selectForumListForMonitor(String tenantCode, String orgCode, PageInfo page)
			throws BusinessException {
		
		try {
			HashMap<Object, Object> paramMap = new HashMap<>();
			paramMap.put("tenantCode", tenantCode);
			paramMap.put("orgCode", orgCode);
			paramMap.put("limitStart", Integer.valueOf(page.getStartRow()));
			paramMap.put("limitEnd", Integer.valueOf(page.getPerPageSize()));
			
			int count = forumTopicsMapper.counForMoniter(paramMap);
			List<ForumTopics> list = new ArrayList<ForumTopics>();
			if(count>0){
				// 分页
				list = forumTopicsMapper.selectForumTopicsForMoniter(paramMap);
			}
			page.setTotalItem(count);
			page.setList(list);
			return page;
		} catch (Exception e) {
			log.error("ForumServiceImpl.selectForumListForMonitor", e);
			throw BusinessException.build("COMMON_400");
		}
	}
}
