package com.zssq.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import net.sf.json.JSONArray;

public class JUnitTools {

	/**
	 * 
	    * @Title: findOneByJDBC  
	    * @Description: 查询最新信息
	    * @param @param ip                  数据库IP
	    * @param @param dbName      数据库名称
	    * @param @param username   数据库用户名
	    * @param @param password    数据库密码
	    * @param @param tableName  表名称
	    * @param @param code             Code
	    * @param @return    参数  
	    * @return String    返回类型  
	    * @throws 
	    * @admin z.h
	 */
public Map<String,String> getInfoByJDBC(String ip,String dbName,String username,String password,String tableName,String code){
		
		HashMap<String, String> hashMap = new HashMap<String,String>();
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url="jdbc:mysql://"+ip+":3306/"+dbName+"?autoReconnect=true&useUnicode=true&characterEncoding=utf-8";
			
			//mydb为数据库
			 conn= DriverManager.getConnection(url,username,password);
			 
			Statement statement = conn.createStatement();
			
			String sql = "select * from "+tableName
					+ " where "+tableName+"_id in ("
					+ " select  max("+tableName+"_id)  from  "+tableName 
			                + "  where is_active = 0  "
			                + "  and    is_disable = 0  "
			                + "  and    is_delete=0  "
					        + "  and  "+tableName+"_code = "+"'"+code+"'"
				        	+ "  and  "+tableName+"_id  not  in "
				        			+ " (   select   "+tableName+"_code from  "+tableName+ "  where is_active=1)  "
				    +")";
			
			ResultSet rs = statement.executeQuery(sql);
			ResultSetMetaData md = rs.getMetaData();
	        int columnCount = md.getColumnCount();
			
			while (rs.next()) {
				for (int i = 1 ;  i < columnCount; i++ ) {
					hashMap.put(dealName(md.getColumnName(i)), rs.getString(md.getColumnName(i)));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//关闭连接
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return hashMap;
		
	}

public Map<String,String> getInfoByJDBCNoDis(String ip,String dbName,String username,String password,String tableName,String code){
	
	HashMap<String, String> hashMap = new HashMap<String,String>();
	Connection conn=null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		
		String url="jdbc:mysql://"+ip+":3306/"+dbName+"?autoReconnect=true&useUnicode=true&characterEncoding=utf-8";
		
		//mydb为数据库
		 conn= DriverManager.getConnection(url,username,password);
		 
		Statement statement = conn.createStatement();
		
		String sql = "select * from "+tableName
				+ " where "+tableName+"_id in ("
				+ " select  max("+tableName+"_id)  from  "+tableName 
		                + "  where is_active = 0  "
		                + "  and    is_disenable = 0  "
		                + "  and    is_delete=0  "
				        + "  and  "+tableName+"_code = "+"'"+code+"'"
			        	+ "  and  "+tableName+"_id  not  in "
			        			+ " (   select   "+tableName+"_code from  "+tableName+ "  where is_active=1)  "
			    +")";
		
		ResultSet rs = statement.executeQuery(sql);
		ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount();
		
		while (rs.next()) {
			for (int i = 1 ;  i < columnCount; i++ ) {
				hashMap.put(dealName(md.getColumnName(i)), rs.getString(md.getColumnName(i)));
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		//关闭连接
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return hashMap;
	
}
/**
 * 
    * @Title: deleteTestData  
    * @Description: 删除
    * @param @param ip
    * @param @param dbName
    * @param @param username
    * @param @param password
    * @param @param tableName
    * @param @param code    参数  
    * @return void    返回类型  
    * @throws 
    * @admin z.h
 */
public void deleteTestData(String ip,String dbName,String username,String password,String tableName,ArrayList<String> code){
	
	Connection conn=null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		
		String url="jdbc:mysql://"+ip+":3306/"+dbName+"?autoReconnect=true&useUnicode=true&characterEncoding=utf-8";
		
		//mydb为数据库
		 conn= DriverManager.getConnection(url,username,password);
		 
		Statement statement = conn.createStatement();
		String codestr ="'";
		for(int i=0 ; i<code.size() ; i++){
			if (i != code.size())
				codestr = codestr+code.get(i)+"','";
			else 
				codestr = codestr+code.get(i)+"'" ;
		}
		String sql = "delete from "+tableName
				+ " where "+ tableName+"_code in ("+codestr+")";
				
		statement.executeUpdate(sql); 
		System.out.println("+++++++++++++++"+sql+"+++++++++++++++++");
	
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		//关闭连接
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}


	/**
	 * 删除下划线首字母大写
	 * @param columnName
	 * @return
	 */
	 private String dealName(String columnName) {
	        if (columnName.contains("_")) {
	            StringBuffer names = new StringBuffer();
	            String arrayName[] = columnName.split("_");
	            names.append(arrayName[0]);
	            for (int i = 1; i < arrayName.length; i++) {
	                String arri=arrayName[i];
	                String tmp=arri.substring(0, 1)+ arri.substring(1, arri.length());
	                names.append(tmp);
	            }
	            columnName=names.toString();
	        }
	        return columnName;
	    }
	
}
