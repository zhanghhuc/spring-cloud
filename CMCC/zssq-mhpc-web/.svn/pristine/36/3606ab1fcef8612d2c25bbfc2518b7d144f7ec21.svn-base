package com.zssq.honor.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.constants.CreditConstants;
import com.zssq.constants.HonorConstants;
import com.zssq.dao.pojo.HonorAwardRecord;
import com.zssq.dao.pojo.HonorComment;
import com.zssq.dao.pojo.HonorCommentReply;
import com.zssq.dao.pojo.HonorDefinition;
import com.zssq.dao.pojo.HonorPraise;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.dao.pojo.TeamInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.honor.vo.AwardingHonorVo;
import com.zssq.honor.vo.CommentingVo;
import com.zssq.honor.vo.DeletingOwnSpeechVo;
import com.zssq.honor.vo.GetAwardListVo;
import com.zssq.honor.vo.GetAwardRecordDetailVo;
import com.zssq.honor.vo.GetAwardWallVo;
import com.zssq.honor.vo.GetCommentListForLocationVo;
import com.zssq.honor.vo.GetCommentListVo;
import com.zssq.honor.vo.GetCommentListWithPageVo;
import com.zssq.honor.vo.GetCommentReplyListForLocationVo;
import com.zssq.honor.vo.GetHonorListVo;
import com.zssq.honor.vo.GetReplyListVo;
import com.zssq.honor.vo.GetReplyListWithPageVo;
import com.zssq.honor.vo.PraiseOrNotVo;
import com.zssq.honor.vo.ReplyingVo;
import com.zssq.honor.vo.RevokingVo;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.HonorService;
import com.zssq.service.ITeamElectService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.utils.StringTools;
import com.zssq.vote.controller.BaseController;

/**
 * @ClassName HonorController
 * @Description 门户荣誉管理
 * @author LiuYunLong
 * @date 2017年3月17日 上午9:22:26
 * @version 1.0
 * @since JDK 1.7
 */
@RequestMapping("/honor")
@Controller
public class HonorController extends BaseController {
	
	@Autowired
	private HonorService honorService;
	
