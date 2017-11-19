package com.zssq.service.impl.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zssq.dao.mapper.UserMsgSubscribeMapper;
import com.zssq.dao.pojo.UserMsgSubscribe;
import com.zssq.kafka.KafkaService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.util.RedisUtil;
import com.zssq.utils.DateUtils;

/**
 * 
 * @ClassName: MsgAddSubscribeServiceImpl  
 * @Description: 添加订阅通知  
 * @author YDB  
 * @date 2017年4月12日  
 *
 */
@Service("msgAddSubscribeService")
public class MsgAddSubscribeServiceImpl  implements KafkaService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());	

	@Autowired
	private UserMsgSubscribeMapper userMsgSubscribeMapper; 
	@Autowired
	private RedisUtil redisUtil;
	
	
	@Override
	public void invokeService(ConsumerRecord record) throws Exception {
		
		String value=(String) record.value();
		try {
	
			JSONObject json=JSONObject.parseObject(value);
			
			UserMsgSubscribe um=new UserMsgSubscribe();
			um.setCreatTime(DateUtils.getTime());
			um.setIsDelete((byte)0);
			um.setSubCode(UUIDHelper.getUUID());
	
			um.setOrgCode(json.getString("orgCode"));
			//um.setOrgLevel(Byte.parseByte(json.getString("orgLevel")));
			um.setSubType(Byte.parseByte(json.getString("subType")));
			um.setSubUserCode(json.getString("subUserCode"));
			um.setTenantCode(json.getString("tenantCode"));
			um.setUserCode(json.getString("userCode"));
			
			int state=userMsgSubscribeMapper.insert(um);
			if(state==1){
				redisUtil.addNumber(json.getString("userCode"), 6, 1);
			}
		
		} catch (Exception e) {
			logger.error("MsgKafka-添加订阅通知-入参:"+value,e);
			
		}
	}

}
