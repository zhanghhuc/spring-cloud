package com.zssq.service.impl;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.zssq.constants.MblogConstants;
import com.zssq.dao.mapper.MblogAtMapper;
import com.zssq.dao.mapper.MblogCollectMapper;
import com.zssq.dao.mapper.MblogCommentMapper;
import com.zssq.dao.mapper.MblogForwardMapper;
import com.zssq.dao.mapper.MblogInfoMapper;
import com.zssq.dao.mapper.MblogNumMapper;
import com.zssq.dao.mapper.MblogPraiseMapper;
import com.zssq.dao.mapper.MblogReplyMapper;
import com.zssq.dao.mapper.MblogResourceMapper;
import com.zssq.dao.mapper.MblogSubscribeMapper;
import com.zssq.dao.mapper.MblogTopicMapper;
import com.zssq.kafka.core.KafkaProducerTemplate;
import com.zssq.qo.sync.MblogForwardVo;
import com.zssq.qo.sync.MblogNumVo;

/**
 * 
    * @ClassName: BaseService  
    * @Description: 基础Service  
    * @author Mr.B  
    * @date 2017年6月2日  
    *
 */
@SuppressWarnings("all")
public class BaseService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** log对象 **/
	public Logger log = Logger.getLogger(this.getClass());
	
	// 微博信息
	@Autowired
	protected MblogInfoMapper mblogInfoMapper;
	// 微博资源
	@Autowired
	protected MblogResourceMapper mblogResourceMapper;
	// 微博AT
	@Autowired
	protected MblogAtMapper mblogAtMapper;
	// 微博话题
	@Autowired
	protected MblogTopicMapper mblogTopicMapper;
	// 微博点赞
	@Autowired
	protected MblogPraiseMapper mblogPraiseMapper;
	// 微博收藏
	@Autowired
	protected MblogCollectMapper mblogCollectMapper;
	// 微博订阅
	@Autowired
	protected MblogSubscribeMapper mblogSubscribeMapper;
	// 微博数量
	@Autowired
	protected MblogNumMapper mblogNumMapper;
	// 微博回复
	@Autowired
	protected MblogReplyMapper mblogReplyMapper;
	// 微博评论
	@Autowired
	protected MblogCommentMapper mblogCommentMapper;
	// 微博转发
	@Autowired
	protected MblogForwardMapper mblogForwardMapper;
	// kafka模板
	@Autowired
	private KafkaProducerTemplate producerTeplate;
	
	/**
	 * 
	    * @Title: ownSendKafkaNumMsg  
	    * @Description: 修改数量信息
	    * @param mblogCode	微博CODE
	    * @param commentCode 评论CODE	
	    * @param replyCode	回复CODE
	    * @param actionType	动作类型
	    * @param baseNum	基数 1/-1
		* @return void    返回类型
	 */
	public void ownSendKafkaNumMsg(String mblogCode,String commentCode,String replyCode,Byte actionType,Integer baseNum){
		try{
			MblogNumVo vo = new MblogNumVo();
			vo.setActionType(actionType);
			vo.setBaseNum(baseNum);
			vo.setMblogCode(mblogCode);
			vo.setCommentCode(commentCode);
			vo.setReplyCode(replyCode);
			//executeNum(vo);
			producerTeplate.send(MblogConstants.MBLOG_KAFKA_TOPIC_NUM, JSONObject.toJSONString(vo));
		}catch(Exception e){
			log.error("kafka消息出现异常-更新相关数量",e);
		}
	}
	
	/**
	 * 
	    * @Title: ownSendKafkaForwardMsg  
	    * @Description:  更新转发信息
	    * @param curMblogCode
	    * @param forMblogCode
		* @return void    返回类型
	 */
	public void ownSendKafkaForwardMsg(String curMblogCode,String forMblogCode){
		try{
			MblogForwardVo vo = new MblogForwardVo();
			vo.setCurMblogCode(curMblogCode);
			vo.setForMblogCode(forMblogCode);
			//executeForward(vo);
			producerTeplate.send(MblogConstants.MBLOG_KAFKA_TOPIC_FORWARD, JSONObject.toJSONString(vo));
		}catch(Exception e){
			log.error("kafka消息出现异常-更新转发信息",e);
		}
	}
}
