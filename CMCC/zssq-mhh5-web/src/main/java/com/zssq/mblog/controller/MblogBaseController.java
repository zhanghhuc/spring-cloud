package com.zssq.mblog.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.constants.CreditConstants;
import com.zssq.constants.MblogConstants;
import com.zssq.constants.MsgClassConstants;
import com.zssq.constants.MsgTopicConstants;
import com.zssq.constants.RelationConstants;
import com.zssq.constants.StatisticConstants;
import com.zssq.dao.pojo.MblogComment;
import com.zssq.dao.pojo.MblogInfo;
import com.zssq.dao.pojo.MblogModel;
import com.zssq.dao.pojo.MblogReply;
import com.zssq.dao.pojo.MessageIntegral;
import com.zssq.dao.pojo.RelationCollect;
import com.zssq.dao.pojo.RelationDynamic;
import com.zssq.dao.pojo.RelationDynamicTeamRel;
import com.zssq.dao.pojo.RelationLike;
import com.zssq.dao.pojo.RelationQuality;
import com.zssq.dao.pojo.RelationSubjectInfo;
import com.zssq.dao.pojo.RelationSubjectResource;
import com.zssq.dao.pojo.StatisticCommon;
import com.zssq.dao.pojo.SysOrgInfo;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.dao.pojo.TeamInfo;
import com.zssq.dao.pojo.UserState;
import com.zssq.exceptions.BusinessException;
import com.zssq.kafka.core.KafkaProducerTemplate;
import com.zssq.pojo.ResultJSON;
import com.zssq.qo.ForwardWeiboQO;
import com.zssq.qo.MblogInfoQO;
import com.zssq.qo.PublishWeiboQO;
import com.zssq.qo.UserInfoQO;
import com.zssq.search.service.SolrQueryService;
import com.zssq.service.IStatisticService;
import com.zssq.service.ISysDeputyService;
import com.zssq.service.ISysOrgService;
import com.zssq.service.ISysUserService;
import com.zssq.service.ITeamElectService;
import com.zssq.service.ITeamInfoService;
import com.zssq.service.ITeamMemberService;
import com.zssq.service.IUserRelationService;
import com.zssq.service.MblogCommentService;
import com.zssq.service.MblogInfoService;
import com.zssq.service.MblogReplyService;
import com.zssq.service.MblogThridService;
import com.zssq.service.RelationThirdDynamicService;
import com.zssq.service.RelationThirdOperateService;
import com.zssq.service.RelationThirdSubjectService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.StringTools;
import com.zssq.vo.RelationDataVO;
import com.zssq.vo.RelationDynamicVO;
import com.zssq.vo.RelationOperateVO;

/**
 * 
    * @ClassName: MblogBaseController  
    * @Description: 基础Controller  
    * @author Mr.B  
    * @date 2017年6月2日  
    *
 */
@Component
public class MblogBaseController {
	/** log对象 **/
	private Logger log = Logger.getLogger(this.getClass());
	// 引入动态
	@Autowired
	private RelationThirdDynamicService relationThirdDynamicService;
	
	// 引入动态操作
	@Autowired
	private RelationThirdOperateService relationThirdOperateService;
	
	// 引入动态
	@Autowired
	private RelationThirdSubjectService relationThirdSubjectService; 
	
	// 引入kafka消息模板
	@SuppressWarnings("rawtypes")
	@Autowired
	protected KafkaProducerTemplate producerTeplate;
	
	// 引入评论Service
	@Autowired
	protected MblogCommentService mblogCommentService;
	
	// 引入微博信息Service
	@Autowired
	protected MblogInfoService mblogInfoService;
	
	// 引入回复Service
	@Autowired
	protected MblogReplyService mblogReplyService;
	
	// 引入用户Service
	@Autowired
	protected ISysUserService sysUserService;
	
	// 引入组织Service
	@Autowired
	protected ISysOrgService sysOrgService;
	
	// 校验代发关系
	@Autowired
	protected ISysDeputyService sysDeputyService;
	
	// 引入人员关系Service
	@Autowired
	protected IUserRelationService userRelationService;
	
	// 引入统计关系Service
	@Autowired
	protected IStatisticService statisticService;
	
	// 引入班组Service
	@Autowired
	protected ITeamInfoService teamInfoService;
	@Autowired
	protected ITeamMemberService teamMemberService;
	@Autowired
	protected ITeamElectService teamElectService;
	@Autowired
	protected MblogThridService mblogThridService;
	@Autowired
	 protected SolrQueryService solrQueryService;
	/**
	 * 
	    * @Title: sendAtMsg  
	    * @Description: 发送 at 消息
	    * @param mblogCode	微博CODE
	    * @param atType		at类型：1：微博，2：转发微博
	    * @param tenantCode	租户
	    * @param userCode	用户CODE
	    * @param atUserList	at用户列表
	    * @param content	内容
		* @return void    返回类型
	 */
	@SuppressWarnings("unchecked")
	protected void sendAtMsg(final String mblogCode,final Byte atType,final String tenantCode,final String userCode,final List<UserInfoQO> atUserList,final String content) throws BusinessException{
		if(null != atUserList && !atUserList.isEmpty()){
			try {
				JSONArray atMsgList = new JSONArray();
				// 创建at消息对象
				// 获取用户列表
				if(null != atUserList && !atUserList.isEmpty()){
					for(UserInfoQO user : atUserList){
						JSONObject atMsg = new JSONObject();
						atMsg.put("userCode", user.getUserCode());
						atMsg.put("atUserCode", userCode);
						atMsg.put("atType", atType);
						atMsg.put("originalCode", mblogCode);
						atMsg.put("tenantCode", tenantCode);
						atMsg.put("content", content);
						atMsg.put("orgCode", user.getOrgCode());
						atMsgList.add(atMsg);
					}
				}
				// 发送消息
				JSONObject body = new JSONObject();
				body.put("userList", atMsgList);
				producerTeplate.send(MsgTopicConstants.TOPIC_AT, body.toJSONString());
				//messageATService.addMessageAT(model);
			}catch(Exception e){
				log.error("MblogBaseController.sendAtMsg:微博AT发送消息出现问题", e);
				//throw new BusinessException("MblogBaseController.sendAtMsg:微博AT发送消息出现问题");
			}
		}
	}
	
