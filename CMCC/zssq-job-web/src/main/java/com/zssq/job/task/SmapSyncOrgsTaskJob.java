package com.zssq.job.task;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.AbstractOneOffElasticJob;
import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.zssq.service.ISmapService;
import com.zssq.util.SpringContextUtil;

/**
 * 
 * @ClassName SmapSyncOrgsTaskJob
 * @Description SMAP同步组织定时任务
 * @author lijie
 * @date 2017年6月6日 下午3:21:13
 * @version 1.0
 * @since JDK 1.7
 */
@Component
public class SmapSyncOrgsTaskJob extends AbstractOneOffElasticJob {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	private ISmapService smapService = SpringContextUtil.getBean(ISmapService.class);
	
	@Override
	protected void process(JobExecutionMultipleShardingContext context){
		
		log.info("----------SmapSyncOrgsTaskJob.process：SMAP同步组织定时任务开始----------");
		try {
			//调用同步接口同步数据
			smapService.syncUsers();
			
			log.info("----------SmapSyncOrgsTaskJob.process：SMAP同步组织定时任务结束----------");
		} catch (Exception e) {
			log.error("----------SmapSyncOrgsTaskJob.process：SMAP同步组织定时任务异常----------", e);
		}
	}

}
