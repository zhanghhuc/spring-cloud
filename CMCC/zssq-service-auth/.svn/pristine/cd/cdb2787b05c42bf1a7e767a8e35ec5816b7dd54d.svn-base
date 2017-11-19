package com.zssq.dao.mapper;

import java.util.List;

import com.mdcl.smap.bean.UserInfo;

public interface SmapUserMapper {
    
	/**
	 * 新增用户信息
	 * 
	 * @param record
	 * 			SMAP 同步到的用户信息
	 * @return 成功时返回新增条数，失败时返回0
	 */
    int insert(UserInfo record);
    
	/**
	 * 新增 SMAP 同步用户信息，保存至静态表，该表记录同步到的每一条组织信息，不做任何处理
	 * 
	 * @param orgInfo
	 * 			SMAP 同步到的用户信息
	 * @return 成功时返回新增条数，失败时返回0
	 */    
    int insertStatic(UserInfo record);

    /**
     * 根据uid获取用户详细信息
     * 
     * @param uid
     * 			用户uid
     * @return 用户详细信息
     */
    UserInfo selectByUid(String uid);
    
    /**
     * 获取指定组织下的员工信息
     * 
     * @param orgCode
     * 			组织编码
     * @return 员工详细信息集合
     */
    List<UserInfo> selectByOrgCode(String orgCode);

    int updateByPrimaryKey(UserInfo record);
    
    
    
    
    
    
    
    
    
    /* ========================= 以下代码为测试代码 =========================*/
    List<UserInfo> selectStaticAll();
}