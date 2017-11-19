package com.zssq.job.task;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.AbstractOneOffElasticJob;
import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.mdcl.smap.bean.UserInfo;
import com.zssq.constants.AuthConstants;
import com.zssq.constants.CreditConstants;
import com.zssq.dao.pojo.CoinAccount;
import com.zssq.dao.pojo.ExpAccount;
import com.zssq.dao.pojo.IntegralAccount;
import com.zssq.dao.pojo.SysOrgInfo;
import com.zssq.service.ICoinAccountService;
import com.zssq.service.IExpAccountService;
import com.zssq.service.IIntegralAccountService;
import com.zssq.service.ISmapService;
import com.zssq.service.ISysOrgService;
import com.zssq.util.SpringContextUtil;
import com.zssq.utils.DateUtils;

/**
 * 
 * @ClassName SmapSyncUsersTaskJob
 * @Description SMAP同步员工定时任务
 * @author lijie
 * @date 2017年6月6日 下午3:21:13
 * @version 1.0
 * @since JDK 1.7
 */
@Component
public class SmapSyncUsersTaskJob extends AbstractOneOffElasticJob {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	private ISmapService smapService = SpringContextUtil.getBean(ISmapService.class);
	
	private ISysOrgService sysOrgService = SpringContextUtil.getBean(ISysOrgService.class);
	
	private IIntegralAccountService integralAccountService = SpringContextUtil.getBean(IIntegralAccountService.class);
	
	private ICoinAccountService coinAccountService = SpringContextUtil.getBean(ICoinAccountService.class);
	
	private IExpAccountService expAccountService = SpringContextUtil.getBean(IExpAccountService.class);
	

	@Override
	protected void process(JobExecutionMultipleShardingContext context){
		
		log.info("----------SmapSyncUsersTaskJob.process：SMAP同步员工定时任务开始----------");
		try {
			//调用同步接口同步数据，返回新增的用户，为其创建金币、积分、经验值账户
			List<UserInfo> userInfoList = smapService.syncUsers();
			if(null != userInfoList && userInfoList.size() != 0){
				for (UserInfo userInfo : userInfoList) {
					//查询该用户的组织机构
					SysOrgInfo sysOrgInfo = sysOrgService.selectBySrcCode(userInfo.getO());
					
					//为新用户创建积分账户
					IntegralAccount integralAccount = new IntegralAccount();
					integralAccount.setAccountCode(userInfo.getUid());
					integralAccount.setAccountType(CreditConstants.TYPE_INDIVIDUAL);
					integralAccount.setOrgCode(sysOrgInfo.getManOrgCode());//所属行政组织
					integralAccount.setIntegralBalance(Integer.valueOf(0));
					integralAccount.setCreateTime(DateUtils.nowTimeMillis());
					integralAccount.setModifyTime(DateUtils.nowTimeMillis());
					integralAccount.setSaasTenantCode(AuthConstants.TENANT_CODE);        		
					integralAccountService.insert(integralAccount);
					
					//为新用户创建金币账户
					CoinAccount coinAccount = new CoinAccount();
					coinAccount.setAccountCode(userInfo.getUid());
					coinAccount.setAccountType(CreditConstants.TYPE_INDIVIDUAL);
					coinAccount.setOrgCode(sysOrgInfo.getManOrgCode());//所属行政组织
					coinAccount.setCoinBalance(Integer.valueOf(0));
					coinAccount.setCreateTime(DateUtils.nowTimeMillis());
					coinAccount.setModifyTime(DateUtils.nowTimeMillis());
					coinAccount.setSaasTenantCode(AuthConstants.TENANT_CODE);           		        		
					coinAccountService.insert(coinAccount);
					
					//为新用户创建经验值账户
					ExpAccount expAccount = new ExpAccount();
					expAccount.setAccountCode(userInfo.getUid());
					expAccount.setCurrentExp(Integer.valueOf(0));
					expAccount.setCurrentLevel(Integer.valueOf(1));
					expAccount.setCreateTime(DateUtils.nowTimeMillis());
					expAccount.setModifyTime(DateUtils.nowTimeMillis());
					expAccount.setOrgCode(sysOrgInfo.getManOrgCode());//所属行政组织        		
					expAccount.setSaasTenantCode(AuthConstants.TENANT_CODE);        		
					expAccountService.insert(expAccount); 				
				}
			}
			
			log.info("----------SmapSyncUsersTaskJob.process：SMAP同步员工定时任务结束----------");
		} catch (Exception e) {
			log.error("----------SmapSyncUsersTaskJob.process：SMAP同步员工定时任务异常----------", e);
		}
	}

}
