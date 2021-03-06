package com.zssq.vote.thread;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.zssq.constants.MsgTopicConstants;
import com.zssq.constants.VoteConstants;
import com.zssq.dao.pojo.VoteComment;
import com.zssq.dao.pojo.VoteCommentReply;
import com.zssq.dao.pojo.VoteInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.kafka.core.KafkaProducerTemplate;
import com.zssq.model.MessageCommentAddModel;
import com.zssq.service.IVoteService;
import com.zssq.util.SpringContextUtil;

/**
 * @ClassName SendCommentOrReplyMsgThread
 * @Description 发送 评论或回复消息
 * @author LiuYunLong
 * @date 2017年4月13日 下午5:40:27
 * @version 1.0
 * @since JDK 1.7
 */
public class SendCommentOrReplyMsgThread implements Runnable{

	private Logger log = Logger.getLogger(this.getClass());
	
	
	// 发布信息传入实体
	private Object infoObj;
	// 发布信息类型（1-应用(例如：投票)； 2-评论； 3-回复）
	private String infoType;
	// 发布信息Code
	private String infoCode;
	// 投票信息Code
	private String voteInfoCode;
	
	private IVoteService voteService = SpringContextUtil.getBean(IVoteService.class);
	
	@SuppressWarnings("rawtypes")
	private KafkaProducerTemplate producerTeplate  = SpringContextUtil.getBean(KafkaProducerTemplate.class);
	
	/**发送类型：5-投票-评论*/
	public static final int MSG_TYPE_5 = 5;
	
	/**发送类型：6-投票-评论 - 回复*/
	public static final int MSG_TYPE_6 = 6;
	
	
	public SendCommentOrReplyMsgThread() {
		
	}
	
	public SendCommentOrReplyMsgThread(String voteInfoCode,Object infoObj,String infoType,String infoCode) {
		this.infoObj = infoObj;
		this.infoType = infoType;
		this.infoCode = infoCode;
		this.voteInfoCode = voteInfoCode;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		
		try {
			MessageCommentAddModel model = null;
			if(VoteConstants.INFO_TYPE_2.equals(infoType)){
				VoteComment voteComment = (VoteComment)infoObj;
				VoteInfo voteInfo = voteService.getVoteInfoByCode(voteComment.getVoteInfoCode(), voteComment.getTenantCode());
				if(null != voteInfo && VoteConstants.SPONSOR_TYPE_PERSON.equals(voteInfo.getSponsorType())){
					model = new MessageCommentAddModel();
					model.setUserCode(voteInfo.getSponsorCode());             //被评论用户code
					model.setCommentUserCode(voteComment.getCommenterCode()); //评论人userCode
					model.setPublicCode(voteComment.getCommenterCode());      //评论人userCode
					model.setType(MSG_TYPE_5);                                //评论类型
					model.setOriginalContent(voteInfo.getTitle());            //原文内容
					model.setOriginalCode(voteInfo.getCode());                //原文Code
					model.setCommentContent(voteComment.getContent());        //评论内容
					model.setTenantCode(voteComment.getTenantCode());         //租户Code
					model.setOrgCode(voteComment.getOrgCode());               //组织机构编号
					model.setCommentCode(infoCode);                           //评论Code
				}
				
			}
			
			if(VoteConstants.INFO_TYPE_3.equals(infoType)){
				VoteCommentReply voteCommentReply = (VoteCommentReply)infoObj;
				VoteComment voteComment = voteService.getCommentByCode(voteCommentReply.getCommentCode(), voteCommentReply.getTenantCode());
				VoteInfo voteInfo = voteService.getVoteInfoByCode(this.voteInfoCode, voteCommentReply.getTenantCode());
				if(null != voteComment){
					model = new MessageCommentAddModel();
					model.setUserCode(voteCommentReply.getQuestionerCode());
					model.setCommentUserCode(voteCommentReply.getReplierCode());
					model.setPublicCode(voteCommentReply.getReplierCode());  //回复人userCode
					model.setType(MSG_TYPE_6); 
					model.setOriginalContent(voteInfo.getTitle());           //投票的title
					model.setOriginalCode(voteInfo.getCode());               //投票的code
					model.setCommentContent(voteCommentReply.getContent());
					model.setTenantCode(voteCommentReply.getTenantCode());
					model.setOrgCode(voteCommentReply.getOrgCode());
					model.setCommentCode(infoCode);
				}
			}
		
			if (null != model) {
				String data = JSONObject.toJSONString(model);
				producerTeplate.send(MsgTopicConstants.TOPIC_COMMENT, data);
			}
		
		} catch (BusinessException e) {
			log.error("SendCommentOrReplyMsgThread.run:发送评论或回复提醒消息出错：", e);
		}
		
		
	}

}
