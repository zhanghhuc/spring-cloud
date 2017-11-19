package com.zssq.credit.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zssq.credit.vo.CoinAccount;
import com.zssq.credit.vo.GoldTeamSta;
import com.zssq.credit.vo.GoldUserSta;
import com.zssq.credit.vo.IntegralAccount;
import com.zssq.credit.vo.MyBatchPreparedStatementSetterForCoin;
import com.zssq.credit.vo.MyBatchPreparedStatementSetterForCoinDetail;
import com.zssq.credit.vo.MyBatchPreparedStatementSetterForIntegral;
import com.zssq.credit.vo.MyBatchPreparedStatementSetterForIntegralDetail;
import com.zssq.credit.vo.OrgInfo;
import com.zssq.credit.vo.TeamInfo;
import com.zssq.credit.vo.UserInfo;
import com.zssq.datasource.DataSource;
import com.zssq.datasource.DataSourceConstants;
import com.zssq.datasource.DynamicDataSourceHolder;

@Service
public class CreditService {

	/** 打印日志 */
	private static Logger logger = LoggerFactory.getLogger(CreditService.class);

	@Resource
	JdbcTemplate jdbcTemplate;

	/**
	 * 
	 * @Title: getGoldUserStaList @Description: 从源库查询个人积分、金币信息 @param @return
	 *         参数 @return List<GoldUserSta> 返回类型 @throws
	 */
	@DataSource(DataSourceConstants.DB2_PORTALDB)
	public List<GoldUserSta> getGoldUserStaList() {
		String sql = "select * from iportal.gold_user_sta";
		List<GoldUserSta> list = jdbcTemplate.query(sql, new GoldUserSta());
		return list;
	}

