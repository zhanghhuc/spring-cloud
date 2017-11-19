package com.zssq.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.constants.ComplaintConstants;
import com.zssq.dao.mapper.ComplaintHandlerRecordMapper;
import com.zssq.dao.mapper.ComplaintInfoMapper;
import com.zssq.dao.mapper.ComplaintMonitorInfoMapper;
import com.zssq.dao.mapper.RelaPersonComplaintMapper;
import com.zssq.dao.pojo.ComplaintHandlerRecord;
import com.zssq.dao.pojo.ComplaintInfo;
import com.zssq.dao.pojo.ComplaintInfoExample;
import com.zssq.dao.pojo.ComplaintMonitorInfo;
import com.zssq.dao.pojo.ComplaintMonitorInfoExample;
import com.zssq.dao.pojo.RelaPersonComplaint;
import com.zssq.dao.pojo.RelaPersonComplaintExample;
import com.zssq.dao.pojo.ComplaintInfoExample.Criteria;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.ComplaintService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.DateUtils;
import com.zssq.utils.PageInfo;

@Service("complaintService")
public class ComplaintServiceImpl implements ComplaintService{

	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private ComplaintInfoMapper complaintInfoMapper;
	@Autowired
	private ComplaintHandlerRecordMapper complaintHandlerRecordMapper;
	@Autowired
	private RelaPersonComplaintMapper personMapper;
	@Autowired
	private RelaPersonComplaintMapper relaPersonComplaintMapper;
	@Autowired
	private ComplaintMonitorInfoMapper complaintMonitorInfoMapper;

