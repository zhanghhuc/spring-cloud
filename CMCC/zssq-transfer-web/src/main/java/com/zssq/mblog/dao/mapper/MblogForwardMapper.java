package com.zssq.mblog.dao.mapper;

import java.util.List;

import com.zssq.mblog.pojo.MblogForward;

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
        * @Title: batchInsert  
        * @Description: 批量插入
        * @param list
    	* @return int    返回类型
     */
    int batchInsert(List<MblogForward> list);
}
