package com.zssq.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zssq.datasource.DataSource;
import com.zssq.datasource.DataSourceConstants;

/**
 * 
 * @ClassName: BlogDataService  
 * @Description: 数据迁移过程数量修改Service  
 * @author ZKZ  
 * @date 2017年5月23日  
 *
 */
@Service("blogDataService")
@DataSource(DataSourceConstants.MYSQL_BLOG)
public class BlogDataService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 
	 * @Title: getMySQLBlogDataMaxId  
	 * @Description: 查询MySQL库中博客数据表id的最大值
	 * @return: int    返回类型
	 */
	@DataSource(DataSourceConstants.MYSQL_BLOG)
	public int getMySQLBlogDataMaxId() {
		// 拼接SQL
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("MAX(id) ");
		sql.append("FROM ");
		sql.append("blog_data ");
		
		// 查询
		return jdbcTemplate.queryForObject(sql.toString(), Integer.class);
	}
	
	/**
	 * 
	 * @Title: getMySQLBlogCommentMaxId  
	 * @Description: 查询MySQL库中评论表id的最大值
	 * @return: int    返回类型
	 */
	@DataSource(DataSourceConstants.MYSQL_BLOG)
	public int getMySQLBlogCommentMaxId() {
		// 拼接SQL
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("MAX(id) ");
		sql.append("FROM ");
		sql.append("blog_comment ");
		
		// 查询
		return jdbcTemplate.queryForObject(sql.toString(), Integer.class);
	}
	
	/**
	 * 
	 * @Title: getMySQLBlogClassMaxId  
	 * @Description: 查询MySQL库中分类表id的最大值
	 * @return: int    返回类型
	 */
	@DataSource(DataSourceConstants.MYSQL_BLOG)
	public int getMySQLBlogClassMaxId() {
		// 拼接SQL
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("MAX(id) ");
		sql.append("FROM ");
		sql.append("blog_class ");
		
		// 查询
		return jdbcTemplate.queryForObject(sql.toString(), Integer.class);
	}
	
	/**
	 * 
	 * @Title: updateClassBlogNum  
	 * @Description: 更新分类下博客数
	 * @param pageNo
	 * @param pageSize
	 * @return: int    返回类型
	 */
	public int updateClassBlogNum(int pageNo, int pageSize) {
		// 拼接SQL
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE blog_class bc ");
		sql.append("SET bc.class_blog_num = ( ");
		sql.append("SELECT ");
		sql.append("count(*) ");
		sql.append("FROM ");
		sql.append("blog_info bi ");
		sql.append("WHERE ");
		sql.append("bi.class_code = bc.class_code ");
		sql.append(") ");
		sql.append("WHERE ");
		sql.append("bc.id BETWEEN ? AND ? ");
		
		// 拼接参数
		Object[] params = new Object[2];
		params[0] = pageNo * pageSize + 1;
		params[1] = pageNo * pageSize + pageSize;
		
		// 查询
		return jdbcTemplate.update(sql.toString(), params);
	}
	
	/**
	 * 
	 * @Title: updateBlogCommentNum  
	 * @Description: 更新博客评论数
	 * @param pageNo
	 * @param pageSize
	 * @return: int    返回类型
	 */
	public int updateBlogCommentNum(int pageNo, int pageSize) {
		// 拼接SQL
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE blog_data bd ");
		sql.append("SET bd.comment_num = ( ");
		sql.append("SELECT ");
		sql.append("count(*) ");
		sql.append("FROM ");
		sql.append("blog_comment bc ");
		sql.append("WHERE ");
		sql.append("bc.blog_code = bd.blog_code ");
		sql.append(") ");
		sql.append("WHERE ");
		sql.append("bd.id BETWEEN ? AND ? ");
		
		// 拼接参数
		Object[] params = new Object[2];
		params[0] = pageNo * pageSize + 1;
		params[1] = pageNo * pageSize + pageSize;
		
		// 查询
		return jdbcTemplate.update(sql.toString(), params);
	}
	
	/**
	 * 
	 * @Title: updateCommentReplyNum  
	 * @Description: 更新评论回复数
	 * @param pageNo
	 * @param pageSize
	 * @return: int    返回类型
	 */
	public int updateCommentReplyNum(int pageNo, int pageSize) {
		// 拼接SQL
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE blog_comment bc ");
		sql.append("SET bc.comment_reply_num = ( ");
		sql.append("SELECT ");
		sql.append("count(*) ");
		sql.append("FROM ");
		sql.append("blog_reply br ");
		sql.append("WHERE ");
		sql.append("br.comment_code = bc.comment_code ");
		sql.append(") ");
		sql.append(") ");
		sql.append("WHERE ");
		sql.append("bc.id BETWEEN ? AND ? ");
		
		// 拼接参数
		Object[] params = new Object[2];
		params[0] = pageNo * pageSize + 1;
		params[1] = pageNo * pageSize + pageSize;
		
		// 查询
		return jdbcTemplate.update(sql.toString(), params);
	}
	
}
