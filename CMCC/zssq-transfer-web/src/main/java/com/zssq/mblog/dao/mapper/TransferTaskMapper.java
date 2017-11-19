package com.zssq.mblog.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.mblog.pojo.TransferTaskVo;

public interface TransferTaskMapper {
    List<TransferTaskVo> selectAllTask();
    int updateTaskStatus(Map<String,Object> paramsMap);
    int updateTaskTotal(Map<String,Object> paramsMap);
    int updateTaskPage(Map<String,Object> paramsMap);
}