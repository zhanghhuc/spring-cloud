package com.zssq.news.service;


import com.zssq.news.vo.NewsNoticeDB2;
import com.zssq.news.vo.NewsNoticeMySql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author SharlaCheung
 * @ClassName: NoticeService
 * @Description: 公告数据迁移Service
 * @date 2017年5月22日
 */
public class NoticeService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * @Title: getDB2SubCount
     * @Description: 查询DB2库中公告关系数据总个数
     * @return: int    返回类型
     */
    public int getDB2NewsCount() {
        // 拼接SQL
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ");
        sql.append("COUNT(*) ");
        sql.append("FROM ");
        sql.append("INFO_NOTICE ");
//		sql.append("WHERE ").append("type = 12 ");
        // 查询
        return jdbcTemplate.queryForObject(sql.toString(), Integer.class);
    }

    /**
     * @param pageNo   页码
     * @param pageSize 每页个数
     * @Title: getDB2SubList
     * @Description: 查询DB2库中公告关系数据
     * @return: List<NewsInfoVoDB2>    返回类型
     */
    public List<NewsNoticeDB2> getDB2SubList(int pageNo, int pageSize) {
        // 拼接SQL
        StringBuffer sql = new StringBuffer();
        sql.append("select ID noticeCode,USER_ID noticeOperatorCode,USER_ID noticeCreatorCode,TITLE noticeTitle,CONTENT noticeContentHtml,CREATE_TIME createTime ");
        sql.append("FROM ");
        sql.append("( ");
        sql.append("SELECT ");
        sql.append("ID,USER_ID,TITLE,CONTENT,CREATE_TIME , ");
        sql.append("rownumber() over() AS rn ");
        sql.append("FROM ");
        sql.append("INFO_NOTICE  ");
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
        return jdbcTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<NewsNoticeDB2>(NewsNoticeDB2.class));
    }

    /**
     * @param mysqlList
     * @Title: insertMySQLSubList
     * @Description: 批量向MySQL库中插入公告数据
     * @return: boolean    返回类型
     */
    public boolean insertMySQLSubList(List<NewsNoticeMySql> mysqlList) {
        // 参数校验
        if (mysqlList == null || mysqlList.isEmpty()) {
            return false;
        }

        // 拼接SQL
        StringBuffer sql = new StringBuffer();
        sql.append("INSERT INTO INFO ");
        sql.append("( notice_code, notice_title, notice_content_text, notice_content_html,notice_file_url, notice_status,\n" +
                "    notice_operator_code, notice_creator_code,org_level, org_code, notice_remark, is_top, notice_sort, is_delete, is_hidden,\n" +
                "    create_time, modify_time ) ");
        sql.append(" VALUES ");
        for (int i = 0; i < mysqlList.size(); i++) {
            NewsNoticeMySql newsNoticeMySql = mysqlList.get(i);
            if (i != 0) {
                sql.append("), ");
            }
            sql.append("(");
            sql.append("\"" + newsNoticeMySql.getNoticeCode() + "\",");
            sql.append("\"" + newsNoticeMySql.getNoticeTitle() + "\",");
            sql.append("\"" + newsNoticeMySql.getNoticeContentHtml() + "\",");
            sql.append("\"" + newsNoticeMySql.getNoticeContentHtml() + "\",");
            sql.append("\"\",");
            sql.append("\"" + newsNoticeMySql.getNoticeStatus() + "\",");
            sql.append("\"" + newsNoticeMySql.getNoticeOperatorCode() + "\",");
            sql.append("\"" + newsNoticeMySql.getNoticeCreatorCode() + "\",");
            sql.append("" + newsNoticeMySql.getOrgLevel() + ",");
            sql.append("\"" + newsNoticeMySql.getOrgCode() + "\",");
            sql.append("\"\",");
            sql.append( newsNoticeMySql.getIsTop() + ",");
            sql.append( newsNoticeMySql.getNoticeSort() + ",");
            sql.append( newsNoticeMySql.getIsDelete() + ",");
            sql.append( newsNoticeMySql.getIsHidden() + ",");
            sql.append( newsNoticeMySql.getCreateTime() + ",");
            sql.append( newsNoticeMySql.getModifyTime());
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
