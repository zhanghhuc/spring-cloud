package com.zssq.job.task;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dangdang.ddframe.job.api.AbstractOneOffElasticJob;
import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.zssq.constants.RelationConstants;
import com.zssq.dao.pojo.RelationHot;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zssq.service.RelationHotService;

/**
 * 
 * @ClassName: HotProduce  
 * @Description: 热点权重更新定时任务  
 * @author sry  
 * @date 2017年4月20日  
 *
 */
@Component
public class HotWeightTaskJob extends AbstractOneOffElasticJob {
	
	@Autowired
	private RelationHotService relationHotService;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	private static Long getBeforeSevenDayTime(){
		Long start = 0l;
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH,-7);
		start = calendar.getTimeInMillis();
		return start;
	}

	@Override
	protected void process(JobExecutionMultipleShardingContext arg0) {
		try{
			log.info("关系热点中热度更新HotWeightTaskJob start");
			//1,删除7天前热点
			Long beforeSevenDayTime =getBeforeSevenDayTime();
			relationHotService.deleteBeforeSevenDayHotData(beforeSevenDayTime);
			Long queryTime = new Date().getTime();
			//2,分页获取前七天的热点(subject) 计算热度 热度值=点赞量*60%+评论数*20%+收藏数*20%
			Map<String, Object> map = new HashMap<>();
			map.put("queryTime",queryTime);
			int showCount = relationHotService.selectCountByJob(map);
			if(showCount>0){
				int totalPage = showCount/RelationConstants.JOB_UPDATE_PER_NUM+1;
				int pageSize = RelationConstants.JOB_UPDATE_PER_NUM;
				for(int page=0;page<totalPage;page++){
					map.put("limitStart", page*pageSize);
					map.put("limitCount", pageSize);
					List<RelationHot> rhList = relationHotService.selectHotByJob(map);
					if(rhList!=null&&rhList.size()>0){
						relationHotService.updateHotWeight(rhList);
					}
	                

				}
			}
			log.info("关系热点中热度更新HotWeightTaskJob end");
		}catch(Exception e){
			log.error("HotWeightTaskJob.process:",e);
		}
	}
}
