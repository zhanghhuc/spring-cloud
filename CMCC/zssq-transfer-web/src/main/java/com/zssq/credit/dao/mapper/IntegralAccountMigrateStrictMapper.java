package com.zssq.credit.dao.mapper;

import java.util.List;

import com.zssq.credit.pojo.IntegralAccountMigrateStrict;

public interface IntegralAccountMigrateStrictMapper {
	
	void batchInsert(List<IntegralAccountMigrateStrict> list);
	
}