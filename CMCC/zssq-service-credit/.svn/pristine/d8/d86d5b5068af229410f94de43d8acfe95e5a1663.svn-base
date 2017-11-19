package com.zssq.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.dao.mapper.ExpAccountMapper;
import com.zssq.dao.pojo.ExpAccount;
import com.zssq.dao.pojo.UserLevelConfig;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.IExpAccountService;

@Service("expAccountService")
public class ExpAccountServiceImpl implements IExpAccountService {
	
	/** 打印日志 */
	private static Logger logger = LoggerFactory.getLogger(ExpAccountServiceImpl.class);

	/** 经验值账户映射器 */
	@Autowired
	ExpAccountMapper expAccountMapper;

	@Override
	public ExpAccount selectByAccountCode(String accountCode) throws BusinessException {
		try {
			ExpAccount expAccount = expAccountMapper.selectByAccountCode(accountCode);
			if (expAccount == null || expAccount.getCurrentExp() == null || expAccount.getCurrentLevel() == null) {
				expAccount = new ExpAccount();
				expAccount.setCurrentExp(0);
				expAccount.setCurrentLevel(0);
			}
			return expAccount;
		} catch (Exception e) {
			logger.error("查询经验值账户失败", e);
			throw new BusinessException("查询经验值账户失败", e);
		}
	}

	@Override
	public boolean insert(ExpAccount expAccount) throws BusinessException {
		try {
			boolean result = false;
			ExpAccount fetch = expAccountMapper.selectByAccountCode(expAccount.getAccountCode());
			if (fetch == null) {
				result = expAccountMapper.insert(expAccount) == 1;
			}
			return result;
		} catch (Exception e) {
			logger.error("添加经验值账户失败", e);
			throw new BusinessException("添加经验值账户失败", e);
		}
	}

	@Override
	public boolean updateByCode(ExpAccount expAccount) throws BusinessException {
		try {
			boolean result = false;
			ExpAccount fetch = expAccountMapper.selectByAccountCode(expAccount.getAccountCode());
			if (fetch != null) {
				result = expAccountMapper.updateByAccountCode(expAccount) == 1;
			}
			return result;
		} catch (Exception e) {
			logger.error("修改经验值账户失败", e);
			throw new BusinessException("修改经验值账户失败", e);
		}
	}

	@Override
	public UserLevelConfig selectMultiByCode(String accountCode) throws BusinessException {
		try {
			return expAccountMapper.selectMultiByCode(accountCode);
		} catch (Exception e) {
			logger.error("获取用户等级、头衔失败", e);
			throw new BusinessException("获取用户等级、头衔失败", e);
		}
	}

}
