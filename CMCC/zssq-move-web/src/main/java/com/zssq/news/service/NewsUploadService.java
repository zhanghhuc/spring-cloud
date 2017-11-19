package com.zssq.news.service;

import com.zssq.datasource.DataSource;
import com.zssq.datasource.DataSourceConstants;
import com.zssq.news.vo.NewsInfoUploadDB2;
import com.zssq.news.vo.NewsInfoUploadMySql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SharlaCheung
 * @ClassName: NewsUploadService
 * @Description: 新闻上传数据迁移Service
 * @date 2017年5月22日
 */
@Service("newsUploadService")
public class NewsUploadService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * @Title: getDB2SubCount
     * @Description: 查询DB2库中新闻上传关系数据总个数
     * @return: int    返回类型
     */
    @DataSource(DataSourceConstants.DB2_BLOG)
    public int getDB2UploadCount() {
        // 拼接SQL
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ");
        sql.append("COUNT(*) ");
        sql.append("FROM ");
        sql.append("INFO_UPLOAD ");
//		sql.append("WHERE ").append("type = 12 ");
        // 查询
        return jdbcTemplate.queryForObject(sql.toString(), Integer.class);
    }

    /**
     * @param pageNo   页码
     * @param pageSize 每页个数
     * @Title: getDB2SubList
     * @Description: 查询DB2库中新闻上传关系数据
     * @return: List<NewsInfoVoDB2>    返回类型
     */
    @DataSource(DataSourceConstants.DB2_BLOG)
    public List<NewsInfoUploadDB2> getDB2SubList(int pageNo, int pageSize) {
        // 拼接SQL
        StringBuffer sql = new StringBuffer();
        sql.append("select  ID  infoUploadCode  ,UF_TYPE infoType,UF_PATH   infoPath ,UF_ORG_NAME infoOrgName  ,UF_NEW_NAME infoNewName ,UF_ORG_EXT infoOrgExt ,UF_NEW_EXT infoNewExt ,UF_UPLOAD_TIME infoUploadTime ,UF_UPDATE_TIME modifyTime ");

        sql.append("FROM ");
        sql.append("( ");
        sql.append("SELECT ");
        sql.append("ID , UF_TYPE,UF_PATH    ,UF_ORG_NAME   ,UF_NEW_NAME ,UF_ORG_EXT  ,UF_NEW_EXT  ,UF_UPLOAD_TIME ,UF_UPDATE_TIME , ");
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
        return jdbcTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<NewsInfoUploadDB2>(NewsInfoUploadDB2.class));
    }

    /**
     * @param mysqlList
     * @Title: insertMySQLSubList
     * @Description: 批量向MySQL库中插入新闻上传数据
     * @return: boolean    返回类型
     */
    @DataSource(DataSourceConstants.MYSQL_BLOG)
    public boolean insertMySQLSubList(List<NewsInfoUploadMySql> mysqlList) {
        // 参数校验
        if (mysqlList == null || mysqlList.isEmpty()) {
            return false;
        }

        // 拼接SQL
        StringBuffer sql = new StringBuffer();
        sql.append("INSERT INTO INFO ");
        sql.append("( info_upload_code, news_code, info_type, info_path, info_org_name, info_new_name, \n" +
                "    info_org_ext, info_new_ext, info_upload_time, create_time, modify_time, remark, tenant_code, \n" +
                "    org_level, org_code ) ");
        sql.append(" VALUES ");
        for (int i = 0; i < mysqlList.size(); i++) {
            NewsInfoUploadMySql newsInfoUploadMySql = mysqlList.get(i);
            if (i != 0) {
                sql.append("), ");
            }
            sql.append("(");
            sql.append("\"" + newsInfoUploadMySql.getInfoUploadCode() + "\",");
            sql.append("\"" + newsInfoUploadMySql.getNewsCode() + "\",");
            sql.append( newsInfoUploadMySql.getInfoType() + ",");
            sql.append("\"" + newsInfoUploadMySql.getInfoPath() + "\",");
            sql.append("\"" + newsInfoUploadMySql.getInfoOrgName() + "\",");
            sql.append("" + newsInfoUploadMySql.getInfoNewName() + ",");
            sql.append("\"" + newsInfoUploadMySql.getInfoOrgExt() + "\",");
            sql.append("\"" + newsInfoUploadMySql.getInfoNewExt() + "\",");
            sql.append( newsInfoUploadMySql.getInfoUploadTime() + ",");
            sql.append( newsInfoUploadMySql.getCreateTime() + ",");
            sql.append( newsInfoUploadMySql.getModifyTime() + ",");
            sql.append("\"" + newsInfoUploadMySql.getRemark() + "\",");
            sql.append("\"" + newsInfoUploadMySql.getTenantCode() + "\",");
            sql.append("\"" + newsInfoUploadMySql.getOrgLevel() + "\",");
            sql.append("\"" + newsInfoUploadMySql.getOrgCode() + "\"");

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
