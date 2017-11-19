package com.zssq.vote.service;

import org.apache.log4j.Logger;

/**
 * 投票源数据迁移service
 * @ClassName ITransferVoteService
 * @Description 
 * @author liurong
 * @date 2017年6月18日 下午3:25:07
 * @version 1.0
 * @since JDK 1.7
 */
public interface ITransferVoteService {
	/**
	 * 统计待迁移的投票主表数据量
	 * @Function count
	 * @Description 
	 * @return
	 * @throws Exception
	 */
	int count() throws Exception;
	
	/**
	 * 迁移投票主表、选项表、参与记录表以及评论和回复表
	 * @Function transferStepOfOne
	 * @Description 
	 * @param startNum
	 * @param endNum
	 */
	void transferBatch(boolean pageFlag, int startNum, int endNum, Logger log);
	/**
	 * 单条操作
	 * @Function transfer
	 * @Description 
	 * @param voteId
	 * @throws Exception
	 */
	String transfer(int voteId, Logger log);
	
}
