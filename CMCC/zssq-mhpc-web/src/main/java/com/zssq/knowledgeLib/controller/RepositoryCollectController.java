package com.zssq.knowledgeLib.controller;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.constants.CreditConstants;
import com.zssq.constants.KnowledgeLibConstants;
import com.zssq.constants.RelationConstants;
import com.zssq.dao.pojo.MessageIntegral;
import com.zssq.dao.pojo.RelationCollect;
import com.zssq.dao.pojo.RepositoryCollect;
import com.zssq.dao.pojo.SysOrgInfo;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.kafka.core.KafkaProducerTemplate;
import com.zssq.knowledgeLib.vo.DoPortalKnowledgeCollectVo;
import com.zssq.knowledgeLib.vo.GetUserCollectKnowledgeListVo;
import com.zssq.model.RepositoryKnowledgeModelForCollect;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.ISysUserService;
import com.zssq.service.RelationThirdOperateService;
import com.zssq.service.RepositoryCollectService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.utils.StringTools;
import com.zssq.vo.RelationDataVO;
import com.zssq.vo.RelationOperateVO;
import com.zssq.vo.RepositoryCollectVo;
import com.zssq.vo.RepositoryKnowledgeVo;
/**
 * 
 * @ClassName: RepositoryCollectController  
 * @Description: 知识库收藏  
 * @author sry  
 * @date 2017年4月28日  
 *
 */
