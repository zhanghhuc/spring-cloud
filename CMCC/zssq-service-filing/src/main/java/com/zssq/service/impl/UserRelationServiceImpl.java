package com.zssq.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.constants.FilingConstants;
import com.zssq.dao.mapper.UserBlacklistMapper;
import com.zssq.dao.mapper.UserConcernsMapper;
import com.zssq.dao.mapper.UserFriendGroupMapper;
import com.zssq.dao.mapper.UserFriendMapper;
import com.zssq.dao.mapper.UserShieldRelationMapper;
import com.zssq.dao.pojo.MyCount;
import com.zssq.dao.pojo.MyGroup;
import com.zssq.dao.pojo.UserBlacklist;
import com.zssq.dao.pojo.UserConcerns;
import com.zssq.dao.pojo.UserFriend;
import com.zssq.dao.pojo.UserFriendGroup;
import com.zssq.dao.pojo.UserShieldRelation;
import com.zssq.dao.pojo.UserState;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.Message;
import com.zssq.service.IUserRelationService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.DateUtils;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.utils.PropertiesUtil;

@Service("userRelationService")
public class UserRelationServiceImpl implements IUserRelationService {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private UserConcernsMapper userConcernsMapper;
	
	@Autowired
	private UserBlacklistMapper userBlacklistMapper;
	
	@Autowired
	private UserFriendGroupMapper userFriendGroupMapper;
	
	@Autowired
	private UserFriendMapper userFriendMapper;
	
