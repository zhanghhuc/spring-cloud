package com.zssq.dao.mapper;

import java.util.Map;

import com.zssq.dao.pojo.MblogForward;

/**
 * 
    * @ClassName: MblogForwardMapper  
    * @Description: 微博转发操作  
    * @author Mr.B  
    * @date 2017年3月30日  
    *
 */
public interface MblogForwardMapper {

	/**
	 * 
	    * @Title: queryForwardInfo  
	    * @Description: 获取转发信息
	    * @param curMblogCode
		* @return String    返回类型
	 */
	String queryForwardInfo(String curMblogCode);
	
	/**
	 * 
	    * @Title: insert  
	    * @Description: 插入数据
	    * @param record
		* @return int    返回类型
	 */
	int insert(MblogForward record);
	
	/**
	 * 
	    * @Title: checkIsFored  
	    * @Description: 校验是否已经转发
	    * @param paramsMap
		* @return int    返回类型
	 */
	int checkIsFored(Map<String,Object> paramsMap);
}
