package com.zssq.mblog.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.mblog.pojo.MoveMblogVo;
import com.zssq.mblog.pojo.TransferForMblog;

public interface TransferMblogMapper {
    
	/**
	 * 
	    * @Title: selectAllSelfMblogInfo  
	    * @Description: 查询所有原创微博信息
	    * @param paramsMap
		* @return List<MoveMblogVo>    返回类型
	 */
	List<MoveMblogVo> selectAllSelfMblogInfo(Map<String,Object> paramsMap);
	
	
	int selectAllSelfMblogInfoCount();

	/**
	 * 
	    * @Title: selectAllForMblogInfo  
	    * @Description: 查询所有的转发微博信息
	    * @param paramsMap
		* @return List<MoveMblogVo>    返回类型
	 */
	List<MoveMblogVo> selectAllForMblogInfo(Map<String,Object> paramsMap);
	
	
	int selectAllForMblogInfoCount();

	/**
	 * 
	    * @Title: selectForPro  
	    * @Description: 获取转发历程
	    * @param paramsMap
		* @return List<TransferForMblog>    返回类型
	 */
	List<TransferForMblog> selectForPro(Map<String,Object> paramsMap);
}