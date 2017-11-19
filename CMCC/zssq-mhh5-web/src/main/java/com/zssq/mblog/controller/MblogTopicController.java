package com.zssq.mblog.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.dao.pojo.MblogTopic;
import com.zssq.exceptions.BusinessException;
import com.zssq.mblog.vo.GetWeiboHotTopicVo;
import com.zssq.mblog.vo.GetWeiboTopicVo;
import com.zssq.pojo.ResultJSON;
import com.zssq.qo.TopicInfoQO;
import com.zssq.service.MblogTopicService;
import com.zssq.utils.PageBean;
import com.zssq.utils.StringTools;

/**
 * 
    * @ClassName: MblogTopicController  
    * @Description: 微博话题  
    * @author Mr.B  
    * @date 2017年3月23日  
    *
 */
@Controller
@RequestMapping("/mblog")
public class MblogTopicController {

	/** log对象 **/
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private MblogTopicService mblogTopicService;
	
	/**
	 * 
	    * @Title: getWeiboTopic  
	    * @Description: 获取的话题信息
	    * @param req
	    * @param res
	    * @param inVo
	    * @throws BusinessException
		* @return ResultJSON    返回类型
	 */
	@RequestMapping(value = "/getWeiboTopic",method = RequestMethod.POST)  
	@ResponseBody
    public ResultJSON getWeiboTopic(HttpServletRequest req,HttpServletResponse res,@RequireValid GetWeiboTopicVo inVo) throws BusinessException{  
		try{
			// TODO 获取根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			
			// 创建QO
			TopicInfoQO qo = new TopicInfoQO();
			qo.setTopicName(inVo.getTopicName());
			
			// 组织信息
			qo.setOrgCode("");
			qo.setTenantCode("");
			
			String topicCode = mblogTopicService.addTopicInfo(qo);
			if(StringTools.isEmpty(topicCode)){
				log.info("MblogInfoController.getWeiboTopic:获取主题失败");
				throw BusinessException.build("COMMON_400");
			}
			JSONObject body = new JSONObject();
			body.put("topicCode", topicCode);
			
			// 创建返回值
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(body);
			return result;
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			log.error("MblogInfoController.getWeiboTopic", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	/**
	 * 
	    * @Title: getWeiboHotTopics  
	    * @Description: 获取热门微博话题
	    * @param req
	    * @param res
	    * @param inVo
	    * @throws BusinessException
		* @return ResultJSON    返回类型
	 */
	@RequestMapping(value = "/getWeiboHotTopics")  
	@ResponseBody
    public ResultJSON getWeiboHotTopics(HttpServletRequest req,HttpServletResponse res,@RequireValid GetWeiboHotTopicVo inVo) throws BusinessException{  
		try{
			// TODO 获取根据userCode信息查询tenant_code(租户标识)、org_code(组织机构编码)、org_level(组织机构等级)
			
			// 创建QO
			TopicInfoQO qo = new TopicInfoQO();
			qo.setPageNo(Integer.valueOf(inVo.getPageNo()));
			qo.setPageSize(Integer.valueOf(inVo.getPageSize()));
			// 获取数据
			PageBean pageBean = mblogTopicService.queryTopicList(qo);
			JSONObject body = new JSONObject();
			body.put("total", pageBean.getTotalCount());
			body.put("nowSysTime", new Date().getTime());
			
			List<MblogTopic> mtList = pageBean.getRecordList();
			JSONArray topicList = new JSONArray();
			if(null != mtList && !mtList.isEmpty()){
				for(MblogTopic mt : mtList){
					JSONObject temp =  new JSONObject();
					temp.put("topicCode", mt.getMblogTopicCode());
					temp.put("topicName", mt.getTopicName());
					topicList.add(temp);
				}
			}
			body.put("topicList", topicList);
			// 创建返回值
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(body);
			return result;
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			log.error("MblogInfoController.getWeiboHotTopics", e);
			throw BusinessException.build("COMMON_400");
		}
	}
}
