package com.zssq.service.impl;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.constants.VoteConstants;
import com.zssq.dao.mapper.VoteJoinAuthMapper;
import com.zssq.dao.pojo.VoteJoinAuth;
import com.zssq.dao.pojo.VoteJoinAuthExample;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.IVoteAuthService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.DateUtils;
import com.zssq.utils.StringTools;
/**
 * 投票权限接口实现类
 * @ClassName VoteAuthServiceImpl
 * @Description 
 * @author liurong
 * @date 2017年3月31日 下午6:27:32
 * @version 1.0
 * @since JDK 1.7
 */
@Service("voteAuthService")
public class VoteAuthServiceImpl implements IVoteAuthService {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private VoteJoinAuthMapper voteJoinAuthMapper;

	@Override
	public void insertAuthInfo(VoteJoinAuth record) throws BusinessException {
		try {
			String code = UUIDHelper.getUUID();
			Long currentTime = DateUtils.getFormatDateLong();
			
			record.setCode(code);
			record.setCreateTime(currentTime);
			record.setModifyTime(currentTime);
			
			voteJoinAuthMapper.insertSelective(record);
		} catch (Exception e) {
			log.error("VoteAuthServiceImpl.insertAuthInfo", e);
			throw BusinessException.build("COMMON_400");
		}
		
	}

	@Override
	public boolean judgeVoteAuthority(String voteInfoCode, String userGCode, String userPCode, String userCCode,
			List<String> userTCodes) throws BusinessException {
		try {
			VoteJoinAuthExample example = new VoteJoinAuthExample();
			VoteJoinAuthExample.Criteria criteria = example.createCriteria();
			
			// 是否禁用:0-否
			criteria.andIsDisableEqualTo(VoteConstants.NO);
			// 是否删除:0-否
			criteria.andIsDeleteEqualTo(VoteConstants.NO);
			// 投票主表CODE
			criteria.andVoteInfoCodeEqualTo(voteInfoCode);
			// 是否范围级联:0-否  1-是
			example.setOrderByClause("p_range_code desc,is_cascade desc");
			
			List<VoteJoinAuth> authList = voteJoinAuthMapper.selectByExample(example);
			for (VoteJoinAuth auth : authList) {
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
