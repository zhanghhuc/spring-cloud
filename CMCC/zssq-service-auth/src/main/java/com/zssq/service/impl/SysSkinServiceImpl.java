package com.zssq.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.dao.mapper.SysSkinInfoMapper;
import com.zssq.dao.pojo.SysSkinInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.ISysSkinService;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;

@Service("sysSkinService")
public class SysSkinServiceImpl implements ISysSkinService {

	/** 日志记录操作 */
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private SysSkinInfoMapper sysSkinInfoMapper;
	
	@Override
	public PageBean selectPage(SysSkinInfo record,PageParam pageParam) throws BusinessException {
		try{
			record.setLimitStart(pageParam.getPageNo() * pageParam.getPageSize());
			record.setLimitEnd(pageParam.getPageSize());
			List<SysSkinInfo> dataList = sysSkinInfoMapper.selectPage(record);
			int count = sysSkinInfoMapper.selectCount(record);
			return new PageBean(record.getLimitStart(), record.getLimitEnd(), count, dataList);
		} catch (Exception e) {
			log.error("皮肤分页查询异常", e);
			throw new BusinessException("皮肤分页查询异常", e);
		}
	}

	@Override
	public SysSkinInfo selectInUse() throws BusinessException {
		try{
			List<SysSkinInfo> inUse = sysSkinInfoMapper.selectInUse();
			if(inUse.size() == 0){
				return sysSkinInfoMapper.selectPage(null).get(0);
			}else{
				return inUse.get(0);
			}
		} catch (Exception e) {
			log.error("查询正在使用的皮肤异常", e);
			throw new BusinessException("查询正在使用的皮肤异常", e);
		}
	}

	@Override
	public void setInUse(Long id) throws BusinessException {
		try{
			sysSkinInfoMapper.updateInUse(id);
			sysSkinInfoMapper.updateNotInUse(id);
		} catch (Exception e) {
			log.error("设置皮肤异常", e);
			throw new BusinessException("设置皮肤异常", e);
		}
	}
	
}
