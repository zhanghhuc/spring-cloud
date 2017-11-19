package com.zssq.service;

import java.util.List;

import com.zssq.dao.pojo.SensitiveWordInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;

/**
 * 
 * @ClassName: ISensitiveWordService  
 * @Description: 敏感词相关的服务  
 * @author power  
 * @date 2017年3月31日  
 *
 */
public interface ISensitiveWordService {
	
	/**
	 * 
	 * @Title: insertSelective  
	 * @Description: 添加敏感词到数据库表sys_sensitive_word
	 * @param @param sensitiveWordInfo
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return boolean    返回类型  
	 * @throws
	 */
	public boolean insertSelective(SensitiveWordInfo sensitiveWordInfo) throws BusinessException;
	
	/**
	 * 
	 * @Title: deleteBySensitiveCode  
	 * @Description: 根据敏感词编号删除敏感词
	 * @param @param sensitiveCode
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return boolean    返回类型  
	 * @throws
	 */
	public boolean deleteBySensitiveCode(String sensitiveCode) throws BusinessException;
	
	/**
	 * 
	 * @Title: selectPage  
	 * @Description: 分页查询敏感词
	 * @param @param pageParam
	 * @param @param record
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return PageBean    返回类型  
	 * @throws
	 */
	public PageBean selectPage(PageParam pageParam, SensitiveWordInfo record) throws BusinessException ;
	
	
	/**
	 * @Function batchInsert
	 * @Description 批量插入敏感词
	 * @param sensitiveWordInfos
	 * @return
	 * @throws BusinessException
	 */
	public int batchInsert(List<SensitiveWordInfo> sensitiveWordInfos) throws BusinessException ;
	
}
