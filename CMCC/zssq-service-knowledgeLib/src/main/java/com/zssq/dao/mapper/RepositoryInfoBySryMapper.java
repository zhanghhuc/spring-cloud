package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.model.RepositoryInfoMH;

public interface RepositoryInfoBySryMapper {

	List<RepositoryInfoMH> getRepositoryListForMH(Map<String, Object> paramMap);
}