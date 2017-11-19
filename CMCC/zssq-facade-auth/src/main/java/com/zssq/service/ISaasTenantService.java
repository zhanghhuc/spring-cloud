package com.zssq.service;

import java.util.List;

import com.zssq.dao.pojo.SaasTenantInfo;
import com.zssq.dao.pojo.SysAdminInfo;
import com.zssq.exceptions.BusinessException;

/**
 * 提供Saas用户登录以及对租户操作的接口
 * 
 * @since  JDK 1.7
 * @author 赵翊
 */
public interface ISaasTenantService {

	/**
	 * Saas管理员登录验证
	 * 
	 * @param adminAccount
	 * 			管理员帐号
	 * @param adminPass
	 * 			管理员密码
	 * @param adminType
	 * 			账号类型，1：saas管理员；2：超级管理员
	 * @return 管理员详细信息，验证失败时返回null
	 * @throws BusinessException
	 */
	public SysAdminInfo saasLogin(String adminAccount, String adminPass, String adminType) throws BusinessException;
	
	/**
	 * Saas管理员退出登录
	 * 
	 * @param adminAccount
	 * 			管理员帐号
	 * @throws BusinessException
	 */
	public Long saasLogout(String adminAccount) throws BusinessException;
	
	/**
	 * @Function addTenant
	 * @Description TODO
	 * @param name 租户全称
	 * @param acount 超级管理员账号
	 * @param pass 密码
	 * @return
	 * @throws BusinessException
	 */
	public Boolean addTenant(String name,String acount,String pass) throws BusinessException;
	
	
	
	/**
	 * @Function selectTenant
	 * @Description 获取租户集合
	 * @return
	 * @throws BusinessException
	 */
	public List<SaasTenantInfo> selectTenant() throws BusinessException;
	
	
	/**
	 * @Function setTenantState
	 * @Description 设置租户启/禁用
	 * @param tenants 租户编码(集合)
	 * @param isEnable 是否可用
	 * @return
	 * @throws BusinessException
	 */
	public boolean setTenantState(String tenants,String isEnable) throws BusinessException;
	
	
	/**
	 * @Function updatePassword
	 * @Description 重置租户密码
	 * @param account 租户账号
	 * @param pass 新密码
	 * @return
	 * @throws BusinessException
	 */
	public boolean updatePassword(String account,String pass) throws BusinessException;
	
	/**
	 * 获取Saas用户登录token
	 * 
	 * @param saasAccount
	 * 			Saas用户帐号
	 * @return Saas用户token
	 * @throws BusinessException
	 */
	public String selectSaasToken(String saasAccount) throws BusinessException;
	
	/**
	 * 根据指定key从缓存服务器中获取值
	 * 
	 * @param key
	 * 			缓存服务器中的唯一key
	 * @return 指定key对应的缓存数据
	 * @throws BusinessException
	 */
	public Object selectByRedisKey(String key) throws BusinessException;
	
	
	/**
	 * @Function updateSaasPass
	 * @Description SaaS修改密码
	 * @param saasAccount SaaS账号
	 * @param oldPass 新密码
	 * @param newPass 新密码
	 * @return
	 * @throws BusinessException
	 */
	public boolean updateSaasPass(String saasAccount,String oldPass,String newPass) throws BusinessException;
	
}