	@Override
	public ComplaintInfo getComplaintInfo(String infoCode, String tenantCode) throws BusinessException {
		try {
			ComplaintInfoExample example = new ComplaintInfoExample();
			ComplaintInfoExample.Criteria criteria = example.createCriteria();
			
			criteria.andIsDeleteEqualTo(ComplaintConstants.COMPLAINT_NO);
			criteria.andIsDisableEqualTo(ComplaintConstants.COMPLAINT_NO);
			criteria.andTenantCodeEqualTo(tenantCode);
			//被举报信息CODE
			criteria.andInfoCodeEqualTo(infoCode);
			
			List<ComplaintInfo> list = complaintInfoMapper.selectByExample(example);
			if(list.size()>0){
				return list.get(0);
			}
			return null;
		} catch (Exception e) {
			log.error("ComplaintService.getComplaintInfo", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@Override
	public synchronized void updateComplaintInfoAndInsertPerson(ComplaintInfo info, RelaPersonComplaint person) throws BusinessException {
		
		try {
			Long time = DateUtils.getFormatDateLong();
		
			//添加举报人信息
			person.setRelaPersonComplaintCode(UUIDHelper.getUUID());
			person.setCreateTime(time);
			person.setModifyTime(time);
			person.setComplaintTime(time);
			int count = personMapper.insertSelective(person);
			
			//举报信息的举报数加一
			if(count>0){
				increaseComplaintCount(info, time);
			}
			
		} catch (Exception e) {
			log.error("ComplaintService.updateComplaintInfoAndInsertPerson", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	/**
	 * 举报信息的举报数加一
	 * @param info 举报信息
	 * @param time 当前时间
	 * @throws BusinessException 
	 */
	private boolean increaseComplaintCount(ComplaintInfo info, Long time) throws BusinessException {
		try {
			ComplaintInfoExample example = new ComplaintInfoExample();
			Criteria criteria = example.createCriteria();
			
			criteria.andIsDeleteEqualTo(ComplaintConstants.COMPLAINT_NO);
			criteria.andIsDisableEqualTo(ComplaintConstants.COMPLAINT_NO);
			//设置举报 code
			criteria.andComplaintInfoCodeEqualTo(info.getComplaintInfoCode());
			//设置租户code
			criteria.andTenantCodeEqualTo(info.getTenantCode());
			
			//此条记录的修改时间改为当前时间
			info.setModifyTime(time);
			
			//举报次数加一
			int count = complaintInfoMapper.updateByExampleSelective(info, example);
			if(count < 1){
				return false;
			}
			return true;
		} catch (Exception e) {
			log.error("ComplaintService.increaseComplaintCount", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@Override
	public synchronized void addComplaintAndPerson(ComplaintInfo info, RelaPersonComplaint person) throws BusinessException {
		
		try {
			//举报信息code
			String complaintUUID = UUIDHelper.getUUID();
			Long currentTime = DateUtils.getFormatDateLong();
			
			info.setComplaintInfoCode(complaintUUID);
			info.setCreateTime(currentTime);
			info.setModifyTime(currentTime);
			info.setComplaintCount(1);
			
			//添加举报信息
			int count = complaintInfoMapper.insertSelective(info);
			
			if(count > 0){
				String personUUID = UUIDHelper.getUUID();
				person.setRelaPersonComplaintCode(personUUID);
				person.setCreateTime(currentTime);
				person.setModifyTime(currentTime);
				person.setComplaintCode(complaintUUID);
				person.setComplaintTime(currentTime);
				//添加举报人信息
				personMapper.insertSelective(person);
			}
			
		} catch (Exception e) {
			log.error("ComplaintService.addComplaintAndPerson", e);
			throw BusinessException.build("COMMON_400");
		}
		
	}

	@Override
	public PageInfo selectComplaintList(ComplaintInfo info,PageInfo page) throws BusinessException {
		
		try {
			ComplaintInfoExample example = new ComplaintInfoExample();
			Criteria criteria = example.createCriteria();
			
			criteria.andIsDeleteEqualTo(ComplaintConstants.COMPLAINT_NO);
			criteria.andIsDisableEqualTo(ComplaintConstants.COMPLAINT_NO);
			
			//设置排序规则
			if(info.getOrderBy() == 1){
				example.setOrderByClause("publish_time desc");
			}
			if(info.getOrderBy() == 2){
				example.setOrderByClause("complaint_count desc");
			}
			//只能处理本公司的内容的举报信息
			criteria.andTenantCodeEqualTo(info.getTenantCode());
			criteria.andPublisherOrgCodeEqualTo(info.getPublisherOrgCode());
			//设置处理状态
			criteria.andHandleStatusEqualTo(info.getHandleStatus());
			//应用类型
			criteria.andApplicationEqualTo(info.getApplication());
			//查询总条数
			int count = complaintInfoMapper.countByExample(example);
			page.setTotalItem(count);
			
			//查询该页记录
			List<ComplaintInfo> list = new ArrayList<ComplaintInfo>();
			if(count >0){	
				//设置分页
				example.setLimitStart(page.getStartRow());
				example.setLimitEnd(page.getPerPageSize());
				list = complaintInfoMapper.selectByExample(example);
			}
			page.setList(list);
			
			return page;
		} catch (Exception e) {
			log.error("ComplaintService.selectComplaintList", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@Override
	public PageInfo selectPersonList(RelaPersonComplaint person, List<String> userCodeList,PageInfo page) throws BusinessException {
		
		try {
			RelaPersonComplaintExample example = new RelaPersonComplaintExample();
			RelaPersonComplaintExample.Criteria criteria = example.createCriteria();
			
			criteria.andIsDeleteEqualTo(ComplaintConstants.COMPLAINT_NO);
			criteria.andIsDisableEqualTo(ComplaintConstants.COMPLAINT_NO);
			criteria.andTenantCodeEqualTo(person.getTenantCode());
			//举报人code
			if(userCodeList.size()>0){
				criteria.andComplainantCodeIn(userCodeList);
			}
			//举报理由
			if(person.getReason() != null){
				criteria.andReasonEqualTo(person.getReason());
			}
			//被举报信息code
			criteria.andComplaintCodeEqualTo(person.getComplaintCode());
			
			//查询总条数
			int count = personMapper.countByExample(example);
			page.setTotalItem(count);

			//查询该页记录
			List<RelaPersonComplaint> list = new ArrayList<RelaPersonComplaint>();
			if (count > 0) {
				//设置分页
				example.setLimitStart(page.getStartRow());
				example.setLimitEnd(page.getPerPageSize());
				list = personMapper.selectByExample(example);
			}
			page.setList(list);
			return page;
		} catch (Exception e) {
			log.error("ComplaintService.selectPersonList", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@Override
	public ComplaintInfo selectComplaintByCode(ComplaintInfo info) throws BusinessException {
		try {
			ComplaintInfoExample example = new ComplaintInfoExample();
			Criteria criteria = example.createCriteria();

			criteria.andIsDeleteEqualTo(ComplaintConstants.COMPLAINT_NO);
			criteria.andIsDisableEqualTo(ComplaintConstants.COMPLAINT_NO);
			criteria.andTenantCodeEqualTo(info.getTenantCode());
			// 举报信息code
			criteria.andComplaintInfoCodeEqualTo(info.getComplaintInfoCode());

			List<ComplaintInfo> list = complaintInfoMapper.selectByExample(example);
			if (list.size() < 1) {
				//举报信息不存在
				throw BusinessException.build("COMPLAINT_20002","举报信息");
			}
			return list.get(0);
		}catch(BusinessException e){
			throw e;
		} catch (Exception e) {
			log.error("ComplaintService.selectComplaintByCode", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
	@Override
	public void updateComplaintStatus(ComplaintInfo info, String userCode,String name) throws BusinessException {
		try {
			ComplaintInfoExample example = new ComplaintInfoExample();
			Criteria criteria = example.createCriteria();

			criteria.andIsDeleteEqualTo(ComplaintConstants.COMPLAINT_NO);
			criteria.andIsDisableEqualTo(ComplaintConstants.COMPLAINT_NO);
			criteria.andTenantCodeEqualTo(info.getTenantCode());
			// 举报信息code
			criteria.andComplaintInfoCodeEqualTo(info.getComplaintInfoCode());
			
			// 修改举报的状态
			ComplaintInfo toChange = new ComplaintInfo();
			Long modifyTime = DateUtils.getFormatDateLong();
			toChange.setModifyTime(modifyTime);
			if (info.getHandleStatus().equals(ComplaintConstants.COMPLAINT_STATUS_BACK)) {
				// 恢复-將状态置为未处理
				toChange.setHandleStatus(ComplaintConstants.COMPLAINT_STATUS_UNHANDLE);
			}else{
				toChange.setHandleStatus(info.getHandleStatus());
			}
			complaintInfoMapper.updateByExampleSelective(toChange, example);
			
			//生成举报处理历程
			ComplaintHandlerRecord record = new ComplaintHandlerRecord();
			Long time = DateUtils.getFormatDateLong();
			record.setComplaintHandlerRecordCode(UUIDHelper.getUUID());
			record.setTenantCode(info.getTenantCode());
			record.setOrgCode(info.getOrgCode());
			record.setCreateTime(time);
			record.setModifyTime(time);
			record.setComplaintCode(info.getComplaintInfoCode());
			record.setHandlerCode(userCode);
			record.setHandlerName(name);
			record.setHandleTime(time);
			record.setHandleResult(info.getHandleStatus());
			complaintHandlerRecordMapper.insertSelective(record);
		} catch (Exception e) {
			log.error("ComplaintService.updateComplaintStatus", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@Override
	public List<ComplaintInfo> selectComplaintByPerson(String complainantCode, String tenantCode, String orgCode) throws BusinessException {
		
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("complainantCode", complainantCode);
			map.put("tenantCode", tenantCode);
			map.put("orgCode", orgCode);
			
			List<ComplaintInfo> infoList = complaintInfoMapper.selectComplaintInfoByPersonCode(map);
			
			return infoList;
		} catch (Exception e) {
			log.error("ComplaintService.selectComplaintByPerson", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@Override
	public List<RelaPersonComplaint> selectComplaintPerson(String complaintInfoCode, String tenantCode)
			throws BusinessException {
		try {
			RelaPersonComplaintExample example = new RelaPersonComplaintExample();
			RelaPersonComplaintExample.Criteria criteria = example.createCriteria();
			
			criteria.andIsDeleteEqualTo(ComplaintConstants.COMPLAINT_NO);
			criteria.andIsDisableEqualTo(ComplaintConstants.COMPLAINT_NO);
			criteria.andTenantCodeEqualTo(tenantCode);
			//举报CODE
			criteria.andComplaintCodeEqualTo(complaintInfoCode);
			
			return relaPersonComplaintMapper.selectByExample(example);
			
		} catch (Exception e) {
			log.error("ComplaintService.selectComplaintPerson", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@Override
	public void insertMonitorInfo(ComplaintMonitorInfo info) throws BusinessException {
		
		try {
			Long time = DateUtils.getFormatDateLong();
			
			info.setCode(UUIDHelper.getUUID());
			info.setCreateTime(time);
			info.setModifyTime(time);
			
			complaintMonitorInfoMapper.insertSelective(info);
		} catch (Exception e) {
			log.error("ComplaintService.insertMonitorInfo", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@Override
	public void deleteOneMontior(ComplaintMonitorInfo info) throws BusinessException {
		try {
			ComplaintMonitorInfoExample example = new ComplaintMonitorInfoExample();
			ComplaintMonitorInfoExample.Criteria criteria = example.createCriteria();
			
			criteria.andIsDeleteEqualTo(ComplaintConstants.COMPLAINT_NO);
			criteria.andIsDisableEqualTo(ComplaintConstants.COMPLAINT_NO);
			criteria.andTenantCodeEqualTo(info.getTenantCode());
			criteria.andOrgCodeEqualTo(info.getOrgCode());
			//被監控的信息code
			criteria.andInfoCodeEqualTo(info.getInfoCode());
			
			complaintMonitorInfoMapper.deleteByExample(example);
		} catch (Exception e) {
			log.error("ComplaintService.deleteOneMontior", e);
			throw BusinessException.build("COMMON_400");
		}
	}

	@Override
	public PageInfo selectDelMonitorList(ComplaintMonitorInfo info, PageInfo page) throws BusinessException {
		
		try {
			ComplaintMonitorInfoExample example = new ComplaintMonitorInfoExample();
			ComplaintMonitorInfoExample.Criteria criteria = example.createCriteria();
			
			criteria.andIsDeleteEqualTo(ComplaintConstants.COMPLAINT_NO);
			criteria.andIsDisableEqualTo(ComplaintConstants.COMPLAINT_NO);
			criteria.andTenantCodeEqualTo(info.getTenantCode());
			//监控的应用
			criteria.andApplicationEqualTo(info.getApplication());
			example.setOrderByClause("id desc");
			
			int count = complaintMonitorInfoMapper.countByExample(example);
			List<ComplaintMonitorInfo> list = new ArrayList<ComplaintMonitorInfo>();
			if(count > 0){
				if(page.isPageFlag()){
					example.setLimitStart(page.getStartRow());
					example.setLimitEnd(page.getPerPageSize());
				}
				list = complaintMonitorInfoMapper.selectByExample(example);
			}
			page.setTotalItem(count);
			page.setList(list);
			return page;
		} catch (Exception e) {
			log.error("ComplaintService.selectDelMonitorList", e);
			throw BusinessException.build("COMMON_400");
		}
	}
}