@Controller
@RequestMapping("/knowledgeLib")
public class RepositoryCollectController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private RepositoryCollectService repositoryCollectService;
	
	@Autowired
	private ISysUserService sysUserService;
	
	@Autowired
	private RelationThirdOperateService relationThirdOperateService;
	
	// 引入kafka消息模板
	@SuppressWarnings("rawtypes")
	@Autowired
	protected KafkaProducerTemplate producerTeplate;
	/**
	 * 
	 * @Title: doCollect  
	 * @Description: 收藏
	 * @param param
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="doCollect",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON doCollect(@RequireValid DoPortalKnowledgeCollectVo param) throws BusinessException {
		
		//返回值
		ResultJSON resultJSON = null;
		
		try {
			//方法进入
			log.info("RepositoryCollectController.doCollect:收藏或取消收藏");
			
			String knowledgeCode = param.getKnowledgeCode();//被收藏编号
			String userCode = param.getUserCode(); // 每页条数
			Byte actionClass = Byte.valueOf(param.getActionClass());//执行动作：1.收藏；2.取消收藏
			String orgCode = "";
			String tenantCode = "";
			SysUserInfo sysUserInfo = sysUserService.selectByCode(userCode);
			if(sysUserInfo==null){
				log.error("RepositoryKnowledgeController.publishKnowledge：获取不到人员信息userCode=" + userCode);
				throw BusinessException.build("COMMON_400");
			}
			orgCode = sysUserInfo.getManageOrgInfo().getSysOrgCode();
			tenantCode = sysUserInfo.getTenantCode();
			Long now = new Date().getTime();
			//拼接参数
			if(KnowledgeLibConstants.REPOSITORY_ACTION_YES.equals(actionClass)){
				//收藏
				RepositoryCollect infoVo = new RepositoryCollect();
				
				infoVo.setKnowledgeCode(knowledgeCode);
				infoVo.setUserCode(userCode);
				infoVo.setOrgCode(orgCode);
				infoVo.setTenantCode(tenantCode);
				infoVo.setCollectCode(UUIDHelper.getUUID());
				infoVo.setCreateTime(now);
				infoVo.setModifyTime(now);
				boolean praiseFlag = repositoryCollectService.doCollect(infoVo);
				if(!praiseFlag){
					log.error("RepositoryCollectController.doShare：收藏或取消收藏失败");
					throw BusinessException.build("KNOWLEDGELIB_27001", "收藏或取消收藏");
				}
				//关系收藏量
				RelationCollect relationCollect = new RelationCollect();
				relationCollect.setCreateTime(now);
				relationCollect.setCollectCode(UUIDHelper.getUUID());
				relationCollect.setModifyTime(now);
				relationCollect.setOrgCode(orgCode);
				relationCollect.setSubjectClass(RelationConstants.RELATION_COLLECT_KB);
				relationCollect.setSubjectCode(knowledgeCode);
				relationCollect.setTenantCode(tenantCode);
				relationCollect.setUserCode(userCode);
				boolean relationFlag = relationThirdOperateService.saveCollect(relationCollect);
				if(!relationFlag){
					log.error("RepositoryPraiseController.doCollect：知识关系收藏失败");
					throw BusinessException.build("KNOWLEDGELIB_27001", "操作");
				}
				//修改收藏量
				updateRelationNum(knowledgeCode,now,KnowledgeLibConstants.REPOSITORY_ACTION_YES,KnowledgeLibConstants.REPOSITORY_ACTION_TYPE_COLLECT);
				
				// 积分操作
				MessageIntegral MessageIntegral = new MessageIntegral();
				MessageIntegral.setActionCode(CreditConstants.COMMAND_CONTENT_COLLECT);
				MessageIntegral.setAccountCode(userCode);
				MessageIntegral.setAccountType(CreditConstants.TYPE_INDIVIDUAL);
				MessageIntegral.setManageOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
				producerTeplate.send(CreditConstants.CREDIT_INCREMENT, JSONObject.toJSONString(MessageIntegral));
				
			}else{
				//取消收藏
				RepositoryCollectVo rv = new RepositoryCollectVo();
				rv.setKnowledgeCode(knowledgeCode);
				rv.setUserCode(userCode);
				boolean deletePraise = repositoryCollectService.deleteCollect(rv);
				if(!deletePraise){
					log.error("RepositoryPraiseController.doCollect：操作失败");
					throw BusinessException.build("KNOWLEDGELIB_27001", "操作");
				}
				RelationOperateVO relationOperateVo = new RelationOperateVO();
				relationOperateVo.setSubjectCode(knowledgeCode);
				relationOperateVo.setUserCode(userCode);
				boolean relationFlag = relationThirdOperateService.deleteCollect(relationOperateVo);
				if(!relationFlag){
					log.error("RepositoryPraiseController.doCollect：知识关系取消收藏失败");
					throw BusinessException.build("KNOWLEDGELIB_27001", "操作");
				}
				//修改收藏量
				updateRelationNum(knowledgeCode,now,KnowledgeLibConstants.REPOSITORY_ACTION_NO,KnowledgeLibConstants.REPOSITORY_ACTION_TYPE_COLLECT);
				
				// 积分操作
				MessageIntegral MessageIntegral = new MessageIntegral();
				MessageIntegral.setActionCode(CreditConstants.COMMAND_CONTENT_COLLECTCANCEL);
				MessageIntegral.setAccountCode(userCode);
				MessageIntegral.setAccountType(CreditConstants.TYPE_INDIVIDUAL);
				MessageIntegral.setManageOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
				producerTeplate.send(CreditConstants.CREDIT_INCREMENT, JSONObject.toJSONString(MessageIntegral));
			}
			
			
			resultJSON = new ResultJSON("COMMON_200", "收藏或取消收藏成功");
			resultJSON.setBody(new JSONObject());
			//方法完毕
			log.info("RepositoryCollectController.doCollect:收藏或取消收藏");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("RepositoryCollectController.doCollect", e);
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
	
	
	
	/**
	 * 
	 * @Title: getUserCollectKnowledgeList  
	 * @Description: 查询个人收藏知识列表
	 * @param param
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="getUserCollectKnowledgeList",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON getUserCollectKnowledgeList(@RequireValid GetUserCollectKnowledgeListVo param) throws BusinessException {
		
		//返回值
		ResultJSON resultJSON = null;
		
		try {
			//方法进入
			log.info("RepositoryCollectController.getUserCollectKnowledgeList:查询个人收藏知识列表");
			
			String userCode = param.getUserCode(); 
			String pageNo = param.getPageNo();
			String pageSize = param.getPageSize();
			String orgCode = "";
			SysUserInfo sysUserInfo = sysUserService.selectByCode(userCode);
			if(sysUserInfo==null){
				log.error("RepositoryCollectController.getUserCollectKnowledgeList：获取不到人员信息userCode=" + userCode);
				throw BusinessException.build("COMMON_400");
			}
			orgCode = sysUserInfo.getManageOrgInfo().getSysOrgCode();
			PageParam pageParam = new PageParam(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			RepositoryKnowledgeVo infoVo = new RepositoryKnowledgeVo();
			infoVo.setUserCode(userCode);
			infoVo.setOrgCode(orgCode);
			PageBean pageBean = repositoryCollectService.getUserCollectKnowledgeList(pageParam,infoVo);
			
			if (pageBean == null) {
				log.error("RepositoryCollectController.getUserCollectKnowledgeList：查询失败");
				throw BusinessException.build("KNOWLEDGELIB_27001", "查询");
			}
			
			// 获取数据
			List<RepositoryKnowledgeModelForCollect> recordList = pageBean.getRecordList();
			//组装 返回参数
			JSONArray ja = new JSONArray();
			for(RepositoryKnowledgeModelForCollect ri:recordList){
				JSONObject jo = new JSONObject();
				jo.put("knowledgeCode", StringTools.formatToString(ri.getKnowledgeCode()));
				jo.put("knowledgeTitle", StringTools.formatToString(ri.getKnowledgeTitle()));
				jo.put("knowledgeDigest", StringTools.formatToString(ri.getKnowledgeDigest()));
				jo.put("contentCode", StringTools.formatToString(ri.getContentCode()));
				jo.put("createTime", StringTools.formatToString(ri.getCreateTime()));
				jo.put("userCode", StringTools.formatToString(ri.getUserCode()));
				//拼接用户参数
				String userName = "";
				String userPhotoUrl = "";
				String orgCodeOrigin = "";
				String orgName = "";
				if(StringTools.isNotEmpty(ri.getUserCode())){
					// 获取动态所属人员信息
					SysUserInfo sysUserInfoOrigin = sysUserService.selectByCode(ri.getUserCode());
					if(sysUserInfo!=null){
						userName = sysUserInfoOrigin.getUserName();
						userPhotoUrl = sysUserInfoOrigin.getHeadPortrait();
						orgCodeOrigin = sysUserInfoOrigin.getOrgCode();
						SysOrgInfo manageOrgInfo = sysUserInfoOrigin.getManageOrgInfo();
						if(manageOrgInfo!=null){
							orgName = manageOrgInfo.getSysOrgFullname();
						}
					}
				}
				jo.put("userName", StringTools.formatToString(userName));
				jo.put("userPhotoUrl", StringTools.formatToString(userPhotoUrl));
				jo.put("orgCode", StringTools.formatToString(orgCodeOrigin));
				jo.put("orgName", StringTools.formatToString(orgName));
				jo.put("collectTime", StringTools.formatToString(ri.getCollectTime()));

				ja.add(jo);
			}
			JSONObject body = new JSONObject();
			body.put("kgList", ja);
			body.put("total", pageBean.getTotalCount());
			resultJSON = new ResultJSON("COMMON_200", "查询个人收藏知识列表");
			resultJSON.setBody(body);
			
			//方法出去
			log.info("RepositoryCollectController.getUserCollectKnowledgeList:查询个人收藏知识列表");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("RepositoryCollectController.getUserCollectKnowledgeList", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return resultJSON;
		
	}
}
