package com.zssq.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.dao.mapper.RepositoryInfoBySryMapper;
import com.zssq.dao.mapper.RepositoryInfoMapperByGuoYang;
import com.zssq.dao.mapper.RepositoryKnowledgeMapperByGuoYang;
import com.zssq.dao.pojo.RepositoryInfo;
import com.zssq.dao.pojo.RepositoryKnowledge;
import com.zssq.exceptions.BusinessException;
import com.zssq.model.GetKnowledgeLibListForGLModel;
import com.zssq.model.RepositoryInfoMH;
import com.zssq.service.RepositoryInfoService;
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
@Service("repositoryInfoService")
public class RepositoryInfoServiceImpl implements RepositoryInfoService{
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private RepositoryInfoBySryMapper repositoryInfoBySryMapper;
	
	@Autowired
	RepositoryInfoMapperByGuoYang repositoryInfoMapperByGuoYang;
	
	@Autowired
	RepositoryKnowledgeMapperByGuoYang repositoryKnowledgeMapperByGuoYang;
	
	@Override
	public List<GetKnowledgeLibListForGLModel> getKnowledgeLibListForGL(GetKnowledgeLibListForGLForSerVo getKnowledgeLibListForGLForSerVo) throws BusinessException {
		List<GetKnowledgeLibListForGLModel> repositoryInfoList = null;
		try{
			if (getKnowledgeLibListForGLForSerVo == null) {
				log.error("RepositoryInfoServiceImpl.getKnowledgeLibListForGL：参数为空");
				throw BusinessException.build("COMMON_402", "getKnowledgeLibListForGLForSerVo");
			}
			String orgCode = getKnowledgeLibListForGLForSerVo.getOrgCode();
			if (StringUtils.isBlank(orgCode)) {
				log.error("RepositoryInfoServiceImpl.getKnowledgeLibListForGL：orgCode为空");
				throw BusinessException.build("COMMON_402", "orgCode");
			}
			
			RepositoryInfo repositoryInfo = new RepositoryInfo();
			repositoryInfo.setOrgCode(orgCode);
			repositoryInfoList = repositoryInfoMapperByGuoYang.selectByOrgCode(repositoryInfo);
		}catch (BusinessException e) {
			throw e;
		}
		return repositoryInfoList;
	}

	
	/**
	 * 组织下 知识库
	 */
	@Override
	public List<RepositoryInfoMH> getRepositoryListForMH(RepositoryInfoVo infoVo) throws BusinessException {
		List<RepositoryInfoMH> infoList = new ArrayList<RepositoryInfoMH>();
		try {
			// 参数校验
			if (infoVo == null) {
				log.error("RepositoryInfoServiceImpl.getRepositoryListForMH：参数为空");
				throw BusinessException.build("COMMON_402", "repositoryCollect");
			}
			String orgCode = infoVo.getOrgCode();
			if (StringUtils.isBlank(orgCode)) {
				log.error("RepositoryInfoServiceImpl.getRepositoryListForMH：orgCode为空");
				throw BusinessException.build("COMMON_402", "orgCode");
			}
			// 拼接参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("orgCode", orgCode); // 组织编号
			// 查询知识库列表
			infoList = repositoryInfoBySryMapper.getRepositoryListForMH(paramMap);
		} catch (BusinessException e) {
			throw e;
		}
		return infoList;
	}

	@Override
	public Integer deleteLibForGL(DeleteLibForGLForSerVO deleteLibForGLForSerVO) throws BusinessException {
		
		Integer count = null;
		try {
			// 参数校验
			if (deleteLibForGLForSerVO == null) {
				log.error("RepositoryInfoServiceImpl.deleteLibForGL：参数为空");
				throw BusinessException.build("COMMON_402", "repositoryCollect");
			}
			String orgCode = deleteLibForGLForSerVO.getOrgCode();
			String repositoryCode = deleteLibForGLForSerVO.getRepositoryCode();
			if (StringUtils.isBlank(orgCode)) {
				log.error("RepositoryInfoServiceImpl.deleteLibForGL：orgCode为空");
				throw BusinessException.build("COMMON_402", "orgCode");
			}
			if (StringUtils.isBlank(repositoryCode)) {
				log.error("RepositoryInfoServiceImpl.deleteLibForGL：repositoryCode为空");
				throw BusinessException.build("COMMON_402", "repositoryCode");
			}
			//删除知识库
			RepositoryInfo repositoryInfo = new RepositoryInfo();
			repositoryInfo.setRepositoryCode(repositoryCode);
			repositoryInfo.setOrgCode(orgCode);
			long modifyTime = new Date().getTime();
			repositoryInfo.setModifyTime(modifyTime);
			count = repositoryInfoMapperByGuoYang.deleteInfoByRepositoryCode(repositoryInfo);
			//删除知识库中的知识
			RepositoryKnowledge repositoryKnowledge = new RepositoryKnowledge();
			Date date = new Date();
			repositoryKnowledge.setCreateTime(date.getTime());
			repositoryKnowledge.setRepositoryCode(repositoryCode);
			repositoryKnowledge.setOrgCode(orgCode);
			Integer countNum = repositoryKnowledgeMapperByGuoYang.deleteKnowledgeForDelLib(repositoryKnowledge);
			if (countNum < 0) {
				log.error("KnowledgeLibController.deleteLibForGL:知识库删除失败,删除的知识为负数");
				throw BusinessException.build("", "知识库删除失败,删除的知识为负数");
			}
		} catch (BusinessException e) {
			throw e;
		}
		return count;
	}

	@Override
	public boolean checkLibTitleForGL(CheckLibTitleForGLForSerVO checkLibTitleForGLForSerVO)  throws BusinessException{
		Boolean resultBoolean = true;
		try {
			// 参数校验
			if (checkLibTitleForGLForSerVO == null) {
				log.error("RepositoryInfoServiceImpl.checkLibTitleForGL：参数为空");
				throw BusinessException.build("COMMON_402", "checkLibTitleForGLForSerVO");
			}
			String repositoryTitle = checkLibTitleForGLForSerVO.getRepositoryTitle();
			String orgCode = checkLibTitleForGLForSerVO.getOrgCode();
			if (StringUtils.isBlank(repositoryTitle)) {
				log.error("RepositoryInfoServiceImpl.deleteLibForGL：repositoryTitle为空");
				throw BusinessException.build("COMMON_402", "repositoryTitle");
			}
			if (StringUtils.isBlank(orgCode)) {
				log.error("RepositoryInfoServiceImpl.deleteLibForGL：orgCode为空");
				throw BusinessException.build("COMMON_402", "orgCode");
			}
			RepositoryInfo repositoryInfo = new RepositoryInfo();
			repositoryInfo.setRepositoryTitle(repositoryTitle);
			repositoryInfo.setOrgCode(orgCode);
			Integer count = repositoryInfoMapperByGuoYang.checkLibTitleForGL(repositoryInfo);
			if(count == 0){
				resultBoolean = false;
			}
		} catch (BusinessException e) {
			throw e;
		}
		return resultBoolean;
	}

}
