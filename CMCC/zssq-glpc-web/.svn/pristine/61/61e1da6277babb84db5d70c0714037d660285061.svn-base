package com.zssq.mblog.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.dao.pojo.MblogReply;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.mblog.vo.GotoWeiboReplyVo;
import com.zssq.pojo.ResultJSON;
import com.zssq.qo.ReplyInfoQO;
import com.zssq.utils.PageBean;

/**
 * 
    * @ClassName: MblogReplyController  
    * @Description: 微博回复  
    * @author Mr.B  
    * @date 2017年3月23日  
    *
 */
@Controller
@RequestMapping("/mblog")
public class MblogReplyController extends MblogBaseController{

	/** log对象 **/
	private Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 
	    * @Title: goToWeiboReply  
	    * @Description: 定位微博回复
	    * @param req
	    * @param res
	    * @param inVo
	    * @throws BusinessException
		* @return ResultJSON    返回类型
	 */
	@RequestMapping(value = "/goToWeiboReply")  
	@ResponseBody
    public ResultJSON goToWeiboReply(HttpServletRequest req,HttpServletResponse res,@RequireValid GotoWeiboReplyVo inVo) throws BusinessException{  
		try{
			// TODO 获取根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			// TODO 调用at消息通知
			
			// 创建QO
			ReplyInfoQO qo = new ReplyInfoQO();
			qo.setMyUserCode(inVo.getUserCode());
			qo.setMblogCode(inVo.getMblogCode());
			qo.setCommentCode(inVo.getCommentCode());
			qo.setReplyCode(inVo.getReplyCode());
			qo.setLineTime(Long.valueOf(inVo.getCurrentTime()));
			qo.setPageSize(Integer.valueOf(inVo.getPageSize()));
			
			// 获取数据
			PageBean pageBean = mblogReplyService.queryReplyList(qo);
			List<MblogReply> mrList = pageBean.getRecordList();
			JSONObject body = new JSONObject();
			body.put("total", pageBean.getTotalCount());
			body.put("nowSysTime", new Date().getTime());
			body.put("currentNo", pageBean.getCurrentPage());
			body.put("goToReplyCode", inVo.getReplyCode());
			
			// 列表
			JSONArray replyList = new JSONArray();
			if(null != mrList && !mrList.isEmpty()){
				for(MblogReply mr : mrList){
					JSONObject temp = new JSONObject();
					temp.put("mblogCode", mr.getMblogCode());
					temp.put("commentCode", mr.getCommentCode());
					temp.put("replyCode", mr.getMblogReplyCode());
					temp.put("userCode", mr.getUserCode());
					// 获取用户组织信息
					SysUserInfo user = getUserInfo(mr.getUserCode());
					if(null != user){
						temp.put("userPhoto", user.getHeadPortrait());
						temp.put("userName", user.getUserName());
						temp.put("orgName", user.getManageOrgInfo().getSysOrgName());
					}else{
						temp.put("userPhoto", "");
						temp.put("userName", "");
						temp.put("orgName", "");
					}
					temp.put("content", mr.getContent());
					temp.put("timeSign", mr.getCreateTime());
					temp.put("mePraise", mr.getMePraise());
					temp.put("praiseNum", mr.getPraiseNum());
					temp.put("replyedUserCode", mr.getReplyedUserCode());
					// 获取被恢复者组织信息
					SysUserInfo replyedUser = getUserInfo(mr.getReplyedUserCode());
					if(null != replyedUser){
						temp.put("replyedUserName", replyedUser.getUserName());
						temp.put("replyedUserPhoto", replyedUser.getHeadPortrait());
						temp.put("replyedOrgName", replyedUser.getManageOrgInfo().getSysOrgName());
					}else{
						temp.put("replyedUserName", "");
						temp.put("replyedUserPhoto", "");
						temp.put("replyedOrgName", "");
					}
					replyList.add(temp);
				}
			}
			body.put("replyList", replyList);
			// 创建返回值
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(body);
			return result;
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			log.error("MblogInfoController.goToWeiboReply", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	
}
