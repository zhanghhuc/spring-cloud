package com.zssq.auth.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.auth.dao.mapper.KcOrgMapper;
import com.zssq.auth.dao.mapper.SysOrgInfoMapper;
import com.zssq.auth.dao.mapper.TransferManOrgMapper;
import com.zssq.auth.pojo.KcOrg;
import com.zssq.auth.pojo.SysOrgInfo;
import com.zssq.auth.pojo.TransferManOrg;

@Service("orgTransferService")
public class OrgTransferServiceImpl implements OrgTransferServiceI {

	Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private KcOrgMapper kcOrgMapper;
	
	@Autowired
	private SysOrgInfoMapper sysOrgInfoMapper;
	
	@Autowired
	private TransferManOrgMapper transferManOrgMapper;
	
	@Override
	public void transferBase() {					
		
		// 每次处理记录数
		Integer executeSize = 300;
		// 记录起始行号
		Integer startRowNum = 0;
		
		// 获取源表记录数		
		Integer dataSize = kcOrgMapper.selectKcOrgCount();	
		
		while(startRowNum <= dataSize) {
			Map<String, Integer> param = new HashMap<String, Integer>();
			param.put("startRowNum", startRowNum);
			param.put("executeSize", executeSize);
			List<KcOrg> kcOrgs = kcOrgMapper.selectKcOrgBatch(param);			
			
			List<SysOrgInfo> sysOrgs = new ArrayList<SysOrgInfo>();
			for(KcOrg kcOrg : kcOrgs) {
				SysOrgInfo sysOrgInfo = new SysOrgInfo();
				sysOrgInfo.setSysOrgCode(StringUtils.remove(UUID.randomUUID().toString(), "-"));
				sysOrgInfo.setSrcCode(kcOrg.getOrgCode());
				sysOrgInfo.setSysOrgName(kcOrg.getOrgName());
				sysOrgInfo.setSysOrgFullname(kcOrg.getOrgFullName());
				sysOrgInfo.setSysOrgOrder(kcOrg.getOrgOrder() == null ? "" : String.valueOf(kcOrg.getOrgOrder()));
				
				Integer orgStatus = kcOrg.getOrgStatus();				
				if(orgStatus != null) {
					
					Integer isEnable = orgStatus;
					if(orgStatus == 0) {
						isEnable = 1;
					} else if(orgStatus == 1) {
						isEnable = 0;
					}
					
					sysOrgInfo.setIsEnable(Byte.valueOf(isEnable.toString()));
					sysOrgInfo.setOrgStatus(Byte.valueOf(orgStatus.toString()));
				}
				sysOrgInfo.setOrgid(kcOrg.getOrgId());
				sysOrgInfo.setSaasTenantCode("856966d06b96418fab2e430d71a1e84e");
				sysOrgInfo.setKcParentCode(kcOrg.getParentCode());
				
				sysOrgs.add(sysOrgInfo);
			}
			sysOrgInfoMapper.insert(sysOrgs);
			
			// 执行一次批量迁移后，更新记录的起始行号
			startRowNum += executeSize;		
		}		
	}

