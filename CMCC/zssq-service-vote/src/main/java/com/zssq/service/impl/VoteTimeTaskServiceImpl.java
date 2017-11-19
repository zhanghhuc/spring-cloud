package com.zssq.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.constants.VoteConstants;
import com.zssq.dao.mapper.VoteInfoMapper;
import com.zssq.dao.pojo.VoteInfo;
import com.zssq.dao.pojo.VoteInfoExample;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.IVoteTimeTaskService;
import com.zssq.utils.DateUtils;

@Service("voteTimeTaskService")
public class VoteTimeTaskServiceImpl implements IVoteTimeTaskService {
	
	@Autowired
	private VoteInfoMapper voteInfoMapper;

	@Override
	public void timeEndVote() throws BusinessException {
		VoteInfoExample example = new VoteInfoExample();
		VoteInfoExample.Criteria criteria = example.createCriteria();
		Long now = DateUtils.getFormatDateLong();
		//查询条件
		criteria.andIsDeleteEqualTo(VoteConstants.NO);
		criteria.andIsDisableEqualTo(VoteConstants.NO);
		criteria.andIsHideEqualTo(VoteConstants.NO);
		criteria.andVoteStatusEqualTo(VoteConstants.STATUS_4);//进行中的投票
		criteria.andEndTimeLessThan(now);//结束时间小于当前时间
		
		List<VoteInfo> voteList = voteInfoMapper.selectByExample(example);
		List<String> codeList = new ArrayList<String>();
		for(VoteInfo vote : voteList){
			codeList.add(vote.getCode());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("codes", codeList);
		map.put("status", VoteConstants.STATUS_5);
		map.put("modifyTime", now);
		if(codeList.size() > 0){
			voteInfoMapper.updateVoteStatusList(map);
		}
	}

}
