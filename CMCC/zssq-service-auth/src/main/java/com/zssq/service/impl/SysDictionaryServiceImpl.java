package com.zssq.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.zssq.common.cache.client.JedisClusterService;
import com.zssq.dao.mapper.SysDictionaryMapper;
import com.zssq.dao.pojo.SysDictionary;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.ISysDictionaryService;

@Service("sysDictionaryService")
public class SysDictionaryServiceImpl implements ISysDictionaryService {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private SysDictionaryMapper sysDictionaryMapper;
	
	@Autowired
	private JedisClusterService jedisClusterService;
	
	/**
	 * 根据code查询字段信息
	 * @see com.zssq.service.ISysDictionaryService#selectByCode(java.lang.String)
	 */
	@Override
	public SysDictionary selectByCode(String dictCode) throws BusinessException {
		try {
			SysDictionary sysDictionary = null;
			String odicJson = jedisClusterService.get(dictCode);
			if(StringUtils.isNotBlank(odicJson)) {
				log.debug("SysDictionaryServiceImpl.selectByRecord：从缓存中获取到了字典信息 " + dictCode);
				sysDictionary = JSON.parseObject(odicJson, SysDictionary.class);
				return sysDictionary;
			}else{
				sysDictionary = sysDictionaryMapper.selectByCode(dictCode);
				if(sysDictionary != null) {
					log.debug("SysDictionaryServiceImpl.selectByRecord：从缓存中获取到了字典信息 " + dictCode);
					odicJson = JSON.toJSONString(sysDictionary);
					jedisClusterService.set(dictCode, odicJson);
				}
				return sysDictionary;
			}
		} catch (Exception e) {
			log.error("获取字典表数据出错", e);
			throw new BusinessException("获取字典表数据出错", e);
		}
	}

	/**
	 * 获取字典集合
	 * @see com.zssq.service.ISysDictionaryService#selectByRecord(com.zssq.dao.pojo.SysDictionary)
	 */
	@Override
	public List<SysDictionary> selectByRecord(SysDictionary record) throws BusinessException {
		try {
			return sysDictionaryMapper.selectByRecord(record);
		} catch (Exception e) {
			log.error("获取字典数据集合出错", e);
			throw new BusinessException("获取字典数据集合出错", e);
		}
	}

}