	@Override
	public void processPraentCodeAndOrgType() {
		
		// 每次处理记录数
		Integer executeSize = 100;
		// 记录起始行号
		Integer startRowNum = 0;
		
		// 获取源表记录数
		Integer dataSize = sysOrgInfoMapper.selectSysOrgCount();

		// 查询所有行政组织信息
		List<TransferManOrg> manOrgs = transferManOrgMapper.selectManOrgAll();
		List<String> manOrgCodes = new ArrayList<String>();
		Map<String, Integer> manOrgMap = new HashMap<String, Integer>();
		for(TransferManOrg manOrg : manOrgs) {
			manOrgCodes.add(manOrg.getOrgCode());
			manOrgMap.put(manOrg.getOrgCode(), manOrg.getOrgLevel());
		}
		
		while(startRowNum <= dataSize) {			
			try {
				// 批量获取待处理的数据，避免一次性加载到内存中的数据量过大
				Map<String, Integer> param = new HashMap<String, Integer>();
				param.put("startRowNum", startRowNum);
				param.put("executeSize", executeSize);
				List<SysOrgInfo> sysOrgs = sysOrgInfoMapper.selectSysOrgBatch(param); 
								
				for(SysOrgInfo sysOrg : sysOrgs) {
					SysOrgInfo updateParam = new SysOrgInfo();		
					updateParam.setSysOrgCode(sysOrg.getSysOrgCode());
					
					// 根据 kc_parent_code 字段值查询组织信息，查询到的记录即为当前组织的上级组织；查询不到时跳过
					List<SysOrgInfo> parentOrgs = sysOrgInfoMapper.selectBySrcCode(sysOrg.getKcParentCode());
					if(CollectionUtils.isNotEmpty(parentOrgs)) {
						SysOrgInfo parentOrg = parentOrgs.get(0);
						updateParam.setParentCode(parentOrg.getSysOrgCode());
					}
					
					// 判断当前组织是否为行政组织，是，则为其设置 sys_org_type 属性
					if(manOrgCodes.contains(sysOrg.getSrcCode()) && !StringUtils.equals("00030000000000000000", sysOrg.getSrcCode())) {
						// 判断组织级别，org_level 为1，设置为“B”，org_level 为2，设置为“C”
						String orgType = null;
						Integer manOrgLevel = manOrgMap.get(sysOrg.getSrcCode().toString());					
						if(manOrgLevel == 1) {
							orgType = "46dfa0a384d24ba9b3f6c5490d1bd1ce";
						} else if(manOrgLevel == 2) {
							orgType = "8fe5db88d10749e697919ba3f27fce26";
						}
						// 更新组织级别 sys_org_type
						updateParam.setSysOrgType(orgType);				
					}
					sysOrgInfoMapper.updateParentCodeAndLevel(updateParam);
				}
							
				// 执行一次批量迁移后，更新记录的起止行号
				startRowNum += executeSize;
			} catch (Exception e) {				
				e.printStackTrace();
			}			
		}
						
		List<SysOrgInfo> orgInfos = sysOrgInfoMapper.selectBySrcCode("00030000000000000000");
		if(CollectionUtils.isNotEmpty(orgInfos)) {
			SysOrgInfo jituanOrgInfo = orgInfos.get(0);
			
			SysOrgInfo updateParam = new SysOrgInfo();		
			updateParam.setSysOrgCode(jituanOrgInfo.getSysOrgCode());
			updateParam.setParentCode("0");
			updateParam.setSysOrgType("3b16c578b64149a2997edae4cc5eeb99");
			
			log.info("设置集团公司为 A 级行政区域，开始......");
			sysOrgInfoMapper.updateParentCodeAndLevel(updateParam);
			log.info("设置集团公司为 A 级行政区域，结束......");
			
			log.info("设置省公司的所属上级组织，开始......");
			sysOrgInfoMapper.updateProvinceParentCode(jituanOrgInfo.getSysOrgCode());
			log.info("设置省公司的所属上级组织，结束......");
		}		
	}

	@Override
	public void processManOrgCode() {		
		
		// 每次处理记录数
		Integer executeSize = 100;
		// 记录起始行号
		Integer startRowNum = 0;
		
		// 获取源表记录数
		Integer dataSize = sysOrgInfoMapper.selectSysOrgCount();
				
		while(startRowNum <= dataSize) {	
			try {
				// 批量获取待处理的数据，避免一次性加载到内存中的数据量过大
				Map<String, Integer> param = new HashMap<String, Integer>();
				param.put("startRowNum", startRowNum);
				param.put("executeSize", executeSize);
				List<SysOrgInfo> sysOrgs = sysOrgInfoMapper.selectSysOrgBatch(param);				
				for(SysOrgInfo sysOrg : sysOrgs) {					
					// 所属行政组织编码不为空
					if(sysOrg.getManOrgCode() == null) {
						// 查询组织所属的行政组织信息
						SysOrgInfo manOrg = searchManSysOrg(sysOrg.getSysOrgCode());
						if(manOrg != null) {
							SysOrgInfo updateParam = new SysOrgInfo();		
							updateParam.setSysOrgCode(sysOrg.getSysOrgCode());
							updateParam.setManOrgCode(manOrg.getSysOrgCode());
							sysOrgInfoMapper.updateManOrgCode(updateParam);
						}
					}
				}
				
				// 执行一次批量迁移后，更新记录的起止行号
				startRowNum += executeSize;
			} catch (Exception e) {				
				e.printStackTrace();
			}			
		}						
	}
	
	private SysOrgInfo searchManSysOrg(String sysOrgCode) {
		
		try {
			String orgCode = sysOrgCode;
			for(int i=0; i<15; i++) {
				SysOrgInfo sysOrg = sysOrgInfoMapper.selectByOrgCode(orgCode);
				if(sysOrg == null) {
					return null;
				} else if(sysOrg.getSysOrgType() != null) {
					return sysOrg;
				} else if(sysOrg.getParentCode() == null) {
					return null;
				} else {
					orgCode = sysOrg.getParentCode();
				}
			}
						
//			SysOrgInfo sysOrg = sysOrgInfoMapper.selectByOrgCode(sysOrgCode);
//			if(sysOrg == null) {
//				return null;
//			} else if(sysOrg.getSysOrgType() != null) {
//				return sysOrg;
//			} else if(sysOrg.getParentCode() == null) {
//				return null;
//			} else {
//				return searchManSysOrg(sysOrg.getParentCode());
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
}
