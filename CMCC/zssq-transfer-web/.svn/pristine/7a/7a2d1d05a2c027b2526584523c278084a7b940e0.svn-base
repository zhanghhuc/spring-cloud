package com.zssq.credit.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.credit.dao.mapper.CoinAccountDetailMapper;
import com.zssq.credit.dao.mapper.CoinAccountMapper;
import com.zssq.credit.dao.mapper.IntegralAccountDetailMapper;
import com.zssq.credit.dao.mapper.IntegralAccountMapper;
import com.zssq.credit.pojo.CoinAccount;
import com.zssq.credit.pojo.CoinAccountDetail;
import com.zssq.credit.pojo.IntegralAccount;
import com.zssq.credit.pojo.IntegralAccountDetail;

@Service("creditDetailTransferService")
public class CreditDetailTransferServiceImpl implements CreditDetailTransferService {

	/** 积分账户映射器 */
	@Autowired
	IntegralAccountMapper integralAccountMapper;

	/** 金币账户映射器 */
	@Autowired
	CoinAccountMapper coinAccountMapper;

	/** 积分明细映射器 */
	@Autowired
	IntegralAccountDetailMapper integralAccountDetailMapper;

	/** 金币明细映射器 */
	@Autowired
	CoinAccountDetailMapper coinAccountDetailMapper;

	@Override
	public void integralDetail() {
		// 导入的偏移量
		int offset = 0;
		// 分批次查询的条数
		int pageSize = 500;
		// 需要导入的总条数
		int totalSize = integralAccountMapper.selectPageCount();

		while (offset <= totalSize) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("limitStart", offset);
			map.put("limitEnd", pageSize);
			// 分批次查询积分账户表
			List<IntegralAccount> integralAccountList = integralAccountMapper.selectPage(map);
			if (CollectionUtils.isNotEmpty(integralAccountList)) {
				// 分批次导入数据的积分明细集合
				List<IntegralAccountDetail> integralDetailList = new ArrayList<IntegralAccountDetail>();
				// 积分明细
				IntegralAccountDetail integralAccountDetail = null;
				for (IntegralAccount integralAccount : integralAccountList) {
					integralAccountDetail = new IntegralAccountDetail();
					integralAccountDetail.setActionCode("-1");// 积分行为编号：-1：数据迁移导入的历史数据
					integralAccountDetail.setAccountCode(integralAccount.getAccountCode());
					integralAccountDetail.setAccountDetailType((byte) 1);// 积分账户明细分类：1-增加，2-减少
					integralAccountDetail.setCurrentBalance(integralAccount.getIntegralBalance());
					integralAccountDetail.setCurrentValue(integralAccount.getIntegralBalance());
					integralAccountDetail.setCreateTime(System.currentTimeMillis());
					integralDetailList.add(integralAccountDetail);
				}
				// 分批次导入积分明细
				if (CollectionUtils.isNotEmpty(integralDetailList)) {
					integralAccountDetailMapper.batchInsert(integralDetailList);
				}
				// 增加偏移量
				offset += pageSize;
			}
		}
	}

	@Override
	public void coinDetail() {
		// 导入的偏移量
		int offset = 0;
		// 分批次查询的条数
		int pageSize = 500;
		// 需要导入的总条数
		int totalSize = coinAccountMapper.selectPageCount();

		while (offset <= totalSize) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("limitStart", offset);
			map.put("limitEnd", pageSize);
			// 分批次查询金币账户表
			List<CoinAccount> coinAccountList = coinAccountMapper.selectPage(map);
			if (CollectionUtils.isNotEmpty(coinAccountList)) {
				// 分批次导入数据的金币明细集合
				List<CoinAccountDetail> coinDetailList = new ArrayList<CoinAccountDetail>();
				// 金币明细
				CoinAccountDetail coinAccountDetail = null;
				for (CoinAccount coinAccount : coinAccountList) {
					coinAccountDetail = new CoinAccountDetail();
					coinAccountDetail.setActionCode("-1");// 积分行为编号：-1：数据迁移导入的历史数据
					coinAccountDetail.setAccountCode(coinAccount.getAccountCode());
					coinAccountDetail.setAccountDetailType((byte) 1);// 金币账户明细分类：1-增加，2-减少
					coinAccountDetail.setCurrentBalance(coinAccount.getCoinBalance());
					coinAccountDetail.setCurrentValue(coinAccount.getCoinBalance());
					coinAccountDetail.setCreateTime(System.currentTimeMillis());
					coinDetailList.add(coinAccountDetail);
				}
				// 分批次导入金币明细
				if (CollectionUtils.isNotEmpty(coinDetailList)) {
					coinAccountDetailMapper.batchInsert(coinDetailList);
				}
				// 增加偏移量
				offset += pageSize;
			}
		}
	}
}
