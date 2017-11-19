package com.zssq.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.zssq.dao.pojo.SysMenuInfo;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;

/**
 * 员工信息业务操作接口
 * 
 * @since  JDK 1.7
 * @author 赵翊
 */
public interface ISysUserService {

	/**
	 * 根据指定的员工编号，获取其详细信息；优先从缓存服务中获取。
	 * 
	 * @param userCode
	 * 			员工编号，必须
	 * @return 员工详细信息
	 * @throws BusinessException
	 */
	public SysUserInfo selectByCode(String userCode) throws BusinessException;
	
	/**
	 * 根据指定的员工编号（多个，以英文半角逗号分割，结尾不能包含分隔符），获取其详细信息
	 * 
	 * @param userCodes
	 * 			员工编号，必须
	 * @return 员工详细信息
	 * @throws BusinessException
	 */
	public List<SysUserInfo> selectByCodes(List<String> userCodes) throws BusinessException;
	
	/**
	 * 根据指定的员工编号（多个，以英文半角逗号分割，结尾不能包含分隔符），获取其详细信息
	 * 
	 * @param userCodes
	 * 			员工编号，必须
	 * @return key,员工编号；value,员工详细信息
	 * @throws BusinessException
	 */
	public Map<String, Object> selectMapByCodes(List<String> userCodes) throws BusinessException;
	
	/**
	 * 根据指定的员工编号（多个，以英文半角逗号分割，结尾不能包含分隔符），获取其详细信息
	 * 
	 * @param userCodes
	 * 			员工编号，必须
	 * @return key,员工编号；value,员工详细信息
	 * @throws BusinessException
	 */
	public Map<String, Object> selectMapByCodes(Set<String> userCodes) throws BusinessException;
	
	/**
	 * 刷新指定用户的缓存
	 * 
	 * @param userCode 用户Code
	 * @throws BusinessException
	 */
	public void refreshUserCache(String userCode) throws BusinessException;
	
	/**
	 * 获取菜单信息，适用于员工授权操作，列举系统中所有的菜单信息，并标识出已授权给指定用户的菜单
	 * 
	 * @param userCode
	 * 			用户编号
	 * @return 系统菜单信息
	 * @throws BusinessException
	 */
	public List<SysMenuInfo> selectMenus(String userCode) throws BusinessException;
	
	/**
	 * 获取菜单信息，列举对指定用户已授权的菜单信息
	 * 
	 * @param userCode
	 * 			用户编号
	 * @return 指定用户已授权的菜单集合
	 * @throws BusinessException
	 */
	public List<SysMenuInfo> selectUserMenus(String userCode) throws BusinessException;
	
	/**
	 * 设置用户与菜单的关联关系
	 * 
	 * @param userCode
	 * 			用户编号
	 * @param menuCodes
	 * 			菜单编号，多个时以英文逗号分割
	 * @return 操作成功时返回新增成功的记录数，失败时返回0
	 * @throws BusinessException
	 */
	public int addUserMenuRelation(String userCode, String menuCodes) throws BusinessException;
		
	/**
	 * @Function selectByRecord
	 * @Description 查询员工管理页面数据(员工)
	 * @param pageParam
	 * @param record
	 * @return
	 */
	public PageBean selectUserManagePage(PageParam pageParam,SysUserInfo record) throws BusinessException;
	
	
	/**
	 * @Function selectUserByManOrg
	 * @Description 通过行政组织编码与菜单权限查询员工
	 * @param manOrgCode
	 * @return
	 * @throws BusinessException
	 */
	public List<SysUserInfo> selectUserByMenuOrg(String manOrgCode,String menuCode) throws BusinessException;
	
	
	/**
	 * @Function selectUserByMenuOrgs
	 * @Description 通过行政组织编码与菜单权限查询员工
	 * @param manOrgCode
	 * @param menuCode
	 * @return
	 * @throws BusinessException
	 */
	public List<SysUserInfo> selectUserByMenuOrgs(List<String> manOrgCodes,String menuCode) throws BusinessException;
	
	
	/**
	 * @Function selectAdminManagePage
	 * @Description 查询员工管理页面数据(超级管理员)
	 * @param pageParam
	 * @param record
	 * @return
	 * @throws BusinessException
	 */
	public PageBean selectAdminManagePage(PageParam pageParam,SysUserInfo record) throws BusinessException;
	
	
	/**
	 * @Function setIsLeader
	 * @Description 设置是否是领导
	 * @param userCodes
	 * @param isLeader 1:YES
	 * @return
	 * @throws BusinessException
	 */
	public boolean setIsLeader(String userCodes,String isLeader) throws BusinessException;
	
	
	
	/**
	 * @Function setUserStatus
	 * @Description 员工启禁用
	 * @param userCodes
	 * @param userStatus
	 * @return
	 * @throws BusinessException
	 */
	public boolean setUserStatus(String userCodes,Byte userStatus) throws BusinessException;
	
	
	/**
	 * @Function selectDeputyUser
	 * @Description 获取代发领导/员工
	 * @param orgCode
	 * @param isLeader
	 * @return
	 * @throws BusinessException
	 */
	public List<SysUserInfo> selectDeputyUser(String orgCode,String isLeader,String userName) throws BusinessException;
	
	
	/**
	 * @Function selectByRecord
	 * @Description 根据条件查询用户集合,目前
	 * @param record
	 * @return
	 * @throws BusinessException
	 */
	public List<SysUserInfo> selectByRecord(SysUserInfo record) throws BusinessException;
	
	
	/**
	 * @Function updateHead
	 * @Description 更新头像
	 * @param userCode
	 * @param head
	 * @return
	 * @throws BusinessException
	 */
	public boolean updateHead(String userCode,String head) throws BusinessException;
	
	
	/**
	 * @Function updateIntro
	 * @Description 更新简介
	 * @param userCode
	 * @param intro
	 * @return
	 * @throws BusinessException
	 */
	public boolean updateIntro(String userCode,String intro) throws BusinessException;
	
	/**
	 * 查询指定组织下的员工信息
	 * 
	 * @param orgCode
	 * 			组织编码
	 * @return 该组织下的员工信息集合
	 * @throws BusinessException
	 */
	public List<SysUserInfo> getUserListByOrgCode(String orgCode)throws BusinessException;
	
	/**
	 * 根据用户名称，模糊匹配出相关的用户编码信息，适用于界面中的按用户名称模糊查询需求
	 * 
	 * @param userName
	 * 			用户名称
	 * @return 用户编码集合
	 * @throws BusinessException
	 */
	public List<String> selectCodeByName(String userName) throws BusinessException;
	/**
	 * 
	 * @Function 
	 * @Description 根据用户名称模糊查询用户
	 * @param userName
	 * @return
	 * @throws BusinessException
	 */
	public PageBean selectUserByUserName(String userName,PageParam page) throws BusinessException;
	
}
