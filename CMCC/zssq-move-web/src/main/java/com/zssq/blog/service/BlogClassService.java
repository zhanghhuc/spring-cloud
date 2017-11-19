package com.zssq.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zssq.blog.vo.DB2BlogCatalog;
import com.zssq.blog.vo.MySQLBlogClass;
import com.zssq.constants.BlogConstants;
import com.zssq.datasource.DataSource;
import com.zssq.datasource.DataSourceConstants;

/**
 * 
 * @ClassName: BlogClassService  
 * @Description: 博客分类数据迁移Service  
 * @author ZKZ  
 * @date 2017年5月19日  
 *
 */
@Service("blogClassService")
public class BlogClassService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 
	 * @Title: getSourceDB2ClassList  
	 * @Description: 查询DB2库中博客分类数据
	 * @return: List<DB2BlogCatalog>    返回类型
	 */
	@DataSource(DataSourceConstants.DB2_BLOG)
	public List<DB2BlogCatalog> getSourceDB2ClassList() {
		// 拼接SQL
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("bc1.CATA_ID     AS cataId, "); 
		sql.append("bc1.cata_name   AS cataName, "); 
		sql.append("bc2.CATA_NAME   AS parentCataName, ");
		sql.append("bc1.create_time AS createTime ");
		sql.append("FROM ");
		sql.append("blog_catalog bc1 ");
		sql.append("LEFT JOIN ");
		sql.append("blog_catalog bc2 ");
		sql.append("ON ");
		sql.append("bc1.PARENTS_ID = bc2.CATA_ID ");
		sql.append("WHERE ");
		sql.append("bc1.del_flag = 0 ");
		sql.append("AND ");
		sql.append("bc1.parents_id != 0 ");
		
		// 查询
		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<DB2BlogCatalog>(DB2BlogCatalog.class));
	}
	
	@DataSource(DataSourceConstants.DB2_BLOG)
	public List<DB2BlogCatalog> getSourceMySQLClassList() {
		// 拼接SQL
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("bc1.CATA_ID     AS cataId, "); 
		sql.append("bc1.cata_name   AS cataName, "); 
		sql.append("bc2.CATA_NAME   AS parentCataName, ");
		sql.append("bc1.create_time AS createTime ");
		sql.append("FROM ");
		sql.append("blog_catalog bc1 ");
		sql.append("LEFT JOIN ");
		sql.append("blog_catalog bc2 ");
		sql.append("ON ");
		sql.append("bc1.PARENTS_ID = bc2.CATA_ID ");
		sql.append("WHERE ");
		sql.append("bc1.del_flag = 0 ");
		sql.append("AND ");
		sql.append("bc1.parents_id != 0 ");
		
		// 查询
		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<DB2BlogCatalog>(DB2BlogCatalog.class));
	}
	
	/**
	 * 
	 * @Title: insertMySQLClassList  
	 * @Description: 批量向MySQL库中插入博客分类数据 - 以人为单位
	 * @param mySQLClassList
	 * @return: boolean    返回类型
	 * @throws Exception 
	 */
	@DataSource(DataSourceConstants.MYSQL_BLOG)
	public boolean insertMySQLClassList(List<MySQLBlogClass> mySQLClassList) throws Exception {
		// 参数校验
		if (mySQLClassList == null || mySQLClassList.isEmpty()) {
			return false;
		}
		
		// 查询是否已经插入成功
		StringBuffer selectSql = new StringBuffer();
		selectSql.append("SELECT ");
		selectSql.append("COUNT(*) ");
		selectSql.append("FROM ");
		selectSql.append("blog_class_temp ");
		selectSql.append("WHERE ");
		selectSql.append("subject_code = ? ");
		
		// 参数
		Object[] params = new Object[1];
		if (BlogConstants.BLOG_CLASS_DEPEND_TEAM.equals(mySQLClassList.get(0).getClassDepend())) {
			params[0] = mySQLClassList.get(0).getTeamCode();
		} else {
			params[0] = mySQLClassList.get(0).getUserCode();
		}
		
		// 查询
		Integer dataCount = jdbcTemplate.queryForObject(selectSql.toString(), params, Integer.class);
		// 如果现存的分类大于5个则肯定已经导入过数据
		if (dataCount > (BlogConstants.BLOG_CLASS_MAX_NUM + 1)) {
			return true;
		}
		
		// 获取总量
		int size = mySQLClassList.size();
		
		// 拼接博客分类SQL
		StringBuffer classSql = new StringBuffer();
		classSql.append("INSERT INTO blog_class ");
		classSql.append("(class_code, ");
		classSql.append("tenant_code, ");
		classSql.append("org_code, ");
		classSql.append("create_time, ");
		classSql.append("modify_time, ");
		classSql.append("remark, ");
		classSql.append("class_name, ");
		classSql.append("user_code, ");
		classSql.append("class_depend, ");
		classSql.append("team_code, ");
		classSql.append("class_blog_num) ");
		classSql.append("VALUES ");
		
		// 博客分类参数
		Object[] classParams = new Object[11 * size];
		
		// 拼接临时表SQL
		StringBuffer tempSql = new StringBuffer();
		tempSql.append("INSERT INTO blog_class_temp ");
		tempSql.append("(old_id, ");
		tempSql.append("subject_code, ");
		tempSql.append("class_code) ");
		tempSql.append("VALUES ");
		
		// 临时表参数
		Object[] tempParams = new Object[3 * size];
		
		for (int i = 0; i < size; i++) {
			MySQLBlogClass mySQLBlogClass = mySQLClassList.get(i);
			
			// 分类表
			if (i != 0) {
				classSql.append("), ");
			}
			classSql.append("(? ");
//			classSql.append("\"" + mySQLBlogClass.getClassCode() + "\"");
			classParams[i * 11] = mySQLBlogClass.getClassCode();
			classSql.append(",? ");
//			classSql.append("\"" + mySQLBlogClass.getTenantCode() + "\"");
			classParams[i * 11 + 1] = mySQLBlogClass.getTenantCode();
			classSql.append(",? ");
//			classSql.append("\"" + mySQLBlogClass.getOrgCode() + "\"");
			classParams[i * 11 + 2] = mySQLBlogClass.getOrgCode();
			classSql.append(",? ");
//			classSql.append("\"" + mySQLBlogClass.getCreateTime() + "\"");
			classParams[i * 11 + 3] = mySQLBlogClass.getCreateTime();
			classSql.append(",? ");
//			classSql.append("\"" + mySQLBlogClass.getModifyTime() + "\"");
			classParams[i * 11 + 4] = mySQLBlogClass.getModifyTime();
			classSql.append(",? ");
//			classSql.append("\"" + mySQLBlogClass.getRemark() + "\"");
			classParams[i * 11 + 5] = mySQLBlogClass.getRemark();
			classSql.append(",? ");
//			classSql.append("\"" + mySQLBlogClass.getClassName() + "\"");
			classParams[i * 11 + 6] = mySQLBlogClass.getClassName();
			classSql.append(",? ");
//			classSql.append("\"" + mySQLBlogClass.getUserCode() + "\"");
			classParams[i * 11 + 7] = mySQLBlogClass.getUserCode();
			classSql.append(",? ");
//			classSql.append("\"" + mySQLBlogClass.getClassDepend() + "\"");
			classParams[i * 11 + 8] = mySQLBlogClass.getClassDepend();
			classSql.append(",? ");
//			classSql.append("\"" + mySQLBlogClass.getTeamCode() + "\"");
			classParams[i * 11 + 9] = mySQLBlogClass.getTeamCode();
			classSql.append(",? ");
//			classSql.append("\"" + mySQLBlogClass.getClassBlogNum() + "\"");
			classParams[i * 11 + 10] = mySQLBlogClass.getClassBlogNum();
			
			// 临时表
			if (i != 0) {
				tempSql.append("), ");
			}
			tempSql.append("(? ");
//			tempSql.append("\"" + mySQLBlogClass.getOldId() + "\"");
			tempParams[i * 3] = mySQLBlogClass.getOldId();
			tempSql.append(",? ");
			if (BlogConstants.BLOG_CLASS_DEPEND_TEAM.equals(mySQLClassList.get(0).getClassDepend())) {
//				tempSql.append("\"" + mySQLBlogClass.getTeamCode() + "\"");
				tempParams[i * 3 + 1] = mySQLBlogClass.getTeamCode();
			} else {
//				tempSql.append("\"" + mySQLBlogClass.getUserCode() + "\"");
				tempParams[i * 3 + 1] = mySQLBlogClass.getUserCode();
			}
			tempSql.append(",? ");
//			tempSql.append("\"" + mySQLBlogClass.getClassCode() + "\"");
			tempParams[i * 3 + 2] = mySQLBlogClass.getClassCode();
		}
		classSql.append(") ");
		tempSql.append(") ");
		
		// 插入博客分类表
		int updateNum = jdbcTemplate.update(classSql.toString(), classParams);
		if (updateNum <= 0) {
			return false;
		}
		
		// 插入博客分类临时表
		int updateTempNum = jdbcTemplate.update(tempSql.toString(), tempParams);
		if (updateTempNum <= 0) {
			throw new Exception();
		}
		
		return true;
	}
	
	/**
	 * 
	 * @Title: createMySQLBlogClassTemp  
	 * @Description: 创建分类临时表
	 * @return: void    返回类型
	 */
	@DataSource(DataSourceConstants.MYSQL_BLOG)
	public void createMySQLBlogClassTemp() {
		// 查询是否存在该表
		StringBuffer selectSql = new StringBuffer();
		selectSql.append("SELECT ");
		selectSql.append("table_name ");
		selectSql.append("FROM ");
		selectSql.append("information_schema. TABLES ");
		selectSql.append("WHERE ");
		selectSql.append("table_name = 'blog_class_temp' ");
		List<String> tableNameList = jdbcTemplate.queryForList(selectSql.toString(), String.class);
		if (tableNameList != null && !tableNameList.isEmpty()) {
			return;
		}
		
		// 拼接SQL
		StringBuffer sql = new StringBuffer();
		sql.append("CREATE TABLE blog_class_temp ( ");
		sql.append("old_id  INTEGER NOT NULL, ");
		sql.append("subject_code  varchar(32) NOT NULL, ");
		sql.append("class_code  varchar(32) NOT NULL, ");
		sql.append("PRIMARY KEY (old_id, subject_code) ");
		sql.append(") ");
		sql.append("ENGINE=InnoDB ");
		sql.append("DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci ");
		sql.append("ROW_FORMAT=COMPACT ");
		
		// 创建表
		jdbcTemplate.execute(sql.toString());
	}
	
	/**
	 * 
	 * @Title: dropMySQLBlogClassTemp  
	 * @Description: 删除分类临时表
	 * @return: void    返回类型
	 */
	@DataSource(DataSourceConstants.MYSQL_BLOG)
	public void dropMySQLBlogClassTemp() {
		StringBuffer sql = new StringBuffer();
		sql.append("DROP TABLE IF EXISTS blog_class_temp ");
		
		// 删除表
		jdbcTemplate.execute(sql.toString());
	}

	/**
	 * 
	 * @Title: getClassCode  
	 * @Description: 获取分类编号
	 * @param blogDepend
	 * @param userCode
	 * @param teamCode
	 * @param cataLevelTwoId
	 * @return: String    返回类型
	 */
	@DataSource(DataSourceConstants.MYSQL_BLOG)
	public String getClassCode(Byte blogDepend, String userCode, String teamCode, Integer cataLevelTwoId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("class_code ");
		sql.append("FROM ");
		sql.append("blog_class_temp ");
		sql.append("WHERE ");
		sql.append("old_id = ? ");
		sql.append("AND ");
		sql.append("subject_code = ? ");
		
		// 参数
		Object[] params = new Object[2];
		params[0] = cataLevelTwoId;
		if (BlogConstants.BLOG_DEPEND_TEAM.equals(blogDepend)) {
			params[1] = teamCode;
		} else {
			params[1] = userCode;
		}
		
		// 查询
		List<String> classCodeList = jdbcTemplate.queryForList(sql.toString(), params, String.class);
		if (classCodeList == null || classCodeList.isEmpty()) {
			return "";
		} else {
			return classCodeList.get(0);
		}
	}

}
