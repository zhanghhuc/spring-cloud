package com.zssq.fastdfs.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.stereotype.Component;

@Component
public class MoveFileDownloadUtil {
	
	
	
	/**
	 * 
	 * @Title: isMoveFile  
	 * Description: 判断是不是 迁移数据 （true-是迁移数据，false-正常数据）
	 * @return    参数  
	 * @return: boolean    返回类型
	 */
	public boolean isMoveFile(String url){
		
		return !(url.substring(0,5).equals("group"));
	}
	
	
	/**
	 * 
	 * @Title: getMoveFile  
	 * @Description: 获取数据迁移文件
	 * @param url
	 * @return    参数  
	 * @return: byte[]    返回类型
	 */
	public byte[] getMoveFile(String ip,String fileUrl){		
		
	 try {
			URL url = new URL(ip+fileUrl.substring(1,fileUrl.length()));
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setConnectTimeout(3*1000);  
	        //防止屏蔽程序抓取而返回403错误  
	        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)"); 
			
	        InputStream inputStream = conn.getInputStream();    
	        
			return readInputStream(inputStream);
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
		 
	} 
	
	
	  /** 
     * 从输入流中获取字节数组 
     * @param inputStream 
     * @return 
     * @throws IOException 
     */  
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {    
        byte[] buffer = new byte[1024];    
        int len = 0;    
        ByteArrayOutputStream bos = new ByteArrayOutputStream();    
        while((len = inputStream.read(buffer)) != -1) {    
            bos.write(buffer, 0, len);    
        }    
        bos.close();    
        return bos.toByteArray();    
    }  
	
	
}