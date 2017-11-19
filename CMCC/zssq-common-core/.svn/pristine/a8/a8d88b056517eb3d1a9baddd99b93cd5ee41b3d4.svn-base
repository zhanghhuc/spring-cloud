package com.zssq.core.tools.mysqlToOther;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

 /**
  * 
     * @ClassName: JavaToXml  
     * @Description: xml生产类  
     * @author Mr.K  
     * @date 2016年3月9日  
     *
  */
public class JavaToXml {

    private static final String LINE = "\r\n";
    private static final String TAB = "\t";
    //dao的实现路径
    private static final String DAOIMP = "jmpm.forum.service.dao.impl.";
    //bean的实体路径
    private static final String ENTITYPATH = "jmpm.forum.facade.user.entity.";
    
    //mysqlUrl
    private static final String mysqlUrl="jdbc:mysql://172.16.127.100:3306/jmpm_forum";

 /**
  * 
     * @Title: tableToXml  
     * @Description: table转mapper.xml
     * @param @param connection
     * @param @param tableName
     * @param @throws SQLException    参数  
     * @return void    返回类型  
     * @throws
  */
    public void tableToXml(Connection connection, String tableName) throws SQLException {
        String sql = "select * from " + tableName + " where 1 <> 1";
        PreparedStatement ps = null;
        ResultSet rs = null;
        ps = connection.prepareStatement(sql);
        rs = ps.executeQuery();
        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount();
        StringBuffer sb = new StringBuffer();
        tableName = tableName.substring(0, 1).toUpperCase() + tableName.subSequence(1, tableName.length());
        String tableNamerid = dealName(tableName);
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n");
        sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\r\n");
        sb.append("\t<!--定义mybatis自身应用信息-->\r\n");
        sb.append("<mapper namespace=\"").append(DAOIMP).append(tableNamerid).append("DaoImpl\">\r\n");
        sb.append("\t<!--数据库表名-->\r\n");
        sb.append("<sql id=\"table\">").append(tableName.toLowerCase()).append("</sql>\r\n");
        sb.append(LINE);
        sb.append("\t<!--配置映射文件-->\r\n");
        sb.append("<resultMap id=\"BaseResultMap\" type=\"").append(ENTITYPATH).append(tableNamerid).append("\">\r\n");
        setColumn(md, columnCount, sb);
        sb.append("</resultMap>");
        sb.append(LINE).append(LINE);
        sb.append("<sql id=\"Base_Column_List\">");
        sb.append(LINE);
        setBaseColumn(md, columnCount, sb);
        sb.append(LINE).append(LINE);
        sb.append("<sql id=\"condition_sql\">");
        sb.append(LINE);
        setBaseWhereColumn(md, columnCount, sb);
        sb.append(LINE).append(LINE);
        sb.append(LINE).append(LINE);
        sb.append("<sql id=\"baseCommonSql_sql\">");
        sb.append(LINE);
        setBaseCommonSql(md, columnCount, sb);
        sb.append(LINE).append(LINE);
        sb.append("<insert id=\"insert\" ").append("useGeneratedKeys=\"true\" ").append(" keyProperty=\"").append(dealLine(md, 1)).append("\" ").append("parameterType=\"").append(ENTITYPATH).append(tableNamerid).append("\">");
        insertColumn(md, columnCount, sb);
        sb.append(LINE).append(LINE);
        getById(md, columnCount, sb);
        sb.append(LINE).append(LINE);
        listPage(md, columnCount, sb);
        sb.append(LINE).append(LINE);
        listPageCount(md, columnCount, sb);
        sb.append(LINE).append(LINE);
        sb.append("<insert id=\"batchInsert\" parameterType=\"").append(ENTITYPATH).append(tableNamerid).append("\">");
        batchInsert(md, columnCount, sb);
        sb.append(LINE).append(LINE);
        getByUserCode(md, columnCount, sb);
        sb.append(LINE).append(LINE);
        getByUserCodes(md, columnCount, sb);
        sb.append(LINE).append(LINE);
        listBy(md, columnCount, sb);
        sb.append(LINE).append(LINE);
        sb.append("</mapper>");
        String paths = System.getProperty("user.dir");
        String endPath = paths + "\\src\\main\\mapper\\";
        buildJavaDec(endPath);
        buildJavaFile(endPath + "\\" + tableNamerid + ".mapper.xml", sb.toString());
    }
/**
 * 
    * @Title: setColumn  
    * @Description: 创建XML列名
    * @param @param md
    * @param @param columnCount
    * @param @param sb
    * @param @throws SQLException    参数  
    * @return void    返回类型  
    * @throws
 */
    private void setColumn(ResultSetMetaData md, int columnCount, StringBuffer sb) throws SQLException {
        for (int i = 1; i <= columnCount; i++) {
            sb.append(TAB);
            String columnName = dealLine(md, i);
            if(i ==1 ){
            	sb.append("<id column=\"").append(md.getColumnName(i)).append("\" property=\"").append(columnName).append("\" />\r\n");
            }else{
            	sb.append("<result column=\"").append(md.getColumnName(i)).append("\" property=\"").append(columnName).append("\" />\r\n");
            }
        }
    }
    private void setBaseColumn(ResultSetMetaData md, int columnCount, StringBuffer sb) throws SQLException {
        sb.append(TAB);
        for (int i = 1; i <= columnCount; i++) {
        	if(i != columnCount){
        		sb.append(md.getColumnName(i)).append(",");
        	}else{
            	sb.append(md.getColumnName(i)).append("\r\n");
        	}
        }
        sb.append("</sql>");
    }
 private void setBaseWhereColumn(ResultSetMetaData md, int columnCount, StringBuffer sb) throws SQLException {
        
        for (int i = 1; i <= columnCount; i++) {
            sb.append(TAB);
            String columnName = dealLine(md, i);
        	sb.append("<if test=\"").append(columnName).append(" != null and ").append(columnName).append(" != \''\"> and ");
        	sb.append(md.getColumnName(i)).append(" = #{").append(columnName).append("}</if>\r\n");
//        	if("is_delete".equals(md.getColumnName(i))||"is_disable".equals(md.getColumnName(i))){
//            	sb.append("0").append("</if>\r\n");
//        	}else{
//        	}
        }
        sb.append("</sql>");
    }
    
