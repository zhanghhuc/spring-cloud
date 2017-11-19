package com.zssq.job.task;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.AbstractOneOffElasticJob;
import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.zssq.service.DiskFileService;

/**
 * 
 * @ClassName: DiskTaskJob  
 * @Description: 网盘定时任务  
 * @author YDB  
 * @date 2017年6月2日  
 *
 */

public class DiskTaskJob  extends AbstractOneOffElasticJob{

	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private DiskFileService diskServer;
	
	
	@Override
	protected void process(JobExecutionMultipleShardingContext arg0) {
		
		
		log.info("网盘定时任务---->删除放入回收站十天的数据");
		
		diskServer.tenDaysDelete();
		
		log.info("网盘定时任务结束--->");
	}

	
	
	
	
}