package com.zssq.mblog.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zssq.datasource.DataSource;
import com.zssq.datasource.DataSourceConstants;
import com.zssq.mblog.vo.Db2AtVo;
import com.zssq.mblog.vo.Db2CommentVo;
import com.zssq.mblog.vo.Db2MblogVo;
import com.zssq.mblog.vo.Db2SubVo;
import com.zssq.mblog.vo.Db2TopicVo;

@Service
@DataSource(DataSourceConstants.DB2_MBLOG)
public class MblogIBMService {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	
	/**
	 * 
	 * @Title: getDb2MblogPageRow  
	 * @Description: 获取微博分页数据
	 * @param pageNo
	 * @param pageSize
	 * @param actionType
	 * @return List<Db2MblogVo>    返回类型
	 */
	public List<Db2MblogVo> getDb2MblogPageRow(int pageNo,int pageSize,int actionType){
		Object[] param = new Object[2];
		param[0] = pageNo*pageSize;
		param[1] = pageSize;
		List<Db2MblogVo> mblogList = jdbcTemplate.query(Db2MblogVo.getMySqlStatement(actionType), param, new BeanPropertyRowMapper<Db2MblogVo>(Db2MblogVo.class));
		return mblogList;
	}
	
	/**
	 * 
	    * @Title: getDb2MblogTotal  
	    * @Description: 获取微博总数
	    * @param actionType
		* @return int    返回类型
	 */
	public int getDb2MblogTotal(int actionType){
		int count = jdbcTemplate.queryForObject(Db2MblogVo.getCountStatement(actionType), new Object[]{}, Integer.class);
		return count;
	}
	
	
	/**
	 * 
	 * @Title: getDb2CommentPageRow  
	 * @Description: 获取评论分页数据
	 * @param pageNo		页码
	 * @param pageSize	每页条数
	 * @return List<Db2CommentVo>    返回类型
	 */
	public List<Db2CommentVo> getDb2CommentPageRow(int pageNo,int pageSize){
		Object[] param = new Object[2];
		param[0] = pageNo*pageSize;
		param[1] = pageSize;
		List<Db2CommentVo> commentList = jdbcTemplate.query(Db2CommentVo.getMySqlStatement(), param, new BeanPropertyRowMapper<Db2CommentVo>(Db2CommentVo.class));
		return commentList;
	}
	
	/**
	 * 
	    * @Title: getDb2CommentTotal  
	    * @Description: 获取评论总数
		* @return int    返回类型
	 */
	public int getDb2CommentTotal(){
		int count = jdbcTemplate.queryForObject(Db2CommentVo.getCountStatement(), new Object[]{}, Integer.class);
		return count;
	}
	
	
	/**
	 * 
	    * @Title: getDb2TopicPageRow  
	    * @Description: 获取话题分页数据
	    * @param pageNo		页码
	    * @param pageSize	每页条数
		* @return List<Db2TopicVo>    返回类型
	 */
	public List<Db2TopicVo> getDb2TopicPageRow(int pageNo,int pageSize){
		Object[] param = new Object[2];
		param[0] = pageNo*pageSize;
		param[1] = pageSize;
		List<Db2TopicVo> topicList = jdbcTemplate.query(Db2TopicVo.getMySqlStatement(), param, new BeanPropertyRowMapper<Db2TopicVo>(Db2TopicVo.class));
		return topicList;
	}
	
	/**
	 * 
	    * @Title: getDb2TopicTotal  
	    * @Description: 获取话题总数
		* @return int    返回类型
	 */
	public int getDb2TopicTotal(){
		int count = jdbcTemplate.queryForObject(Db2TopicVo.getCountStatement(), new Object[]{}, Integer.class);
		return count;
	}
	
	
	/**
	 * 
	    * @Title: getDb2AtPageRow  
	    * @Description: 获取at分页数据
	    * @param pageNo
	    * @param pageSize
		* @return List<Db2AtVo>    返回类型
	 */
	public List<Db2AtVo> getDb2AtPageRow(int pageNo,int pageSize){
		Object[] param = new Object[2];
		param[0] = pageNo*pageSize;
		param[1] = pageSize;
		List<Db2AtVo> atList = jdbcTemplate.query(Db2AtVo.getMySqlStatement(), param, new BeanPropertyRowMapper<Db2AtVo>(Db2AtVo.class));
		return atList;
	}
	
	/**
	 * 
	    * @Title: getDb2AtTotal  
	    * @Description: 获取AT总数
		* @return int    返回类型
	 */
	public int getDb2AtTotal(){
		int count = jdbcTemplate.queryForObject(Db2AtVo.getCountStatement(), new Object[]{}, Integer.class);
		return count;
	}

	
	/**
	 * 
	    * @Title: getDb2SubPageRow  
	    * @Description: 获取订阅分页列表
	    * @param pageNo
	    * @param pageSize
		* @return List<Db2SubVo>    返回类型
	 */
	@DataSource(DataSourceConstants.DB2_PORTALDB)
	public List<Db2SubVo> getDb2SubPageRow(int pageNo,int pageSize){
		Object[] param = new Object[2];
		param[0] = pageNo*pageSize;
		param[1] = pageSize;
		List<Db2SubVo> subList = jdbcTemplate.query(Db2SubVo.getMySqlStatement(), param, new BeanPropertyRowMapper<Db2SubVo>(Db2SubVo.class));
		return subList;
	}
	
	/**
	 * 
	    * @Title: getDb2SubTotal  
	    * @Description: 获取sub总数
		* @return int    返回类型
	 */
	@DataSource(DataSourceConstants.DB2_PORTALDB)
	public int getDb2SubTotal(){
		int count = jdbcTemplate.queryForObject(Db2SubVo.getCountStatement(), new Object[]{}, Integer.class);
		return count;
	}
	
} 
