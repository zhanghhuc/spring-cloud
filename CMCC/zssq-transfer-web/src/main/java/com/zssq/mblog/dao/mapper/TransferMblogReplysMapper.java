package com.zssq.mblog.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.mblog.pojo.MoveComVo;

public interface TransferMblogReplysMapper {
   
	List<MoveComVo> selectAllComment(Map<String,Object> paramsMap);

	int selectAllCommentCount();
}