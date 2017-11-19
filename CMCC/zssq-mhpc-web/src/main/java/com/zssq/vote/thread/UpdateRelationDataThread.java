package com.zssq.vote.thread;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.zssq.constants.RelationConstants;
import com.zssq.kafka.core.KafkaProducerTemplate;
import com.zssq.util.SpringContextUtil;
import com.zssq.utils.DateUtils;
import com.zssq.vo.RelationDataVO;

public class UpdateRelationDataThread implements Runnable{

	private Logger log = Logger.getLogger(this.getClass());
	
	//内容编号
	private String subjectCode;
	
	//修改数量
	private Integer updateNumber;
	
	//更改类型
	private Byte updateClass;
	
	@SuppressWarnings("rawtypes")
	private KafkaProducerTemplate producerTeplate  = SpringContextUtil.getBean(KafkaProducerTemplate.class);

	public UpdateRelationDataThread() {
		
	}
	
	public UpdateRelationDataThread(String subjectCode,Integer updateNumber,Byte updateClass){
		this.subjectCode = subjectCode;
		this.updateNumber = updateNumber;
		this.updateClass = updateClass;
	}
	
	@SuppressWarnings({ "unchecked"})
	@Override
	public void run() {
		RelationDataVO relationDataVO = null;
		try{
			relationDataVO = new RelationDataVO();
			relationDataVO.setModifyTime(DateUtils.getFormatDateLong());
			relationDataVO.setSubjectCode(this.subjectCode);
			relationDataVO.setUpdateClass(this.updateClass);
			relationDataVO.setUpdateNumber(this.updateNumber);
			if(null != relationDataVO){
				String data = JSONObject.toJSONString(relationDataVO);
				producerTeplate.send(RelationConstants.RELATION_TOPIC, data);
			}
			
		}catch(Exception e){
			log.error("UpdateRelationDataThread 更改动态数量信息出错",e);
		}
		
	}
	
	
}
