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
import com.zssq.blog.vo.GetReportCommentVo;
import com.zssq.dao.model.BlogCommentModel;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.BlogCommentService;
import com.zssq.service.ISysUserService;
import com.zssq.utils.PageBean;
import com.zssq.utils.StringTools;
import com.zssq.vo.BlogCommentVO;

/**
 * 
 * @ClassName: BlogCommentController  
 * @Description: 博客评论  
 * @author sry  
 * @date 2017年4月15日  
 *
 */
@Controller
@RequestMapping("/blog")
public class BlogCommentController {
	
	/** log对象 **/
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private BlogCommentService blogCommentService;
	
	@Autowired
	private ISysUserService sysUserService;
	/**
	 * 
	 * @Title: getReportCommentList  
	 * @Description: 定位被举报的评论
	 * @param inVo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value = "/getReportCommentList",method = RequestMethod.POST)  
	@ResponseBody
	public ResultJSON getReportCommentList(@RequireValid GetReportCommentVo inVo) throws BusinessException{  
		try{
			log.info("BlogCommentController.getReportCommentList：定位被举报的评论");
			// 创建QO
			String userCode = StringTools.formatToString(inVo.getUserCode());
			BlogCommentVO qo = new BlogCommentVO();
			qo.setUserCode(StringTools.formatToString(inVo.getUserCode()));
			qo.setBlogCode(inVo.getBlogCode());
			qo.setPageSize(Integer.valueOf(inVo.getPageSize()));
			qo.setCommentCode(inVo.getCommentCode());
			qo.setQueryTime(Long.valueOf(inVo.getQueryTime()));
			String reportCommentCode = inVo.getCommentCode();
			SysUserInfo quser = sysUserService.selectByCode(userCode);
			/*String orgCode = null;
			if(quser==null||quser.getManageOrgInfo()==null||StringUtils.isEmpty(quser.getManageOrgInfo().getSysOrgCode())){
				log.error("BlogCommentController.getReportCommentList：用户或组织没有获取到userCode:"+userCode);
				throw BusinessException.build("BLOG_13002", "查询热点列表");
			}
			orgCode = quser.getManageOrgInfo().getSysOrgCode();
			qo.setOrgCode(orgCode);*/
			// 获取数据
			PageBean pageBean = blogCommentService.getReportCommentList(qo);
			//组装返回参数
			List<BlogCommentModel> bcList = pageBean.getRecordList();
			JSONObject body = new JSONObject();
			body.put("commentTotal", pageBean.getTotalCount());
			body.put("pageNo", pageBean.getCurrentPage());
			body.put("reportCommentCode", reportCommentCode);
			
			// 列表
			JSONArray commentList = new JSONArray();
			if(null != bcList && !bcList.isEmpty()){
				for(BlogCommentModel mc : bcList){
					JSONObject temp = new JSONObject();
					temp.put("commentCode", mc.getCommentCode());
					temp.put("userCode", mc.getUserCode());
					// 获取用户组织信息
					SysUserInfo user = sysUserService.selectByCode(mc.getUserCode());
					if(null != user){
						temp.put("userPhotoUrl", user.getHeadPortrait());
						temp.put("userName", user.getUserName());
						temp.put("orgName", user.getManageOrgInfo().getSysOrgFullname());
						temp.put("userOrgCode", user.getManageOrgInfo().getSysOrgCode());
					}else{
						temp.put("userPhoto", "");
						temp.put("userName", "");
						temp.put("orgName", "");
						temp.put("userOrgCode", "");
					}
					temp.put("commentContent", mc.getCommentContent());
					temp.put("createTime", mc.getCreateTime());
					temp.put("commentLikeNum", mc.getCommentLikeNum());
					temp.put("commentReplyNum", mc.getCommentReplyNum());
					//TODO 标记是否 点赞
					//temp.put("isLike", mc.get)
					commentList.add(temp);
				}
			}
			body.put("commentList", commentList);
			// 创建返回值
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(body);
			log.info("BlogCommentController.getReportCommentList：定位被举报的评论成功");
			return result;
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			log.error("BlogCommentController.getReportCommentList", e);
			throw BusinessException.build("COMMON_400");
		}
	}
}
