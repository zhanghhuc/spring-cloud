package com.zssq.service;

import java.util.List;
import java.util.Map;

import com.zssq.dao.pojo.SysDeputyInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;

public interface ISysDeputyService {

	/**
	 * @Function addDeputy
	 * @Description 新增代发
	 * @param leaderCode
	 * @param staffCode
	 * @param apps
	 * @return
	 * @throws BusinessException
	 */
	public Boolean addDeputy(String leaderCode,String staffCode,String apps, String userCode) throws BusinessException;
	
	
	/**
	 * @Function modifyDeputy
	 * @Description 修改代发
	 * @param deputyCode
	 * @param apps
	 * @return
	 * @throws BusinessException
	 */
	public Boolean modifyDeputy(String deputyCode,String apps) throws BusinessException;
	
	
	/**
	 * @Function deleteDeputy
	 * @Description 删除代发配置
	 * @param deputyCode
	 * @return
	 * @throws BusinessException
	 */
	public Boolean deleteDeputy(String deputyCode) throws BusinessException;
	
	
	/**
	 * @Function getPageBean
	 * @Description 获取配置列表
	 * @param map
	 * @param pageParam
	 * @return
	 * @throws BusinessException
	 */
	public PageBean getPageBean(Map<String,Object> map,PageParam pageParam) throws BusinessException ;
	
	/**
	 * @Function selectByCode
	 * @Description 获取代发详细信息
	 * @param deputyCode
	 * @return
	 * @throws BusinessException
	 */
	public SysDeputyInfo selectByCode(String deputyCode) throws BusinessException;
	
	/**
	 * @Function selectByRecord
	 * @Description TODO
	 * @param record
	 * @return
	 * @throws BusinessException
	 */
	public List<SysDeputyInfo> selectByRecord(SysDeputyInfo record) throws BusinessException;
	
	
	/**
	 * @Function isDeputy
	 * @Description 是否存在代发关系
	 * @param userCode 代发人
	 * @param leaderCode 代发领导
	 * @param apps 代发应用
	 * @return
	 * @throws BusinessException
	 */
	public boolean isDeputy(String userCode,String leaderCode,String app) throws BusinessException;
	
	/**
	 * 
	 * @Title: getDeputyCode  
	 * @Description: 获取代发编号
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return String    返回类型  
	 * @throws
	 */
	public String getDeputyCode() throws BusinessException;
}
