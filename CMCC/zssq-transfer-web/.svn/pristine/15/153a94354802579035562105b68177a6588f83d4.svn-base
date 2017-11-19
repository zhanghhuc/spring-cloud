package com.zssq.vote.junit.test;

import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.zssq.vote.service.ITransferVoteService;
import com.zssq.vote.service.IVoteCountService;
import com.zssq.vote.thread.ThreadPoolVote;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring.xml"})
//@TransactionConfiguration(transactionManager = "transactionManagerVote", defaultRollback = true)
public class VoteBatchJunitTest {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private ITransferVoteService transferVoteService;
	@Autowired
	private IVoteCountService voteCountService;
	
	@Test
	@Transactional //标明此方法需使用事务
	@Rollback(true) //标明使用完此方法后事务回滚
	public void test() {
		try {
			// 创建一个计时器
			StopWatch watch = new StopWatch();
			// 计时器开始
			watch.start();
			// 截止到2017年6月26日16:37:26有10558条数据待处理
			// 待迁移投票表总数据量
			int total = transferVoteService.count();
			
			// 创建的线程数量
			int threadCount = 30;
			// 每个线程处理的数据量
			int endNum = (int) Math.ceil((double) total / threadCount);
			log.debug("@@@@@@@@@@待迁移投票总数量为：" + total + ",线程数量：" + threadCount + ",每个线程处理的投票数量：" + endNum);
			for (int i = 0; i < threadCount; i++) {
				int startNum = 0;
				if (i > 0) {
					startNum = i * endNum;
				}
				ThreadPoolVote.addMultiTask(startNum, endNum);
				Thread.sleep(2000);
			}
			ThreadPoolVote.shutdown();
			while (true) {
				log.debug("~~~~~~~~~~~~~fixedThreadPool线程池中活动的线程数为：" + ThreadPoolVote.getActiveCount());
				if (ThreadPoolVote.isTerminated()) {
					
					// 批量添加参与范围数据
					voteCountService.addJoinAuth();
					
					break;
				}
				Thread.sleep(2000);
			}
			
			// 计时器停止
			watch.stop();
			// 获取计时器计时时间
			Long time = watch.getTime();
			log.debug("----------总共耗时：" + time + "毫秒");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
//		System.out.println(System.nanoTime());
		System.out.println(Runtime.getRuntime().availableProcessors());
		int total = 10558;
		int num = (int) Math.ceil((double) total / 30);
		
		System.out.println(num);
	}
	
}
