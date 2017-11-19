package com.zssq.vote.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zssq.vote.service.VoteService;

@Controller
@RequestMapping("/vote")
public class VoteController {

	@Autowired
	private VoteService voteService;
	
	ExecutorService votePool = Executors.newFixedThreadPool(10);

	public void init(){
		voteService.init();
	}
	
	@RequestMapping("/parse")
	@ResponseBody
	public void voteInfo() {
		try {
			votePool.execute(new Runnable() {
				@Override
				public void run() {
					voteService.voteInfoParse();
				}
			});
			votePool.execute(new Runnable() {
				@Override
				public void run() {
					voteService.voteJoinParse();
				}
			});
			votePool.execute(new Runnable() {
				@Override
				public void run() {
					voteService.voteCommentParse();
				}
			});
			
			votePool.shutdown();
			while (true) {  
			    if (votePool.isTerminated()) {  
			    	voteService.countCommentAndJoinNum();
			        break;  
			    }  
			    Thread.sleep(200);  
			}
		} catch (Exception e) {
			e.printStackTrace();
		}  
		
		
	}

	@RequestMapping("/parseJoin")
	@ResponseBody
	public void voteJoinParse() {
		voteService.voteJoinParse();
	}

	@RequestMapping("/parseComment")
	@ResponseBody
	public void voteCommentParse() {
		voteService.voteCommentParse();
	}
	
	@RequestMapping("/parseCount")
	@ResponseBody
	public void count(){
		voteService.countCommentAndJoinNum();
	}
}
