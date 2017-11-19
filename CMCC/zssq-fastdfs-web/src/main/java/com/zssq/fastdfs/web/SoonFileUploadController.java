package com.zssq.fastdfs.web;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zssq.fastdfs.model.CheckFileModel;
import com.zssq.fastdfs.server.SoonFileService;

/**
 * 文件快传
 * @author power
 *
 */

@Controller
@RequestMapping("/soon")
public class SoonFileUploadController {

	
	@Autowired
	private SoonFileService soonFileService;
	
	/**
	 * 校验文件
	 * @return
	 */
	@RequestMapping("/checkFile")
	@ResponseBody
	public HashMap<String, Object> checkFile(String code){
		
		HashMap<String, Object> ret=new HashMap<>();
		CheckFileModel model=soonFileService.CheckFile(code);
		if(model.isState()){
			//存在
			ret.put("state", "1");
			ret.put("msg","文件存在");
			ret.put("url",model.getUrl());
		}else{
			//不存在
			ret.put("state", "0");
			ret.put("msg","文件不存在");
		}
		
		return ret;
	} 	
	
	
	
	
	/**
	 * 保存 文件信息
	 * @return
	 */
	
	@RequestMapping("/saveCode")
	@ResponseBody
	public HashMap<String, Object> saveFileCode(String code,String url){
		HashMap<String, Object> ret=new HashMap<String, Object>();
		
		if(code==null || code =="" || url==null || url=="" ){
			return null;
		}
		
		try {
			soonFileService.saveFileCode(code, url);
			ret.put("state","0");
		} catch (Exception e) {
			e.printStackTrace();
			ret.put("state","1");
		}
		
		return ret;
	}
	
	
	
	
	
	
	
	
}