	/**
	 * 
	    * @Title: sendCommentMsg  
	    * @Description: 发送评论信息
	    * @param mblogCode		微博CODE
	    * @param commentCode	评论CODE
	    * @param replyCode		回复CODE
	    * @param content		内容
	    * @param sourceContent	原始内容
	    * @param userCode		用户CODE
	    * @param comedUserCode	被评论或者回复的用户CODE
	    * @param type			动作类型：评论，回复
	    * @param orgCode		组织CODE
	    * @param tenantCode		租户
		* @return void    返回类型
	 */
	@SuppressWarnings("unchecked")
	protected void sendCommentMsg(final String mblogCode,final String commentCode,final String replyCode,final String content,final String sourceContent,final String userCode,final String comedUserCode,final Byte type,final String orgCode,final String tenantCode) throws BusinessException{
		try{
			// 是否发送
			boolean isSend = true;
			// 创建消息
			JSONObject comMsg = new JSONObject();
			// 获取微博信息
			if(StringTools.isNotEmpty(mblogCode)){
				comMsg.put("userCode", comedUserCode);
				comMsg.put("commentUserCode", userCode);
				comMsg.put("originalContent", sourceContent);
				comMsg.put("originalCode", mblogCode);
				comMsg.put("commentContent", content);
				comMsg.put("tenantCode", tenantCode);
				comMsg.put("orgCode", orgCode);
				comMsg.put("commentCode", commentCode);
				comMsg.put("publicCode", replyCode);
				
				
				switch (type) {
					case MblogConstants.MBLOG_ACTION_COMMENT:{ // 评论
						comMsg.put("type", MsgClassConstants.COMMENT_MBLOG_COMMENT);
						break;
					}
					case MblogConstants.MBLOG_ACTION_REPLY:{  // 回复
						comMsg.put("type", MsgClassConstants.COMMENT_MBLOG_REPLY);
						break;
					}
					default:{
						// 失败
						isSend = false;
						log.info("MblogBaseController.sendCommentMsg:点赞发送消息出现问题");
						break;
					}
				}
			}else{
				isSend = false;
			}
			if(isSend){
				// 调用kafka发送消息
				producerTeplate.send(MsgTopicConstants.TOPIC_COMMENT, comMsg.toJSONString());
				//messageCommentService.addMsgComment(model);
			}
		}catch(Exception e){
			log.error("MblogBaseController.sendCommentMsg:评论发送消息出现问题", e);
			//throw new BusinessException("MblogBaseController.sendCommentMsg:评论发送消息出现问题");
		}
	}
	
	/**
	 * 
	    * @Title: sendPraiseMsg  
	    * @Description: 发送点赞信息
	    * @param record				主题
	    * @param userCode			用户CODE
	    * @param type				动作类型：微博点赞，评论点赞，回复点赞
	    * @param orgCode			组织CODE
	    * @param tenantCode			租户CODE
		* @return void    返回类型
	 */
	@SuppressWarnings("unchecked")
	protected void sendPraiseMsg(final Object record,final String userCode,final Byte type,final String orgCode,final String tenantCode) throws BusinessException{
		try{
			boolean isSend = true;
			// 创建发送信息对象
			JSONObject praMsg = new JSONObject();
			praMsg.put("praiseUserCode", userCode);
			praMsg.put("tenantCode", tenantCode);
			praMsg.put("orgCode", orgCode);
			if(null != record){
				switch(type){
					// 微博点赞
					case MblogConstants.MBLOG_ACTION_MBLOG_PRAISE:{
						// 获取微博信息
						MblogInfo mblog = (MblogInfo)record;
						praMsg.put("userCode", mblog.getUserCode());
						praMsg.put("praiseType", MsgClassConstants.PRAISE_MBLOG);
						praMsg.put("originalCode", mblog.getMblogCode());
						praMsg.put("content", mblog.getContent());
						break;
					}
					// 评论点赞
					case MblogConstants.MBLOG_ACTION_COMMENT_PRAISE:{
						// 获取评论信息
						MblogComment comment = (MblogComment)record;
						praMsg.put("userCode", comment.getUserCode());
						praMsg.put("praiseType", MsgClassConstants.PRAISE_MBLOG_COMMENT);
						praMsg.put("originalCode", comment.getMblogCommentCode());
						praMsg.put("content", comment.getContent());
						break;
					}
					// 回复点赞
					case MblogConstants.MBLOG_ACTION_REPLY_PRAISE:{
						// 获取回复信息
						MblogReply reply = (MblogReply)record;
						praMsg.put("userCode", reply.getUserCode());
						praMsg.put("praiseType", MsgClassConstants.PRAISE_MBLOG_REPLY);
						praMsg.put("originalCode", reply.getMblogReplyCode());
						praMsg.put("content", reply.getContent());
						break;
					}
					// 什么都不是
					default:{
						isSend = false;
						log.info("MblogBaseController.sendPraiseMsg:点赞发送消息出现问题");
						break;
					}
				}
			}else{
				isSend = false;
			}
			if(isSend){
				// 发送消息
				producerTeplate.send(MsgTopicConstants.TOPIC_PRAISE, praMsg.toJSONString());
				//messagePraiseService.addMessagePraise(model);
			}
		}catch(Exception e){
			log.error("MblogBaseController.sendPraiseMsg:点赞发送消息出现问题", e);
			//throw new BusinessException("MblogBaseController.sendPraiseMsg:点赞发送消息出现问题");
		}
	}
	
	/**
	 * 
	    * @Title: sendSubscribeMsg  
	    * @Description: 发送订阅信息
	    * @param userCode		用户CODE
	    * @param subUserCode	被订阅用户CODE
	    * @param orgCode		组织CODE
	    * @param tenantCode		租户CODE
		* @return void    返回类型
	 */
	@SuppressWarnings("unchecked")
	protected void sendSubscribeMsg(final String userCode,final String subUserCode,final String orgCode,final String tenantCode){
		try {
			// 创建对象
			JSONObject subMsg = new JSONObject();
			subMsg.put("userCode", subUserCode);
			subMsg.put("subUserCode", userCode);
			subMsg.put("subType", MsgClassConstants.SUBSCRIBE_MBLOG);
			subMsg.put("tenantCode", tenantCode);
			subMsg.put("orgCode", orgCode);
			// 发送消息
			producerTeplate.send(MsgTopicConstants.TOPIC_SUBSCRIBE, subMsg.toJSONString());
			//messageSubscribeService.addMessageSubscribe(model);
		}catch(Exception e){
			log.error("MblogBaseController.sendSubscribeMsg:微博订阅发送消息出现问题", e);
		}
	}
	
