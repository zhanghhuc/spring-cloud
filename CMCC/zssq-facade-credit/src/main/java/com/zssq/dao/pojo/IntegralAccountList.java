package com.zssq.dao.pojo;

import java.util.List;

import com.zssq.pojo.BasePage;

/**
 * 
 * @ClassName: IntegralAccount  
 * @Description: 公司的积分账户查询列表实体  
 * @author power  
 * @date 2017年4月27日  
 *
 */
public class IntegralAccountList extends BasePage {

	private static final long serialVersionUID = 1L;

	/** 积分账户编号集合 */
	private List<String> accountCodes;

	public List<String> getAccountCodes() {
		return accountCodes;
	}

	public void setAccountCodes(List<String> accountCodes) {
		this.accountCodes = accountCodes;
	}

}