	@Autowired
	private ITeamElectService teamElectService;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * @Function awardingHonor
	 * @Description 颁发荣誉
	 * @param inVo 
	 * @return ResultJSON
	 * @throws BusinessException
	 */
	@RequestMapping(value = "awarding", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON awardingHonor(@RequireValid AwardingHonorVo inVo) throws BusinessException {
		try {
			//准备参数
			HonorAwardRecord honorAwardRecord = new HonorAwardRecord();
			honorAwardRecord.setHonorCode(inVo.getHonorCode());
			honorAwardRecord.setHonorName(inVo.getHonorName());
			honorAwardRecord.setHonorUrl(inVo.getHonorUrl());
			if (StringTools.isNotEmpty(inVo.getAgentCode())) {
				honorAwardRecord.setAgentCode(inVo.getAgentCode());
			}
			honorAwardRecord.setAwarderCode(inVo.getAwarderCode());
			
			//根据awarderCode 查询 awarderName 和 awarderPosition
			SysUserInfo awarderInfo = getUserInfo(inVo.getAwarderCode());
			String operateOrgCode = "";
			if(awarderInfo != null){
				honorAwardRecord.setAwarderName(awarderInfo.getUserName());
				operateOrgCode = awarderInfo.getManageOrgInfo().getSysOrgCode();
				honorAwardRecord.setAwarderPosition("");
			}else{
				honorAwardRecord.setAwarderName("");
				honorAwardRecord.setAwarderPosition("");
			}
			honorAwardRecord.setHonoreeType(Byte.valueOf(inVo.getHonoreeType()));
			honorAwardRecord.setHonoreeCode(inVo.getHonoreeCode());
			honorAwardRecord.setAwardReason(inVo.getAwardReason());
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			honorAwardRecord.setTenantCode(tenantCode);
			honorAwardRecord.setOrgCode(orgCode);
			List<String> codeList = honorService.addAwardingHonor(honorAwardRecord);
			
			List<String> teamCodes = new ArrayList<>();
			StringBuilder awardCode = new StringBuilder();
			for (String code : codeList) {
				try {
					/** 1、查询荣誉授予记录 */
					HonorAwardRecord record = honorService.getAwardHonorRecordInfoByCode(code,tenantCode);
					if(null != record){
						/** 2、若是授予对象为班组 查看班组是否解散 */
						Byte isDissolve = 0;
						if (HonorConstants.HONOREE_TYPE_2.equals(record.getHonoreeType())) {
							// 授予班组荣誉
							TeamInfo teamInfo = getTeamInfoByCode(record.getHonoreeCode());
							isDissolve = teamInfo.getIsDissolve();
							//班组没有解散的情况
							if(HonorConstants.NO.equals(isDissolve)){
								// 拼装班组的Code
								teamCodes.add(record.getHonoreeCode());
							}
						}
						/** 3、组织参数 发布动态 */
						String dynamicCode = UUIDHelper.getUUID();
						record.setDynamicCode(dynamicCode);
						publishDynamicOfAwardHonor(record,operateOrgCode,isDissolve);

						/** 4、发送"每获得一次荣誉"行为信息  默认发放1个金币  */
						// 班组
						if (HonorConstants.HONOREE_TYPE_2.equals(record.getHonoreeType())) {
							noticeAction(orgCode, CreditConstants.COMMAND_HONOR_ATTAIN, record.getHonoreeCode(), CreditConstants.TYPE_TEAM);			
						}
						// 个人
						if (HonorConstants.HONOREE_TYPE_1.equals(record.getHonoreeType())){
							noticeAction(orgCode, CreditConstants.COMMAND_HONOR_ATTAIN, record.getHonoreeCode(), CreditConstants.TYPE_INDIVIDUAL);	
						}
					}else{
						throw BusinessException.build("HONOR_21002", "荣誉授予记录不存在");
					}
				} catch (Exception e) {
					// 动态发布失败，则进行数据回滚
					log.info("动态发布失败，进行授予荣誉记录数据回滚操作...");
					honorService.deleteAwardRecord(honorAwardRecord.getTenantCode(), code);
					log.error("动态发布失败，异常信息：", e);
					throw e;
				}
				awardCode.append(code).append("|");
			}
			if (codeList.size() > 0) {
				awardCode.substring(0, awardCode.length() - 1);
			}
			// 颁发班组荣誉时更改状态
			teamElectService.awardHonor(teamCodes);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("awardCode", awardCode.toString());
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(jsonObject);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("HonorController.awardingHonor", e);
			throw BusinessException.build("COMMON_400");
		}
	}


	/**
	 * @Function getHonorList
	 * @Description 查询荣誉列表
	 * @param inVo
	 * @return ResultJSON
	 * @throws BusinessException
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON getHonorList(@RequireValid GetHonorListVo inVo) throws BusinessException {
		try {
			Integer pageNo = 0;
			Integer pageSize  = 0;
			//判断是否进行分页操作
			if (inVo.getIsPageFlag().equals(String.valueOf(HonorConstants.NO))) {
				pageNo = 0;
				pageSize = Integer.MAX_VALUE;
			}
			if (inVo.getIsPageFlag().equals(String.valueOf(HonorConstants.YES))) {
				if (StringTools.isNotEmpty(inVo.getPageNo())) {
					pageNo = Integer.valueOf(inVo.getPageNo());
				} else {
					throw BusinessException.build("COMMON_402", "pageNo");
				}

				if (StringTools.isNotEmpty(inVo.getPageSize())) {
					pageSize = Integer.valueOf(inVo.getPageSize());
				} else {
					throw BusinessException.build("COMMON_402", "pageSize");
				}
			}
			
			//设置分页参数
			PageParam pagePrarm = new PageParam(pageNo, pageSize);
			
			//设置查询条件
			HonorDefinition honorDefinition = new HonorDefinition();
			honorDefinition.setHonorName(inVo.getHonorName());
			if (StringTools.isNotEmpty(inVo.getHonorType())) {
				honorDefinition.setHonorType(Byte.valueOf(inVo.getHonorType()));
			}
			
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			honorDefinition.setTenantCode(tenantCode);
			honorDefinition.setOrgCode(orgCode);
			
			//调用查询服务
			PageBean pageBean = honorService.getHonorList(pagePrarm,honorDefinition);
			List<HonorDefinition> honorList = pageBean.getRecordList();
			
			//组织返回荣誉信息
			JSONArray jsonArray = new JSONArray();
			for(HonorDefinition honor : honorList){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("honorCode", honor.getHonorDefinitionCode());
				jsonObject.put("honorName", honor.getHonorName());
				jsonObject.put("honorType", StringTools.formatToString(honor.getHonorType()));
				jsonObject.put("honorUrl", honor.getHonorUrl());
				jsonObject.put("remark", StringTools.formatToString(honor.getRemark()));
				jsonArray.add(jsonObject);
			}
			
			JSONObject jo = new JSONObject();
			jo.put("totalCount", pageBean.getTotalCount());
			jo.put("honorList", JSON.toJSON(jsonArray));
			//设置返回信息
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(jo);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("HonorController.getHonorList", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * @Function getAwardRecordList
	 * @Description 查询荣誉颁发记录列表
	 * @param inVo
	 * @return ResultJSON
	 * @throws BusinessException
	 */
	@RequestMapping(value = "record/list", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON getAwardRecordList(@RequireValid GetAwardListVo inVo) throws BusinessException {
		try {
			//准备参数
			PageParam pageParam = new PageParam(Integer.valueOf(inVo.getPageNo()), Integer.valueOf(inVo.getPageSize()));
			HonorAwardRecord honorAwardRecord = new HonorAwardRecord();
			honorAwardRecord.setAwarderCode(inVo.getAwarderCode());
			if (StringTools.isNotEmpty(inVo.getIsRevoked())) {
				honorAwardRecord.setIsRevoked(Byte.valueOf(inVo.getIsRevoked()));
			}
			// 判断颁发人是否是领导 不是:代发人（agentCode == userCode）
			if (!inVo.getAwarderCode().equals(inVo.getUserCode())) {
				honorAwardRecord.setAgentCode(inVo.getUserCode());
			}
			
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			honorAwardRecord.setTenantCode(tenantCode);
			honorAwardRecord.setOrgCode(orgCode);
			
			PageBean pageBean = honorService.getAwardRecordList(pageParam,honorAwardRecord);
			List<HonorAwardRecord> recordList = pageBean.getRecordList();
			
			JSONArray jsonArray = new JSONArray();
			for (HonorAwardRecord honorAward : recordList) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("awardCode", honorAward.getHonorAwardRecordCode());
				jsonObject.put("honorName", honorAward.getHonorName());
				jsonObject.put("awarderCode", honorAward.getAwarderCode());
				jsonObject.put("awardReason", honorAward.getAwardReason());
				//根据awarderCode  查询领奖对象name 领奖对象所属组织 orgCode
				if(HonorConstants.HONOREE_TYPE_1.equals(honorAward.getHonoreeType())){
					SysUserInfo honoreeInfo = getUserInfo(honorAward.getHonoreeCode());
					jsonObject.put("honoreeName",honoreeInfo.getUserName()); 
					jsonObject.put("honoreeOrg", honoreeInfo.getManageOrgInfo().getSysOrgName()); 
					jsonObject.put("awardTime", StringTools.formatToString(honorAward.getCreateTime()));  
				}
				if(HonorConstants.HONOREE_TYPE_2.equals(honorAward.getHonoreeType())){
					TeamInfo teamInfo = getTeamInfoByCode(honorAward.getHonoreeCode());
					jsonObject.put("honoreeName",teamInfo.getTeamName()); 
					jsonObject.put("honoreeOrg", teamInfo.getOrgCode()); 
					jsonObject.put("awardTime", StringTools.formatToString(honorAward.getCreateTime()));
				}
				jsonObject.put("isRevoked", honorAward.getIsRevoked());
				jsonArray.add(jsonObject);
			}
			
			JSONObject jo = new JSONObject();
			jo.put("totalCount", pageBean.getTotalCount());
			jo.put("awardList", jsonArray);
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(jo);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("HonorController.getAwardRecordList", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * @Function revokingAward
	 * @Description 撤销荣誉
	 * @param inVo
	 * @return ResultJSON
	 * @throws BusinessException
	 */
	@RequestMapping(value = "revoking", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON revokingAward(@RequireValid RevokingVo inVo) throws BusinessException {
		try {
			// 根据userCode信息查询tenant_code(租户标识)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			honorService.updateAwardStatus(inVo.getAwardCode(), tenantCode,inVo.getUserCode());
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(new JSONObject());
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("HonorController.revokingAward", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * @Function getAwardWall
	 * @Description 查询荣誉墙
	 * @param inVo
	 * @return ResultJSON
	 * @throws BusinessException
	 */
	@RequestMapping(value = "record/wall", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON getAwardWall(@RequireValid GetAwardWallVo inVo) throws BusinessException {
		try {
			//准备入参
			PageParam pageParam = new PageParam(Integer.valueOf(inVo.getPageNo()), Integer.valueOf(inVo.getPageSize()));
			HonorAwardRecord honorAwardRecord = new HonorAwardRecord();
			honorAwardRecord.setHonoreeCode(inVo.getHonoreeCode());
			honorAwardRecord.setAdmirerCode(inVo.getUserCode());

			//根据userCode 查询租户标识
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			honorAwardRecord.setTenantCode(tenantCode);
			PageBean pageBean = honorService.getAwardWall(pageParam,honorAwardRecord);
			List<HonorAwardRecord> recordList = pageBean.getRecordList();
			JSONArray jsonArray = new JSONArray();
			for (HonorAwardRecord honorAward : recordList) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("awardCode", honorAward.getHonorAwardRecordCode());
				jsonObject.put("honorName", honorAward.getHonorName());
				jsonObject.put("honorUrl", honorAward.getHonorUrl());
				jsonObject.put("awarderCode", honorAward.getAwarderCode());
				jsonObject.put("awarderName", honorAward.getAwarderName());
				//根据颁发人Code 查询颁发人所属组织Code
				SysUserInfo awarderInfo = getUserInfo(honorAward.getAwarderCode());
				if(awarderInfo != null){
					jsonObject.put("awarderOrg", awarderInfo.getManageOrgInfo().getSysOrgName()); //颁发人所属组织
				}else{
					jsonObject.put("awarderOrg", ""); //颁发人所属组织
				}
				jsonObject.put("awarderPosition", honorAward.getAwarderPosition() + "");
				jsonObject.put("honoreeCode", honorAward.getHonoreeCode());
				jsonObject.put("awardTime", StringTools.formatToString(honorAward.getCreateTime()));
				jsonObject.put("awardReason", honorAward.getAwardReason());
				jsonObject.put("commentCount", StringTools.formatToString(honorAward.getCommentCount()));
				jsonObject.put("praiseCount", StringTools.formatToString(honorAward.getPraiseCount()));
				if (null != honorAward.getAdmirerCode()) {
					jsonObject.put("isPraise", StringTools.formatToString(HonorConstants.YES)); // 已经点赞过
				} else {
					jsonObject.put("isPraise", StringTools.formatToString(HonorConstants.NO)); // 已经点赞过
				}
				jsonArray.add(jsonObject);
			}
			
			//设置返回结果
			JSONObject jo = new JSONObject();
			jo.put("totalCount", pageBean.getTotalCount());
			jo.put("awardList", jsonArray);
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(jo);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("HonorController.getAwardWall", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * @Function praiseOrNot
	 * @Description 点赞/取消点赞操作
	 * @param inVo
	 * @return ResultJSON
	 * @throws BusinessException
	 */
	@RequestMapping(value = "praiseOrNot", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON praiseOrNot(@RequireValid PraiseOrNotVo inVo) throws BusinessException {
		try {
			HonorPraise honorPraise = new HonorPraise();
			honorPraise.setInfoCode(inVo.getInfoCode());
			honorPraise.setInfoType(Byte.valueOf(inVo.getInfoType()));
			honorPraise.setOperating(Byte.valueOf(inVo.getOperating()));
			honorPraise.setAdmirerCode(inVo.getUserCode());  //点赞人
			
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			honorPraise.setOrgCode(orgCode);
			honorPraise.setTenantCode(tenantCode);
			//调用相应的服务
			int praiseCount = honorService.updatePraiseStatus(honorPraise);
			
			// 点赞
			if (HonorConstants.OPERAT_TYPE_1.equals(inVo.getOperating())) {
				/**发送"点赞"行为信息*/
				noticeAction(orgCode, CreditConstants.COMMAND_ADMIRE_PUBLISH, inVo.getUserCode(), CreditConstants.TYPE_INDIVIDUAL);
			}
			// 取消点赞
			if (HonorConstants.OPERAT_TYPE_0.equals(inVo.getOperating())) {
				/**发送"取消点赞"行为信息*/
				noticeAction(orgCode, CreditConstants.COMMAND_ADMIRE_DEL, inVo.getUserCode(), CreditConstants.TYPE_INDIVIDUAL);
			}
			
			ResultJSON result = new ResultJSON("COMMON_200");
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("praiseCount", StringTools.formatToString(praiseCount));
			result.setBody(jsonObject);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("HonorController.praiseOrNot", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * @Function getCommentList
	 * @Description 查询荣誉评论列表
	 * @param inVo
	 * @return ResultJSON
	 * @throws BusinessException
	 */
	@RequestMapping(value = "award/comment/list", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON getCommentList(@RequireValid GetCommentListVo inVo) throws BusinessException {
		try {
			//准备入参
			HonorComment honorComment = new HonorComment(); 
			//授予记录Code
			honorComment.setAwardRecordCode(inVo.getAwardCode()); 
			honorComment.setAdmirerCode(inVo.getUserCode());
			honorComment.setPageSize(Integer.valueOf(inVo.getPageSize()));
			if(StringTools.isNotEmpty(inVo.getId())){
				honorComment.setId(Long.valueOf(inVo.getId()));
			}
			// 根据userCode信息查询tenant_code(租户标识)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			honorComment.setTenantCode(tenantCode);
			
			PageBean pageBean = honorService.getCommentList(honorComment);
			List<HonorComment> commentList = pageBean.getRecordList();
			JSONArray jsonArray = new JSONArray();
			for (HonorComment hc : commentList) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", StringTools.formatToString(hc.getId()));
				jsonObject.put("awardCode", hc.getAwardRecordCode());
				jsonObject.put("commentCode", hc.getHonorAwardCommentCode());
				jsonObject.put("commenterCode", hc.getCommenterCode());
				
				//根据 commenterCode 查询 commenterName、commenterUrl、commenterOrg
				SysUserInfo commenterInfo = getUserInfo(hc.getCommenterCode());
				if(commenterInfo != null){
					jsonObject.put("commenterName",StringTools.formatToString(commenterInfo.getUserName()));
					jsonObject.put("commenterUrl", StringTools.formatToString(commenterInfo.getHeadPortrait()));
					jsonObject.put("commenterOrg", StringTools.formatToString(commenterInfo.getManageOrgInfo().getSysOrgName()));
				}else{
					jsonObject.put("commenterName","");
					jsonObject.put("commenterUrl", "");
					jsonObject.put("commenterOrg", "");
				}
				jsonObject.put("content",hc.getContent() );
				jsonObject.put("createTime", StringTools.formatToString(hc.getCreateTime()));
				jsonObject.put("replyCount",StringTools.formatToString(hc.getReplyCount()));
				jsonObject.put("praiseCount",StringTools.formatToString(hc.getPraiseCount()));
				if (null != hc.getAdmirerCode()) {
					jsonObject.put("isPraise", StringTools.formatToString(HonorConstants.YES)); // 已经点赞过
				} else {
					jsonObject.put("isPraise", StringTools.formatToString(HonorConstants.NO)); // 未点赞过
				}
				
				jsonArray.add(jsonObject);
			}
			
			//设置返回结果
			JSONObject jo = new JSONObject();
			jo.put("commentList", jsonArray);
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(JSON.toJSON(jo));
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("HonorController.getCommentList", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	
	
	/**
	 * @Function getReplyList
	 * @Description 查询评论回复列表
	 * @param inVo
	 * @return ResultJSON
	 * @throws BusinessException
	 */
	@RequestMapping(value = "award/reply/list", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON getCommentReplyList(@RequireValid GetReplyListVo inVo) throws BusinessException {
		try {
			//准备入参
			HonorCommentReply honorCommentReply = new HonorCommentReply(); 
			//授予记录Code
			honorCommentReply.setAwardRecordCode(inVo.getAwardCode());   //荣誉记录Code
			honorCommentReply.setAwardCommentCode(inVo.getCommentCode()); //评论Code
			honorCommentReply.setAdmirerCode(inVo.getUserCode());
			honorCommentReply.setPageSize(Integer.valueOf(inVo.getPageSize())); //加载更多条数
			if (StringTools.isNotEmpty(inVo.getId())) {
				honorCommentReply.setId(Long.valueOf(inVo.getId()));
			}
			// 根据userCode信息查询tenant_code(租户标识)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			honorCommentReply.setTenantCode(tenantCode);
			PageBean pageBean = honorService.getCommentReplyList(honorCommentReply);
			
			List<HonorCommentReply> commenReplytList = pageBean.getRecordList();
			JSONArray jsonArray = new JSONArray();
			for (HonorCommentReply hcr : commenReplytList) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", StringTools.formatToString(hcr.getId()));
				jsonObject.put("awardCode", hcr.getAwardRecordCode());
				jsonObject.put("commentCode", hcr.getAwardCommentCode());
				jsonObject.put("replyCode", hcr.getHonorCommentReplyCode());
				jsonObject.put("replierCode", hcr.getReplierCode());
				jsonObject.put("questionerCode", hcr.getQuestionerCode());
				
				//根据 replierCode 查询 replierName、replierUrl、replierOrg
				List<SysUserInfo> users = getUserInfos(hcr.getReplierCode(), hcr.getQuestionerCode());
				for (SysUserInfo sysUserInfo : users) {
					if (hcr.getReplierCode().equals(sysUserInfo.getUserCode())) {
						jsonObject.put("replierName", StringTools.formatToString(sysUserInfo.getUserName()));
						jsonObject.put("replierUrl", StringTools.formatToString(sysUserInfo.getHeadPortrait()));
						jsonObject.put("replierOrg", StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgName()));
					}
					if (hcr.getQuestionerCode().equals(sysUserInfo.getUserCode())) {
						jsonObject.put("questionerName", StringTools.formatToString(sysUserInfo.getUserName()));
						jsonObject.put("questionerUrl", StringTools.formatToString(sysUserInfo.getHeadPortrait()));
						jsonObject.put("questionerOrg", StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgName()));
					}
				}
				if (!jsonObject.containsKey("replierName")) {
					jsonObject.put("replierName", "");
				}
				if (!jsonObject.containsKey("replierUrl")) {
					jsonObject.put("replierUrl", "");
				}
				if (!jsonObject.containsKey("replierOrg")) {
					jsonObject.put("replierOrg", "");
				}
				if (!jsonObject.containsKey("questionerName")) {
					jsonObject.put("questionerName", "");
				}
				if (!jsonObject.containsKey("questionerUrl")) {
					jsonObject.put("questionerUrl", "");
				}
				if (!jsonObject.containsKey("questionerOrg")) {
					jsonObject.put("questionerOrg", "");
				}
				
				jsonObject.put("content",hcr.getContent() );
				jsonObject.put("createTime", StringTools.formatToString(hcr.getCreateTime()));
				jsonObject.put("praiseCount",StringTools.formatToString(hcr.getPraiseCount()));
				if (null != hcr.getAdmirerCode()) {
					jsonObject.put("isPraise", StringTools.formatToString(HonorConstants.YES)); // 已经点赞过
				} else {
					jsonObject.put("isPraise", StringTools.formatToString(HonorConstants.NO)); // 未点赞过
				}
				
				jsonArray.add(jsonObject);
			}
			
			//设置返回结果
			JSONObject jo = new JSONObject();
			jo.put("replyList", jsonArray);
			// 剩余记录数
			jo.put("surplusCount", pageBean.getTotalCount());
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(JSON.toJSON(jo));
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("HonorController.getCommentReplyList", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * @Function honorCommenting
	 * @Description 发表评论
	 * @param inVo
	 * @return ResultJSON
	 * @throws BusinessException
	 */
	@RequestMapping(value = "award/commenting", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON honorCommenting(@RequireValid CommentingVo inVo) throws BusinessException {
		try {
			HonorComment honorComment = new HonorComment();
			honorComment.setAwardRecordCode(inVo.getAwardCode());
			honorComment.setContent(inVo.getContent());
			honorComment.setCommenterCode(inVo.getUserCode());
			
			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			honorComment.setTenantCode(tenantCode);
			honorComment.setOrgCode(orgCode);
			//调用评论添加服务
			String commentCode = honorService.addComment(honorComment);
			//获取详情
			HonorComment honorCommentInfo = honorService.getCommentInfo(commentCode,tenantCode);
			JSONObject jsonObject = new JSONObject();
			if (null != honorCommentInfo) {
				jsonObject.put("id", StringTools.formatToString(honorCommentInfo.getId()));
				jsonObject.put("awardCode", honorCommentInfo.getAwardRecordCode());
				jsonObject.put("commentCode", honorCommentInfo.getHonorAwardCommentCode());
				jsonObject.put("commenterCode", honorCommentInfo.getCommenterCode());
				
				//根据 commenterCode 查询 commenterName、commenterUrl、commenterOrg
				SysUserInfo commenterInfo = getUserInfo(honorCommentInfo.getCommenterCode());
				if(commenterInfo != null){
					jsonObject.put("commenterName",StringTools.formatToString(commenterInfo.getUserName()));
					jsonObject.put("commenterUrl", StringTools.formatToString(commenterInfo.getHeadPortrait()));
					jsonObject.put("commenterOrg", StringTools.formatToString(commenterInfo.getManageOrgInfo().getSysOrgName()));
				}else{
					jsonObject.put("commenterName","");
					jsonObject.put("commenterUrl", "");
					jsonObject.put("commenterOrg", "");
				}
				jsonObject.put("content",honorCommentInfo.getContent() );
				jsonObject.put("createTime", StringTools.formatToString(honorCommentInfo.getCreateTime()));
				jsonObject.put("replyCount",StringTools.formatToString(honorCommentInfo.getReplyCount()));
				jsonObject.put("praiseCount",StringTools.formatToString(honorCommentInfo.getPraiseCount()));
				jsonObject.put("isPraise", StringTools.formatToString(HonorConstants.NO)); // 未点赞过
			}
			
			/**发送"成功发表一次评论"行为信息*/
			noticeAction(orgCode, CreditConstants.COMMAND_COMMENT_PUBLISH, inVo.getUserCode(), CreditConstants.TYPE_INDIVIDUAL);
			
			//设置返回结果
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(jsonObject);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("HonorController.honorCommenting", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	
	/**
	 * @Function honorCommentReply
	 * @Description 发表回复
	 * @param inVo
	 * @return ResultJSON
	 * @throws BusinessException
	 */
	@RequestMapping(value = "award/replying", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON honorCommentReply(@RequireValid ReplyingVo inVo) throws BusinessException {
		try {
			HonorCommentReply honorCommentReply = new HonorCommentReply();
			
			honorCommentReply.setAwardRecordCode(inVo.getAwardCode());
			honorCommentReply.setAwardCommentCode(inVo.getCommentCode());
			honorCommentReply.setQuestionerCode(inVo.getQuestionerCode());
			honorCommentReply.setContent(inVo.getContent());
			honorCommentReply.setReplierCode(inVo.getUserCode());
			

			// 根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			honorCommentReply.setTenantCode(tenantCode);
			honorCommentReply.setOrgCode(orgCode);
			//调用回复添加服务
			String replyCode = honorService.addCommentReply(honorCommentReply);
			
			HonorCommentReply commentReplyInfo = honorService.getCommentReplyInfo(replyCode,tenantCode);
			JSONObject jsonObject = new JSONObject();
			if(null != commentReplyInfo){
				jsonObject.put("id", StringTools.formatToString(commentReplyInfo.getId()));
				jsonObject.put("awardCode", commentReplyInfo.getAwardRecordCode());
				jsonObject.put("commentCode", commentReplyInfo.getAwardCommentCode());
				jsonObject.put("replyCode", commentReplyInfo.getHonorCommentReplyCode());
				jsonObject.put("replierCode", commentReplyInfo.getReplierCode());
				jsonObject.put("questionerCode", commentReplyInfo.getQuestionerCode());
				
				//根据 replierCode 查询 replierName、replierUrl、replierOrg
				List<SysUserInfo> users = getUserInfos(commentReplyInfo.getReplierCode(), commentReplyInfo.getQuestionerCode());
				for (SysUserInfo sysUserInfo : users) {
					if (commentReplyInfo.getReplierCode().equals(sysUserInfo.getUserCode())) {
						jsonObject.put("replierName", StringTools.formatToString(sysUserInfo.getUserName()));
						jsonObject.put("replierUrl", StringTools.formatToString(sysUserInfo.getHeadPortrait()));
						jsonObject.put("replierOrg", StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgName()));
					}
					if (commentReplyInfo.getQuestionerCode().equals(sysUserInfo.getUserCode())) {
						jsonObject.put("questionerName", StringTools.formatToString(sysUserInfo.getUserName()));
						jsonObject.put("questionerUrl", StringTools.formatToString(sysUserInfo.getHeadPortrait()));
						jsonObject.put("questionerOrg", StringTools.formatToString(sysUserInfo.getManageOrgInfo().getSysOrgName()));
					}
				}
				if (!jsonObject.containsKey("replierName")) {
					jsonObject.put("replierName", "");
				}
				if (!jsonObject.containsKey("replierUrl")) {
					jsonObject.put("replierUrl", "");
				}
				if (!jsonObject.containsKey("replierOrg")) {
					jsonObject.put("replierOrg", "");
				}
				if (!jsonObject.containsKey("questionerName")) {
					jsonObject.put("questionerName", "");
				}
				if (!jsonObject.containsKey("questionerUrl")) {
					jsonObject.put("questionerUrl", "");
				}
				if (!jsonObject.containsKey("questionerOrg")) {
					jsonObject.put("questionerOrg", "");
				}
				
				jsonObject.put("content",commentReplyInfo.getContent() );
				jsonObject.put("createTime", StringTools.formatToString(commentReplyInfo.getCreateTime()));
				jsonObject.put("praiseCount",StringTools.formatToString(commentReplyInfo.getPraiseCount()));
				jsonObject.put("isPraise", StringTools.formatToString(HonorConstants.NO)); // 未点赞过
				
			}
			
			/**发送"成功回复一次评论"行为信息*/
			noticeAction(orgCode, CreditConstants.COMMAND_COMMENT_REPLY, inVo.getUserCode(), CreditConstants.TYPE_INDIVIDUAL);
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(jsonObject);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("HonorController.honorCommentReply", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	
	/**
	 * @Function deletingOwnSpeech
	 * @Description 删除评论/回复
	 * @param inVo
	 * @return ResultJSON
	 * @throws BusinessException
	 */
	@RequestMapping(value = "award/deletingOwnSpeech", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON deletingOwnSpeech(@RequireValid DeletingOwnSpeechVo inVo) throws BusinessException {
		try {
			// 根据userCode信息查询tenant_code(租户标识)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			
			// 删除评论
			if (HonorConstants.DELETE_INFO_TYPE_1.equals(inVo.getInfoType())) {
				HonorComment honorComment = new HonorComment();
				// 评论人Code
				honorComment.setCommenterCode(inVo.getUserCode());
				honorComment.setHonorAwardCommentCode(inVo.getInfoCode());
				honorComment.setTenantCode(tenantCode);
				honorService.deleteComment(honorComment);
				
				/**发送"删除一条我的评论"行为信息*/
				noticeAction(orgCode, CreditConstants.COMMAND_COMMENT_DEL, inVo.getUserCode(), CreditConstants.TYPE_INDIVIDUAL);
			}
			
			// 删除回复
			if (HonorConstants.DELETE_INFO_TYPE_2.equals(inVo.getInfoType())) {
				HonorCommentReply honorCommentReply = new HonorCommentReply();
				// 回复人Code
				honorCommentReply.setReplierCode(inVo.getUserCode());
				honorCommentReply.setHonorCommentReplyCode(inVo.getInfoCode());
				honorCommentReply.setTenantCode(tenantCode);
				honorService.deleteCommentReply(honorCommentReply);
				
				/**发送"删除一条我的回复"行为信息*/
				noticeAction(orgCode, CreditConstants.COMMAND_COMMENT_REPLYDEL, inVo.getUserCode(), CreditConstants.TYPE_INDIVIDUAL);
			}
			//设置返回结果
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(new JSONObject());
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("HonorController.deletingOwnSpeech", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * @Function getAwardRecordDetail
	 * @Description 获取荣誉墙详情
	 * @param inVo
	 * @return 
	 * @throws BusinessException
	 */
	@RequestMapping(value = "record/detail", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON getAwardRecordDetail(@RequireValid GetAwardRecordDetailVo inVo) throws BusinessException {
		try {
			//准备入参
			HonorAwardRecord honorAwardRecord = new HonorAwardRecord(); 
			//授予记录Code
			honorAwardRecord.setHonorAwardRecordCode(inVo.getAwardCode()); 
			honorAwardRecord.setAdmirerCode(inVo.getUserCode());
			
			// 根据userCode信息查询tenant_code(租户标识)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			honorAwardRecord.setTenantCode(tenantCode);
			HonorAwardRecord  honorAward = honorService.getAwardRecordDetail(honorAwardRecord);
			JSONObject jsonObject = new JSONObject();
			if (null != honorAward) {
				jsonObject.put("awardCode", honorAward.getHonorAwardRecordCode());
				jsonObject.put("honorName", honorAward.getHonorName());
				jsonObject.put("honorUrl", honorAward.getHonorUrl());
				jsonObject.put("awarderCode", honorAward.getAwarderCode());
				jsonObject.put("awarderName", honorAward.getAwarderName());
				//根据颁发人Code 查询颁发人所属组织Code
				SysUserInfo awarderInfo = getUserInfo(honorAward.getAwarderCode());
				if(awarderInfo != null){
					jsonObject.put("awarderOrg", awarderInfo.getManageOrgInfo().getSysOrgName()); //颁发人所属组织
				}else{
					jsonObject.put("awarderOrg", ""); //颁发人所属组织
				}
				jsonObject.put("awarderPosition", honorAward.getAwarderPosition() + "");
				jsonObject.put("honoreeCode", honorAward.getHonoreeCode());
				jsonObject.put("awardTime", StringTools.formatToString(honorAward.getCreateTime()));
				jsonObject.put("awardReason", honorAward.getAwardReason());
				jsonObject.put("commentCount", StringTools.formatToString(honorAward.getCommentCount()));
				jsonObject.put("praiseCount", StringTools.formatToString(honorAward.getPraiseCount()));
				if (null != honorAward.getAdmirerCode()) {
					jsonObject.put("isPraise", StringTools.formatToString(HonorConstants.YES)); // 已经点赞过
				} else {
					jsonObject.put("isPraise", StringTools.formatToString(HonorConstants.NO)); // 已经点赞过
				}
			}
			//设置返回结果
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(jsonObject);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("HonorController.getAwardRecordDetail", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	
	/**
	 * @Function getCommentListForLocation
	 * @Description 查询评论列表 --定位
	 * @param inVo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "award/comment/list/fixed", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON getCommentListForLocation(@RequireValid GetCommentListForLocationVo inVo)
			throws BusinessException {
		try {
			//准备入参
			HonorComment honorComment = new HonorComment(); 
			//授予记录Code
			honorComment.setAwardRecordCode(inVo.getAwardCode()); 
			honorComment.setHonorAwardCommentCode(inVo.getCommentCode());
			honorComment.setAdmirerCode(inVo.getUserCode());
			honorComment.setPageSize(Integer.valueOf(inVo.getPageSize()));
			if (StringTools.isNotEmpty(inVo.getId())) {
				honorComment.setId(Long.valueOf(inVo.getId()));
			}
			// 根据userCode信息查询tenant_code(租户标识)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			honorComment.setTenantCode(tenantCode);
			
			PageBean pageBean = honorService.getCommentListForLocation(honorComment);
			List<HonorComment> commentList = pageBean.getRecordList();
			JSONArray jsonArray = new JSONArray();
			for (HonorComment hc : commentList) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", StringTools.formatToString(hc.getId()));
				jsonObject.put("awardCode", hc.getAwardRecordCode());
				jsonObject.put("commentCode", hc.getHonorAwardCommentCode());
				jsonObject.put("commenterCode", hc.getCommenterCode());
				//根据 commenterCode 查询 commenterName、commenterUrl、commenterOrg
				SysUserInfo commenterInfo = getUserInfo(hc.getCommenterCode());
				if(commenterInfo != null){
					jsonObject.put("commenterName", StringTools.formatToString(commenterInfo.getUserName()) );
					jsonObject.put("commenterUrl", StringTools.formatToString(commenterInfo.getHeadPortrait()));
					jsonObject.put("commenterOrg", StringTools.formatToString(commenterInfo.getManageOrgInfo().getSysOrgName()));
				}else{
					jsonObject.put("commenterName", "");
					jsonObject.put("commenterUrl", "");
					jsonObject.put("commenterOrg", "");
				}
				jsonObject.put("content", hc.getContent());
				jsonObject.put("createTime", StringTools.formatToString(hc.getCreateTime()));
				jsonObject.put("replyCount", StringTools.formatToString(hc.getReplyCount()));
				jsonObject.put("praiseCount", StringTools.formatToString(hc.getPraiseCount()));
				if (null != hc.getAdmirerCode()) {
					jsonObject.put("isPraise", StringTools.formatToString(HonorConstants.YES)); // 已经点赞过
				} else {
					jsonObject.put("isPraise", StringTools.formatToString(HonorConstants.NO)); // 未点赞过
				}
				jsonArray.add(jsonObject);
			}
			
			//设置返回结果
			JSONObject jo = new JSONObject();
			jo.put("commentList", jsonArray);
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(jo);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("HonorController.getCommentListForLocation", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * @Function getCommentReplyListForLocation
	 * @Description 查询评论回复列表 -- 定位
	 * @param inVo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "award/reply/list/fixed", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON getCommentReplyListForLocation(@RequireValid GetCommentReplyListForLocationVo inVo)
			throws BusinessException {
		try {
			//准备入参
			HonorCommentReply honorCommentReply = new HonorCommentReply(); 
			//授予记录Code
			honorCommentReply.setAwardRecordCode(inVo.getAwardCode());   //荣誉记录Code
			honorCommentReply.setAwardCommentCode(inVo.getCommentCode()); //评论Code
			honorCommentReply.setHonorCommentReplyCode(inVo.getReplyCode());
			honorCommentReply.setAdmirerCode(inVo.getUserCode());
			honorCommentReply.setPageSize(Integer.valueOf(inVo.getPageSize())); //加载更多条数
			if (StringTools.isNotEmpty(inVo.getId())) {
				honorCommentReply.setId(Long.valueOf(inVo.getId()));
			}

			// 根据userCode信息查询tenant_code(租户标识)
			SysUserInfo userInfo = getUserInfo(inVo.getUserCode());
			String tenantCode = userInfo.getTenantCode();
			honorCommentReply.setTenantCode(tenantCode);
			
			PageBean pageBean = honorService.getCommentReplyListForLocation(honorCommentReply);
			List<HonorCommentReply> commenReplytList = pageBean.getRecordList();
			JSONArray jsonArray = new JSONArray();
			for (HonorCommentReply hcr : commenReplytList) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", StringTools.formatToString(hcr.getId()));
				jsonObject.put("awardCode", hcr.getAwardRecordCode());
				jsonObject.put("commentCode", hcr.getAwardCommentCode());
				jsonObject.put("replyCode", hcr.getHonorCommentReplyCode());
				jsonObject.put("replierCode", hcr.getReplierCode());
				
				// 根据 replierCode 查询 replierName、replierUrl、replierOrg
				SysUserInfo replierInfo = getUserInfo(hcr.getReplierCode());
				if(replierInfo != null){
					jsonObject.put("replierName", StringTools.formatToString(replierInfo.getUserName()));
					jsonObject.put("replierUrl", StringTools.formatToString(replierInfo.getHeadPortrait()));
					jsonObject.put("replierOrg", StringTools.formatToString(replierInfo.getManageOrgInfo().getSysOrgName()));
				}else{
					jsonObject.put("replierName", "");
					jsonObject.put("replierUrl", "");
					jsonObject.put("replierOrg", "");
				}
				jsonObject.put("questionerCode", hcr.getQuestionerCode());
				// 根据 questionerCode 查询 questionerName、questionerUrl、questionerOrg
				SysUserInfo questionerInfo = getUserInfo(hcr.getQuestionerCode());
				if(questionerInfo != null){
					jsonObject.put("questionerName", StringTools.formatToString(questionerInfo.getUserName()));
					jsonObject.put("questionerUrl", StringTools.formatToString(questionerInfo.getHeadPortrait()));
					jsonObject.put("questionerOrg", StringTools.formatToString(questionerInfo.getManageOrgInfo().getSysOrgName()));
				}else{
					jsonObject.put("questionerName", "");
					jsonObject.put("questionerUrl", "");
					jsonObject.put("questionerOrg", "");
				}
				jsonObject.put("content",hcr.getContent() );
				jsonObject.put("createTime", StringTools.formatToString(hcr.getCreateTime()));
				jsonObject.put("praiseCount",StringTools.formatToString(hcr.getPraiseCount()));
				if (null != hcr.getAdmirerCode()) {
					jsonObject.put("isPraise", StringTools.formatToString(HonorConstants.YES)); // 已经点赞过
				} else {
					jsonObject.put("isPraise", StringTools.formatToString(HonorConstants.NO)); // 未点赞过
				}
				
				jsonArray.add(jsonObject);
			}
			
			//设置返回结果
			JSONObject jo = new JSONObject();
			jo.put("replyList", jsonArray);
			jo.put("surplusCount",pageBean.getTotalCount());
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(jo);
			return result;
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("HonorController.getCommentReplyListForLocation", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	
	
	
	
	/*================================================================================*/
	/**
	 * @Function getCommentList
	 * @Description 查询荣誉评论列表 --分页
	 * @param inVo
	 * @return ResultJSON
	 * @throws BusinessException
	 */
	//@RequestMapping(value = "award/comment/list", method = RequestMethod.GET)
	//@ResponseBody
	public ResultJSON getCommentListWithPage(@RequireValid GetCommentListWithPageVo inVo)throws BusinessException{
		
		try{
			//准备入参
			PageParam pageParam = new PageParam(Integer.valueOf(inVo.getPageNo()),Integer.valueOf(inVo.getPageSize()));
			HonorComment honorComment = new HonorComment(); 
			//授予记录Code
			honorComment.setAwardRecordCode(inVo.getAwardCode()); 
			honorComment.setAdmirerCode(inVo.getUserCode());
			
			PageBean pageBean = honorService.getCommentListWithPage(pageParam,honorComment);
			List<HonorComment> commentList = pageBean.getRecordList();
			
			JSONArray jsonArray = new JSONArray();
			for (HonorComment hc : commentList) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("awardCode", hc.getAwardRecordCode());
				jsonObject.put("commentCode", hc.getHonorAwardCommentCode());
				jsonObject.put("commenterCode", hc.getCommenterCode());
				
				// TODO根据 commenterCode 查询 commenterName、commenterUrl、commenterOrg
				//jsonObject.put("commenterName", "" );
				//jsonObject.put("commenterUrl", "" );
				//jsonObject.put("commenterOrg", "");
				
				jsonObject.put("content",hc.getContent() );
				jsonObject.put("createTime", StringTools.formatToString(hc.getCreateTime()));
				jsonObject.put("replyCount",StringTools.formatToString(hc.getReplyCount()));
				jsonObject.put("praiseCount",StringTools.formatToString(hc.getPraiseCount()));
				if(null != hc.getAdmirerCode()){
					jsonObject.put("isPraise", "1");  //已经点赞过
				}else{
					jsonObject.put("isPraise", "0");  //未点赞过
				}
				
				jsonArray.add(jsonObject);
			}
			
			//设置返回结果
			JSONObject jo = new JSONObject();
			jo.put("totalCount", pageBean.getTotalCount());
			jo.put("commentList", jsonArray);
			
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(JSON.toJSON(jo));
			return result;
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			throw BusinessException.build("COMMON_999");
		}
	}
	
	/**
	 * @Function getCommentReplyListWithPage
	 * @Description 查询评论回复列表 -- 分页
	 * @param inVo
	 * @return ResultJSON
	 * @throws BusinessException
	 */
	//@RequestMapping(value = "award/reply/list", method = RequestMethod.GET)
	//@ResponseBody
	public ResultJSON getCommentReplyListWithPage(@RequireValid GetReplyListWithPageVo inVo)throws BusinessException{
		
		try{
			//准备入参
			PageParam pageParam = new PageParam(Integer.valueOf(inVo.getPageNo()),Integer.valueOf(inVo.getPageSize()));
			
			HonorCommentReply honorCommentReply = new HonorCommentReply(); 
			//授予记录Code
			honorCommentReply.setAwardRecordCode(inVo.getAwardCode());   //荣誉记录Code
			honorCommentReply.setAwardCommentCode(inVo.getCommentCode()); //评论Code
			honorCommentReply.setAdmirerCode(inVo.getUserCode());
			
			PageBean pageBean = honorService.getCommentReplyListWithPage(pageParam,honorCommentReply);
			List<HonorCommentReply> commenReplytList = pageBean.getRecordList();
			
			JSONArray jsonArray = new JSONArray();
			for (HonorCommentReply hcr : commenReplytList) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("awardCode", hcr.getAwardRecordCode());
				jsonObject.put("commentCode", hcr.getAwardCommentCode());
				jsonObject.put("replyCode", hcr.getHonorCommentReplyCode());
				jsonObject.put("replierCode", hcr.getReplierCode());
				
				// TODO根据 replierCode 查询 replierName、replierUrl、replierOrg
				//jsonObject.put("replierName", "" );
				//jsonObject.put("replierUrl", "" );
				//jsonObject.put("replierOrg", "");
				
				jsonObject.put("questionerCode", hcr.getQuestionerCode());
				
				// TODO根据 questionerCode 查询 questionerName、questionerUrl、questionerOrg
				//jsonObject.put("questionerName", "" );
				//jsonObject.put("questionerUrl", "" );
				//jsonObject.put("questionerOrg", "");
				
				jsonObject.put("content",hcr.getContent() );
				jsonObject.put("createTime", StringTools.formatToString(hcr.getCreateTime()));
				jsonObject.put("praiseCount",StringTools.formatToString(hcr.getPraiseCount()));
				if(null != hcr.getAdmirerCode()){
					jsonObject.put("isPraise", "1");  //已经点赞过
				}else{
					jsonObject.put("isPraise", "0");  //未点赞过
				}
				
				jsonArray.add(jsonObject);
			}
			
			//设置返回结果
			JSONObject jo = new JSONObject();
			jo.put("totalCount", pageBean.getTotalCount());
			jo.put("replyList", jsonArray);
			
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(JSON.toJSON(jo));
			return result;
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			throw BusinessException.build("COMMON_999");
		}
	}
	
	/*================================================================================*/
	
}
