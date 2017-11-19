package com.zssq.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.constants.ForumConstants;
import com.zssq.dao.mapper.ForumPlatesMapper;
import com.zssq.dao.mapper.ForumPlatesMemberMapper;
import com.zssq.dao.pojo.ForumPlates;
import com.zssq.dao.pojo.ForumPlatesExample;
import com.zssq.dao.pojo.ForumPlatesMember;
import com.zssq.dao.pojo.ForumPlatesMemberExample;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.IForumAuthService;
import com.zssq.utils.DateUtils;
import com.zssq.utils.StringTools;
/**
 * 论坛权限接口实现类
 * @ClassName ForumAuthServiceImpl
 * @Description 
 * @author liurong
 * @date 2017年4月7日 上午9:46:06
 * @version 1.0
 * @since JDK 1.7
 */
@Service("forumAuthService")
public class ForumAuthServiceImpl implements IForumAuthService {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private ForumPlatesMemberMapper forumPlatesMemberMapper;
	@Autowired
	private ForumPlatesMapper forumPlatesMapper;

	@Override
	public boolean queryUserIsTeamLeaderByPlatesCode(String tenantCode, String platesCode, String userCode) throws BusinessException {
		boolean isTeamLeader = false;
		try {
			ForumPlatesMember member = forumPlatesMemberMapper.queryOneMemberWithPlatesCode(tenantCode, platesCode, userCode);
			if (member != null) {
				if (member.getIsModerator() == ForumConstants.YES) {
					isTeamLeader = true;
				}
			}
		} catch (Exception e) {
			log.error("ForumAuthServiceImpl.queryUserIsTeamLeaderByPlatesCode", e);
			throw BusinessException.build("COMMON_400");
		}
		return isTeamLeader;
	}
	
	@Override
	public boolean queryUserIsTeamLeaderByBelongCode(String tenantCode, String belongCode, String userCode) throws BusinessException {
		boolean isTeamLeader = false;
		try {
			ForumPlatesMember member = forumPlatesMemberMapper.queryOneMemberWithBelongCode(tenantCode, belongCode, userCode);
			if (member != null) {
				if (member.getIsModerator() == ForumConstants.YES) {
					isTeamLeader = true;
				}
			}
		} catch (Exception e) {
			log.error("ForumAuthServiceImpl.queryUserIsTeamLeaderByBelongCode", e);
			throw BusinessException.build("COMMON_400");
		}
		return isTeamLeader;
	}

	@Override
	public boolean queryUserCanbeSendTopic(String tenantCode, String belongCode, String userCode)
			throws BusinessException {
		boolean canbeSend = false;
		try {
			ForumPlatesMember record = forumPlatesMemberMapper.queryOneMemberWithBelongCode(tenantCode, belongCode, userCode);
			// 如果查询不出所属论坛的用户信息，则无发帖权限
			if (record == null) {
				return canbeSend;
			}
			// 如果是版主，则具有发帖权限
			if (record.getIsModerator() == ForumConstants.YES) {
				canbeSend = true;
			} else {
				// 是否能发帖(0-否  1-是)
				if(record.getIsSend() == ForumConstants.YES){
					canbeSend = true;
				} else {
					canbeSend = false;
				}
			}
		} catch (Exception e) {
			log.error("ForumAuthServiceImpl.queryUserCanbeSendTopic", e);
			throw BusinessException.build("COMMON_400");
		}
		return canbeSend;
	}

