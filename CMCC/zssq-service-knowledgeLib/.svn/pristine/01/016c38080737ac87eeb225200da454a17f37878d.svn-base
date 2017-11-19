package com.zssq.dao.mapper;

import java.util.List;

import com.zssq.dao.pojo.RepositoryInfo;
import com.zssq.model.GetKnowledgeLibListForGLModel;

public interface RepositoryInfoMapperByGuoYang {
    
    /*
     * 查询公司下知识库列表
     */
    List<GetKnowledgeLibListForGLModel> selectByOrgCode(RepositoryInfo repositoryInfo);

	Integer deleteInfoByRepositoryCode(RepositoryInfo repositoryInfo);

	Integer checkLibTitleForGL(RepositoryInfo repositoryInfo);
	
	Integer insertRepositoryInfo(RepositoryInfo repositoryInfo);


	Integer updateKnowledgeLib(RepositoryInfo repositoryInfo);
	
	Integer isUniqueForUpdate(RepositoryInfo repositoryInfo);

}