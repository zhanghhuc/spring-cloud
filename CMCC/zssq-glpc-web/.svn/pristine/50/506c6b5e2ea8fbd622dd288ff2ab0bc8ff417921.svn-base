package com.zssq.blog.controller;

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
import com.zssq.blog.vo.GetCommentReplyVo;
import com.zssq.dao.model.BlogReplyModel;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.BlogCommentService;
import com.zssq.service.ISysUserService;
import com.zssq.utils.PageBean;
import com.zssq.vo.BlogCommentVO;

/**
 * 
 * @ClassName: BlogCommentReplyController  
 * @Description: 博客评论回复  
 * @author sry  
 * @date 2017年4月15日  
 *
 */
@Controller
@RequestMapping("/blog")
public class BlogCommentReplyController {


	/** log对象 **/
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private BlogCommentService blogCommentService;
	
	@Autowired
	private ISysUserService sysUserService;
	/**
	 * 
	 * @Title: getReportCommentList  
	 * @Description: 定位 博客 评论 回复
	 * @param inVo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value = "/getReportReplyList",method = RequestMethod.POST)  
	@ResponseBody
	public ResultJSON getReportReplyList(@RequireValid GetCommentReplyVo inVo) throws BusinessException{  
		try{
			log.info("BlogCommentReplyController.getReportReplyList：定位 博客 评论 回复");
			// 创建QO
			BlogCommentVO qo = new BlogCommentVO();
			qo.setUserCode(inVo.getUserCode());
			qo.setBlogCode(inVo.getBlogCode());
			qo.setPageSize(Integer.valueOf(inVo.getPageSize()));
			qo.setReplyCode(inVo.getReplyCode());
			qo.setCommentCode(inVo.getCommentCode());
			qo.setQueryTime(Long.valueOf(inVo.getQueryTime()));
			String reportReplyCode = inVo.getReplyCode();
			// 获取数据
			PageBean pageBean = blogCommentService.getReportReplyList(qo);
			//组装返回参数
			List<BlogReplyModel> brList = pageBean.getRecordList();
			JSONObject body = new JSONObject();
			body.put("replyTotal", pageBean.getTotalCount());
			body.put("pageNo", pageBean.getCurrentPage());
			body.put("reportReplyCode", reportReplyCode);
			
			// 列表
			JSONArray commentList = new JSONArray();
			if(null != brList && !brList.isEmpty()){
				for(BlogReplyModel br : brList){
					JSONObject temp = new JSONObject();
					temp.put("replyCode", br.getReplyCode());
					temp.put("replyUserCode", br.getUserCode());
					// 获取用户组织信息
					SysUserInfo user = sysUserService.selectByCode(br.getUserCode());
					if(null != user){
						temp.put("replyUserPhotoUrl", user.getHeadPortrait());
						temp.put("replyUserName", user.getUserName());
						temp.put("replyUserOrgName", user.getManageOrgInfo().getSysOrgFullname());
						temp.put("replyUserOrgCode", user.getManageOrgInfo().getSysOrgCode());
					}else{
						temp.put("replyUserPhotoUrl", "");
						temp.put("replyUserName", "");
						temp.put("replyUserOrgName", "");
						temp.put("replyUserOrgCode", "");
					}
					temp.put("toReplyUserCode", br.getToReplyUserCode());
					SysUserInfo toUser = sysUserService.selectByCode(br.getToReplyUserCode());
					if(null != toUser){
						temp.put("toReplyUserPhotoUrl", toUser.getHeadPortrait());
						temp.put("toReplyUserName", toUser.getUserName());
						temp.put("toReplyUserOrgName", toUser.getOrgInfo().getSysOrgFullname());
						temp.put("toReplyUserOrgCode", toUser.getOrgCode());
					}else{
						temp.put("toReplyUserPhotoUrl", "");
						temp.put("toReplyUserName", "");
						temp.put("toReplyUserOrgName", "");
						temp.put("toReplyUserOrgCode", "");
					}
					temp.put("replyContent", br.getReplyContent());
					temp.put("createTime", br.getCreateTime());
					temp.put("replyLikeNum", br.getReplyLikeNum());
					//TODO 标记是否 点赞
					//temp.put("isLike", mc.get)
					commentList.add(temp);
				}
			}
			body.put("commentList", commentList);
			// 创建返回值
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(body);
			log.info("BlogCommentReplyController.getReportReplyList：定位 博客 评论 回复成功");
			return result;
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			log.error("BlogCommentReplyController.getReportReplyList", e);
			throw BusinessException.build("COMMON_400");
		}
	}
}
