package com.zssq.dao.mapper;

import java.util.Map;

import com.zssq.dao.pojo.RepositoryPraise;

public interface RepositoryPraiseBySryMapper {

    int insert(RepositoryPraise record);

	int selectCount(Map<String, Object> paramMap);

	int delete(Map<String, Object> paramMap);
}