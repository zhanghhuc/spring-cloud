package com.zssq.dao.mapper;

import java.util.Map;

import com.zssq.dao.pojo.RepositoryKnowledgeContent;
import com.zssq.model.RepositoryKnowledgeContentMH;

public interface RepositoryKnowledgeContentBySryMapper {

    int insert(RepositoryKnowledgeContent record);

	RepositoryKnowledgeContentMH selectKnowledgeContent(Map<String, Object> paramMap);
}