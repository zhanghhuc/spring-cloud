package com.zssq.job.task;

import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.AbstractOneOffElasticJob;
import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.zssq.constants.ElasticJobConstants;
import com.zssq.constants.StatisticMongoDBConstants;
import com.zssq.service.IStatisticService;

@Component
public class StatisticMongoDBTaskJob extends AbstractOneOffElasticJob {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private IStatisticService statisticService;
	
	@Override
	protected void process(JobExecutionMultipleShardingContext context){
		try {
			//获取分片参数
	    	Map<Integer,String> items = context.getShardingItemParameters();
	    	
	    	if(ElasticJobConstants.A.equals(items.get(0))){ //使用0分片处理数据
	    		System.out.println("开启定时任务" + new Date());
		    	String[] values = new String[]{StatisticMongoDBConstants.HOMEPAGE,StatisticMongoDBConstants.NEWS,StatisticMongoDBConstants.ACTIVITY,StatisticMongoDBConstants.MARROW,StatisticMongoDBConstants.HOTSPOT,StatisticMongoDBConstants.DYNAMIC,StatisticMongoDBConstants.TOP,StatisticMongoDBConstants.DETAIL,StatisticMongoDBConstants.TEAM,StatisticMongoDBConstants.PEOPLE};
		    	
		    	String[] keys = new String[1];
		        keys[0] = "type";
		        int num = -1; //全部查询
		    	
		        boolean result = false;
		        
		        for (String v : values) {
		        	String[] value = new String[1];
		        	value[0] = v;
		        	
		        	result = statisticService.addMongoDBVisit(keys, value, num);
		        	
		        	if(result){
		            	log.info("StatisticMongoDBTaskJob.job1:执行结束,成功,type=" + value[0] + ",时间" + new Date());
		        	}else{
		        		log.info("StatisticMongoDBTaskJob.job1:执行结束,失败,type=" + value[0] + ",时间" + new Date());
		        	}
				}
	    	}
        
		} catch (Exception e) {
			log.error("StatisticMongoDBTaskJob.process:",e);
		}
	} 
} 