	@Override
	public ForumPlatesMember queryUserForumAuthority(String tenantCode, String platesCode, String userCode)
			throws BusinessException {
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
					authority.setCanbeShield(ForumConstants.YES);
					authority.setIsReply(ForumConstants.YES);
					authority.setIsSend(ForumConstants.YES);
				} else {
					authority.setCanbeDelete(ForumConstants.NO);
					authority.setCanbeBest(ForumConstants.NO);
					authority.setCanbeTop(ForumConstants.NO);
					authority.setCanbeShield(ForumConstants.NO);
				}
			} else {
				authority.setCanbeDelete(ForumConstants.NO);
				authority.setCanbeBest(ForumConstants.NO);
				authority.setCanbeTop(ForumConstants.NO);
				authority.setCanbeShield(ForumConstants.NO);
				authority.setIsReply(ForumConstants.NO);
				authority.setIsSend(ForumConstants.NO);
			}
			return authority;
		} catch (Exception e) {
			log.error("ForumAuthServiceImpl.queryUserForumAuthority", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@Override
	public void updateForumPlateEmpowerment(ForumPlates forumPlates) throws BusinessException {
		try {
			ForumPlatesExample example = new ForumPlatesExample();
			ForumPlatesExample.Criteria criteria = example.createCriteria();
			
			// 是否禁用:0-否
			criteria.andIsDisableEqualTo(ForumConstants.NO);
			// 是否删除:0-否
			criteria.andIsDeleteEqualTo(ForumConstants.NO);
			// 租户标识
			criteria.andTenantCodeEqualTo(forumPlates.getTenantCode());
			// CODE值
			criteria.andForumPlatesCodeEqualTo(forumPlates.getForumPlatesCode());
			// 更新时间
			forumPlates.setModifyTime(DateUtils.getFormatDateLong());
			/** 1、更新论坛权限 */
			if(forumPlatesMapper.updateByExampleSelective(forumPlates, example) > 0){
				ForumPlatesMemberExample memberExample = new ForumPlatesMemberExample();
				ForumPlatesMemberExample.Criteria memberCriteria = memberExample.createCriteria();
				
				// 是否禁用:0-否
				memberCriteria.andIsDisableEqualTo(ForumConstants.NO);
				// 是否删除:0-否
				memberCriteria.andIsDeleteEqualTo(ForumConstants.NO);
				// 租户标识
				memberCriteria.andTenantCodeEqualTo(forumPlates.getTenantCode());
				// 论坛版块CODE
				memberCriteria.andForumPlatesCodeEqualTo(forumPlates.getForumPlatesCode());
				// 条件为不是版主
				memberCriteria.andIsModeratorEqualTo(ForumConstants.NO);
				
				ForumPlatesMember forumPlatesMember = new ForumPlatesMember();
				forumPlatesMember.setIsReply(forumPlates.getAllReply());
				forumPlatesMember.setIsSend(forumPlates.getAllSend());
				forumPlatesMember.setModifyTime(DateUtils.getFormatDateLong());
				
				/** 2、更新论坛成员权限 */
				forumPlatesMemberMapper.updateByExampleSelective(forumPlatesMember, memberExample);
			}
			
		} catch (Exception e) {
			log.error("ForumAuthServiceImpl.updateForumPlateEmpowerment", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@Override
	public ForumPlates selectForumPlate(ForumPlates forumPlates) throws BusinessException {
		ForumPlates fp = new ForumPlates();
		try {
			ForumPlatesExample example = new ForumPlatesExample();
			ForumPlatesExample.Criteria criteria = example.createCriteria();
			
			// 是否禁用:0-否
			criteria.andIsDisableEqualTo(ForumConstants.NO);
			// 是否删除:0-否
			criteria.andIsDeleteEqualTo(ForumConstants.NO);
			// 租户标识
			criteria.andTenantCodeEqualTo(forumPlates.getTenantCode());
			if (StringTools.isNotEmpty(forumPlates.getForumPlatesCode())) {
				// 板块CODE查询条件
				criteria.andBelongCodeEqualTo(forumPlates.getForumPlatesCode());
			}
			if (StringTools.isNotEmpty(forumPlates.getBelongCode())) {
				// 板块所属CODE查询条件
				criteria.andBelongCodeEqualTo(forumPlates.getBelongCode());
			}
			
			//查询结果集
			List<ForumPlates> list = forumPlatesMapper.selectByExample(example);
			if(list.size() > 0){
				fp = list.get(0); 
			}
			return fp;
		} catch (Exception e) {
			log.error("ForumAuthServiceImpl.selectForumPlateEmpowerment", e);
			throw BusinessException.build("COMMON_400");
		}
	}

}
