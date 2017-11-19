package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.dao.pojo.SysUserRole;
import com.zssq.dao.pojo.UserInfoByName;

public interface SysUserInfoMapper {

	/**
	 * 根据组织编码查询用户编码集合
	 * 
	 * @param orgCode
	 * 			组织编码
	 * @return 该组织内的用户编码集合
	 */
	List<String> selectUserCodesByOrgCode(String orgCode);
	
	/**
	 * 批量插入用户信息
	 * 
	 * @param sysUserInfos
	 *            用户详细信息集合
	 * @return 成功时返回插入成功记录数，失败时返回0
	 */
	int insertValues(List<SysUserInfo> sysUserInfos);

	int insert(SysUserInfo record);

	int insertSelective(SysUserInfo record);

	SysUserInfo selectByCode(String userCode);
	
	/**
	 * 根据组织编码删除用户
	 * 
	 * @param orgCode
	 * 			组织编码
	 * @return 成功时返回删除记录数，失败时返回0
	 */
	int deleteByOrgCode(String orgCode);

	// 根据用户codes删除角色
	int deleteRoleByCodes(List<String> userCodes);

	// 批量插入用户角色
	int batchInsertRoles(List<SysUserRole> userRoles);

	List<SysUserInfo> selectByRecord(SysUserInfo record);

	/** 员工管理页面查询(数据) */
	List<SysUserInfo> selectPage(SysUserInfo record);

	/** 员工管理页面查询(总数) */
	int selectPageCount(SysUserInfo record);

	/** 设置领导/普通员工 */
	int setUserStatus(Map<String, Object> map);

	/** 获取代发领导/员工 */
	List<SysUserInfo> selectDeputyUser(Map<String, Object> map);

	/** 通过组织编号获取员工 */
	List<SysUserInfo> getUserListByOrgCode(Map<String, Object> map);

	/** 通过行政组织编码与菜单权限查询员工 */
	List<SysUserInfo> selectUserByMenuOrg(Map<String, Object> map);

	/** 通过行政组织编码查询员工(待分页) */
	List<SysUserInfo> selectUserByManOrg(SysUserInfo record);

	/** 通过行政组织编码查询员工 */
	int selectUserByManOrgCount(SysUserInfo record);
	
	/** 根据code查询用户名 */
	List<String> selectCodeByName(String userName);
	
	/** 根据userName查询用户信息 	 */
	List<UserInfoByName> selectInfoByName(Map<String, Object> map);
	
	/** 根据userName查询用户(数量) 	 */
	int selectCountByName(String userName);
	
	/** 根据user_code更新记录      */
	int updateByUserCode(SysUserInfo sysUserInfo);

}