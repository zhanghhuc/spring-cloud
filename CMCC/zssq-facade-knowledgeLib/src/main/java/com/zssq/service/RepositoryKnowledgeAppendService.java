package com.zssq.service;
import com.zssq.dao.pojo.RepositoryKnowledgeAppend;
import com.zssq.exceptions.BusinessException;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.vo.GetKnowledgeAppendForGLVo;
import com.zssq.vo.RepositoryKnowledgeAppendVo;
/**
 * 
 * @ClassName: RepositoryKnowledgeAppendService  
 * @Description: 知识追加  
 * @author sry  
 * @date 2017年4月28日  
 *
 */
public interface RepositoryKnowledgeAppendService {
	/**
	 * 
	 * @Title: getPortalKnowledgeAppend  
	 * @Description: 获取知识追加内容列表
	 * @param pageParam
	 * @param infoVo
	 * @return    参数  
	 * @return: PageBean    返回类型
	 */
	PageBean getPortalKnowledgeAppend(PageParam pageParam, RepositoryKnowledgeAppendVo infoVo) throws BusinessException;
	
	/**
	 * 
	 * @Title: addPortalKnowledgeAppendForMH  
	 * @Description: 追加知识
	 * @param infoVo
	 * @return    参数  
	 * @return: Boolean    返回类型
	 */
	Boolean addPortalKnowledgeAppendForMH(RepositoryKnowledgeAppend infoVo) throws BusinessException;
	/**
	 * 
	 * @Title: getKnowledgeAppendForGL  
	 * @Description: 管理后台获取知识追加
	 * @param pageParam
	 * @param getKnowledgeAppendForGLVo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: PageBean    返回类型
	 */
	PageBean getKnowledgeAppendForGL(PageParam pageParam,
			GetKnowledgeAppendForGLVo getKnowledgeAppendForGLVo) throws BusinessException ;
	

	int selectCountForIsSelf(RepositoryKnowledgeAppend repositoryKnowledgeAppend) throws BusinessException;

}
