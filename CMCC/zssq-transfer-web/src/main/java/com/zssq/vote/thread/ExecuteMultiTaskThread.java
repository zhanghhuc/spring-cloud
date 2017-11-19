package com.zssq.vote.thread;

import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;

import com.zssq.util.LoggerUtils;
import com.zssq.util.SpringContextUtil;
import com.zssq.vote.service.ITransferVoteService;

public class ExecuteMultiTaskThread implements Runnable {
	
	//private Logger log = Logger.getLogger(this.getClass());
	
	private int startNum;
	private int endNum;
	
	public ExecuteMultiTaskThread(int startNum, int endNum) {
		this.startNum = startNum;
		this.endNum = endNum;
	}
	
	private ITransferVoteService transferVoteService = SpringContextUtil.getBean(ITransferVoteService.class);

	@Override
	public void run() {
		Thread.currentThread().setName("线程-" + startNum + "-" + endNum);
		Logger log = LoggerUtils.getLogger(Thread.currentThread().getName(), "vote_" + startNum + "_" + endNum);
		// 创建一个计时器
		StopWatch watch = new StopWatch();
		// 计时器开始
		watch.start();
		try {
			transferVoteService.transferBatch(true, startNum, endNum, log);
			// 计时器停止
			watch.stop();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(Thread.currentThread().getName() + "出错了。。。", e);
		}
		// 获取计时器计时时间
		Long time = watch.getTime();
		log.debug("线程："+Thread.currentThread().getName()+",总共耗时：" + time + "毫秒");
	}

}
