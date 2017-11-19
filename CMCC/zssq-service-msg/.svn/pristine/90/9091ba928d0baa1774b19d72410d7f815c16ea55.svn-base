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
import com.zssq.dao.mapper.UserMsgATMapper;
import com.zssq.dao.pojo.UserMsgAT;
import com.zssq.kafka.KafkaService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.util.RedisUtil;
import com.zssq.utils.DateUtils;

/**
 * 
 * @ClassName: MsgAddAtServiceImpl  
 * @Description: 添加At消息  
 * @author YDB  
 * @date 2017年4月12日  
 *
 */
@Service("msgAddAtService")
public class MsgAddAtServiceImpl implements  KafkaService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());	

	
	@Autowired
	private  UserMsgATMapper	userMsgATMapper;
	@Autowired
	private RedisUtil redisUtil;
	
	@SuppressWarnings("rawtypes")
	@Override
	public void invokeService(ConsumerRecord record) throws Exception {
		
		String value=(String) record.value();
		
		try {
			
			JSONObject json=JSONObject.parseObject(value);
			
			JSONArray userArray=json.getJSONArray("userList");
			List<UserMsgAT> userList=new ArrayList<>();
			
			// 需要缓存消息提醒的用户Code列表
			List<String> userCodeList = new ArrayList<String>();
			
			for (int i = 0; i < userArray.size(); i++) {
				
				JSONObject userMsgObj = userArray.getJSONObject(i);
				
				UserMsgAT userMsgAT=new UserMsgAT();
				userMsgAT.setAtCode(UUIDHelper.getUUID());
				userMsgAT.setCreateTime(DateUtils.getTime());
				userMsgAT.setIsDelete((byte)0);
				userMsgAT.setUserCode(userMsgObj.getString("userCode"));
				userMsgAT.setAtUserCode(userMsgObj.getString("atUserCode"));
				userMsgAT.setOrgCode(userMsgObj.getString("orgCode"));
				userMsgAT.setTenantCode(userMsgObj.getString("tenantCode"));
				userMsgAT.setAtType(Byte.parseByte(userMsgObj.getString("atType")));
				userMsgAT.setOriginalCode(userMsgObj.getString("originalCode"));
				//转发微博时候才会有一句话
				userMsgAT.setContent(userMsgObj.getString("content"));
				userList.add(userMsgAT);
				
				userCodeList.add(userMsgObj.getString("userCode"));
			}
			
			int state=userMsgATMapper.batchMsg(userList);
			
			// 判断消息入库是否成功
			if(state==1){
				// 添加用户消息数量
				for(String userCode :userCodeList){
					redisUtil.addNumber(userCode, 1, 1);
				}
			}
		
		} catch (Exception e) {
			logger.error("MsgKafka-添加at消息失败-入参:"+value,e);
			
		}
		
	}

}
