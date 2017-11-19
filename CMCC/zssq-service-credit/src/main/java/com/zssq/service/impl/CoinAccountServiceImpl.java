package com.zssq.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.dao.mapper.CoinAccountMapper;
import com.zssq.dao.pojo.CoinAccount;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.ICoinAccountService;

@Service("coinAccountService")
public class CoinAccountServiceImpl implements ICoinAccountService {
	
	/** 打印日志 */
	private static Logger logger = LoggerFactory.getLogger(CoinAccountServiceImpl.class);

	/** 金币账户映射器 */
	@Autowired
	CoinAccountMapper coinAccountMapper;

	@Override
	public CoinAccount selectByAccountCode(String accountCode) throws BusinessException {
		try {
			return coinAccountMapper.selectByAccountCode(accountCode);
		} catch (Exception e) {
			logger.error("查询金币账户失败", e);
			throw new BusinessException("查询金币账户失败", e);
		}
	}

	@Override
	public boolean insert(CoinAccount coinAccount) throws BusinessException {
		try {
			boolean result = false;
			CoinAccount fetch = coinAccountMapper.selectByAccountCode(coinAccount.getAccountCode());
			if (fetch == null) {
				result = coinAccountMapper.insert(coinAccount) == 1;
			}
			return result;
		} catch (Exception e) {
			logger.error("添加金币账户失败", e);
			throw new BusinessException("添加金币账户失败", e);
		}
	}

	@Override
	public boolean updateByCode(CoinAccount coinAccount) throws BusinessException {
		try {
			boolean result = false;
			CoinAccount fetch = coinAccountMapper.selectByAccountCode(coinAccount.getAccountCode());
			if (fetch != null) {
				result = coinAccountMapper.updateByAccountCode(coinAccount) == 1;
			}
			return result;
		} catch (Exception e) {
			logger.error("修改金币账户失败", e);
			throw new BusinessException("修改金币账户失败", e);
		}
	}
	
}
