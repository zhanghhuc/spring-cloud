package com.zssq.service.sync;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zssq.constants.MblogConstants;
import com.zssq.dao.mapper.MblogNumMapper;
import com.zssq.kafka.KafkaService;
import com.zssq.qo.sync.MblogNumVo;

/**
 * 
    * @ClassName: MblogNumService  
    * @Description: 微博数目异步修改   微博：收藏，评论，点赞； 评论：回复，点赞； 回复：点赞
    * @author Mr.B  
    * @date 2017年3月29日  
    *
 */
@SuppressWarnings("all") 
@Service("mblogNumService")
public class MblogNumService implements KafkaService{

	/** log对象 **/
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private MblogNumMapper mblogNumMapper; // 微博数目Mapper
	
	@Override
	public void invokeService(ConsumerRecord record) throws Exception {
		try{
			if(null != record.value()){
				log.info(">>>>>>>>>>>>>>>>>>>Start Mblog topic num>>>>>>>>>>>>>>>>>>>>>>>>>");
				// 获取参数
				MblogNumVo vo = JSONObject.toJavaObject(JSON.parseObject((String)record.value()), MblogNumVo.class);
				// 创建参数
				Map<String,Object> paramsMap = new HashMap<String,Object>();
				// 获取当前时间
				long time = new Date().getTime();
				paramsMap.put("modifyTime", time);
				switch(vo.getActionType()){
					case MblogConstants.MBLOG_ACTION_COLLECT:{ // 收藏
						log.info("*****************Mblog Collect************************");
						paramsMap.put("subjectCode", vo.getMblogCode());
						paramsMap.put("num",vo.getBaseNum()*1);
						// 更新收藏数
						mblogNumMapper.updateColNumByCode(paramsMap);
						break;
					}
					case MblogConstants.MBLOG_ACTION_COMMENT:{ // 评论
						log.info("*****************Mblog Comment************************");
						int count = 1;
						/*if(vo.getBaseNum() < 0){
							count = mblogNumMapper.getReplyNumByCode(vo.getCommentCode());
							count += 1;
						}*/
						paramsMap.put("subjectCode", vo.getMblogCode());
						paramsMap.put("num",vo.getBaseNum()*count);
						// 更新评论数
						mblogNumMapper.updateComNumByCode(paramsMap);
						break;
					}
					case MblogConstants.MBLOG_ACTION_REPLY:{ // 回复
						log.info("*****************Mblog reply************************");
						paramsMap.put("num",vo.getBaseNum()*1);
						// 更新回复数
						paramsMap.put("subjectCode", vo.getCommentCode());
						mblogNumMapper.updateReplyNumByCode(paramsMap);
						// 更新评论数
						/*paramsMap.put("subjectCode", vo.getMblogCode());
						mblogNumMapper.updateComNumByCode(paramsMap);*/
						break;
					}
					case MblogConstants.MBLOG_ACTION_MBLOG_PRAISE:{ // 微博点赞
						log.info("*****************Mblog Praise************************");
						// 设置参数信息
						paramsMap.put("subjectCode", vo.getMblogCode());
						paramsMap.put("num",vo.getBaseNum()*1);
						// 更新点赞数
						mblogNumMapper.updatePraNumByCode(paramsMap);
						break;
					}
					case MblogConstants.MBLOG_ACTION_COMMENT_PRAISE:{ // 评论点赞
						log.info("*****************Mblog Comment Praise************************");
						// 设置参数信息
						paramsMap.put("subjectCode", vo.getCommentCode());
						paramsMap.put("num",vo.getBaseNum()*1);
						// 更新点赞数
						mblogNumMapper.updatePraNumByCode(paramsMap);
						break;
					}
					case MblogConstants.MBLOG_ACTION_REPLY_PRAISE:{ // 回复点赞
						log.info("*****************Mblog Reply Praise************************");
						// 设置参数信息
						paramsMap.put("subjectCode", vo.getReplyCode());
						paramsMap.put("num",vo.getBaseNum()*1);
						// 更新点赞数
						mblogNumMapper.updatePraNumByCode(paramsMap);
						break;
					}
					default:{
						log.info("executeNum:线程处理业务ActionType数据无意义");
					}
				}
				log.info(">>>>>>>>>>>>>>>>>>>End Mblog topic num>>>>>>>>>>>>>>>>>>>>>>>>>");
			}
			
		}catch(Exception e){
			throw new Exception("MblogNumService.invokeService:操作失败",e);
		}
	}
}
