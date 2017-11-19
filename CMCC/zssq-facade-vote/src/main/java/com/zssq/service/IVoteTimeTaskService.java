package com.zssq.service;

import com.zssq.exceptions.BusinessException;

public interface IVoteTimeTaskService {

	/**
	 * 
	 * @Title: timeEndVote
	 * @Description: 定时任务结束投票
	 * @throws BusinessException
	 */
	public void timeEndVote() throws BusinessException;
}
