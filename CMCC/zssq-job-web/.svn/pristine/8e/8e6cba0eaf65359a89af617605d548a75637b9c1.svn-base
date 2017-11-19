package com.zssq.job.task;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.AbstractOneOffElasticJob;
import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.IVoteTimeTaskService;

@Component
public class VoteStatusJob extends AbstractOneOffElasticJob {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private IVoteTimeTaskService voteTimeTaskService;
	@Override
	protected void process(JobExecutionMultipleShardingContext shardingContext) {
		try {
			log.info("--------voteStatusJobStart---------");
			voteTimeTaskService.timeEndVote();
			log.info("--------voteStatusJobEnd---------");
		} catch (BusinessException e) {
			log.error("VoteStatusJob.process:",e);
		}
	}

}
