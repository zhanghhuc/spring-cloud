package com.zssq.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.zssq.dao.mapper.VoteCommentMapper;
import com.zssq.dao.mapper.VoteInfoMapper;
import com.zssq.exceptions.BusinessException;
import com.zssq.utils.DateUtils;

public class CommonService {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private VoteInfoMapper voteInfoMapper;
	@Autowired
	private VoteCommentMapper voteCommentMapper;
	
	// 加、减运算枚举
	public enum Operator {
		ADD, SUB
	}
	/**
	 * 减少/增加投票主表中的评论数
	 * @Function updateCommentNumForVoteInfo
	 * @Description 
	 * @param voteInfoCode 投票主表CODE
	 * @param opt
	 * @param count
	 * @throws BusinessException
	 */
	protected synchronized void updateCommentNumForVoteInfo(String voteInfoCode, Operator opt, int count) throws BusinessException {
		try {
			long curTime = DateUtils.getFormatDateLong();
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", voteInfoCode);// 投票主表CODE
			map.put("modifyTime", curTime);// 最后更新时间
			if (opt == Operator.ADD) {
				map.put("express", "+" + count);
			}
			if (opt == Operator.SUB) {
				map.put("express", "-" + count);
			}
			voteInfoMapper.updateCommentNum(map);
		} catch (Exception e) {
			log.error("CommonService.updateCommentNumForVoteInfo", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	/**
	 * 减少/增加评论表中的回复数
	 * @Function updateReplyCountForVoteComment
	 * @Description 
	 * @param voteInfoCode 投票主表CODE
	 * @param commentCode  评论CODE
	 * @param opt
	 * @param count
	 * @throws BusinessException
	 */
	protected synchronized void updateReplyCountForVoteComment(String voteInfoCode, String commentCode, Operator opt, int count) throws BusinessException {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", commentCode);// 评论CODE
			map.put("modifyTime", DateUtils.getFormatDateLong());// 最后更新时间
			if (opt == Operator.ADD) {
				map.put("express", "+" + count);
			}
			if (opt == Operator.SUB) {
				map.put("express", "-" + count);
			}
			voteCommentMapper.updateReplyNum(map);
		} catch (Exception e) {
			log.error("CommonService.updateReplyCountForVoteComment", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	/**
	 * 减少/增加投票主表中的分享数
	 * @Function updateCommentNumForVoteInfo
	 * @Description 
	 * @param voteInfoCode 投票主表CODE
	 * @param opt
	 * @param count
	 * @throws BusinessException
	 */
	protected synchronized void updateShareNumForVoteInfo(String voteInfoCode, Operator opt, int count) throws BusinessException {
		try {
			long curTime = DateUtils.getFormatDateLong();
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", voteInfoCode);// 投票主表CODE
			map.put("modifyTime", curTime);// 最后更新时间
			if (opt == Operator.ADD) {
				map.put("express", "+" + count);
			}
			if (opt == Operator.SUB) {
				map.put("express", "-" + count);
			}
			voteInfoMapper.updateShareNum(map);
		} catch (Exception e) {
			log.error("CommonService.updateShareNumForVoteInfo", e);
			throw BusinessException.build("COMMON_400");
		}
	}

}
