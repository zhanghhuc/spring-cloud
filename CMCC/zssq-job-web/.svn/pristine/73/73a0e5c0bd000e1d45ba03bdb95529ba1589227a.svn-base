package com.zssq.job.demo;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.AbstractOneOffElasticJob;
import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;

@Component
public class OneOffElasticJob extends AbstractOneOffElasticJob{
 
    @Override
	public void process(JobExecutionMultipleShardingContext context) {
    	//获取分片参数
    	Map<Integer,String> items = context.getShardingItemParameters();
    	
    	System.out.println(items.get(0));
    	System.out.println(items.get(1));
    	System.out.println(items.get(2));
    	
        System.out.println(new Date());
    }
}