    private void setBaseCommonSql(ResultSetMetaData md, int columnCount, StringBuffer sb) throws SQLException {
         sb.append(TAB);
         sb.append("<include refid=\"table\" />\r\n");
         sb.append(TAB);
         sb.append("WHERE ").append(md.getColumnName(1)).append(" in  \r\n");
         sb.append(TAB).append(TAB);
         sb.append(" (  \r\n");
         sb.append(TAB).append(TAB);
         sb.append(" SELECT  ").append(md.getColumnName(1)).append(" \r\n");
         sb.append(TAB).append(TAB);
         sb.append("FROM \r\n");
         sb.append(TAB).append(TAB).append(TAB);
         sb.append("<include refid=\"table\" />\r\n");
         sb.append(TAB).append(TAB);
         sb.append("WHERE \r\n");
         sb.append(TAB).append(TAB).append(TAB);
         sb.append("1=1").append("  \r\n");
         sb.append(TAB).append(TAB).append(TAB);
         sb.append("AND ").append("  \r\n");
         sb.append(TAB).append(TAB).append(TAB);
         sb.append("<include refid=\"table\" />").append(".").append("`").append(md.getColumnName(1)).append("`\r\n");
         sb.append(TAB).append(TAB).append(TAB);
         sb.append("IN ").append("  \r\n");
         sb.append(TAB).append(TAB).append(TAB);
         sb.append("(\r\n");
         sb.append(TAB).append(TAB).append(TAB).append(TAB);
         sb.append(" SELECT  ").append(md.getColumnName(1)).append(" \r\n");
         sb.append(TAB).append(TAB).append(TAB).append(TAB);
         sb.append("FROM\r\n");
         sb.append(TAB).append(TAB).append(TAB).append(TAB);
         sb.append("(\r\n");
         sb.append(TAB).append(TAB).append(TAB).append(TAB).append(TAB);
         sb.append("SELECT MAX(").append(md.getColumnName(1)).append(") ").append(md.getColumnName(1)).append(",").append(md.getColumnName(2)).append("\r\n");
         sb.append(TAB).append(TAB).append(TAB).append(TAB).append(TAB);
         sb.append("FROM(\r\n");
         sb.append(TAB).append(TAB).append(TAB).append(TAB).append(TAB);
         sb.append("SELECT ").append(md.getColumnName(1)).append(",").append(md.getColumnName(2)).append(", id, ").append("CASE WHEN ").append(md.getColumnName(5)).append(" = 1 THEN 1 ELSE 0 END AS ").append(md.getColumnName(5)).append("\r\n");
         sb.append(TAB).append(TAB).append(TAB).append(TAB).append(TAB);
         sb.append("FROM(\r\n");
         sb.append(TAB).append(TAB).append(TAB).append(TAB).append(TAB).append(TAB);
         sb.append("SELECT ").append(md.getColumnName(1)).append(",").append(md.getColumnName(2)).append("\r\n");
         sb.append(TAB).append(TAB).append(TAB).append(TAB).append(TAB).append(TAB);
         sb.append("FROM\r\n");
         sb.append(TAB).append(TAB).append(TAB).append(TAB).append(TAB).append(TAB);
         sb.append("<include refid=\"table\" />\r\n");
         sb.append(TAB).append(TAB).append(TAB).append(TAB).append(TAB).append(TAB);
         sb.append("WHERE \r\n");
         sb.append(TAB).append(TAB).append(TAB).append(TAB).append(TAB).append(TAB);
         sb.append(md.getColumnName(5)).append(" = 0\r\n");
         sb.append(TAB).append(TAB).append(TAB).append(TAB).append(TAB);
         sb.append(") AS l\r\n");
         sb.append(TAB).append(TAB).append(TAB).append(TAB).append(TAB);
         sb.append("LEFT JOIN\r\n");
         sb.append(TAB).append(TAB).append(TAB).append(TAB).append(TAB);
         sb.append("(\r\n");
         sb.append(TAB).append(TAB).append(TAB).append(TAB).append(TAB).append(TAB);
         sb.append("SELECT ").append(md.getColumnName(2)).append("  id,").append(md.getColumnName(5)).append("\r\n");
         sb.append(TAB).append(TAB).append(TAB).append(TAB).append(TAB).append(TAB);
         sb.append("FROM \r\n");
         sb.append(TAB).append(TAB).append(TAB).append(TAB).append(TAB).append(TAB);
         sb.append("<include refid=\"table\" />\r\n");
         sb.append(TAB).append(TAB).append(TAB).append(TAB).append(TAB).append(TAB);
         sb.append("WHERE \r\n");
         sb.append(TAB).append(TAB).append(TAB).append(TAB).append(TAB).append(TAB);
         sb.append(md.getColumnName(5)).append(" = 1\r\n");
         sb.append(TAB).append(TAB).append(TAB).append(TAB).append(TAB);
         sb.append(") AS r \r\n");
         sb.append(TAB).append(TAB).append(TAB).append(TAB).append(TAB);
         sb.append("ON l.").append(md.getColumnName(1)).append(" = ").append("r.id\r\n");
         sb.append(TAB).append(TAB).append(TAB).append(TAB);
         sb.append(") AS not_active \r\n");
         sb.append(TAB).append(TAB).append(TAB).append(TAB);
         sb.append("WHERE \r\n");
         sb.append(TAB).append(TAB).append(TAB).append(TAB);
         sb.append(md.getColumnName(5)).append(" = 0\r\n");
         sb.append(TAB).append(TAB).append(TAB).append(TAB);
         sb.append("GROUP BY ").append(md.getColumnName(2)).append("\r\n");
         sb.append(TAB).append(TAB).append(TAB).append(TAB);
         sb.append(") AS a \r\n");
         sb.append(TAB).append(TAB).append(TAB);
         sb.append(") \r\n");
         sb.append(TAB).append(TAB);
         sb.append(") \r\n");
         sb.append(TAB);
         sb.append("<include refid=\"condition_sql\" />\r\n");
         sb.append(TAB);
         sb.append("ORDER BY create_time\r\n");
        sb.append("</sql>");
    }
    
