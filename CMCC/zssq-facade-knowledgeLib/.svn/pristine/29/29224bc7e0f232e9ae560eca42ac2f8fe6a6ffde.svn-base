package com.zssq.service;
import java.util.List;

import com.zssq.dao.pojo.RepositoryKnowledge;
import com.zssq.dao.pojo.RepositoryKnowledgeContent;
import com.zssq.exceptions.BusinessException;
import com.zssq.model.GetKnowledgeByTitleForGLModel;
import com.zssq.model.RepositoryKnowledgeFrontMH;
import com.zssq.model.RepositoryKnowledgeMH;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.vo.DeleteKnowledgeListForGLForSerVO;
import com.zssq.vo.EditKnowledgeLibForForInGLVO;
import com.zssq.vo.GetKnowledgeByTitleForGLForSerVo;
import com.zssq.vo.GetKnowledgeListByLibForGLForSerVo;
import com.zssq.vo.MoveKnowledgeForGLSerVO;
import com.zssq.vo.RepositoryKnowledgeVo;
import com.zssq.vo.RepositorySearchVo;
/**
 * 
 * @ClassName: RepositoryKnowledgeService  
 * @Description: 知识基本信息
 * @author sry  
 * @date 2017年4月28日  
 *
 */
public interface RepositoryKnowledgeService {
	/**
	 * 
	 * @Title: getPortalKnowledgeListByLibForMH  
	 * @Description: 查询知识库下知识列表
	 * @param pageParam
	 * @param infoVo
	 * @return    参数  
	 * @return: PageBean    返回类型
	 */
	PageBean getPortalKnowledgeListByLibForMH(PageParam pageParam, RepositoryKnowledgeVo infoVo) throws BusinessException;;
	
	/**
	 * 
	 * @Title: getBaseKnowlegeByCond  
	 * @Description: 知识基本信息
	 * @param qo
	 * @return    参数  
	 * @return: RepositoryKnowledge    返回类型
	 */
	RepositoryKnowledge getBaseKnowlegeByCond(RepositoryKnowledgeVo qo) throws BusinessException;
	
	/**
	 * 
	 * @Title: addPortalKnowledgeListForMH  
	 * @Description: 发布知识
	 * @param kgVo
	 * @param kgcVo
	 * @return    参数  
	 * @return: Boolean    返回类型
	 */
	Boolean addPortalKnowledgeForMH(RepositoryKnowledge kgVo,RepositoryKnowledgeContent kgcVo) throws BusinessException;
	/**
	 * @Title: getKnowledgeListByLibForGL  
	 * @Description: 后台获取知识库知识列表
	 * @param pp
	 * @param getKnowledgeListByLibForGLForSerVo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: PageBean    返回类型
	 */
	PageBean getKnowledgeListByLibForGL (PageParam pp , GetKnowledgeListByLibForGLForSerVo getKnowledgeListByLibForGLForSerVo) throws BusinessException;

	/**
	 * 
	 * @Title: getPortalFrontHotKnowledgeLibListForMH  
	 * @Description: 获取门户首页知识热区
	 * @param infoVo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: List<RepositoryKnowledgeFrontMH>    返回类型
	 */
	List<RepositoryKnowledgeFrontMH> getPortalFrontHotKnowledgeLibListForMH(RepositoryKnowledgeVo infoVo) throws BusinessException;
	
	/**
	 * 
	 * @Title: getPortalSearchKnowledgeListForMH  
	 * @Description: 搜索知识
	 * @param pageParam
	 * @param infoVo
	 * @return    参数  
	 * @return: PageBean    返回类型
	 */
	PageBean getPortalSearchKnowledgeListForMH(PageParam pageParam, RepositorySearchVo infoVo) throws BusinessException;
	/**
	 * 
	 * @Title: moveKnowledgeForGL  
	 * @Description: 知识移动到
	 * @param moveKnowledgeForGLSerVO
	 * @return
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean moveKnowledgeForGL(MoveKnowledgeForGLSerVO moveKnowledgeForGLSerVO) throws BusinessException;

	/**
	 * 
	 * @Title: getShowHotListByGL  
	 * @Description: 搜索知识
	 * @param pageParam
	 * @param repositorySearchVo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: PageBean    返回类型
	 */
	public PageBean getShowHotListByGL(PageParam pageParam,RepositorySearchVo repositorySearchVo) throws BusinessException;
	/**
	 * 
	 * @Title: deleteKnowledgeListForGL  
	 * @Description: 删除知识
	 * @param deleteKnowledgeListForGLForSerVO
	 * @return
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean deleteKnowledgeListForGL(DeleteKnowledgeListForGLForSerVO deleteKnowledgeListForGLForSerVO) throws BusinessException;
	/**
	 * 
	 * @Title: insertKnowledgeLib  
	 * @Description: 创建知识库
	 * @param editKnowledgeLibForForInGLVO
	 * @return
	 * @throws BusinessException    参数  
	 * @return: Integer    返回类型
	 */
	public Integer insertKnowledgeLib(EditKnowledgeLibForForInGLVO editKnowledgeLibForForInGLVO) throws BusinessException;
	/**
	 * 
	 * @Title: updateKnowledgeLib  
	 * @Description: 更新知识库
	 * @param editKnowledgeLibForForInGLVO
	 * @return
	 * @throws BusinessException    参数  
	 * @return: Integer    返回类型
	 */
	public Integer updateKnowledgeLib(EditKnowledgeLibForForInGLVO editKnowledgeLibForForInGLVO) throws BusinessException;
	
	/**
	 * 
	 * @Title: getKnowledgeByTitleForGL  
	 * @Description: 通过code获取知识
	 * @param getKnowledgeByTitleForGLForSerVo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: GetKnowledgeByTitleForGLModel    返回类型
	 */
	public GetKnowledgeByTitleForGLModel getKnowledgeByTitleForGL(
			GetKnowledgeByTitleForGLForSerVo getKnowledgeByTitleForGLForSerVo) throws BusinessException;
	
	/**
	 * 
	 * @Title: getPortalKnowledgeByCodeForMH  
	 * @Description: 知识code 获取知识
	 * @param infoVo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: RepositoryKnowledgeMH    返回类型
	 */
	RepositoryKnowledgeMH getPortalKnowledgeByCodeForMH(RepositoryKnowledgeVo infoVo) throws BusinessException;

	/*List<String> hadName(MoveKnowledgeForGLSerVO moveKnowledgeForGLSerVO) throws BusinessException;*/


}
