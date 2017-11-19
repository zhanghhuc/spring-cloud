package com.zssq.job.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.exceptions.BusinessException;
import com.zssq.job.vo.JobVO;
import com.zssq.pojo.Message;
import com.zssq.pojo.ResultJSON;
import com.zssq.util.JobUtil;
import com.zssq.utils.PropertiesUtil;


@Controller  
@RequestMapping("/job")
public class JobController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping("/addJob")  
	@ResponseBody
    public ResultJSON addJob(HttpServletRequest request,HttpServletResponse response,@RequireValid JobVO param) throws BusinessException{  
        
        Message m = null;
        ResultJSON rj = new ResultJSON();
        try {
        	JobUtil.runCustomJob(param.getJobName(), param.getJobClass(), Integer.valueOf(param.getShardingTotalCount()), param.getCron());
        	
        	m = PropertiesUtil.getMessage("COMMON_200");
            
            rj.setReturnCode(m.getCode());
            rj.setReturnTip(m.getTip());
            rj.setBody(new JSONObject());
            
            log.info("添加定时任务成功,任务名:" + param.getJobName() + ",任务类:" + param.getJobClass());
            
		} catch (Exception e) {
			throw BusinessException.build("JOB_0", "添加任务");
		}
        
        return rj;
	}
}
