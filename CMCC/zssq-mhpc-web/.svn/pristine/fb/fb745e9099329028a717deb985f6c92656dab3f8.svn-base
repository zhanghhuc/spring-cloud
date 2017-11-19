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
import com.zssq.dao.pojo.MessageIntegral;
import com.zssq.dao.pojo.RepositoryKnowledgeAppend;
import com.zssq.dao.pojo.SysOrgInfo;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.kafka.core.KafkaProducerTemplate;
import com.zssq.knowledgeLib.vo.AppendKnowledgeVo;
import com.zssq.knowledgeLib.vo.GetPortalKnowledgeAppendVo;
import com.zssq.model.RepositoryKnowledgeAppendMH;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.ISysUserService;
import com.zssq.service.RepositoryKnowledgeAppendService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.utils.StringTools;
import com.zssq.vo.RepositoryKnowledgeAppendVo;
/**
 * 
 * @ClassName: RepositoryKnowledgeAppendController  
 * @Description: 知识库追加  
 * @author sry  
 * @date 2017年6月13日  
 *
 */
@Controller
@RequestMapping("/knowledgeLib")
public class RepositoryKnowledgeAppendController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private RepositoryKnowledgeAppendService knowledgeAppendService;
	@Autowired
	private ISysUserService sysUserService;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	protected KafkaProducerTemplate producerTeplate;
	/**
	 * 
	 * @Title: getPortalKnowledgeAppend  
	 * @Description: 获取知识追加内容列表
	 * @param param
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="getPortalKnowledgeAppend",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON getPortalKnowledgeAppend(@RequireValid GetPortalKnowledgeAppendVo param) throws BusinessException {
		
		//返回值
		ResultJSON resultJSON = null;
		
		try {
			//方法进入
			log.info("RepositoryKnowledgeAppendController.getPortalKnowledgeAppend:获取知识追加内容列表");
			
			String knowledgeCode = param.getKnowledgeCode();//知识库编号
			String userCode = param.getUserCode();//用户编号
			String pageSize = param.getPageSize(); // 每页条数
			String pageNo = param.getPageNo(); // 页码
			String isSelfAppend = param.getIsSelfAppend();
			//拼接参数
			
			PageParam pageParam = new PageParam(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			RepositoryKnowledgeAppendVo infoVo = new RepositoryKnowledgeAppendVo();
			infoVo.setKnowledgeCode(knowledgeCode);
			infoVo.setUserCode(userCode);
			Byte seltAppend = null;
			if(StringTools.isNotEmpty(isSelfAppend)){
				seltAppend = Byte.valueOf(isSelfAppend);
			}
			infoVo.setIsSelfAppend(seltAppend);
			
			PageBean pageBean = knowledgeAppendService.getPortalKnowledgeAppend(pageParam,infoVo);
			
			if (pageBean == null) {
				log.error("RepositoryKnowledgeAppendController.getPortalKnowledgeAppend：获取知识追加内容列表");
				throw BusinessException.build("RELATION_19002", "查询");
			}
			
			// 获取数据
			List<RepositoryKnowledgeAppendMH> recordList = pageBean.getRecordList();
			//组装 返回参数
			JSONArray ja = new JSONArray();
			for(RepositoryKnowledgeAppendMH ri:recordList){
				JSONObject jo = new JSONObject();
				jo.put("appendCode", StringTools.formatToString(ri.getAppendCode()));
				jo.put("createTime", StringTools.formatToString(ri.getCreateTime()));
				jo.put("appendContent", StringTools.formatToString(ri.getAppendContent()));
				jo.put("appendDigest", StringTools.formatToString(ri.getAppendDigest()));
				jo.put("isSelfAppend", StringTools.formatToString(ri.getIsSelfAppend()));
				jo.put("userCode", StringTools.formatToString(ri.getUserCode()));
				jo.put("praiseNum", StringTools.formatToString(ri.getPraiseNum()));
				jo.put("isPraised", StringTools.formatToString(ri.getIsPraised()));
				//拼接用户参数
				String userName = "";
				String userPhotoUrl = "";
				String orgCode = "";
				String orgName = "";
				if(StringTools.isNotEmpty(ri.getUserCode())){
					// 获取动态所属人员信息
					SysUserInfo sysUserInfo = sysUserService.selectByCode(ri.getUserCode());
					if(sysUserInfo!=null){
						userName = sysUserInfo.getUserName();
						userPhotoUrl = sysUserInfo.getHeadPortrait();
						orgCode = sysUserInfo.getOrgCode();
						SysOrgInfo manageOrgInfo = sysUserInfo.getManageOrgInfo();
						if(manageOrgInfo!=null){
							orgName = manageOrgInfo.getSysOrgFullname();
						}
					}
				}
				jo.put("userName", StringTools.formatToString(userName));
				jo.put("userPhotoUrl", StringTools.formatToString(userPhotoUrl));
				jo.put("orgCode", StringTools.formatToString(orgCode));
				jo.put("orgName", StringTools.formatToString(orgName));
				ja.add(jo);
			}
			JSONObject body = new JSONObject();
			body.put("appendList", ja);
			body.put("total", pageBean.getTotalCount());
			resultJSON = new ResultJSON("COMMON_200", "查询知识库下知识追加列表");
			resultJSON.setBody(body);
			
			//方法出去
			log.info("RepositoryKnowledgeAppendController.getPortalKnowledgeAppend:获取知识追加内容列表");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("RepositoryKnowledgeAppendController.getPortalKnowledgeAppend", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return resultJSON;
		
	}
	
	/**
	 * 
	 * @Title: appendKnowledge  
	 * @Description: 追加知识
	 * @param param
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@ResponseBody
	@RequestMapping(value = "appendKnowledge",method=RequestMethod.POST)
	public ResultJSON appendKnowledge(@RequireValid AppendKnowledgeVo param) throws BusinessException{
	ResultJSON resultJSON = null;
		
		try {
			//方法进入
			log.info("RepositoryKnowledgeAppendController.appendKnowledge:发布知识追加");
			
			String knowledgeCode = param.getKnowledgeCode();//知识编号
			String appendContent = param.getAppendContent();//追加内容
			String userCode = param.getUserCode(); // 用户编号
			String appendDigest = param.getAppendDigest();//追加摘要
			String orgCode = "";
			String tenantCode = "";
			SysUserInfo sysUserInfo = sysUserService.selectByCode(userCode);
			if(sysUserInfo==null){
				log.error("RepositoryKnowledgeAppendController.appendKnowledge：获取不到人员信息userCode=" + userCode);
				throw BusinessException.build("COMMON_400");
			}
			orgCode = sysUserInfo.getManageOrgInfo().getSysOrgCode();
			tenantCode = sysUserInfo.getTenantCode();
			
			
			//拼接参数
			Long now = new Date().getTime();
			RepositoryKnowledgeAppend infoVo = new RepositoryKnowledgeAppend();
			infoVo.setKnowledgeCode(knowledgeCode);
			infoVo.setUserCode(userCode);
			infoVo.setOrgCode(orgCode);
			infoVo.setTenantCode(tenantCode);
			infoVo.setAppendContent(appendContent);
			infoVo.setAppendCode(UUIDHelper.getUUID());
			infoVo.setCreateTime(now);
			infoVo.setModifyTime(now);
			infoVo.setAppendDigest(appendDigest);
			Boolean addFlag = knowledgeAppendService.addPortalKnowledgeAppendForMH(infoVo);
			
			if(!addFlag){
				log.error("RepositoryKnowledgeAppendController.appendKnowledge：发布知识失败");
				throw BusinessException.build("KNOWLEDGELIB_27001", "发布知识");
			}else{
				resultJSON = new ResultJSON("COMMON_200", "发布知识");
				resultJSON.setBody(new JSONObject());
				
				//方法出去
				log.info("RepositoryKnowledgeAppendController.appendKnowledge:发布知识");
			}
			int count = knowledgeAppendService.selectCountForIsSelf(infoVo);
			if(count <= 0){
				// 积分操作
				MessageIntegral MessageIntegral = new MessageIntegral();
				MessageIntegral.setActionCode(CreditConstants.COMMAND_FILETOLORE_UPLOAD);
				MessageIntegral.setAccountCode(userCode);
				MessageIntegral.setAccountType(CreditConstants.TYPE_INDIVIDUAL);
				MessageIntegral.setManageOrgCode(sysUserInfo.getManageOrgInfo().getSysOrgCode());
				producerTeplate.send(CreditConstants.CREDIT_INCREMENT, JSONObject.toJSONString(MessageIntegral));
			}
			
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("RepositoryKnowledgeAppendController.appendKnowledge", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return resultJSON;

	}
	
}