	    /**  
	    * @Title: saveDynamic  
	    * @Description: 保存动态
	    * @param source	来源类型：个人，转发
	    * @param vo
	    * @throws BusinessException
		* @return boolean    返回类型    
	    */  
	protected <T> boolean saveDynamic(Byte source,T vo) throws BusinessException{
		// 创建返回值
		boolean result = true;
		try{
			// 动态信息
			RelationDynamic reDy = new RelationDynamic();
			// 班组关系
			RelationDynamicTeamRel  reTR = null;
			// 动态内容信息
			RelationSubjectInfo reInfo = new RelationSubjectInfo();
			// 内容资源信息
			List<RelationSubjectResource> resList = new ArrayList<RelationSubjectResource>();
			switch(source){
				case MblogConstants.MBLOG_SOURCE_SELF:{ // 发表
					PublishWeiboQO qo = (PublishWeiboQO)vo;
					long time = qo.getTimeSign();
					// 动态信息
					reDy.setDynamicCode(qo.getDynamicCode());
					reDy.setTenantCode(qo.getTenatCode());
					reDy.setOrgCode(qo.getOrgCode());
					reDy.setCreateTime(time);
					reDy.setModifyTime(time);
					reDy.setRemark(MblogConstants.MBLOG_BLANK);
					reDy.setDynamicClass(RelationConstants.RELATION_DYNAMIC_SEND_MBLOG);
					if(qo.getIsTeam() == MblogConstants.MBLOG_YES){
						reDy.setDynamicDepend(RelationConstants.RELATION_DYNAMIC_DEPEND_TEAM);
						reDy.setTeamCode(qo.getTeamCode());
						
						// 班组关系
						reTR = new RelationDynamicTeamRel();
						reTR.setCreateTime(time);
						reTR.setDynamicCode(qo.getDynamicCode());
						reTR.setModifyTime(time);
						reTR.setOrgCode(qo.getOrgCode());
						reTR.setRelCode(UUIDHelper.getUUID());
						reTR.setRelIsHomeShow(RelationConstants.RELATION_YES);
						reTR.setRelIsQuality(RelationConstants.RELATION_NO);
						reTR.setRelIsRecommend(RelationConstants.RELATION_NO);
						reTR.setRemark(MblogConstants.MBLOG_BLANK);
						reTR.setTeamCode(qo.getTeamCode());
						// 获取班组信息
						TeamInfo team = getTeamInfo(qo.getTeamCode());
						if(null != team){
							reTR.setTeamIsDissolve(team.getIsDissolve());
							reTR.setTeamIsExcellent(teamElectService.isExcelent(qo.getTeamCode()) ? RelationConstants.RELATION_YES : RelationConstants.RELATION_NO);
							reTR.setTeamIsNoOne(team.getTeamType() == 0 ? RelationConstants.RELATION_NO : RelationConstants.RELATION_YES);
						}
						
						reTR.setTenantCode(qo.getTenatCode());
					}else{
						reDy.setDynamicDepend(RelationConstants.RELATION_DYNAMIC_DEPEND_USER);
						reDy.setTeamCode(MblogConstants.MBLOG_BLANK);
					}
					reDy.setUserCode(qo.getUserCode());
					reDy.setSubjectCode(qo.getMblogCode());
					reDy.setDynamicIsShield(RelationConstants.RELATION_NO);
					reDy.setDynamicIsDelete(RelationConstants.RELATION_NO);
					reDy.setIsSubjectShow(RelationConstants.RELATION_YES);
					reDy.setIsSubjectDataShow(RelationConstants.RELATION_YES);
					reDy.setOperateTime(time);
					
					// 内容信息
					reInfo.setSubjectCode(qo.getMblogCode());
					reInfo.setTenantCode(qo.getTenatCode());
					reInfo.setOrgCode(qo.getOrgCode());
					reInfo.setCreateTime(time);
					reInfo.setModifyTime(time);
					reInfo.setSubjectTitle(qo.getTitle());
					reInfo.setRemark(MblogConstants.MBLOG_BLANK);
					reInfo.setSubjectClass(RelationConstants.RELATION_SUBJECT_MBLOG);
					if(qo.getIsTeam() == MblogConstants.MBLOG_YES){
						reInfo.setSubjectDepend(RelationConstants.RELATION_SUBJECT_DEPEND_TEAM);
						reInfo.setTeamCode(qo.getTeamCode());
					}else{
						reInfo.setSubjectDepend(RelationConstants.RELATION_SUBJECT_DEPEND_USER);
						reInfo.setTeamCode(MblogConstants.MBLOG_BLANK);
					}
					reInfo.setUserCode(qo.getUserCode());
					reInfo.setSubjectPublishTime(time);
					reInfo.setSubjectDigest(qo.getContent());
					/*if(null != qo.getImgs() && !qo.getImgs().isEmpty()){
						reInfo.setSubjectUrl(qo.getImgs().get(0));
					}else{
						reInfo.setSubjectUrl(MblogConstants.MBLOG_BLANK);
					}*/
					reInfo.setSourceCode(MblogConstants.MBLOG_BLANK);
					reInfo.setSourceUserCode(MblogConstants.MBLOG_BLANK);
					reInfo.setSourcePublishTime(MblogConstants.MBLOG_TIME_ZERO);
					reInfo.setSourceIsDelete(RelationConstants.RELATION_NO);
					reInfo.setSourceIsShield(RelationConstants.RELATION_NO);
					reInfo.setContentTips(MblogConstants.MBLOG_BLANK);
					reInfo.setSubjectIsDelete(RelationConstants.RELATION_NO);
					reInfo.setSubjectIsShield(RelationConstants.RELATION_NO);
					reInfo.setSubjectSource(RelationConstants.RELATION_SUBJECT_SOURCE_ORIGINAL);
					// 资源
					// 图片资源
					List<String> imgs = qo.getImgs();
					// 音频资源
					List<String> audios = qo.getAudios();
					// 视频资源
					List<String> vedios = qo.getVideos();
					if(null != imgs && !imgs.isEmpty()){ // 图片资源
						for(String img : imgs){
							RelationSubjectResource temp = new RelationSubjectResource();
							temp.setSubjectResCode(UUIDHelper.getUUID());
							temp.setTenantCode(qo.getTenatCode());
							temp.setOrgCode(qo.getOrgCode());
							temp.setCreateTime(time);
							temp.setModifyTime(time);
							temp.setRemark(MblogConstants.MBLOG_BLANK);
							temp.setSubjectCode(qo.getMblogCode());
							temp.setResUrl(img);
							temp.setResClass(RelationConstants.RELATION_SUBJECT_RES_PICTURE);
							resList.add(temp);
						}
					}
					if(null != audios && !audios.isEmpty()){ // 音频资源
						for(String audio : audios){
							RelationSubjectResource temp = new RelationSubjectResource();
							temp.setSubjectResCode(UUIDHelper.getUUID());
							temp.setTenantCode(qo.getTenatCode());
							temp.setOrgCode(qo.getOrgCode());
							temp.setCreateTime(time);
							temp.setModifyTime(time);
							temp.setRemark(MblogConstants.MBLOG_BLANK);
							temp.setSubjectCode(qo.getMblogCode());
							temp.setResUrl(audio);
							temp.setResClass(RelationConstants.RELATION_SUBJECT_RES_AUDIO);
							resList.add(temp);
						}
					}
					if(null != vedios && !vedios.isEmpty()){ // 视频资源
						for(String vedio : vedios){
							RelationSubjectResource temp = new RelationSubjectResource();
							temp.setSubjectResCode(UUIDHelper.getUUID());
							temp.setTenantCode(qo.getTenatCode());
							temp.setOrgCode(qo.getOrgCode());
							temp.setCreateTime(time);
							temp.setModifyTime(time);
							temp.setRemark(MblogConstants.MBLOG_BLANK);
							temp.setSubjectCode(qo.getMblogCode());
							temp.setResUrl(vedio);
							temp.setResClass(RelationConstants.RELATION_SUBJECT_RES_VIDEO);
							resList.add(temp);
						}
					}
					break;
				}
				case MblogConstants.MBLOG_SOURCE_FORWARD:{ // 转发
					ForwardWeiboQO qo = (ForwardWeiboQO)vo;
					long time = qo.getTimeSign();
					// 获取转发微博内容
					MblogInfo info = qo.getSourceMblogInfo();
					// 动态信息
					reDy.setDynamicCode(qo.getDynamicCode());
					reDy.setTenantCode(qo.getTenatCode());
					reDy.setOrgCode(qo.getOrgCode());
					reDy.setCreateTime(time);
					reDy.setModifyTime(time);
					reDy.setRemark(MblogConstants.MBLOG_BLANK);
					reDy.setDynamicClass(RelationConstants.RELATION_DYNAMIC_FORWARD_MBLOG);
					reDy.setDynamicDepend(RelationConstants.RELATION_DYNAMIC_DEPEND_USER);
					reDy.setTeamCode(MblogConstants.MBLOG_BLANK);
					reDy.setUserCode(qo.getUserCode());
					reDy.setSubjectCode(qo.getMblogCode());
					reDy.setDynamicIsShield(RelationConstants.RELATION_NO);
					reDy.setDynamicIsDelete(RelationConstants.RELATION_NO);
					reDy.setIsSubjectShow(RelationConstants.RELATION_YES);
					reDy.setIsSubjectDataShow(RelationConstants.RELATION_YES);
					reDy.setOperateTime(time);
					
					// 内容信息
					reInfo.setSubjectCode(qo.getMblogCode());
					reInfo.setTenantCode(qo.getTenatCode());
					reInfo.setOrgCode(qo.getOrgCode());
					reInfo.setCreateTime(time);
					reInfo.setModifyTime(time);
					reInfo.setSubjectTitle(qo.getTitle());
					reInfo.setRemark(MblogConstants.MBLOG_BLANK);
					reInfo.setSubjectClass(RelationConstants.RELATION_SUBJECT_MBLOG);
					reInfo.setSubjectDepend(RelationConstants.RELATION_SUBJECT_DEPEND_USER);
					reInfo.setSubjectSource(RelationConstants.RELATION_SUBJECT_SOURCE_FORWARD);
					reInfo.setTeamCode(MblogConstants.MBLOG_BLANK);
					reInfo.setUserCode(qo.getUserCode());
					reInfo.setSubjectPublishTime(time);
					reInfo.setSubjectDigest(info.getContent());
					reInfo.setSubjectUrl(MblogConstants.MBLOG_BLANK);
					reInfo.setSourceCode(qo.getSourceMblogCode());
					reInfo.setSourceUserCode(qo.getSourceUserCode());
					if(info.getMblogDepend() == MblogConstants.MBLOG_DEPEND_GROUP){
						reInfo.setSourceTeamCode(info.getTeamCode());
					}
					reInfo.setSourceDepend(info.getMblogDepend());
					reInfo.setSourcePublishTime(info.getPublishTime());
					reInfo.setSourceIsDelete(info.getIsDelete());
					reInfo.setSourceIsShield(info.getIsShield());
					reInfo.setContentTips(qo.getContent());
					reInfo.setSubjectIsDelete(RelationConstants.RELATION_NO);
					reInfo.setSubjectIsShield(RelationConstants.RELATION_NO);
					
					// 资源
					// 图片资源
					List<String> imgs = null;
					// 音频资源
					List<String> audios = null;
					// 视频资源
					List<String> vedios = null;
					if(StringTools.isNotEmpty(info.getImgs())){
						imgs = Arrays.asList(info.getImgs().split(","));
					}
					if(StringTools.isNotEmpty(info.getAudios())){
						audios = Arrays.asList(info.getAudios().split(","));
					}
					if(StringTools.isNotEmpty(info.getVideos())){
						vedios = Arrays.asList(info.getVideos().split(","));
					}
					if(null != imgs && !imgs.isEmpty()){ // 图片资源
						for(String img : imgs){
							RelationSubjectResource temp = new RelationSubjectResource();
							temp.setSubjectResCode(UUIDHelper.getUUID());
							temp.setTenantCode(qo.getTenatCode());
							temp.setOrgCode(qo.getOrgCode());
							temp.setCreateTime(time);
							temp.setModifyTime(time);
							temp.setRemark(MblogConstants.MBLOG_BLANK);
							temp.setSubjectCode(qo.getMblogCode());
							temp.setResUrl(img);
							temp.setResClass(RelationConstants.RELATION_SUBJECT_RES_PICTURE);
							resList.add(temp);
						}
					}
					if(null != audios && !audios.isEmpty()){ // 音频资源
						for(String audio : audios){
							RelationSubjectResource temp = new RelationSubjectResource();
							temp.setSubjectResCode(UUIDHelper.getUUID());
							temp.setTenantCode(qo.getTenatCode());
							temp.setOrgCode(qo.getOrgCode());
							temp.setCreateTime(time);
							temp.setModifyTime(time);
							temp.setRemark(MblogConstants.MBLOG_BLANK);
							temp.setSubjectCode(qo.getMblogCode());
							temp.setResUrl(audio);
							temp.setResClass(RelationConstants.RELATION_SUBJECT_RES_AUDIO);
							resList.add(temp);
						}
					}
					if(null != vedios && !vedios.isEmpty()){ // 视频资源
						for(String vedio : vedios){
							RelationSubjectResource temp = new RelationSubjectResource();
							temp.setSubjectResCode(UUIDHelper.getUUID());
							temp.setTenantCode(qo.getTenatCode());
							temp.setOrgCode(qo.getOrgCode());
							temp.setCreateTime(time);
							temp.setModifyTime(time);
							temp.setRemark(MblogConstants.MBLOG_BLANK);
							temp.setSubjectCode(qo.getMblogCode());
							temp.setResUrl(vedio);
							temp.setResClass(RelationConstants.RELATION_SUBJECT_RES_VIDEO);
							resList.add(temp);
						}
					}
					break;
				}
				default:{
					throw new BusinessException("参数异常");
				}
			}
			result = relationThirdDynamicService.saveDynamic(reDy, reTR,reInfo, resList);
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			throw new BusinessException("发送动态出现异常",e);
		}
		return result;
	}
	
