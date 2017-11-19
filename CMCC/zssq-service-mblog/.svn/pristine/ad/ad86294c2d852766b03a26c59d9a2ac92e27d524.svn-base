package com.zssq.dao.mapper;

import java.util.Map;

import com.zssq.dao.pojo.MblogSubscribe;

/**
 * 
    * @ClassName: MblogSubscribeMapper  
    * @Description: 微博订阅Mapper  
    * @author Mr.B  
    * @date 2017年3月20日  
    *
 */
public interface MblogSubscribeMapper {
	
	/**
	 * 
	    * @Title: deleteByPrimaryKey  
	    * @Description: 删除
	    * @param id
		* @return int    返回类型
	 */
    int deleteByPrimaryKey(Long id);

    /**
     * 
        * @Title: insert  
        * @Description: 添加
        * @param record
    	* @return int    返回类型
     */
    int insert(MblogSubscribe record);

    /**
     * 
        * @Title: insertSelective  
        * @Description: 选择性的添加
        * @param record
    	* @return int    返回类型
     */
    int insertSelective(MblogSubscribe record);

    /**
     * 
        * @Title: selectByPrimaryKey  
        * @Description: 根据主键获取信息
        * @param id
    	* @return MblogSubscribe    返回类型
     */
    MblogSubscribe selectByPrimaryKey(Long id);

    /**
     * 
        * @Title: updateByPrimaryKeySelective  
        * @Description: 根据主键选择性的更新
        * @param record
    	* @return int    返回类型
     */
    int updateByPrimaryKeySelective(MblogSubscribe record);

    /**
     * 
        * @Title: updateByPrimaryKey  
        * @Description: 根据主键更新
        * @param record
    	* @return int    返回类型
     */
    int updateByPrimaryKey(MblogSubscribe record);
    
    /**
     * 
        * @Title: deleteByCode  
        * @Description: 删除订阅信息
        * @param paramsMap
    	* @return int    返回类型
     */
    int deleteByCode(Map<String,Object> paramsMap);
    
    /**
     * 
        * @Title: checkIsSub  
        * @Description: 校验是否订阅
        * @param paramsMap
    	* @return int    返回类型
     */
    int checkIsSub(Map<String,Object> paramsMap);
}