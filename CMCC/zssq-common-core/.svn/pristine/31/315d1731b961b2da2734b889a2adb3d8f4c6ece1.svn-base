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
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import com.zssq.utils.StringTools;

 /**
  * 
     * @ClassName: JavaToBean  
     * @Description: bean生产类  
     * @author Mr.K  
     * @date 2016年3月9日  
     *
  */
public class JavaToBean {

    private static final String LINE = "\r\n";
    private static final String TAB = "\t";
    private static final String ENTITY_PATH = "jmpm.forum.facade.user.entity";
    //mysqlUrl
    private static final String mysqlUrl="jdbc:mysql://172.16.127.100:3306/jmpm_forum";
   
    String packages = this.getClass().getPackage().getName().replace("common", "model");;
    private static final String[] notInStr = {"create_time","time_sign","is_disable","is_delete","is_active"};
    private static Map<String, String> map;

    static {
        map = new HashMap<String, String>();
        map.put("VARCHAR", "String");
        map.put("INTEGER", "Integer");
        map.put("BIGINT", "Long");
        map.put("FLOAT", "float");
        map.put("TIMESTAMP", "Date");
        map.put("CHAR", "String");
        map.put("DATETIME", "Date");
        map.put("DATE", "Date");
        map.put("TIMESTAMP_IMPORT", "import java.util.Date");
        map.put("DATETIME_IMPORT","import java.util.Date");
        map.put("INT","Integer");
        map.put("SMALLINT","Integer");
        map.put("TINYINT","Integer");
    }

    public static String getPojoType(String dataType) {
        StringTokenizer st = new StringTokenizer(dataType);
        return map.get(st.nextToken());
    }
    
    public static String getImport(String dataType) {
        if (map.get(dataType)==null||"".equals(map.get(dataType))) {
           return null;    
        }else{
           return map.get(dataType);
        }
    }

 
    public void tableToBean(Connection connection, String tableName) throws SQLException {
        String sql = "select * from " + tableName + " where 1 <> 1";
        PreparedStatement ps = null;
        ResultSet rs = null;
        ps = connection.prepareStatement(sql);
        rs = ps.executeQuery();
        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount();
        StringBuffer sb = new StringBuffer();
        tableName = tableName.substring(0, 1).toUpperCase() + tableName.subSequence(1, tableName.length());
        tableName = this.dealLine(tableName);
        sb.append("package " + ENTITY_PATH + " ;");
        sb.append(LINE);
        importPackage(md, columnCount, sb);
        sb.append(LINE);
        sb.append(LINE);
        sb.append("import jmpm.common.entity.BaseEntity;");
        sb.append(LINE);
        sb.append(LINE);
        sb.append("public class " + tableName + " extends BaseEntity{");
        sb.append(LINE);
        sb.append(LINE);
        sb.append("	").append("private static final long serialVersionUID = 1L;");
        sb.append(LINE);
        sb.append(LINE);
        defProperty(md, columnCount, sb);
        sb.append(LINE);
        genSetGet(md, columnCount, sb);
        sb.append("}");
        String paths = System.getProperty("user.dir");
        String endPath = paths + "\\src\\main\\bean\\";
        buildJavaDec(endPath);
        buildJavaFile(endPath + "\\" + tableName + ".java", sb.toString());
    }
    private void genSetGet(ResultSetMetaData md, int columnCount, StringBuffer sb) throws SQLException {
        for (int i = 1; i <= columnCount; i++) {
        	if(StringTools.isContainsString(md.getColumnName(i),notInStr)){
        		continue;
        	}
            sb.append(TAB);
            String pojoType = getPojoType(md.getColumnTypeName(i));
            String columnName = dealLine(md, i);
            String getName = null;
            String setName = null;
            if (columnName.length() > 1) {
                getName = "public " + pojoType + " get" + columnName.substring(0, 1).toUpperCase()
                        + columnName.substring(1, columnName.length()) + "() {";
                setName = "public void set" + columnName.substring(0, 1).toUpperCase()
                        + columnName.substring(1, columnName.length()) + "(" + pojoType + " " + columnName + ") {";
            } else {
                getName = "public get" + columnName + "() {";
                setName = "public set" + columnName + "(" + pojoType + " " + columnName + ") {";
            }
            sb.append(LINE).append(TAB).append(getName);
            sb.append(LINE).append(TAB).append(TAB);
            sb.append("return " + columnName + ";");
            sb.append(LINE).append(TAB).append("}");
            sb.append(LINE);
            sb.append(LINE).append(TAB).append(setName);
            sb.append(LINE).append(TAB).append(TAB);
            sb.append("this." + columnName + " = " + columnName + ";");
            sb.append(LINE).append(TAB).append("}");
            sb.append(LINE);

        }
    }
    
    private void importPackage(ResultSetMetaData md, int columnCount, StringBuffer sb) throws SQLException {
        for (int i = 1; i <= columnCount; i++) {
            String im=getImport(md.getColumnTypeName(i)+"_IMPORT");
            if (im!=null) {
                sb.append(im+ ";");
                sb.append(LINE);
            }
        }
    }
    //属性定义
    private void defProperty(ResultSetMetaData md, int columnCount, StringBuffer sb) throws SQLException {
        
        for (int i = 1; i <= columnCount; i++) {
        	if(StringTools.isContainsString(md.getColumnName(i),notInStr)){
        		continue;
        	}
            sb.append(TAB);
            String columnName = dealLine(md, i);
            sb.append("private " + getPojoType(md.getColumnTypeName(i)) + " " + columnName + ";");
            sb.append(LINE);
        }
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
    private String dealLine(ResultSetMetaData md, int i) throws SQLException {
        String columnName = md.getColumnName(i);
        columnName = dealName(columnName);
        return columnName;
    }

    private String dealLine(String tableName) {
        tableName = dealName(tableName);
        return tableName;
    }
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

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
    	//数据库的url
        String jdbcString = mysqlUrl;
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(jdbcString, "root", "123456");
        DatabaseMetaData databaseMetaData = con.getMetaData();
        String[] tableType = { "TABLE" };
        ResultSet rs = databaseMetaData.getTables(null, null, "%",tableType);
        JavaToBean d = new JavaToBean();
        while(rs.next()){
            String tableName=rs.getString(3).toString();
            d.tableToBean(con,tableName);
        }
        System.out.print("bean生成OK！！");
    }
}