	/**
	 * 
	    * @Title: deleteDynamic  
	    * @Description: 删除微博动态
	    * @param mblogCode	微博CODE
	    * @param dynamicCode 动态CODE
	    * @param userCode	用户CODE
	    * @param mblogSource 微博来源
	    * @throws BusinessException
		* @return boolean    返回类型
	 */
	protected boolean deleteDynamic(String mblogCode,String dynamicCode,String userCode,Byte mblogSource) throws BusinessException{
		// 创建返回值
		boolean result = true;
		try{
			// 删除微博动态信息
			if(StringTools.isNotEmpty(mblogCode) && StringTools.isNotEmpty(dynamicCode)){
				RelationDynamicVO vo = new RelationDynamicVO();
				vo.setDynamicCode(dynamicCode);
				vo.setSubjectCode(mblogCode);
				vo.setSubjectClass(RelationConstants.RELATION_SUBJECT_MBLOG);
				if(mblogSource == MblogConstants.MBLOG_SOURCE_FORWARD){
					vo.setDynamicClass(RelationConstants.RELATION_DYNAMIC_FORWARD_MBLOG);
				}else{
					vo.setDynamicClass(RelationConstants.RELATION_DYNAMIC_SEND_MBLOG);
				}
				vo.setUserCode(userCode);
				vo.setModifyTime(new Date().getTime());
				result = relationThirdDynamicService.deleteDynamic(vo);
			}else{
				result = false;
			}
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			throw new BusinessException("删除动态出现异常",e);
		}
		return result;
	}
	/**
	 * 
	    * @Title: checkEssence  
	    * @Description: 判断是否置精
	    * @param dynamicCode
	    * @return
		* @return boolean    返回类型
	 * @throws BusinessException 
	 */
	protected boolean checkEssence(String dynamicCode) throws BusinessException{
		if(StringTools.isNotEmpty(dynamicCode)){
			RelationQuality vo = new RelationQuality();
			vo.setSubjectCode(dynamicCode);
			vo.setSubjectClass(RelationConstants.RELATION_SUBJECT_MBLOG);
			return relationThirdSubjectService.getQualityStatus(vo);
		}
		return false;
	}
	
