package com.zssq.service;

import com.zssq.dao.pojo.RelationDynamic;
import com.zssq.dao.pojo.RelationRecommend;
import com.zssq.exceptions.BusinessException;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.vo.RelationRecVO;

/**
 * 
 * @ClassName: RelationRecService  
 * @Description: 推荐  
 * @author ZKZ 
 * @date 2017年3月16日  
 *
 */
public interface RelationRecService {
	
	/**
	 * 
	 * @Title: getPortalRecList  
	 * @Description: 查询推荐列表-门户
	 * @param pageParam
	 * @param relationRecVO
	 * @throws BusinessException    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean getPortalRecList(PageParam pageParam, RelationRecVO relationRecVO) throws BusinessException;

	/**
	 * 
	 * @Title: getRecStatus  
	 * @Description: 判断是否已经推荐
	 * @param relationRecVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean getRecStatus(RelationRecVO relationRecVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: saveRec  
	 * @Description: 保存推荐信息
	 * @param relationRecommend
	 * @param relationDynamic
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean saveRec(RelationRecommend relationRecommend, RelationDynamic relationDynamic) throws BusinessException;
	
	/**
	 * 
	 * @Title: updateRecStatus  
	 * @Description: 修改推荐状态（忽略）
	 * @param relationRecVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean updateRecStatus(RelationRecVO relationRecVO) throws BusinessException;
	
}
