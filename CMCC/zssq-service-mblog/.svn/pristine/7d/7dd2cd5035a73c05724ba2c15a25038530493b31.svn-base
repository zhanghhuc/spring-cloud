package com.zssq.service.sync;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zssq.dao.mapper.MblogForwardMapper;
import com.zssq.dao.mapper.MblogNumMapper;
import com.zssq.dao.pojo.MblogForward;
import com.zssq.kafka.KafkaService;
import com.zssq.qo.sync.MblogForwardVo;
import com.zssq.utils.StringTools;

/**
 * 
    * @ClassName: MblogForwardService  
    * @Description: 微博转发kafka信息  
    * @author Mr.B  
    * @date 2017年3月31日  
    *
 */
@SuppressWarnings("all") 
@Service("mblogForwardService")
public class MblogForwardService implements KafkaService{

	/** log对象 **/
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private MblogForwardMapper mblogForwardMapper;
	
	@Autowired
	private MblogNumMapper mblogNumMapper; // 微博数目Mapper
	
	@Override
	public void invokeService(ConsumerRecord record) throws Exception {
		try{
			if(null != record.value()){
				log.info(">>>>>>>>>>>>>>>>>>>Start Mblog topic forward>>>>>>>>>>>>>>>>>>>>>>>>>");
				// 获取参数
				MblogForwardVo vo = JSONObject.toJavaObject(JSON.parseObject((String)record.value()), MblogForwardVo.class);
				if(StringTools.isNotEmpty(vo.getCurMblogCode()) && StringTools.isNotEmpty(vo.getForMblogCode())){
					MblogForward forward = new MblogForward();
					forward.setCurMblogCode(vo.getCurMblogCode());
					String sourceMblogCode = mblogForwardMapper.queryForwardInfo(vo.getForMblogCode());
					if(StringTools.isNotEmpty(sourceMblogCode)){
						forward.setSourceMblogCode(sourceMblogCode+","+vo.getForMblogCode());
					}else{
						forward.setSourceMblogCode(vo.getForMblogCode());
					}
					// 插入转发记录
					if(mblogForwardMapper.insert(forward) < 0){
						log.info("MblogForwardService.invokeService:kafka处理业务插入转发记录失败");
					}
					// 更新转发次数
					List<String> codes = Arrays.asList(forward.getSourceMblogCode().split(","));
					Map<String,Object> paramsMap = new HashMap<String,Object>();
					paramsMap.put("modifyTime", new Date().getTime());
					paramsMap.put("subjectCodes", codes);
					if(mblogNumMapper.batchUpdateForNumByCode(paramsMap) < 0){
						log.info("MblogForwardService.invokeService:kafka处理业务更新转发数量失败");
					}
				}
				log.info(">>>>>>>>>>>>>>>>>>>End Mblog topic forward>>>>>>>>>>>>>>>>>>>>>>>>>");
			}
			
		}catch(Exception e){
			throw new Exception("MblogForwardService.invokeService:操作失败",e);
		}
		
	}
}