	/**
	 * 
	    * @Title: praiseDynamic  
	    * @Description: 动态点赞
	    * @param mblogCode	微博CODE
	    * @param userCode	用户CODE
	    * @param isPraise	是否点赞：1：点赞，0：取消点赞
	    * @param orgCode	组织CODE
	    * @param tenantCode	租户CODE
	    * @throws BusinessException
		* @return boolean    返回类型
	 */
	protected boolean praiseDynamic(String mblogCode,String userCode,Byte isPraise,String orgCode,String tenantCode) throws BusinessException{
		// 创建返回值
		boolean result = true;
		try{
			switch(isPraise){
				case MblogConstants.MBLOG_YES:{ // 点赞
					long time = new Date().getTime();
					RelationLike like = new RelationLike();
					like.setLikeCode(UUIDHelper.getUUID());
					like.setOrgCode(orgCode);
					//like.setOrgLevel(orgLevel);
					like.setTenantCode(tenantCode);
					like.setRemark(MblogConstants.MBLOG_BLANK);
					like.setSubjectClass(RelationConstants.RELATION_LIKE_MBLOG);
					like.setSubjectCode(mblogCode);
					like.setUserCode(userCode);
					like.setCreateTime(time);
					like.setModifyTime(time);
					result = relationThirdOperateService.saveLike(like);
					break;
				}
				case MblogConstants.MBLOG_NO:{ // 取消点赞
					RelationOperateVO vo = new RelationOperateVO();
					vo.setUserCode(userCode);
					vo.setSubjectCode(mblogCode);
					result = relationThirdOperateService.deleteLike(vo);
					break;
				}
				default:{
					result = false;
					log.info("MblogBaseController.praiseDynamic:动态点赞、取消点赞出现问题");
					break;
				}
			}
			
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			throw new BusinessException("动态点赞出现异常",e);
		}
		return result;
	}
	
	/**
	 * 
	    * @Title: collectDynamic  
	    * @Description: 收藏动态
	    * @param mblogCode	微博CODE
	    * @param userCode	用户CODE
	    * @param isCollect	是否收藏：1:收藏，2：取消收藏
	    * @param orgCode	组织CODE
	    * @param tenantCode	租户CODE
	    * @throws BusinessException
		* @return boolean    返回类型
	 */
	protected boolean collectDynamic(String mblogCode,String userCode,Byte isCollect,String orgCode,String tenantCode) throws BusinessException{
		// 创建返回值
		boolean result = true;
		try{
			switch(isCollect){
				case MblogConstants.MBLOG_YES:{ // 收藏
					long time = new Date().getTime();
					RelationCollect collect = new RelationCollect();
					collect.setCollectCode(UUIDHelper.getUUID());
					collect.setOrgCode(orgCode);
					//collect.setOrgLevel(orgLevel);
					collect.setTenantCode(tenantCode);
					collect.setRemark(MblogConstants.MBLOG_BLANK);
					collect.setSubjectClass(RelationConstants.RELATION_COLLECT_MBLOG);
					collect.setSubjectCode(mblogCode);
					collect.setUserCode(userCode);
					collect.setCreateTime(time);
					collect.setModifyTime(time);
					result = relationThirdOperateService.saveCollect(collect);
					break;
				}
				case MblogConstants.MBLOG_NO:{ // 取消收藏
					RelationOperateVO vo = new RelationOperateVO();
					vo.setUserCode(userCode);
					vo.setSubjectCode(mblogCode);
					result = relationThirdOperateService.deleteCollect(vo);
					break;
				}
				default:{
					result = false;
					log.info("MblogBaseController.collectDynamic:动态收藏、取消收藏出现问题");
					break;
				}
			}
			
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			throw new BusinessException("收藏动态出现异常",e);
		}
		return result;
	}
	
