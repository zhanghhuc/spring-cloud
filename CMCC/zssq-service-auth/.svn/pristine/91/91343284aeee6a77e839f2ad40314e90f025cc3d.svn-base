package com.zssq.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.constants.AuthConstants;
import com.zssq.dao.mapper.SequenceDeputyMapper;
import com.zssq.dao.mapper.SysDeputyInfoMapper;
import com.zssq.dao.pojo.SequenceDeputy;
import com.zssq.dao.pojo.SysDeputyInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.Message;
import com.zssq.service.ISysDeputyService;
import com.zssq.utils.DateUtils;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.utils.PropertiesUtil;

@Service("sysDeputyService")
public class SysDeputyServiceImpl implements ISysDeputyService {

	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	SysDeputyInfoMapper sysDeputyInfoMapper;

	@Autowired
	SequenceDeputyMapper sequenceDeputyMapper;

	@Override
	public Boolean addDeputy(String leaderCode, String staffCode, String apps, String userCode) throws BusinessException {
		try {
			SysDeputyInfo record = new SysDeputyInfo();
			record.setDeputyUserCode(staffCode);
			record.setDeputyLeaderCode(leaderCode);
			List<SysDeputyInfo> deputyList = sysDeputyInfoMapper.selectByRecord(record);
			if (deputyList.size() > 0) {
				Message message = null;
				message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10004);
				throw new BusinessException(message.getCode(),
						String.format(message.getTip(), "新增代发配置", "领导/代发人员已存在!"));
			} else {
				record.setDeputyCode(this.getDeputyCode());
				record.setDeputyAppCode(apps);
				record.setCreateTime(DateUtils.getFormatDateLong());
				record.setModifyTime(DateUtils.getFormatDateLong());
				record.setIsEnable(AuthConstants.BOOLEAN_TRUE);
				record.setCreateUser(userCode);
				return sysDeputyInfoMapper.insertSelective(record) == 1;
			}
		} catch (BusinessException e) {
			throw new BusinessException(e.getMessageCode(), e.getMessage());
		} catch (Exception e) {
			log.error("新增代发", e);
			throw new BusinessException("新增代发配置异常", e);
		}
	}

	@Override
	public Boolean modifyDeputy(String deputyCode, String apps) throws BusinessException {
		try {
			SysDeputyInfo record = new SysDeputyInfo();
			record.setDeputyCode(deputyCode);
			record.setDeputyAppCode(apps);
			record.setModifyTime(DateUtils.getFormatDateLong());
			return sysDeputyInfoMapper.updateDeputyApps(record) >= 1;
		} catch (Exception e) {
			log.error("修改代发配置", e);
			throw new BusinessException("修改代发配置异常", e);
		}
	}

	@Override
	public Boolean deleteDeputy(String deputyCode) throws BusinessException {
		try {
			return sysDeputyInfoMapper.deleteByCode(deputyCode) >= 1;
		} catch (Exception e) {
			log.error("删除代发", e);
			throw new BusinessException("删除代发", e);
		}
	}

	@Override
	public PageBean getPageBean(Map<String, Object> map, PageParam pageParam) throws BusinessException {
		try {
			map.put("limitStart", pageParam.getPageNo() * pageParam.getPageSize());
			map.put("limitEnd", pageParam.getPageSize());
			int count = sysDeputyInfoMapper.selectPageCount(map);
			List<SysDeputyInfo> dataList = sysDeputyInfoMapper.selectPage(map);
			return new PageBean((Integer) map.get("limitStart"), (Integer) map.get("limitEnd"), count, dataList);
		} catch (Exception e) {
			log.error("获取代发列表", e);
			throw new BusinessException("获取代发列表", e);
		}
	}

	/**
	 * 获取代发详细信息
	 * 
	 * @see com.zssq.service.ISysDeputyService#selectByCode(java.lang.String)
	 */
	@Override
	public SysDeputyInfo selectByCode(String deputyCode) throws BusinessException {
		try {
			return sysDeputyInfoMapper.selectByCode(deputyCode);
		} catch (Exception e) {
			log.error("获取代发信息", e);
			throw new BusinessException("获取代发信息", e);
		}
	}

	/**
	 * 按条件查询
	 * 
	 * @see com.zssq.service.ISysDeputyService#selectByRecord(com.zssq.dao.pojo.SysDeputyInfo)
	 */
	@Override
	public List<SysDeputyInfo> selectByRecord(SysDeputyInfo record) throws BusinessException {
		try {
			return sysDeputyInfoMapper.selectByRecord(record);
		} catch (Exception e) {
			log.error("获取代发信息", e);
			throw new BusinessException("获取代发信息", e);
		}
	}

	/**
	 * 是否代发领导该应用
	 * 
	 * @see com.zssq.service.ISysDeputyService#isDeputy(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isDeputy(String userCode, String leaderCode, String app) throws BusinessException {
		try {
			SysDeputyInfo record = new SysDeputyInfo();
			record.setDeputyAppCode(app);
			record.setDeputyUserCode(userCode);
			record.setDeputyLeaderCode(leaderCode);
			return sysDeputyInfoMapper.selectByRecord(record).size() > 0;
		} catch (Exception e) {
			log.error("是否代发领导该应用", e);
			throw new BusinessException("是否代发领导该应用", e);
		}
	}

	/**
	 * 获取代发编号
	 */
	@Override
	public String getDeputyCode() throws BusinessException {
		try {
			SequenceDeputy record = new SequenceDeputy();
			// 添加代发序列
			sequenceDeputyMapper.insert(record);
			// 获取当前年份
			String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
			// 获取5位代发序列号
			String deputyId = StringUtils.leftPad(record.getId() + "", 5, "0");
			// 返回deputyCode的格式为：pz1700001
			return AuthConstants.DEPUTY_NAME_PREFIX + year.substring(2) + deputyId;
		} catch (Exception e) {
			log.error("获取代发编号", e);
			throw new BusinessException("获取代发编号", e);
		}
	}

}
