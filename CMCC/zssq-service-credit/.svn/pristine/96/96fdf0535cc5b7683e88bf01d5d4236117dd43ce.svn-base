package com.zssq.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.dao.mapper.CoinAccountDetailMapper;
import com.zssq.dao.mapper.CoinAccountMapper;
import com.zssq.dao.pojo.CoinAccount;
import com.zssq.dao.pojo.CoinAccountDetail;
import com.zssq.dao.pojo.CoinEntity;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.ICoinAccountAndDetailService;

@Service("coinAccountAndDetailService")
public class CoinAccountAndDetailServiceImpl implements ICoinAccountAndDetailService {

	/** 打印日志 */
	private static Logger logger = LoggerFactory.getLogger(CoinAccountAndDetailServiceImpl.class);

	/** 金币账户映射器 */
	@Autowired
	CoinAccountMapper coinAccountMapper;

	/** 金币明细映射器 */
	@Autowired
	CoinAccountDetailMapper coinAccountDetailMapper;

	@Override
	public void saveAccountAndDetail(CoinEntity coinEntity) throws BusinessException {
		try {
			String accountCode = coinEntity.getAccountCode();
			Byte accountType = coinEntity.getAccountType();
			String actionComment = coinEntity.getActionComment();
			String orgCode = coinEntity.getOrgCode();
			Integer value = coinEntity.getValue() == null ? 0 : coinEntity.getValue();

			// 金币账户表维护
			CoinAccount coinAccount = new CoinAccount();
			coinAccount.setAccountCode(accountCode);
			coinAccount.setAccountType(accountType);
			coinAccount.setOrgCode(orgCode);
			coinAccount.setCoinBalance(value);
			Long create = System.currentTimeMillis();
			Long modify = create;
			coinAccount.setCreateTime(create);
			coinAccount.setModifyTime(modify);

			CoinAccount coinAccountFetch = coinAccountMapper.selectByAccountCode(accountCode);
			if (coinAccountFetch == null) {
				coinAccountMapper.insert(coinAccount);
			} else {
				coinAccount.setCoinBalance(coinAccountFetch.getCoinBalance() + value);
				coinAccountMapper.updateByAccountCode(coinAccount);
			}

			// 金币明细表维护
			CoinAccountDetail coinAccountDetail = new CoinAccountDetail();
			coinAccountDetail.setAccountCode(accountCode);
			Byte accountDetailType = value >= 0 ? (byte) 1 : (byte) 2;
			Integer currentValue = Math.abs(value);
			// 本次增减前余额
			Integer coinFormerBalance = coinAccountDetailMapper.selectBalance(coinAccountDetail);
			// 本次增减后余额
			Integer coinCurrentBalance = (coinFormerBalance == null ? 0 : coinFormerBalance) + value;
			coinAccountDetail.setAccountDetailType(accountDetailType);
			coinAccountDetail.setCurrentValue(currentValue);
			coinAccountDetail.setCurrentBalance(coinCurrentBalance);
			coinAccountDetail.setActionComment(actionComment);
			coinAccountDetail.setCreateTime(System.currentTimeMillis());

			coinAccountDetailMapper.insert(coinAccountDetail);
		} catch (Exception e) {
			logger.error("维护金币账户与金币明细表失败", e);
			throw new BusinessException("维护金币账户与金币明细表失败", e);
		}
	}

}
