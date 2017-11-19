package com.zssq.service.impl.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zssq.dao.mapper.UserMsgPraiseMapper;
import com.zssq.dao.pojo.UserMsgPraise;
import com.zssq.kafka.KafkaService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.util.RedisUtil;
import com.zssq.utils.DateUtils;

/**
 * 
 * @ClassName: MsgAddPraiseServiceImpl  
 * @Description: 添加消息点赞  
 * @author YDB  
 * @date 2017年4月12日  
 *
 */
@Service("msgAddPraiseService")
public class MsgAddPraiseServiceImpl implements KafkaService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());	
	
	@Autowired
	private UserMsgPraiseMapper userMsgPraiseMapper;
	@Autowired
	private RedisUtil redisUtil;
	
	
	@Override
	public void invokeService(ConsumerRecord record) throws Exception {
		
		
		String value=(String) record.value();
		try {
		
			JSONObject json=JSONObject.parseObject(value);
			
			UserMsgPraise UserMsgPraise=new UserMsgPraise();
			UserMsgPraise.setIsDelete((byte)0);
			UserMsgPraise.setCreateTime(DateUtils.getTime());
			UserMsgPraise.setPraiseCode(UUIDHelper.getUUID());
			
			UserMsgPraise.setUserCode(json.getString("userCode"));
			UserMsgPraise.setPraiseUserCode(json.getString("praiseUserCode"));
			UserMsgPraise.setPraiseType(Byte.parseByte(json.getString("praiseType")));
			UserMsgPraise.setOrgCode(json.getString("orgCode"));
			UserMsgPraise.setTenantCode(json.getString("tenantCode"));
			//UserMsgPraise.setOrgLevel(Byte.parseByte(json.getString("orgLeve")));
			UserMsgPraise.setContent(json.getString("content"));
			UserMsgPraise.setOriginalCode(json.getString("originalCode"));
			int state=userMsgPraiseMapper.insert(UserMsgPraise);
			if(state==1){
				redisUtil.addNumber(json.getString("userCode"),3, 1);
			}
		
		} catch (Exception e) {
			logger.error("MsgKafka-添加消息点赞-入参:"+value,e);
		}
		
	}
	
	
	
	

}
