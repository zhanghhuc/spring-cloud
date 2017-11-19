package com.zssq.disk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zssq.datasource.DataSource;
import com.zssq.datasource.DataSourceConstants;
import com.zssq.disk.vo.DiskFileDB2;
import com.zssq.disk.vo.DiskFileMySql;

import java.util.List;

/**
 * @author SharlaCheung
 * @ClassName: DiskService
 * @Description: 网盘数据迁移Service
 * @date 2017年5月23日
 */
@Service("diskService")
public class DiskService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * @Title: getDB2SubCount
     * @Description: 查询DB2库中网盘关系数据总个数
     * @return: int    返回类型
     */
    @DataSource(DataSourceConstants.DB2_ZSSQDB)
    public int getDB2DiskFileCount() {
        // 拼接SQL
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ");
        sql.append("COUNT(*) ");
        sql.append("FROM ");
        sql.append("NETDISK_FILE ");
//		sql.append("WHERE ").append("type = 12 ");
        // 查询
        return jdbcTemplate.queryForObject(sql.toString(), Integer.class);
    }

    /**
     * @param pageNo   页码
     * @param pageSize 每页个数
     * @Title: getDB2SubList
     * @Description: 查询DB2库中网盘关系数据
     * @return: List<NewsInfoVoDB2>    返回类型
     */
    @DataSource(DataSourceConstants.DB2_ZSSQDB)
    public List<DiskFileDB2> getDB2SubList(int pageNo, int pageSize) {
        // 拼接SQL
        StringBuffer sql = new StringBuffer();
        sql.append("select FILE_ID fileCode, FILE_NAME fileName, FILE_TYPE fileType,FILE_SIZE fileSize ,FILE_PATH fileUrl ,FILE_PID parentCode , CREATE_TIME creatTime , UPDATE_TIME editTime , FILE_STATE isDelete,USER_ID userCode  ");
        sql.append("FROM ");
        sql.append("( ");
        sql.append("SELECT ");
        sql.append("FILE_ID ,FILE_NAME, FILE_TYPE ,FILE_SIZE ,FILE_PATH ,FILE_PID , CREATE_TIME, UPDATE_TIME, FILE_STATE,USER_ID , ");
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
        return jdbcTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<DiskFileDB2>(DiskFileDB2.class));
    }

    /**
     * @param mysqlList
     * @Title: insertMySQLSubList
     * @Description: 批量向MySQL库中插入网盘数据
     * @return: boolean    返回类型
     */
    @DataSource(DataSourceConstants.MYSQL_DISK)
    public boolean insertMySQLSubList(List<DiskFileMySql> mysqlList) {
        // 参数校验
        if (mysqlList == null || mysqlList.isEmpty()) {
            return false;
        }

        // 拼接SQL
        StringBuffer sql = new StringBuffer();
        sql.append("INSERT INTO INFO ");
        sql.append("( file_code, file_name, file_size, file_type, file_url, file_suffix, parent_code, \n" +
                "    creat_time, edit_time, is_delete, user_code, edit_user_code, report_type, org_code, \n" +
                "    tenant_code) ");
        sql.append(" VALUES ");
        for (int i = 0; i < mysqlList.size(); i++) {
            DiskFileMySql diskFileMySql = mysqlList.get(i);
            if (i != 0) {
                sql.append("), ");
            }
            sql.append("(");
            sql.append("\"" + diskFileMySql.getFileCode() + "\",");
            sql.append("\"" + diskFileMySql.getFileName() + "\",");
            sql.append( diskFileMySql.getFileSize() + ",");
            sql.append( diskFileMySql.getFileType() + ",");
            sql.append("\"" + diskFileMySql.getFileUrl() + "\",");
            sql.append("\"" + diskFileMySql.getFileSuffix() + "\",");
            sql.append("\"" + diskFileMySql.getParentCode() + "\",");
            sql.append( diskFileMySql.getCreatTime() + ",");
            sql.append( diskFileMySql.getEditTime() + ",");
            sql.append( diskFileMySql.getIsDelete() + ",");
            sql.append("\"" + diskFileMySql.getUserCode() + "\",");
            sql.append("\"" + diskFileMySql.getEditUserCode() + "\",");
            sql.append( diskFileMySql.getReportType() + ",");
            sql.append("\"" + diskFileMySql.getOrgCode() + "\",");
            sql.append("\"" + diskFileMySql.getTenantCode() + "\"");
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
