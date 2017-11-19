package com.zssq.mblog.vo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
    * @ClassName: ThreadPoolVo  
    * @Description: 线程池VO  
    * @author Mr.B  
    * @date 2017年5月22日  
    *
 */
public class ThreadPoolVo {

	/** 核心线程数 **/
	public static final int SERVICE_THREADPOOL_CORESIZE = 50;
	/** 最大线程数 **/
	public static final int SERVICE_THREADPOOL_MAXSIZE = 80;
	/** 最大空闲时间 **/
	public static final int SERVICE_THREADPOOL_ALIVETIME = 20;
	/** 缓存队列大小 **/
	public static final int SERVICE_THREADPOOL_QUEUESIZE = 30;
	
	/**
	 * 创建线程池
	 */
	public static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
			SERVICE_THREADPOOL_CORESIZE,
			SERVICE_THREADPOOL_MAXSIZE, 
			SERVICE_THREADPOOL_ALIVETIME, 
			TimeUnit.SECONDS,
			new ArrayBlockingQueue<Runnable>(SERVICE_THREADPOOL_QUEUESIZE),
			new ThreadPoolExecutor.CallerRunsPolicy());
	
	/**
	 * 
	    * @Title: execute  
	    * @Description: 创建执行类型
	    * @param command
		* @return void    返回类型
	 */
	public static void execute(Runnable command){
		threadPool.execute(command);
	}
}
