package com.zssq.service;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zssq.dao.pojo.VoteInfo;
import com.zssq.exceptions.BusinessException;

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class Test {
	
	@Autowired
	private IVoteThirdService voteThirdService;
	
	@org.junit.Test
	public void test(){
		try {
			List<VoteInfo> list = voteThirdService.sumupTeamVote("BZ1700171", 0, 10);
			
			System.out.println(list.size());
		
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

}
