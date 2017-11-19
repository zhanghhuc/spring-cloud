package com.zssq.dao.mapper;

import java.util.Map;

import com.zssq.dao.pojo.MblogPraise;

/**
 * 
    * @ClassName: MblogPraiseMapper  
    * @Description: 微博点赞Mapper  
    * @author Mr.B  
    * @date 2017年3月20日  
    *
 */
public interface MblogPraiseMapper {
	
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
    int insert(MblogPraise record);

    /**
     * 
        * @Title: insertSelective  
        * @Description: 选择性的添加
        * @param record
    	* @return int    返回类型
     */
    int insertSelective(MblogPraise record);

    /**
     * 
        * @Title: selectByPrimaryKey  
        * @Description: 根据主键获取信息
        * @param id
    	* @return MblogPraise    返回类型
     */
    MblogPraise selectByPrimaryKey(Long id);

    /**
     * 
        * @Title: updateByPrimaryKeySelective  
        * @Description: 根据主键选择性的更新
        * @param record
    	* @return int    返回类型
     */
    int updateByPrimaryKeySelective(MblogPraise record);

    /**
     * 
        * @Title: updateByPrimaryKey  
        * @Description: 根据主键更新
        * @param record
    	* @return int    返回类型
     */
    int updateByPrimaryKey(MblogPraise record);
    
    /**
     * 
        * @Title: deleteByCode  
        * @Description: 删除点赞
        * @param paramsMap
    	* @return int    返回类型
     */
    int deleteByCode(Map<String,Object> paramsMap);
    
    /**
     * 
        * @Title: checkIsPraise  
        * @Description: 判断是否点赞
        * @param paramsMap
    	* @return int    返回类型
     */
    int checkIsPraise(Map<String,Object> paramsMap);
}