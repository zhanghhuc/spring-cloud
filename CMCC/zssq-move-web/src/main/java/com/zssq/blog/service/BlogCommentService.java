package com.zssq.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zssq.blog.vo.DB2BlogReply;
import com.zssq.blog.vo.MySQLBlogComment;
import com.zssq.datasource.DataSource;
import com.zssq.datasource.DataSourceConstants;

/**
 * 
 * @ClassName: BlogCommentService  
 * @Description: 评论数据迁移Service  
 * @author ZKZ  
 * @date 2017年5月22日  
 *
 */
@Service("blogCommentService")
public class BlogCommentService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 
	 * @Title: getSourceDB2CommentCount  
	 * @Description: 查询DB2库中评论数据总个数
	 * @return: int    返回类型
	 */
	@DataSource(DataSourceConstants.DB2_BLOG)
	public int getSourceDB2CommentCount() {
		// 拼接SQL
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("COUNT(*) ");
		sql.append("FROM ");
		sql.append("blog_reply ");
		sql.append("WHERE ");
		sql.append("del_flag = 0 ");
		sql.append("AND ");
		sql.append("flag_reply = 0 ");
			
		// 查询
		return jdbcTemplate.queryForObject(sql.toString(), Integer.class);
	}
	
	@DataSource(DataSourceConstants.DB2_BLOG)
	public int getSourceMySQLCommentCount() {
		// 拼接SQL
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("COUNT(*) ");
		sql.append("FROM ");
		sql.append("blog_reply ");
		sql.append("WHERE ");
		sql.append("del_flag = 0 ");
		sql.append("AND ");
		sql.append("flag_reply = 0 ");
			
		// 查询
		return jdbcTemplate.queryForObject(sql.toString(), Integer.class);
	}
	
	/**
	 * 
	 * @Title: getSourceDB2CommentList  
	 * @Description: 查询DB2库中评论数据
	 * @param pageNo
	 * @param pageSize
	 * @return: List<DB2BlogReply>    返回类型
	 */
	@DataSource(DataSourceConstants.DB2_BLOG)
	public List<DB2BlogReply> getSourceDB2CommentList(int pageNo, int pageSize) {
		// 拼接SQL
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("br.id AS id, ");
		sql.append("br.blog_id AS blogId, ");
		sql.append("br.user_id AS userId, ");
		sql.append("br.create_time AS createTime, ");
		sql.append("br.update_time AS updateTime, ");
		sql.append("br.reply_content AS replyContent ");
		sql.append("FROM ");
		sql.append("( ");
		sql.append("SELECT ");
		sql.append("id, ");
		sql.append("blog_id, ");
		sql.append("user_id, ");
		sql.append("create_time, ");
		sql.append("update_time, ");
		sql.append("reply_content, ");
		sql.append("rownumber() over () AS rn ");
		sql.append("FROM blog_reply ");
		sql.append("WHERE ");
		sql.append("del_flag = 0 ");
		sql.append("AND ");
		sql.append("flag_reply = 0 ");
		sql.append("ORDER BY id ASC ");
		sql.append(") br ");
		sql.append("WHERE ");
		sql.append("br.rn BETWEEN ? AND ? ");

		// 拼接参数
		Object[] params = new Object[2];
		params[0] = pageNo * pageSize + 1;
		params[1] = pageNo * pageSize + pageSize;
		
		// 查询
		return jdbcTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<DB2BlogReply>(DB2BlogReply.class));
	}
	
	@DataSource(DataSourceConstants.DB2_BLOG)
	public List<DB2BlogReply> getSourceMySQLCommentList(int pageNo, int pageSize) {
		// 拼接SQL
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("id AS id, ");
		sql.append("blog_id AS blogId, ");
		sql.append("user_id AS userId, ");
		sql.append("create_time AS createTime, ");
		sql.append("update_time AS updateTime, ");
		sql.append("reply_content AS replyContent ");
		sql.append("FROM ");
		sql.append("blog_reply ");
		sql.append("WHERE ");
		sql.append("del_flag = 0 ");
		sql.append("AND flag_reply = 0 ");
		sql.append("ORDER BY ");
		sql.append("id ASC ");
		sql.append("LIMIT ?, ? ");
		
		// 拼接参数
		Object[] params = new Object[2];
		params[0] = pageNo * pageSize;
		params[1] = pageSize;
		
		// 查询
		return jdbcTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<DB2BlogReply>(DB2BlogReply.class));
	}
	
	/**
	 * 
	 * @Title: insertMySQLCommentList  
	 * @Description: 批量向MySQL库中插入评论数据
	 * @param mySQLCommentList
	 * @throws Exception    参数  
	 * @return: boolean    返回类型
	 */
	@DataSource(DataSourceConstants.MYSQL_BLOG)
	public boolean insertMySQLCommentList(List<MySQLBlogComment> mySQLCommentList) throws Exception {
		// 参数校验
		if (mySQLCommentList == null || mySQLCommentList.isEmpty()) {
			return false;
		}
		
		// 获取总量
		int size = mySQLCommentList.size();
		
		// 拼接评论SQL
		StringBuffer commentSql = new StringBuffer();
		commentSql.append("INSERT INTO blog_comment ");
		commentSql.append("(comment_code, ");
		commentSql.append("tenant_code, ");
		commentSql.append("org_code, ");
		commentSql.append("create_time, ");
		commentSql.append("modify_time, ");
		commentSql.append("remark, ");
		commentSql.append("blog_code, ");
		commentSql.append("user_code, ");
		commentSql.append("comment_content, ");
		commentSql.append("comment_is_delete, ");
		commentSql.append("comment_is_shield, ");
		commentSql.append("comment_like_num, ");
		commentSql.append("comment_reply_num) ");
		commentSql.append("VALUES ");
		
		// 评论参数
		Object[] commentParams = new Object[13 * size];
		
		// 拼接评论临时表SQL
		StringBuffer tempSql = new StringBuffer();
		tempSql.append("INSERT INTO blog_comment_temp ");
		tempSql.append("(old_id, ");
		tempSql.append("comment_code) ");
		tempSql.append("VALUES ");
		
		// 临时表参数
		Object[] tempParams = new Object[2 * size];
		
		for (int i = 0; i < size; i++) {
			MySQLBlogComment mySQLBlogComment = mySQLCommentList.get(i);
			
			// 评论表
			if (i != 0) {
				commentSql.append("), ");
			}
			commentSql.append("(? ");
//			commentSql.append("\"" + mySQLBlogComment.getCommentCode() + "\"");
			commentParams[i * 13] = mySQLBlogComment.getCommentCode();
			commentSql.append(",? ");
//			commentSql.append("\"" + mySQLBlogComment.getTenantCode() + "\"");
			commentParams[i * 13 + 1] = mySQLBlogComment.getTenantCode();
			commentSql.append(",? ");
//			commentSql.append("\"" + mySQLBlogComment.getOrgCode() + "\"");
			commentParams[i * 13 + 2] = mySQLBlogComment.getOrgCode();
			commentSql.append(",? ");
//			commentSql.append("\"" + mySQLBlogComment.getCreateTime() + "\"");
			commentParams[i * 13 + 3] = mySQLBlogComment.getCreateTime();
			commentSql.append(",? ");
//			commentSql.append("\"" + mySQLBlogComment.getModifyTime() + "\"");
			commentParams[i * 13 + 4] = mySQLBlogComment.getModifyTime();
			commentSql.append(",? ");
//			commentSql.append("\"" + mySQLBlogComment.getRemark() + "\"");
			commentParams[i * 13 + 5] = mySQLBlogComment.getRemark();
			commentSql.append(",? ");
//			commentSql.append("\"" + mySQLBlogComment.getBlogCode() + "\"");
			commentParams[i * 13 + 6] = mySQLBlogComment.getBlogCode();
			commentSql.append(",? ");
//			commentSql.append("\"" + mySQLBlogComment.getUserCode() + "\"");
			commentParams[i * 13 + 7] = mySQLBlogComment.getUserCode();
			commentSql.append(",? ");
//			commentSql.append("\"" + mySQLBlogComment.getCommentContent() + "\"");
			commentParams[i * 13 + 8] = mySQLBlogComment.getCommentContent();
			commentSql.append(",? ");
//			commentSql.append("\"" + mySQLBlogComment.getCommentIsDelete() + "\"");
			commentParams[i * 13 + 9] = mySQLBlogComment.getCommentIsDelete();
			commentSql.append(",? ");
//			commentSql.append("\"" + mySQLBlogComment.getCommentIsShield() + "\"");
			commentParams[i * 13 + 10] = mySQLBlogComment.getCommentIsShield();
			commentSql.append(",? ");
//			commentSql.append("\"" + mySQLBlogComment.getCommentLikeNum() + "\"");
			commentParams[i * 13 + 11] = mySQLBlogComment.getCommentLikeNum();
			commentSql.append(",? ");
//			commentSql.append("\"" + mySQLBlogComment.getCommentReplyNum() + "\"");
			commentParams[i * 13 + 12] = mySQLBlogComment.getCommentReplyNum();
			
			// 临时表
			if (i != 0) {
				tempSql.append("), ");
			}
			tempSql.append("(? ");
//			tempSql.append("\"" + mySQLBlogComment.getOldId() + "\"");
			tempParams[i * 2] = mySQLBlogComment.getOldId();
			tempSql.append(",? ");
//			tempSql.append("\"" + mySQLBlogComment.getBlogCode() + "\"");
			tempParams[i * 2 + 1] = mySQLBlogComment.getBlogCode();
		}
		commentSql.append(") ");
		tempSql.append(") ");
		
		// 插入评论
		int updateBlogNum = jdbcTemplate.update(commentSql.toString(), commentParams);
		if (updateBlogNum <= 0) {
			throw new Exception();
		} 
		
		// 插入临时表
		int updateBlogTempNum = jdbcTemplate.update(tempSql.toString(), tempParams);
		if (updateBlogTempNum <= 0) {
			throw new Exception();
		} 
		
		return true;
	}
	
	/**
	 * 
	 * @Title: createMySQLCommentTemp  
	 * @Description: 创建评论临时表
	 * @return: void    返回类型
	 */
	@DataSource(DataSourceConstants.MYSQL_BLOG)
	public void createMySQLCommentTemp() {
		// 查询是否存在该表
		StringBuffer selectSql = new StringBuffer();
		selectSql.append("SELECT ");
		selectSql.append("table_name ");
		selectSql.append("FROM ");
		selectSql.append("information_schema. TABLES ");
		selectSql.append("WHERE ");
		selectSql.append("table_name = 'blog_comment_temp' ");
		List<String> tableNameList = jdbcTemplate.queryForList(selectSql.toString(), String.class);
		if (tableNameList != null && !tableNameList.isEmpty()) {
			return;
		}
		
		// 拼接SQL
		StringBuffer sql = new StringBuffer();
		sql.append("CREATE TABLE blog_comment_temp ( ");
		sql.append("old_id  INTEGER NOT NULL, ");
		sql.append("comment_code  varchar(32) NOT NULL, ");
		sql.append("PRIMARY KEY (old_id) ");
		sql.append(") ");
		sql.append("ENGINE=InnoDB ");
		sql.append("DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci ");
		sql.append("ROW_FORMAT=COMPACT ");
		
		// 创建表
		jdbcTemplate.execute(sql.toString());
	}
	
	/**
	 * 
	 * @Title: dropMySQLCommentTemp  
	 * @Description: 删除评论临时表
	 * @return: void    返回类型
	 */
	@DataSource(DataSourceConstants.MYSQL_BLOG)
	public void dropMySQLCommentTemp() {
		StringBuffer sql = new StringBuffer();
		sql.append("DROP TABLE IF EXISTS blog_comment_temp ");
		
		// 删除表
		jdbcTemplate.execute(sql.toString());
	}

	/**
	 * 
	 * @Title: getCommentCode  
	 * @Description: 获取评论编号
	 * @param parentsId
	 * @return: String    返回类型
	 */
	@DataSource(DataSourceConstants.MYSQL_BLOG)
	public String getCommentCode(Integer parentsId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("comment_code ");
		sql.append("FROM ");
		sql.append("blog_comment_temp ");
		sql.append("WHERE ");
		sql.append("old_id = ? ");
		
		// 参数
		Object[] params = new Object[1];
		params[0] = parentsId;
		
		// 查询
		List<String> commentCodeList = jdbcTemplate.queryForList(sql.toString(), params, String.class);
		if (commentCodeList == null || commentCodeList.isEmpty()) {
			return "";
		} else {
			return commentCodeList.get(0);
		}
	}
	
}