  private void insertColumn(ResultSetMetaData md, int columnCount, StringBuffer sb) throws SQLException {
	  	sb.append(LINE);
        sb.append(TAB);
        sb.append("insert into");
	  	sb.append(LINE);
        sb.append(TAB);
        sb.append("<include refid=\"table\" />\r\n");
        sb.append(TAB);
        sb.append("(");
        for (int i = 2; i <= columnCount; i++) {
            if(i != columnCount ){
            	sb.append(md.getColumnName(i)).append(",");
            }else{
            	sb.append(md.getColumnName(i));
            }
        }
        sb.append(")\r\n");
        sb.append(TAB);
        sb.append("values (\r\n");
        sb.append(TAB);
        for(int i = 2; i <= columnCount; i++){
            String columnName = dealLine(md, i);
            if(i != columnCount ){
                sb.append("#{").append(columnName).append("},");
            }else{
                sb.append("#{").append(columnName).append("})");
            }
        }
        sb.append(LINE);
        sb.append("</insert>");
    }
  
  private void getById(ResultSetMetaData md, int columnCount, StringBuffer sb) throws SQLException {
      sb.append("<select id=\"getById\" parameterType=\"string\" resultMap=\"BaseResultMap\">\r\n");
      sb.append(TAB);
      sb.append("SELECT * FROM\r\n");
      sb.append(TAB);
      sb.append("<include refid=\"table\" />\r\n");
      sb.append(TAB);
      sb.append("WHERE ").append(md.getColumnName(1)).append(" = #{").append(dealLine(md, 1)).append("}\r\n");
      sb.append("</select>");
  }
    
