package com.zssq.service;

import com.zssq.exceptions.BusinessException;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.vo.RelationShareVO;

/**
 * @ClassName RelationShareService
 * @Description 分享
 * @author LXW
 * @date 2017年6月12日 下午3:41:37
 * @version 1.0
 * @since JDK 1.7
 */
public interface RelationShareService {

	/**
	 * 
	 * @Title: getUserShareList  
	 * @Description: 获取个人分享列表
	 * @param pageParam
	 * @param relationShareVO
	 * @throws BusinessException    参数  
	 * @return: PageBean    返回类型
	 */
	PageBean getUserShareList(PageParam pageParam, RelationShareVO relationShareVO) throws BusinessException;

	/**
	 * 
	 * @Title: deleteShare  
	 * @Description: 删除分享
	 * @param relationShareVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	boolean deleteShare(RelationShareVO relationShareVO) throws BusinessException;

}
