package com.zssq.service;

import java.util.List;

import com.zssq.dao.pojo.BlogClass;
import com.zssq.exceptions.BusinessException;
import com.zssq.vo.BlogClassVO;

/**
 * 
 * @ClassName: BlogClassService  
 * @Description: 博客分类  
 * @author ZKZ  
 * @date 2017年3月20日  
 *
 */
public interface BlogClassService {
	
	/**
	 * 
	 * @Title: getBlogClassList  
	 * @Description: 查询分类列表
	 * @param blogClassVO
	 * @throws BusinessException    参数  
	 * @return: List<BlogClass>    返回类型
	 */
	public List<BlogClass> getBlogClassList(BlogClassVO blogClassVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getClassNum  
	 * @Description: 查询分类个数
	 * @param blogClassVO
	 * @throws BusinessException    参数  
	 * @return: int    返回类型
	 */
	public int getClassNum(BlogClassVO blogClassVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: saveBlogClass  
	 * @Description: 保存分类信息
	 * @param blogClass 分类信息
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean saveBlogClass(BlogClass blogClass) throws BusinessException;

	/**
	 * 
	 * @Title: updateClassBlogNum  
	 * @Description: 修改分类下博客数量
	 * @param blogClassVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean updateClassBlogNum(BlogClassVO blogClassVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: deleteBlogClass  
	 * @Description: 删除分类
	 * @param blogClassVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean deleteBlogClass(BlogClassVO blogClassVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getDefaultClassCode  
	 * @Description: 查询默认分类编号
	 * @param blogClassVO
	 * @throws BusinessException    参数  
	 * @return: String    返回类型
	 */
	public String getDefaultClassCode(BlogClassVO blogClassVO) throws BusinessException;

}
