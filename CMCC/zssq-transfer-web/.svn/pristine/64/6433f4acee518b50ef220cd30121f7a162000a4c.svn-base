package com.zssq.vote.junit.test;


import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.zssq.vote.service.IVoteRelationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring.xml"})
//@TransactionConfiguration(transactionManager = "transactionManagerVote", defaultRollback = true)
public class VoteRelationJunitTest {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private IVoteRelationService voteRelationService;
	
	@Test
	//@Transactional //标明此方法需使用事务
	//@Rollback(true) //标明使用完此方法后事务回滚
	public void test() {
		try {
			// 创建一个计时器
			StopWatch watch = new StopWatch();
			// 计时器开始
			watch.start();
			
			voteRelationService.transferAll();
			
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
