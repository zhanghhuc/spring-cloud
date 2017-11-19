package com.zssq.fastdfs.util;


import java.util.UUID;

public class UUIDHelper {
	/** 
     * 获得一个UUID 
     * @return String UUID 
     */ 
    public static String getUUID(){ 
        String s = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        return removeStr("-",s); 
    } 
    
    public static String removeStr(String str,String src){
    	String[] srcs = src.split(str);
    	
    	StringBuffer sb = new StringBuffer();
    	
    	for(String s : srcs){
    		sb.append(s);
    	}
    	
    	return sb.toString();
    } 
    
}
