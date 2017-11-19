package com.zssq.news.service;

import com.zssq.datasource.DataSource;
import com.zssq.datasource.DataSourceConstants;
import com.zssq.news.vo.NewsInfoVoDB2;
import com.zssq.news.vo.NewsInfoVoMysql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SharlaCheung
 * @ClassName: NewsService
 * @Description: 新闻数据迁移Service
 * @date 2017年5月22日
 */
@Service("newsService")
public class NewsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * @Title: getDB2SubCount
     * @Description: 查询DB2库中新闻关系数据总个数
     * @return: int    返回类型
     */
    @DataSource(DataSourceConstants.DB2_BLOG)
    public int getDB2NewsCount() {
        // 拼接SQL
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ");
        sql.append("COUNT(*) ");
        sql.append("FROM ");
        sql.append("INFO ");
//		sql.append("WHERE ").append("type = 12 ");
        // 查询
        return jdbcTemplate.queryForObject(sql.toString(), Integer.class);
    }

    /**
     * @param pageNo   页码
     * @param pageSize 每页个数
     * @Title: getDB2SubList
     * @Description: 查询DB2库中新闻关系数据
     * @return: List<NewsInfoVoDB2>    返回类型
     */
    @DataSource(DataSourceConstants.DB2_BLOG)
    public List<NewsInfoVoDB2> getDB2SubList(int pageNo, int pageSize) {
        // 拼接SQL
        StringBuffer sql = new StringBuffer();
        sql.append("select INFO_ID newsCode,INFO_TITLE infoTitle,INFO_TEXT infoContentHtml,INFO_STATE infoStatus,USER_ID infoOperatorCode,USER_ID infoCreatorCode,IMG_URL fileUrl,CREATETIME createTime,UPDATETIME modifyTime ");

        sql.append("FROM ");
        sql.append("( ");
        sql.append("SELECT ");
        sql.append("INFO_ID ,INFO_TITLE ,INFO_TEXT ,INFO_STATE ,USER_ID ,USER_ID ,IMG_URL ,CREATETIME ,UPDATETIME , ");
        sql.append("rownumber() over() AS rn ");
        sql.append("FROM ");
        sql.append("INFO  ");
//        sql.append("WHERE ");
//        sql.append("msr.TYPE = 12 ");
        sql.append(") AS t ");
        sql.append("WHERE ");
        sql.append("t.rn BETWEEN ? AND ?");

        // 拼接参数
        Object[] params = new Object[2];
        params[0] = pageNo * pageSize + 1;
        params[1] = pageNo * pageSize + pageSize;

        // 查询
        return jdbcTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<NewsInfoVoDB2>(NewsInfoVoDB2.class));
    }

    /**
     * @param mysqlList
     * @Title: insertMySQLSubList
     * @Description: 批量向MySQL库中插入新闻数据
     * @return: boolean    返回类型
     */
    @DataSource(DataSourceConstants.MYSQL_BLOG)
    public boolean insertMySQLSubList(List<NewsInfoVoMysql> mysqlList) {
        // 参数校验
        if (mysqlList == null || mysqlList.isEmpty()) {
            return false;
        }

        // 拼接SQL
        StringBuffer sql = new StringBuffer();
        sql.append("INSERT INTO INFO ");
        sql.append("( news_code, info_title, info_content_text, info_content_html, info_status, info_operator_code, \n" +
                "    info_creator_code, org_level, org_code, file_url, is_top, info_sort,is_hidden, info_good_count,\n" +
                "    info_comment_count,is_delete, is_shield, create_time, modify_time, top_time ) ");
        sql.append(" VALUES ");
        for (int i = 0; i < mysqlList.size(); i++) {
            NewsInfoVoMysql newsInfoVoMysql = mysqlList.get(i);
            if (i != 0) {
                sql.append("), ");
            }
            sql.append("(");
            sql.append("\"" + newsInfoVoMysql.getNewsCode() + "\",");
            sql.append("\"" + newsInfoVoMysql.getInfoTitle() + "\",");
            sql.append("\"" + newsInfoVoMysql.getInfoContentText() + "\",");
            sql.append("\"" + newsInfoVoMysql.getInfoContentHtml() + "\",");
            sql.append("" + newsInfoVoMysql.getInfoStatus() + ",");
            sql.append("\"" + newsInfoVoMysql.getInfoOperatorCode() + "\",");
            sql.append("\"" + newsInfoVoMysql.getInfoCreatorCode() + "\",");
            sql.append("\"" + newsInfoVoMysql.getOrgLevel() + "\",");
            sql.append("\"" + newsInfoVoMysql.getOrgCode() + "\",");
            sql.append("\"" + newsInfoVoMysql.getFileUrl() + "\",");
            sql.append( newsInfoVoMysql.getIsTop() + ",");
            sql.append( newsInfoVoMysql.getInfoSort() + ",");
            sql.append( newsInfoVoMysql.getIsHidden() + ",");
            sql.append( newsInfoVoMysql.getInfoGoodCount() + ",");
            sql.append( newsInfoVoMysql.getInfoCommentCount() + ",");
            sql.append( newsInfoVoMysql.getIsDelete() + ",");
            sql.append( newsInfoVoMysql.getIsShield() + ",");
            sql.append( newsInfoVoMysql.getCreateTime() + ",");
            sql.append( newsInfoVoMysql.getModifyTime() + ",");
            sql.append( newsInfoVoMysql.getTopTime() );

        }
        sql.append(") ");

        // 插入
        int updateNum = jdbcTemplate.update(sql.toString());
        if (updateNum <= 0) {
            return false;
        }

        return true;
    }

}