	@Autowired
	private UserShieldRelationMapper userShieldRelationMapper;
	
	
	/**
	 * 添加关注
	 * @see com.zssq.service.IUserConcernsService#addUSerConcerns(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean addUserConcerns(String userCode,String isConcernsUserCode) throws BusinessException {
		try {
			//查询 若是好友或者已经关注，直接return成功，防止前端重复调用接口造成数据库重复数据
			UserConcerns concernsRecord = new UserConcerns();
			concernsRecord.setConcernsUserCode(userCode);
			concernsRecord.setIsConcernsUserCode(isConcernsUserCode);
			List<UserConcerns> concernsList1 = userConcernsMapper.selectByRecord(concernsRecord);
			UserFriend friendRecord = new UserFriend();
			friendRecord.setUserCode(userCode);
			friendRecord.setFriendUserCode(isConcernsUserCode);
			List<UserFriend> userFriendList = userFriendMapper.selectMyFriends(friendRecord);
			if(concernsList1.size() > 0 || userFriendList.size() > 0){
				return true;
			}
			
			//查询黑名单,我在你的黑名单,我不能关注你
			UserBlacklist blackList = new UserBlacklist();
			blackList.setUserCode(isConcernsUserCode);
			blackList.setBlacklistUserCode(userCode);
			List<UserBlacklist> yourBlacklist = userBlacklistMapper.selectByRecord(blackList);
			if(yourBlacklist.size() > 0){//不能关注
				Message message = PropertiesUtil.getMessage(FilingConstants.RETURNCODE_FILING_11004);
				throw new BusinessException(message.getCode(), String.format(message.getTip(), "添加关注","对方已经拉黑你了..."));
			}
			//你在我的黑名单,移除黑名单
			blackList.setUserCode(userCode);
			blackList.setBlacklistUserCode(isConcernsUserCode);
			List<UserBlacklist> myBlacklist = userBlacklistMapper.selectByRecord(blackList);
			if(myBlacklist.size() > 0){//移除黑名单
				userBlacklistMapper.deleteBlack(blackList);
			}
			//查询是否存在粉丝关系
			UserConcerns concerns = new UserConcerns();
			concerns.setIsConcernsUserCode(userCode);
			concerns.setConcernsUserCode(isConcernsUserCode);
			List<UserConcerns> concernsList = userConcernsMapper.selectByRecord(concerns);
			if(concernsList.size() > 0){//成为好友
				//删除关注关系
				userConcernsMapper.delUSerConcerns(concerns);
				//存入好友表
				UserFriend friend = new UserFriend();
				friend.setCreateTime(DateUtils.getFormatDateLong());
				friend.setModifyTime(DateUtils.getFormatDateLong());
				
				friend.setUserFriendCode(UUIDHelper.getUUID());
				friend.setUserCode(userCode);
				friend.setFriendUserCode(isConcernsUserCode);
				friend.setFriendGroupCode(FilingConstants.DEFAULT_GROUP);
				userFriendMapper.insertSelective(friend);
				friend.setUserFriendCode(UUIDHelper.getUUID());
				friend.setUserCode(isConcernsUserCode);
				friend.setFriendUserCode(userCode);
				friend.setFriendGroupCode(FilingConstants.DEFAULT_GROUP);
				return userFriendMapper.insertSelective(friend) == 1;
			}else{//仅关注
				UserConcerns record = new UserConcerns();
				record.setConcernsCode(UUIDHelper.getUUID());
				record.setConcernsUserCode(userCode);
				record.setCreateTime(DateUtils.getFormatDateLong());
				record.setIsConcernsUserCode(isConcernsUserCode);
				record.setModifyTime(DateUtils.getFormatDateLong());
				return userConcernsMapper.insertSelective(record) == 1;
			}
		} catch (BusinessException e){
			throw e;
		}catch (Exception e) {
			log.error("添加关注异常", e);
			throw new BusinessException("添加关注异常", e);
		}
	}

	/**
	 * 移除关注
	 * @see com.zssq.service.IUserConcernsService#delUSerConcerns(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean delUserConcerns(String concernsUserCode, String isConcernsUserCode) throws BusinessException {
		try {
			//是否好友
			UserFriend record = new UserFriend();
			record.setUserCode(concernsUserCode);
			record.setFriendUserCode(isConcernsUserCode);
			if(userFriendMapper.selectMyFriends(record).size() > 0){//是好友
				//删好友
				userFriendMapper.delectFriend(record);
				//插入
				UserConcerns userConcerns = new UserConcerns();
				userConcerns.setConcernsCode(UUIDHelper.getUUID());
				userConcerns.setConcernsUserCode(isConcernsUserCode);
				userConcerns.setIsConcernsUserCode(concernsUserCode);
				userConcerns.setCreateTime(DateUtils.getFormatDateLong());
				userConcerns.setModifyTime(DateUtils.getFormatDateLong());
				return userConcernsMapper.insertSelective(userConcerns) == 1;
			}else{//不是好友
				//查是否是关注，防止前端重复调用接口造成错误
				UserConcerns userConcerns = new UserConcerns();
				userConcerns.setConcernsUserCode(concernsUserCode);
				userConcerns.setIsConcernsUserCode(isConcernsUserCode);
				List<UserConcerns> userConcernsList = userConcernsMapper.selectByRecord(userConcerns);
				if(userConcernsList.size() == 0){
					return true;
				}else{
					return userConcernsMapper.delUSerConcerns(userConcerns) == 1;
				}
				
			}
		} catch (Exception e) {
			log.error("移除关注/粉丝出错", e);
			throw new BusinessException("移除关注/粉丝出错", e);
		}
	}

	/**
	 * 我的关注
	 * @see com.zssq.service.IUserConcernsService#selectConcerns(java.lang.String)
	 */
	@Override
	public PageBean selectConcerns(PageParam pageParam,String userCode) throws BusinessException {
		try {
			UserConcerns record = new UserConcerns();
			if(pageParam != null){
				record.setLimitStart(pageParam.getPageNo() * pageParam.getPageSize());
				record.setLimitEnd(pageParam.getPageSize());
			}
			record.setConcernsUserCode(userCode);
			List<UserConcerns> dataList = userConcernsMapper.selectByRecord(record);
			int count = userConcernsMapper.selectCount(record);
			return new PageBean(record.getLimitStart(), record.getLimitEnd(), count, dataList);
		} catch (Exception e) {
			log.error("查询我的关注出错", e);
			throw new BusinessException("查询我的关注出错", e);
		}
	}

	/**
	 * 查询我的关注,返回关注人code集合
	 * @see com.zssq.service.IUserRelationService#selectConcerns(java.lang.String)
	 */
	@Override
	public List<String> selectConcerns(String userCode) throws BusinessException {
		try {
			List<String> dataList = userConcernsMapper.selectMyConcerns(userCode);
			return dataList;
		} catch (Exception e) {
			log.error("查询我的关注出错", e);
			throw new BusinessException("查询我的关注出错", e);
		}
	}

	/**
	 * 粉丝列表
	 * @see com.zssq.service.IUserConcernsService#selectFans(java.lang.String)
	 */
	@Override
	public PageBean selectFans(PageParam pageParam,String userCode) throws BusinessException {
		try {
			UserConcerns record = new UserConcerns();
			if(pageParam != null){
				record.setLimitStart(pageParam.getPageNo() * pageParam.getPageSize());
				record.setLimitEnd(pageParam.getPageSize());
			}
			record.setIsConcernsUserCode(userCode);
			List<UserConcerns> dataList = userConcernsMapper.selectByRecord(record);
			int count = userConcernsMapper.selectCount(record);
			return new PageBean(record.getLimitStart(), record.getLimitEnd(), count, dataList);
		} catch (Exception e) {
			log.error("查询粉丝列表出错", e);
			throw new BusinessException("查询粉丝列表出错", e);
		}
	}

