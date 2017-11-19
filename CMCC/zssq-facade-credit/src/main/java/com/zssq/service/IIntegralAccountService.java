package com.zssq.service;

import com.zssq.dao.pojo.IntegralAccount;
import com.zssq.dao.pojo.IntegralAccountList;
import com.zssq.dao.pojo.IntegralAccountListWithType;
import com.zssq.dao.pojo.RankEntity;
import com.zssq.exceptions.BusinessException;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;

/**
 * 
 * @ClassName: IIntegralAccountService  
 * @Description: 积分账户相关的服务  
 * @author power  
 * @date 2017年4月12日  
 *
 */
public interface IIntegralAccountService {

	/**
	 * 
	 * @Title: selectPage  
	 * @Description: 根据accountType(和orgCode)分页查询积分账户表integral_account
	 * @param @param pageParam 分页参数
	 * @param @param record 积分账户实体
	 * @param @return    参数  
	 * @return PageBean    分页查询结果实体  
	 * @throws
	 */
	public PageBean selectPage(PageParam pageParam,IntegralAccount record) throws BusinessException;
	
	/**
	 * 
	 * @Title: selectPage  
	 * @Description: 根据accountType(和orgCode)，
	 * 				   按照积分余额倒序分页查询积分账户表integral_account
	 * @param @param pageParam 分页参数
	 * @param @param record 积分账户实体
	 * @param @return    参数  
	 * @return PageBean    分页查询结果实体
	 * @throws
	 */
	public PageBean selectPageRank(PageParam pageParam,IntegralAccount record) throws BusinessException;
	
	/**
	 * 
	 * @Title: selectByAccountCode  
	 * @Description: 根据账户编号获取账户信息
	 * @param @param accountCode 账户编号
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return IntegralAccount    积分账户实体  
	 * @throws
	 */
	public IntegralAccount selectByAccountCode(String accountCode) throws BusinessException;
	
	/**
	 * 
	 * @Title: insert  
	 * @Description: 添加积分账户
	 * @param @param IntegralAccount 积分账户实体
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return boolean    true-成功，false-失败  
	 * @throws
	 */
	public boolean insert(IntegralAccount integralAccount) throws BusinessException;
	
	/**
	 * 
	 * @Title: updateByCode  
	 * @Description: 修改积分账户
	 * @param @param IntegralAccount 积分账户实体
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return boolean    true-成功，false-失败 
	 * @throws
	 */
	public boolean updateByCode(IntegralAccount integralAccount) throws BusinessException;
	
	/**
	 * 
	 * @Title: selectPageByRank  
	 * @Description: 按照积分倒序排序（若积分相同，则再按照金币倒序排序）
	 * @param @param pageParam 分页参数
	 * @param @param record 班组积分、金币排行榜输入参数实体 
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return PageBean    分页查询结果实体  
	 * @throws
	 */
	public PageBean selectPageByRank(PageParam pageParam, RankEntity record) throws BusinessException;
	
	/**
	 * 
	 * @Title: selectPageByOrgCodes  
	 * @Description: 根据accountCodes查询公司积分账户列表
	 * @param @param pageParam 分页参数
	 * @param @param record 公司的积分账户查询列表实体  
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return PageBean    分页查询结果实体
	 * @throws
	 */
	public PageBean selectPageByOrgCodes(PageParam pageParam, IntegralAccountList record) throws BusinessException;
	
	/**
	 * 
	 * @Title: selectPageByOrgCodes  
	 * @Description: 根据accountCodes和accountType查询个人或班组的积分账户列表
	 * @param @param pageParam 分页参数
	 * @param @param record 个人或班组的积分账户查询列表实体  
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return PageBean    分页查询结果实体
	 * @throws
	 */
	public PageBean selectPageByOrgCodesAndAccountType(PageParam pageParam, IntegralAccountListWithType record) throws BusinessException;
	
}
