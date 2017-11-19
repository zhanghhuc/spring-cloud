package com.zssq.credit.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.credit.pojo.IntegralAccount;

public interface IntegralAccountMapper {
	
	void batchInsert(List<IntegralAccount> list);
	
	List<IntegralAccount> selectPage(Map<String,Object> map);
	
	int selectPageCount();
	
}