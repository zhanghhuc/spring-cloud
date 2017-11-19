package com.zssq.forum.thread;

import org.apache.log4j.Logger;

import com.zssq.constants.StatisticConstants;
import com.zssq.dao.pojo.StatisticCommon;
import com.zssq.dao.pojo.TeamInfo;
import com.zssq.service.IStatisticService;
import com.zssq.service.ITeamInfoService;
import com.zssq.util.SpringContextUtil;
import com.zssq.utils.DateUtils;
public class StatisticsForumThread implements Runnable {
	
	private Logger log = Logger.getLogger(this.getClass());
	

	private IStatisticService statisticService = SpringContextUtil.getBean(IStatisticService.class);
	
	private ITeamInfoService teamInfoService = SpringContextUtil.getBean(ITeamInfoService.class);
	// 公司code
	private String orgCode;
	// 班组code
	private String teamCode;
	// 个人code
	private String peopleCode;
	// 个人名称
	private String peopleName;
	
	public StatisticsForumThread() {

	}
	
	public StatisticsForumThread(String orgCode, String peopleCode, String peopleName, String teamCode) {
		this.orgCode = orgCode;
		this.peopleCode = peopleCode;
		this.peopleName = peopleName;
		this.teamCode = teamCode;
	}

	@Override
	public void run() {
		try {
			StatisticCommon userRecord = new StatisticCommon();
			userRecord.setOrgCode(orgCode);
			userRecord.setPeopleCode(peopleCode);
			userRecord.setPeopleName(peopleName);
			userRecord.setPost(StatisticConstants.POST);
			userRecord.setCreateTime(DateUtils.nowTimeMillis());
			
			TeamInfo teamInfo = teamInfoService.selectByCode(teamCode);
			StatisticCommon teamRecord = new StatisticCommon();
			teamRecord.setOrgCode(teamInfo.getOrgCode());
			teamRecord.setTeamCode(teamInfo.getTeamCode());
			teamRecord.setTeamName(teamInfo.getTeamName());
			teamRecord.setPost(StatisticConstants.POST);
			teamRecord.setCreateTime(DateUtils.nowTimeMillis());
			statisticService.addRecord(teamRecord, userRecord);
		} catch (Exception e) {
			log.error("StatisticForumThread.run:调用统计接口出错：", e);
		}
	}

}
