package com.zssq.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.dao.mapper.ExpAccountDetailMapper;
import com.zssq.dao.pojo.ExpAccountDetail;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.IExpAccountDetailService;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;

@Service("expAccountDetailService")
public class ExpAccountDetailServiceImpl implements IExpAccountDetailService {
	
	/** 打印日志 */
	private static Logger logger = LoggerFactory.getLogger(ExpAccountDetailServiceImpl.class);

	/** 经验值明细映射器 */
	@Autowired
	ExpAccountDetailMapper expAccountDetailMapper;

	@Override
	public PageBean selectPage(PageParam pageParam, ExpAccountDetail record) throws BusinessException {
		try {
			record.setLimitStart(pageParam.getPageNo() * pageParam.getPageSize());
			record.setLimitEnd(pageParam.getPageSize());
			List<ExpAccountDetail> recordList = expAccountDetailMapper.selectPage(record);
			int totalCount = expAccountDetailMapper.selectPageCount(record);
			PageBean pageBean = new PageBean(record.getLimitStart(), record.getLimitEnd(), totalCount, recordList);
			return pageBean;
		} catch (Exception e) {
			logger.error("分页查询经验值明细列表失败", e);
			throw new BusinessException("分页查询经验值明细列表失败", e);
		}
	}

	@Override
	public boolean insert(ExpAccountDetail expAccountDetail) throws BusinessException {
		try {
			return expAccountDetailMapper.insert(expAccountDetail) == 1;
		} catch (Exception e) {
			logger.error("添加经验值明细失败", e);
			throw new BusinessException("添加经验值明细失败", e);
		}
	}

}
