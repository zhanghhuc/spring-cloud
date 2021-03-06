package com.zssq.fastdfs.web;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.zssq.fastdfs.FastDFSTemplate;
import com.zssq.fastdfs.FileInfo;
import com.zssq.fastdfs.common.NameValuePair;
import com.zssq.fastdfs.config.FastDFSConfig;
import com.zssq.fastdfs.util.MoveFileDownloadUtil;

import net.coobird.thumbnailator.Thumbnails;

/**
 * 文件上传操作控制转发，使用 SpringMVC 映射请求路径，供前端项目组调用
 * 
 * @author ZhaoYi
 */
@Controller
@RequestMapping("/file")
public class FastDFSController {

	private Log log = LogFactory.getLog(FastDFSController.class);
	
	/** 预定义HTTP请求参数，表示本地文件的完全限定名 */
	//public static final String HTTP_REQUEST_PARAM_FILEDESC = "FILEDESC";
	
	/** 预定义HTTP请求参数，表示远程文件ID */
	public static final String HTTP_REQUEST_PARAM_REMOTEFILEID = "url";
	
	@Autowired
	private FastDFSTemplate fastDFSTemplate;
	
	@Autowired
	private FastDFSConfig fastDFSConfig;
	
	@Autowired
	private MoveFileDownloadUtil moveFileUtil;
	
	@RequestMapping(value = "/showUploadView")  
	public String showUploadView(){
		return "upload";
	}
	
