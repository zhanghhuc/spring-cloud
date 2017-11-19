package com.zssq.auth.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zssq.datasource.DataSource;
import com.zssq.datasource.DataSourceConstants;
import com.zssq.datasource.DynamicDataSourceHolder;

@Service("orgTransferService")
public class OrgTransferServiceImpl implements OrgTransferServiceI {

	/** 日志记录器 */
	private Log log = LogFactory.getLog(OrgTransferServiceImpl.class);
	
	/** 数据持久化操作模板 */
	@Resource
	private JdbcTemplate jdbcTemplate;	
	
	@Override
	public void executeTransferBase() {
		
		StringBuffer sqlBuff_1 = new StringBuffer("select count(0) from kc_org");
		
		StringBuffer sqlBuff_2 = new StringBuffer("select t.* from ");
		sqlBuff_2.append("(select row_number() over() as num, kc_org.* from kc_org) t ");
		sqlBuff_2.append("where t.num between ? and ?");
		
		StringBuffer sqlBuff_3 = new StringBuffer("insert into transfer_org_info ");
		sqlBuff_3.append("(sys_org_code, src_code, sys_org_name, sys_org_fullname, org_level, sys_org_order, is_enable, org_id, kc_parent_code) values ");
		sqlBuff_3.append("(?, ?, ?, ?, ?, ?, ?, ?, ?)");						
		
		// 每次处理记录数
		Integer executeSize = 1000;
		// 记录起始行号
		Integer startRowNum = 0;
		// 记录结束行号
		Integer endRowNum = executeSize;
		
		// 获取源表记录数		
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.DB2_ACTIVITY);
		Integer dataSize = jdbcTemplate.queryForObject(sqlBuff_1.toString(), Integer.class);		
//		Integer dataSize = 55;
		
		while(startRowNum <= dataSize) {
			DynamicDataSourceHolder.setDataSource(DataSourceConstants.DB2_ACTIVITY);
			List<Map<String, Object>> kcOrgs = jdbcTemplate.queryForList(sqlBuff_2.toString(), startRowNum, endRowNum);			
						
			DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_AUTH);
			for(Map<String, Object> kcOrg : kcOrgs) {
				String sysOrgCode = StringUtils.remove(UUID.randomUUID().toString(), "-");
				String srcCode = kcOrg.get("ORG_CODE").toString();
				String sysOrgName = kcOrg.get("ORG_NAME").toString();
				String sysOrgFullName = kcOrg.get("ORG_FULL_NAME").toString();
				String orgLevel = kcOrg.get("ORG_LEVEL").toString();
				String sysOrgOrder = kcOrg.get("ORG_ORDER").toString();
				
				String sourceStatus = kcOrg.get("ORG_STATUS").toString();
				Byte isEnable = Byte.valueOf(sourceStatus);
				
				Integer orgId = Integer.valueOf(kcOrg.get("ORG_ID").toString());
				String kcParentCode = kcOrg.get("PARENT_CODE").toString();	
				
				jdbcTemplate.update(sqlBuff_3.toString(), sysOrgCode, srcCode, sysOrgName, sysOrgFullName, orgLevel, sysOrgOrder, isEnable, orgId, kcParentCode);
			}
			