	/**
	 * 
	 * @Title: getGoldTeamStaList @Description: 从源库查询班组积分、金币信息 @param @return
	 *         参数 @return List<GoldTeamSta> 返回类型 @throws
	 */
	@DataSource(DataSourceConstants.DB2_PORTALDB)
	public List<GoldTeamSta> getGoldTeamStaList() {
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.DB2_PORTALDB);
		String sql = "select * from iportal.gold_team_sta";
		List<GoldTeamSta> list = jdbcTemplate.query(sql, new GoldTeamSta());
		return list;
	}

	/**
	 * 
	 * @Title: getIntegralAccountList @Description: 从本地库查询积分账户列表 @param @return
	 *         参数 @return List<IntegralAccount> 返回类型 @throws
	 */
	@DataSource(DataSourceConstants.MYSQL_CREDIT)
	public List<IntegralAccount> getIntegralAccountList() {
		String sql = "select * from integral_account";
		List<IntegralAccount> list = jdbcTemplate.query(sql, new IntegralAccount());
		return list;
	}

	/**
	 * 
	 * @Title: getCoinAccountList @Description: 从本地库查询金币账户列表 @param @return
	 *         参数 @return List<CoinAccount> 返回类型 @throws
	 */
	@DataSource(DataSourceConstants.MYSQL_CREDIT)
	public List<CoinAccount> getCoinAccountList() {
		String sql = "select * from coin_account";
		List<CoinAccount> list = jdbcTemplate.query(sql, new CoinAccount());
		return list;
	}

	/**
	 * 
	 * @Title: insertIntegralWithUser @Description: 分批导入个人积分账户 @param 参数 @return
	 *         void 返回类型 @throws
	 */
	public void iinsertIntegralWithUser() {
		// 记录userCode为空的计数器
		int countUserCode = 0;
		// 记录orgCode为空的计数器
		int countOrgCode = 0;
		// 记录userCode和orgCode都为空的计数器
		int countBoth = 0;
		// 需要导入的总记录数
		int size = 0;

		// 添加个人积分账户的sql
		String sql = "insert into integral_account(account_code,account_type,integral_balance,create_time,modify_time,org_code) values(?,?,?,?,?,?)";

		// 切换数据源到DB2
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.DB2_PORTALDB);
		// 获取个人积分、金币账户列表
		List<GoldUserSta> goldUserStaList = getGoldUserStaList();

		// 批量导入个人积分账户的对象集合
		List<GoldUserSta> goldUserStaBatchList = new ArrayList<GoldUserSta>();

		// 切换数据源到本地auth库
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_AUTH);
		if (CollectionUtils.isNotEmpty(goldUserStaList)) {
			// 总记录数
			size = goldUserStaList.size();
			for (int i = 0; i < size; i++) {
				// 批量导入个人积分账户的对象
				GoldUserSta goldUserStaBatch = new GoldUserSta();

				// 获取个人积分、金币账户的自增主键
				Integer userId = goldUserStaList.get(i).getUserId();

				// 查询个人信息的sql
				String sqlForUser = "SELECT user_code,org_code from transfer_user_info where user_id = ?";
				// 查询个人信息
				List<UserInfo> userInfoList = jdbcTemplate.query(sqlForUser, new UserInfo(), userId);
				
				// 用户编号
				String userCode = "";
				// 所属组织编号
				String orgCode = "";
				// 所属行政组织编号
				String manOrgCode = "";
				if (CollectionUtils.isNotEmpty(userInfoList)) {
					UserInfo userInfo = userInfoList.get(0);
					if (userInfo != null) {
						userCode = userInfo.getUserCode();
						orgCode = userInfo.getOrgCode();
						// 若查询到的个人所属组织编号不为空，则在表transfer_org_info中查询用户所属的行政组织编号
						if(StringUtils.isNotBlank(orgCode)){
							// 查询个人所属的行政组织编号的sql
							String sqlForOrgCode = "SELECT man_org_code from transfer_org_info where sys_org_code = ?";
							// 查询个人所属的行政组织编号
							List<OrgInfo> orgInfoList = jdbcTemplate.query(sqlForOrgCode, new OrgInfo(), orgCode);
							if(CollectionUtils.isNotEmpty(orgInfoList)){
								OrgInfo orgInfo = orgInfoList.get(0);
								if (orgInfo != null) {
									manOrgCode = orgInfo.getManOrgCode();
								}
							}
						}
					}
				}

				if (StringUtils.isBlank(userCode) && StringUtils.isBlank(manOrgCode)) {
					countBoth++;
					logger.info("IntegralWithUser------在库zssq_auth中查询user_id为{}的用户信息时，未查到user_code和man_org_code", userId);
				}
				// 若从zssq_auth中未查到相应记录，则覆盖userCode和manOrgCode，以便于校验迁移的数据
				if (StringUtils.isBlank(userCode)) {
					countUserCode++;
					logger.info("IntegralWithUser------在库zssq_auth-表transfer_user_info中查询user_id为{}的用户信息时，未查到user_code", userId);
					// 覆盖userCode，以便于校验迁移的数据
					userCode = "userId" + userId;
				}
				if (StringUtils.isBlank(manOrgCode)) {
					countOrgCode++;
					logger.info("IntegralWithUser------在库zssq_auth-表transfer_org_info中查询sys_org_code为{}，user_id为{}的用户信息时，未查到man_org_code", orgCode, userId);
					// 覆盖manOrgCode，以便于校验迁移的数据
					manOrgCode = "orgUserId" + userId;
				}

				goldUserStaBatch.setUserCode(userCode);
				goldUserStaBatch.setOrgCode(manOrgCode);
				goldUserStaBatch.setUserId(userId);
				goldUserStaBatch.setPointNum(goldUserStaList.get(i).getPointNum());
				goldUserStaBatch.setCreateTime(goldUserStaList.get(i).getCreateTime());
				goldUserStaBatch.setUpdateTime(goldUserStaList.get(i).getUpdateTime());
				// 向集合中添加对象
				goldUserStaBatchList.add(goldUserStaBatch);

				// 集合满500条记录后则单批次导入数据
				if ((i + 1) % 500 == 0) {
					// 单批次导入数据
					// 切换数据源到本地credit库
					DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_CREDIT);
					// 批量添加个人积分账户
					jdbcTemplate.batchUpdate(sql, new MyBatchPreparedStatementSetterForIntegral(goldUserStaBatchList));
					// 单批次导入后清空集合
					goldUserStaBatchList.clear();
					// 将数据源切换到本地auth库
					DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_AUTH);
				} else if (i == size - 1) {
					// 如果size不是500的整数倍，则在遍历到最后一条记录时，批量执行剩下的所有
					// 切换数据源到本地credit库
					DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_CREDIT);
					// 批量添加个人积分账户
					jdbcTemplate.batchUpdate(sql, new MyBatchPreparedStatementSetterForIntegral(goldUserStaBatchList));
				}
			}
		}
		logger.info("IntegralWithUser------需要导入的总记录数为{}", size);
		logger.info("IntegralWithUser------userCode为空的记录数为{}", countUserCode);
		logger.info("IntegralWithUser------orgCode为空的记录数为{}", countOrgCode);
		logger.info("IntegralWithUser------userCode和orgCode都为空的记录数为{}", countBoth);
	}

	/**
	 * 
	 * @Title: insertIntegralWithTeam @Description: 分批导入班组积分账户 @param 参数 @return
	 *         void 返回类型 @throws
	 */
	public void iinsertIntegralWithTeam() {
		// 记录teamCode为空的计数器
		int countTeamCode = 0;
		// 记录orgCode为空的计数器
		int countOrgCode = 0;
		// 记录userCode和orgCode都为空的计数器
		int countBoth = 0;
		// 需要导入的总记录数
		int size = 0;

		// 添加班组积分账户的sql
		String sql = "insert into integral_account(account_code,account_type,integral_balance,create_time,modify_time,org_code) values(?,?,?,?,?,?)";

		// 切换数据源到本地DB2
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.DB2_PORTALDB);
		// 获取班组积分、金币账户列表
		List<GoldTeamSta> goldTeamStaList = getGoldTeamStaList();

		// 批量导入班组积分账户的对象集合
		List<GoldTeamSta> goldTeamStaBatchList = new ArrayList<GoldTeamSta>();

		// 切换数据源到本地team库
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_TEAM);
		if (CollectionUtils.isNotEmpty(goldTeamStaList)) {
			size = goldTeamStaList.size();
			for (int i = 0; i < size; i++) {
				// 批量导入班组积分账户的对象
				GoldTeamSta goldTeamStaBatch = new GoldTeamSta();

				// 获取班组积分、金币账户的自增主键
				Integer teamId = goldTeamStaList.get(i).getTeamId();

				// 查询班组信息的sql
				String sqlForTeam = "select team_code,org_code from team_info where team_id = ?";
				// 查询班组信息
				List<TeamInfo> teamInfoList = jdbcTemplate.query(sqlForTeam, new TeamInfo(), teamId);
				// 班组编号
				String teamCode = "";
				// 所属行政组织编号
				String orgCode = "";
				if (CollectionUtils.isNotEmpty(teamInfoList)) {
					TeamInfo teamInfo = teamInfoList.get(0);
					if (teamInfo != null) {
						teamCode = teamInfo.getTeamCode();
						orgCode = teamInfo.getOrgCode();
					}
				}

				if (StringUtils.isBlank(teamCode) && StringUtils.isBlank(orgCode)) {
					countBoth++;
					logger.info("IntegralWithTeam------在库zssq_team中查询teamId为{}的班组信息时，未查到teamCode和orgCode", teamId);
				}
				// 若从zssq_team中未查到相应记录，则覆盖teamCode和orgCode，以便于校验迁移的数据
				if (StringUtils.isBlank(teamCode)) {
					countTeamCode++;
					logger.info("IntegralWithTeam------在库zssq_team中查询teamId为{}的班组信息时，未查到teamCode", teamId);
					// 覆盖teamCode，以便于校验迁移的数据
					teamCode = "teamId" + teamId;
				}
				if (StringUtils.isBlank(orgCode)) {
					countOrgCode++;
					logger.info("IntegralWithTeam------在库zssq_team中查询teamId为{}的班组信息时，未查到orgCode", teamId);
					// 覆盖orgCode，以便于校验迁移的数据
					orgCode = "orgTeamId" + teamId;
				}

				goldTeamStaBatch.setTeamCode(teamCode);
				goldTeamStaBatch.setOrgCode(orgCode);
				goldTeamStaBatch.setTeamId(teamId);
				goldTeamStaBatch.setPointNum(goldTeamStaList.get(i).getPointNum());
				goldTeamStaBatch.setCreateTime(goldTeamStaList.get(i).getCreateTime());
				goldTeamStaBatch.setUpdateTime(goldTeamStaList.get(i).getUpdateTime());
				// 向集合中添加对象
				goldTeamStaBatchList.add(goldTeamStaBatch);

				// 集合满500条记录后则单批次导入数据
				if ((i + 1) % 500 == 0) {
					// 单批次导入数据
					// 切换数据源到本地credit库
					DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_CREDIT);
					// 批量添加班组积分账户
					jdbcTemplate.batchUpdate(sql, new MyBatchPreparedStatementSetterForIntegral(goldTeamStaBatchList));
					// 单批次导入后清空集合
					goldTeamStaBatchList.clear();
					// 切换数据源到本地team库
					DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_TEAM);
				} else if (i == size - 1) {
					// 如果size不是500的整数倍，则在遍历到最后一条记录时，批量执行剩下的所有
					// 切换数据源到本地credit库
					DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_CREDIT);
					// 批量添加班组积分账户
					jdbcTemplate.batchUpdate(sql, new MyBatchPreparedStatementSetterForIntegral(goldTeamStaBatchList));
				}
			}
		}
		logger.info("IntegralWithTeam------需要导入的总记录数为{}", size);
		logger.info("IntegralWithTeam------teamCode为空的记录数为{}", countTeamCode);
		logger.info("IntegralWithTeam------orgCode为空的记录数为{}", countOrgCode);
		logger.info("IntegralWithTeam------teamCode和orgCode都为空的记录数为{}", countBoth);
	}

	/**
	 * 
	 * @Title: insertCoinWithUser @Description: 分批导入个人金币账户 @param 参数 @return
	 *         void 返回类型 @throws
	 */
	public void iinsertCoinWithUser() {
		// 记录userCode为空的计数器
		int countUserCode = 0;
		// 记录orgCode为空的计数器
		int countOrgCode = 0;
		// 记录userCode和orgCode都为空的计数器
		int countBoth = 0;
		// 需要导入的总记录数
		int size = 0;

		// 添加个人金币账户的sql
		String sql = "insert into coin_account(account_code,account_type,coin_balance,create_time,modify_time,org_code) values(?,?,?,?,?,?)";

		// 切换数据源到DB2
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.DB2_PORTALDB);
		// 获取个人积分、金币账户列表
		List<GoldUserSta> goldUserStaList = getGoldUserStaList();

		// 批量导入个人金币账户的对象集合
		List<GoldUserSta> goldUserStaBatchList = new ArrayList<GoldUserSta>();

		// 切换数据源到本地auth库
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_AUTH);
		if (CollectionUtils.isNotEmpty(goldUserStaList)) {
			// 总记录数
			size = goldUserStaList.size();
			for (int i = 0; i < size; i++) {
				// 批量导入个人金币账户的对象
				GoldUserSta goldUserStaBatch = new GoldUserSta();

				// 获取个人积分、金币账户的自增主键
				Integer userId = goldUserStaList.get(i).getUserId();

				// 查询个人信息的sql
				String sqlForUser = "SELECT user_code,org_code from transfer_user_info where user_id = ?";
				// 查询个人信息
				List<UserInfo> userInfoList = jdbcTemplate.query(sqlForUser, new UserInfo(), userId);
				
				// 用户编号
				String userCode = "";
				// 所属组织编号
				String orgCode = "";
				// 所属行政组织编号
				String manOrgCode = "";
				if (CollectionUtils.isNotEmpty(userInfoList)) {
					UserInfo userInfo = userInfoList.get(0);
					if (userInfo != null) {
						userCode = userInfo.getUserCode();
						orgCode = userInfo.getOrgCode();
						// 若查询到的个人所属组织编号不为空，则在表transfer_org_info中查询用户所属的行政组织编号
						if(StringUtils.isNotBlank(orgCode)){
							// 查询个人所属的行政组织编号的sql
							String sqlForOrgCode = "SELECT man_org_code from transfer_org_info where sys_org_code = ?";
							// 查询个人所属的行政组织编号
							List<OrgInfo> orgInfoList = jdbcTemplate.query(sqlForOrgCode, new OrgInfo(), orgCode);
							if(CollectionUtils.isNotEmpty(orgInfoList)){
								OrgInfo orgInfo = orgInfoList.get(0);
								if (orgInfo != null) {
									manOrgCode = orgInfo.getManOrgCode();
								}
							}
						}
					}
				}

				if (StringUtils.isBlank(userCode) && StringUtils.isBlank(manOrgCode)) {
					countBoth++;
					logger.info("CoinWithUser------在库zssq_auth中查询user_id为{}的用户信息时，未查到user_code和man_org_code", userId);
				}
				// 若从zssq_auth中未查到相应记录，则覆盖userCode和manOrgCode，以便于校验迁移的数据
				if (StringUtils.isBlank(userCode)) {
					countUserCode++;
					logger.info("CoinWithUser------在库zssq_auth-表transfer_user_info中查询user_id为{}的用户信息时，未查到user_code", userId);
					// 覆盖userCode，以便于校验迁移的数据
					userCode = "userId" + userId;
				}
				if (StringUtils.isBlank(manOrgCode)) {
					countOrgCode++;
					logger.info("CoinWithUser------在库zssq_auth-表transfer_org_info中查询sys_org_code为{}，user_id为{}的用户信息时，未查到man_org_code", orgCode, userId);
					// 覆盖manOrgCode，以便于校验迁移的数据
					manOrgCode = "orgUserId" + userId;
				}

				goldUserStaBatch.setUserCode(userCode);
				goldUserStaBatch.setOrgCode(manOrgCode);
				goldUserStaBatch.setUserId(userId);
				goldUserStaBatch.setGoldCoin(goldUserStaList.get(i).getGoldCoin());
				goldUserStaBatch.setCreateTime(goldUserStaList.get(i).getCreateTime());
				goldUserStaBatch.setUpdateTime(goldUserStaList.get(i).getUpdateTime());
				// 向集合中添加对象
				goldUserStaBatchList.add(goldUserStaBatch);

				// 集合满500条记录后则单批次导入数据
				if ((i + 1) % 500 == 0) {
					// 单批次导入数据
					// 切换数据源到本地credit库
					DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_CREDIT);
					// 批量添加个人金币账户
					jdbcTemplate.batchUpdate(sql, new MyBatchPreparedStatementSetterForCoin(goldUserStaBatchList));
					// 单批次导入后清空集合
					goldUserStaBatchList.clear();
					// 切换数据源到本地auth库
					DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_AUTH);
				} else if (i == size - 1) {
					// 如果size不是500的整数倍，则在遍历到最后一条记录时，批量执行剩下的所有
					// 切换数据源到本地credit库
					DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_CREDIT);
					// 批量添加个人金币账户
					jdbcTemplate.batchUpdate(sql, new MyBatchPreparedStatementSetterForCoin(goldUserStaBatchList));
				}
			}
		}
		logger.info("CoinWithUser------需要导入的总记录数为{}", size);
		logger.info("CoinWithUser------userCode为空的记录数为{}", countUserCode);
		logger.info("CoinWithUser------orgCode为空的记录数为{}", countOrgCode);
		logger.info("CoinWithUser------userCode和orgCode都为空的记录数为{}", countBoth);
	}

	/**
	 * 
	 * @Title: insertCoinWithTeam @Description: 分批导入班组金币账户 @param 参数 @return
	 *         void 返回类型 @throws
	 */
	public void iinsertCoinWithTeam() {
		// 记录teamCode为空的计数器
		int countTeamCode = 0;
		// 记录orgCode为空的计数器
		int countOrgCode = 0;
		// 记录userCode和orgCode都为空的计数器
		int countBoth = 0;
		// 需要导入的总记录数
		int size = 0;

		// 添加班组金币账户的sql
		String sql = "insert into coin_account(account_code,account_type,coin_balance,create_time,modify_time,org_code) values(?,?,?,?,?,?)";

		// 切换数据源到本地DB2
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.DB2_PORTALDB);
		// 获取班组积分、金币账户列表
		List<GoldTeamSta> goldTeamStaList = getGoldTeamStaList();

		// 批量导入班组积分账户的对象集合
		List<GoldTeamSta> goldTeamStaBatchList = new ArrayList<GoldTeamSta>();

		// 切换数据源到本地team库
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_TEAM);
		if (CollectionUtils.isNotEmpty(goldTeamStaList)) {
			size = goldTeamStaList.size();
			for (int i = 0; i < size; i++) {
				// 批量导入班组金币账户的对象
				GoldTeamSta goldTeamStaBatch = new GoldTeamSta();

				// 获取班组积分、金币账户的自增主键
				Integer teamId = goldTeamStaList.get(i).getTeamId();

				// 查询班组信息的sql
				String sqlForTeam = "select team_code,org_code from team_info where team_id = ?";
				// 查询班组信息
				List<TeamInfo> teamInfoList = jdbcTemplate.query(sqlForTeam, new TeamInfo(), teamId);
				// 班组编号
				String teamCode = "";
				// 所属行政组织编号
				String orgCode = "";
				if (CollectionUtils.isNotEmpty(teamInfoList)) {
					TeamInfo teamInfo = teamInfoList.get(0);
					if (teamInfo != null) {
						teamCode = teamInfo.getTeamCode();
						orgCode = teamInfo.getOrgCode();
					}
				}

				if (StringUtils.isBlank(teamCode) && StringUtils.isBlank(orgCode)) {
					countBoth++;
					logger.info("CoinWithTeam------在库zssq_team中查询teamId为{}的班组信息时，未查到teamCode和orgCode", teamId);
				}
				// 若从zssq_team中未查到相应记录，则覆盖teamCode和orgCode，以便于校验迁移的数据
				if (StringUtils.isBlank(teamCode)) {
					countTeamCode++;
					logger.info("CoinWithTeam------在库zssq_team中查询teamId为{}的班组信息时，未查到teamCode", teamId);
					// 覆盖teamCode，以便于校验迁移的数据
					teamCode = "teamId" + teamId;
				}
				if (StringUtils.isBlank(orgCode)) {
					countOrgCode++;
					logger.info("CoinWithTeam------在库zssq_team中查询teamId为{}的班组信息时，未查到orgCode", teamId);
					// 覆盖orgCode，以便于校验迁移的数据
					orgCode = "orgTeamId" + teamId;
				}

				goldTeamStaBatch.setTeamCode(teamCode);
				goldTeamStaBatch.setOrgCode(orgCode);
				goldTeamStaBatch.setTeamId(teamId);
				goldTeamStaBatch.setGoldCoin(goldTeamStaList.get(i).getGoldCoin());
				goldTeamStaBatch.setCreateTime(goldTeamStaList.get(i).getCreateTime());
				goldTeamStaBatch.setUpdateTime(goldTeamStaList.get(i).getUpdateTime());
				// 向集合中添加对象
				goldTeamStaBatchList.add(goldTeamStaBatch);

				// 集合满500条记录后则单批次导入数据
				if ((i + 1) % 500 == 0) {
					// 单批次导入数据
					// 切换数据源到本地credit库
					DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_CREDIT);
					// 批量添加班组金币账户
					jdbcTemplate.batchUpdate(sql, new MyBatchPreparedStatementSetterForCoin(goldTeamStaBatchList));
					// 单批次导入后清空集合
					goldTeamStaBatchList.clear();
					// 切换数据源到本地team库
					DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_TEAM);
				} else if (i == size - 1) {
					// 如果size不是500的整数倍，则在遍历到最后一条记录时，批量执行剩下的所有
					// 切换数据源到本地credit库
					DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_CREDIT);
					// 批量添加班组金币账户
					jdbcTemplate.batchUpdate(sql, new MyBatchPreparedStatementSetterForCoin(goldTeamStaBatchList));
				}
			}
		}
		logger.info("CoinWithTeam------需要导入的总记录数为{}", size);
		logger.info("CoinWithTeam------teamCode为空的记录数为{}", countTeamCode);
		logger.info("CoinWithTeam------orgCode为空的记录数为{}", countOrgCode);
		logger.info("CoinWithTeam------teamCode和orgCode都为空的记录数为{}", countBoth);
	}

	/**
	 * 
	 * @Title: insertIntegralDetail @Description: 添加数据迁移历史积分明细 @param 参数 @return
	 *         void 返回类型 @throws
	 */
	@DataSource(DataSourceConstants.MYSQL_CREDIT)
	public void insertIntegralDetail() {
		// 需要导入的总记录数
		int size = 0;

		// 添加数据迁移历史积分明细的sql
		String sql = "insert into integral_account_detail(account_code,account_detail_type,current_value,current_balance,action_code,create_time) values(?,?,?,?,?,?)";

		// 获取积分账户列表
		List<IntegralAccount> integralAccountList = getIntegralAccountList();

		// 批量导入积分账户的对象集合
		List<IntegralAccount> integralAccountBatchList = new ArrayList<IntegralAccount>();
		if (CollectionUtils.isNotEmpty(integralAccountList)) {
			size = integralAccountList.size();
			for (int i = 0; i < size; i++) {
				// 批量导入积分账户的对象
				IntegralAccount integralAccountBatch = new IntegralAccount();
				integralAccountBatch.setAccountCode(integralAccountList.get(i).getAccountCode());
				integralAccountBatch.setIntegralBalance(integralAccountList.get(i).getIntegralBalance());
				// 向集合中添加对象
				integralAccountBatchList.add(integralAccountBatch);

				// 集合满500条记录后则单批次导入数据
				if ((i + 1) % 500 == 0) {
					// 单批次导入数据
					jdbcTemplate.batchUpdate(sql,
							new MyBatchPreparedStatementSetterForIntegralDetail(integralAccountBatchList));
					// 单批次导入后清空集合
					integralAccountBatchList.clear();
				} else if (i == size - 1) {
					// 如果size不是500的整数倍，则在遍历到最后一条记录时，批量执行剩下的所有
					jdbcTemplate.batchUpdate(sql,
							new MyBatchPreparedStatementSetterForIntegralDetail(integralAccountBatchList));
				}
			}
		}
		logger.info("IntegralDetail------需要导入的总记录数为{}", size);
	}

	/**
	 * 
	 * @Title: insertCoinDetail @Description: 添加数据迁移历史金币明细 @param 参数 @return
	 *         void 返回类型 @throws
	 */
	@DataSource(DataSourceConstants.MYSQL_CREDIT)
	public void insertCoinDetail() {
		// 需要导入的总记录数
		int size = 0;

		// 添加数据迁移历史金币明细的sql
		String sql = "insert into coin_account_detail(account_code,account_detail_type,current_value,current_balance,action_code,create_time) values(?,?,?,?,?,?)";

		// 获取金币账户列表
		List<CoinAccount> coinAccountList = getCoinAccountList();

		// 批量导入金币账户的对象集合
		List<CoinAccount> coinAccountBatchList = new ArrayList<CoinAccount>();
		if (CollectionUtils.isNotEmpty(coinAccountList)) {
			size = coinAccountList.size();
			for (int i = 0; i < size; i++) {
				// 批量导入金币账户的对象
				CoinAccount coinAccountBatch = new CoinAccount();
				coinAccountBatch.setAccountCode(coinAccountList.get(i).getAccountCode());
				coinAccountBatch.setCoinBalance(coinAccountList.get(i).getCoinBalance());
				// 向集合中添加对象
				coinAccountBatchList.add(coinAccountBatch);

				// 集合满500条记录后则单批次导入数据
				if ((i + 1) % 500 == 0) {
					// 单批次导入数据
					jdbcTemplate.batchUpdate(sql,
							new MyBatchPreparedStatementSetterForCoinDetail(coinAccountBatchList));
					// 单批次导入后清空集合
					coinAccountBatchList.clear();
				} else if (i == size - 1) {
					// 如果size不是500的整数倍，则在遍历到最后一条记录时，批量执行剩下的所有
					jdbcTemplate.batchUpdate(sql,
							new MyBatchPreparedStatementSetterForCoinDetail(coinAccountBatchList));
				}
			}
		}
		logger.info("CoinDetail------需要导入的总记录数为{}", size);
	}

}
