package com.zssq.vote.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolVote {
	// 可缓存线程池
	private static ExecutorService cachedThreadPool;
	// 固定大小线程池
	private static ExecutorService fixedThreadPool;
	
	public static void init(){
		cachedThreadPool = Executors.newCachedThreadPool();
		// 默认创建固定大小为50个的线程池
		fixedThreadPool = Executors.newFixedThreadPool(50);
	}
	
	public static void addSingleTask(int voteId) {
		cachedThreadPool.execute(new ExecuteSingleTaskThread(voteId));
	}
	
	public static void addMultiTask(int startNum, int endNum){
		fixedThreadPool.execute(new ExecuteMultiTaskThread(startNum, endNum));
	}
	
	public static int getActCount(){
		ThreadPoolExecutor tpe = ((ThreadPoolExecutor) cachedThreadPool);
		return tpe.getActiveCount();
	}
	
	public static int getActiveCount() {
		ThreadPoolExecutor tpe = ((ThreadPoolExecutor) fixedThreadPool);
		return tpe.getActiveCount();
	}
	
	public static boolean isTerminated() {
		return fixedThreadPool.isTerminated();
	}

	public static void shutdown() {
		fixedThreadPool.shutdown();
	}
	
}
