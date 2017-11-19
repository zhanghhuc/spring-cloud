package com.zssq.mblog.controller;

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
import com.zssq.constants.MblogConstants;
import com.zssq.dao.pojo.MblogInfo;
import com.zssq.dao.pojo.MblogModel;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.mblog.vo.GetWeiboInfoVo;
import com.zssq.mblog.vo.GetWeiboListVo;
import com.zssq.mblog.vo.ShieldWeiboVo;
import com.zssq.pojo.ResultJSON;
import com.zssq.qo.MblogInfoQO;
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
			// TODO 获取用户以及组织信息
			// 创建QO
			MblogInfoQO qo = new MblogInfoQO();
			qo.setMyUserCode(inVo.getUserCode());
			qo.setMblogCode(inVo.getMblogCode());
			// 获取分页数据
			MblogModel mblog = mblogInfoService.getMblogInfo(qo);
			// 创建返回信息
			JSONObject body = new JSONObject();
			body.put("nowSysTime", new Date().getTime());
			// 分解单个信息
			body.put("mblogCode", mblog.getMblogCode());
			body.put("userCode", mblog.getUserCode());
			
			// 获取用户组织信息
			SysUserInfo user =  sysUserService.selectByCode(mblog.getUserCode());
			if(null != user){
				body.put("userName", user.getUserName());
				body.put("userPhoto", user.getHeadPortrait());
				body.put("orgName", user.getManageOrgInfo().getSysOrgName());
			}else{
				body.put("userName", "");
				body.put("userPhoto", "");
				body.put("orgName", "");
			}
			
			body.put("teamCode", mblog.getTeamCode());
			body.put("teamName","");
			
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
				wbSub.put("mblogCode", mi.getMblogCode());
				wbSub.put("userCode", mi.getUserCode());
				
				// 获取用户组织信息
				SysUserInfo forUser =  sysUserService.selectByCode(mi.getUserCode());
				if(null != forUser){
					wbSub.put("userName", forUser.getUserName());
					wbSub.put("userPhoto", forUser.getHeadPortrait());
					wbSub.put("orgName", forUser.getManageOrgInfo().getSysOrgName());
				}else{
					wbSub.put("userName", "");
					wbSub.put("userPhoto", "");
					wbSub.put("orgName", "");
				}
				
				wbSub.put("teamCode", mi.getTeamCode());
				wbSub.put("teamName", "");
				
				wbSub.put("mblogDepend", mi.getMblogDepend());
				wbSub.put("summary", mi.getSummary());
				wbSub.put("content", mi.getContent());
				wbSub.put("imgs", mi.getImgs());
				wbSub.put("audios", mi.getAudios());
				wbSub.put("videos", mi.getVideos());
				wbSub.put("mblogSource", mi.getMblogSource());
				wbSub.put("forwardNum", mi.getForwardNum());
				wbSub.put("collectNum", mi.getCollectNum());
				wbSub.put("commentNum", mi.getCommentNum());
				wbSub.put("praiseNum", mi.getPraiseNum());
				wbSub.put("publishTime", mi.getPublishTime());
				wbSub.put("meCollect", mi.getMeCollect());
				wbSub.put("mePraise", mi.getMePraise());
			}
			body.put("wbSub", wbSub);
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
			qo.setIsShield(Byte.valueOf(inVo.getIsShield()));
			qo.setOrgCode(inVo.getOrgCode());
			qo.setPageNo(Integer.parseInt(inVo.getPageNo()));
			qo.setPageSize(Integer.parseInt(inVo.getPageSize()));
			
			// 获取分页数据
			PageBean pageBean = mblogInfoService.getMonitoredList(qo);
			List<MblogInfo> mbList = pageBean.getRecordList();
			// 创建返回信息
			JSONObject body = new JSONObject();
			JSONArray mblogList = new JSONArray();
			body.put("totalCount", pageBean.getTotalCount());
			body.put("nowSysTime", new Date().getTime());
			if(null != mbList && !mbList.isEmpty()){
				for(MblogInfo mblog : mbList){
					// 分解单个信息
					JSONObject temp = new JSONObject();
					temp.put("mblogCode", mblog.getMblogCode());
					temp.put("dynamicCode", mblog.getDynamicCode());
					temp.put("userCode", mblog.getUserCode());
					temp.put("teamCode", mblog.getTeamCode());
					temp.put("mblogDepend", mblog.getMblogDepend());
					// 获取用户组织信息
					SysUserInfo user =  sysUserService.selectByCode(mblog.getUserCode());
					if(null != user){
						temp.put("userName", user.getUserName());
						temp.put("orgName", user.getManageOrgInfo().getSysOrgName());
					}else{
						temp.put("userName", "");
						temp.put("orgName", "");
					}
					
					// 获取用户组织信息
					if(StringTools.isNotEmpty(mblog.getShieldUserCode())){
						SysUserInfo opUser =  sysUserService.selectByCode(mblog.getShieldUserCode());
						if(null != opUser){
							temp.put("opUserName", opUser.getUserName());
						}else{
							temp.put("opUserName", "");
						}
						temp.put("opTime", mblog.getShieldTime());
					}else{
						temp.put("opUserName", "");
						temp.put("opTime", "");
					}
					temp.put("publishTime", mblog.getPublishTime());
					
					
					mblogList.add(temp);
				}
			}
			body.put("weiboList", mblogList);
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
	    * @Title: shieldWeibo  
	    * @Description: 屏蔽微博
	    * @param req
	    * @param res
	    * @param inVo
	    * @throws BusinessException
		* @return ResultJSON    返回类型
	 */
	@RequestMapping(value = "/shieldWeibo",method = RequestMethod.POST)  
	@ResponseBody
	public ResultJSON shieldWeibo(HttpServletRequest req,HttpServletResponse res,@RequireValid ShieldWeiboVo inVo) throws BusinessException{  
		try{
			// 创建QO
			MblogInfoQO qo = new MblogInfoQO();
			qo.setMyUserCode(inVo.getUserCode());
			qo.setIsShield(Byte.valueOf(inVo.getIsShield()));
			qo.setMblogCode(inVo.getMblogCode());	
			
			if(StringTools.isEmpty(inVo.getMblogCode())){
				throw BusinessException.build("MBLOG_12001","屏蔽");
			}
			if(!mblogInfoService.modifyShield(qo)){
				throw BusinessException.build("MBLOG_12001","屏蔽");
			}
			ResultJSON result = new ResultJSON("COMMON_200");
			return result;
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			log.error("MblogInfoController.shieldWeibo", e);
			throw BusinessException.build("COMMON_400");
		}
	}
}
