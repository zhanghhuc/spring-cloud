package com.zssq.filing.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zssq.datasource.DataSource;
import com.zssq.datasource.DataSourceConstants;
import com.zssq.datasource.DynamicDataSourceHolder;
import com.zssq.filing.vo.BatchSetterForUserConcerns;
import com.zssq.filing.vo.BatchSetterForUserFriend;
import com.zssq.filing.vo.BatchSetterForUserFriendGroup;
import com.zssq.filing.vo.KcFriendGroup;
import com.zssq.filing.vo.KcFriendGrpUsrRel;
import com.zssq.filing.vo.UserFriendGroup;
import com.zssq.filing.vo.UserInfo;

@Service
public class FilingService {

	@Resource
	JdbcTemplate jdbcTemplate;
	
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 
	 * @Title: getKcFriendGroup @Description: 获取用户好友分组列表 @param @return
	 *         参数 @return List<KcFriendGroup> 返回类型 @throws
	 */
	@DataSource(DataSourceConstants.DB2_PORTALDB)
	public List<KcFriendGroup> getKcFriendGroup() {
		String sql = "select * from iportal.kc_friend_group where IS_DEFAULT = '0'";
		List<KcFriendGroup> list = jdbcTemplate.query(sql, new KcFriendGroup());
		return list;
	}

	/**
	 * 
	 * @Title: getKcFriendGrpUsrRel @Description: 获取用户关注列表 @param @return
	 *         参数 @return List<KcFriendGrpUsrRel> 返回类型 @throws
	 */
	@DataSource(DataSourceConstants.DB2_PORTALDB)
	public List<KcFriendGrpUsrRel> getKcFriendGrpUsrRelWithConcerns() {
		String sql = "select * from iportal.kc_friend_grp_usr_rel where status = '0'";
		List<KcFriendGrpUsrRel> list = jdbcTemplate.query(sql, new KcFriendGrpUsrRel());
		return list;
	}

	/**
	 * 
	 * @Title: getKcFriendGrpUsrRelWithFriend @Description:
	 *         获取用户好友列表 @param @return 参数 @return List
	 *         <KcFriendGrpUsrRel> 返回类型 @throws
	 */
	@DataSource(DataSourceConstants.DB2_PORTALDB)
	public List<KcFriendGrpUsrRel> getKcFriendGrpUsrRelWithFriend() {
		String sql = "select * from iportal.kc_friend_grp_usr_rel where status = '1'";
		List<KcFriendGrpUsrRel> list = jdbcTemplate.query(sql, new KcFriendGrpUsrRel());
		return list;
	}

	/**
	 * 
	 * @Title: iinsertUserFriendGroup 
	 * @Description: 导入用户好友分组列表 
	 * @param 参数 
	 * @return void 返回类型 
	 * @throws
	 */
	public void iinsertUserFriendGroup() {
		// 添加用户好友分组的sql
		String sql = "insert into transfer_user_friend_group(friend_group_code,user_code,group_name,is_default,create_time,modify_time,group_id) values(?,?,?,?,?,?,?)";

		// 切换数据源到DB2
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.DB2_PORTALDB);
		// 获取好友分组列表
		List<KcFriendGroup> kcFriendGroupList = getKcFriendGroup();

		// 批量导入用户好友分组的对象集合
		List<KcFriendGroup> kcFriendGroupBatchList = new ArrayList<KcFriendGroup>();

