package com.zssq.util;

import java.lang.reflect.Constructor;
import java.util.Properties;

import com.dangdang.ddframe.job.api.ElasticJob;
import com.dangdang.ddframe.job.api.JobConfiguration;
import com.dangdang.ddframe.job.schedule.JobController;
import com.dangdang.ddframe.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.reg.zookeeper.ZookeeperRegistryCenter;
import com.zssq.utils.PropertiesUtil;

public class JobUtil {
	private static Properties props = new PropertiesUtil("elastic-job.properties");
	
	// 定义Zookeeper注册中心配置对象
    private static ZookeeperConfiguration zkConfig = new ZookeeperConfiguration(props.getProperty("serverLists"), props.getProperty("namespace"), Integer.valueOf(props.getProperty("baseSleepTimeMilliseconds")), Integer.valueOf(props.getProperty("maxSleepTimeMilliseconds")), Integer.valueOf(props.getProperty("maxRetries")));
    
    // 定义Zookeeper注册中心
    private static CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(zkConfig);
    
    /**
     * 添加定时任务
     * @param jobName 任务名
     * @param jobClass 执行的任务类，包全名
     * @param shardingTotalCount 分片总数
     * @param cron quartz表达式
     */
    public static void runCustomJob(String jobName,String jobClass,int shardingTotalCount,String cron){
    	try {
    		//利用反射实例化类
	    	Class cls = Class.forName(jobClass); 
	    	Constructor ct = cls.getConstructor(null); 
	//    	Object obj = ct.newInstance(null).; 
	    	JobConfiguration jobConfig1 = new JobConfiguration(jobName, (Class<? extends ElasticJob>) ct.newInstance(null).getClass(), shardingTotalCount, cron);
	    	
	    	 // 连接注册中心
	        regCenter.init();
	        // 启动作业1
	        new JobController(regCenter, jobConfig1).init();
    
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public static void main(final String[] args) {
    	
    	JobUtil.runCustomJob("testOnce", "com.zssq.job.demo.OneOffElasticJob", 3, "0/10 * * * * ?");
    	
    }
}