	/**
	 * 
	    * @Title: updateCommentNum  
	    * @Description: 更新评论数目
	    * @param mblogCode		微博CODE
	    * @param commentCode	评论CODE
	    * @param replyCode		回复CODE
	    * @param actionType		动作： 评论，回复
	    * @param isDelete		是否删除
	    * @throws BusinessException
		* @return void    返回类型
	 */
	@SuppressWarnings("all")
	protected void updateCommentNum(String mblogCode,String commentCode,String replyCode,Byte actionType,boolean isDelete) throws BusinessException{
		try{
			boolean isSend = true;
			// 创建对象
			RelationDataVO vo = new RelationDataVO();
			// 设置微博CODE
			vo.setSubjectCode(mblogCode);
			vo.setModifyTime(new Date().getTime());
			// 类型
			vo.setUpdateClass(RelationConstants.RELATION_THIRD_DATANUM_TYPE_COMMENT);
			// 不同动作不同操作
			switch(actionType){
				case MblogConstants.MBLOG_ACTION_COMMENT:{ // 评论
					if(isDelete){
						vo.setUpdateNumber(MblogConstants.MBLOG_NUM_SUB);
					}else{
						vo.setUpdateNumber(MblogConstants.MBLOG_NUM_ADD);
					}
					break;
				}
				case MblogConstants.MBLOG_ACTION_REPLY:{ // 回复
					vo.setUpdateNumber(isDelete ? MblogConstants.MBLOG_NUM_SUB : MblogConstants.MBLOG_NUM_ADD);
					break;
				}
				default:{
					isSend = false;
					log.info("MblogBaseController.updateCommentNum:更新动态出现问题");
					break;
				}
			}
			if(isSend){
				// 发送kafka消息
				producerTeplate.send(RelationConstants.RELATION_TOPIC, JSONObject.toJSONString(vo));
			}
		}catch(Exception e){
			throw new BusinessException("动态更新评论数出现异常",e);
		}
	}
	
	/**
	 * 
	    * @Title: updatePraiseNum  
	    * @Description: 更新点赞数量
	    * @param mblogCode	微博CODE
	    * @param isPraise	点赞
	    * @throws BusinessException
		* @return void    返回类型
	 */
	@SuppressWarnings("all")
	protected void updatePraiseNum(String mblogCode,Byte isPraise) throws BusinessException{
		try{
			boolean isSend = true;
			// 创建对象
			RelationDataVO vo = new RelationDataVO();
			// 设置微博CODE
			vo.setSubjectCode(mblogCode);
			vo.setModifyTime(new Date().getTime());
			// 类型
			vo.setUpdateClass(RelationConstants.RELATION_THIRD_DATANUM_TYPE_LIKE);
			// 不同动作不同操作
			switch(isPraise){
				case MblogConstants.MBLOG_YES:{ // 点赞
					vo.setUpdateNumber(MblogConstants.MBLOG_NUM_ADD);
					break;
				}
				case MblogConstants.MBLOG_NO:{ // 取消点赞
					vo.setUpdateNumber(MblogConstants.MBLOG_NUM_SUB);
					break;
				}
				default:{
					isSend = false;
					log.info("MblogBaseController.updatePraiseNum:更新动态出现问题");
					break;
				}
			}
			if(isSend){
				// 发送kafka消息
				producerTeplate.send(RelationConstants.RELATION_TOPIC, JSONObject.toJSONString(vo));
			}
		}catch(Exception e){
			throw new BusinessException("动态更新评论数出现异常",e);
		}
	}
	
	/**
	 * 
	    * @Title: updateCollectNum  
	    * @Description: 更新收藏数量
	    * @param mblogCode
	    * @param isCollect
	    * @throws BusinessException
		* @return void    返回类型
	 */
	@SuppressWarnings("all")
	protected void updateCollectNum(String mblogCode,Byte isCollect) throws BusinessException{
		try{
			boolean isSend = true;
			// 创建对象
			RelationDataVO vo = new RelationDataVO();
			// 设置微博CODE
			vo.setSubjectCode(mblogCode);
			vo.setModifyTime(new Date().getTime());
			// 类型
			vo.setUpdateClass(RelationConstants.RELATION_THIRD_DATANUM_TYPE_COLLECT);
			// 不同动作不同操作
			switch(isCollect){
				case MblogConstants.MBLOG_YES:{ // 收藏
					vo.setUpdateNumber(MblogConstants.MBLOG_NUM_ADD);
					break;
				}
				case MblogConstants.MBLOG_NO:{ // 取消收藏
					vo.setUpdateNumber(MblogConstants.MBLOG_NUM_SUB);
					break;
				}
				default:{
					isSend = false;
					log.info("MblogBaseController.updateCollectNum:更新动态出现问题");
					break;
				}
			}
			if(isSend){
				// 发送kafka消息
				producerTeplate.send(RelationConstants.RELATION_TOPIC, JSONObject.toJSONString(vo));
			}
		}catch(Exception e){
			throw new BusinessException("动态更新评论数出现异常",e);
		}
	}
	/**
	 * 
	    * @Title: updateForwardNum  
	    * @Description: 更新转发次数
	    * @param forMblogCode
	    * @throws BusinessException
		* @return void    返回类型
	 */
	@SuppressWarnings("all")
	protected void updateForwardNum(String forMblogCode) throws BusinessException{
		try{
			if(StringTools.isNotEmpty(forMblogCode)){
				// 获取微博转发历程
				List<String> mblogCodes = mblogInfoService.getForwardProcess(forMblogCode);
				if(null != mblogCodes && !mblogCodes.isEmpty()){
					// 设置微博CODE
					long time = new Date().getTime();
					for(String mblogCode : mblogCodes){
						RelationDataVO vo = new RelationDataVO();
						// 设置更新次数
						vo.setUpdateNumber(MblogConstants.MBLOG_NUM_ADD);
						vo.setSubjectCode(mblogCode);
						vo.setModifyTime(time);
						vo.setUpdateClass(RelationConstants.RELATION_THIRD_DATANUM_TYPE_FORWARD);
						// 发送kafka消息
						producerTeplate.send(RelationConstants.RELATION_TOPIC, JSON.toJSONString(vo));
					}
				}
			}
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			throw new BusinessException("动态更新评论数出现异常",e);
		}
	}
	
	/**
	 * 
	    * @Title: sendCreditMsg  
	    * @Description: 	发送积分信息
	    * @param accountCode	账号CODE
	    * @param orgCode 		组织CODE
	    * @param accountType	账号类型
	    * @param actionCode		动作类型
		* @return void    返回类型
	 */
	@SuppressWarnings("unchecked")
	protected void sendCreditMsg(String accountCode,String orgCode,Byte accountType,String actionCode){
		MessageIntegral msg = new MessageIntegral();
		msg.setAccountCode(accountCode);
		msg.setActionCode(actionCode);
		msg.setAccountType(accountType);
		msg.setManageOrgCode(orgCode);
		producerTeplate.send(CreditConstants.CREDIT_INCREMENT, JSONObject.toJSONString(msg));
	}
	
