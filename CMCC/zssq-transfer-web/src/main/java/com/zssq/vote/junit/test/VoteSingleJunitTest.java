package com.zssq.vote.junit.test;


import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zssq.utils.StringTools;
import com.zssq.vote.service.ITransferVoteService;
import com.zssq.vote.service.IVoteCountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring.xml"})
//@TransactionConfiguration(transactionManager = "transactionManagerVote", defaultRollback = true)
public class VoteSingleJunitTest {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private ITransferVoteService transferVoteService;
	@Autowired
	private IVoteCountService voteCountService;
	
	@Test
	//@Transactional //标明此方法需使用事务
	//@Rollback(true) //标明使用完此方法后事务回滚
	public void test() {
		try {
			// 创建一个计时器
			StopWatch watch = new StopWatch();
			// 计时器开始
			watch.start();
			
			int voteId = 2482;
			
			String voteInfoCode = transferVoteService.transfer(voteId, log);
			
			if (StringTools.isNotEmpty(voteInfoCode)) {
				// 添加参与范围数据
				voteCountService.addOneJoinAuth(voteInfoCode);
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
	
}