//package com.zssq.job.task;
//
//import java.util.Date;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Properties;
//import java.util.Set;
//
//import org.apache.log4j.Logger;
//import org.springframework.stereotype.Component;
//
//import com.dangdang.ddframe.job.api.AbstractOneOffElasticJob;
//import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
//import com.zssq.constants.ElasticJobConstants;
//import com.zssq.utils.PropertiesUtil;
//
//import redis.clients.jedis.Jedis;
//
//@Component
//public class SSOWebTaskJob extends AbstractOneOffElasticJob {
//	
//	private Properties propsRedis = new PropertiesUtil("ssoRedis.properties");
//	
//	private Logger log = Logger.getLogger(this.getClass());
//	
//	@Override
//	protected void process(JobExecutionMultipleShardingContext context) {
//		//获取分片参数
//    	Map<Integer,String> items = context.getShardingItemParameters();
//    	
//    	if(ElasticJobConstants.A.equals(items.get(0))){ //使用0分片处理数据
//    		System.out.println("开启定时任务" + new Date());
//        	Jedis redis = new Jedis(propsRedis.getProperty("webRedisIP"), Integer.valueOf(propsRedis.getProperty("webRedisPort")));  //本地redis
//        	redis.auth(propsRedis.getProperty("webRedisPwd"));
//        	log.info("TaskJob.job1:Server is running:" + redis.ping());
//        	
//        	Set<String> set = redis.keys("*");
////    		System.out.println("获取目前redis中都有哪些key");
//    		Iterator it = set.iterator();
//    		while(it.hasNext()){ //循环删除所有key
//    			String key = (String) it.next();
//    			String value = redis.get(key);
//    			log.info("TaskJob.job1:web端redis中的key" + key);
//    			log.info("TaskJob.job1:web端redis中的value" + value);
//    			
//    			
//    			redis.del(key);
//    		}
//    	}
//		
//	} 
//} 