package com.zssq.auth.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zssq.auth.service.OrgTransferServiceI;

@Controller
public class OrgTransferController {

	/** 组织数据迁移业务组件 */
	@Resource
	private OrgTransferServiceI orgTransferService;
	
	/**
	 * 组织数据迁移：基础迁移
	 * 
	 * @return
	 */
	@RequestMapping("/executeTransferBase") 
	@ResponseBody
	public String executeTransferBase() {
		
		orgTransferService.executeTransferBase();
		return "success";
	}
	
	/**
	 * 组织数据迁移：设置“上级组织编码”及“组织级别”
	 * 
	 * @return
	 */
	@RequestMapping("/processPraentCodeAndOrgType") 
	@ResponseBody
	public String processPraentCodeAndOrgType() {
		
		orgTransferService.processPraentCodeAndOrgType();
		return "success";
	}	
	
	/**
	 * 组织数据迁移：设置“行政组织编码”
	 * 
	 * @return
	 */
	@RequestMapping("/processManOrgCode") 
	@ResponseBody
	public String processManOrgCode() {
		
		orgTransferService.processManOrgCode();
		return "success";
	}
	
	/**
	 * 以 orgId 为筛选条件，从 kc_org 表中获取单条记录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/searchKcOrgOne")  
	@ResponseBody
	public Map<String, Object> searchKcOrgOne(HttpServletRequest request) {
		
		try {
			String orgId = request.getParameter("orgId");
			return orgTransferService.searchKcOrgOne(Integer.valueOf(orgId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 统计 kc_org 表记录数
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/searchKcOrgCount")  
	@ResponseBody
	public Integer searchKcOrgCount(HttpServletRequest request) {
		
		try {
			return orgTransferService.searchKcOrgCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 从 kc_org 表中获取指定行号的记录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/searchKcOrgPage")  
	@ResponseBody
	public List<Map<String, Object>> searchKcOrgPage(HttpServletRequest request) {
		
		try {
			Integer startRowNum = Integer.valueOf(request.getParameter("startRowNum"));
			Integer endRowNum = Integer.valueOf(request.getParameter("endRowNum"));
			
			return orgTransferService.searchKcOrgPage(startRowNum, endRowNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