			// 执行一次批量迁移后，更新记录的起止行号
			startRowNum += executeSize + 1;
			endRowNum = startRowNum + executeSize;			
		}
	}
	
	@Override
	@DataSource(DataSourceConstants.MYSQL_AUTH)	
	public void processPraentCodeAndOrgType() {
		
		String sql_1 = "select * from transfer_man_org"; 				
		String sql_2 = "select * from transfer_org_info where src_code = ?";
		String sql_3 = "update transfer_org_info set parent_code = ? where sys_org_code = ?";
		String sql_4 = "update transfer_org_info set sys_org_type = ? where sys_org_code = ?";
		String sql_5 = "select * from transfer_org_info limit ?, ?";
		String sql_6 = "select count(0) from transfer_org_info";
		
		// 每次处理记录数
		Integer executeSize = 1000;
		// 记录起始行号
		Integer startRowNum = 0;
		// 记录结束行号
		Integer endRowNum = executeSize;
		
		// 获取源表记录数
		Integer dataSize = jdbcTemplate.queryForObject(sql_6, Integer.class);		
//		Integer dataSize = 55;

		// 查询所有行政组织信息
		List<Map<String, Object>> manOrgs = jdbcTemplate.queryForList(sql_1);
		List<String> manOrgCodes = new ArrayList<String>();
		Map<String, Integer> manOrgMap = new HashMap<String, Integer>();
		for(Map<String, Object> manOrg : manOrgs) {
			manOrgCodes.add(manOrg.get("ORG_CODE").toString());
			manOrgMap.put(manOrg.get("ORG_CODE").toString(), (Integer) manOrg.get("ORG_LEVEL"));
		}
		
		while(startRowNum <= dataSize) {			
			try {
				// 批量获取待处理的数据，避免一次性加载到内存中的数据量过大
				List<Map<String, Object>> sysOrgs = jdbcTemplate.queryForList(sql_5, startRowNum, endRowNum); 
								
				for(Map<String, Object> sysOrg : sysOrgs) {
					// 根据 kc_parent_code 字段值查询组织信息，查询到的记录即为当前组织的上级组织；查询不到时跳过
					List<Map<String, Object>> parentOrgs = jdbcTemplate.queryForList(sql_2, sysOrg.get("kc_parent_code"));
					if(CollectionUtils.isNotEmpty(parentOrgs)) {
						Map<String, Object> parentOrg = parentOrgs.get(0);
						jdbcTemplate.update(sql_3, parentOrg.get("sys_org_code"), sysOrg.get("sys_org_code"));
					}
					
					// 判断当前组织是否为行政组织，是，则为其设置 sys_org_type 属性
					if(manOrgCodes.contains(sysOrg.get("src_code")) && !StringUtils.equals("00030000000000000000", sysOrg.get("src_code").toString())) {
						// 判断组织级别，org_level 为1，设置为“B”，org_level 为2，设置为“C”
						String orrType = null;
						Integer manOrgLevel = manOrgMap.get(sysOrg.get("src_code").toString());					
						if(manOrgLevel == 1) {
							orrType = "46dfa0a384d24ba9b3f6c5490d1bd1ce";
						} else if(manOrgLevel == 2) {
							orrType = "8fe5db88d10749e697919ba3f27fce26";
						}
						// 更新组织级别 sys_org_type
						jdbcTemplate.update(sql_4, orrType, sysOrg.get("sys_org_code"));					
					}
				}
							
				// 执行一次批量迁移后，更新记录的起止行号
				startRowNum += executeSize + 1;
				endRowNum = startRowNum + executeSize;
			} catch (Exception e) {				
				e.printStackTrace();
			}			
		}				
	}
	
	@DataSource(DataSourceConstants.MYSQL_AUTH)	
	public void processManOrgCode() {
		
		String sql_1 = "select count(0) from transfer_org_info";
		String sql_2 = "update transfer_org_info set man_org_code = ? where sys_org_code = ?";	
		String sql_3 = "select * from transfer_org_info limit ?, ?";		
		
		// 每次处理记录数
		Integer executeSize = 1000;
		// 记录起始行号
		Integer startRowNum = 0;
		// 记录结束行号
		Integer endRowNum = executeSize;
		
		// 获取源表记录数
		Integer dataSize = jdbcTemplate.queryForObject(sql_1, Integer.class);		
//		Integer dataSize = 55;
				
		while(startRowNum <= dataSize) {	
			
			log.info("设置组织所属的行政组织编码，处理行号：开始行号=" + startRowNum + ", 结束行号：" + endRowNum + "  【开始】");
			
			try {
				// 批量获取待处理的数据，避免一次性加载到内存中的数据量过大
				List<Map<String, Object>> sysOrgs = jdbcTemplate.queryForList(sql_3, startRowNum, endRowNum);				
				for(Map<String, Object> sysOrg : sysOrgs) {
					
					// 所属行政组织编码不为空
					if(sysOrg.get("man_org_code") != null) {
						// 查询组织所属的行政组织信息
						Map<String, Object> manOrg = searchManSysOrg(sysOrg.get("sys_org_code").toString());
						if(manOrg != null) {
							jdbcTemplate.update(sql_2, manOrg.get("sys_org_code").toString(), sysOrg.get("sys_org_code").toString());
						}
					}
				}
				
				log.info("设置组织所属的行政组织编码，处理行号：开始行号=" + startRowNum + ", 结束行号：" + endRowNum + "  【结束】");
				
				// 执行一次批量迁移后，更新记录的起止行号
				startRowNum += executeSize + 1;
				endRowNum = startRowNum + executeSize;
			} catch (Exception e) {				
				e.printStackTrace();
			}			
		}				
	}	
	
	public Integer searchSysOrgCount() {
		
		try {
			StringBuffer sqlBuff = new StringBuffer("select count(0) from transfer_org_info");
			return jdbcTemplate.queryForObject(sqlBuff.toString(), Integer.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}	
	
	public List<Map<String, Object>> searchSysOrgPage(int startRowNum, int endRowNum) {

		try {
			StringBuffer sqlBuff = new StringBuffer("select * from transfer_org_info limit ?, ?");			
			return jdbcTemplate.queryForList(sqlBuff.toString(), startRowNum, endRowNum);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
	
	public Map<String, Object> searchManSysOrg(String sysOrgCode) {
		
		try {
			Map<String, Object> sysOrg = searchSysOrg(sysOrgCode);
			if(sysOrg == null) {
				return null;
			} else if(sysOrg.get("sys_org_type") != null) {
				return sysOrg;
			} else {
				return searchManSysOrg(sysOrg.get("parent_code") != null ? sysOrg.get("parent_code").toString() : "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Map<String, Object> searchSysOrg(String sysOrgCode) {
		
		try {
			String sql = "select * from transfer_org_info where sys_org_code = ?";
			List<Map<String, Object>> sysOrgs = jdbcTemplate.queryForList(sql, sysOrgCode);
			
			if(CollectionUtils.isNotEmpty(sysOrgs)) {
				return sysOrgs.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	@DataSource(DataSourceConstants.DB2_ACTIVITY)
	public Map<String, Object> searchKcOrgOne(int orgId) {
		
		try {
			StringBuffer sqlBuff = new StringBuffer("select * from kc_org where ORG_ID = ?");
			return jdbcTemplate.queryForMap(sqlBuff.toString(), orgId);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	@Override
	public Integer searchKcOrgCount() {
		
		try {
			StringBuffer sqlBuff = new StringBuffer("select count(0) from kc_org");
			DynamicDataSourceHolder.setDataSource(DataSourceConstants.DB2_ACTIVITY);
			return jdbcTemplate.queryForObject(sqlBuff.toString(), Integer.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@DataSource(DataSourceConstants.DB2_ACTIVITY)
	public List<Map<String, Object>> searchKcOrgPage(int startRowNum, int endRowNum) {

		try {
			StringBuffer sqlBuff = new StringBuffer("select t.* from ");
			sqlBuff.append("(select row_number() over() as num, kc_org.* from kc_org) t ");
			sqlBuff.append("where t.num between ? and ?");
			
			return jdbcTemplate.queryForList(sqlBuff.toString(), startRowNum, endRowNum);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
	
	/**
	 * 组织数据迁移，逐条从源表中获取数据插入
	 * 
	 * @param kcOrgs
	 */
//	private void insertSysOrg(List<Map<String, Object>> kcOrgs) {
//		
//		try {
//			StringBuffer sqlBuff = new StringBuffer("insert into transfer_org_info ");
//			sqlBuff.append("(sys_org_code, src_code, sys_org_name, sys_org_fullname, org_level, sys_org_order, is_enable, org_id, kc_parent_code) values ");
//			sqlBuff.append("(?, ?, ?, ?, ?, ?, ?, ?, ?)");
//												
//			jdbcTemplate.batchUpdate(sqlBuff.toString(), new SysOrgBatchInsertPrepared(kcOrgs)); 
//		} catch (Exception e) {
//			e.printStackTrace();
//		}		
//	}
}
