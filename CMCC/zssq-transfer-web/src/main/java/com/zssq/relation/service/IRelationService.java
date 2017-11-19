package com.zssq.relation.service;
/**
 * 
 * @ClassName: IRelationService  
 * @Description: 关系推荐模块数据导入  
 * @author sry  
 * @date 2017年6月22日  
 *
 */
public interface IRelationService {
	/**
	 * 
	 * @Title: importMblogRecommend  
	 * @Description: 导入微博
	 * @param pageNo
	 * @param pageSize
	 * @return    参数  
	 * @return: long    返回类型
	 *//*
	long importMblogRecommend(int pageNo, int pageSize);
	*//**
	 * 
	 * @Title: importBlogRecommend  
	 * @Description: 导入博客推荐
	 * @param pageNo
	 * @param pageSize
	 * @return    参数  
	 * @return: long    返回类型
	 *//*
	long importBlogRecommend(Integer pageNo, Integer pageSize);
	*//**
	 * 
	 * @Title: recBlogCount  
	 * @Description: 共多少可导 博客数据
	 * @return    参数  
	 * @return: int    返回类型
	 *//*
	int recBlogCount();
	*//**
	 * 
	 * @Title: recMblogCount  
	 * @Description: 共多少可导 微博数据
	 * @return    参数  
	 * @return: int    返回类型
	 *//*
	int recMblogCount();*/
	/**
	 * 
	 * @Title: batchImportRecommend  
	 * @Description: 批量导入推荐    参数  
	 * @return: void    返回类型
	 */
	boolean batchImportRecommend();
	/**
	 * 
	 * @Title: batchImportBlog  
	 * @Description: 批量导入博客 关系内容
	 * @return: void    返回类型
	 */
	void batchImportBlog();
	/**
	 * 
	 * @Title: batchImportMblog  
	 * @Description: 批量导入微博 关系内容
	 * @return: void    返回类型
	 */
	void batchImportMblog();
	
}
