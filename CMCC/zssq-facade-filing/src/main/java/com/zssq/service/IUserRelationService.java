package com.zssq.service;

import java.util.List;

import com.zssq.dao.pojo.MyCount;
import com.zssq.dao.pojo.MyGroup;
import com.zssq.dao.pojo.UserState;
import com.zssq.exceptions.BusinessException;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;

public interface IUserRelationService {

	/**
	 * @Function addUSerConcerns
	 * @Description 添加关注
	 * @param userCode 操作人
	 * @param isConcernsUserCode 被关注人
	 * @return
	 */
	public boolean addUserConcerns(String userCode,String isConcernsUserCode) throws BusinessException;
	
	
	/**
	 * @Function delUserConcerns
	 * @Description 移除关注/粉丝
	 * @param concernsUserCode 粉丝
	 * @param isConcernsUserCode 大V
	 * @return
	 * @throws BusinessException
	 */
	public boolean delUserConcerns(String concernsUserCode,String isConcernsUserCode) throws BusinessException;
	
	/**
	 * @Function selectConcerns
	 * @Description 我的关注
	 * @param userCode
	 * @return
	 * @throws BusinessException
	 */
	public PageBean selectConcerns(PageParam pageParam,String userCode) throws BusinessException;
	
	
	/**
	 * @Function selectConcerns
	 * @Description 查询我的关注,返回关注人code集合
	 * @param userCode
	 * @return
	 * @throws BusinessException
	 */
	public List<String> selectConcerns(String userCode) throws BusinessException;
	
	/**
	 * @Function selectFans
	 * @Description 我的粉丝
	 * @param userCode
	 * @return
	 * @throws BusinessException
	 */
	public PageBean selectFans(PageParam pageParam,String userCode) throws BusinessException;
	
	/**
	 * @Function selectFriends
	 * @Description 我的好友
	 * @param userCode
	 * @return
	 * @throws BusinessException
	 */
	public PageBean selectFriends(PageParam pageParam,String userCode,String groupCode) throws BusinessException;
	
	
	/**
	 * @Function selectFriends
	 * @Description 我的好友,code集合
	 * @param userCode
	 * @return
	 * @throws BusinessException
	 */
	public List<String> selectFriends(String userCode) throws BusinessException;
	
	
	/**
	 * @Function isFriends
	 * @Description 是否是我的好友
	 * @param userCode
	 * @param otherCode
	 * @return
	 * @throws BusinessException
	 */
	public boolean isFriends(String userCode,String otherCode) throws BusinessException;
	
	/**
	 * @Function addBlack
	 * @Description 拉黑
	 * @param userCode
	 * @param blackUser
	 * @return
	 * @throws BusinessException
	 */
	public boolean addBlack(String userCode,String blackUser) throws BusinessException;
	
	/**
	 * @Function delBlack
	 * @Description 移除黑名单
	 * @param userCode
	 * @param blackUser
	 * @return
	 * @throws BusinessException
	 */
	public boolean delBlack(String userCode,String blackUser) throws BusinessException;
	
	/**
	 * @Function selectBlacks
	 * @Description 黑名单列表
	 * @param pageParam
	 * @param userCode
	 * @return
	 * @throws BusinessException
	 */
	public PageBean selectBlacks(PageParam pageParam,String userCode) throws BusinessException;


	/**
	 * @Function selectUserState
	 * @Description 返回两个用户间的好友/关注/拉黑关系 
	 * @param userCode
	 * @param otherCode
	 * @return
	 * @throws BusinessException
	 */
	public UserState selectUserState(String userCode,String otherCode) throws BusinessException;
	
	/**
	 * @Function addUserGroup
	 * @Description 新建好友分组
	 * @param userCode
	 * @param groupName
	 * @return
	 * @throws BusinessException
	 */
	public boolean addGroup(String userCode,String groupName) throws BusinessException;
	
	
	/**
	 * @Function delGroup
	 * @Description 删除分组
	 * @param userCode
	 * @param groupCode
	 * @return
	 * @throws BusinessException
	 */
	public boolean delGroup(String groupCode) throws BusinessException;


	/**
	 * @Function changeGroup
	 * @Description 修改分组
	 * @param userCode
	 * @param groupCode
	 * @return
	 * @throws BusinessException
	 */
	public boolean changeUserGroup(String userCode,String friendCode,String groupCode) throws BusinessException;


	/**
	 * @Function selectMyGroup
	 * @Description 查询我的分组
	 * @return
	 * @throws BusinessException
	 */
	public List<MyGroup> selectMyGroup(String userCode) throws BusinessException;
	
	
	/**
	 * @Function selectMyCount
	 * @Description 我的数量  好友/粉丝/关注
	 * @param userCode
	 * @return
	 * @throws BusinessException
	 */
	public MyCount selectMyCount(String userCode) throws BusinessException;
	
	
	/**
	 * 
	 * @Function shieldOn
	 * @Description 开启屏蔽私信
	 * @param userCode
	 * @return
	 * @throws BusinessException
	 */
	public boolean shieldOn(String userCode) throws BusinessException;
	
	
	/**
	 * 
	 * @Function shieldOff
	 * @Description 关闭私信屏蔽
	 * @param userCode
	 * @return
	 * @throws BusinessException
	 */
	public boolean shieldOff(String userCode) throws BusinessException;
	
	
	/**
	 * 
	 * @Function checkShield
	 * @Description 查看用户是否开启了屏蔽私信
	 * @param userCode
	 * @return
	 * @throws BusinessException
	 */
	public boolean checkShield(String userCode) throws BusinessException;
	
	
	/**
	 * 
	 * @Function selectConcernsAndFriends
	 * @Description 获取关注和好友的user_code集合
	 * @param userCode
	 * @return
	 * @throws BusinessException
	 */
	public List<String> selectConcernsAndFriends(String userCode) throws BusinessException;

}
