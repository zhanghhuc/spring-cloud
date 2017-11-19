package com.zssq.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;


/**
 * 
 * @描述: 数据访问层基础支撑接口.
 * @版本: 1.0 .
 * @param <T>
 */
public interface BaseDao<T> {

	/**
	 * 根据实体对象新增记录.
	 * 
	 * @param entity
	 *            .
	 * @return id .
	 */
	long insert(T entity);

	/**
	 * 批量保存对象.
	 * 
	 * @param entity
	 *            .
	 * @return id .
	 */
	long insert(List<T> list);


	/**
	 * 根据ID查找记录.
	 * 
	 * @param id
	 *            .
	 * @return entity .
	 */
	T getById(long id);


	/**
	 * 根据条件查询 listBy: <br/>
	 * 
	 * @param paramMap
	 * @return 返回集合
	 */
	List<T> listBy(Map<String, Object> paramMap);

	List<Object> listBy(Map<String, Object> paramMap, String sqlId);
	/**
	 * 根据条件查询 listBy: <br/>
	 * 
	 * @param paramMap
	 * @return 返回实体
	 */
	T getBy(Map<String, Object> paramMap);

	Object getBy(Map<String, Object> paramMap, String sqlId);
	/**
	 * 根据条件查询 getByUserCode: <br/>
	 * 
	 * @param paramMap
	 * @return 返回实体
	 */
	T getByUserCode(Map<String, Object> paramMap);
	
	/**
	 * 根据条件查询 getByUserCode: <br/>
	 * 
	 * @param paramMap
	 * @return 返回实体
	 */
	List<T> getByUserCodes(Map<String, Object> paramMap);
	/**
	 * 
	    * @Title: getSeqNextValue  
	    * @Description: 取得时间戳
	    * @param @param seqName
	    * @param @return    参数  
	    * @return String    返回类型  
	    * @throws
	 */
	Long getTimeSign(String tableName, String keyName);

	/**
	 * 
	    * @Title: listPage  
	    * @Description: 分页查询 .
	    * @param @param pageParam
	    * @param @param paramMap
	    * @param @return    参数  
	    * @return PageBean    返回类型  
	    * @throws
	 */
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap);

	public long listCount(Map<String, Object> paramMap);
	
	SqlSessionTemplate getSessionTemplate();

	SqlSession getSqlSession();
}
