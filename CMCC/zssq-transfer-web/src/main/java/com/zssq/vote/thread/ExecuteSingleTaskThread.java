package com.zssq.vote.thread;

import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;

import com.zssq.util.LoggerUtils;
import com.zssq.util.SpringContextUtil;
import com.zssq.vote.service.ITransferVoteService;

public class ExecuteSingleTaskThread implements Runnable {
	
	private int voteId;
	
	public ExecuteSingleTaskThread(int voteId) {
		this.voteId = voteId;
	}
	
	private ITransferVoteService transferVoteService = SpringContextUtil.getBean(ITransferVoteService.class);

	@Override
	public void run() {
		Logger log = null;
		Thread.currentThread().setName("singleTask_" + voteId);
		// 创建一个计时器
		StopWatch watch = new StopWatch();
		// 计时器开始
		watch.start();
		try {
			log = LoggerUtils.getLogger(this.getClass().getName(), "singleTask");
			
			log.debug("%%@@cachedThreadPool中活跃的线程数：" + ThreadPoolVote.getActCount());
			
			transferVoteService.transfer(voteId, log);
		} catch (Exception e) {
			log.error("$$$ExecuteSingleTaskThread出错了，voteId=" + voteId, e);
		}
		// 获取计时器计时时间
		Long time = watch.getTime();
		log.debug("线程：" + Thread.currentThread().getName() + ",总共耗时：" + time + "毫秒");
	}

}