    private void listPage(ResultSetMetaData md, int columnCount, StringBuffer sb) throws SQLException {
        sb.append("<select id=\"listPage\" parameterType=\"java.util.Map\" resultMap=\"BaseResultMap\">\r\n");
        sb.append(TAB);
        sb.append("SELECT * FROM\r\n");
        sb.append(TAB);
        sb.append("<include refid=\"baseCommonSql_sql\" />\r\n");
        sb.append(TAB);
        sb.append("<if test=\"pageFirst != null and pageSize != null and pageSize != 0\">\r\n");
        sb.append(TAB).append(TAB);
        sb.append("limit #{pageFirst} , #{pageSize}\r\n");
        sb.append(TAB);
        sb.append("</if>\r\n");
        sb.append("</select>");
    }
    private void listPageCount(ResultSetMetaData md, int columnCount, StringBuffer sb) throws SQLException {
	  	sb.append(LINE);
        sb.append("<select id=\"listPageCount\" parameterType=\"java.util.Map\" resultType=\"long\">\r\n");
        sb.append(TAB);
        sb.append("SELECT COUNT(1) FROM\r\n");
        sb.append(TAB);
        sb.append("<include refid=\"baseCommonSql_sql\" />\r\n");
        sb.append("</select>");
    }
    
    private void batchInsert(ResultSetMetaData md, int columnCount, StringBuffer sb) throws SQLException {
	  	sb.append(LINE);
        sb.append(TAB);
        sb.append("insert into");
	  	sb.append(LINE);
        sb.append(TAB);
        sb.append("<include refid=\"table\" />\r\n");
        sb.append(TAB);
        sb.append("(");
        for (int i = 2; i <= columnCount; i++) {
            if(i != columnCount ){
            	sb.append(md.getColumnName(i)).append(",");
            }else{
            	sb.append(md.getColumnName(i));
            }
        }
        sb.append(")\r\n");
        sb.append(TAB);
        sb.append("values \r\n");
        sb.append(TAB);
        sb.append("<foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\">\r\n");
        sb.append(TAB);
        sb.append("(");
        for(int i = 2; i <= columnCount; i++){
            String columnName = dealLine(md, i);
            if(i != columnCount ){
                sb.append("#{item.").append(columnName).append("},");
            }else{
                sb.append("#{item.").append(columnName).append("})");
            }
        }
        sb.append(LINE);
        sb.append(TAB);
        sb.append("</foreach>");
        sb.append(LINE);
        sb.append("</insert>");
    }
    private void getByUserCode(ResultSetMetaData md, int columnCount, StringBuffer sb) throws SQLException {
        sb.append("<select id=\"getByUserCode\" parameterType=\"java.util.Map\" resultMap=\"BaseResultMap\">\r\n");
        sb.append(TAB);
        sb.append("SELECT * FROM\r\n");
        sb.append(TAB);
        sb.append("<include refid=\"table\" />\r\n");
        sb.append(TAB);
        sb.append("WHERE \r\n").append(TAB).append(md.getColumnName(1)).append(" = (\r\n");
        sb.append(TAB).append(TAB);
        sb.append("SELECT \r\n");
        sb.append(TAB).append(TAB);
        sb.append("MAX(").append(md.getColumnName(1)).append(")");
        sb.append(TAB).append(TAB);
        sb.append("FROM \r\n");
        sb.append(TAB).append(TAB);
        sb.append("<include refid=\"table\" />\r\n");
        sb.append(TAB).append(TAB);
        sb.append("WHERE \r\n");
        sb.append(TAB).append(TAB);
        sb.append(md.getColumnName(2)).append(" = #{").append(dealLine(md, 2)).append("})\r\n");
        sb.append(TAB).append(TAB).append(TAB);
        sb.append("and ").append(md.getColumnName(1)).append(" not in ( \r\n");
        sb.append(TAB).append(TAB).append(TAB).append(TAB);
        sb.append(" SELECT  ").append(md.getColumnName(2)).append(" FROM\r\n");
        sb.append(TAB).append(TAB).append(TAB).append(TAB).append(TAB);
        sb.append("<include refid=\"table\" />\r\n");
        sb.append(TAB).append(TAB).append(TAB).append(TAB);
        sb.append("WHERE \r\n");
        sb.append(TAB).append(TAB).append(TAB).append(TAB).append(TAB);
        sb.append("is_active = '0' \r\n");
        sb.append(TAB).append(TAB).append(TAB).append(TAB).append(TAB);
        sb.append("and is_disable = '0' \r\n");
        sb.append(TAB).append(TAB).append(TAB).append(TAB).append(TAB);
        sb.append("and is_delete = '0' \r\n");
        sb.append(TAB).append(TAB).append(TAB).append(TAB);
        sb.append(") \r\n");
        sb.append("</select>");
    }
    
