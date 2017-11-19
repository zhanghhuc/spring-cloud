package com.zssq.mblog.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.constants.CreditConstants;
import com.zssq.constants.MblogConstants;
import com.zssq.constants.SolrCoreConstants;
import com.zssq.dao.pojo.MblogInfo;
import com.zssq.dao.pojo.MblogModel;
import com.zssq.dao.pojo.SysOrgInfo;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.dao.pojo.TeamInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.mblog.vo.CheckIsSubscribeVo;
import com.zssq.mblog.vo.CollectWeiboVo;
import com.zssq.mblog.vo.DelWeiboGroupVo;
import com.zssq.mblog.vo.DelWeiboVo;
import com.zssq.mblog.vo.ForwardWeiboVo;
import com.zssq.mblog.vo.GetColWeiboListVo;
import com.zssq.mblog.vo.GetHomeWeiboListVo;
import com.zssq.mblog.vo.GetSubWeiboListVo;
import com.zssq.mblog.vo.GetWeiboInfoVo;
import com.zssq.mblog.vo.GetWeiboListGroupVo;
import com.zssq.mblog.vo.GetWeiboListVo;
import com.zssq.mblog.vo.PraiseWeiboVo;
import com.zssq.mblog.vo.PublishWeiboGroupVo;
import com.zssq.mblog.vo.PublishWeiboVo;
import com.zssq.mblog.vo.SubscribeWeiboVo;
import com.zssq.pojo.ResultJSON;
import com.zssq.qo.ForwardWeiboQO;
import com.zssq.qo.MblogInfoQO;
import com.zssq.qo.PublishWeiboQO;
import com.zssq.qo.UserInfoQO;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.PageBean;
import com.zssq.utils.StringTools;

/**
 * 
    * @ClassName: MblogInfoController  
    * @Description: 微博信息操作  
    * @author Mr.B  
    * @date 2017年3月22日  
    *
 */
@Controller
@RequestMapping("/mblog")
public class MblogInfoController extends MblogBaseController{

	/** log对象 **/
	private Logger log = Logger.getLogger(this.getClass());
	

