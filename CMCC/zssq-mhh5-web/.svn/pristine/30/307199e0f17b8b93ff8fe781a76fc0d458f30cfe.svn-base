package com.zssq.credit.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.constants.CreditConstants;
import com.zssq.credit.vo.IntegralRankSearchVo;
import com.zssq.dao.pojo.IntegralAccount;
import com.zssq.dao.pojo.SysOrgInfo;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.dao.pojo.TeamInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.Message;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.IIntegralAccountService;
import com.zssq.service.ISysOrgService;
import com.zssq.service.ISysUserService;
import com.zssq.service.ITeamInfoService;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.utils.PropertiesUtil;
import com.zssq.utils.StringTools;

/**
 * 
 * @ClassName: IntegralAccountController
 * @Description: 积分账户相关的服务
 * @author power
 * @date 2017年4月12日
 *
 */
@Controller
@RequestMapping("/credit")
public class IntegralAccountController {
	
	/** 打印日志 */
	private static Logger logger = LoggerFactory.getLogger(IntegralAccountController.class);

	/** 积分账户服务 */
	@Autowired
	IIntegralAccountService integralAccountService;

	/** 员工信息服务 */
	@Autowired
	ISysUserService sysUserService;

	/** 组织信息服务 */
	@Autowired
	ISysOrgService sysOrgService;

	/** 班组信息服务 */
	@Autowired
	ITeamInfoService teamInfoService;

	/**
	 * 
	 * @Title: rankIntegralAccount  
	 * @Description: 集团门户/省门户/市门户中个人/班组的积分排行榜
	 * @param @param param
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return ResultJSON    返回类型  
	 * @throws
	 */
	@RequestMapping("/rank")
	@ResponseBody
	public ResultJSON rankIntegralAccount(@RequireValid IntegralRankSearchVo param) throws BusinessException {
		ResultJSON rj = new ResultJSON();
		Message message = null;
		try {
			// 封装分页参数
			PageParam pageParam = new PageParam(Integer.parseInt(param.getPageNo()),
					Integer.parseInt(param.getPageSize()));
			// 封装积分账户
			IntegralAccount record = new IntegralAccount();
			
			// 排行榜类型：1-个人，2-班组
			String accountType = param.getAccountType();
			record.setAccountType(Byte.parseByte(param.getAccountType()));
			
			// 门户类型：A-集团门户，B-省门户，C-市门户
			String mhType = param.getMhType();
			// 集团门户以外的门户需要设置orgCode
			if (!"A".equals(mhType)) {
				record.setOrgCode(param.getOrgCode());
			}

			// 分页查询积分账户
			PageBean pageBean = integralAccountService.selectPageRank(pageParam, record);
			
			// 积分账户列表
			List<IntegralAccount> recordList = pageBean.getRecordList();
			// 总记录数
			Integer totalCount = pageBean.getTotalCount();

			// 封装json数组
			JSONArray jsonArray = new JSONArray();
			
			if (CollectionUtils.isNotEmpty(recordList)) {
				for (IntegralAccount recordTemp : recordList) {
					// 封装json对象
					JSONObject jsonObject = new JSONObject();
					if (recordTemp != null) {
						String accountCode = recordTemp.getAccountCode();                     // 积分账户编号
						
						jsonObject.put("orgFullName", "");                                    // 所属行政组织名称
						jsonObject.put("name", "");                                           // 用户名称或班组名称
						jsonObject.put("num", 
								StringTools.formatToString(recordTemp.getRownum()));          // 排行榜名次
						jsonObject.put("integralBalance", 
								StringTools.formatToString(recordTemp.getIntegralBalance())); // 积分余额
						jsonObject.put("accountCode", 
								StringTools.formatToString(accountCode));                     
						jsonObject.put("headPortrait", 
								StringTools.formatToString(""));                              // 头像
						
						// 根据指定的组织机构编号获取组织机构信息
						SysOrgInfo sysOrgInfo = sysOrgService.selectByCode(recordTemp.getOrgCode());
						if (sysOrgInfo != null) {
							// 覆盖orgFullName
							jsonObject.put("orgFullName", StringTools.formatToString(sysOrgInfo.getSysOrgFullname()));
						}
						
						// 封装员工信息
						SysUserInfo sysUserInfo = null;
						// 封装班组信息
						TeamInfo teamInfo = null;
						
						if ("1".equals(accountType)) {
							// 根据账户编号获取员工信息
							sysUserInfo = sysUserService.selectByCode(accountCode);
							if (sysUserInfo != null) {
								// 覆盖name
								jsonObject.put("name", StringTools.formatToString(sysUserInfo.getUserName()));
								// 覆盖headPortrait
								jsonObject.put("headPortrait", 
										StringTools.formatToString(sysUserInfo.getHeadPortrait()));                            
							}
						} else if ("2".equals(accountType)) {
							// 根据账户编号获取员工信息
							teamInfo = teamInfoService.selectByCode(accountCode);
							if (teamInfo != null) {
								// 覆盖name
								jsonObject.put("name", StringTools.formatToString(teamInfo.getTeamName()));
								// 覆盖headPortrait
								jsonObject.put("headPortrait", 
										StringTools.formatToString(teamInfo.getTeamIcon()));                            
							}
						}
						
						// json数组添加元素
						jsonArray.add(jsonObject);
						
					}
				}
			}

			JSONObject body = new JSONObject();
			body.put("totalCount", totalCount);
			body.put("dataList", jsonArray);

			message = PropertiesUtil.getMessage(CreditConstants.RETURNCODE_CREDIT_200);

			rj.setReturnCode(message.getCode());
			rj.setReturnTip(String.format(message.getTip(), "获取积分排行榜列表"));
			rj.setBody(body);
			return rj;
		} catch (Exception e) {
			logger.error("IntegralAccountController.rankIntegralAccount 查询积分排行榜列表失败", e);
			message = PropertiesUtil.getMessage(CreditConstants.RETURNCODE_CREDIT_17002);
			throw new BusinessException(message.getCode(), String.format(message.getTip(), "获取积分排行榜列表"));
		}
	}

}
