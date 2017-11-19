package com.zssq.fastdfs.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.zssq.fastdfs.server.FileServer;
import com.zssq.fastdfs.util.UploadFileUtil;
import com.zssq.fastdfs.web.vo.FileInfoVo;
import com.zssq.fastdfs.ClientGlobal;
import com.zssq.fastdfs.FastDFSTemplate;
import com.zssq.fastdfs.FileInfo;
import com.zssq.fastdfs.StorageClient1;
import com.zssq.fastdfs.StorageServer;
import com.zssq.fastdfs.TrackerServer;
import com.zssq.fastdfs.config.FastDFSConfig;

@Controller
@RequestMapping("/file")
public class BreakpointUploadController {


	private Log log = LogFactory.getLog(FastDFSController.class);
	
	/** 预定义HTTP请求参数，表示本地文件的完全限定名 */
	public static final String HTTP_REQUEST_PARAM_FILEDESC = "FILEDESC";
	
	/** 预定义HTTP请求参数，表示远程文件ID */
	public static final String HTTP_REQUEST_PARAM_REMOTEFILEID = "REMOTEFILEID";
	
	@Autowired
	private UploadFileUtil uploadFileUtil;
	
	@Autowired
	private FileServer fileServer; 
	
	
	/**
	 * 断点续传
	 * @param fv
	 * @param request
	 * @return
	 */
	
	@RequestMapping("breakpointUpload") 
	@ResponseBody
	public Map<String,Object> tests(FileInfoVo fv,HttpServletRequest request){
		Map<String, Object> retMap=new HashMap<>();
		//默认值上传失败
		retMap.put("returnCode", "0");
		retMap.put("body","");
		
		String [] fileInfo=null;
		
		if(fv==null){
			retMap.put("returnTip","参数错误");
			return retMap;
		}
		
		 //获得文件
		 CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		//判断fv.getChunk()==0 上传 else  用uid数据库校验文件信息  判断 thunk 和数据库是否匹配，如果匹配正常，否则失败

		 //判断首次上传文件
		 if(fv.getChunk()==0){
			 
			 //上传文件
			 fileInfo=uploadFileUtil.UploadFileAppend(multipartResolver, request);
			  
			 //判断文件上传是否成功
				if(fileInfo==null || fileInfo.length!=2){
					//返回失败结果
					retMap.put("returnTip","上传失败");
					return retMap;
				}				 
				fv.setFileName(fileInfo[1]);
				fv.setFileGroupId(fileInfo[0]);
				//记录数据库-完成上传
				int ret=fileServer.FirstAddFile(fv);
				if(ret!=2){
					retMap.put("returnTip","上传失败");
					return null;
				}
				
				HashMap<String, Object> metaMap=new HashMap<>();
				metaMap.put("groupID", fv.getFileGroupId());
				metaMap.put("fileName",fv.getFileName());
				metaMap.put("url",fileInfo[0]+"/"+fileInfo[1]);

				retMap.put("returnCode", "1");
				retMap.put("body", metaMap);
				retMap.put("returnTip","上传成功");
			
				//返回上传成功
		  }else{
			  
			  //校验文件
			  if(checkFile(fv)==-1){
				  retMap.put("returnTip","文件校验失败");
				  return retMap;
			  }
			  //校验文件
			  if(checkFile(fv)==-2){
				  retMap.put("returnTip","跳过验证");
				  retMap.put("returnCode","1");
				  return retMap;
				  
			  }
			  
			  
			 int ret= uploadFileUtil.AppendFile(multipartResolver, request,fv.getFileGroupId(),fv.getFileName());
			 
			 //追加失败
			 if(ret!=0){
				 retMap.put("returnTip","续传失败");
				return retMap; 
			 }
			 ret=fileServer.FileAppend(fv.getChunk(),fv.getUid());
			  //添加数据库记录
			 if(ret!=1){
				 retMap.put("returnTip","续传失败");
				 return retMap;
			 }
			 //返回成功
			 retMap.put("returnCode","1");
			 retMap.put("returnTip","续传成功");
		  }
		 
		 //判断最后一次上传后 删除文件
		 if(fv.getChunks()-fv.getChunk()==1){
			 //最后一次上传更新表
			 fileServer.UpdateFileState(fv.getUid());
		 }
		 
		return retMap;
	}
	
	
	/**
	 * 校验文件 
	 * @return
	 */
	private int checkFile(FileInfoVo fv){
		
		
		  int index= fileServer.getFileIndex(fv);
		  if(index==fv.getChunk()){
			  return -2;
		  }
		  if(index==-1){
			 //校验失败
			  return -1;
		  }
			  	//校验呵呵
		   if((index+1)!=fv.getChunk()){
			   return -1;
		   }
		return 1;
	}
	
	
}
