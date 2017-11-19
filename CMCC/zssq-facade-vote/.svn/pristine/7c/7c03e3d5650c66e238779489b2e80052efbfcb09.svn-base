package com.zssq.service;

import java.util.List;

import com.zssq.dao.pojo.VoteJoinAuth;
import com.zssq.exceptions.BusinessException;

/**
 * 投票权限接口服务
 * @ClassName IVoteAuthService
 * @Description 
 * @author liurong
 * @date 2017年3月31日 下午6:27:54
 * @version 1.0
 * @since JDK 1.7
 */
public interface IVoteAuthService {
	/**
	 * 新增投票权限信息
	 * @Function insertAuthInfo
	 * @Description 
	 * @param record
	 * @throws BusinessException
	 */
	public void insertAuthInfo(VoteJoinAuth record) throws BusinessException;
	/**
	 * 判断用户是否有参与投票的权限
	 * @Function judgeVoteAuthority
	 * @Description 
	 * @param voteInfoCode  投票主表信息CODE
	 * @param userGCode     参与用户所在集团CODE
	 * @param userPCode     参与用户所在省CODE，没有则传""
	 * @param userCCode     参与用户所在市CODE，没有则传""
	 * @param userTCode     参与用户所在班组CODE，没有则传空对象
	 * @return true：有权限  
	 * 		   false：无权限
	 * @throws BusinessException
	 */
	public boolean judgeVoteAuthority(String voteInfoCode, String userGCode, String userPCode, String userCCode,
			List<String> userTCodes) throws BusinessException;
	

}