    private void getByUserCodes(ResultSetMetaData md, int columnCount, StringBuffer sb) throws SQLException {
        sb.append("<select id=\"getByUserCodes\"  parameterType=\"java.util.Map\"  resultMap=\"BaseResultMap\">\r\n");
        sb.append(TAB);
        sb.append("SELECT * FROM\r\n");
        sb.append(TAB);
        sb.append("<include refid=\"baseCommonSql_sql\" />\r\n");
        sb.append("</select>");
    }
    private void listBy(ResultSetMetaData md, int columnCount, StringBuffer sb) throws SQLException {
        sb.append("<select id=\"listBy\" parameterType=\"java.util.Map\" resultMap=\"BaseResultMap\">\r\n");
        sb.append(TAB);
        sb.append("SELECT * FROM\r\n");
        sb.append(TAB);
        sb.append("<if test=\"isNotHistory != null and isNotHistory != \'' and isNotHistory == 0\">\r\n");
        sb.append(TAB).append(TAB);
        sb.append("<include refid=\"baseCommonSql_sql\" />\r\n");
        sb.append(TAB);
        sb.append("</if>\r\n");
        sb.append(TAB);
        sb.append("<if test=\"isNotHistory != null and isNotHistory != \'' and isNotHistory == 1\">\r\n");
        sb.append(TAB).append(TAB);
        sb.append("<include refid=\"table\" />\r\n");
        sb.append(TAB).append(TAB);
        sb.append("<where>\r\n");
        sb.append(TAB).append(TAB).append(TAB);
        sb.append("<include refid=\"condition_sql\" />\r\n");
        sb.append(TAB).append(TAB);
        sb.append("</where>\r\n");
        sb.append(TAB);
        sb.append("</if>\r\n");
        sb.append("</select>");
    }
    
    private String dealLine(ResultSetMetaData md, int i) throws SQLException {
        String columnName = md.getColumnName(i);
        columnName = dealName(columnName);
        return columnName;
    }
    /**
     * 
        * @Title: dealName  
        * @Description: 删除下斜线
        * @param @param columnName
        * @param @return    参数  
        * @return String    返回类型  
        * @throws
     */
    private String dealName(String columnName) {
        if (columnName.contains("_")) {
            StringBuffer names = new StringBuffer();
            String arrayName[] = columnName.split("_");
            names.append(arrayName[0]);
            for (int i = 1; i < arrayName.length; i++) {
                String arri=arrayName[i];
                String tmp=arri.substring(0, 1).toUpperCase()+ arri.substring(1, arri.length());
                names.append(tmp);
            }
            columnName=names.toString();
        }
        return columnName;
    }
    /**
     * 
        * @Title: buildJavaDec  
        * @Description: 创建文件夹
        * @param @param filePath
        * @param @param fileContent    参数  
        * @return void    返回类型  
        * @throws
     */
    public void buildJavaDec(String filePath) {
    	
            File file = new File(filePath);
            
            if(!file.exists() && !file.isDirectory()){
            	    file.mkdir();
            }
    }
    /**
     * 
        * @Title: buildJavaFile  
        * @Description: 创建xml文件
        * @param @param filePath
        * @param @param fileContent    参数  
        * @return void    返回类型  
        * @throws
     */
    public void buildJavaFile(String filePath, String fileContent) {
        try {
            File file = new File(filePath);
            FileOutputStream osw = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(osw);
            pw.println(fileContent);
            pw.close();
        } catch (Exception e) {
            System.out.println("生成txt文件出错：" + e.getMessage());
        }
    }

    /**
     * 
        * @Title: main  
        * @Description: main可执行方法
        * @param @param args
        * @param @throws SQLException
        * @param @throws ClassNotFoundException    参数  
        * @return void    返回类型  
        * @throws
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String jdbcString = mysqlUrl;
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(jdbcString, "root", "123456");
        DatabaseMetaData databaseMetaData = con.getMetaData();
        String[] tableType = { "TABLE" };
        ResultSet rs = databaseMetaData.getTables(null, null, "%",tableType);
        JavaToXml d = new JavaToXml();
        while(rs.next()){
            String tableName=rs.getString(3).toString();
            d.tableToXml(con,tableName);
        }
        System.out.println("xml生成ok！！");
    }
}