		// 切换数据源到本地auth库
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_AUTH);

		if (CollectionUtils.isNotEmpty(kcFriendGroupList)) {
			// 总记录数
			int size = kcFriendGroupList.size();
			logger.info("----------好友分组导入开始----------");
			logger.info("----------好友分组原始记录总数：" + size);
			int errorCount = 0;//导入有错误记录数
			for (int i = 0; i < size; i++) {
				// 批量导入用户好友分组的对象
				KcFriendGroup kcFriendGroupBatch = new KcFriendGroup();

				// 获取用户好友分组的userId，用字段CREATE_USER作为user_id字段去sys_user_info表中查询user_code
				String createUser = kcFriendGroupList.get(i).getCreateUser();
				Integer userId = Integer.valueOf(createUser);
				// 查询个人信息的sql
				String sqlForUser = "SELECT user_code from transfer_user_info where user_id = ?";
				// 查询个人信息
				List<UserInfo> userInfoList = jdbcTemplate.query(sqlForUser, new UserInfo(), userId);
				// 用户编号
				String userCode = "";
				if (CollectionUtils.isNotEmpty(userInfoList)) {
					UserInfo userInfo = userInfoList.get(0);
					if (userInfo != null) {
						userCode = userInfo.getUserCode();
					}
				}else{
					//未查到对应userCode
					userCode = "error--userId:" + userId;
					errorCount++;
				}

				kcFriendGroupBatch.setUserCode(userCode);
				kcFriendGroupBatch.setGroupName(kcFriendGroupList.get(i).getGroupName());
				kcFriendGroupBatch.setIsDefault(kcFriendGroupList.get(i).getIsDefault());
				kcFriendGroupBatch.setCreateDate(kcFriendGroupList.get(i).getCreateDate());
				kcFriendGroupBatch.setModifyDate(kcFriendGroupList.get(i).getModifyDate());
				kcFriendGroupBatch.setId(kcFriendGroupList.get(i).getId());
				// 向集合中添加对象
				kcFriendGroupBatchList.add(kcFriendGroupBatch);

				// 集合满100条记录后则单批次导入数据
				if ((i + 1) % 100 == 0) {
					// 单批次导入数据
					// 切换数据源到本地filing库
					DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_FILING);
					// 批量添加用户好友分组
					jdbcTemplate.batchUpdate(sql, new BatchSetterForUserFriendGroup(kcFriendGroupBatchList));
					// 单批次导入后清空集合
					kcFriendGroupBatchList.clear();
					logger.info("----------好友分组已导入记录数：" + (i+1));
					// 将数据源切换到本地auth库
					DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_AUTH);
				} else if (i == size - 1) {
					// 如果size不是100的整数倍，则在遍历到最后一条记录时，批量执行剩下的所有
					// 切换数据源到本地filing库
					DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_FILING);
					// 批量添加用户好友分组
					jdbcTemplate.batchUpdate(sql, new BatchSetterForUserFriendGroup(kcFriendGroupBatchList));
					logger.info("----------好友分组导入记录总数：" + (i+1));
				}
			}

			logger.info("----------好友分组导入有异常的记录数：" + errorCount);
			logger.info("----------好友分组导入结束----------");
		}
	}

	/**
	 * 
	 * @Title: iinsertUserConcerns 
	 * @Description: 导入用户关注的列表 
	 * @param 参数 
	 * @return void 返回类型
	 * @throws
	 */
	public void iinsertUserConcerns() {
		// 添加用户关注列表的sql
		String sql = "insert into transfer_user_concerns(concerns_code,concerns_user_code,is_concerns_user_code,create_time,modify_time) values(?,?,?,?,?)";

		// 切换数据源到DB2
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.DB2_PORTALDB);
		// 获取用户关注的列表
		List<KcFriendGrpUsrRel> kcFriendGrpUsrRelList = getKcFriendGrpUsrRelWithConcerns();

		// 批量导入用户关注列表的对象集合
		List<KcFriendGrpUsrRel> kcFriendGrpUsrRelBatchList = new ArrayList<KcFriendGrpUsrRel>();

		// 切换数据源到本地auth库
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_AUTH);
		if (CollectionUtils.isNotEmpty(kcFriendGrpUsrRelList)) {
			// 总记录数
			int size = kcFriendGrpUsrRelList.size();
			logger.info("----------用户关注导入开始----------");
			logger.info("----------用户关注原始记录总数：" + size);
			int errorCount = 0;//导入有异常记录数
			for (int i = 0; i < size; i++) {
				boolean errorFlag = false;//该记录出现异常的标志，默认是没有异常
				// 批量导入用户关注的对象
				KcFriendGrpUsrRel kcFriendGrpUsrRelBatch = new KcFriendGrpUsrRel();

				// 获取关注人的userId，根据源表中的USER_ID做user_id字段去sys_user_info表中查询user_code
				Integer userId = kcFriendGrpUsrRelList.get(i).getUserId();
				// 查询个人信息的sql
				String sqlForUser = "SELECT user_code from transfer_user_info where user_id = ?";
				// 查询个人信息
				List<UserInfo> userInfoList = jdbcTemplate.query(sqlForUser, new UserInfo(), userId);
				// 用户编号
				String userCode = "";
				if (CollectionUtils.isNotEmpty(userInfoList)) {
					UserInfo userInfo = userInfoList.get(0);
					if (userInfo != null) {
						userCode = userInfo.getUserCode();
					}
				}else{
					//未查到userCode
					userCode = "error--userId:" + userId;
					errorFlag = true;
				}

				// 获取被关注人的userId，根据源表中的FRIEND_ID字段做为user_id去sys_user_info表中查询user_code字段
				Integer friendId = kcFriendGrpUsrRelList.get(i).getFriendId();
				// 查询个人信息
				List<UserInfo> userInfoList2 = jdbcTemplate.query(sqlForUser, new UserInfo(), friendId);
				// 用户编号
				String userCode2 = "";
				if (CollectionUtils.isNotEmpty(userInfoList2)) {
					UserInfo userInfo = userInfoList2.get(0);
					if (userInfo != null) {
						userCode2 = userInfo.getUserCode();
					}
				}else{
					//未查到关注用户信息
					userCode2 = "error--userId:" + friendId;
					errorFlag = true;
				}
				
				if(errorFlag){//统计异常记录数量
					errorCount++;
				}
				
				kcFriendGrpUsrRelBatch.setUserCode(userCode);
				kcFriendGrpUsrRelBatch.setConcernedCode(userCode2);
				kcFriendGrpUsrRelBatch.setCreateDate(kcFriendGrpUsrRelList.get(i).getCreateDate());
				// 向集合中添加对象
				kcFriendGrpUsrRelBatchList.add(kcFriendGrpUsrRelBatch);

				// 集合满100条记录后则单批次导入数据
				if ((i + 1) % 100 == 0) {
					// 单批次导入数据
					// 切换数据源到本地filing库
					DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_FILING);
					// 批量添加用户关注列表
					jdbcTemplate.batchUpdate(sql, new BatchSetterForUserConcerns(kcFriendGrpUsrRelBatchList));
					// 单批次导入后清空集合
					kcFriendGrpUsrRelBatchList.clear();
					logger.info("----------用户关注已导入记录数：" + (i+1));
					// 将数据源切换到本地auth库
					DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_AUTH);
				} else if (i == size - 1) {
					// 如果size不是100的整数倍，则在遍历到最后一条记录时，批量执行剩下的所有
					// 切换数据源到本地filing库
					DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_FILING);
					// 批量添加用户关注列表
					jdbcTemplate.batchUpdate(sql, new BatchSetterForUserConcerns(kcFriendGrpUsrRelBatchList));
					logger.info("----------用户关注导入记录总数：" + (i+1));
				}
			}
			
			logger.info("----------用户关注导入记录异常数量：" + errorCount);
			logger.info("----------用户关注导入结束----------");

		}
	}

	/**
	 * 
	 * @Title: iinsertUserFriend 
	 * @Description: 导入用户好友列表
	 * @param 参数 
	 * @return void 返回类型 
	 * @throws
	 */
	public void iinsertUserFriend() {
		// 添加用户好友列表的sql
		String sql = "insert into transfer_user_friend(user_friend_code,user_code,friend_user_code,friend_group_code,create_time,modify_time) values(?,?,?,?,?,?)";

		// 切换数据源到DB2
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.DB2_PORTALDB);
		// 获取用户好友列表
		List<KcFriendGrpUsrRel> kcFriendGrpUsrRelList = getKcFriendGrpUsrRelWithFriend();

		// 批量导入用户好友列表的对象集合
		List<KcFriendGrpUsrRel> kcFriendGrpUsrRelBatchList = new ArrayList<KcFriendGrpUsrRel>();

		if (CollectionUtils.isNotEmpty(kcFriendGrpUsrRelList)) {
			// 总记录数
			int size = kcFriendGrpUsrRelList.size();
			logger.info("----------用户好友导入开始----------");
			logger.info("----------用户好友原始记录总数：" + size);
			int errorCount = 0;//记录导入异常的数量
			for (int i = 0; i < size; i++) {
				boolean errorFlag = false;
				// 批量导入用户好友的对象
				KcFriendGrpUsrRel kcFriendGrpUsrRelBatch = new KcFriendGrpUsrRel();

				// 获取用户的userId，根据源表中的USER_ID做user_id字段去sys_user_info表中查询user_code
				Integer userId = kcFriendGrpUsrRelList.get(i).getUserId();
				// 查询个人信息的sql
				String sqlForUser = "SELECT user_code from transfer_user_info where user_id = ?";
				// 查询个人信息
				// 切换数据源到本地auth库
				DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_AUTH);
				List<UserInfo> userInfoList = jdbcTemplate.query(sqlForUser, new UserInfo(), userId);
				// 用户编号
				String userCode = "";
				if (CollectionUtils.isNotEmpty(userInfoList)) {
					UserInfo userInfo = userInfoList.get(0);
					userCode = userInfo.getUserCode();
				}else{
					//未查到userCode
					userCode = "error--userId:" + userId;
					errorFlag = true;
				}

				// 获取好友的userId，根据源表中的FRIEND_ID字段做为user_id去sys_user_info表中查询user_code字段
				Integer friendId = kcFriendGrpUsrRelList.get(i).getFriendId();
				// 查询个人信息
				List<UserInfo> userInfoList2 = jdbcTemplate.query(sqlForUser, new UserInfo(), friendId);
				// 用户编号
				String userCode2 = "";
				if (CollectionUtils.isNotEmpty(userInfoList2)) {
					UserInfo userInfo = userInfoList2.get(0);
					userCode2 = userInfo.getUserCode();
				}else{
					//未查到friend的Code
					userCode2 = "error--userId:" + friendId;
					errorFlag = true;
				}

				
				/*
				 * 根据源表中 GROUP_ID 字段作为 ID 去源表 KC_FRIEND_GROUP 中查询记录，如果该记录
				 * IS_DEFAULT=1 则该字段值为000，否则根据 GROUP_ID 去目标表 user_friend_group
				 * 中查询，用 friend_group_code 字段填充
				 */
				String groupCode = "";
				// 获取组编号groupId
				Integer groupId = kcFriendGrpUsrRelList.get(i).getGroupId();
				// 切换数据源到DB2
				DynamicDataSourceHolder.setDataSource(DataSourceConstants.DB2_PORTALDB);
				String sqlForGroup = "select * from iportal.kc_friend_group where ID = ?";
				List<KcFriendGroup> groupInfo = jdbcTemplate.query(sqlForGroup, new KcFriendGroup(), groupId);
				if(CollectionUtils.isNotEmpty(groupInfo)){
					KcFriendGroup kcFriendGroup = groupInfo.get(0);
					if(kcFriendGroup!=null){
						String isDefault = kcFriendGroup.getIsDefault();
						if("1".equals(isDefault)){
							//默认分组
							groupCode = "000";
						}else{
							//不是默认分组，查询groupCode
							String sqlForGroup2 = "select * from transfer_user_friend_group where group_id = ?";
							// 切换数据源到本地filing库
							DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_FILING);
							List<UserFriendGroup> userFriendGroupList = jdbcTemplate.query(sqlForGroup2, new UserFriendGroup(), groupId);
							if(CollectionUtils.isNotEmpty(userFriendGroupList)){
								UserFriendGroup userFriendGroup = userFriendGroupList.get(0);
								if(userFriendGroup!=null){
									groupCode = userFriendGroup.getFriendGroupCode();
								}
							}
						}
					}
				}else{
					//未查到该分组信息
					groupCode = "error--groupId:" + groupId;
					errorFlag = true;
				}
				
				if(errorFlag){//统计导入异常的记录数
					errorCount++;
				}
				
				kcFriendGrpUsrRelBatch.setUserCode(userCode);
				kcFriendGrpUsrRelBatch.setFriendCode(userCode2);
				kcFriendGrpUsrRelBatch.setGroupCode(groupCode);
				kcFriendGrpUsrRelBatch.setCreateDate(kcFriendGrpUsrRelList.get(i).getCreateDate());
				// 向集合中添加对象
				kcFriendGrpUsrRelBatchList.add(kcFriendGrpUsrRelBatch);

				// 集合满100条记录后则单批次导入数据
				if ((i + 1) % 100 == 0) {
					// 单批次导入数据
					// 切换数据源到本地filing库
					DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_FILING);
					// 批量添加用户好友列表
					jdbcTemplate.batchUpdate(sql, new BatchSetterForUserFriend(kcFriendGrpUsrRelBatchList));
					// 单批次导入后清空集合
					kcFriendGrpUsrRelBatchList.clear();
					logger.info("----------用户好友已导入记录数：" + (i+1));
				} else if (i == size - 1) {
					// 如果size不是100的整数倍，则在遍历到最后一条记录时，批量执行剩下的所有
					// 切换数据源到本地filing库
					DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_FILING);
					// 批量添加用户好友列表
					jdbcTemplate.batchUpdate(sql, new BatchSetterForUserFriend(kcFriendGrpUsrRelBatchList));
					logger.info("----------用户好友导入总记录数：" + (i+1));
				}
			}
			
			logger.info("----------用户好友导入异常记录数：" + errorCount);
			logger.info("----------用户好友导入结束----------");
		}
	}
}
