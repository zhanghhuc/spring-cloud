package com.zssq.vote.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zssq.utils.StringTools;
import com.zssq.vote.service.ITransferVoteService;
import com.zssq.vote.service.IVoteCountService;
import com.zssq.vote.service.IVoteRelationService;
import com.zssq.vote.thread.ThreadPoolVote;

@Controller
@RequestMapping("/transfer")
public class TransferVoteController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private ITransferVoteService transferVoteService;
	@Autowired
	private IVoteCountService voteCountService;
	@Autowired
	private IVoteRelationService voteRelationService;
	
	@RequestMapping("/index")
	public String toIndex(HttpServletRequest request, Model model) {

		return "index";
	}
	
	@RequestMapping("/vote/all")
	@ResponseBody
	public JSONObject transVoteAll(HttpServletRequest request, Model model) {
		JSONObject json = new JSONObject();
		try {
			// 创建一个计时器
			StopWatch watch = new StopWatch();
			// 计时器开始
			watch.start();
			// 截止到2017年6月26日16:37:26有10558条数据待处理
			// 待迁移投票表总数据量
			int total = transferVoteService.count();
			
			// 创建的线程数量
			int threadCount = 35;
			
			String tCount = request.getParameter("threadCount");
			if (StringTools.isNotEmpty(tCount)) {
				threadCount = Integer.parseInt(tCount);
			}
			
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
					
					// 关系内容数据
					voteRelationService.transferAll();
					
					break;
				}
				Thread.sleep(2000);
			}
			
			// 计时器停止
			watch.stop();
			// 获取计时器计时时间
			Long time = watch.getTime();
			log.debug("----------总共耗时：" + time + "毫秒");
			
			json.put("status", "success");
			json.put("msg", "所有投票源数据迁移成功，总耗时：" + time + "毫秒");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("TransferVoteController.transVoteAll出错：",e);
			
			json.put("status", "fail");
			json.put("msg", "所有投票源数据迁移成功出错：" + e);
		}
		return json;
	}
	
	@RequestMapping("/vote/single")
	@ResponseBody
	public JSONObject transVoteSingle(HttpServletRequest request, Model model) {
		JSONObject json = new JSONObject();
		try {
			// 创建一个计时器
			StopWatch watch = new StopWatch();
			// 计时器开始
			watch.start();
			
			String vId = request.getParameter("voteId");
			
			int voteId = Integer.parseInt(vId);
			
			String voteInfoCode = transferVoteService.transfer(voteId, log);
			
			if (StringTools.isNotEmpty(voteInfoCode)) {
				// 添加参与范围数据
				voteCountService.addOneJoinAuth(voteInfoCode);
				
				// 关系内容数据
				voteRelationService.transfer(voteInfoCode);
			}
			
			// 计时器停止
			watch.stop();
			// 获取计时器计时时间
			Long time = watch.getTime();
			log.debug("----------总共耗时：" + time + "毫秒");
			
			json.put("status", "success");
			json.put("msg", "单条投票源数据迁移成功，总耗时：" + time + "毫秒");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("TransferVoteController.transVoteSingle出错：",e);
			json.put("status", "fail");
			json.put("msg", "单条投票源数据迁移成功出错：" + e);
		}
		return json;
	}

}