	/**
	 * 好友列表
	 * @see com.zssq.service.IUserConcernsService#selectFriends(PageParam, java.lang.String)
	 */
	@Override
	public PageBean selectFriends(PageParam pageParam,String userCode,String groupCode) throws BusinessException {
		try {
			UserFriend record = new UserFriend();
			if(pageParam != null){
				record.setLimitStart(pageParam.getPageNo() * pageParam.getPageSize());
				record.setLimitEnd(pageParam.getPageSize());
			}
			record.setUserCode(userCode);
			record.setFriendGroupCode(groupCode);
			List<UserFriend> dataList = userFriendMapper.selectMyFriends(record);
			int count = userFriendMapper.selectMyFriendsCount(record);
			return new PageBean(record.getLimitStart(), record.getLimitEnd(), count, dataList);
		} catch (Exception e) {
			log.error("查询好友列表出错", e);
			throw new BusinessException("查询好友列表出错", e);
		}
	}

	/**
	 * 我的好友返回code集合
	 * @see com.zssq.service.IUserRelationService#selectFriends(java.lang.String)
	 */
	@Override
	public List<String> selectFriends(String userCode) throws BusinessException {
		try {
			return userFriendMapper.selectFriends(userCode);
		} catch (Exception e) {
			log.error("查询好友列表出错", e);
			throw new BusinessException("查询好友列表出错", e);
		}
	}

	/**
	 * 是否是好友
	 * @see com.zssq.service.IUserRelationService#isFriends(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isFriends(String userCode, String otherCode) throws BusinessException {
		try {
			UserFriend record = new UserFriend();
			record.setUserCode(userCode);
			record.setFriendUserCode(otherCode);
			return userFriendMapper.selectMyFriends(record).size() > 0;
		} catch (Exception e) {
			log.error("查询好友列表出错", e);
			throw new BusinessException("查询好友列表出错", e);
		}
	}

	/**
	 * 拉黑
	 * @see com.zssq.service.IUserRelationService#addBlack(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean addBlack(String userCode, String blackUser) throws BusinessException {
		try {
			//先查看是否已经在我的黑名单，防止前端重复调用接口造成数据库数据重复
			UserBlacklist blackListRecord = new UserBlacklist();
			blackListRecord.setUserCode(userCode);
			blackListRecord.setBlacklistUserCode(blackUser);
			List<UserBlacklist> blackList = userBlacklistMapper.selectByRecord(blackListRecord);
			if(blackList.size() > 0){
				return true;
			}
			
			//删除好友/关注关系
			UserFriend userFriend = new UserFriend();
			userFriend.setUserCode(userCode);
			userFriend.setFriendUserCode(blackUser);
			if(userFriendMapper.delectFriend(userFriend) == 0){
				UserConcerns userConcerns = new UserConcerns();
				userConcerns.setConcernsUserCode(userCode);
				userConcerns.setIsConcernsUserCode(blackUser);
				userConcernsMapper.delBoth(userConcerns);
			}
			//加入黑名单
			UserBlacklist record = new UserBlacklist();
			record.setBlacklistCode(UUIDHelper.getUUID());
			record.setUserCode(userCode);
			record.setBlacklistUserCode(blackUser);
			record.setCreateTime(DateUtils.getFormatDateLong());
			record.setModifyTime(DateUtils.getFormatDateLong());
			return userBlacklistMapper.insertSelective(record) == 1;
		} catch (Exception e) {
			log.error("添加黑名单出错", e);
			throw new BusinessException("添加黑名单出错", e);
		}
	}

	/**
	 * 移除黑名单
	 * @see com.zssq.service.IUserRelationService#delBlack(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean delBlack(String userCode, String blackUser) throws BusinessException {
		try {
			//查询是否在黑名单 防止前端重复调用接口 出错
			UserBlacklist blackRecord = new UserBlacklist();
			blackRecord.setUserCode(userCode);
			blackRecord.setBlacklistUserCode(blackUser);
			List<UserBlacklist> userBlacklist = userBlacklistMapper.selectByRecord(blackRecord);
			if(userBlacklist.size() == 0){
				return true;
			}
			
			UserBlacklist record = new UserBlacklist();
			record.setUserCode(userCode);
			record.setBlacklistUserCode(blackUser);
			return userBlacklistMapper.deleteBlack(record) == 1;
		} catch (Exception e) {
			log.error("移移出黑名单出错", e);
			throw new BusinessException("移移出黑名单出错", e);
		}
	}

	/**
	 * 我的黑名单
	 * @see com.zssq.service.IUserRelationService#selectBlacks(PageParam, java.lang.String)
	 */
	@Override
	public PageBean selectBlacks(PageParam pageParam,String userCode) throws BusinessException {
		try {
			UserBlacklist record = new UserBlacklist();
			if(pageParam != null){
				record.setLimitStart(pageParam.getPageNo() * pageParam.getPageSize());
				record.setLimitEnd(pageParam.getPageSize());
			}
			record.setUserCode(userCode);
			List<UserBlacklist> dataList = userBlacklistMapper.selectPage(record);
			int count = userBlacklistMapper.selectCount(userCode);
			return new PageBean(record.getLimitStart(), record.getLimitEnd(), count, dataList);
		} catch (Exception e) {
			log.error("查询黑名单出错", e);
			throw new BusinessException("查询黑名单出错", e);
		}
	}

