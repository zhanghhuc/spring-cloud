package com.zssq.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.constants.VoteConstants;
import com.zssq.dao.mapper.ActivityJoinAuthMapper;
import com.zssq.dao.pojo.ActivityJoinAuth;
import com.zssq.dao.pojo.ActivityJoinAuthExample;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.IActivityAuthService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.DateUtils;
import com.zssq.utils.StringTools;
@Service("activityAuthService")
public class ActivityAuthServiceImpl implements IActivityAuthService {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private ActivityJoinAuthMapper activityJoinAuthMapper;
	
	@Override
	public void insertAuthInfo(ActivityJoinAuth record) throws BusinessException {
		try {
			String code = UUIDHelper.getUUID();
			Long currentTime = DateUtils.getFormatDateLong();
			
			record.setCode(code);
			record.setCreateTime(currentTime);
			record.setModifyTime(currentTime);
			
			activityJoinAuthMapper.insertSelective(record);
		} catch (Exception e) {
			log.error("ActivityAuthServiceImpl.insertAuthInfo", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	@Override
	public void updateAuthInfo(ActivityJoinAuth record) throws BusinessException {
		try {
			/**1.先delete该活动的权限信息*/
			ActivityJoinAuthExample example = new ActivityJoinAuthExample();
			ActivityJoinAuthExample.Criteria criteria = example.createCriteria();
			
			criteria.andActivityCodeEqualTo(record.getActivityCode());
			
			activityJoinAuthMapper.deleteByExample(example);
			
			/**2.insert新数据*/
			insertAuthInfo(record);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("ActivityAuthServiceImpl.updateAuthInfo", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@Override
	public boolean judgeAuthority(String activityCode, String userGCode, String userPCode, String userCCode,
			List<String> userTCodes) throws BusinessException {
		try {
			ActivityJoinAuthExample example = new ActivityJoinAuthExample();
			ActivityJoinAuthExample.Criteria criteria = example.createCriteria();
			
			// 是否禁用:0-否
			criteria.andIsDisableEqualTo(VoteConstants.NO);
			// 是否删除:0-否
			criteria.andIsDeleteEqualTo(VoteConstants.NO);
			// 活动主表CODE
			criteria.andActivityCodeEqualTo(activityCode);
			// 是否范围级联:0-否  1-是
			example.setOrderByClause("p_range_code desc,is_cascade desc");
			
			List<ActivityJoinAuth> authList = activityJoinAuthMapper.selectByExample(example);
			for (ActivityJoinAuth auth : authList) {
				byte rangeType = auth.getRangeType();
				
				if (rangeType == VoteConstants.RANGE_TYPE_ORG || rangeType == VoteConstants.RANGE_TYPE_PRO
						|| rangeType == VoteConstants.RANGE_TYPE_CITY) {
					// 如果用户所属的集团CODE与能参与的集团CODE都不相等，则肯定无权限
					if (!auth.getgRangeCode().equals(userGCode)) {
						return false;
					}
				}
				
				// 参与范围：1-集团
				if (rangeType == VoteConstants.RANGE_TYPE_ORG) {
					// 是否级联范围：0-否 1-是
					if (auth.getIsCascade() == VoteConstants.NO) {
						// 不级联范围，则需判断此用户属于集团用户
						if (StringTools.isEmpty(userPCode) && StringTools.isEmpty(userCCode)) {
							// 此用户为集团用户
							return true;
						} else {
							// 此用户至少属于集团下某个省/直辖市的用户
							return false;
						}
					} else {
						return true;
					}
				// 参与范围：2-省 
				} else if (rangeType == VoteConstants.RANGE_TYPE_PRO) {
					if (userPCode.equals(auth.getpRangeCode())) {
						// 是否级联范围：0-否 1-是
						if (auth.getIsCascade() == VoteConstants.NO) {
							// 不级联范围，则需判断此用户属于省用户
							if (StringTools.isEmpty(userCCode)) {
								return true;
							} else {
								// 此用户属于集团下某个省/直辖市的某个市的用户
								return false;
							}
						} else {
							return true;
						}
					} else {
						// 不属于这个省的用户
						return false;
					}
				// 参与范围： 3-市 
				} else if (rangeType == VoteConstants.RANGE_TYPE_CITY) {
					if (!userPCode.equals(auth.getpRangeCode())) {
						return false;
					}
					if (userCCode.equals(auth.getcRangeCode())) {
						return true;
					} else {
						return false;
					}
				// 参与范围： 4-班组 
				} else if (rangeType == VoteConstants.RANGE_TYPE_CLASS) {
					if (userTCodes.contains(auth.gettRangeCode())) {
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			log.error("VoteAuthServiceImpl.judgeAuthority", e);
			throw BusinessException.build("COMMON_400");
		}
		return false;
	}

}
