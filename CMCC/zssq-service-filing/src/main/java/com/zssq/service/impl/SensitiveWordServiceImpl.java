package com.zssq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.dao.mapper.SensitiveWordMapper;
import com.zssq.dao.pojo.SensitiveWordInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.Message;
import com.zssq.service.ISensitiveWordService;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.utils.PropertiesUtil;

/**
 * 
 * @ClassName: SensitiveWordServiceImpl  
 * @Description: 敏感词相关的服务  
 * @author power  
 * @date 2017年3月31日  
 *
 */
@Service("sensitiveWordService")
public class SensitiveWordServiceImpl implements ISensitiveWordService {
	
	@Autowired
	SensitiveWordMapper sensitiveWordMapper; 

	@Override
	public boolean insertSelective(SensitiveWordInfo sensitiveWordInfo) throws BusinessException{
		try {
			return sensitiveWordMapper.insertSelective(sensitiveWordInfo)==1;
		} catch (Exception e) {
			Message m = PropertiesUtil.getMessage("COMMON_400");
			e.printStackTrace();
			throw new BusinessException(m.getCode(),m.getTip());
		}
	}

	@Override
	public boolean deleteBySensitiveCode(String sensitiveCode) throws BusinessException {
		try {
			return sensitiveWordMapper.deleteBySensitiveCode(sensitiveCode)==1;
		} catch (Exception e) {
			Message m = PropertiesUtil.getMessage("COMMON_400");
			e.printStackTrace();
			throw new BusinessException(m.getCode(),m.getTip());
		}
	}

	@Override
	public PageBean selectPage(PageParam pageParam, SensitiveWordInfo record)
			throws BusinessException {
		try {
			record.setLimitStart(pageParam.getPageNo() * pageParam.getPageSize());
			record.setLimitEnd(pageParam.getPageSize());
			List<SensitiveWordInfo> wordList = sensitiveWordMapper.selectPage(record);
			int totalCount = sensitiveWordMapper.selectPageCount(record);
			PageBean pageBean=new PageBean(record.getLimitStart(), record.getLimitEnd(), totalCount, wordList);
			return pageBean;
		} catch (Exception e) {
			Message m = PropertiesUtil.getMessage("COMMON_400");
			e.printStackTrace();
			throw new BusinessException(m.getCode(),m.getTip());
		}
	}

	@Override
	public int batchInsert(List<SensitiveWordInfo> sensitiveWordInfos) throws BusinessException {
		try {
			return sensitiveWordMapper.batchInsert(sensitiveWordInfos);
		} catch (Exception e) {
			Message m = PropertiesUtil.getMessage("COMMON_400");
			e.printStackTrace();
			throw new BusinessException(m.getCode(),m.getTip());
		}
	}
}