	/**
	 * 
	    * @Title: checkIsBlack  
	    * @Description: 	判断俩人是否是黑名单关系
	    * @param userCode
	    * @param otherCode
	    * @throws BusinessException
		* @return boolean    返回类型
	 */
	protected boolean checkIsBlack(String userCode,String otherCode) throws BusinessException{
		UserState userState = userRelationService.selectUserState(userCode, otherCode);
		if(null != userState){
			return userState.getIsBlack() > 0;
		}
		return false;
	}
	
	/**
	 * 
	    * @Title: getMyFrAndCo  
	    * @Description: 	获取我的好友以及我的关注
	    * @param userCode	我的用户CODE
	    * @throws BusinessException
		* @return List<String>    返回类型
	 */
	protected List<String> getMyFrAndCo(String userCode) throws BusinessException{
		List<String> userCodes = new ArrayList<String>();
		// 获取我的关注
		//userCodes.addAll(userRelationService.selectConcerns(userCode));
		// 获取我的好友
		//userCodes.addAll(userRelationService.selectFriends(userCode));
		userCodes.add(userCode);
		return userCodes;
	}
	
	/**
	 * 
	    * @Title: getUserInfoList  
	    * @Description: 获取用户列表
	    * @param userCodes
	    * @throws BusinessException
		* @return List<SysUserInfo>    返回类型
	 */
	protected List<SysUserInfo> getUserInfoList(List<String> userCodes) throws BusinessException{
		List<SysUserInfo> userList = sysUserService.selectByCodes(userCodes);
		if(null != userList){
			return userList;
		}
		return new ArrayList<SysUserInfo>();
	}
	
	/**
	 * 
	    * @Title: getUserInfo  
	    * @Description: 获取用户信息
	    * @param userCode
	    * @throws BusinessException
		* @return SysUserInfo    返回类型
	 */
	protected SysUserInfo getUserInfo(String userCode) throws BusinessException{
		// 获取用户信息
		if(StringTools.isNotEmpty(userCode)){
			return sysUserService.selectByCode(userCode);
		}
		return null;
	}
	
	/**
	 * 
	    * @Title: getOrgInfo  
	    * @Description: 获取组织信息
	    * @param orgCode
	    * @throws BusinessException
		* @return SysUserInfo    返回类型
	 */
	protected SysOrgInfo getOrgInfo(String orgCode) throws BusinessException{
		// 获取用户信息
		if(StringTools.isNotEmpty(orgCode)){
			return sysOrgService.selectByCode(orgCode);
		}
		return null;
	}
	
	/**
	 * 
	    * @Title: getTeamInfo  
	    * @Description: 获取班组信息
	    * @param teamCode	班组信息
	    * @throws BusinessException
		* @return TeamInfo    返回类型
	 */
	protected TeamInfo getTeamInfo(String teamCode) throws BusinessException{
		if(StringTools.isNotEmpty(teamCode)){
			return teamInfoService.selectByCode(teamCode);
		}
		return null;
	}
	
	/**
	 * 
	    * @Title: getOrgName  
	    * @Description: 获取组织名字
	    * @param orgCode
	    * @throws BusinessException
		* @return String    返回类型
	 */
	protected String getOrgName(String orgCode) throws BusinessException{
		// 获取用户信息
		if(StringTools.isNotEmpty(orgCode)){
			SysOrgInfo org = sysOrgService.selectByCode(orgCode);
			if(null != org){
				return org.getSysOrgFullname();
			}
			return "";
		}
		return "";
	}
	
	/**
	 * 
	    * @Title: checkTeamLeader  
	    * @Description: 判断是否是班组长
	    * @param teamCode
	    * @param userCode
	    * @throws BusinessException
		* @return boolean    返回类型
	 */
	protected boolean checkTeamLeader(String teamCode,String userCode) throws BusinessException{
		if(StringTools.isNotEmpty(teamCode)){
			List<String> leaderCodes = teamMemberService.selectTeamLeaders(teamCode);
			if(null != leaderCodes){
				return leaderCodes.contains(userCode);
			}
		}
		return false;
	}
	
	/**
	 * 
	    * @Title: addStatisticRecord  
	    * @Description: 添加统计记录
	    * @param isTeam 	是否是班组
	    * @param teamCode	班组CODE
	    * @param userCode	用户CODE
		* @return void    返回类型
	 */
	protected void addStatisticRecord(final boolean isTeam,final String teamCode,final String userCode) throws BusinessException{
		
		try {
			StatisticCommon record = new StatisticCommon();
			// 班组长信息
			if(isTeam){
				TeamInfo team = teamInfoService.selectByCode(teamCode);
				record.setOrgCode(team.getOrgCode());
				record.setTeamCode(teamCode);
				record.setTeamName(team.getTeamName());
			}else{
			// 个人
				SysUserInfo user = sysUserService.selectByCode(userCode);
				record.setOrgCode(user.getManageOrgInfo().getSysOrgCode());
				record.setPeopleCode(userCode);
				record.setPeopleName(user.getUserName());
			}
			record.setMicroblog(StatisticConstants.MICROBLOG);
			if(statisticService.addRecord(record) < 0){
				log.info("MblogBaseController.addStatisticRecord:发布微博添加统计失败");
			}
		} catch (BusinessException e) {
			log.error("MblogBaseController.addStatisticRecord:发布微博添加统计出现问题", e);
			throw e;
		}
	}
	
