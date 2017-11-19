package com.zssq.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.dao.mapper.IntegralAccountDetailMapper;
import com.zssq.dao.pojo.IntegralAccountDetail;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.IIntegralAccountDetailService;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;

/**
 * 
 * @ClassName: IntegralAccountDetailServiceImpl
 * @Description: 积分明细相关的服务
 * @author power
 * @date 2017年4月12日
 *
 */
@Service("integralAccountDetailService")
public class IntegralAccountDetailServiceImpl implements IIntegralAccountDetailService {
	
	/** 打印日志 */
	private static Logger logger = LoggerFactory.getLogger(IntegralAccountDetailServiceImpl.class);

	/** 积分明细映射器 */
	@Autowired
	IntegralAccountDetailMapper integralAccountDetailMapper;

	@Override
	public PageBean selectPage(PageParam pageParam, IntegralAccountDetail record) throws BusinessException {
		try {
			record.setLimitStart(pageParam.getPageNo() * pageParam.getPageSize());
			record.setLimitEnd(pageParam.getPageSize());
			List<IntegralAccountDetail> recordList = integralAccountDetailMapper.selectPage(record);
			int totalCount = integralAccountDetailMapper.selectPageCount(record);
			PageBean pageBean = new PageBean(record.getLimitStart(), record.getLimitEnd(), totalCount, recordList);
			return pageBean;
		} catch (Exception e) {
			logger.error("分页查询积分账户明细列表失败", e);
			throw new BusinessException("分页查询积分账户明细列表失败", e);
		}
	}

	@Override
	public boolean insert(IntegralAccountDetail integralAccountDetail) throws BusinessException {
		try {
			return integralAccountDetailMapper.insert(integralAccountDetail) == 1;
		} catch (Exception e) {
			logger.error("添加积分账户明细失败", e);
			throw new BusinessException("添加积分账户明细失败", e);
		}
	}
	

}
