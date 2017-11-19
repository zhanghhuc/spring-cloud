package com.zssq.mblog.dao.mapper;

import java.util.List;

import com.zssq.mblog.pojo.TransferIdCode;

public interface TransferIdCodeMapper {

	/**
     * 
        * @Title: batchInsert  
        * @Description: 批量插入
        * @param list
    	* @return int    返回类型
     */
    int batchInsert(List<TransferIdCode> list);
}
