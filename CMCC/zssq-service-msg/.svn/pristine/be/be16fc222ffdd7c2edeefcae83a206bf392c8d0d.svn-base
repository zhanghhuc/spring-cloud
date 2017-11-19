package com.zssq.service.impl.kafka;

import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.dao.mapper.UserMsgNoticeMapper;
import com.zssq.dao.pojo.UserMsgNotice;
import com.zssq.kafka.KafkaService;
import com.zssq.util.RedisUtil;

/**
 * 
 * @ClassName: MsgAddNoticeServiceImpl  
 * @Description: 添加notice消息  
 * @author YDB  
 * @date 2017年4月12日  
 *
 */
@Service("msgAddNoticeService")
public class MsgAddNoticeServiceImpl implements  KafkaService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());	
	
	@Autowired
	private UserMsgNoticeMapper userMsgNoticeMapper;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@SuppressWarnings("rawtypes")
	@Override
	public void invokeService(ConsumerRecord record) throws Exception {
		
		String value=(String) record.value();
		try {
		
			JSONArray jsonList=JSONArray.parseArray(value);
			List<UserMsgNotice> list=new ArrayList<>();
			
			for(int i=0;i<jsonList.size();i++){
				UserMsgNotice temp=new UserMsgNotice();
				temp=JSONObject.parseObject(jsonList.getString(i), UserMsgNotice.class);		
				list.add(temp);
			}
			
			
			if(list==null||list.size()==0){
				return;
			}
			userMsgNoticeMapper.saveNoticeList(list);
			
			//添加消息数量
			for (int i = 0; i < list.size(); i++) {
				redisUtil.addNumber(list.get(i).getUserCode(),8,1);
			}
		
		} catch (Exception e) {
			logger.error("MsgKafka-添加系统通知-参数:"+value,e);
		}
		
	}

}
