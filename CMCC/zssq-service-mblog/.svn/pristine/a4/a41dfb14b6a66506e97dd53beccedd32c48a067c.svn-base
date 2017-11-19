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
import com.zssq.qo.sync.MblogEssenceVo;
import com.zssq.utils.StringTools;

/**
 * 
    * @ClassName: MblogEssenceService  
    * @Description: 微博置精数目修改异步操作Service  
    * @author Mr.B  
    * @date 2017年3月29日  
    *
 */
@SuppressWarnings("all")
@Service("mblogEssenceService")
public class MblogEssenceService implements KafkaService{

	/** log对象 **/
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private MblogNumMapper mblogNumMapper;
	
	@Override
	public void invokeService(ConsumerRecord record) throws Exception {
		try{
			if(null != record.value()){
				log.info(">>>>>>>>>>>>>>>>>>>Start Mblog topic Essence>>>>>>>>>>>>>>>>>>>>>>>>>");
				// 获取参数
				MblogEssenceVo vo = JSONObject.toJavaObject(JSON.parseObject((String)record.value()), MblogEssenceVo.class);
				String mblogCode = vo.getMblogCode();
				Byte isEssence = vo.getIsEssence();
				Byte scope = vo.getScope();
				if(StringTools.isNotEmpty(mblogCode)){
					// 封装参数
					Map<String,Object> paramsMap = new HashMap<String, Object>();
					paramsMap.put("modifyTime", new Date().getTime());
					paramsMap.put("num", isEssence == 0 ? MblogConstants.MBLOG_NUM_SUB : MblogConstants.MBLOG_NUM_ADD);
					paramsMap.put("subjectCode", mblogCode);
					switch(scope){
						case MblogConstants.MBLOG_ESSENCE_SCOPE_GROUP:{ // 集团
							log.info("*****************Mblog Group************************");
							mblogNumMapper.updateGessNumByCode(paramsMap);
							break;
						}
						case MblogConstants.MBLOG_ESSENCE_SCOPE_PROVINCE:{ // 省级
							log.info("*****************Mblog PROVINCE************************");
							mblogNumMapper.updatePessNumByCode(paramsMap);
							break;
						}
						case MblogConstants.MBLOG_ESSENCE_SCOPE_CITY:{ // 市级
							log.info("*****************Mblog CITY************************");
							mblogNumMapper.updateCessNumByCode(paramsMap);
							break;
						}
						case MblogConstants.MBLOG_ESSENCE_SCOPE_TEAM:{ // 班组
							log.info("*****************Mblog TEAM************************");
							mblogNumMapper.updateTessNumByCode(paramsMap);
							break;
						}
						default:{
							log.info("MblogEssenceService.invokeService:kafka处理业务更新置精数量失败");
						}
					}
				}else{
					log.info("MblogEssenceService.invokeService:kafka处理业务更新置精数量失败");
				}
				log.info(">>>>>>>>>>>>>>>>>>>Start Mblog topic Essence>>>>>>>>>>>>>>>>>>>>>>>>>");
			}
			
		}catch(Exception e){
			throw new Exception("MblogEssenceService.invokeService:修改置精数目操作失败",e);
		}
	}

}
