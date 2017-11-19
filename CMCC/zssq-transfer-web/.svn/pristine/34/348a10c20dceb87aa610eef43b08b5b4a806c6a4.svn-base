package com.zssq.auth.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.auth.dao.mapper.KcUserMapper;
import com.zssq.auth.dao.mapper.SysOrgInfoMapper;
import com.zssq.auth.dao.mapper.SysUserInfoMapper;
import com.zssq.auth.pojo.KcUser;
import com.zssq.auth.pojo.SysOrgInfo;
import com.zssq.auth.pojo.SysUserInfo;

@Service("userTransferService")
public class UserTransferServiceImpl implements UserTransferServiceI {

	@Autowired
	private KcUserMapper kcUserMapper;
	
	@Autowired
	private SysUserInfoMapper sysUserInfoMapper;
	
	@Autowired
	private SysOrgInfoMapper sysOrgInfoMapper;
	
	@Override
	public void executeTransfer() {
				
		// 每次处理记录数
		Integer executeSize = 300;
		// 记录起始行号
		Integer startRowNum = 0;
		
		// 获取源表记录数
		Integer dataSize = kcUserMapper.selectKcUserCount();
		
		while(startRowNum <= dataSize) {
			Map<String, Integer> param = new HashMap<String, Integer>();
			param.put("startRowNum", startRowNum);
			param.put("executeSize", executeSize);
			List<KcUser> kcUsers = kcUserMapper.selectKcUserBatch(param); 
			
			List<SysUserInfo> sysUsers = new ArrayList<SysUserInfo>();			
			for(KcUser kcUser : kcUsers) {
				if(kcUser.getUserCode().length() > 32){
					continue;
				}
				
				try {
					SysUserInfo sysUser = new SysUserInfo();
					
					sysUser.setUserCode(kcUser.getUserCode());
					sysUser.setUserName(kcUser.getUserName());
					sysUser.setUserSex(Byte.valueOf(kcUser.getSex().toString()));
					sysUser.setUserOfficePhone(kcUser.getOfficePhone());
					sysUser.setUserStatus(Byte.valueOf(kcUser.getStatus().toString()));
					sysUser.setUserid(kcUser.getUserId());
					sysUser.setTenantCode("856966d06b96418fab2e430d71a1e84e");

					List<SysOrgInfo> sysOrgs = sysOrgInfoMapper.selectBySrcCode(kcUser.getOrgCode());
					if(CollectionUtils.isNotEmpty(sysOrgs)) {
						sysUser.setOrgCode(sysOrgs.get(0).getSysOrgCode());
					}
					
					sysUsers.add(sysUser);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			sysUserInfoMapper.insertBatch(sysUsers);					
			// 执行一次批量迁移后，更新记录的起止行号
			startRowNum += executeSize;
		}		
	}

}