	/**
	 * 
	    * @Title: getMblogInfo  
	    * @Description: 获取微博详情
	    * @param mblogCode	微博CODE
	    * @param userCode	用户CODE
		* @return ResultJSON    返回类型
	 * @throws BusinessException 
	 */
	protected ResultJSON getMblogInfo(String mblogCode,String userCode) throws BusinessException{
		try{
			// 创建QO
			MblogInfoQO qo1 = new MblogInfoQO();
			qo1.setMyUserCode(userCode);
			qo1.setMblogCode(mblogCode);
			// 获取分页数据
			MblogModel mblog = mblogInfoService.getMblogInfo(qo1);
			// 创建返回信息
			JSONObject body = new JSONObject();
			body.put("nowSysTime", new Date().getTime());
			// 分解单个信息
			body.put("mblogCode", mblog.getMblogCode());
			body.put("userCode", mblog.getUserCode());
			
			// 获取用户组织信息
			SysUserInfo user1 =  getUserInfo(mblog.getUserCode());
			if(null != user1){
				body.put("userName", user1.getUserName());
				body.put("userPhoto", user1.getHeadPortrait());
				body.put("orgName", user1.getManageOrgInfo().getSysOrgFullname());
			}else{
				body.put("userName", "");
				body.put("userPhoto", "");
				body.put("orgName", "");
			}
			
			if(mblog.getMblogDepend() == MblogConstants.MBLOG_DEPEND_SELF){
				body.put("teamCode", "");
				body.put("teamName", "");
			}else{
				// 获取班组信息
				TeamInfo team = getTeamInfo(mblog.getTeamCode());
				if(null != team){
					body.put("teamCode", mblog.getTeamCode());
					body.put("teamName", team.getTeamName());
				}else{
					body.put("teamCode", "");
					body.put("teamName", "");
				}
			}
			
			body.put("mblogDepend", mblog.getMblogDepend());
			body.put("summary", mblog.getSummary());
			body.put("content", mblog.getContent());
			body.put("imgs", mblog.getImgs());
			body.put("audios", mblog.getAudios());
			body.put("videos", mblog.getVideos());
			body.put("mblogSource", mblog.getMblogSource());
			body.put("forwardNum", mblog.getForwardNum());
			body.put("collectNum", mblog.getCollectNum());
			body.put("commentNum", mblog.getCommentNum());
			body.put("praiseNum", mblog.getPraiseNum());
			body.put("publishTime", mblog.getPublishTime());
			body.put("meCollect", mblog.getMeCollect());
			body.put("mePraise", mblog.getMePraise());
			body.put("dynamicCode", mblog.getDynamicCode());
			JSONObject wbSub = new JSONObject();
			if(MblogConstants.MBLOG_SOURCE_FORWARD == mblog.getMblogSource()){
				MblogInfo mi = mblog.getForwardMblog();
				if(null != mi){
					wbSub.put("mblogCode", mi.getMblogCode());
					wbSub.put("userCode", mi.getUserCode());
					
					// 获取用户组织信息
					SysUserInfo forUser =  getUserInfo(mi.getUserCode());
					// 获取组织信息
					SysOrgInfo org = getOrgInfo(mi.getOrgCode());
					if(null != forUser){
						wbSub.put("userName", forUser.getUserName());
						wbSub.put("userPhoto", forUser.getHeadPortrait());
					}else{
						wbSub.put("userName", "");
						wbSub.put("userPhoto", "");
					}
					if(null != org){
						wbSub.put("orgName", org.getSysOrgFullname());
					}else{
						wbSub.put("orgName", "");
					}
					if(mi.getMblogDepend() == MblogConstants.MBLOG_DEPEND_SELF){
						wbSub.put("teamCode", "");
						wbSub.put("teamName", "");
					}else{
						// 获取班组信息
						TeamInfo team = getTeamInfo(mi.getTeamCode());
						if(null != team){
							wbSub.put("teamCode", mi.getTeamCode());
							wbSub.put("teamName", team.getTeamName());
						}else{
							wbSub.put("teamCode", "");
							wbSub.put("teamName", "");
						}
					}
					
					wbSub.put("summary", mi.getSummary());
					wbSub.put("content", mi.getContent());
					wbSub.put("imgs", mi.getImgs());
					wbSub.put("audios", mi.getAudios());
					wbSub.put("videos", mi.getVideos());
					wbSub.put("mblogSource", mi.getMblogSource());
					wbSub.put("publishTime", mi.getPublishTime());
					wbSub.put("mblogDepend", mi.getMblogDepend());
				}
				
			}
			body.put("wbSub", wbSub);
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(body);
			return result;
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			throw BusinessException.build("COMMON_400",e);
		}
	}
	
	/**
	 * 
	    * @Title: getMblogInfo  
	    * @Description: 获取微博详情
	    * @param qo	
		* @return ResultJSON    返回类型
	 * @throws BusinessException 
	 */
	protected ResultJSON getMblogInfo(PublishWeiboQO qo) throws BusinessException{
		try{
			// 创建返回信息
			JSONObject body = new JSONObject();
			body.put("nowSysTime", new Date().getTime());
			// 分解单个信息
			body.put("mblogCode", qo.getMblogCode());
			body.put("userCode", qo.getUserCode());
			
			// 获取用户组织信息
			SysUserInfo user1 =  getUserInfo(qo.getUserCode());
			if(null != user1){
				body.put("userName", user1.getUserName());
				body.put("userPhoto", user1.getHeadPortrait());
				body.put("orgName", user1.getManageOrgInfo().getSysOrgFullname());
			}else{
				body.put("userName", "");
				body.put("userPhoto", "");
				body.put("orgName", "");
			}
			
			if(qo.getIsTeam() == MblogConstants.MBLOG_NO){
				body.put("teamCode", "");
				body.put("teamName", "");
				body.put("mblogDepend", MblogConstants.MBLOG_DEPEND_SELF);
			}else{
				body.put("mblogDepend", MblogConstants.MBLOG_DEPEND_SELF);
				// 获取班组信息
				TeamInfo team = getTeamInfo(qo.getTeamCode());
				if(null != team){
					body.put("teamCode", qo.getTeamCode());
					body.put("teamName", team.getTeamName());
				}else{
					body.put("teamCode", "");
					body.put("teamName", "");
				}
			}
			body.put("summary", qo.getSummary());
			body.put("content", qo.getContent());
			body.put("imgs", StringUtils.collectionToCommaDelimitedString(qo.getImgs()));
			body.put("audios", StringUtils.collectionToCommaDelimitedString(qo.getAudios()));
			body.put("videos", StringUtils.collectionToCommaDelimitedString(qo.getVideos()));
			body.put("mblogSource", MblogConstants.MBLOG_SOURCE_SELF);
			body.put("forwardNum", MblogConstants.MBLOG_ZERO);
			body.put("collectNum", MblogConstants.MBLOG_ZERO);
			body.put("commentNum", MblogConstants.MBLOG_ZERO);
			body.put("praiseNum", MblogConstants.MBLOG_ZERO);
			body.put("publishTime", qo.getTimeSign());
			body.put("meCollect", MblogConstants.MBLOG_NO);
			body.put("mePraise", MblogConstants.MBLOG_NO);
			body.put("dynamicCode", qo.getDynamicCode());
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(body);
			return result;
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			throw BusinessException.build("COMMON_400",e);
		}
	}
	
	/**
	 * 
	    * @Title: checkIsTeam  
	    * @Description: 校验微博是否属于班组
	    * @param mblogCode
	    * @throws BusinessException
		* @return boolean    返回类型
	 */
	protected boolean checkIsTeam(String mblogCode) throws BusinessException{
		try{
			if(StringTools.isNotEmpty(mblogCode)){
				MblogInfo mblog = mblogInfoService.getMblogInfo(mblogCode);
				if(mblog.getMblogDepend() == MblogConstants.MBLOG_DEPEND_GROUP){
					return true;
				}
			}
			return false;
		}catch(Exception e){
			throw BusinessException.build("COMMON_400",e);
		}
	}
}
