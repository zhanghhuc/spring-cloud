package com.zssq.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zssq.blog.vo.DB2Blog;
import com.zssq.blog.vo.MySQLBlogDraft;
import com.zssq.blog.vo.MySQLBlogDraftContent;
import com.zssq.datasource.DataSource;
import com.zssq.datasource.DataSourceConstants;

/**
 * 
 * @ClassName: BlogDraftService  
 * @Description: 博客草稿数据迁移Service  
 * @author ZKZ  
 * @date 2017年5月22日  
 *
 */
@Service("blogDraftService")
public class BlogDraftService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 
	 * @Title: getSourceDB2DraftCount  
	 * @Description: 查询DB2库中博客草稿数据总个数
	 * @return: int    返回类型
	 */
	@DataSource(DataSourceConstants.DB2_BLOG)
	public int getSourceDB2DraftCount() {
		// 拼接SQL
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("COUNT(*) ");
		sql.append("FROM ");
		sql.append("blog ");
		sql.append("WHERE ");
		sql.append("blog_state = 8 ");
		
		// 查询
		return jdbcTemplate.queryForObject(sql.toString(), Integer.class);
	}
	
	@DataSource(DataSourceConstants.DB2_BLOG)
	public int getSourceMySQLDraftCount() {
		// 拼接SQL
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("COUNT(*) ");
		sql.append("FROM ");
		sql.append("blog ");
		sql.append("WHERE ");
		sql.append("blog_state = 8 ");
		
		// 查询
		return jdbcTemplate.queryForObject(sql.toString(), Integer.class);
	}
	
	/**
	 * 
	 * @Title: getSourceDB2Draftist  
	 * @Description: 查询DB2库中博客草稿数据
	 * @param pageNo
	 * @param pageSize
	 * @return: List<DB2Blog>    返回类型
	 */
	@DataSource(DataSourceConstants.DB2_BLOG)
	public List<DB2Blog> getSourceDB2Draftist(int pageNo, int pageSize) {
		// 拼接SQL
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("bl.blog_id AS blogId, ");
		sql.append("bl.title AS title, ");
		sql.append("bl.user_id AS userId, ");
		sql.append("bl.cata_level_two_id AS cataLevelTwoId, ");
		sql.append("bl.create_time AS createTime, ");
		sql.append("bl.updatetime AS updatetime, ");
		sql.append("bl.digest AS digest, ");
		sql.append("bl.content AS content, ");
		sql.append("bi.team_id AS teamId, ");
		sql.append("bi.city_id AS cityId, ");
		sql.append("bi.province_id AS provinceId ");
		sql.append("FROM ");
		sql.append("( ");
		sql.append("SELECT ");
		sql.append("b.blog_id, ");
		sql.append("b.title, ");
		sql.append("b.user_id, ");
		sql.append("b.cata_level_two_id, ");
		sql.append("b.create_time, ");
		sql.append("b.updatetime, ");
		sql.append("b.digest, ");
		sql.append("b.content ");
		sql.append("FROM ");
		sql.append("( ");
		sql.append("SELECT ");
		sql.append("blog_id, ");
		sql.append("title, ");
		sql.append("user_id, ");
		sql.append("cata_level_two_id, ");
		sql.append("create_time, ");
		sql.append("updatetime, ");
		sql.append("digest, ");
		sql.append("content, ");
		sql.append("rownumber() over () AS rn ");
		sql.append("FROM blog ");
		sql.append("WHERE ");
		sql.append("blog_state = 8 ");
		sql.append("ORDER BY blog_id ASC ");
		sql.append(") b ");
		sql.append("WHERE ");
		sql.append("b.rn BETWEEN ? AND ? ");
		sql.append(") bl ");
		sql.append("LEFT JOIN ");
		sql.append("blog_index bi ");
		sql.append("ON bl.blog_id = bi.blog_id ");

		// 拼接参数
		Object[] params = new Object[2];
		params[0] = pageNo * pageSize + 1;
		params[1] = pageNo * pageSize + pageSize;
		
		// 查询
		return jdbcTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<DB2Blog>(DB2Blog.class));
	}
	
	@DataSource(DataSourceConstants.DB2_BLOG)
	public List<DB2Blog> getSourceMySQLDraftist(int pageNo, int pageSize) {
		// 拼接SQL
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("bl.blog_id AS blogId, ");
		sql.append("bl.title AS title, ");
		sql.append("bl.user_id AS userId, ");
		sql.append("bl.cata_level_two_id AS cataLevelTwoId, ");
		sql.append("bl.create_time AS createTime, ");
		sql.append("bl.updatetime AS updatetime, ");
		sql.append("bl.digest AS digest, ");
		sql.append("bl.content AS content, ");
		sql.append("bi.team_id AS teamId, ");
		sql.append("bi.city_id AS cityId, ");
		sql.append("bi.province_id AS provinceId ");
		sql.append("FROM ");
		sql.append("( ");
		sql.append("SELECT ");
		sql.append("blog_id, ");
		sql.append("title, ");
		sql.append("user_id, ");
		sql.append("cata_level_two_id, ");
		sql.append("create_time, ");
		sql.append("updatetime, ");
		sql.append("digest, ");
		sql.append("content ");
		sql.append("FROM ");
		sql.append("blog ");
		sql.append("WHERE ");
		sql.append("blog_state = 8 ");
		sql.append("ORDER BY ");
		sql.append("blog_id ASC ");
		sql.append("LIMIT ?, ? ");
		sql.append(") bl ");
		sql.append("LEFT JOIN blog_index bi ");
		sql.append("ON bl.blog_id = bi.blog_id ");
		
		// 拼接参数
		Object[] params = new Object[2];
		params[0] = pageNo * pageSize;
		params[1] = pageSize;
		
		// 查询
		return jdbcTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<DB2Blog>(DB2Blog.class));
	}
	
	/**
	 * 
	 * @Title: insertMySQLDraftList  
	 * @Description: 批量向MySQL库中插入博客草稿数据
	 * @param mySQLDraftList
	 * @param mySQLDraftContentList
	 * @throws Exception    参数  
	 * @return: boolean    返回类型
	 */
	@DataSource(DataSourceConstants.MYSQL_BLOG)
	public boolean insertMySQLDraftList(List<MySQLBlogDraft> mySQLDraftList, List<MySQLBlogDraftContent> mySQLDraftContentList) throws Exception {
		// 参数校验
		if (mySQLDraftList == null || mySQLDraftList.isEmpty()) {
			return false;
		}
		if (mySQLDraftContentList == null || mySQLDraftContentList.isEmpty()) {
			return false;
		}
		
		// 获取总量
		int size = mySQLDraftList.size();
		
		// 拼接草稿SQL
		StringBuffer draftSql = new StringBuffer();
		draftSql.append("INSERT INTO blog_draft ");
		draftSql.append("(draft_code, ");
		draftSql.append("tenant_code, ");
		draftSql.append("org_code, ");
		draftSql.append("create_time, ");
		draftSql.append("modify_time, ");
		draftSql.append("remark, ");
		draftSql.append("user_code, ");
		draftSql.append("draft_depend, ");
		draftSql.append("team_code, ");
		draftSql.append("draft_digest, ");
		draftSql.append("draft_title, ");
		draftSql.append("class_code, ");
		draftSql.append("draft_tags, ");
		draftSql.append("draft_plan_publish_time) ");
		draftSql.append("VALUES ");
		
		// 草稿参数
		Object[] draftParams = new Object[14 * size];
		
		for (int i = 0; i < size; i++) {
			MySQLBlogDraft mySQLBlogDraft = mySQLDraftList.get(i);
			if (i != 0) {
				draftSql.append("), ");
			}
			draftSql.append("(? ");
//			draftSql.append("\"" + mySQLBlogDraft.getDraftCode() + "\"");
			draftParams[i * 14] = mySQLBlogDraft.getDraftCode();
			draftSql.append(",? ");
//			draftSql.append("\"" + mySQLBlogDraft.getTenantCode() + "\"");
			draftParams[i * 14 + 1] = mySQLBlogDraft.getTenantCode();
			draftSql.append(",? ");
//			draftSql.append("\"" + mySQLBlogDraft.getOrgCode() + "\"");
			draftParams[i * 14 + 2] = mySQLBlogDraft.getOrgCode();
			draftSql.append(",? ");
//			draftSql.append("\"" + mySQLBlogDraft.getCreateTime() + "\"");
			draftParams[i * 14 + 3] = mySQLBlogDraft.getCreateTime();
			draftSql.append(",? ");
//			draftSql.append("\"" + mySQLBlogDraft.getModifyTime() + "\"");
			draftParams[i * 14 + 4] = mySQLBlogDraft.getModifyTime();
			draftSql.append(",? ");
//			draftSql.append("\"" + mySQLBlogDraft.getRemark() + "\"");
			draftParams[i * 14 + 5] = mySQLBlogDraft.getRemark();
			draftSql.append(",? ");
//			draftSql.append("\"" + mySQLBlogDraft.getUserCode() + "\"");
			draftParams[i * 14 + 6] = mySQLBlogDraft.getUserCode();
			draftSql.append(",? ");
//			draftSql.append("\"" + mySQLBlogDraft.getDraftDepend() + "\"");
			draftParams[i * 14 + 7] = mySQLBlogDraft.getDraftDepend();
			draftSql.append(",? ");
//			draftSql.append("\"" + mySQLBlogDraft.getTeamCode() + "\"");
			draftParams[i * 14 + 8] = mySQLBlogDraft.getTeamCode();
			draftSql.append(",? ");
//			draftSql.append("\"" + mySQLBlogDraft.getDraftDigest() + "\"");
			draftParams[i * 14 + 9] = mySQLBlogDraft.getDraftDigest();
			draftSql.append(",? ");
//			draftSql.append("\"" + mySQLBlogDraft.getDraftTitle() + "\"");
			draftParams[i * 14 + 10] = mySQLBlogDraft.getDraftTitle();
			draftSql.append(",? ");
//			draftSql.append("\"" + mySQLBlogDraft.getClassCode() + "\"");
			draftParams[i * 14 + 11] = mySQLBlogDraft.getClassCode();
			draftSql.append(",? ");
//			draftSql.append("\"" + mySQLBlogDraft.getDraftTags() + "\"");
			draftParams[i * 14 + 12] = mySQLBlogDraft.getDraftTags();
			draftSql.append(",? ");
//			draftSql.append("\"" + mySQLBlogDraft.getDraftPlanPublishTime() + "\"");
			draftParams[i * 14 + 13] = mySQLBlogDraft.getDraftPlanPublishTime();
		}
		draftSql.append(") ");
		
		// 插入草稿
		int updateDraftNum = jdbcTemplate.update(draftSql.toString(), draftParams);
		if (updateDraftNum <= 0) {
			throw new Exception();
		} 
		
		// 拼接草稿内容SQL
		StringBuffer draftContentSql = new StringBuffer();
		draftContentSql.append("INSERT INTO blog_draft_content ");
		draftContentSql.append("(draft_content_code, ");
		draftContentSql.append("tenant_code, ");
		draftContentSql.append("org_code, ");
		draftContentSql.append("create_time, ");
		draftContentSql.append("modify_time, ");
		draftContentSql.append("remark, ");
		draftContentSql.append("draft_code, ");
		draftContentSql.append("draft_content_info) ");
		draftContentSql.append("VALUES ");
		
		// 草稿内容参数
		Object[] draftContentParams = new Object[8 * size];
		
		for (int i = 0; i < size; i++) {
			MySQLBlogDraftContent mySQLBlogDraftContent = mySQLDraftContentList.get(i);
			if (i != 0) {
				draftContentSql.append("), ");
			}
			draftContentSql.append("(? ");
//			draftContentSql.append("\"" + mySQLBlogDraftContent.getDraftContentCode() + "\"");
			draftContentParams[i * 8] = mySQLBlogDraftContent.getDraftContentCode();
			draftContentSql.append(",? ");
//			draftContentSql.append("\"" + mySQLBlogDraftContent.getTenantCode() + "\"");
			draftContentParams[i * 8 + 1] = mySQLBlogDraftContent.getTenantCode();
			draftContentSql.append(",? ");
//			draftContentSql.append("\"" + mySQLBlogDraftContent.getOrgCode() + "\"");
			draftContentParams[i * 8 + 2] = mySQLBlogDraftContent.getOrgCode();
			draftContentSql.append(",? ");
//			draftContentSql.append("\"" + mySQLBlogDraftContent.getCreateTime() + "\"");
			draftContentParams[i * 8 + 3] = mySQLBlogDraftContent.getCreateTime();
			draftContentSql.append(",? ");
//			draftContentSql.append("\"" + mySQLBlogDraftContent.getModifyTime() + "\"");
			draftContentParams[i * 8 + 4] = mySQLBlogDraftContent.getModifyTime();
			draftContentSql.append(",? ");
//			draftContentSql.append("\"" + mySQLBlogDraftContent.getRemark() + "\"");
			draftContentParams[i * 8 + 5] = mySQLBlogDraftContent.getRemark();
			draftContentSql.append(",? ");
//			draftContentSql.append("\"" + mySQLBlogDraftContent.getDraftCode() + "\"");
			draftContentParams[i * 8 + 6] = mySQLBlogDraftContent.getDraftCode();
			draftContentSql.append(",? ");
//			draftContentSql.append("\"" + mySQLBlogDraftContent.getDraftContentInfo() + "\"");
			draftContentParams[i * 8 + 7] = mySQLBlogDraftContent.getDraftContentInfo();
		}
		draftContentSql.append(") ");
		
		// 插入草稿内容
		int updateBlogContentNum = jdbcTemplate.update(draftContentSql.toString(), draftContentParams);
		if (updateBlogContentNum <= 0) {
			throw new Exception();
		} 
		
		return true;
	}
	
}