	/**
	 * 
	    * @Title: publishWeibo  
	    * @Description: 发表微博
	    * @param req
	    * @param res
	    * @param inVo
	    * @throws BusinessException
		* @return ResultJSON    返回类型
	 */
	@RequestMapping(value = "/publishWeibo",method = RequestMethod.POST)  
	@ResponseBody
    public ResultJSON publishWeibo(HttpServletRequest req,HttpServletResponse res,@RequireValid PublishWeiboVo inVo) throws BusinessException{  
		try{
			// 创建QO
			String mblogCode = UUIDHelper.getUUID();
			String dynamicCode =  UUIDHelper.getUUID();
			String imgs = inVo.getImgs();	
			String audios = inVo.getAudios();
			String vedios = inVo.getVideos();
			String atUserCodes = inVo.getAtUserCodes();
			String topicCodes = inVo.getTopicCodes();
			
			PublishWeiboQO qo = new PublishWeiboQO();
			qo.setMblogCode(mblogCode);
			qo.setUserCode(inVo.getUserCode());
			qo.setContent(inVo.getContent());
			qo.setSummary(inVo.getSummary());
			qo.setTitle(inVo.getTextContent());
			
			// 组织信息
			SysUserInfo userInfo =  getUserInfo(inVo.getUserCode());
			
			//敏感词过滤
            JSONArray jsonArray = solrQueryService.CheckSentence(userInfo.getManageOrgInfo().getSysOrgCode(),inVo.getContent(),
                    SolrCoreConstants.SENSITIVE_WORD_CORE);
            if( jsonArray.size() > 0 ){
            	ResultJSON resJson = new ResultJSON("COMMON_999");
            	JSONObject body = new JSONObject();
                body.put("totalCount", jsonArray.size());
                body.put("isPass", false);
                body.put("list", jsonArray);
                resJson.setBody(body);
                return resJson ;
            }
			            
			if(!"".equals(imgs.trim())){
				qo.setImgs(Arrays.asList(imgs.split(",")));
			}
			if(!"".equals(vedios.trim())){
				qo.setVideos(Arrays.asList(vedios.split(",")));
			}
			if(!"".equals(audios.trim())){
				qo.setAudios(Arrays.asList(audios.split(",")));
			}
			if(!"".equals(topicCodes.trim())){
				qo.setTopicCodes(Arrays.asList(topicCodes.split(",")));
			}
			qo.setIsTeam(MblogConstants.MBLOG_NO);
			qo.setDynamicCode(dynamicCode);
			qo.setTimeSign(new Date().getTime());
			
			// At用户列表
			List<UserInfoQO> userList = new ArrayList<UserInfoQO>();
			if(null != inVo.getAtUserCodes() && !inVo.getAtUserCodes().isEmpty()){
				// 获取用户的组织信息
				if(!"".equals(atUserCodes.trim())){
					List<SysUserInfo> userOrgList = getUserInfoList(Arrays.asList(atUserCodes.split(",")));
					if(null != userOrgList && !userOrgList.isEmpty()){
						for(SysUserInfo user : userOrgList){
							// 组织信息
							if(null != user){
								UserInfoQO temp = new UserInfoQO();
								temp.setOrgCode(user.getManageOrgInfo().getSysOrgCode());
								temp.setUserCode(user.getUserCode());
								userList.add(temp);
							}
						}
					}
				}
			}
			qo.setAtUserCodes(userList);
			
			// 组织信息
			SysUserInfo user =  getUserInfo(inVo.getUserCode());
			if(null != user){
				qo.setOrgCode(user.getManageOrgInfo().getSysOrgCode());
				qo.setTenatCode(user.getTenantCode());
			}
			
			// 添加动态信息
			if(!saveDynamic(MblogConstants.MBLOG_SOURCE_SELF, qo)){
				log.info("MblogInfoController.publishWeibo:调用动态服务-添加微博失败");
				throw BusinessException.build("MBLOG_12001","添加动态");
			}
			// 添加微博信息
			if(!mblogInfoService.addMblogInfo(qo)){
				log.info("MblogInfoController.publishWeibo:调用微博服务-添加微博失败");
				throw BusinessException.build("MBLOG_12001","添加微博");
			}
			// 添加at消息
			sendAtMsg(mblogCode, MblogConstants.MBLOG_SOURCE_SELF, qo.getTenatCode(), inVo.getUserCode(), userList,inVo.getContent());
			
			// 添加积分信息
			sendCreditMsg(inVo.getUserCode(), qo.getOrgCode(),CreditConstants.TYPE_INDIVIDUAL, CreditConstants.COMMAND_MICROBLOG_PUBLISH);
			
			// 添加统计
			addStatisticRecord(false, null, inVo.getUserCode());
			
			// 创建返回信息
			return getMblogInfo(qo);
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			log.error("MblogInfoController.publishWeibo", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	/**
	 * 
	    * @Title: publishWeiboGroup  
	    * @Description: 班组长发表微博
	    * @param req
	    * @param res
	    * @param inVo
	    * @throws BusinessException
		* @return ResultJSON    返回类型
	 */
	@RequestMapping(value = "/publishWeiboGroup",method = RequestMethod.POST)  
	@ResponseBody
    public ResultJSON publishWeiboGroup(HttpServletRequest req,HttpServletResponse res,@RequireValid PublishWeiboGroupVo inVo) throws BusinessException{  
		try{
			// 组织信息
			SysUserInfo userInfo =  getUserInfo(inVo.getUserCode());
			
			//敏感词过滤
            JSONArray jsonArray = solrQueryService.CheckSentence(userInfo.getManageOrgInfo().getSysOrgCode(),inVo.getContent(),
                    SolrCoreConstants.SENSITIVE_WORD_CORE);
            if( jsonArray.size() > 0 ){
            	ResultJSON resJson = new ResultJSON("COMMON_999");
            	JSONObject body = new JSONObject();
                body.put("totalCount", jsonArray.size());
                body.put("isPass", false);
                body.put("list", jsonArray);
                resJson.setBody(body);
                return resJson ;
            }
			// 判断用户与班组关系
			if(!checkTeamLeader(inVo.getTeamCode(), inVo.getUserCode())){
				throw BusinessException.build("COMMON_403");
			}
			// 创建QO
			String mblogCode = UUIDHelper.getUUID();
			String dynamicCode =  UUIDHelper.getUUID();
			String imgs = inVo.getImgs();	
			String audios = inVo.getAudios();
			String vedios = inVo.getVideos();
			String atUserCodes = inVo.getAtUserCodes();
			String topicCodes = inVo.getTopicCodes();
			
			PublishWeiboQO qo = new PublishWeiboQO();
			qo.setMblogCode(mblogCode);
			qo.setUserCode(inVo.getUserCode());
			qo.setTeamCode(inVo.getTeamCode());
			qo.setContent(inVo.getContent());
			qo.setTitle(inVo.getTextContent());
			qo.setSummary(inVo.getSummary());
			if(!"".equals(imgs.trim())){
				qo.setImgs(Arrays.asList(imgs.split(",")));
			}
			if(!"".equals(vedios.trim())){
				qo.setVideos(Arrays.asList(vedios.split(",")));
			}
			if(!"".equals(audios.trim())){
				qo.setAudios(Arrays.asList(audios.split(",")));
			}
			if(!"".equals(topicCodes.trim())){
				qo.setTopicCodes(Arrays.asList(topicCodes.split(",")));
			}
			qo.setIsTeam(MblogConstants.MBLOG_YES);
			qo.setDynamicCode(dynamicCode);
			qo.setTimeSign(new Date().getTime());
			
			// At用户列表
			List<UserInfoQO> userList = new ArrayList<UserInfoQO>();
			if(null != inVo.getAtUserCodes() && !inVo.getAtUserCodes().isEmpty()){
				// 获取用户的组织信息
				if(!"".equals(atUserCodes.trim())){
					List<SysUserInfo> userOrgList = getUserInfoList(Arrays.asList(atUserCodes.split(",")));
					if(null != userOrgList && !userOrgList.isEmpty()){
						for(SysUserInfo user : userOrgList){
							// 组织信息
							if(null != user){
								UserInfoQO temp = new UserInfoQO();
								temp.setOrgCode(user.getManageOrgInfo().getSysOrgCode());
								temp.setUserCode(user.getUserCode());
								userList.add(temp);
							}
						}
					}
				}
			}
			qo.setAtUserCodes(userList);
			
			// 组织信息
			SysUserInfo user =  getUserInfo(inVo.getUserCode());
			TeamInfo team = getTeamInfo(inVo.getTeamCode());
			if(null != team){
				qo.setOrgCode(team.getOrgCode());
			}
			if(null != user){
				qo.setTenatCode(user.getTenantCode());
			}
			
			// 添加动态信息
			if(!saveDynamic(MblogConstants.MBLOG_SOURCE_SELF, qo)){
				log.info("MblogInfoController.publishWeiboGroup:调用动态服务-添加微博失败");
				throw BusinessException.build("MBLOG_12001","添加动态");
			}
			// 添加微博信息
			if(!mblogInfoService.addMblogInfo(qo)){
				log.info("MblogInfoController.publishWeiboGroup:调用微博服务-添加微博失败");
				throw BusinessException.build("MBLOG_12001","添加微博");
			}
			// 添加at消息
			sendAtMsg(mblogCode, MblogConstants.MBLOG_SOURCE_SELF, qo.getTenatCode(), inVo.getUserCode(), userList,inVo.getContent());
			
			// 添加积分信息
			sendCreditMsg(inVo.getTeamCode(), qo.getOrgCode(),CreditConstants.TYPE_TEAM, CreditConstants.COMMAND_MICROBLOG_TEAMPUBLISH);
			
			// 添加统计
			addStatisticRecord(true, inVo.getTeamCode(), inVo.getUserCode());		
			
			// 创建返回信息
			return getMblogInfo(qo);
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			log.error("MblogInfoController.publishWeiboGroup", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	/**
	 * 
	    * @Title: forwardWeibo  
	    * @Description: 转发微博
	    * @param req
	    * @param res
	    * @param inVo
	    * @throws BusinessException
		* @return ResultJSON    返回类型
	 */
	@RequestMapping(value = "/forwardWeibo",method = RequestMethod.POST)  
	@ResponseBody
	public ResultJSON forwardWeibo(HttpServletRequest req,HttpServletResponse res,@RequireValid ForwardWeiboVo inVo) throws BusinessException{  
		try{
			
			// 组织信息
			SysUserInfo userInfo =  getUserInfo(inVo.getUserCode());
			
			//敏感词过滤
	        JSONArray jsonArray = solrQueryService.CheckSentence(userInfo.getManageOrgInfo().getSysOrgCode(),inVo.getContent(),
	                SolrCoreConstants.SENSITIVE_WORD_CORE);
	        if( jsonArray.size() > 0 ){
	        	ResultJSON resJson = new ResultJSON("COMMON_999");
	        	JSONObject body = new JSONObject();
	            body.put("totalCount", jsonArray.size());
	            body.put("isPass", false);
	            body.put("list", jsonArray);
	            resJson.setBody(body);
	            return resJson ;
	        }
			            
			// 创建QO
			String mblogCode = UUIDHelper.getUUID();
			String dynamicCode =  UUIDHelper.getUUID();
			String atUserCodes = inVo.getAtUserCodes();
			
			// 判断原微博是否存在
			MblogInfo info = mblogInfoService.getMblogInfo(inVo.getMblogCode());
			if(null == info || info.getIsDelete() == MblogConstants.MBLOG_YES || info.getIsShield() == MblogConstants.MBLOG_YES){
				throw BusinessException.build("MBLOG_12002","微博");
			}
			// 如果是个人的需要判断是否是黑名单
			if(info.getMblogDepend() == MblogConstants.MBLOG_DEPEND_SELF){
				if(checkIsBlack(inVo.getForUserCode(), inVo.getUserCode())){
					throw BusinessException.build("COMMON_403");
				}
			}
			// 判断是否转发过了
			/*if(mblogInfoService.checkIsFor(inVo.getMblogCode(), inVo.getUserCode())){
				throw BusinessException.build("MBLOG_12003");
			}*/
			// 转发QO
			ForwardWeiboQO qo = new ForwardWeiboQO();
			qo.setMblogCode(mblogCode);
			qo.setUserCode(inVo.getUserCode());
			qo.setContent(inVo.getContent());
			qo.setTitle(inVo.getTextContent());
			qo.setSummary(inVo.getSummary());
			qo.setSourceMblogCode(inVo.getMblogCode());
			qo.setSourceUserCode(inVo.getMblogUserCode());
			qo.setForMblogCode(inVo.getForMblogCode());
			qo.setForUserCode(inVo.getForUserCode());
			qo.setDynamicCode(dynamicCode);
			qo.setTimeSign(new Date().getTime());
			qo.setSourceMblogInfo(info);
			
			String atUsers = "";
			if(!inVo.getForUserCode().equals(inVo.getMblogUserCode())){
				atUsers = inVo.getForUserCode() + "," + inVo.getMblogUserCode();
			}else{
				atUsers = inVo.getForUserCode();
			}
			// At用户列表
			if(StringTools.isNotEmpty(atUserCodes)){
				atUserCodes = atUserCodes + "," + atUsers;
			}else{
				atUserCodes = atUsers;
			}
			List<UserInfoQO> userList = new ArrayList<UserInfoQO>();
			//if(null != inVo.getAtUserCodes() && !inVo.getAtUserCodes().isEmpty()){
			// 获取用户的组织信息
			if(!"".equals(atUserCodes.trim())){
				List<SysUserInfo> userOrgList = getUserInfoList(Arrays.asList(atUserCodes.split(",")));
				if(null != userOrgList && !userOrgList.isEmpty()){
					for(SysUserInfo user : userOrgList){
						// 组织信息
						if(null != user){
							UserInfoQO temp = new UserInfoQO();
							temp.setOrgCode(user.getManageOrgInfo().getSysOrgCode());
							temp.setUserCode(user.getUserCode());
							userList.add(temp);
						}
					}
				}
			}
			//}
			
			
			qo.setAtUserCodes(userList);
			
			// 组织信息
			SysUserInfo user =  getUserInfo(inVo.getUserCode());
			if(null != user){
				qo.setOrgCode(user.getManageOrgInfo().getSysOrgCode());
				qo.setTenatCode(user.getTenantCode());
			}
			
			// 添加动态信息
			if(!saveDynamic(MblogConstants.MBLOG_SOURCE_FORWARD, qo)){
				log.info("MblogInfoController.forwardWeibo:调用动态服务-转发微博失败");
				throw BusinessException.build("MBLOG_12001","转发微博-动态");
			}
			// 添加微博信息
			if(!mblogInfoService.addForwardMblogInfo(qo)){
				log.info("MblogInfoController.forwardWeibo:调用微博服务-转发微博失败");
				throw BusinessException.build("MBLOG_12001","转发微博");
			}
			
			// 修改动态转发次数
			updateForwardNum(qo.getForMblogCode());
			
			// 添加at消息 
			sendAtMsg(mblogCode, MblogConstants.MBLOG_SOURCE_FORWARD, qo.getTenatCode(), inVo.getUserCode(), userList, inVo.getContent());
			
			// 添加积分信息
			sendCreditMsg(inVo.getUserCode(), qo.getOrgCode(),CreditConstants.TYPE_INDIVIDUAL, CreditConstants.COMMAND_CONTENT_RELAY);			
			
			// 创建返回信息
			JSONObject body = new JSONObject();
			body.put("mblogCode", mblogCode);
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(body);
			return result;
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			log.error("MblogInfoController.forwardWeibo", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	
	/**
	 * 
	    * @Title: getWeiboList  
	    * @Description: 获取微博列表
	    * @param req
	    * @param res
	    * @param inVo
	    * @throws BusinessException
		* @return ResultJSON    返回类型
	 */
	@RequestMapping(value = "/getWeiboList")  
	@ResponseBody
    public ResultJSON getWeiboList(HttpServletRequest req,HttpServletResponse res,@RequireValid GetWeiboListVo inVo) throws BusinessException{  
		try{
			// 创建QO
			MblogInfoQO qo = new MblogInfoQO();
			qo.setMyUserCode(inVo.getUserCode());
			qo.setLineTime(Long.valueOf(inVo.getCurrentTime()));
			qo.setPageNo(Integer.valueOf(inVo.getPageNo()));
			qo.setPageSize(Integer.valueOf(inVo.getPageSize()));
			
			// 创建返回值
			PageBean pageBean = new PageBean();
			// 判断是个人的还是他人的
			if(inVo.getOtherCode().equals(inVo.getUserCode())){
				// 获取用户关注的好友列表
				qo.setUserCodes(getMyFrAndCo(inVo.getUserCode()));
				// 获取分页数据
				pageBean = mblogInfoService.queryMyMblogList(qo);
			}else{
				// 获取他人CODE
				qo.setOtherUserCode(inVo.getOtherCode());
				// 获取分页数据
				pageBean = mblogInfoService.queryOtherMblogList(qo);
			}
			
			// 获取数据
			List<MblogModel> mbList = pageBean.getRecordList();
			// 创建返回信息
			JSONObject body = new JSONObject();
			JSONArray mblogList = new JSONArray();
			body.put("total", pageBean.getTotalCount());
			body.put("nowSysTime", new Date().getTime());
			if(null != mbList && !mbList.isEmpty()){
				for(MblogModel mblog : mbList){
					// 分解单个信息
					JSONObject temp = new JSONObject();
					temp.put("mblogCode", mblog.getMblogCode());
					temp.put("userCode", mblog.getUserCode());
					
					// 获取用户信息
					SysUserInfo user =  getUserInfo(mblog.getUserCode());
					if(null != user){
						temp.put("userName", user.getUserName());
						temp.put("userPhoto", user.getHeadPortrait());
						temp.put("orgName", user.getManageOrgInfo().getSysOrgFullname());
					}else{
						temp.put("userName", "");
						temp.put("userPhoto", "");
						temp.put("orgName", "");
					}
					
					temp.put("summary", mblog.getSummary());
					temp.put("content", mblog.getContent());
					temp.put("imgs", mblog.getImgs());
					temp.put("audios", mblog.getAudios());
					temp.put("videos", mblog.getVideos());
					temp.put("mblogSource", mblog.getMblogSource());
					temp.put("forwardNum", mblog.getForwardNum());
					temp.put("collectNum", mblog.getCollectNum());
					temp.put("commentNum", mblog.getCommentNum());
					temp.put("praiseNum", mblog.getPraiseNum());
					temp.put("publishTime", mblog.getPublishTime());
					temp.put("meCollect", mblog.getMeCollect());
					temp.put("mePraise", mblog.getMePraise());
					temp.put("dynamicCode", mblog.getDynamicCode());
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
					temp.put("wbSub", wbSub);
					
					mblogList.add(temp);
				}
			}
			body.put("mblogList", mblogList);
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(body);
			return result;
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			log.error("MblogInfoController.getWeiboList", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * 
	    * @Title: getWeiboInfo  
	    * @Description: 获取微博详情
	    * @param req
	    * @param res
	    * @param inVo
	    * @throws BusinessException
		* @return ResultJSON    返回类型
	 */
	@RequestMapping(value = "/getWeiboInfo")  
	@ResponseBody
	public ResultJSON getWeiboInfo(HttpServletRequest req,HttpServletResponse res,@RequireValid GetWeiboInfoVo inVo) throws BusinessException{  
		try{
			// 创建QO
			MblogInfoQO qo = new MblogInfoQO();
			qo.setMyUserCode(inVo.getUserCode());
			qo.setMblogCode(inVo.getMblogCode());
			// 获取分页数据
			MblogModel mblog = mblogInfoService.getMblogInfo(qo);
			// 创建返回信息
			JSONObject body = new JSONObject();
			if(null != mblog){
				body.put("nowSysTime", new Date().getTime());
				// 分解单个信息
				body.put("mblogCode", mblog.getMblogCode());
				body.put("userCode", mblog.getUserCode());
				
				// 获取用户组织信息
				SysUserInfo user =  getUserInfo(mblog.getUserCode());
				if(null != user){
					body.put("userName", user.getUserName());
					body.put("userPhoto", user.getHeadPortrait());
					body.put("orgName", user.getManageOrgInfo().getSysOrgFullname());
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
			}
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(body);
			return result;
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			log.error("MblogInfoController.getWeiboInfo", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * 
	    * @Title: getSubWeiboList  
	    * @Description: 获取订阅微博
	    * @param req
	    * @param res
	    * @param inVo
	    * @throws BusinessException
		* @return ResultJSON    返回类型
	 */
	@RequestMapping(value = "/getSubWeiboList")  
	@ResponseBody
	public ResultJSON getSubWeiboList(HttpServletRequest req,HttpServletResponse res,@RequireValid GetSubWeiboListVo inVo) throws BusinessException{  
		try{
			// 创建QO
			MblogInfoQO qo = new MblogInfoQO();
			qo.setMyUserCode(inVo.getUserCode());
			qo.setLineTime(Long.valueOf(inVo.getCurrentTime()));
			qo.setPageNo(Integer.valueOf(inVo.getPageNo()));
			qo.setPageSize(Integer.valueOf(inVo.getPageSize()));
			// 获取分页数据
			PageBean pageBean = mblogInfoService.queryMySubscribeList(qo);
			List<MblogModel> mbList = pageBean.getRecordList();
			// 创建返回信息
			JSONObject body = new JSONObject();
			JSONArray mblogList = new JSONArray();
			body.put("total", pageBean.getTotalCount());
			body.put("nowSysTime", new Date().getTime());
			if(null != mbList && !mbList.isEmpty()){
				for(MblogModel mblog : mbList){
					// 分解单个信息
					JSONObject temp = new JSONObject();
					temp.put("mblogCode", mblog.getMblogCode());
					temp.put("userCode", mblog.getUserCode());
					
					// 获取用户组织信息
					SysUserInfo user =  getUserInfo(mblog.getUserCode());
					// 获取组织信息
					SysOrgInfo orgT = getOrgInfo(mblog.getOrgCode());
					if(null != user){
						temp.put("userName", user.getUserName());
						temp.put("userPhoto", user.getHeadPortrait());
					}else{
						temp.put("userName", "");
						temp.put("userPhoto", "");
					}
					if(null != orgT){
						temp.put("orgName", orgT.getSysOrgFullname());
					}else{
						temp.put("orgName", "");
					}
					
					if(mblog.getMblogDepend() == MblogConstants.MBLOG_DEPEND_SELF){
						temp.put("teamCode", "");
						temp.put("teamName", "");
					}else{
						// 获取班组信息
						TeamInfo team = getTeamInfo(mblog.getTeamCode());
						if(null != team){
							temp.put("teamCode", mblog.getTeamCode());
							temp.put("teamName", team.getTeamName());
						}else{
							temp.put("teamCode", "");
							temp.put("teamName", "");
						}
					}

					temp.put("mblogDepend", mblog.getMblogDepend());
					temp.put("summary", mblog.getSummary());
					temp.put("content", mblog.getContent());
					temp.put("imgs", mblog.getImgs());
					temp.put("audios", mblog.getAudios());
					temp.put("videos", mblog.getVideos());
					temp.put("mblogSource", mblog.getMblogSource());
					temp.put("forwardNum", mblog.getForwardNum());
					temp.put("collectNum", mblog.getCollectNum());
					temp.put("commentNum", mblog.getCommentNum());
					temp.put("praiseNum", mblog.getPraiseNum());
					temp.put("publishTime", mblog.getPublishTime());
					temp.put("meCollect", mblog.getMeCollect());
					temp.put("mePraise", mblog.getMePraise());
					temp.put("dynamicCode", mblog.getDynamicCode());
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
					temp.put("wbSub", wbSub);
					
					mblogList.add(temp);
				}
			}
			body.put("mblogList", mblogList);
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(body);
			return result;
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			log.error("MblogInfoController.getSubWeiboList", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * 
	    * @Title: getColWeiboList  
	    * @Description: 获取收藏的微博列表
	    * @param req
	    * @param res
	    * @param inVo
	    * @throws BusinessException
		* @return ResultJSON    返回类型
	 */
	@RequestMapping(value = "/getColWeiboList")  
	@ResponseBody
	public ResultJSON getColWeiboList(HttpServletRequest req,HttpServletResponse res,@RequireValid GetColWeiboListVo inVo) throws BusinessException{  
		try{
			// 创建QO
			MblogInfoQO qo = new MblogInfoQO();
			qo.setMyUserCode(inVo.getUserCode());
			qo.setLineTime(Long.valueOf(inVo.getCurrentTime()));
			qo.setPageNo(Integer.valueOf(inVo.getPageNo()));
			qo.setPageSize(Integer.valueOf(inVo.getPageSize()));
			// 获取分页数据
			PageBean pageBean = mblogInfoService.queryMyCollectList(qo);
			List<MblogModel> mbList = pageBean.getRecordList();
			// 创建返回信息
			JSONObject body = new JSONObject();
			JSONArray mblogList = new JSONArray();
			body.put("total", pageBean.getTotalCount());
			body.put("nowSysTime", new Date().getTime());
			if(null != mbList && !mbList.isEmpty()){
				for(MblogModel mblog : mbList){
					// 分解单个信息
					JSONObject temp = new JSONObject();
					temp.put("mblogCode", mblog.getMblogCode());
					temp.put("userCode", mblog.getUserCode());
					
					// 获取用户组织信息
					SysUserInfo user =  getUserInfo(mblog.getUserCode());
					// 获取组织信息
					SysOrgInfo orgT = getOrgInfo(mblog.getOrgCode());
					if(null != user){
						temp.put("userName", user.getUserName());
						temp.put("userPhoto", user.getHeadPortrait());
					}else{
						temp.put("userName", "");
						temp.put("userPhoto", "");
					}
					if(null != orgT){
						temp.put("orgName", orgT.getSysOrgFullname());
					}else{
						temp.put("orgName", "");
					}
					
					if(mblog.getMblogDepend() == MblogConstants.MBLOG_DEPEND_SELF){
						temp.put("teamCode", "");
						temp.put("teamName", "");
					}else{
						// 获取班组信息
						TeamInfo team = getTeamInfo(mblog.getTeamCode());
						if(null != team){
							temp.put("teamCode", mblog.getTeamCode());
							temp.put("teamName", team.getTeamName());
						}else{
							temp.put("teamCode", "");
							temp.put("teamName", "");
						}
					}
					
					temp.put("mblogDepend", mblog.getMblogDepend());
					temp.put("summary", mblog.getSummary());
					temp.put("content", mblog.getContent());
					temp.put("imgs", mblog.getImgs());
					temp.put("audios", mblog.getAudios());
					temp.put("videos", mblog.getVideos());
					temp.put("mblogSource", mblog.getMblogSource());
					temp.put("forwardNum", mblog.getForwardNum());
					temp.put("collectNum", mblog.getCollectNum());
					temp.put("commentNum", mblog.getCommentNum());
					temp.put("praiseNum", mblog.getPraiseNum());
					temp.put("publishTime", mblog.getPublishTime());
					temp.put("meCollect", mblog.getMeCollect());
					temp.put("mePraise", mblog.getMePraise());
					temp.put("dynamicCode", mblog.getDynamicCode());
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
					temp.put("wbSub", wbSub);
					
					mblogList.add(temp);
				}
			}
			body.put("mblogList", mblogList);
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(body);
			return result;
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			log.error("MblogInfoController.getColWeiboList", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * 
	    * @Title: getHomeWeiboList  
	    * @Description: 获取首页微博列表
	    * @param req
	    * @param res
	    * @param inVo
	    * @throws BusinessException
		* @return ResultJSON    返回类型
	 */
	@RequestMapping(value = "/getHomeWeiboList")  
	@ResponseBody
	public ResultJSON getHomeWeiboList(HttpServletRequest req,HttpServletResponse res,@RequireValid GetHomeWeiboListVo inVo) throws BusinessException{  
		try{
			
			// 创建QO
			MblogInfoQO qo = new MblogInfoQO();
			qo.setMyUserCode(inVo.getUserCode());
			qo.setLineTime(Long.valueOf(inVo.getCurrentTime()));
			qo.setPageNo(Integer.valueOf(inVo.getPageNo()));
			qo.setPageSize(Integer.valueOf(inVo.getPageSize()));
			qo.setOtherUserCode(inVo.getOtherCode());
			// 获取分页数据
			PageBean pageBean = mblogInfoService.queryOtherMblogList(qo);
			List<MblogModel> mbList = pageBean.getRecordList();
			// 创建返回信息
			JSONObject body = new JSONObject();
			JSONArray mblogList = new JSONArray();
			body.put("total", pageBean.getTotalCount());
			body.put("nowSysTime", new Date().getTime());
			if(null != mbList && !mbList.isEmpty()){
				for(MblogModel mblog : mbList){
					// 分解单个信息
					JSONObject temp = new JSONObject();
					temp.put("mblogCode", mblog.getMblogCode());
					temp.put("userCode", mblog.getUserCode());
					
					// 获取用户组织信息
					SysUserInfo user =  getUserInfo(mblog.getUserCode());
					if(null != user){
						temp.put("userName", user.getUserName());
						temp.put("userPhoto", user.getHeadPortrait());
						temp.put("orgName", user.getManageOrgInfo().getSysOrgFullname());
					}else{
						temp.put("userName", "");
						temp.put("userPhoto", "");
						temp.put("orgName", "");
					}
					
					temp.put("summary", mblog.getSummary());
					temp.put("content", mblog.getContent());
					temp.put("imgs", mblog.getImgs());
					temp.put("audios", mblog.getAudios());
					temp.put("videos", mblog.getVideos());
					temp.put("mblogSource", mblog.getMblogSource());
					temp.put("forwardNum", mblog.getForwardNum());
					temp.put("collectNum", mblog.getCollectNum());
					temp.put("commentNum", mblog.getCommentNum());
					temp.put("praiseNum", mblog.getPraiseNum());
					temp.put("publishTime", mblog.getPublishTime());
					temp.put("meCollect", mblog.getMeCollect());
					temp.put("mePraise", mblog.getMePraise());
					temp.put("dynamicCode", mblog.getDynamicCode());
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
					temp.put("wbSub", wbSub);
					
					mblogList.add(temp);
				}
			}
			body.put("mblogList", mblogList);
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(body);
			return result;
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			log.error("MblogInfoController.getHomeWeiboList", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	/**
	 * 
	    * @Title: getWeiboListGroup  
	    * @Description: 获取微博班组空间微博列表
	    * @param req
	    * @param res
	    * @param inVo
	    * @throws BusinessException
		* @return ResultJSON    返回类型
	 */
	@RequestMapping(value = "/getWeiboListGroup")  
	@ResponseBody
	public ResultJSON getWeiboListGroup(HttpServletRequest req,HttpServletResponse res,@RequireValid GetWeiboListGroupVo inVo) throws BusinessException{  
		try{
			// 创建QO
			MblogInfoQO qo = new MblogInfoQO();
			qo.setMyUserCode(inVo.getUserCode());
			qo.setLineTime(Long.valueOf(inVo.getCurrentTime()));
			qo.setPageNo(Integer.valueOf(inVo.getPageNo()));
			qo.setPageSize(Integer.valueOf(inVo.getPageSize()));
			qo.setTeamCode(inVo.getTeamCode());
			
			// 获取分页数据
			PageBean pageBean = mblogInfoService.queryTeamMblogList(qo);
			List<MblogModel> mbList = pageBean.getRecordList();
			// 创建返回信息
			JSONObject body = new JSONObject();
			JSONArray mblogList = new JSONArray();
			body.put("total", pageBean.getTotalCount());
			body.put("nowSysTime", new Date().getTime());
			if(null != mbList && !mbList.isEmpty()){
				for(MblogModel mblog : mbList){
					// 分解单个信息
					JSONObject temp = new JSONObject();
					temp.put("mblogCode", mblog.getMblogCode());
					temp.put("userCode", mblog.getUserCode());
					
					// 获取用户组织信息
					SysUserInfo user =  getUserInfo(mblog.getUserCode());
					// 获取组织信息
					SysOrgInfo orgT = getOrgInfo(mblog.getOrgCode());
					if(null != user){
						temp.put("userName", user.getUserName());
						temp.put("userPhoto", user.getHeadPortrait());
					}else{
						temp.put("userName", "");
						temp.put("userPhoto", "");
					}
					if(null != orgT){
						temp.put("orgName", orgT.getSysOrgFullname());
					}else{
						temp.put("orgName", "");
					}
					// 获取班组信息
					TeamInfo team = getTeamInfo(mblog.getTeamCode());
					if(null != team){
						temp.put("teamCode", mblog.getTeamCode());
						temp.put("teamName", team.getTeamName());
					}else{
						temp.put("teamCode", "");
						temp.put("teamName", "");
					}
					
					temp.put("mblogDepend", mblog.getMblogDepend());
					temp.put("summary", mblog.getSummary());
					temp.put("content", mblog.getContent());
					temp.put("imgs", mblog.getImgs());
					temp.put("audios", mblog.getAudios());
					temp.put("videos", mblog.getVideos());
					temp.put("mblogSource", mblog.getMblogSource());
					temp.put("forwardNum", mblog.getForwardNum());
					temp.put("collectNum", mblog.getCollectNum());
					temp.put("commentNum", mblog.getCommentNum());
					temp.put("praiseNum", mblog.getPraiseNum());
					temp.put("publishTime", mblog.getPublishTime());
					temp.put("meCollect", mblog.getMeCollect());
					temp.put("mePraise", mblog.getMePraise());
					temp.put("dynamicCode", mblog.getDynamicCode());
					
					mblogList.add(temp);
				}
			}
			body.put("mblogList", mblogList);
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(body);
			return result;
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			log.error("MblogInfoController.getWeiboListGroup", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * 
	    * @Title: praiseWeibo  
	    * @Description: 点赞微博
	    * @param req
	    * @param res
	    * @param inVo
	    * @throws BusinessException
		* @return ResultJSON    返回类型
	 */
	@RequestMapping(value = "/praiseWeibo",method = RequestMethod.POST)  
	@ResponseBody
    public ResultJSON praiseWeibo(HttpServletRequest req,HttpServletResponse res,@RequireValid PraiseWeiboVo inVo) throws BusinessException{  
		try{
			
			// 判断原微博是否存在
			MblogInfo info = mblogInfoService.getMblogInfo(inVo.getMblogCode());
			if(null == info || info.getIsDelete() == MblogConstants.MBLOG_YES || info.getIsShield() == MblogConstants.MBLOG_YES){
				throw BusinessException.build("MBLOG_12002","微博");
			}
			// 判断是否是个人的
			if(info.getMblogDepend() == MblogConstants.MBLOG_DEPEND_SELF){
				// 判断用户关系
				if(Byte.valueOf(inVo.getActionType()) == 1){ // 点赞
					if(checkIsBlack(info.getUserCode(), inVo.getUserCode())){ // 校验关系
						throw BusinessException.build("COMMON_403");
					}
				}
			}
			
			// 创建QO
			MblogInfoQO qo = new MblogInfoQO();
			qo.setMyUserCode(inVo.getUserCode());
			qo.setIsPraise(Byte.valueOf(inVo.getActionType()) == 1 ? MblogConstants.MBLOG_YES : MblogConstants.MBLOG_NO);
			qo.setMblogCode(inVo.getMblogCode());
			
			// 获取用户组织信息
			SysUserInfo user =  getUserInfo(inVo.getUserCode());
			if(null != user){
				qo.setOrgCode(user.getManageOrgInfo().getSysOrgCode());
				qo.setTenantCode(user.getTenantCode());
			}
			
			// 点赞微博
			if(!mblogInfoService.addOrDelMblogPraise(qo)){
				log.info("MblogInfoController.praiseWeibo:调用微博服务-点赞操作失败");
				throw BusinessException.build("MBLOG_12001","点赞微博");
			}
			// 点赞动态
			praiseDynamic(qo.getMblogCode(), qo.getMyUserCode(), qo.getIsPraise(), qo.getOrgCode(), qo.getTenantCode());
			/*if(!praiseDynamic(qo.getMblogCode(), qo.getMyUserCode(), qo.getIsPraise(), qo.getOrgCode(), qo.getTenantCode())){
				log.info("MblogInfoController.praiseWeibo:调用动态服务-点赞操作失败");
				throw BusinessException.build("MBLOG_12001","点赞微博-动态");
			}*/
			if(qo.getIsPraise() == MblogConstants.MBLOG_YES){
				// 点赞消息
				sendPraiseMsg(info, qo.getMyUserCode(), MblogConstants.MBLOG_ACTION_MBLOG_PRAISE, qo.getOrgCode(), qo.getTenantCode());
			}
			
			// 修改点赞数量
			updatePraiseNum(qo.getMblogCode(), qo.getIsPraise());
			
			// 添加积分信息
			sendCreditMsg(inVo.getUserCode(), qo.getOrgCode(),CreditConstants.TYPE_INDIVIDUAL,Byte.valueOf(inVo.getActionType()) == 1 ? CreditConstants.COMMAND_ADMIRE_PUBLISH : CreditConstants.COMMAND_ADMIRE_DEL);			

			// 返回数据
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(new JSONObject());
			return result;
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			log.error("MblogInfoController.praiseWeibo", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	/**
	 * 
	    * @Title: collectWeibo  
	    * @Description: 收藏微博
	    * @param req
	    * @param res
	    * @param inVo
	    * @throws BusinessException
		* @return ResultJSON    返回类型
	 */
	@RequestMapping(value = "/collectWeibo",method = RequestMethod.POST)  
	@ResponseBody
	public ResultJSON collectWeibo(HttpServletRequest req,HttpServletResponse res,@RequireValid CollectWeiboVo inVo) throws BusinessException{  
		try{
			// 判断原微博是否存在
			MblogInfo info = mblogInfoService.getMblogInfo(inVo.getMblogCode());
			if(null == info || info.getIsDelete() == MblogConstants.MBLOG_YES || info.getIsShield() == MblogConstants.MBLOG_YES){
				throw BusinessException.build("MBLOG_12002","微博");
			}
			// 判断是否是个人的
			if(info.getMblogDepend() == MblogConstants.MBLOG_DEPEND_SELF){
				// 判断用户关系
				if(Byte.valueOf(inVo.getActionType()) == 1){
					if(checkIsBlack(info.getUserCode(), inVo.getUserCode())){
						throw BusinessException.build("COMMON_403");
					}
				}
			}
			
			// 创建QO
			MblogInfoQO qo = new MblogInfoQO();
			qo.setMyUserCode(inVo.getUserCode());
			qo.setIsCollect(Byte.valueOf(inVo.getActionType()) == 1 ? MblogConstants.MBLOG_YES : MblogConstants.MBLOG_NO);
			qo.setMblogCode(inVo.getMblogCode());
			
			// 获取用户组织信息
			SysUserInfo user =  getUserInfo(inVo.getUserCode());
			if(null != user){
				qo.setOrgCode(user.getManageOrgInfo().getSysOrgCode());
				qo.setTenantCode(user.getTenantCode());
			}
			
			// 调用微博收藏
			if(!mblogInfoService.addOrDelMblogCollect(qo)){
				log.info("MblogInfoController.collectWeibo:调用微博服务-收藏操作失败");
				throw BusinessException.build("MBLOG_12001","收藏微博");
			}
			// 调用动态收藏
			collectDynamic(qo.getMblogCode(), qo.getMyUserCode(), qo.getIsCollect(), qo.getOrgCode(), qo.getTenantCode());
			/*if(!collectDynamic(qo.getMblogCode(), qo.getMyUserCode(), qo.getIsCollect(), qo.getOrgCode(), qo.getTenantCode())){
				log.info("MblogInfoController.collectWeibo:调用动态服务-收藏操作失败");
				throw BusinessException.build("MBLOG_12001","收藏微博-动态");
			}*/
			
			// 修改收藏数量
			updateCollectNum(qo.getMblogCode(), qo.getIsCollect());
			
			// 添加积分信息
			sendCreditMsg(inVo.getUserCode(), qo.getOrgCode(),CreditConstants.TYPE_INDIVIDUAL,Byte.valueOf(inVo.getActionType()) == 1 ? CreditConstants.COMMAND_CONTENT_COLLECT : CreditConstants.COMMAND_CONTENT_COLLECTCANCEL);			
			
			// 返回信息
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(new JSONObject());
			return result;
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			log.error("MblogInfoController.collectWeibo", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * 
	    * @Title: delWeibo  
	    * @Description: 删除微博
	    * @param req
	    * @param res
	    * @param inVo
	    * @throws BusinessException
		* @return ResultJSON    返回类型
	 */
	@RequestMapping(value = "/delWeibo",method = RequestMethod.POST)  
	@ResponseBody
	public ResultJSON delWeibo(HttpServletRequest req,HttpServletResponse res,@RequireValid DelWeiboVo inVo) throws BusinessException{  
		try{
			// 判断原微博是否存在
			MblogInfo info = mblogInfoService.getMblogInfo(inVo.getMblogCode());
			if(null == info || info.getIsDelete() == MblogConstants.MBLOG_YES || info.getIsShield() == MblogConstants.MBLOG_YES){
				throw BusinessException.build("MBLOG_12002","微博");
			}
			// 判断微博是否是精华
			if(checkEssence(inVo.getDynamicCode())){
				throw BusinessException.build("COMMON_403");
			}
			// 创建QO
			MblogInfoQO qo = new MblogInfoQO();
			qo.setMyUserCode(inVo.getUserCode());
			qo.setMblogCode(inVo.getMblogCode());
			
			
			// 调用微博删除
			if(!mblogInfoService.deleteMblogInfo(qo)){
				log.info("MblogInfoController.delWeibo:调用微博服务-删除操作失败");
				throw BusinessException.build("MBLOG_12001","删除微博");
			}
			// 调用动态删除
			deleteDynamic(inVo.getMblogCode(), inVo.getDynamicCode(),inVo.getUserCode(),Byte.valueOf(inVo.getMblogSource()));
			/*if(!deleteDynamic(inVo.getMblogCode(), inVo.getDynamicCode(),inVo.getUserCode(),Byte.valueOf(inVo.getMblogSource()))){
				log.info("MblogInfoController.delWeibo:调用动态服务-删除操作失败");
				throw BusinessException.build("MBLOG_12001","删除微博-动态");
			}*/
			
			// 添加积分信息
			sendCreditMsg(inVo.getUserCode(), qo.getOrgCode(),CreditConstants.TYPE_INDIVIDUAL, Byte.valueOf(inVo.getMblogSource())== MblogConstants.MBLOG_SOURCE_SELF ? CreditConstants.COMMAND_MICROBLOG_DEL : CreditConstants.COMMAND_CONTENT_RELAYDEL);			
			
			// 返回信息
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(new JSONObject());
			return result;
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			log.error("MblogInfoController.delWeibo", e);
			throw BusinessException.build("COMMON_400");
		}
		
	}
	
	/**
	 * 
	    * @Title: delWeiboGroup  
	    * @Description: 班组长删除微博
	    * @param req
	    * @param res
	    * @param inVo
	    * @throws BusinessException
		* @return ResultJSON    返回类型
	 */
	@RequestMapping(value = "/delWeiboGroup",method = RequestMethod.POST)  
	@ResponseBody
	public ResultJSON delWeiboGroup(HttpServletRequest req,HttpServletResponse res,@RequireValid DelWeiboGroupVo inVo) throws BusinessException{  
		try{
			
			// 判断原微博是否存在
			MblogInfo info = mblogInfoService.getMblogInfo(inVo.getMblogCode());
			if(null == info || info.getIsDelete() == MblogConstants.MBLOG_YES || info.getIsShield() == MblogConstants.MBLOG_YES){
				throw BusinessException.build("MBLOG_12002","微博");
			}
			// 动态查询 是否 被置精 若果没有被置精 就调用 动态删除 
			if(!checkTeamLeader(inVo.getTeamCode(), inVo.getUserCode())){
				throw BusinessException.build("COMMON_403");
			}
			if(checkEssence(inVo.getDynamicCode())){
				throw BusinessException.build("COMMON_403");
			}
			// 创建QO
			MblogInfoQO qo = new MblogInfoQO();
			qo.setMyUserCode(inVo.getUserCode());
			qo.setMblogCode(inVo.getMblogCode());
			qo.setTeamCode(inVo.getTeamCode());
			
			// 调用微博删除
			if(!mblogInfoService.deleteMblogInfoGroup(qo)){
				log.info("MblogInfoController.delWeiboGroup:调用微博服务-删除操作失败");
				throw BusinessException.build("MBLOG_12001","删除微博");
			}
			// 调用动态删除
			deleteDynamic(inVo.getMblogCode(), inVo.getDynamicCode(),inVo.getUserCode(),MblogConstants.MBLOG_SOURCE_SELF);
			/*if(!deleteDynamic(inVo.getMblogCode(), inVo.getDynamicCode(),inVo.getUserCode(),MblogConstants.MBLOG_SOURCE_SELF)){
				log.info("MblogInfoController.delWeiboGroup:调用动态服务-删除操作失败");
				throw BusinessException.build("MBLOG_12001","删除微博-动态");
			}*/
			
			// 添加积分信息
			sendCreditMsg(inVo.getTeamCode(), qo.getOrgCode(),CreditConstants.TYPE_TEAM,CreditConstants.COMMAND_MICROBLOG_TEAMDEL);			
			
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(new JSONObject());
			return result;
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			log.error("MblogInfoController.delWeiboGroup", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * 
	    * @Title: subscribeWeibo  
	    * @Description: 订阅微博
	    * @param req
	    * @param res
	    * @param inVo
	    * @throws BusinessException
		* @return ResultJSON    返回类型
	 */
	@RequestMapping(value = "/subscribeWeibo",method = RequestMethod.POST)  
	@ResponseBody
	public ResultJSON subscribeWeibo(HttpServletRequest req,HttpServletResponse res,@RequireValid SubscribeWeiboVo inVo) throws BusinessException{  
		try{

			// 判断用户关系 收藏并且是个人的判断
			if(Byte.valueOf(inVo.getActionType()) == 1 && Byte.valueOf(inVo.getSubType()) == 1){
				if(checkIsBlack(inVo.getSubUserCode(), inVo.getUserCode())){
					throw BusinessException.build("COMMON_403");
				}
			}
			
			// 创建QO
			MblogInfoQO qo = new MblogInfoQO();
			qo.setMyUserCode(inVo.getUserCode());
			qo.setSubScribeUserCode(inVo.getSubUserCode());
			qo.setSubTeamCode(inVo.getSubTeamCode());
			qo.setIsSubscribe(Byte.valueOf(inVo.getActionType()) == 1 ? MblogConstants.MBLOG_YES : MblogConstants.MBLOG_NO);
			qo.setSubType(Byte.valueOf(inVo.getSubType()));
			
			// 获取订阅用户组织信息
			SysUserInfo user = getUserInfo(inVo.getUserCode());
			if(null != user){
				qo.setOrgCode(user.getOrgCode());
				qo.setTenantCode(user.getTenantCode());
			}
			// 获取被订阅组织信息
			SysUserInfo subUser = getUserInfo(inVo.getSubUserCode());
			if(null != subUser){
				qo.setSubOrgCode(subUser.getOrgCode());
			}
			
			// 调用微博订阅
			if(!mblogInfoService.addOrDelMblogSubscribe(qo)){
				log.info("MblogInfoController.delWeibo:订阅操作失败");
				throw BusinessException.build("MBLOG_12001","订阅微博");
			}
			
			if(Byte.valueOf(inVo.getSubType()) == MblogConstants.MBLOG_SUB_PER){
				// 调用订阅消息
				sendSubscribeMsg(qo.getMyUserCode(), qo.getSubScribeUserCode(), qo.getOrgCode(), qo.getTenantCode());
			}
			
			// 添加积分信息
			sendCreditMsg(inVo.getUserCode(), qo.getOrgCode(),CreditConstants.TYPE_INDIVIDUAL,Byte.valueOf(inVo.getActionType()) == 1 ? CreditConstants.COMMAND_MICROBLOG_SUBSCRIBE : CreditConstants.COMMAND_MICROBLOG_UNSUBSCRIBE);			
			
			// 返回信息
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(new JSONObject());
			return result;
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			log.error("MblogInfoController.subscribeWeibo", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * 
	    * @Title: checkIsSubscribe  
	    * @Description: 校验是否订阅
	    * @param req
	    * @param res
	    * @param inVo
	    * @throws BusinessException
		* @return ResultJSON    返回类型
	 */
	@RequestMapping(value = "/checkIsSubscribe",method = RequestMethod.POST)  
	@ResponseBody
	public ResultJSON checkIsSubscribe(HttpServletRequest req,HttpServletResponse res,@RequireValid CheckIsSubscribeVo inVo) throws BusinessException{  
		try{
			boolean isSub = mblogInfoService.checkIsSubscribe(inVo.getUserCode(), inVo.getSubjectCode(), Byte.valueOf(inVo.getSubType()));
			// 返回信息
			ResultJSON result = new ResultJSON("COMMON_200");
			JSONObject body = new JSONObject();
			body.put("isSub", isSub ? 1 : 0);
			result.setBody(body);
			return result;
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			log.error("MblogInfoController.checkIsSubscribe", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
}
