package com.zssq.vote.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.zssq.constants.AuthConstants;
import com.zssq.constants.CreditConstants;
import com.zssq.constants.HonorConstants;
import com.zssq.constants.RelationConstants;
import com.zssq.constants.VoteConstants;
import com.zssq.dao.pojo.ActivityPrize;
import com.zssq.dao.pojo.CoinEntity;
import com.zssq.dao.pojo.HonorAwardRecord;
import com.zssq.dao.pojo.MessageIntegral;
import com.zssq.dao.pojo.OrgLinkList;
import com.zssq.dao.pojo.RelationDynamic;
import com.zssq.dao.pojo.RelationDynamicTeamRel;
import com.zssq.dao.pojo.SysOrgInfo;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.dao.pojo.TeamInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.kafka.core.KafkaProducerTemplate;
import com.zssq.service.HonorService;
import com.zssq.service.ICoinAccountAndDetailService;
import com.zssq.service.ISysOrgService;
import com.zssq.service.ISysUserService;
import com.zssq.service.ITeamElectService;
import com.zssq.service.ITeamInfoService;
import com.zssq.service.RelationThirdDynamicService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.DateUtils;


/**
 * 投票模块的BaseController
 * @ClassName BaseController
 * @Description 
 * @author liurong
 * @date 2017年4月1日 上午11:32:22
 * @version 1.0
 * @since JDK 1.7
 */