	/**
	 * 获取关注/拉黑/好友关系
	 * @see com.zssq.service.IUserRelationService#selectUserState(java.lang.String, java.lang.String)
	 */
	@Override
	public UserState selectUserState(String userCode, String otherCode) throws BusinessException {
		try {
			UserState userState = new UserState();
			userState.setIsConcerns(FilingConstants.BOOLEAN_FALSE);
			userState.setIsBlack(FilingConstants.BOOLEAN_FALSE);
			userState.setIsFriend(FilingConstants.BOOLEAN_FALSE);
			
			//是否好友，是好友一定是关注
			UserFriend record = new UserFriend();
			record.setUserCode(userCode);
			record.setFriendUserCode(otherCode);
			if(userFriendMapper.selectMyFriends(record).size() > 0){
				userState.setIsFriend(FilingConstants.BOOLEAN_TRUE);
				userState.setIsConcerns(FilingConstants.BOOLEAN_TRUE);
				return userState;
			}
			//关注
			UserConcerns userConcerns = new UserConcerns();
			userConcerns.setConcernsUserCode(userCode);
			userConcerns.setIsConcernsUserCode(otherCode);
			if(userConcernsMapper.selectByRecord(userConcerns).size() > 0){
				userState.setIsConcerns(FilingConstants.BOOLEAN_TRUE);
				return userState;
			}
			
			//黑名单
			UserBlacklist userBlacklist = new UserBlacklist();
			userBlacklist.setUserCode(userCode);
			userBlacklist.setBlacklistUserCode(otherCode);
			if(userBlacklistMapper.selectByRecord(userBlacklist).size() > 0){
				userState.setIsBlack(FilingConstants.BOOLEAN_TRUE);
			}
			
			return userState;
		} catch (Exception e) {
			log.error("获取关注/拉黑/好友关系异常", e);
			throw new BusinessException("获取关注/拉黑/好友关系异常", e);
		}
	}

	/**
	 * 新建好友分组
	 * @see com.zssq.service.IUserRelationService#addGroup(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean addGroup(String userCode, String groupName) throws BusinessException {
		try {
			UserFriendGroup record = new UserFriendGroup();
			record.setFriendGroupCode(UUIDHelper.getUUID());
			record.setUserCode(userCode);
			record.setGroupName(groupName);
			record.setCreateTime(DateUtils.getFormatDateLong());
			record.setModifyTime(DateUtils.getFormatDateLong());
			return userFriendGroupMapper.insertSelective(record) == 1;
		} catch (Exception e) {
			log.error("添加好友分组出错", e);
			throw new BusinessException("添加好友分组出错", e);
		}
	}

	/**
	 * 删除好友分组
	 * @see com.zssq.service.IUserRelationService#delGroup(java.lang.String)
	 */
	@Override
	public boolean delGroup(String groupCode) throws BusinessException {
		try {
			//将该分组成员划分到默认分组
			userFriendMapper.deleteGroup(groupCode);
			//删除该分组，但是如果是默认分组 不可删除
			if(FilingConstants.DEFAULT_GROUP.equals(groupCode)){
				return false;
			}else{
				return userFriendGroupMapper.deleteByCode(groupCode) == 1;
			}
		} catch (Exception e) {
			log.error("删除好友分组异常", e);
			throw new BusinessException("删除好友分组异常", e);
		}
	}

