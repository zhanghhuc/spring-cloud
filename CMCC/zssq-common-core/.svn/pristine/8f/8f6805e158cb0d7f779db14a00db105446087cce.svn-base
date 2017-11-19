package com.zssq.core.tools.mysqlToOther;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

 /**
  * 
     * @ClassName: JavaToDaoImp  
     * @Description: mysql生成daoImp
     * @author Mr.K  
     * @date 2016年3月10日  
     *
  */
public class JavaToDaoImpl {

    private static final String LINE = "\r\n";
    // Dao放置路径
    private static final String PACKAGEDAONAME = "jmpm.forum.service.dao.";
    private static final String PACKAGENAME = "jmpm.forum.service.dao.impl";
    private static final String ENTITYPATH = "jmpm.forum.facade.user.entity.";
    private static final String mysqlUrl="jdbc:mysql://172.16.127.100:3306/jmpm_forum";

    public void tableToDao(Connection connection, String tableName) throws SQLException {
        StringBuffer sb = new StringBuffer();
        tableName = tableName.substring(0, 1).toUpperCase() + tableName.subSequence(1, tableName.length());
        tableName = dealName(tableName);
        sb.append("package " + PACKAGENAME + ";");
        sb.append(LINE);
        sb.append(LINE);
        sb.append("import org.springframework.stereotype.Repository;");
        sb.append(LINE);
        sb.append("import jmpm.common.core.dao.BaseDaoImpl;");
        sb.append(LINE);
        sb.append("import ").append(ENTITYPATH).append(tableName).append(";");
        sb.append(LINE);
        sb.append("import ").append(PACKAGEDAONAME).append(tableName).append("Dao;");
        sb.append(LINE);
        sb.append(LINE);
        sb.append("@Repository(\"").append(toLowName(tableName)).append("Dao\")");
        sb.append(LINE);
        sb.append("public class ").append(tableName).append("DaoImpl ").append("extends BaseDaoImpl<").append(tableName).append("> ").append("implements ").append(tableName).append("Dao {");
        sb.append(LINE);
        sb.append(LINE);
        sb.append("}");
        String paths = System.getProperty("user.dir");
        String endPath = paths + "\\src\\main\\daoImpl\\";
        buildJavaDec(endPath);
        buildJavaFile(endPath + "\\" + tableName + "DaoImpl.java", sb.toString());
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
    private String toLowName(String columnName) {
        return columnName.substring(0,1).toLowerCase()+columnName.substring(1,columnName.length());
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
    	//数据库的url
        String jdbcString = mysqlUrl;
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(jdbcString, "root", "123456");
        DatabaseMetaData databaseMetaData = con.getMetaData();
        String[] tableType = { "TABLE" };
        ResultSet rs = databaseMetaData.getTables(null, null, "%",tableType);
        JavaToDaoImpl d = new JavaToDaoImpl();
        while(rs.next()){
            String tableName=rs.getString(3).toString();
            d.tableToDao(con,tableName);
        }
        System.out.print("daoImpl生成OK！！");
    }
}