package com.zssq.service;

import java.util.List;

import com.zssq.dao.pojo.ActivityHistory;
import com.zssq.dao.pojo.ActivityInfo;
import com.zssq.dao.pojo.ActivityInfoWithBLOBs;
import com.zssq.dao.pojo.ActivityJoin;
import com.zssq.dao.pojo.ActivityJoinAdjunct;
import com.zssq.dao.pojo.ActivityPrize;
import com.zssq.dao.pojo.ActivityWinningRecord;
import com.zssq.exceptions.BusinessException;
import com.zssq.utils.PageInfo;

/**
 * 
    * @ClassName: IActivityModelTwoService  
    * @Description: 活动管理模块接口2
    * @author liuzhijie  
    * @date 2017年4月18日  
    *
 */
public interface IActivityModelTwoService {
	
	/**
	 * 
	 * @Title: updateActivity  
	 * @Description: 提交或撤销审核
	 * @param act 活动信息
	 * @param winningType 操作类型
	 * @throws BusinessException     
	 * @return void    返回类型 
	 */
	public void updateSubmitRevoke(ActivityInfoWithBLOBs act , String winningType) throws BusinessException;
	
	/**
	 * 
	 * @Title: getActivityHistoryList  
	 * @Description: 查询活动历程列表
	 * @param history 活动历程对象
	 * @param pageNo 第几页
	 * @param pageSize 每页大小
	 * @return PageInfo
	 * @throws BusinessException
	 */
	public PageInfo getActivityHistoryList(ActivityHistory history , String pageNo , String pageSize) 
			throws BusinessException;
	
	/**
	 * 
	 * @Title: updateExaminingActivity  
	 * @Description: 审核活动（通过/驳回
	 * @param act 活动对象
	 * @throws BusinessException
	 */
	public ActivityInfoWithBLOBs updateExaminingActivity(ActivityInfoWithBLOBs act) throws BusinessException;
	
	/**
	 * 
	 * @Title: updateAdvanceOfflineTime  
	 * @Description: 提前下线
	 * @param act
	 * @throws BusinessException
	 */
	public void updateAdvanceOfflineTime(ActivityInfoWithBLOBs act) throws BusinessException;
	
	/**
	 * 
	 * @Title: getActivityJoinList  
	 * @Description: 查询活动参与记录列表
	 * @param actJoin 活动参与对象
	 * @param pageNo 第几页
	 * @param pageSize 每页大小
	 * @param queryType 查询类型 1-未评分作品 2-已评分作品 3-获奖作品
	 * @return
	 * @throws BusinessException
	 */
	public PageInfo getActivityJoinList(ActivityJoin actJoin, String pageNo , String pageSize , String queryType) 
			throws BusinessException;
	
	/**
	 * 
	 * @Title: getActivityJoin  
	 * @Description: 查询活动参与记录并修改读取状态
	 * @param join
	 * @return
	 * @throws BusinessException
	 */
	public ActivityJoin updateActivityJoin(ActivityJoin join) throws BusinessException;
	
	/**
	 * 
	 * @Title: getActivityJoinAdjunctList  
	 * @Description: 查询活动参与作品列表
	 * @param adjunct
	 * @return
	 * @throws BusinessException
	 */
	public List<ActivityJoinAdjunct> getActivityJoinAdjunctList(ActivityJoinAdjunct adjunct) throws BusinessException;
	
	/**
	 * 
	 * @Title: updateActivityJoinScore  
	 * @Description: 给作品评分
	 * @param join
	 * @throws BusinessException
	 */
	public void updateActivityJoinScore(ActivityJoin join) throws BusinessException;
	
	/**
	 * 
	 * @Title: getPortalActivityList  
	 * @Description: 获取门户页面活动列表
	 * @param activity
	 * @return
	 * @throws BusinessException
	 */
	public List<ActivityInfo> getPortalActivityList(ActivityInfo activity, String pageSize) throws BusinessException;
	
	/**
	 * 
	 * @Title: queryActivityDetail  
	 * @Description: 查询活动详情
	 * @param activityCode 活动编号
	 * @param activityType 活动类型
	 * @param userCode 用户编号
	 * @return
	 * @throws BusinessException
	 */
	public ActivityInfoWithBLOBs queryActivityDetail(String activityCode, String activityType,String userCode,String tenantCode) throws BusinessException;
	
	/**
	 * 
	 * @Title: getActivityList  
	 * @Description: 查询活动列表
	 * @param activity
	 * @param page
	 * @return
	 * @throws BusinessException
	 */
	public List<ActivityInfo> getActivityList(ActivityInfo activity, PageInfo page) throws BusinessException;
	
	/**
	 * 
	 * @Title: joinActivity  
	 * @Description: 参加活动
	 * @param adjunct
	 * @param userCode
	 * @param userOrgCode
	 * @throws BusinessException
	 */
	public void insertJoinActivity(ActivityJoinAdjunct adjunct, ActivityJoin join) throws BusinessException;
	
	/**
	 * 
	 * @Title: getAwardeeList  
	 * @Description: 查询获奖人列表
	 * @param activityCode
	 * @return
	 * @throws BusinessException
	 */
	public List<ActivityWinningRecord> getAwardeeList(String activityCode) throws BusinessException;
	
	/**
	 * 
	 * @Title: getJoinPrizeList
	 * @Description: 查询活动的参与奖
	 * @param activityPrize
	 * @return
	 * @throws BusinessException
	 */
	public ActivityPrize getJoinPrize(ActivityPrize activityPrize) throws BusinessException;
	
	/**
	 * 
	 * @Title: insertPrize
	 * @Description: 插入获奖记录
	 * @param prize
	 * @return
	 * @throws BusinessException
	 */
	public int insertWinningRecord(ActivityWinningRecord winning) throws BusinessException;
	
	/**
	 * 
	 * @Title: updateWinningRecord
	 * @Description: 更新获奖记录
	 * @param winning
	 * @return
	 * @throws BusinessException
	 */
	public int updateWinningRecord(ActivityWinningRecord winning) throws BusinessException;
	
	/**
	 * 
	 * @Title: getActivityListForMonitor
	 * @Description: 内容监控-查询活动列表
	 * @param orgCode
	 * @param tenantCode
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws BusinessException
	 */
	public PageInfo getActivityListForMonitor(String orgCode, String tenantCode, String pageNo, String pageSize) throws BusinessException;
	
}
