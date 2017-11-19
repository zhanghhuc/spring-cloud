package com.zssq.mblog.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.mblog.pojo.MoveAtVo;

public interface TransferMblogMentionMapper {
    
	List<MoveAtVo> selectAtList(Map<String,Object> paramsMap);

	int selectAtListCount();
}