package com.zssq.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.dao.mapper.UserLevelConfigMapper;
import com.zssq.dao.pojo.UserLevelConfig;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.IUserLevelConfigService;

/**
 * 
 * @ClassName: UserLevelConfigServiceImpl  
 * @Description: 等级经验值配置表相关的操作   
 * @author power  
 * @date 2017年5月5日  
 *
 */
@Service("userLevelConfigService")
public class UserLevelConfigServiceImpl implements IUserLevelConfigService {
	
	/** 打印日志 */
	private static Logger logger = LoggerFactory.getLogger(UserLevelConfigServiceImpl.class);
	
	/** 经验值等级配置映射器 */
	@Autowired
	UserLevelConfigMapper userLevelConfigMapper;

	@Override
	public List<UserLevelConfig> selectAll() throws BusinessException {
		try {
			return userLevelConfigMapper.selectAll();
		} catch (Exception e) {
			logger.error("查询经验值等级列表失败", e);
			throw new BusinessException("查询经验值等级列表失败", e);
		}
	}

}
