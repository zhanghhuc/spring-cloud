package com.zssq.dao.mapper;

import java.util.List;

import com.zssq.dao.pojo.MblogAt;

/**
 * 
    * @ClassName: MblogAtMapper  
    * @Description: 微博ATMapper  
    * @author Mr.B  
    * @date 2017年3月20日  
    *
 */
public interface MblogAtMapper {
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
    int insert(MblogAt record);

    /**
     * 
        * @Title: insertSelective  
        * @Description: 有选择性的添加
        * @param record
    	* @return int    返回类型
     */
    int insertSelective(MblogAt record);

    /**
     * 
        * @Title: selectByPrimaryKey  
        * @Description: 通过ID后去信息
        * @param id
    	* @return MblogAt    返回类型
     */
    MblogAt selectByPrimaryKey(Long id);

    /**
     * 
        * @Title: updateByPrimaryKeySelective  
        * @Description: 有选择性的更新
        * @param record
    	* @return int    返回类型
     */
    int updateByPrimaryKeySelective(MblogAt record);

    /**
     * 
        * @Title: updateByPrimaryKey  
        * @Description: 更新
        * @param record
    	* @return int    返回类型
     */
    int updateByPrimaryKey(MblogAt record);
    
    /**
     * 
        * @Title: batchInsert  
        * @Description: 批量插入
        * @param list
    	* @return int    返回类型
     */
    int batchInsert(List<MblogAt> list);
}