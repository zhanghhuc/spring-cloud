package com.zssq.forum.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zssq.forum.service.ITransferForumService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring.xml"})
public class ForumJunitTest {
	
	@Autowired
	private ITransferForumService transferForumService;
	
	@Test
	//@Transactional //标明此方法需使用事务
	//@Rollback(true) //标明使用完此方法后事务回滚
	public void test() {
		int startNum = 0;
		int endNum = 3500;
		try {
			transferForumService.transfer(true, startNum, endNum);
			System.out.println("迁移成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	

}
