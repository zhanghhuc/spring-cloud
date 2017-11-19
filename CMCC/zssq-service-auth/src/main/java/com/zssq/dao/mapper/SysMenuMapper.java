package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.dao.pojo.SysMenuInfo;
import com.zssq.dao.pojo.SysUserMenu;

/**
 * 菜单信息持久化操作接口
 * 
 * @since  JDK 1.7
 * @author 赵翊
 */
public interface SysMenuMapper {

	/**
	 * 获取指定菜单可操作的接口列表
	 * 
	 * @param userCode
	 * 			用户Code
	 * @return 该用户可操作的接口映射列表
	 */
	public List<String> selectUserPermissions(String userCode);
	 
	/**
	 * 获取系统菜单信息
	 * 
	 * @return 系统菜单信息
	 */
	public List<SysMenuInfo> selectByParentCode(String parentCode);
	
	/**
	 * 获取菜单信息，判断当前菜单是否已授权给某个用户
	 * 
	 * @param searchParam
	 * 			封装查询条件：userCode、menuCode
	 * @return 系统菜单信息
	 */
	public List<SysMenuInfo> selectForAuth(Map<String, String> searchParam);
	
	/**
	 * 删除指定用户与菜单的关联关系
	 * 
	 * @param userCode
	 * 			用户编号
	 * @return
	 */
	public int deleteUserMenuRelation(String userCode);
	
	/**
	 * 设置用户与菜单的关联关系
	 * 
	 * @param userMenus
	 * 			用户与菜单关联关系实体
	 * @return
	 */
	public int insertUserMenuRelation(List<SysUserMenu> userMenus);
	
	
	/**
	 * @Function selectByUserParentCode
	 * @Description 用户一级菜单
	 * @param map
	 * @return
	 * @throws BusinessException
	 */
	public List<SysMenuInfo> selectByUserParentCode(Map<String,Object> map);
	
	
	/**
	 * @Function selectUserForAuth
	 * @Description 用户二级菜单
	 * @param map
	 * @return
	 */
	public List<SysMenuInfo> selectForUserAuth(Map<String,String> map);
	
	
}
