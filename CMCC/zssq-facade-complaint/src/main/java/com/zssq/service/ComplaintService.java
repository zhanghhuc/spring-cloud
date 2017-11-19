package com.zssq.service;

import java.util.List;

import com.zssq.dao.pojo.ComplaintInfo;
import com.zssq.dao.pojo.ComplaintMonitorInfo;
import com.zssq.dao.pojo.RelaPersonComplaint;
import com.zssq.exceptions.BusinessException;
import com.zssq.utils.PageInfo;

public interface ComplaintService {
	
	/**
	 * 查询该举报信息
	 * @param infoCode 被举报信息code
	 * @param tenantCode  租户code
	 * @throws BusinessException
	 */
	public ComplaintInfo getComplaintInfo(String infoCode, String tenantCode) throws BusinessException;

	/**
	 * 举报信息的举报数加一，添加一条举报人信息
	 * @param info 举报信息对象
	 * @param person 举报人对象
	 * @throws BusinessException
	 */
	public void updateComplaintInfoAndInsertPerson(ComplaintInfo info, RelaPersonComplaint person) throws BusinessException;
	/**
	 * 添加一条举报信息，添加一条举报人信息
	 * @param info 举报信息对象
	 * @param person 举报人对象
	 * @throws BusinessException
	 */
	public void addComplaintAndPerson(ComplaintInfo info, RelaPersonComplaint person) throws BusinessException;

	/**
	 *查询举报信息列表
	 * @param info 举报信息对象
	 * @param page 分页对象
	 * @return
	 * @throws BusinessException
	 */
	public PageInfo selectComplaintList(ComplaintInfo info,PageInfo page) throws BusinessException ;

	/**
	 * 查询举报人列表
	 * @param person 举报人对象
	 * @param userCodeList 举报人code集合
	 * @param page 分页对象
	 * @return
	 */
	public PageInfo selectPersonList(RelaPersonComplaint person, List<String> userCodeList,PageInfo page)throws BusinessException ;

	/**
	 * 处理举报信息
	 * @param info 举报信息对象
	 * @param userCode 
	 * @param name 
	 * @throws BusinessException
	 */
	public void updateComplaintStatus(ComplaintInfo info, String userCode, String name)throws BusinessException ;

	/**
	 * 查询某一举报人所举报的信息列表
	 * @param complainantCode 举报人code
	 * @param tenantCode  租户code
	 * @param orgCode 举报人所在公司code
	 * @return
	 */
	public List<ComplaintInfo> selectComplaintByPerson(String complainantCode, String tenantCode, String orgCode)throws BusinessException ;

	/**
	 * 查询一个举报信息对应的所有举报人
	 * @param complaintInfoCode 举报code
	 * @param tenantCode 租户code
	 * @return
	 * @throws BusinessException
	 */
	public List<RelaPersonComplaint> selectComplaintPerson(String complaintInfoCode, String tenantCode)throws BusinessException ;

	/**
	 * 添加一条删除操作记录
	 * @param info 记录实体
	 * @throws BusinessException
	 */
	public void insertMonitorInfo(ComplaintMonitorInfo info)throws BusinessException ;

	/**
	 * 刪除一条删除操作记录
	 * @param info
	 * @throws BusinessException
	 */
	public void deleteOneMontior(ComplaintMonitorInfo info)throws BusinessException ;

	/**
	 * 查询已删除的监控信息列表
	 * @param info 查询条件实体
	 * @param page 分页数据
	 * @return
	 * @throws BusinessException
	 */
	public PageInfo selectDelMonitorList(ComplaintMonitorInfo info, PageInfo page)throws BusinessException ;

	/**
	 * 根据举报code查询举报信息
	 * @param info
	 * @return
	 * @throws BusinessException
	 */
	public ComplaintInfo selectComplaintByCode(ComplaintInfo info)throws BusinessException ;
	
}