public class BaseController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private ISysOrgService sysOrgService;
	@Autowired
	private HonorService honorService;
	@SuppressWarnings("rawtypes")
	@Autowired
	private KafkaProducerTemplate producerTeplate;
	@Autowired
	private ICoinAccountAndDetailService coinAccountAndDetailService;
	@Autowired
	private ITeamInfoService teamInfoService;
	
	/**
	 * 通过kafka发送行为信息（积分）
	 * @Function noticeAction
	 * @Description 
	 * @param manageOrgCode 行政组织编号
	 * @param actionCode    用户行为编号
	 * @param accountCode   根据用户行为主体的不同，该字段可存放：用户编号/班组编号/组织编号
	 * @param accountType
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	protected void noticeAction(String manageOrgCode, String actionCode, String accountCode, Byte accountType) throws BusinessException {
		MessageIntegral mi = new MessageIntegral();
		mi.setManageOrgCode(manageOrgCode);
		mi.setActionCode(actionCode);
		mi.setAccountCode(accountCode);
		mi.setAccountType(accountType);
		String data = JSONObject.toJSONString(mi);
		producerTeplate.send(CreditConstants.CREDIT_INCREMENT, data);
	}
	
	/**
	 * 获得userCode的详细信息
	 * @Function getUserInfo
	 * @Description 
	 * @param userCode
	 * @return
	 * @throws BusinessException
	 */
	protected SysUserInfo getUserInfo(String userCode) throws BusinessException {
		return sysUserService.selectByCode(userCode);
	}
	/**
	 * 获得多个userCode的详细信息
	 * @Function getUserInfos
	 * @Description 
	 * @param userCode
	 * @return
	 * @throws BusinessException
	 */
	protected List<SysUserInfo> getUserInfos(String... userCode) throws BusinessException {
		List<SysUserInfo> list = new ArrayList<SysUserInfo>();
		
		List<String> paramList = new ArrayList<String>();
		for (String string : userCode) {
			paramList.add(string);
		}
		list = sysUserService.selectByCodes(paramList);
		return list;
	}
	/**
	 * 获得某个机构的组织机构树
	 * @Function getOrgTreeByAnyOrgCode
	 * @Description 
	 * @param rangeCode
	 * @return
	 * @throws BusinessException
	 */
	protected OrgLinkList getOrgTreeByAnyOrgCode(String orgType, String orgCode) throws BusinessException {
		// 查询此rangeCode的组织机构信息
		SysOrgInfo record = new SysOrgInfo();
		// 集团，A级机构
		if (AuthConstants.ORG_TYPE_A.equals(orgType) || VoteConstants.RANGE_TYPE_ORG.toString().equals(orgType)) {
			record.setSysOrgType(AuthConstants.ORG_TYPE_A);
		} else if (AuthConstants.ORG_TYPE_B.equals(orgType) || VoteConstants.RANGE_TYPE_PRO.toString().equals(orgType)) {
			// 省，B级机构
			record.setSysOrgType(AuthConstants.ORG_TYPE_B);
		} else if (AuthConstants.ORG_TYPE_C.equals(orgType) || VoteConstants.RANGE_TYPE_CITY.toString().equals(orgType)) {
			// 市，C级机构
			record.setSysOrgType(AuthConstants.ORG_TYPE_C);
		}
		record.setSysOrgCode(orgCode);
		return sysOrgService.getOrgLinkList(record);
	}
	
	/**
	 * 根据班组CODE获得班组详情
	 * @Function getTeamInfoByCode
	 * @Description 
	 * @param teamCode 班组CODE
	 * @return
	 * @throws BusinessException
	 */
	protected TeamInfo getTeamInfoByCode(String teamCode) throws BusinessException {
		TeamInfo teamInfo = teamInfoService.selectByCode(teamCode);
		if (teamInfo == null) {
			throw BusinessException.build("VOTE_18002", "班组信息");
		}
		return teamInfo;
	}
	
	
	/**
	 * 除法运算，四舍五入保留scale位小数
	 * @Function divide
	 * @Description 
	 * @param num
	 * @param divisor
	 * @param scale
	 * @return
	 * @throws BusinessException
	 */
	protected double divide(int num, int divisor, int scale) throws BusinessException {
		return new BigDecimal(num).divide(new BigDecimal(divisor), 6 ,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 活动模块颁发荣誉
	 * @Function awardHonorForActivity
	 * @Description 
	 * @param activityPrize
	 * @throws BusinessException
	 */
	protected void awardHonorForActivity(final ActivityPrize activityPrize,final String userCode)throws BusinessException {
		
		new Thread( new Runnable() {
			
			@Override
			public void run(){
				try {
					//准备参数
					HonorAwardRecord honorAwardRecord = new HonorAwardRecord();
					honorAwardRecord.setHonorCode(activityPrize.getAwardHonorCode());
					honorAwardRecord.setHonorName(activityPrize.getAwardHonorName());
					honorAwardRecord.setHonorUrl(activityPrize.getAwardHonorUrl());
					honorAwardRecord.setAwarderCode(userCode);
					
					//根据awarderCode 查询 awarderName 和 awarderPosition
					SysUserInfo awarderInfo = getUserInfo(userCode);
					String tenantCode = awarderInfo.getTenantCode();
					String orgCode = awarderInfo.getManageOrgInfo().getSysOrgCode();
					honorAwardRecord.setAwarderName(awarderInfo.getUserName());
					honorAwardRecord.setAwarderPosition("");
					honorAwardRecord.setHonoreeType(HonorConstants.HONOREE_TYPE_1);
					honorAwardRecord.setHonoreeCode(activityPrize.getAccepterCode());
					honorAwardRecord.setAwardReason("参加活动，荣获" + activityPrize.getPrizeName());
					honorAwardRecord.setTenantCode(tenantCode);
					honorAwardRecord.setOrgCode(orgCode);
					
					List<String> codeList = honorService.addAwardingHonor(honorAwardRecord);
					for (String code : codeList) {
						try {
							/** 1、查询荣誉授予记录 */
							HonorAwardRecord record = honorService.getAwardHonorRecordInfoByCode(code,tenantCode);
							if(null != record){
								/** 2、若是授予对象为班组 查看班组是否解散 */
								Byte isDissolve = 0;
								if (HonorConstants.HONOREE_TYPE_2.equals(record.getHonoreeType())) {
									// 授予班组荣誉
									TeamInfo teamInfo = getTeamInfoByCode(record.getHonoreeCode());
									isDissolve = teamInfo.getIsDissolve();
								}
								String dynamicCode = UUIDHelper.getUUID();
								record.setDynamicCode(dynamicCode);
								/** 3、组织参数 发布动态 */
								String operateOrgCode = awarderInfo.getManageOrgInfo().getSysOrgCode();
								publishDynamicOfAwardHonor(record,operateOrgCode,isDissolve);
							}else{
								throw BusinessException.build("HONOR_21002", "荣誉授予记录");
							}
						} catch (Exception e) {
							// 动态发布失败，则进行数据回滚
							log.info("动态发布失败，进行授予荣誉记录数据回滚操作...");
							honorService.deleteAwardRecord(honorAwardRecord.getTenantCode(), code);
							log.error("动态发布失败，异常信息：", e);
							throw e;
						}
					}
					
				} catch (Exception e) {
					log.error("BaseController.awardHonorForActivity",e);
				}
			}
		}).start();
	}
	
	
	/**
	 * @Function increaseCoinAccount
	 * @Description 颁发活动奖项时增加金币
	 * @param userCode
	 * @param orgCode
	 * @param rewardGoldNum
	 * @throws BusinessException
	 */
	protected void increaseCoinAccount(final String userCode, final String orgCode, final Integer rewardGoldNum)
			throws BusinessException {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					CoinEntity coinEntity = new CoinEntity();
					coinEntity.setAccountCode(userCode);
					coinEntity.setAccountType(CreditConstants.TYPE_INDIVIDUAL);
					coinEntity.setActionComment("参与活动，获得金币");
					coinEntity.setOrgCode(orgCode);
					coinEntity.setValue(rewardGoldNum);
					coinAccountAndDetailService.saveAccountAndDetail(coinEntity);
				} catch (Exception e) {
					log.error("BaseController.increaseCoinAccount", e);
				}
			}
		}).start();
	}
	
	
	@Autowired
	private RelationThirdDynamicService relationThirdDynamicService;
	@Autowired
	private ITeamElectService teamElectService;
	
	/**
	 * @Function publishDynamicOfAwardHonor
	 * @Description 颁发荣誉发布动态信息
	 * @param honorAwardRecord
	 * @param isDissolve
	 * @throws BusinessException
	 */
	protected void publishDynamicOfAwardHonor( HonorAwardRecord honorAwardRecord, String operateOrgCode, Byte isDissolve) throws BusinessException {
		long curTime = DateUtils.getFormatDateLong();
		// 组织动态表数据
		RelationDynamic rd = new RelationDynamic();
		rd.setTenantCode(honorAwardRecord.getTenantCode());
		rd.setOrgCode(honorAwardRecord.getOrgCode());
		rd.setCreateTime(curTime);
		rd.setModifyTime(curTime);
		rd.setOperateTime(curTime);  
		rd.setDynamicCode(honorAwardRecord.getDynamicCode());// 动态编号
		rd.setDynamicClass(RelationConstants.RELATION_DYNAMIC_HONOR);// 授予荣誉
		rd.setSubjectCode(honorAwardRecord.getHonorAwardRecordCode());//获奖记录Code
		rd.setOperateOrgCode(operateOrgCode); //颁发领导的orgCode
		rd.setSubjectUrlHonor(honorAwardRecord.getHonorUrl());  //荣誉Url
		rd.setSubjectCode(honorAwardRecord.getHonorAwardRecordCode()); //荣誉授予code
		rd.setSubjectName(honorAwardRecord.getHonorName());  //荣誉Name
		
		rd.setDynamicIsDelete(RelationConstants.RELATION_NO);// 是否删除0.否；1.是
		rd.setDynamicIsShield(RelationConstants.RELATION_NO);// 是否屏蔽0.否；1.是
		rd.setIsSubjectShow(RelationConstants.RELATION_NO);// 是否显示内容
		rd.setIsSubjectDataShow(RelationConstants.RELATION_NO);// 是否显示内容数据   
		rd.setUserCode(honorAwardRecord.getHonoreeCode());// 所属人编号
		
		// 组织动态班组关系表
		RelationDynamicTeamRel rdt = null;
		if (HonorConstants.HONOREE_TYPE_1.equals(honorAwardRecord.getHonoreeType())) {
			rd.setDynamicDepend(RelationConstants.RELATION_SUBJECT_DEPEND_USER);// 从属关系1-个人
		}
		if (HonorConstants.HONOREE_TYPE_2.equals(honorAwardRecord.getHonoreeType())) {
			rd.setDynamicDepend(RelationConstants.RELATION_SUBJECT_DEPEND_TEAM);// 从属关系2-班组
			rd.setTeamCode(honorAwardRecord.getHonoreeCode());// 所属班组编号
			
			rdt = new RelationDynamicTeamRel();
			rdt.setRelCode(UUIDHelper.getUUID());
			rdt.setTenantCode(honorAwardRecord.getTenantCode());
			rdt.setOrgCode(honorAwardRecord.getOrgCode());
			rdt.setCreateTime(curTime);
			rdt.setModifyTime(curTime);
			rdt.setDynamicCode(honorAwardRecord.getDynamicCode());
			rdt.setRelIsHomeShow(RelationConstants.RELATION_NO);// 是否首页展示
			rdt.setRelIsQuality(RelationConstants.RELATION_NO);// 是否精华
			rdt.setRelIsRecommend(RelationConstants.RELATION_NO);// 是否推荐
			rdt.setTeamCode(honorAwardRecord.getHonoreeCode());// 班组编号
			rdt.setTeamIsDissolve(isDissolve);// 是否解散
			
			boolean isExcelent = teamElectService.isExcelent(honorAwardRecord.getHonoreeCode());
			if (isExcelent) {
				rdt.setTeamIsExcellent(RelationConstants.RELATION_YES);// 班组是否百强班组
			} else {
				rdt.setTeamIsExcellent(RelationConstants.RELATION_NO);
			}
		}
		relationThirdDynamicService.saveDynamic(rd, rdt, null, null);
	}
	
	
	


}
