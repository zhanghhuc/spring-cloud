package com.zssq.auth.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zssq.datasource.DataSourceConstants;
import com.zssq.datasource.DynamicDataSourceHolder;

@Service("userTransferService")
public class UserTransferServiceImpl implements UserTransferServiceI {
	
	/** 日志记录器 */
	private Log log = LogFactory.getLog(UserTransferServiceImpl.class);	

	/** 数据持久化操作模板 */
	@Resource
	private JdbcTemplate jdbcTemplate;		
	
	@Override
	public void executeTransferUser() {
		
		String sql_1 = "select count(0) from kc_user";
		
		StringBuffer sqlBuff_2 = new StringBuffer("select t.* from ");
		sqlBuff_2.append("(select row_number() over() as num, kc_user.* from kc_user) t ");
		sqlBuff_2.append("where t.num between ? and ?");
		
		StringBuffer sqlBuff_3 = new StringBuffer("insert into transfer_user_info ");
		sqlBuff_3.append("(user_code, user_name, user_sex, user_office_phone, org_code, user_status, saas_tenant_code, user_id) values ");
		sqlBuff_3.append("(?, ?, ?, ?, ?, ?, ?, ?)");	
		
		String sql_4 = "select * from transfer_org_info where src_code = ?";
				
		// 每次处理记录数
		Integer executeSize = 1000;
		// 记录起始行号
		Integer startRowNum = 0;
		// 记录结束行号
		Integer endRowNum = executeSize;
		
		// 获取源表记录数
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.DB2_ACTIVITY);
		Integer dataSize = jdbcTemplate.queryForObject(sql_1, Integer.class);		
		
		log.info("员工数据迁移，数据总量：" + dataSize);
		
//		Integer dataSize = 200;
		
		while(startRowNum <= dataSize) {
			
			log.info("员工数据迁移，处理行号：开始行号=" + startRowNum + ", 结束行号：" + endRowNum + "  【开始】");
			
			DynamicDataSourceHolder.setDataSource(DataSourceConstants.DB2_ACTIVITY);
			List<Map<String, Object>> kcUsers = jdbcTemplate.queryForList(sqlBuff_2.toString(), startRowNum, endRowNum); 
			
			DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_AUTH);
			for(Map<String, Object> kcUser : kcUsers) {
				try {
					String userCode = kcUser.get("USER_CODE").toString();
					String userName = kcUser.get("USER_NAME").toString();
					Byte userSex = Byte.valueOf(kcUser.get("SEX").toString());
					String phone = kcUser.get("OFFICE_PHONE").toString();
					
					// 根据原始组织编码获取清洗后的组织编码
					String srcOrgCode = kcUser.get("ORG_CODE").toString();
					Map<String, Object> sysOrg = null;
					List<Map<String, Object>> sysOrgs = jdbcTemplate.queryForList(sql_4, srcOrgCode);
					if(CollectionUtils.isNotEmpty(sysOrgs)) {
						sysOrg = sysOrgs.get(0);
					}				
					String sysOrgCode = sysOrg == null ? "" : sysOrg.get("sys_org_code").toString();
									
					Byte userStatus = Byte.valueOf(kcUser.get("STATUS").toString());
					String saasTenantCode = "856966d06b96418fab2e430d71a1e84e";
					String userId = kcUser.get("USER_ID").toString();
					
					jdbcTemplate.update(sqlBuff_3.toString(), userCode, userName, userSex, phone, sysOrgCode, userStatus, saasTenantCode, userId);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			log.info("员工数据迁移，处理行号：开始行号=" + startRowNum + ", 结束行号：" + endRowNum + "  【结束】");
						
			// 执行一次批量迁移后，更新记录的起止行号
			startRowNum += executeSize + 1;
			endRowNum = startRowNum + executeSize;		
		}
	}

}
