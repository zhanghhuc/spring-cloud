package com.zssq.dao.mapper;

import java.util.HashMap;
import java.util.List;

import com.zssq.model.GetKnowledgeAppendForGLModel;


public interface RepositoryKnowledgeAppendMapperByGuoYang {

	List<GetKnowledgeAppendForGLModel> getKnowledgeAppendForGL(HashMap<String, Object> map);

	Integer getCountAppendForGL(HashMap<String, Object> map);
}