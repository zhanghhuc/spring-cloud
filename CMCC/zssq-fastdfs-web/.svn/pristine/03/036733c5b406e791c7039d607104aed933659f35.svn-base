package com.zssq.fastdfs.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.zssq.fastdfs.FastDFSTemplate;

@Component
public class UploadFileUtil {

	@Autowired
	private FastDFSTemplate fastDFSTemplate;
	
	/**
	 *   断点续传-上传文件
	 * @param multipartResolver
	 * @param request
	 * @return
	 */
	public String[] UploadFileAppend(CommonsMultipartResolver multipartResolver,HttpServletRequest request){
		
		 try { 
	            if(multipartResolver.isMultipart(request)){		// 判断是否为文件上传请求，即请求头中包含：Content-Type:multipart/form-data;   
	                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
	                Iterator<String> iter = multiRequest.getFileNames();		// 获取待上传的文件名  
	                while(iter.hasNext()){ 
	                    MultipartFile file = multiRequest.getFile(iter.next());	// 获取待上传文件对象
	                    if(null != file){
	                    	
	                    	String sourceFileName = file.getOriginalFilename();
	            			byte[] fileBuff = file.getBytes();
	            			String[] fileId = fastDFSTemplate.uploadAppenderFile(fileBuff, sourceFileName.substring(sourceFileName.lastIndexOf(".") + 1), null);
	            			
	            			return fileId;
	            			
	                    }                     	  
	                }	
	            }                 	
	            
	        	return null;
			} catch (Exception e) {
				e.printStackTrace();
				
	        	return null;	
			}
	}
	
	
	
	/**
	 *  追加文件
	 * @param multipartResolver
	 * @param request
	 * @param groupId
	 * @param fileName
	 * @return
	 */
	public int AppendFile(CommonsMultipartResolver multipartResolver,HttpServletRequest request,String groupId,String fileName){
		
		int ret=1;
		 
	    try { 
	        if(multipartResolver.isMultipart(request)){		// 判断是否为文件上传请求，即请求头中包含：Content-Type:multipart/form-data;   
	            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
	            Iterator<String> iter = multiRequest.getFileNames();		// 获取待上传的文件名  
	            while(iter.hasNext()){ 
	                MultipartFile file = multiRequest.getFile(iter.next());	// 获取待上传文件对象
	                if(null != file){
	                	byte[] fileBuff = file.getBytes();
	                	//上传成功后 ret=0
	        		ret=fastDFSTemplate.appendFile(groupId, fileName, fileBuff);
	                }                     	  
	            }	
	        }                 	
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return ret;
	}
}
