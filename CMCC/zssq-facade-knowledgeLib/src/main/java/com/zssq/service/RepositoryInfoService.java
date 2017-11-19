package com.zssq.service;

import java.util.List;

import com.zssq.exceptions.BusinessException;
import com.zssq.model.GetKnowledgeLibListForGLModel;
import com.zssq.model.RepositoryInfoMH;
import com.zssq.vo.CheckLibTitleForGLForSerVO;
import com.zssq.vo.DeleteLibForGLForSerVO;
import com.zssq.vo.GetKnowledgeLibListForGLForSerVo;
import com.zssq.vo.RepositoryInfoVo;
/**
 * 
 * @ClassName: RepositoryInfoService  
 * @Description: 知识库信息  
 * @author sry  
 * @date 2017年4月28日  
 *
 */
public interface RepositoryInfoService {
	/**
	 * 
	 * @Title: getRepositoryListForMH  
	 * @Description: 门户查询公司下知识库列表
	 * @param infoVo
	 * @return    参数  
	 * @return: List<RepositoryInfoMH>    返回类型
	 */
	List<RepositoryInfoMH> getRepositoryListForMH(RepositoryInfoVo infoVo) throws BusinessException; ;
	/**
	 * 
	 * @Title: getKnowledgeLibListForGL  
	 * @Description: 获取知识库列表
	 * @param getKnowledgeLibListForGLForSerVo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: List<GetKnowledgeLibListForGLModel>    返回类型
	 */
	public List<GetKnowledgeLibListForGLModel> getKnowledgeLibListForGL(GetKnowledgeLibListForGLForSerVo getKnowledgeLibListForGLForSerVo) throws BusinessException ;
	/**
	 * 
	 * @Title: deleteLibForGL  
	 * @Description: 删除知识库
	 * @param deleteLibForGLForSerVO
	 * @return
	 * @throws BusinessException    参数  
	 * @return: Integer    返回类型
	 */
	public Integer deleteLibForGL(DeleteLibForGLForSerVO deleteLibForGLForSerVO) throws BusinessException ;
	/**
	 * 
	 * @Title: checkLibTitleForGL  
	 * @Description: 知识库标题查重
	 * @param checkLibTitleForGLForSerVO
	 * @return
	 * @throws BusinessException    参数  
	 * @return: boolean    返回类型
	 */
	public boolean checkLibTitleForGL(CheckLibTitleForGLForSerVO checkLibTitleForGLForSerVO) throws BusinessException ;
}
