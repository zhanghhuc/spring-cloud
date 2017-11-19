package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.dao.pojo.RelationDynamic;
import com.zssq.model.RelationDynamicModel;

/**
 * 
 * @ClassName: RelationDynamicMapper  
 * @Description: 动态  
 * @author ZKZ  
 * @date 2017年4月17日  
 *
 */
public interface RelationDynamicMapper {

    /**
     * 
     * @Title: insert  
     * @Description: 保存动态信息
     * @param relationDynamic
     * @return: int    返回类型
     */
    int insert(RelationDynamic relationDynamic);

    /**
     * 
     * @Title: delete  
     * @Description: 删除动态信息
     * @param dynamicMap
     * @return: int    返回类型
     */
	int delete(Map<String, Object> dynamicMap);
	
	/**
	 * 
	 * @Title: batchDelete  
	 * @Description: 批量删除动态信息
	 * @param dynamicMap
	 * @return: int    返回类型
	 */
	int batchDelete(Map<String, Object> dynamicMap);
	
	/**
	 * 
	 * @Title: deleteRepo  
	 * @Description: 删除知识库动态
	 * @param dynamicMap
	 * @return: int    返回类型
	 */
	int deleteRepo(Map<String, Object> dynamicMap);

	/**
	 * 
	 * @Title: shield  
	 * @Description: 屏蔽/恢复动态
	 * @param dynamicMap
	 * @return: int    返回类型
	 */
	int shield(Map<String, Object> dynamicMap);
	
	/**
	 * 
	 * @Title: selectUserSpaceDynamicCount  
	 * @Description: 查询个人空间动态个数
	 * @param paramMap
	 * @return: int    返回类型
	 */
	int selectUserSpaceDynamicCount(Map<String, Object> paramMap);

	/**
	 * 
	 * @Title: selectUserSpaceDynamicList  
	 * @Description: 查询个人空间动态列表
	 * @param paramMap
	 * @return: List<RelationDynamicModel>    返回类型
	 */
	List<RelationDynamicModel> selectUserSpaceDynamicList(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: selectUserDynamicCount  
	 * @Description: 查询个人主页动态个数
	 * @param paramMap
	 * @return: int    返回类型
	 */
	int selectUserDynamicCount(Map<String, Object> paramMap);

	/**
	 * 
	 * @Title: selectUserDynamicList  
	 * @Description: 查询个人主页动态列表
	 * @param paramMap
	 * @return: List<RelationDynamicModel>    返回类型
	 */
	List<RelationDynamicModel> selectUserDynamicList(Map<String, Object> paramMap);

	/**
	 * 
	 * @Title: selectTeamDynamicCount  
	 * @Description: 查询班组动态个数
	 * @param paramMap
	 * @return: int    返回类型
	 */
	int selectTeamDynamicCount(Map<String, Object> paramMap);

	/**
	 * 
	 * @Title: selectTeamDynamicList  
	 * @Description: 查询班组动态列表
	 * @param paramMap
	 * @return: List<RelationDynamicModel>    返回类型
	 */
	List<RelationDynamicModel> selectTeamDynamicList(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: selectTeamUserDynamicCount  
	 * @Description: 查询班组成员动态个数
	 * @param paramMap
	 * @return: int    返回类型
	 */
	int selectTeamUserDynamicCount(Map<String, Object> paramMap);

	/**
	 * 
	 * @Title: selectTeamUserDynamicList  
	 * @Description: 查询班组成员动态列表
	 * @param paramMap
	 * @return: List<RelationDynamicModel>    返回类型
	 */
	List<RelationDynamicModel> selectTeamUserDynamicList(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: selectPortalUserDynamicCount  
	 * @Description: 查询门户成员动态个数
	 * @param paramMap
	 * @return: int    返回类型
	 */
	int selectPortalUserDynamicCount(Map<String, Object> paramMap);

	/**
	 * 
	 * @Title: selectPortalUserDynamicList  
	 * @Description: 查询门户成员动态列表
	 * @param paramMap
	 * @return: List<RelationDynamicModel>    返回类型
	 */
	List<RelationDynamicModel> selectPortalUserDynamicList(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: selectPortalTeamDynamicCount  
	 * @Description: 查询门户班组动态个数
	 * @param paramMap
	 * @return: int    返回类型
	 */
	int selectPortalTeamDynamicCount(Map<String, Object> paramMap);

	/**
	 * 
	 * @Title: selectPortalTeamDynamicList  
	 * @Description: 查询门户班组动态列表
	 * @param paramMap
	 * @return: List<RelationDynamicModel>    返回类型
	 */
	List<RelationDynamicModel> selectPortalTeamDynamicList(Map<String, Object> paramMap);

	/**
	 * 
	 * @Title: selectPortalTopTeamDynamicCount  
	 * @Description: 查询门户1号班组动态个数
	 * @param paramMap
	 * @return: int    返回类型
	 */
	int selectPortalTopTeamDynamicCount(Map<String, Object> paramMap);

	/**
	 * 
	 * @Title: selectPortalTopTeamDynamicList  
	 * @Description: 查询门户1号班组动态列表
	 * @param paramMap
	 * @return: List<RelationDynamicModel>    返回类型
	 */
	List<RelationDynamicModel> selectPortalTopTeamDynamicList(Map<String, Object> paramMap);

	/**
	 * 
	 * @Title: selectPortalExTeamDynamicCount  
	 * @Description: 查询门户百强班组动态个数
	 * @param paramMap
	 * @return: int    返回类型
	 */
	int selectPortalExTeamDynamicCount(Map<String, Object> paramMap);

	/**
	 * 
	 * @Title: selectPortalExTeamDynamicList  
	 * @Description: 查询门户百强班组动态列表
	 * @param paramMap
	 * @return: List<RelationDynamicModel>    返回类型
	 */
	List<RelationDynamicModel> selectPortalExTeamDynamicList(Map<String, Object> paramMap);

	/**
	 * 
	 * @Title: getDynamicTeamCodes  
	 * @Description: 查询动态被哪些关注的班组首页展示
	 * @param paramMap
	 * @return: List<String>    返回类型
	 */
	List<String> getDynamicTeamCodes(Map<String, Object> paramMap);

}