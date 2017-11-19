package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.dao.pojo.RelationShare;
import com.zssq.model.RelationShareModel;

/**
 * 
 * @ClassName: RelationShareMapper  
 * @Description: 分享记录  
 * @author ZKZ  
 * @date 2017年6月15日  
 *
 */
public interface RelationShareMapper {

	/**
     * 
     * @Title: insert  
     * @Description: 保存分享信息
     * @param relationShare
     * @return: int    返回类型
     */
    int insert(RelationShare relationShare);
    
    /**
     * 
     * @Title: selectUserShareCount  
     * @Description: 查询分享个数
     * @param paramMap
     * @return: int    返回类型
     */
    int selectUserShareCount(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: selectUserShareList  
     * @Description: 查询分享列表
     * @param paramMap
     * @return: List<RelationShareModel>    返回类型
     */
	List<RelationShareModel> selectUserShareList(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: delete  
	 * @Description: 删除
	 * @param paramMap
	 * @return: int    返回类型
	 */
	int delete(Map<String, Object> paramMap);
}