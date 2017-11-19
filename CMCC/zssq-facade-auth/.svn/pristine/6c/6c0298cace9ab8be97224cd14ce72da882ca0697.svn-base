package com.zssq.service;

import com.zssq.dao.pojo.SysSkinInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;

public interface ISysSkinService {

	/**
	 * @Function selectPage
	 * @Description 分页查询
	 * @return
	 * @throws BusinessException
	 */
	public PageBean selectPage(SysSkinInfo record,PageParam pageParam) throws BusinessException;
	
	/**
	 * @Function selectInUse
	 * @Description 查询正在使用的皮肤
	 * @return
	 * @throws BusinessException
	 */
	public SysSkinInfo selectInUse() throws BusinessException;
	
	/**
	 * @Function setInUse
	 * @Description 设置皮肤
	 * @param id
	 * @throws BusinessException
	 */
	public void setInUse(Long id)throws BusinessException;
}
