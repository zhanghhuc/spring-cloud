package com.zssq.service;

import java.util.List;

import com.zssq.dao.pojo.RelationDynamic;
import com.zssq.dao.pojo.RelationDynamicTeamRel;
import com.zssq.dao.pojo.RelationSubjectInfo;
import com.zssq.dao.pojo.RelationSubjectResource;
import com.zssq.exceptions.BusinessException;
import com.zssq.vo.RelationDynamicVO;

/**
 * 
 * @ClassName: RelationThirdDynamicService  
 * @Description: 动态
 * @author ZKZ  
 * @date 2017年3月16日  
 *
 */
public interface RelationThirdDynamicService {
	
	/**
	 * 
	 * @Title: saveDynamic  
	 * @Description: 保存动态信息
	 * @param relationDynamic 动态信息
	 * @param relationDynamicTeamRel 动态班组关系信息
	 * @param relationSubjectInfo 内容信息
	 * @param relationSubjectResourceList 内容资源信息
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean saveDynamic(RelationDynamic relationDynamic, RelationDynamicTeamRel relationDynamicTeamRel,
			RelationSubjectInfo relationSubjectInfo, List<RelationSubjectResource> relationSubjectResourceList)
					throws BusinessException;
	
	/**
	 * 
	 * @Title: deleteDynamic  
	 * @Description: 删除动态
	 * @param relationDynamicVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean deleteDynamic(RelationDynamicVO relationDynamicVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: deleteBatchDynamic  
	 * @Description: 批量删除动态
	 * @param relationDynamicVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean deleteBatchDynamic(RelationDynamicVO relationDynamicVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: deleteRepoDynamic  
	 * @Description: 删除知识库动态
	 * @param relationDynamicVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean deleteRepoDynamic(RelationDynamicVO relationDynamicVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: updateShieldStatus  
	 * @Description: 屏蔽/恢复动态
	 * @param relationDynamicVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean updateShieldStatus(RelationDynamicVO relationDynamicVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: updateTeamExc  
	 * @Description: 班组设为/取消百强班组
	 * @param relationDynamicVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean updateTeamExc(RelationDynamicVO relationDynamicVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: updateTeamNoOne  
	 * @Description: 班组设为/取消一号班组
	 * @param relationDynamicVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean updateTeamNoOne(RelationDynamicVO relationDynamicVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: updateTeamStatus  
	 * @Description: 解散班组
	 * @param relationDynamicVO
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean updateTeamStatus(RelationDynamicVO relationDynamicVO) throws BusinessException;

}
