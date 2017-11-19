package com.zssq.vote.thread;

import org.apache.log4j.Logger;

import com.zssq.constants.ForumConstants;
import com.zssq.constants.StatisticConstants;
import com.zssq.constants.VoteConstants;
import com.zssq.dao.pojo.StatisticCommon;
import com.zssq.service.IStatisticService;
import com.zssq.util.SpringContextUtil;
import com.zssq.utils.DateUtils;
public class StatisticsVoteThread implements Runnable {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	// 待统计对象类型
	private Byte statisticsObjectType;
	// 公司code
	private String orgCode;
	// 班组code
	private String teamCode;
	// 班组名称
	private String teamName;
	// 个人code
	private String peopleCode;
	// 个人名称
	private String peopleName;
	
	public StatisticsVoteThread() {
		
	}
	
	public StatisticsVoteThread(Byte statisticsObjectType, String orgCode, String objCode, String objName) {
		this.statisticsObjectType = statisticsObjectType;
		this.orgCode = orgCode;
		if (ForumConstants.STATISTICS_OBJECT_TYPE_1 == statisticsObjectType) {
			this.peopleCode = objCode;
			this.peopleName = objName;
		}
		if (ForumConstants.STATISTICS_OBJECT_TYPE_2 == statisticsObjectType) {
			this.teamCode = objCode;
			this.teamName = objName;
		}
	}
	
	private IStatisticService statisticService = SpringContextUtil.getBean(IStatisticService.class);

	@Override
	public void run() {
		try {
			StatisticCommon record = new StatisticCommon();
			record.setOrgCode(orgCode);
			record.setVote(StatisticConstants.VOTE);
			record.setCreateTime(DateUtils.nowTimeMillis());
			if (VoteConstants.STATISTICS_OBJECT_TYPE_1 == statisticsObjectType) {
				record.setPeopleCode(peopleCode);
				record.setPeopleName(peopleName);
			}
			if (VoteConstants.STATISTICS_OBJECT_TYPE_2 == statisticsObjectType) {
				record.setTeamCode(teamCode);
				record.setTeamName(teamName);
			}
			statisticService.addRecord(record);
		} catch (Exception e) {
			log.error("StatisticsVoteThread.run:调用统计接口出错：", e);
		}
	}

}
