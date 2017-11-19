package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.dao.pojo.MblogComment;

/**
 * 
    * @ClassName: MblogCommentMapper  
    * @Description: 微博评论Mapper  
    * @author Mr.B  
    * @date 2017年3月20日  
    *
 */
public interface MblogCommentMapper {
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
    int insert(MblogComment record);

    /**
     * 
        * @Title: insertSelective  
        * @Description: 选择性的 添加
        * @param record
    	* @return int    返回类型
     */
    int insertSelective(MblogComment record);

    /**
     * 
        * @Title: selectByPrimaryKey  
        * @Description: 更具主键获取信息
        * @param id
    	* @return MblogComment    返回类型
     */
    MblogComment selectByPrimaryKey(Long id);

    /**
     * 
        * @Title: updateByPrimaryKeySelective  
        * @Description: 选择性更新
        * @param record
    	* @return int    返回类型
     */
    int updateByPrimaryKeySelective(MblogComment record);

    /**
     * 
        * @Title: updateByPrimaryKeyWithBLOBs  
        * @Description: 更新大字段
        * @param record
    	* @return int    返回类型
     */
    int updateByPrimaryKeyWithBLOBs(MblogComment record);

    /**
     * 
        * @Title: updateByPrimaryKey  
        * @Description: 根据主键更新
        * @param record
    	* @return int    返回类型
     */
    int updateByPrimaryKey(MblogComment record);
    
    /**
     * 
        * @Title: queryCommentListByPage  
        * @Description: 获取评论分页列表信息
        * @param paramsMap
    	* @return List<MblogComment>    返回类型
     */
    List<MblogComment> queryCommentListByPage(Map<String,Object> paramsMap);
    
    /**
     * 
        * @Title: queryCommentListByPageCount  
        * @Description: 获取评论列表总数
        * @param paramsMap
    	* @return long    返回类型
     */
    long queryCommentListByPageCount(Map<String,Object> paramsMap);
    
    /**
     * 
        * @Title: deleteInfoByCode  
        * @Description: 删除评论信息
        * @param paramsMap
    	* @return int    返回类型
     */
    int deleteInfoByCode(Map<String,Object> paramsMap);
    
    
    /**
     * 
        * @Title: shieldInfoByCode  
        * @Description: 屏蔽评论信息
        * @param paramsMap
    	* @return int    返回类型
     */
    int shieldInfoByCode(Map<String,Object> paramsMap);
    
    

    /**
     * 
        * @Title: queryCountByCode  
        * @Description: 查询某一条评论所在的位置
        * @param paramsMap
    	* @return long    返回类型
     */
    long queryCountByCode(Map<String,Object> paramsMap);
    
    /**
     * 
        * @Title: batchDeleteInfo  
        * @Description: 批量删除信息
        * @param paramsMap
    	* @return int    返回类型
     */
    int batchDeleteInfo(Map<String,Object> paramsMap);
    
    /**
     * 
        * @Title: getInfoByCode  
        * @Description: 查询评论的一条信息
        * @param commentCode
    	* @return MblogComment    返回类型
     */
    MblogComment getInfoByCode(String commentCode);
    
    /**
     * 
        * @Title: getDetailByCode  
        * @Description: 
        * @param commentCode
    	* @return MblogComment    返回类型
     */
    MblogComment getDetailByCode(Map<String,Object> paramsMap);
}