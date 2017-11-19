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
import com.zssq.dao.pojo.MblogComment;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.mblog.vo.GotoWeiboComVo;
import com.zssq.pojo.ResultJSON;
import com.zssq.qo.CommentInfoQO;
import com.zssq.utils.PageBean;

/**
 * 
    * @ClassName: MblogCommentController  
    * @Description: 微博评论  
    * @author Mr.B  
    * @date 2017年3月23日  
    *
 */
@Controller
@RequestMapping("/mblog")
public class MblogCommentController extends MblogBaseController{

	/** log对象 **/
	private Logger log = Logger.getLogger(this.getClass());
	
	
	/**
	 * 
	    * @Title: goToWeiboCom  
	    * @Description: 定位评论
	    * @param req
	    * @param res
	    * @param inVo
	    * @throws BusinessException
		* @return ResultJSON    返回类型
	 */
	@RequestMapping(value = "/goToWeiboCom")  
	@ResponseBody
	public ResultJSON goToWeiboCom(HttpServletRequest req,HttpServletResponse res,@RequireValid GotoWeiboComVo inVo) throws BusinessException{  
		try{
			// TODO 获取根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			
			// 创建QO
			CommentInfoQO qo = new CommentInfoQO();
			qo.setMyUserCode(inVo.getUserCode());
			qo.setMblogCode(inVo.getMblogCode());
			qo.setLineTime(Long.valueOf(inVo.getCurrentTime()));
			qo.setPageSize(Integer.valueOf(inVo.getPageSize()));
			qo.setCommentCode(inVo.getCommentCode());
			
			// 获取数据
			PageBean pageBean = mblogCommentService.queryCommentList(qo);
			List<MblogComment> mcList = pageBean.getRecordList();
			JSONObject body = new JSONObject();
			body.put("total", pageBean.getTotalCount());
			body.put("nowSysTime", new Date().getTime());
			body.put("currentNo", pageBean.getCurrentPage());
			body.put("goToCommentCode", inVo.getCommentCode());
			
			// 列表
			JSONArray commentList = new JSONArray();
			if(null != mcList && !mcList.isEmpty()){
				for(MblogComment mc : mcList){
					JSONObject temp = new JSONObject();
					temp.put("mblogCode", mc.getMblogCode());
					temp.put("commentCode", mc.getMblogCommentCode());
					temp.put("userCode", mc.getUserCode());
					// 获取用户组织信息
					SysUserInfo user = getUserInfo(mc.getUserCode());
					if(null != user){
						temp.put("userPhoto", user.getHeadPortrait());
						temp.put("userName", user.getUserName());
						temp.put("orgName", user.getManageOrgInfo().getSysOrgName());
					}else{
						temp.put("userPhoto", "");
						temp.put("userName", "");
						temp.put("orgName", "");
					}
					temp.put("content", mc.getContent());
					temp.put("timeSign", mc.getCreateTime());
					temp.put("mePraise", mc.getMePraise());
					temp.put("praiseNum", mc.getPraiseNum());
					temp.put("replyNum", mc.getReplyNum());
					commentList.add(temp);
				}
			}
			body.put("commentList", commentList);
			// 创建返回值
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(body);
			return result;
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			log.error("MblogInfoController.goToWeiboCom", e);
			throw BusinessException.build("COMMON_400");
		}
	}
}