	/**
	 * 变更好友分组
	 * @see com.zssq.service.IUserRelationService#changeUserGroup(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean changeUserGroup(String userCode,String friendCode,String groupCode) throws BusinessException {
		try {
			UserFriend record = new UserFriend();
			record.setUserCode(userCode);
			record.setFriendUserCode(friendCode);
			record.setFriendGroupCode(groupCode);
			return userFriendMapper.updateGroup(record) > 0;
		} catch (Exception e) {
			log.error("变更好友分组异常", e);
			throw new BusinessException("变更好友分组异常", e);
		}
	}

	/**
	 * 获取好友分组及人数
	 * @see com.zssq.service.IUserRelationService#selectMyGroup(java.lang.String)
	 */
	@Override
	public List<MyGroup> selectMyGroup(String userCode) throws BusinessException {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userCode", userCode);
			map.put("defaultGroup", FilingConstants.DEFAULT_GROUP);
			return userFriendGroupMapper.selectMyGroup(map);
		} catch (Exception e) {
			log.error("查询分组信息", e);
			throw new BusinessException("查询分组信息", e);
		}
	}
	
	/**
	 * 我的数量  好友/粉丝/关注/黑名单
	 * @see com.zssq.service.IUserRelationService#selectMyCount(java.lang.String)
	 */
	public MyCount selectMyCount(String userCode) throws BusinessException{
		try {
			UserFriend record = new UserFriend();
			record.setUserCode(userCode);
			int friendsCount = userFriendMapper.selectMyFriendsCount(record);
			int fansCount = userConcernsMapper.selectMyFansCount(userCode);
			int concernsCount = userConcernsMapper.selectMyConcernsCount(userCode);
			int blackCount = userBlacklistMapper.selectCount(userCode);
			MyCount myCount = new MyCount();
			myCount.setFriendsCount(friendsCount);
			myCount.setConcernsCount(concernsCount);
			myCount.setFansCount(fansCount);
			myCount.setBlackCount(blackCount);
			return myCount;
		} catch (Exception e) {
			log.error("查询我的数量  好友/粉丝/关注/黑名单 异常", e);
			throw new BusinessException("我的数量  好友/粉丝/关注/黑名单", e);
		}
	}

	
	/**
	 * 
	 * 开启私信屏蔽功能.
	 * @see com.zssq.service.IUserRelationService#shieldOn(java.lang.String)
	 */
	@Override
	public boolean shieldOn(String userCode) throws BusinessException {
		
		try {
			UserShieldRelation record = new UserShieldRelation();
			record.setShieldCode(UUIDHelper.getUUID());
			record.setUserCode(userCode);
			record.setCreateTime(DateUtils.getFormatDateLong());
			return userShieldRelationMapper.insert(record) > 0;
		} catch (Exception e) {
			log.error("用户开启私信屏蔽功能", e);
			throw new BusinessException("用户开启私信屏蔽功能", e);
		}
	}

	
	/**
	 * 
	 * 关闭私信屏蔽.
	 * @see com.zssq.service.IUserRelationService#shieldOff(java.lang.String)
	 */
	@Override
	public boolean shieldOff(String userCode) throws BusinessException {
		try {
			UserShieldRelation record = new UserShieldRelation();
			record.setUserCode(userCode);
			return userShieldRelationMapper.deleteByUserCode(record) > 0;
		} catch (Exception e) {
			log.error("用户关闭私信屏蔽功能", e);
			throw new BusinessException("用户关闭私信屏蔽功能", e);
		}
	}
	
	
	/**
	 * 
	 * 查看用户是否开启了私信屏蔽.
	 * @see com.zssq.service.IUserRelationService#checkShield(java.lang.String)
	 */
	@Override
	public boolean checkShield(String userCode) throws BusinessException {
		
		try {
			UserShieldRelation record = new UserShieldRelation();
			record.setUserCode(userCode);
			return userShieldRelationMapper.selectCountByUserCode(record) > 0;
		} catch (Exception e) {
			log.error("查看用户是否开启了私信屏蔽", e);
			throw new BusinessException("查看用户是否开启了私信屏蔽", e);
		}
		
	}

	
	/**
	 * 
	 * 获取关注和好友的user_code集合.
	 * @see com.zssq.service.IUserRelationService#selectConcernsAndFriends(java.lang.String)
	 */
	@Override
	public List<String> selectConcernsAndFriends(String userCode) throws BusinessException {
		
		try {
			List<String> dataList = new ArrayList<String>();
			dataList.addAll(userConcernsMapper.selectMyConcerns(userCode));
			dataList.addAll(userFriendMapper.selectFriends(userCode));
			
			return dataList;
		} catch (Exception e) {
			log.error("获取关注和好友的user_code集合", e);
			throw new BusinessException("获取关注和好友的user_code集合", e);
		}
	}


}
