package com.zssq.dao.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zssq.dao.pojo.RepositoryKnowledge;
import com.zssq.model.GetKnowledgeByTitleForGLModel;
import com.zssq.model.GetKnowledgeListByLibForGLModel;
import com.zssq.model.RepositoryKnowledgeModelForCollect;
import com.zssq.model.RepositoryKnowledgeModelForSearch;

public interface RepositoryKnowledgeMapperByGuoYang {
    
    
    List<GetKnowledgeListByLibForGLModel> selectByLib(HashMap<String, Object> map);
    
    int selectByLibCount(Map<String, Object> map);
    
    Integer updateRepositoryInfoForKnowledge(RepositoryKnowledge repositoryKnowledge);

	int selectShowCountByGL(Map<String, Object> map);

	List<RepositoryKnowledgeModelForSearch> getShowHotListByGL(Map<String, Object> map);

	Integer deleteByKnowledgeCode(RepositoryKnowledge repositoryKnowledge);
	
	GetKnowledgeByTitleForGLModel selectByTitle(RepositoryKnowledge repositoryKnowledge);

	Integer deleteKnowledgeForDelLib(RepositoryKnowledge repositoryKnowledge);

	List<RepositoryKnowledgeModelForCollect> getUserCollectKnowledgeList(Map<String, Object> map);

	int getUserCollectKnowledgeListCount(Map<String, Object> map);

	List<GetKnowledgeListByLibForGLModel> selectByLibForMove(HashMap<String, Object> map);

	GetKnowledgeListByLibForGLModel getByCode(HashMap<String, Object> mapForName);
	
}