package com.zssq.core.dao;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SqlRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.druid.pool.DruidDataSource;
import com.zssq.exceptions.BizException;
import com.zssq.pojo.BaseEntity;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;

/**
 * 
 * @描述: 数据访问层基础支撑类.
 * @版本: 1.0 .
 * @param <T>
 */
public abstract class BaseDaoImpl<T extends BaseEntity> extends SqlSessionDaoSupport implements BaseDao<T> {

	protected static final Logger log = LoggerFactory.getLogger(BaseDaoImpl.class);

	public static final String SQL_INSERT = "insert";
	public static final String SQL_BATCH_INSERT = "batchInsert";
	public static final String SQL_GET_BY_ID = "getById";
	public static final String SQL_LIST_BY = "listBy";
	public static final String SQL_USER_CODE = "getByUserCode";
	public static final String SQL_USER_CODES = "getByUserCodes";
	public static final String SQL_LIST_PAGE = "listPage";
	public static final String SQL_LIST_PAGE_COUNT = "listPageCount";
	public static final String SQL_COUNT_BY_PAGE_PARAM = "countByPageParam"; // 根据当前分页参数进行统计

	/**
	 * 注入SqlSessionTemplate实例(要求Spring中进行SqlSessionTemplate的配置).<br/>
	 * 可以调用sessionTemplate完成数据库操作.
	 */
	@Autowired
	private SqlSessionTemplate sessionTemplate;

	@Autowired
	protected SqlSessionFactory sqlSessionFactory;

	@Autowired
	private DruidDataSource druidDataSource;

	public SqlSessionTemplate getSessionTemplate() {
		return sessionTemplate;
	}

	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}

	public SqlSession getSqlSession() {
		return super.getSqlSession();
	}

	public long insert(T t) {

		if (t == null)
			throw new RuntimeException("T is null");

		int result = sessionTemplate.insert(getStatement(SQL_INSERT), t);

		if (result <= 0)
			throw BizException.DB_INSERT_RESULT_0;
		// TODO 后期决定
		// if (t != null && t.getId() != null && result > 0)
		// return t.getId();

		return result;
	}

	public long insert(List<T> list) {

		if (list == null || list.size() <= 0)
			return 0;

		int result = sessionTemplate.insert(getStatement(SQL_BATCH_INSERT), list);

		if (result <= 0)
			throw BizException.DB_INSERT_RESULT_0;

		return result;
	}

	public T getById(long id) {
		return sessionTemplate.selectOne(getStatement(SQL_GET_BY_ID), id);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> listBy(Map<String, Object> paramMap) {
		return (List) this.listBy(paramMap, SQL_LIST_BY);
	}

	public List<Object> listBy(Map<String, Object> paramMap, String sqlId) {
		if (paramMap == null)
			paramMap = new HashMap<String, Object>();

		return sessionTemplate.selectList(getStatement(sqlId), paramMap);
	}

	@SuppressWarnings("unchecked")
	public T getBy(Map<String, Object> paramMap) {
		return (T) this.getBy(paramMap, SQL_LIST_BY);
	}

	public Object getBy(Map<String, Object> paramMap, String sqlId) {
		if (paramMap == null || paramMap.isEmpty())
			return null;

		return this.getSqlSession().selectOne(getStatement(sqlId), paramMap);
	}
	@SuppressWarnings("unchecked")
	public T getByUserCode(Map<String, Object> paramMap) {
		return (T) this.getBy(paramMap, SQL_USER_CODE);
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> getByUserCodes(Map<String, Object> paramMap) {
		return (List) this.listBy(paramMap, SQL_USER_CODES);
	}
	
	
	public String getStatement(String sqlId) {

		String name = this.getClass().getName();

		StringBuffer sb = new StringBuffer().append(name).append(".").append(sqlId);

		return sb.toString();
	}
	/**
	 * 分页查询 .
	 */
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap<String, Object>();
		}

		// 根据页面传来的分页参数构造SQL分页参数
		paramMap.put("pageFirst", (pageParam.getPageNo() - 1) * pageParam.getPageSize());
		paramMap.put("pageSize", pageParam.getPageSize());
		paramMap.put("startRowNum", (pageParam.getPageNo() - 1) * pageParam.getPageSize());
		paramMap.put("endRowNum", pageParam.getPageNo() * pageParam.getPageSize());

		// 统计总记录数
		Long count = sessionTemplate.selectOne(getStatement(SQL_LIST_PAGE_COUNT), paramMap);

		// 获取分页数据集
		List<Object> list = sessionTemplate.selectList(getStatement(SQL_LIST_PAGE), paramMap);

		// 构造分页对象
		return new PageBean(pageParam.getPageNo(), pageParam.getPageSize(), count.intValue(), list);
	}

	
	public long listCount(Map<String, Object> paramMap) {
		return sessionTemplate.selectOne(getStatement(SQL_LIST_PAGE_COUNT), paramMap);
	}
	/**
	 * 取得时间 戳
	 */
	public Long getTimeSign(String tableName, String keyName) {
		boolean isClosedConn = false;
		// 获取当前线程的连接
		Connection connection = this.sessionTemplate.getConnection();
		// 获取Mybatis的SQLRunner类
		SqlRunner sqlRunner = null;
		try {
			// 要执行的SQL
			String sql = "";
			// 数据库驱动类
			String driverClass = druidDataSource.getDriver().getClass().getName();
			// 不同的数据库,拼接SQL语句
			if (driverClass.equals("com.mysql.jdbc.Driver")) {
				sql = "select  MAX(TIME_SIGN)TIME_SIGN from+ " 
				 + tableName + " where " +tableName+"_code = " +keyName ;
			}
			// 如果状态为关闭,则需要从新打开一个连接
			if (connection.isClosed()) {
				connection = sqlSessionFactory.openSession().getConnection();
				isClosedConn = true;
			}
			sqlRunner = new SqlRunner(connection);
			Object[] args = {};
			// 执行SQL语句
			Map<String, Object> params = sqlRunner.selectOne(sql, args);
			for (Object o : params.values()) {
				return Long.parseLong(o.toString());
			}
			return null;
		} catch (Exception e) {

		} finally {
			if (isClosedConn) {
				sqlRunner.closeConnection();
			}
		}
		return null;
	}

}
