package com.zssq.knowledgeLib.controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 
 * @ClassName: RepositoryPraiseController  
 * @Description: 知识库点赞  
 * @author sry  
 * @date 2017年4月28日  
 *
 */
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.constants.CreditConstants;
import com.zssq.constants.KnowledgeLibConstants;
import com.zssq.constants.RelationConstants;
import com.zssq.dao.pojo.MessageIntegral;
import com.zssq.dao.pojo.RelationLike;
import com.zssq.dao.pojo.RepositoryPraise;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.kafka.core.KafkaProducerTemplate;
import com.zssq.knowledgeLib.vo.DoPortalKnowledgePraiseVo;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.ISysUserService;
import com.zssq.service.RelationThirdOperateService;
import com.zssq.service.RepositoryPraiseService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.vo.RelationDataVO;
import com.zssq.vo.RelationOperateVO;
import com.zssq.vo.RepositoryPraiseVo;
/**
 * 
 * @ClassName: RepositoryPraiseController  
 * @Description: 知识点赞  
 * @author sry  
 * @date 2017年6月13日  
 *
 */
@Controller
@RequestMapping("/knowledgeLib")
public class RepositoryPraiseController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private RepositoryPraiseService repositoryPraiseService;
	
	@Autowired
	private ISysUserService iSysUserService;
	
	@Autowired
	private RelationThirdOperateService relationThirdOperateService;
	
	// 引入kafka消息模板
	@SuppressWarnings("rawtypes")
	@Autowired
	protected KafkaProducerTemplate producerTeplate;
	/**
	 * 
	 * @Title: doPraise  
	 * @Description: 点赞
	 * @param param
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="doPraise",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON doPraise(@RequireValid DoPortalKnowledgePraiseVo param) throws BusinessException {
		
		//返回值
		ResultJSON resultJSON = null;
		
		try {
			//方法进入
			log.info("RepositoryPraiseController.doPraise:点赞或取消点赞");
			
			String objectCode = param.getObjectCode();//被点赞编号
			Byte actionType = Byte.valueOf(param.getActionType());//操作类型：1.知识；2.追加知识
			Byte actionClass = Byte.valueOf(param.getActionClass());//用户编号
			String userCode = param.getUserCode(); // 用户编号
			//String token = param.getToken(); // token
			
			// 查询人员信息
			SysUserInfo sysUserInfo = iSysUserService.selectByCode(userCode);
			if (sysUserInfo == null) {
				log.error("RepositoryPraiseController.doPraise：获取不到人员信息userCode=" + userCode);
				throw BusinessException.build("COMMON_400");
			}
			String orgCode = sysUserInfo.getManageOrgInfo().getSysOrgCode();
			String tenantCode = sysUserInfo.getTenantCode();
			long time = new Date().getTime(); // 当前时间
			//判断 追加 or 知识点赞
			if(KnowledgeLibConstants.REPOSITORY_PRAISE_TYPE_KG.equals(actionType)){
				//知识 需要通知 关系 
				if(KnowledgeLibConstants.REPOSITORY_ACTION_YES.equals(actionClass)){
					//点赞
					String praiseCode = UUIDHelper.getUUID();
					RepositoryPraise infoVo = new RepositoryPraise();
					infoVo.setPraiseCode(praiseCode);
					infoVo.setObjectCode(objectCode);
					infoVo.setCreateTime(time);
					infoVo.setModifyTime(time);
					infoVo.setOrgCode(orgCode);
					infoVo.setPraiseType(KnowledgeLibConstants.REPOSITORY_PRAISE_TYPE_KG);
					infoVo.setTenantCode(tenantCode);
					infoVo.setUserCode(userCode);
					
					boolean praiseFlag =repositoryPraiseService.savePraise(infoVo);
					if(!praiseFlag){
						log.error("RepositoryPraiseController.doPraise：操作失败");
						throw BusinessException.build("KNOWLEDGELIB_27001", "操作");
					}
					//关系点赞量
					RelationLike relationLike = new RelationLike();
					relationLike.setCreateTime(time);
					relationLike.setLikeCode(praiseCode);
					relationLike.setModifyTime(time);
					relationLike.setOrgCode(orgCode);
					relationLike.setSubjectClass(RelationConstants.RELATION_LIKE_KB);
					relationLike.setSubjectCode(objectCode);
					relationLike.setTenantCode(tenantCode);
					relationLike.setUserCode(userCode);
					boolean relationFlag = relationThirdOperateService.saveLike(relationLike);
					if(!relationFlag){
						log.error("RepositoryPraiseController.doPraise：知识关系点赞失败");
						throw BusinessException.build("KNOWLEDGELIB_27001", "操作");
					}
					//修改点赞量
					updateRelationNum(objectCode,time,KnowledgeLibConstants.REPOSITORY_ACTION_YES,KnowledgeLibConstants.REPOSITORY_ACTION_TYPE_PRAISE);
					
					// 积分操作
					MessageIntegral MessageIntegral = new MessageIntegral();
					MessageIntegral.setActionCode(CreditConstants.COMMAND_ADMIRE_PUBLISH);
					MessageIntegral.setAccountCode(userCode);
					MessageIntegral.setAccountType(CreditConstants.TYPE_INDIVIDUAL);
					MessageIntegral.setManageOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
					producerTeplate.send(CreditConstants.CREDIT_INCREMENT, JSONObject.toJSONString(MessageIntegral));
				}else{
					//取消点赞
					RepositoryPraiseVo rv = new RepositoryPraiseVo();
					rv.setObjectCode(objectCode);
					rv.setPraiseType(KnowledgeLibConstants.REPOSITORY_PRAISE_TYPE_KG);
					rv.setUserCode(userCode);
					boolean deletePraise = repositoryPraiseService.deletePraise(rv);
					if(!deletePraise){
						log.error("RepositoryPraiseController.doPraise：操作失败");
						throw BusinessException.build("KNOWLEDGELIB_27001", "操作");
					}
					RelationOperateVO relationOperateVo = new RelationOperateVO();
					relationOperateVo.setSubjectCode(objectCode);
					relationOperateVo.setUserCode(userCode);
					boolean relationFlag = relationThirdOperateService.deleteLike(relationOperateVo);
					if(!relationFlag){
						log.error("RepositoryPraiseController.doPraise：知识关系取消点赞失败");
						throw BusinessException.build("KNOWLEDGELIB_27001", "操作");
					}
					//修改点赞量
					updateRelationNum(objectCode,time,KnowledgeLibConstants.REPOSITORY_ACTION_NO,KnowledgeLibConstants.REPOSITORY_ACTION_TYPE_PRAISE);
					
					
					// 积分操作
					MessageIntegral MessageIntegral = new MessageIntegral();
					MessageIntegral.setActionCode(CreditConstants.COMMAND_ADMIRE_DEL);
					MessageIntegral.setAccountCode(userCode);
					MessageIntegral.setAccountType(CreditConstants.TYPE_INDIVIDUAL);
					MessageIntegral.setManageOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
					producerTeplate.send(CreditConstants.CREDIT_INCREMENT, JSONObject.toJSONString(MessageIntegral));
				}
			}else{
				//追加
				if(KnowledgeLibConstants.REPOSITORY_ACTION_YES.equals(actionClass)){
					//点赞
					String praiseCode = UUIDHelper.getUUID();
					RepositoryPraise infoVo = new RepositoryPraise();
					infoVo.setPraiseCode(praiseCode);
					infoVo.setObjectCode(objectCode);
					infoVo.setCreateTime(time);
					infoVo.setModifyTime(time);
					infoVo.setOrgCode(orgCode);
					infoVo.setPraiseType(KnowledgeLibConstants.REPOSITORY_PRAISE_TYPE_KGAPPEND);
					infoVo.setTenantCode(tenantCode);
					infoVo.setUserCode(userCode);
					
					boolean praiseFlag =repositoryPraiseService.savePraise(infoVo);
					if(!praiseFlag){
						log.error("RepositoryPraiseController.doPraise：操作失败");
						throw BusinessException.build("KNOWLEDGELIB_27001", "操作");
					}
					
					// 积分操作
					MessageIntegral MessageIntegral = new MessageIntegral();
					MessageIntegral.setActionCode(CreditConstants.COMMAND_ADMIRE_PUBLISH);
					MessageIntegral.setAccountCode(userCode);
					MessageIntegral.setAccountType(CreditConstants.TYPE_INDIVIDUAL);
					MessageIntegral.setManageOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
					producerTeplate.send(CreditConstants.CREDIT_INCREMENT, JSONObject.toJSONString(MessageIntegral));
				}else{
					//取消点赞
					RepositoryPraiseVo rv = new RepositoryPraiseVo();
					rv.setObjectCode(objectCode);
					rv.setPraiseType(KnowledgeLibConstants.REPOSITORY_PRAISE_TYPE_KGAPPEND);
					rv.setUserCode(userCode);
					boolean deletePraise = repositoryPraiseService.deletePraise(rv);
					if(!deletePraise){
						log.error("RepositoryPraiseController.doPraise：操作失败");
						throw BusinessException.build("KNOWLEDGELIB_27001", "操作");
					}
					
					// 积分操作
					MessageIntegral MessageIntegral = new MessageIntegral();
					MessageIntegral.setActionCode(CreditConstants.COMMAND_ADMIRE_DEL);
					MessageIntegral.setAccountCode(userCode);
					MessageIntegral.setAccountType(CreditConstants.TYPE_INDIVIDUAL);
					MessageIntegral.setManageOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
					producerTeplate.send(CreditConstants.CREDIT_INCREMENT, JSONObject.toJSONString(MessageIntegral));
				}
			}
			//返回结果
			resultJSON = new ResultJSON("COMMON_200", "点赞或取消点赞成功");
			resultJSON.setBody(new JSONObject());
			//方法完毕
			log.info("RepositoryPraiseController.doPraise:点赞或取消点赞");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("RepositoryPraiseController.doPraise", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return resultJSON;
		
	}
	/**
	 * 
	 * @Title: updateRelationNum  
	 * @Description: kafka 数量修改
	 * @param subjectCode
	 * @param modifyTime
	 * @param actioinClass
	 * @param actionType
	 * @throws BusinessException    参数  
	 * @return: void    返回类型
	 */
	@SuppressWarnings("unchecked")
	private void updateRelationNum(String subjectCode,Long modifyTime,Byte actioinClass,Byte actionType) throws BusinessException {
		try{
			RelationDataVO rv = new RelationDataVO();
			rv.setModifyTime(modifyTime);
			rv.setSubjectCode(subjectCode);
			if(KnowledgeLibConstants.REPOSITORY_ACTION_TYPE_COLLECT.equals(actionType)){
				rv.setUpdateClass(RelationConstants.RELATION_THIRD_DATANUM_TYPE_COLLECT);
				if(KnowledgeLibConstants.REPOSITORY_ACTION_YES.equals(actioinClass)){
					rv.setUpdateNumber(KnowledgeLibConstants.NUM_ONE);
				}else{
					rv.setUpdateNumber(KnowledgeLibConstants.NUM_BELOW_ONE);
				}
			}else if(KnowledgeLibConstants.REPOSITORY_ACTION_TYPE_PRAISE.equals(actionType)){
				rv.setUpdateClass(RelationConstants.RELATION_THIRD_DATANUM_TYPE_LIKE);
				if(KnowledgeLibConstants.REPOSITORY_ACTION_YES.equals(actioinClass)){
					rv.setUpdateNumber(KnowledgeLibConstants.NUM_ONE);
				}else{
					rv.setUpdateNumber(KnowledgeLibConstants.NUM_BELOW_ONE);
				}
			}else{
				return;
			}
			producerTeplate.send(RelationConstants.RELATION_TOPIC, JSONObject.toJSONString(rv));
		}catch(Exception e){
			throw new BusinessException("关系中更新数量出现异常",e);
		}
		
	}
	
}
