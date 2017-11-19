package com.zssq.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.dao.mapper.IntegralActionMapper;
import com.zssq.dao.pojo.IntegralAction;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.IIntegralActionService;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;

/**
 * 
 * @ClassName: IntegralActionServiceImpl  
 * @Description: 积分行为相关的服务  
 * @author power  
 * @date 2017年4月11日  
 *
 */
@Service("integralActionService")
public class IntegralActionServiceImpl implements IIntegralActionService {
	
	/** 打印日志 */
	private static Logger logger = LoggerFactory.getLogger(IntegralActionServiceImpl.class);
	
	/** 积分行为映射器 */
	@Autowired
	IntegralActionMapper integralActionMapper;

	@Override
	public PageBean selectPage(PageParam pageParam,IntegralAction record) throws BusinessException {
		try {
			record.setLimitStart(pageParam.getPageNo() * pageParam.getPageSize());
			record.setLimitEnd(pageParam.getPageSize());
			List<IntegralAction> list = integralActionMapper.selectPage(record);
			int totalCount = integralActionMapper.selectPageCount(record);
			PageBean pageBean=new PageBean(record.getLimitStart(), record.getLimitEnd(), totalCount, list);
			return pageBean;
		} catch (Exception e) {
			logger.error("分页查询积分行为列表失败", e);
			throw new BusinessException("分页查询积分行为列表失败", e);
		}
	}

	@Override
	public boolean updateByActionCode(IntegralAction record) throws BusinessException {
		try {
			return integralActionMapper.updateByActionCode(record)==1;
		} catch (Exception e) {
			logger.error("修改积分行为失败", e);
			throw new BusinessException("修改积分行为失败", e);
		}
	}

	@Override
	public IntegralAction selectByActionCode(String actionCode) throws BusinessException {
		try {
			return integralActionMapper.selectByActionCode(actionCode);
		} catch (Exception e) {
			logger.error("查询积分行为失败", e);
			throw new BusinessException("查询积分行为失败", e);
		}
	}

	@Override
	public List<IntegralAction> selectAll(Byte actionType) throws BusinessException {
		try {
			return integralActionMapper.selectAll(actionType);
		} catch (Exception e) {
			logger.error("查询积分行为列表失败", e);
			throw new BusinessException("查询积分行为列表失败", e);
		}
	}

	@Override
	public List<IntegralAction> selectByIntegralPlus(Byte actionType) throws BusinessException {
		try {
			return integralActionMapper.selectByIntegralPlus(actionType);
		} catch (Exception e) {
			logger.error("查询积分值为正的积分行为列表失败", e);
			throw new BusinessException("查询积分值为正的积分行为列表失败", e);
		}
	}

	@Override
	public List<IntegralAction> selectByIntegralMinus(Byte actionType) throws BusinessException {
		try {
			return integralActionMapper.selectByIntegralMinus(actionType);
		} catch (Exception e) {
			logger.error("查询积分值为负的积分行为列表失败", e);
			throw new BusinessException("查询积分值为负的积分行为列表失败", e);
		}
	}


}
