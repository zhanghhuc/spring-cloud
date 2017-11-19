package com.zssq.dao.mapper;

import java.util.Map;

import com.zssq.dao.pojo.MblogCollect;

/**
 * 
    * @ClassName: MblogCollectMapper  
    * @Description: 微博收藏Mapper  
    * @author Mr.B  
    * @date 2017年3月20日  
    *
 */
public interface MblogCollectMapper {
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
        * @Description: 插入
        * @param record
    	* @return int    返回类型
     */
    int insert(MblogCollect record);

    /**
     * 
        * @Title: insertSelective  
        * @Description: 有选择性的添加
        * @param record
    	* @return int    返回类型
     */
    int insertSelective(MblogCollect record);

    /**
     * 
        * @Title: selectByPrimaryKey  
        * @Description: 根据ID获取信息
        * @param id
    	* @return MblogCollect    返回类型
     */
    MblogCollect selectByPrimaryKey(Long id);

    /**
     * 
        * @Title: updateByPrimaryKeySelective  
        * @Description: 有选择性的更新
        * @param record
    	* @return int    返回类型
     */
    int updateByPrimaryKeySelective(MblogCollect record);

    /**
     * 
        * @Title: updateByPrimaryKey  
        * @Description: 更新
        * @param record
    	* @return int    返回类型
     */
    int updateByPrimaryKey(MblogCollect record);
    
    /**
     * 
        * @Title: deleteByCode  
        * @Description: 删除收藏信息
        * @param paramsMap
    	* @return int    返回类型
     */
    int deleteByCode(Map<String,Object> paramsMap);
    
    /**
     * 
        * @Title: checkIsCollect  
        * @Description: 校验是否收藏
        * @param paramsMap
    	* @return int    返回类型
     */
    int checkIsCollect(Map<String,Object> paramsMap);
}