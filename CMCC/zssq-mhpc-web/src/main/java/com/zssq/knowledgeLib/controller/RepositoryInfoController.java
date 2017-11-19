package com.zssq.knowledgeLib.controller;

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
import com.zssq.exceptions.BusinessException;
import com.zssq.knowledgeLib.vo.GetPortalKnowledgeLibListVo;
import com.zssq.model.RepositoryInfoMH;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.RepositoryInfoService;
import com.zssq.utils.StringTools;
import com.zssq.vo.RepositoryInfoVo;
/**
 * 
 * @ClassName: RepositoryInfoController  
 * @Description: 知识库信息  
 * @author sry  
 * @date 2017年4月28日  
 *
 */
@Controller
@RequestMapping("/knowledgeLib")
public class RepositoryInfoController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private RepositoryInfoService repositoryInfoService;
	/**
	 * 
	 * @Title: getPortalKnowledgeLibList  
	 * @Description: 查询公司下知识库列表
	 * @param param
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping(value="getPortalKnowledgeLibList",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON getPortalKnowledgeLibList(@RequireValid GetPortalKnowledgeLibListVo param) throws BusinessException {
		
		//返回值
		ResultJSON resultJSON = null;
		
		try {
			//方法进入
			log.info("RepositoryInfoController.getPortalKnowledgeLibList:查询公司下知识库列表");
			
			String orgCode = StringTools.formatToString(param.getOrgCode());//公司（组织）编号
			String queryTime = StringTools.formatToString(param.getQueryTime());//查询时间
			//String userCode = StringTools.formatToString(param.getUserCode());//用户编号
			
			//拼接参数
			RepositoryInfoVo infoVo = new RepositoryInfoVo();
			infoVo.setOrgCode(orgCode);
			infoVo.setQueryTime(Long.valueOf(queryTime));
			List<RepositoryInfoMH> lrepository = repositoryInfoService.getRepositoryListForMH(infoVo);
			
			//组装 返回参数
			JSONArray ja = new JSONArray();
			for(RepositoryInfoMH ri:lrepository){
				JSONObject jo = new JSONObject();
				jo.put("repositoryCode", StringTools.formatToString(ri.getRepositoryCode()));
				jo.put("repositoryTitle", StringTools.formatToString(ri.getRepositoryTitle()));
				jo.put("repositorySummary", StringTools.formatToString(ri.getRepositorySummary()));
				ja.add(jo);
			}
			JSONObject body = new JSONObject();
			body.put("libList", ja);
			resultJSON = new ResultJSON("COMMON_200", "查询公司下知识库列表");
			resultJSON.setBody(body);
			
			//方法出去
			log.info("RepositoryInfoController.getPortalKnowledgeLibList:查询公司下知识库列表成功");
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("RepositoryInfoController.getPortalKnowledgeLibList", e);
			throw BusinessException.build("COMMON_400");
		}
		
		return resultJSON;
		
	}
}
