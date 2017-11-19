package com.zssq.knowledgeLib.controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.constants.KnowledgeLibConstants;
import com.zssq.constants.RelationConstants;
import com.zssq.dao.pojo.RelationDynamic;
import com.zssq.dao.pojo.RepositoryKnowledge;
import com.zssq.dao.pojo.RepositoryShare;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.kafka.core.KafkaProducerTemplate;
import com.zssq.knowledgeLib.vo.DoPortalKnowledgeShareVo;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.ISysUserService;
import com.zssq.service.RelationThirdDynamicService;
import com.zssq.service.RepositoryKnowledgeService;
import com.zssq.service.RepositoryShareService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.vo.RelationDataVO;
import com.zssq.vo.RepositoryKnowledgeVo;
import com.zssq.vo.RepositoryShareVo;
/**
 * 
 * @ClassName: RepositoryShareController  
 * @Description: 知识库分享  
 * @author sry  
 * @date 2017年4月28日  
 *
 */
@Controller
@RequestMapping("/knowledgeLib")
public class RepositoryShareController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private RepositoryShareService repositoryShareService;
	
	@Autowired
	private ISysUserService iSysUserService;
	
	// 引入kafka消息模板
	@SuppressWarnings("rawtypes")
	@Autowired
	protected KafkaProducerTemplate producerTeplate;
	@Autowired
	private RepositoryKnowledgeService repositoryKnowledgeService;
	
	@Autowired
	private RelationThirdDynamicService relationThirdDynamicService;
	/**
	 * 
	 * @Title: doShare  
	 * @Description: 知识分享
	 * @param param
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="doShare",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON doShare(@RequireValid DoPortalKnowledgeShareVo param) throws BusinessException {
		
		//返回值
		ResultJSON resultJSON = null;
		
		try {
			//方法进入
			log.info("RepositoryShareController.doShare: 知识分享");
			
			String knowledgeCode = param.getKnowledgeCode();//知识编号
			String userCode = param.getUserCode(); // 用户code
			//拼接参数
			RepositoryShareVo infoVo = new RepositoryShareVo();
			infoVo.setKnowledgeCode(knowledgeCode);
			infoVo.setUserCode(userCode);
			//验证知识是否存在
			RepositoryKnowledgeVo qo = new RepositoryKnowledgeVo();
			qo.setKnowledgeCode(knowledgeCode);
			RepositoryKnowledge rk = repositoryKnowledgeService.getBaseKnowlegeByCond(qo);
			if(rk==null){
				log.error("RepositoryShareController.doShare：获取不到知识信息knowledgeCode=" + knowledgeCode);
				throw BusinessException.build("COMMON_400");
			}
			// 查询人员信息
			SysUserInfo sysUserInfo = iSysUserService.selectByCode(userCode);
			if (sysUserInfo == null) {
				log.error("RepositoryShareController.doShare：获取不到人员信息userCode=" + userCode);
				throw BusinessException.build("COMMON_400");
			}
			if(sysUserInfo.getManageOrgInfo() == null){
				log.error("RepositoryShareController.doShare：获取不到ManageOrgInfo userCode=" + userCode);
				throw BusinessException.build("COMMON_400");
			}
			String orgCode = sysUserInfo.getManageOrgInfo().getSysOrgCode();//组织编号
			String tenantCode = sysUserInfo.getTenantCode();//租户编号
			Long time =  new Date().getTime(); // 当前时间
			
			RepositoryShare rs = new RepositoryShare();
			rs.setCreateTime(time);
			rs.setKnowledgeCode(knowledgeCode);
			rs.setModifyTime(time);
			rs.setOrgCode(orgCode);
			rs.setShareCode(UUIDHelper.getUUID());
			rs.setTenantCode(tenantCode);
			rs.setUserCode(userCode);
			// 动态信息
			RelationDynamic relationDynamic = new RelationDynamic();
			relationDynamic.setDynamicCode(UUIDHelper.getUUID());
			relationDynamic.setTenantCode(sysUserInfo.getTenantCode());
			relationDynamic.setOrgCode(orgCode);
			relationDynamic.setCreateTime(time);
			relationDynamic.setModifyTime(time);
			relationDynamic.setDynamicClass(RelationConstants.RELATION_DYNAMIC_KB);
			relationDynamic.setDynamicDepend(RelationConstants.RELATION_DYNAMIC_DEPEND_USER);
			relationDynamic.setUserCode(userCode);
			relationDynamic.setOperateTime(time);
			relationDynamic.setSubjectCode(knowledgeCode);
			relationDynamic.setDynamicIsDelete(RelationConstants.RELATION_NO);
			relationDynamic.setDynamicIsShield(RelationConstants.RELATION_NO);
			relationDynamic.setIsSubjectShow(RelationConstants.RELATION_YES);
			relationDynamic.setIsSubjectDataShow(RelationConstants.RELATION_YES);
			
			// 保存动态信息
			boolean saveDynamicFlag = relationThirdDynamicService.saveDynamic(relationDynamic, null, null, null);
			if (!saveDynamicFlag) {
				log.error("RepositoryShareController.doShare：保存动态信息失败");
				throw BusinessException.build("KNOWLEDGELIB_27001", "操作");
			}
			//修改 关系 内容 分享量
			updateRelationShareNum(knowledgeCode,time);
			
			boolean shareFlag = repositoryShareService.saveShare(rs);
			if(!shareFlag){
				log.error("RepositoryShareController.doShare： 知识分享失败");
				throw BusinessException.build("KNOWLEDGELIB_27001", " 知识分享点赞");
			}else{
				resultJSON = new ResultJSON("COMMON_200", " 知识分享成功");
				resultJSON.setBody(new JSONObject());
				//方法完毕
				log.info("RepositoryShareController.doShare: 知识分享");
			}
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("RepositoryShareController.doShare", e);
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
	private void updateRelationShareNum(String subjectCode,Long modifyTime) throws BusinessException {
		try{
			RelationDataVO rv = new RelationDataVO();
			rv.setModifyTime(modifyTime);
			rv.setSubjectCode(subjectCode);
			rv.setUpdateNumber(KnowledgeLibConstants.NUM_ONE);
			rv.setUpdateClass(RelationConstants.RELATION_THIRD_DATANUM_TYPE_SHARE);
			producerTeplate.send(RelationConstants.RELATION_TOPIC, JSONObject.toJSONString(rv));
		}catch(Exception e){
			throw new BusinessException("关系中更新数量出现异常",e);
		}
		
	}
}
