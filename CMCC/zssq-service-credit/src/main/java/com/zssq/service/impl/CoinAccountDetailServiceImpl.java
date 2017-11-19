package com.zssq.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.dao.mapper.CoinAccountDetailMapper;
import com.zssq.dao.mapper.IntegralActionMapper;
import com.zssq.dao.pojo.CoinAccountDetail;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.ICoinAccountDetailService;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;

@Service("coinAccountDetailService")
public class CoinAccountDetailServiceImpl implements ICoinAccountDetailService {
	
	/** 打印日志 */
	private static Logger logger = LoggerFactory.getLogger(CoinAccountDetailServiceImpl.class);

	/** 金币明细映射器 */
	@Autowired
	CoinAccountDetailMapper coinAccountDetailMapper;
	
	/** 积分行为映射器 */
	@Autowired
	IntegralActionMapper integralActionMapper;

	@Override
	public PageBean selectPage(PageParam pageParam, CoinAccountDetail record) throws BusinessException {
		try {
			record.setLimitStart(pageParam.getPageNo() * pageParam.getPageSize());
			record.setLimitEnd(pageParam.getPageSize());
			List<CoinAccountDetail> recordList = coinAccountDetailMapper.selectPage(record);
			int totalCount = coinAccountDetailMapper.selectPageCount(record);
			PageBean pageBean = new PageBean(record.getLimitStart(), record.getLimitEnd(), totalCount, recordList);
			return pageBean;
		} catch (Exception e) {
			logger.error("分页查询金币明细列表失败", e);
			throw new BusinessException("分页查询金币明细列表失败", e);
		}
	}

	@Override
	public boolean insert(CoinAccountDetail coinAccountDetail) throws BusinessException {
		try {
			return coinAccountDetailMapper.insert(coinAccountDetail) == 1;
		} catch (Exception e) {
			logger.error("添加金币明细失败", e);
			throw new BusinessException("添加金币明细失败", e);
		}
	}

	@Override
	public Integer selectBalance(CoinAccountDetail coinAccountDetail) throws BusinessException {
		try {
			return coinAccountDetailMapper.selectBalance(coinAccountDetail);
		} catch (Exception e) {
			logger.error("查询用户金币明细的当前余额失败", e);
			throw new BusinessException("查询用户金币明细的当前余额失败", e);
		}
	}

}
