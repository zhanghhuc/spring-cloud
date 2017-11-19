package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.dao.pojo.RepositoryKnowledge;
import com.zssq.model.RepositoryKnowledgeFrontMH;
import com.zssq.model.RepositoryKnowledgeMH;

public interface RepositoryKnowledgeBySryMapper {

    int insert(RepositoryKnowledge record);

	int updateNumTime(Map<String, Object> paramMap);
	
	int selectCount(Map<String, Object> paramMap);
	
	List<RepositoryKnowledgeMH> list(Map<String, Object> paramMap);

	int selectCountForIsSelf(Map<String, Object> paramMap);

	RepositoryKnowledge selectByCode(Map<String, Object> paramMap);

	List<RepositoryKnowledgeFrontMH> getPortalFrontHotKnowledgeLibListForMH(Map<String, Object> paramMap);

	RepositoryKnowledgeMH selectKnowledgeContent(Map<String, Object> paramMap);
}