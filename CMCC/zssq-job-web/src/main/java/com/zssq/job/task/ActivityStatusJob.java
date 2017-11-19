package com.zssq.job.task;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.AbstractOneOffElasticJob;
import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.IActivityTimeTaskService;

@Component
public class ActivityStatusJob extends AbstractOneOffElasticJob {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private IActivityTimeTaskService ativityTimeTaskService;

	@Override
	protected void process(JobExecutionMultipleShardingContext arg0) {
		try {
			log.info("--------activityStatusJobStart---------");
			ativityTimeTaskService.updateActivityStatusTiming();
			log.info("--------activityStatusJobEnd---------");
		} catch (BusinessException e) {
			log.error("ActivityStatusJob.process:",e);
		}

	}

}
