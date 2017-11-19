package com.zssq.dao.mapper;

import java.util.List;

import com.zssq.dao.pojo.IntegralAccount;
import com.zssq.dao.pojo.IntegralAccountList;
import com.zssq.dao.pojo.IntegralAccountListWithType;
import com.zssq.dao.pojo.IntegralAndCoin;
import com.zssq.dao.pojo.RankEntity;

/**
 * 
 * @ClassName: IntegralAccountMapper  
 * @Description: 操作积分账户表  integral_account  
 * @author power  
 * @date 2017年4月28日  
 *
 */
public interface IntegralAccountMapper {

	/**
	 * 
	 * @Title: selectByAccountCode  
	 * @Description: 根据账户编号查询积分账户
	 * @param @param accountCode 账户编号
	 * @param @return    参数  
	 * @return IntegralAccount    积分账户实体 
	 * @throws
	 */
	IntegralAccount selectByAccountCode(String accountCode);

	/**
	 * 
	 * @Title: insert  
	 * @Description: 新增积分账户
	 * @param @param integralAccount 积分账户实体
	 * @param @return    参数  
	 * @return int    操作结果  
	 * @throws
	 */
	int insert(IntegralAccount integralAccount);

	/**
	 * 
	 * @Title: updateByAccountCode  
	 * @Description: 修改积分账户
	 * @param @param integralAccount 积分账户实体
	 * @param @return    参数  
	 * @return int    操作结果 
	 * @throws
	 */
	int updateByAccountCode(IntegralAccount integralAccount);

	/**
	 * 
	 * @Title: selectPage  
	 * @Description: 分页查询积分账户列表
	 * @param @param record 积分账户查询实体
	 * @param @return    参数  
	 * @return List<IntegralAccount>    积分账户列表  
	 * @throws
	 */
	List<IntegralAccount> selectPage(IntegralAccount record);

	/**
	 * 
	 * @Title: selectPageRank  
	 * @Description: 分页查询积分账户排行榜
	 * @param @param record 积分账户查询实体
	 * @param @return    参数  
	 * @return List<IntegralAccount>    积分账户列表
	 * @throws
	 */
	List<IntegralAccount> selectPageRank(IntegralAccount record);

	/**
	 * 
	 * @Title: selectPageCount  
	 * @Description: 查询积分账户列表的总记录数
	 * @param @param record 积分账户查询实体
	 * @param @return    参数  
	 * @return int    总记录数  
	 * @throws
	 */
	int selectPageCount(IntegralAccount record);

	/**
	 * 
	 * @Title: sort  
	 * @Description: 查询积分账户列表并排序（先按积分倒序排列，再按金币倒序排列）
	 * @param @param rankEntity 班组积分、金币排行榜输入参数
	 * @param @return    参数  
	 * @return List<IntegralAndCoin>    返回类型  
	 * @throws
	 */
	List<IntegralAndCoin> sort(RankEntity rankEntity);
	
	/**
	 * 
	 * @Title: sortCount  
	 * @Description: 查询积分账户列表并排序（先按积分倒序排列，再按金币倒序排列）的总记录数
	 * @param @param rankEntity 班组积分、金币排行榜输入参数实体
	 * @param @return    参数  
	 * @return int    总记录数  
	 * @throws
	 */
	int sortCount(RankEntity rankEntity);
	
	/**
	 * 
	 * @Title: selectPageByOrgCodes  
	 * @Description: 分页查询公司积分账户列表并按积分倒序排列
	 * @param @param record 公司的积分账户查询列表实体  
	 * @param @return    参数  
	 * @return List<IntegralAccount>    公司的积分账户列表 
	 * @throws
	 */
	List<IntegralAccount> selectPageByOrgCodes(IntegralAccountList record);
	
	/**
	 * 
	 * @Title: selectPageCountByOrgCodes  
	 * @Description: 查询公司积分账户列表并按积分倒序排列的总记录数
	 * @param @param record 公司的积分账户查询列表实体  
	 * @param @return    参数  
	 * @return int    总记录数  
	 * @throws
	 */
	int selectPageCountByOrgCodes(IntegralAccountList record);
	
	/**
	 * 
	 * @Title: selectPageByOrgCodesAndAccountType  
	 * @Description: 分页查询个人或班组积分账户列表并按积分倒序排列
	 * @param @param record 个人或班组的积分账户查询列表实体
	 * @param @return    参数  
	 * @return List<IntegralAccount>    个人或班组的积分账户列表
	 * @throws
	 */
	List<IntegralAccount> selectPageByOrgCodesAndAccountType(IntegralAccountListWithType record);
	
	/**
	 * 
	 * @Title: selectPageCountByOrgCodesAndAccountType  
	 * @Description: 查询个人或班组积分账户列表并按积分倒序排列的总记录数
	 * @param @param record 个人或班组的积分账户查询列表实体
	 * @param @return    参数  
	 * @return int    总记录数
	 * @throws
	 */
	int selectPageCountByOrgCodesAndAccountType(IntegralAccountListWithType record);

}
