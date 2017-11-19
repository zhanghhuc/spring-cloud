package com.zssq.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zssq.blog.vo.DB2BlogReply;
import com.zssq.blog.vo.MySQLBlogReply;
import com.zssq.datasource.DataSource;
import com.zssq.datasource.DataSourceConstants;

/**
 * 
 * @ClassName: BlogReplyService  
 * @Description: 评论回复数据迁移Service  
 * @author ZKZ  
 * @date 2017年5月22日  
 *
 */
@Service("blogReplyService")
public class BlogReplyService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 
	 * @Title: getSourceDB2ReplyCount  
	 * @Description: 查询DB2库中评论回复数据总个数
	 * @return: int    返回类型
	 */
	@DataSource(DataSourceConstants.DB2_BLOG)
	public int getSourceDB2ReplyCount() {
		// 拼接SQL
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("COUNT(*) ");
		sql.append("FROM ");
		sql.append("blog_reply ");
		sql.append("WHERE ");
		sql.append("del_flag = 0 ");
		sql.append("AND ");
		sql.append("(flag_reply = 1 ");
		sql.append("OR ");
		sql.append("flag_reply = 2) ");
		
		// 查询
		return jdbcTemplate.queryForObject(sql.toString(), Integer.class);
	}
	
	@DataSource(DataSourceConstants.DB2_BLOG)
	public int getSourceMySQLReplyCount() {
		// 拼接SQL
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("COUNT(*) ");
		sql.append("FROM ");
		sql.append("blog_reply ");
		sql.append("WHERE ");
		sql.append("del_flag = 0 ");
		sql.append("AND ");
		sql.append("(flag_reply = 1 ");
		sql.append("OR ");
		sql.append("flag_reply = 2) ");
		
		// 查询
		return jdbcTemplate.queryForObject(sql.toString(), Integer.class);
	}
	
	/**
	 * 
	 * @Title: getSourceDB2ReplyList  
	 * @Description: 查询DB2库中评论回复数据
	 * @param pageNo
	 * @param pageSize
	 * @return: List<DB2BlogReply>    返回类型
	 */
	@DataSource(DataSourceConstants.DB2_BLOG)
	public List<DB2BlogReply> getSourceDB2ReplyList(int pageNo, int pageSize) {
		// 拼接SQL
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("brl.id AS id, ");
		sql.append("brl.blog_id AS blogId, ");
		sql.append("brl.user_id AS userId, ");
		sql.append("brl.parents_id AS parentsId, ");
		sql.append("brl.create_time AS createTime, ");
		sql.append("brl.update_time AS updateTime, ");
		sql.append("brl.reply_content AS replyContent, ");
		sql.append("brp.user_id AS toReplyUserId, ");
		sql.append("brp.flag_reply AS flagReply ");
		sql.append("FROM ");
		sql.append("(SELECT ");
		sql.append("br.id, ");
		sql.append("br.blog_id, ");
		sql.append("br.user_id, ");
		sql.append("br.parents_id, ");
		sql.append("br.create_time, ");
		sql.append("br.update_time, ");
		sql.append("br.reply_content, ");
		sql.append("br.flag_reply ");
		sql.append("FROM ");
		sql.append("(  ");
		sql.append("SELECT ");
		sql.append("id, ");
		sql.append("blog_id, ");
		sql.append("user_id, ");
		sql.append("parents_id, ");
		sql.append("create_time, ");
		sql.append("update_time, ");
		sql.append("reply_content, ");
		sql.append("flag_reply, ");
		sql.append("rownumber() over () AS rn ");
		sql.append("FROM blog_reply ");
		sql.append("WHERE ");
		sql.append("del_flag = 0 ");
		sql.append("AND ");
		sql.append("(flag_reply = 1 ");
		sql.append("OR ");
		sql.append("flag_reply = 2) ");
		sql.append("ORDER BY id ASC ");
		sql.append(") br ");
		sql.append("WHERE ");
		sql.append("br.rn BETWEEN ? AND ? ) brl ");
		sql.append("LEFT JOIN blog_reply brp ");
		sql.append("ON brl.flag_reply = 2 ");
		sql.append("AND brl.parents_id = brp.id ");

		// 拼接参数
		Object[] params = new Object[2];
		params[0] = pageNo * pageSize + 1;
		params[1] = pageNo * pageSize + pageSize;
		
		// 查询
		return jdbcTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<DB2BlogReply>(DB2BlogReply.class));
	}
	
	@DataSource(DataSourceConstants.DB2_BLOG)
	public List<DB2BlogReply> getSourceMySQLReplyList(int pageNo, int pageSize) {
		// 拼接SQL
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("br.id AS id, ");
		sql.append("br.blog_id AS blogId, ");
		sql.append("br.user_id AS userId, ");
		sql.append("br.parents_id AS parentsId, ");
		sql.append("br.create_time AS createTime, ");
		sql.append("br.update_time AS updateTime, ");
		sql.append("br.reply_content AS replyContent, ");
		sql.append("brp.user_id AS toReplyUserId, ");
		sql.append("brp.flag_reply AS flagReply ");
		sql.append("FROM ");
		sql.append("( ");
		sql.append("SELECT ");
		sql.append("id, ");
		sql.append("blog_id, ");
		sql.append("user_id, ");
		sql.append("parents_id, ");
		sql.append("create_time, ");
		sql.append("update_time, ");
		sql.append("reply_content, ");
		sql.append("flag_reply ");
		sql.append("FROM ");
		sql.append("blog_reply ");
		sql.append("WHERE ");
		sql.append("del_flag = 0 ");
		sql.append("AND (flag_reply = 1 OR flag_reply = 2) ");
		sql.append("ORDER BY ");
		sql.append("id ASC ");
		sql.append("LIMIT ?, ? ");
		sql.append(") br ");
		sql.append("LEFT JOIN blog_reply brp ON br.flag_reply = 2 ");
		sql.append("AND br.parents_id = brp.id ");
		
		// 拼接参数
		Object[] params = new Object[2];
		params[0] = pageNo * pageSize;
		params[1] = pageSize;
		
		// 查询
		return jdbcTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<DB2BlogReply>(DB2BlogReply.class));
	}
	
	/**
	 * 
	 * @Title: insertMySQLReplyList  
	 * @Description: 批量向MySQL库中插入评论回复数据
	 * @param mySQLCommentList
	 * @throws Exception    参数  
	 * @return: boolean    返回类型
	 */
	@DataSource(DataSourceConstants.MYSQL_BLOG)
	public boolean insertMySQLReplyList(List<MySQLBlogReply> mySQLReplyList) throws Exception {
		// 参数校验
		if (mySQLReplyList == null || mySQLReplyList.isEmpty()) {
			return false;
		}
		
		// 获取总量
		int size = mySQLReplyList.size();
		
		// 拼接评论回复SQL
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO blog_reply ");
		sql.append("(reply_code, ");
		sql.append("tenant_code, ");
		sql.append("org_code, ");
		sql.append("create_time, ");
		sql.append("modify_time, ");
		sql.append("remark, ");
		sql.append("blog_code, ");
		sql.append("comment_code, ");
		sql.append("user_code, ");
		sql.append("to_reply_user_code, ");
		sql.append("reply_content, ");
		sql.append("reply_is_delete, ");
		sql.append("replyt_is_shield, ");
		sql.append("reply_like_num) ");
		sql.append("VALUES ");
		
		// 回复参数
		Object[] params = new Object[14 * size];
		
		for (int i = 0; i < mySQLReplyList.size(); i++) {
			MySQLBlogReply mySQLBlogReply = mySQLReplyList.get(i);
			if (i != 0) {
				sql.append("), ");
			}
			sql.append("(? ");
//			sql.append("\"" + mySQLBlogReply.getReplyCode() + "\"");
			params[i * 14] = mySQLBlogReply.getReplyCode();
			sql.append(",? ");
//			sql.append("\"" + mySQLBlogReply.getTenantCode() + "\"");
			params[i * 14 + 1] = mySQLBlogReply.getTenantCode();
			sql.append(",? ");
//			sql.append("\"" + mySQLBlogReply.getOrgCode() + "\"");
			params[i * 14 + 2] = mySQLBlogReply.getOrgCode();
			sql.append(",? ");
//			sql.append("\"" + mySQLBlogReply.getCreateTime() + "\"");
			params[i * 14 + 3] = mySQLBlogReply.getCreateTime();
			sql.append(",? ");
//			sql.append("\"" + mySQLBlogReply.getModifyTime() + "\"");
			params[i * 14 + 4] = mySQLBlogReply.getModifyTime();
			sql.append(",? ");
//			sql.append("\"" + mySQLBlogReply.getRemark() + "\"");
			params[i * 14 + 5] = mySQLBlogReply.getRemark();
			sql.append(",? ");
//			sql.append("\"" + mySQLBlogReply.getBlogCode() + "\"");
			params[i * 14 + 6] = mySQLBlogReply.getBlogCode();
			sql.append(",? ");
//			sql.append("\"" + mySQLBlogReply.getCommentCode() + "\"");
			params[i * 14 + 7] = mySQLBlogReply.getCommentCode();
			sql.append(",? ");
//			sql.append("\"" + mySQLBlogReply.getUserCode() + "\"");
			params[i * 14 + 8] = mySQLBlogReply.getUserCode();
			sql.append(",? ");
//			sql.append("\"" + mySQLBlogReply.getToReplyUserCode() + "\"");
			params[i * 14 + 9] = mySQLBlogReply.getToReplyUserCode();
			sql.append(",? ");
//			sql.append("\"" + mySQLBlogReply.getReplyContent() + "\"");
			params[i * 14 + 10] = mySQLBlogReply.getReplyContent();
			sql.append(",? ");
//			sql.append("\"" + mySQLBlogReply.getReplyIsDelete() + "\"");
			params[i * 14 + 11] = mySQLBlogReply.getReplyIsDelete();
			sql.append(",? ");
//			sql.append("\"" + mySQLBlogReply.getReplytIsShield() + "\"");
			params[i * 14 + 12] = mySQLBlogReply.getReplytIsShield();
			sql.append(",? ");
//			sql.append("\"" + mySQLBlogReply.getReplyLikeNum() + "\"");
			params[i * 14 + 13] = mySQLBlogReply.getReplyLikeNum();
		}
		sql.append(") ");
		
		// 插入回复
		int updateBlogNum = jdbcTemplate.update(sql.toString(), params);
		if (updateBlogNum <= 0) {
			return false;
		} 
		
		return true;
	}
	
}
