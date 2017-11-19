package com.zssq.credit.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.zssq.credit.vo.IntegralAccountSearchVo;
import com.zssq.dao.pojo.IntegralAccount;
import com.zssq.dao.pojo.IntegralAccountList;
import com.zssq.dao.pojo.IntegralAccountListWithType;
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

	/** 组织机构服务 */
	@Autowired
	ISysOrgService sysOrgService;

	/** 班组信息服务 */
	@Autowired
	ITeamInfoService teamInfoService;

	/**
	 * 
	 * @Title: integralAccountLikeList  
	 * @Description: 公司/班组/个人的积分列表查询(总列表或模糊查询列表)
	 * @param @param param 校验前端输入参数  
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return ResultJSON    返回类型  
	 * @throws
	 */
	@RequestMapping("/accountList")
	@ResponseBody
	public ResultJSON integralAccountLikeList(@RequireValid IntegralAccountSearchVo param) throws BusinessException {
		ResultJSON rj = new ResultJSON();
		Message message = null;
		try {
			// 封装分页参数
			PageParam pageParam = new PageParam(Integer.parseInt(param.getPageNo()),
					Integer.parseInt(param.getPageSize()));
			// 账户类型：1-个人，2-班组，3-公司
			String accountType = param.getAccountType();
			// 查询积分账户编号
			String accountCode = param.getAccountCode();
			// 查询所属行政组织编号
			String orgCode = param.getOrgCode();
			// 模糊查询名称
			String name = param.getName();
			// 用户编号
			String userCode = param.getUserCode();
			
			// 若上送的orgCode为空，则查询用户所属的orgCode
			if(StringUtils.isBlank(orgCode)){
				// 根据用户编号获取用户信息
				SysUserInfo sysUserInfo = sysUserService.selectByCode(userCode);
				if (sysUserInfo != null) {
					// 用户所属行政组织信息
					SysOrgInfo manageOrgInfo = sysUserInfo.getManageOrgInfo();
					if (manageOrgInfo != null) {
						// 用户所属行政组织编号
						orgCode = manageOrgInfo.getSysOrgCode();
					}
				}
			}

			// 获取该用户所属行政组织的下级行政组织列表
			List<SysOrgInfo> sysOrgInfos = sysOrgService.selectSubManOrg(orgCode);
			
			// 封装该用户所属的行政组织及其下级行政组织列表
			List<String> orgCodes=new ArrayList<String>();
			// 添加该用户所属的行政组织编号
			orgCodes.add(orgCode);
			
			// 添加该用户所属行政组织的下级行政组织
			if (CollectionUtils.isNotEmpty(sysOrgInfos)) {
				for(SysOrgInfo sysOrgInfo:sysOrgInfos){
					orgCodes.add(sysOrgInfo.getManOrgCode());
				}
			}
			
			// 总记录数
			Integer totalCount = 0;
			// 封装输出参数json数组
			JSONArray jsonArray = new JSONArray();
			
			if ("1".equals(accountType)) {
				// 个人积分账户查询列表实体  
				IntegralAccountListWithType record = new IntegralAccountListWithType();
				record.setAccountType(accountType);
				record.setOrgCodes(orgCodes);
				
				// 个人组合查询
				// 1.orgCodes开头已经设置好
				// 2.accountCode取用户上送的
				if(StringUtils.isNotBlank(accountCode)){
					record.setAccountCode(accountCode);
				}
				// 3.个人积分账户根据个人名称name做模糊查询
				if(StringUtils.isNotBlank(name)){
					// 个人账户编号集合
					List<String> accountCodes = sysUserService.selectCodeByName(name);
					record.setAccountCodes(accountCodes);
				}
				
				// 根据orgCodes和accountType获取个人积分账户列表
				PageBean pageBean = integralAccountService.selectPageByOrgCodesAndAccountType(pageParam, record);
				
				// 总记录数
				totalCount = pageBean.getTotalCount();
				// 个人积分账户列表输出参数组装
				jsonArray = individual(accountType, jsonArray, pageBean);
			} else if ("2".equals(accountType)) {
				// 班组积分账户查询列表实体  
				IntegralAccountListWithType record = new IntegralAccountListWithType();
				record.setAccountType(accountType);
				record.setOrgCodes(orgCodes);
				
				// 班组组合查询
				// 1.orgCodes开头已经设置好
				// 2.accountCode取用户上送的
				if(StringUtils.isNotBlank(accountCode)){
					record.setAccountCode(accountCode);
				}
				// 3.班组积分账户根据班组名称name做模糊查询
				if(StringUtils.isNotBlank(name)){
					// 班组账户编号集合
					List<String> accountCodes = teamInfoService.selectCodeByName(name);
					record.setAccountCodes(accountCodes);
				}
				
				// 根据orgCodes和accountType获取班组积分账户列表
				PageBean pageBean = integralAccountService.selectPageByOrgCodesAndAccountType(pageParam, record);
				
				// 总记录数
				totalCount = pageBean.getTotalCount();
				// 班组积分账户列表输出参数组装
				jsonArray = team(accountType, jsonArray, pageBean);
			} else if ("3".equals(accountType)) {
				// 公司积分账户查询列表实体
				IntegralAccountList record = new IntegralAccountList();
				// 公司积分账户模糊查询
				if(StringUtils.isNotBlank(name)){
					orgCodes = sysOrgService.selectManOrgCodeByOrgName(name, orgCodes);
				}
				record.setAccountCodes(orgCodes);
				
				// 根据accountCodes获取班组积分账户列表
				PageBean pageBean = integralAccountService.selectPageByOrgCodes(pageParam, record);
				
				// 总记录数
				totalCount = pageBean.getTotalCount();
				// 公司积分账户列表输出参数组装
				jsonArray = corporation(accountType, jsonArray, pageBean);
			}

			JSONObject body = new JSONObject();
			body.put("totalCount", totalCount);
			body.put("dataList", jsonArray);

			message = PropertiesUtil.getMessage(CreditConstants.RETURNCODE_CREDIT_200);

			rj.setReturnCode(message.getCode());
			rj.setReturnTip(String.format(message.getTip(), "获取积分账户列表"));
			rj.setBody(body);
			return rj;
		} catch (Exception e) {
			logger.error("IntegralAccountController.integralAccountLikeList 查询积分账户列表失败", e);
			message = PropertiesUtil.getMessage(CreditConstants.RETURNCODE_CREDIT_17002);
			throw new BusinessException(message.getCode(), String.format(message.getTip(), "获取积分账户列表"));
		}
	}

	/**
	 * 
	 * @Title: individual  
	 * @Description: 组装返回给前端的个人积分账户列表
	 * @param @param accountType 账户类型
	 * @param @param jsonArray json数组
	 * @param @param pageBean 分页数据实体
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return JSONArray    返回类型  
	 * @throws
	 */
	private JSONArray individual(String accountType, JSONArray jsonArray, PageBean pageBean) throws BusinessException {
		// 查询到的个人积分账户列表
		List<IntegralAccount> recordList = pageBean.getRecordList();
		
		if (CollectionUtils.isNotEmpty(recordList)) {
			for(IntegralAccount recordTemp:recordList){
				String userName = "";                                                               // 用户名称
				String orgFullNameIndi = "";                                                        // 所属行政组织全名
				String orgCodeIndi = recordTemp.getOrgCode();                                       // 所属行政组织编号
				Integer integralBalanceIndi = 
						recordTemp.getIntegralBalance()==null?0:recordTemp.getIntegralBalance();    // 积分余额
				String accountCodeIndi = recordTemp.getAccountCode();                               // 积分账户编号
				
				integralBalanceIndi = 
						recordTemp.getIntegralBalance()==null?0:recordTemp.getIntegralBalance();    // 积分余额
				SysUserInfo sysUserInfoIndi = sysUserService.selectByCode(recordTemp.getAccountCode());
				if(sysUserInfoIndi != null){
					userName = sysUserInfoIndi.getUserName();
					SysOrgInfo manageOrgInfo = sysUserInfoIndi.getManageOrgInfo();
					if(manageOrgInfo!=null){
						orgFullNameIndi = manageOrgInfo.getSysOrgFullname();
					}
				}
				
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("userName", StringTools.formatToString(userName));
				jsonObject.put("orgFullName", StringTools.formatToString(orgFullNameIndi));
				jsonObject.put("orgCode", StringTools.formatToString(orgCodeIndi));
				jsonObject.put("accountType", StringTools.formatToString(accountType));
				jsonObject.put("integralBalance", StringTools.formatToString(integralBalanceIndi));
				jsonObject.put("accountCode", StringTools.formatToString(accountCodeIndi));
				// json数组添加元素
				jsonArray.add(jsonObject);
			}
		}
		
		return jsonArray;
	}

	/**
	 * 
	 * @Title: team  
	 * @Description: 组装返回给前端的班组积分账户列表
	 * @param @param accountType 账户类型
	 * @param @param jsonArray json数组
	 * @param @param pageBean 分页数据实体
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return JSONArray    返回类型  
	 * @throws
	 */
	private JSONArray team(String accountType, JSONArray jsonArray, PageBean pageBean) throws BusinessException {
		// 查询到的班组积分账户列表
		List<IntegralAccount> recordList = pageBean.getRecordList();
		
		if (CollectionUtils.isNotEmpty(recordList)) {
			// 班组编号集合
			List<String> teamCodes = new ArrayList<String>();
			for(IntegralAccount recordTemp:recordList){
				// 添加班组编号到teamCodes中
				teamCodes.add(recordTemp.getAccountCode());
			}
			
			// 根据班组编号集合获取班组信息集合
			List<TeamInfo> teamInfos = teamInfoService.selectByTeamCodes(teamCodes);
			
			for(IntegralAccount recordTemp:recordList){
				String orgFullNameTeam = "";                                                        // 所属行政组织全名
				Integer sumCount = 0;                                                               // 组内人数
				String teamName = "";                                                               // 班组名称
				String orgCodeTeam = recordTemp.getOrgCode();                                       // 所属行政组织编号
				Integer integralBalanceTeam = 
						recordTemp.getIntegralBalance()==null?0:recordTemp.getIntegralBalance();    // 积分余额
				String accountCodeTeam = recordTemp.getAccountCode();                               // 积分账户编号
				
				// 根据用户所属行政组织编号获取组织信息
				SysOrgInfo sysOrgInfo = sysOrgService.selectByCode(orgCodeTeam);
				if (sysOrgInfo != null) {
					orgFullNameTeam = sysOrgInfo.getSysOrgFullname();
				}
				
				// 封装输出参数json对象
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("orgFullName", StringTools.formatToString(orgFullNameTeam));
				jsonObject.put("sumCount", StringTools.formatToString(sumCount));
				jsonObject.put("teamName", StringTools.formatToString(teamName));
				jsonObject.put("orgCode", StringTools.formatToString(orgCodeTeam));
				jsonObject.put("accountType", StringTools.formatToString(accountType));
				jsonObject.put("integralBalance", StringTools.formatToString(integralBalanceTeam));
				jsonObject.put("accountCode", StringTools.formatToString(accountCodeTeam));
				
				for(TeamInfo teamInfoTemp:teamInfos){
					if(accountCodeTeam != null && accountCodeTeam.equals(teamInfoTemp.getTeamCode())){
						// 覆盖sumCount
						jsonObject.put("sumCount", StringTools.formatToString(teamInfoTemp.getSumCount()));
						// 覆盖teamName
						jsonObject.put("teamName", StringTools.formatToString(teamInfoTemp.getTeamName()));
						break;
					}
				}
				
				// json数组添加元素
				jsonArray.add(jsonObject);
			}
		}
		
		return jsonArray;
	}

	/**
	 * 
	 * @Title: corporation  
	 * @Description: 组装返回给前端的公司积分账户列表
	 * @param @param accountType 账户类型
	 * @param @param jsonArray json数组
	 * @param @param pageBean 分页数据实体
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return JSONArray    返回类型  
	 * @throws
	 */
	private JSONArray corporation(String accountType, JSONArray jsonArray, PageBean pageBean) throws BusinessException {
		// 查询到的公司积分账户列表
		List<IntegralAccount> recordList = pageBean.getRecordList();
		
		if (CollectionUtils.isNotEmpty(recordList)) {
			for(IntegralAccount recordTemp:recordList){
				String orgFullName = "";                                                         // 所属行政组织全名
				Integer integralBalance = 
						recordTemp.getIntegralBalance()==null?0:recordTemp.getIntegralBalance(); // 积分余额
				String orgCode = recordTemp.getOrgCode();                                        // 所属行政组织编号
				String accountCode = recordTemp.getAccountCode();                                // 积分账户编号
				SysOrgInfo sysOrgInfo = sysOrgService.selectByCode(recordTemp.getOrgCode());
				if(sysOrgInfo!=null){
					orgFullName = sysOrgInfo.getSysOrgFullname();
				}
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("orgFullName", StringTools.formatToString(orgFullName));
				jsonObject.put("orgCode", StringTools.formatToString(orgCode));
				jsonObject.put("accountType", StringTools.formatToString(accountType));
				jsonObject.put("integralBalance", StringTools.formatToString(integralBalance));
				jsonObject.put("accountCode", StringTools.formatToString(accountCode));
				// json数组添加元素
				jsonArray.add(jsonObject);
			}
		}
		
		return jsonArray;
	}

}