	/**
	 * 文件上传
	 * 
	 * @param request
	 * 			HTTP 请求头
	 * @return JSON 字符串形式的上传结果信息
	 */
	@RequestMapping(value = "/upload") 
	@ResponseBody
    public Map<String, Object> upload(HttpServletRequest request) {  
			
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
            			
            			metaMap.put("url",remoteFileId);
            			Map<String, Object> result = new HashMap<String, Object>();
            			result.put("body", metaMap);
            			result.put("returnCode", "1");
            			result.put("returnTip", "上传成功");
            			
            			return result;                    	
                    }                     	  
                }	
            }                 	
        	return getFailureResponse("服务器未接收到文件");
		} catch (Exception e) {
			log.error("文件上传出错：服务器处理异常", e);                 	
			e.printStackTrace();
        	return getFailureResponse("服务器处理异常");	
		}
    }
	
	/**
	 * 上传图片，并根据指定的缩略图规格，生成图片缩略图
	 */
	@RequestMapping(value = "/upload/image") 
	@ResponseBody
    public Map<String, Object> uploadImageFile(HttpServletRequest request,String scale) {  
			
        try { 
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());   
            if(multipartResolver.isMultipart(request)){		// 判断是否为文件上传请求，即请求头中包含：Content-Type:multipart/form-data;   
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
                Iterator<String> iter = multiRequest.getFileNames();		// 获取待上传的文件名  
                while(iter.hasNext()){ 
                    MultipartFile file = multiRequest.getFile(iter.next());	// 获取待上传文件对象
                    if(null != file){
                    	Map<String, String> metaMap = new HashMap<String, String>();
                    	
                    	String sourceFileName = file.getOriginalFilename();									// 待上传文件名称
                    	String fileNameNoExt = StringUtils.substringBefore(sourceFileName, ".");			// 待上传文件名称，不包含扩展名
                    	String fileExtName = sourceFileName.substring(sourceFileName.lastIndexOf(".") + 1);	// 待上传文件扩展名
                    	                    	
                    	// 上传主文件
                    	String masterFileId = fastDFSTemplate.uploadFile(file.getBytes(), fileExtName, metaMap);
                    	
        				String thumbnailPath = fastDFSConfig.getThumbnailPath() + File.separator + fileNameNoExt + "-" + scale + "." + fileExtName;
        				String[] lengthAndWidth = StringUtils.split(scale, "x");
        				
        				//第一次参数宽，第二个高
                    	Thumbnails.of(file.getInputStream()).size(Integer.valueOf(lengthAndWidth[0]), Integer.valueOf(lengthAndWidth[1])).toFile(thumbnailPath);
                    	
            			// 上传从文件
            			String slaveFileId = fastDFSTemplate.uploadSlaveFile(masterFileId, "-" + scale, thumbnailPath, fileExtName, null);
            			
            			log.debug("主从文件上传：从文件ID：" + slaveFileId);
            			 
            			File tempSlaveFile = new File(thumbnailPath);
            			if(tempSlaveFile.exists()) {
            				tempSlaveFile.delete();
            			}

            			metaMap.put("url", masterFileId);
            			metaMap.put("tbUrl", slaveFileId);
            			
            			Map<String, Object> result = new HashMap<String, Object>();
            			result.put("body", metaMap);
            			result.put("returnCode", "1");
            			result.put("returnTip", "上传成功");
            			
            			return result;                    	
                    }                     	  
                }	
            }                 	
        	return getFailureResponse("服务器未接收到文件");
		} catch (Exception e) {
			log.error("文件上传出错：服务器处理异常", e);                 	
        	return getFailureResponse("服务器处理异常");	
		}
    }
	
	/**
	 * 获取请求失败时的响应信息
	 * 
	 * @param prompt
	 * 			响应信息说明
	 * @return 以 Map 封装的响应信息
	 */
	private Map<String, Object> getFailureResponse(String prompt) {
		
		Map<String, Object> metaMap = new HashMap<String, Object>();
    	metaMap.put("returnCode", "0");
    	metaMap.put("returnTip", prompt);                    	
    	return metaMap;
	}
	
	/** 
	 * 文件下载，需指定远程文件ID和本地文件完全限定名(用于文件写入)
	 * 
	 * @param request Http 请求参数
	 * @return 0, 下载成功; 1, 下载失败
	 */
	@RequestMapping("/download")
	public void download(HttpServletRequest request, HttpServletResponse response,String url,String fileName,String fileIp) {
		byte[] fileBuff=null;
		if(url==null  || fileName==null  || url.equals("")  || fileName.equals("")){
			return;
		}
		
		if(moveFileUtil.isMoveFile(url)){
			//迁移数据
			fileBuff =moveFileUtil.getMoveFile(fileIp,url);
		}else{
			//正常数据
			fileBuff = fastDFSTemplate.downloadByByte(url);// 文件下载，返回字节流
		}
		
	  /*NameValuePair[] metas = fastDFSTemplate.getFileMetaInfo(url);	// 获取远程文件 Meta 信息
		Map<String, String> metaMap = new HashMap<String, String>();
		if(metas != null && metas.length > 0){
			for(NameValuePair nameValuePair : metas){
				metaMap.put(nameValuePair.getName(), nameValuePair.getValue());
			}
		}*/
		
		try {
			//出来下载名称乱码
			String codedfilename = null;  
			String agent = request.getHeader("USER-AGENT"); 
			
			if (null != agent && -1 != agent.indexOf("MSIE") || null != agent  
                    && -1 != agent.indexOf("Trident") || null != agent && -1 != agent.indexOf("Edge")) {// ie  
   
                String name = java.net.URLEncoder.encode(fileName, "UTF8");  
   
                codedfilename = name;  
            } else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等  
   
                codedfilename = new String(fileName.getBytes("UTF-8"), "iso-8859-1");  
            }  
			
			response.setContentType("application/octet-stream");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + codedfilename);
			OutputStream os = response.getOutputStream();
			os.write(fileBuff);
			os.flush();
			os.close();
		} catch (Exception e) {
			log.error("下载文件出错", e);
		} 
	}
	
	/**
	 * 删除远程文件，需指定远程文件ID
	 * 
	 * @param request Http 请求参数
	 * @return 0, 下载成功; 1, 下载失败
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public 	Map<String, Object> deleteFile(HttpServletRequest request) {
		
		String remoteFileId = request.getParameter(HTTP_REQUEST_PARAM_REMOTEFILEID);			
		int state= fastDFSTemplate.deleteFile(remoteFileId);
		Map<String, Object> metaMap = new HashMap<String, Object>();
    	metaMap.put("returnCode", state==0?1:0);
    	metaMap.put("returnTip", state==0?"操作成":"操作失败");                
    	metaMap.put("body", "");
    	
    	return metaMap;
	}
	
	/**
	 * 获取远程文件 Meta 信息，需指定远程文件ID
	 * 
	 * @param request Http 请求参数
	 * @return Meta 信息，JSON 形式字符串，未获取到时返回null
	 */
	@RequestMapping("/get/fileinfo")
	@ResponseBody
	public Map<String, Object> getFileMeta(HttpServletRequest request) {
		
		String remoteFileId = request.getParameter(HTTP_REQUEST_PARAM_REMOTEFILEID);
		NameValuePair[] metas = fastDFSTemplate.getFileMetaInfo(remoteFileId);		// 远程文件meta信息
		FileInfo fileInfo = fastDFSTemplate.queryFileInfo(remoteFileId);			// 远程文件存储信息
		
		Map<String, String> metaMap = new HashMap<String, String>();
		if(metas != null && metas.length > 0){
			for(NameValuePair nameValuePair : metas){
				metaMap.put(nameValuePair.getName(), nameValuePair.getValue());
			}
		}
		
		metaMap.put("createtime", String.valueOf(fileInfo.getCreateTimestamp().getTime()));
		metaMap.put("filesize", String.valueOf(fileInfo.getFileSize()));
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("body", metaMap);
		
		result.put("returnCode", "1");
		result.put("returnTip", "获取文件信息成功");
		
		return result;
	}
}
