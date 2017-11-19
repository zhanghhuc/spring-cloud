package test.java.com.powertorque.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zssq.vote.controller.VoteController;



/**
 * Created by POWER on 2017/5/15.
 */
//@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
//@ContextConfiguration(locations = { "classpath:spring.xml" })
public class VoteTest {
//    @Autowired
    private VoteController voteController;

    
    @Test
    public void init(){
    	String[] statuss = "".split("\\|");
		for(String s : statuss){
			System.out.println(s);
		}
        
    }

    @org.junit.Test
    public void startParseVote(){
        voteController.voteInfo();
    }
    
    @org.junit.Test
    public void startParseVoteJoin(){
    	voteController.voteJoinParse();
    }

    @org.junit.Test
    public void comment(){
        voteController.voteCommentParse();
    }
    @org.junit.Test
    public void count(){
    	voteController.count();
    }
    @org.junit.Test
    public void te(){
    	 try {
    		 ExecutorService exe = Executors.newFixedThreadPool(50);  
			 for (int i = 1; i <= 5; i++) {  
				 final int a = i;
			     exe.execute(new Runnable(){

					@Override
					public void run() {
						System.out.println(a);
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  
					}}); 
			 }  
			 exe.shutdown();
			 while (true) {  
			     if (exe.isTerminated()) {  
			         System.out.println("结束了！");  
			         break;  
			     }  
			     Thread.sleep(200);  
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}  
    }
}
