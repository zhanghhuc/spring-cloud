package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.dao.model.BlogModel;
import com.zssq.dao.model.TeamBlogModel;
import com.zssq.dao.pojo.BlogInfo;
import com.zssq.pojo.RssReturnPo;

/**
 * 
 * @ClassName: BlogInfoMapper  
 * @Description: 博客  
 * @author ZKZ  
 * @date 2017年4月6日  
 *
 */
public interface BlogInfoMapper {
	
	/**
     * 
     * @Title: selectCount  
     * @Description: 查询个数
     * @param paramMap
     * @return: int    返回类型
     */
    int selectCount(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: list  
     * @Description: 查询博客列表 - 分页
     * @param paramMap
     * @return: List<BlogModel>    返回类型
     */
    List<BlogModel> list(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: selectQualityCount  
     * @Description: 查询精品博客数量
     * @param paramMap
     * @return: int    返回类型
     */
    int selectQualityCount(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: qualityList  
     * @Description: 查询精品博客列表 - 分页
     * @param paramMap
     * @return: List<BlogModel>    返回类型
     */
    List<BlogModel> qualityList(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: selectCollectCount  
     * @Description: 查询收藏博客数量
     * @param paramMap
     * @return: int    返回类型
     */
    int selectCollectCount(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: collectList  
     * @Description: 查询收藏博客列表 - 分页
     * @param paramMap
     * @return: List<BlogModel>    返回类型
     */
    List<BlogModel> collectList(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: selectSubCount  
     * @Description: 查询订阅博客数量
     * @param paramMap
     * @return: int    返回类型
     */
     int selectSubCount(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: subList  
     * @Description: 查询订阅博客列表 - 分页
     * @param paramMap
     * @return: List<BlogModel>    返回类型
     */
     List<BlogModel> subList(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: selectPlanCount  
     * @Description: 查询定时发布博客数量
     * @param paramMap
     * @return: int    返回类型
     */
    int selectPlanCount(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: planList  
     * @Description: 查询定时发布博客列表 - 分页
     * @param paramMap
     * @return: List<BlogInfo>    返回类型
     */
    List<BlogInfo> planList(Map<String, Object> paramMap);

    /**
     * 
     * @Title: planAllList  
     * @Description: 查询定时发布博客列表 - 不分页
     * @param paramMap
     * @return: List<BlogInfo>    返回类型
     */
	List<BlogInfo> planAllList(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: selectRssList  
	 * @Description: 查询订阅博客列表
	 * @param paramMap
	 * @return: List<RssReturnPo>    返回类型
	 */
	List<RssReturnPo> selectRssList(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: selectMiniInfo  
     * @Description: 查询博客基本信息
     * @param paramMap
     * @return: BlogInfo    返回类型
     */
    BlogInfo selectMiniInfo(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: selectBaseInfo  
     * @Description: 查询博客内容
     * @param paramMap
     * @return: BlogModel    返回类型
     */
    BlogModel selectBaseInfo(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: select  
     * @Description: 查询博客详情
     * @param paramMap
     * @return: BlogModel    返回类型
     */
    BlogModel select(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: getPlanPublishTime  
     * @Description: 查询定时发布时间
     * @param blogCode
     * @return: Long    返回类型
     */
    Long getPlanPublishTime(String blogCode);
    
	/**
	 * 
	 * @Title: insert  
	 * @Description: 保存博客信息
	 * @param blogInfo
	 * @return: int    返回类型
	 */
    int insert(BlogInfo blogInfo);
    
    /**
     * 
     * @Title: insertForward  
     * @Description: 保存转发信息
     * @param blogInfo
     * @return: int    返回类型
     */
//    int insertForward(BlogInfo blogInfo);
    
    /**
     * 
     * @Title: update  
     * @Description: 更新博客信息
     * @param paramMap
     * @return: int    返回类型
     */
    int update(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: updateClass  
     * @Description: 修改博客分类
     * @param paramMap
     * @return: int    返回类型
     */
    int updateClass(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: updatePublishTime  
     * @Description: 修改定时发布时间
     * @param paramMap
     * @return: int    返回类型
     */
    int updatePublishTime(Map<String, Object> paramMap);
    
    /**
     * 
     * @Title: delete  
     * @Description: 删除博客
     * @param paramMap
     * @return: int    返回类型
     */
    int delete(Map<String, Object> paramMap);

    /**
     * 
     * @Title: shield  
     * @Description: 屏蔽/恢复博客
     * @param paramMap
     * @return: int    返回类型
     */
	int shield(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: selectCountByGL  
	 * @Description: 管理后台 共几条
	 * @param paramMap
	 * @return    参数  
	 * @return: int    返回类型
	 */
	int selectCountByGL(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: listByGL  
	 * @Description: 管理后台 记录
	 * @param paramMap
	 * @return    参数  listByGL
	 * @return: List<BlogInfo>    返回类型
	 */
	List<BlogInfo> listByGL(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: getTitleDataByCode  
	 * @Description: 根据code查tital和date
	 * @param paramMap
	 * @return: List<BlogModel>    返回类型
	 */
	List<BlogModel> getTitleDataByCode(Map<String, Object> paramMap);

	/**
	 * 
	 * @Title: selectTeamBlogList  
	 * @Description: 班组归档时获取博客列表
	 * @param paramMap
	 * @return: List<TeamBlogModel>    返回类型
	 */
	List<TeamBlogModel> selectTeamBlogList(Map<String, Object> paramMap);

}