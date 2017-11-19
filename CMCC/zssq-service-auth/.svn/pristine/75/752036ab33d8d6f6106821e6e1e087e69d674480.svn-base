package com.zssq.dao.mapper;

import com.zssq.dao.pojo.SyncResult;

public interface SyncResultMapper {

	/**
	 * 获取上次同步结果信息
	 * 
	 * @param syncType
	 * 			标识获取哪一类数据；1：用户；2：组织
	 * @return 同步结果详细信息
	 */
	SyncResult selectLastSyncResult(String syncType);
	
	/**
	 * 新增同步结果信息
	 * 
	 * @param syncResult
	 * 			同步结果详细信息
	 * @return 成功时返回新增记录数，失败时返回0
	 */
	int insert(SyncResult syncResult);
}
