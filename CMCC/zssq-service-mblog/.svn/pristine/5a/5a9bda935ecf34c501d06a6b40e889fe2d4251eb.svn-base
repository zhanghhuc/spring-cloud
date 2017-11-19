package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.dao.pojo.MblogTopic;

/**
 * 
    * @ClassName: MblogTopicMapper  
    * @Description: 微博话题Mapper  
    * @author Mr.B  
    * @date 2017年3月20日  
    *
 */
public interface MblogTopicMapper {
	
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
    int insert(MblogTopic record);

    /**
     * 
        * @Title: insertSelective  
        * @Description: 有选择性的添加
        * @param record
    	* @return int    返回类型
     */
    int insertSelective(MblogTopic record);

    /**
     * 
        * @Title: selectByPrimaryKey  
        * @Description: 根据主键获取信息
        * @param id
    	* @return MblogTopic    返回类型
     */
    MblogTopic selectByPrimaryKey(Long id);

    /**
     * 
        * @Title: updateByPrimaryKeySelective  
        * @Description: 根据主键选择性的更新
        * @param record
    	* @return int    返回类型
     */
    int updateByPrimaryKeySelective(MblogTopic record);

    /**
     * 
        * @Title: updateByPrimaryKey  
        * @Description: 根据主键更新
        * @param record
    	* @return int    返回类型
     */
    int updateByPrimaryKey(MblogTopic record);
    
    /**
     * 
        * @Title: queryTopicListByPage  
        * @Description: 获取列表
        * @param paramsMap
    	* @return List<MblogTopic>    返回类型
     */
    List<MblogTopic> queryTopicListByPage(Map<String,Object> paramsMap);
    
    /**
     * 
        * @Title: queryTopicListByPageCount  
        * @Description: 获取个数
        * @param paramsMap
    	* @return long    返回类型
     */
    long queryTopicListByPageCount(Map<String,Object> paramsMap);
    
    /**
     * 
        * @Title: checkIsExist  
        * @Description: 校验是否存在指定的数据
        * @param paramsMap
    	* @return MblogTopic    返回类型
     */
    MblogTopic checkIsExist(Map<String,Object> paramsMap);
    
    /**
     * 
        * @Title: checkIsExistByCode  
        * @Description: 查看是否存在
        * @param topicCode
    	* @return int    返回类型
     */
    int checkIsExistByCode(String topicCode);
    
    /**
     * 
        * @Title: batchUpdateNumByCode  
        * @Description: 批量更新
        * @param paramsMap
    	* @return int    返回类型
     */
    int batchUpdateNumByCode(Map<String,Object> paramsMap);
    
    /**
     * 
        * @Title: updateNumByCode  
        * @Description: 单个更新
        * @param paramsMap
    	* @return int    返回类型
     */
    int updateNumByCode(Map<String,Object> paramsMap);
}