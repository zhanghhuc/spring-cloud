package com.zssq.fastdfs.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.zssq.fastdfs.FastDFSTemplate;
import com.zssq.fastdfs.util.ueditor.UeditorConstants;

/**
 * 文件上传操作控制转发，使用 SpringMVC 映射请求路径，供前端项目组调用
 * 
 * @author ZhaoYi
 */
@Controller
@RequestMapping("/file")
public class BaiduController {

	private Log log = LogFactory.getLog(BaiduController.class);
	
	/** 预定义HTTP请求参数，表示本地文件的完全限定名 */
	//public static final String HTTP_REQUEST_PARAM_FILEDESC = "FILEDESC";
	
	/** 预定义HTTP请求参数，表示远程文件ID */
	public static final String HTTP_REQUEST_PARAM_REMOTEFILEID = "url";
	
	@Autowired
	private FastDFSTemplate fastDFSTemplate;
	
	
	/**
	 * 文件上传
	 * 
	 * @param request
	 * 			HTTP 请求头
	 * @return JSON 字符串形式的上传结果信息
	 */
	@RequestMapping(value = "/baiduUpload") 
	@ResponseBody
    public void upload(HttpServletRequest request, HttpServletResponse response) {  
		
 		JSONObject jsonRet=new JSONObject();
		String action=request.getParameter("action");
		String callback=request.getParameter("callback");
		String fileHost=request.getParameter("fileHost");
		
		try {
			if(action!=null && action.equals("config")){
				response.getWriter().print(callback+"("+UeditorConstants.getConfig()+");");
				return;
				
			}else{
				
			    try { 
			        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());   
			        if(multipartResolver.isMultipart(request)){		// 判断是否为文件上传请求，即请求头中包含：Content-Type:multipart/form-data;   
			            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
			            Iterator<String> iter = multiRequest.getFileNames();		// 获取待上传的文件名  
			            while(iter.hasNext()){ 
			                MultipartFile file = multiRequest.getFile(iter.next());	// 获取待上传文件对象
			                if(null != file){
			                	Map<String, String> metaMap = new HashMap<String, String>();
			                	
			                	String sourceFileName = file.getOriginalFilename();
			                	metaMap.put("sourcename", sourceFileName);
			                	
			                	log.info("上传文件：" + sourceFileName);
			                	
			        			byte[] fileBuff = file.getBytes();
			        			
			        			String remoteFileId = fastDFSTemplate.uploadFile(fileBuff, sourceFileName.substring(sourceFileName.lastIndexOf(".") + 1), metaMap);
			        			
			        			jsonRet.put("original", sourceFileName);
			        			jsonRet.put("fileName",remoteFileId);
			        			jsonRet.put("type",sourceFileName.substring(sourceFileName.lastIndexOf(".") ));
			        			jsonRet.put("url",fileHost+"/"+remoteFileId);
			        			jsonRet.put("size",file.getSize());
			        			jsonRet.put("title",sourceFileName);
			        			jsonRet.put("state","SUCCESS");
			        			
			                }                     	  
			            }	
			        }                 	
			        
				} catch (Exception e) {
					log.error("文件上传出错：服务器处理异常", e);                 	
					e.printStackTrace();
					jsonRet.put("state", "ERROR");
				}
		}
			
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.getWriter().print(jsonRet.toJSONString());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
	
	
	
	

}
