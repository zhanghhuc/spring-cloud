package com.zssq.forum.service;

public interface ITransferForumService {

	/**
	 * @Function transfer
	 * @Description 论坛版块迁移
	 * @param pageFlag
	 * @param startNum
	 * @param endNum
	 * @throws Exception
	 */
	void transfer(boolean pageFlag, int startNum, int endNum) throws Exception;
}
