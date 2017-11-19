package com.zssq.disk.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.dao.pojo.DiskFile;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.disk.vo.CopyFoladerVO;
import com.zssq.disk.vo.CopyVO;
import com.zssq.disk.vo.CreateVO;
import com.zssq.disk.vo.DelFileVO;
import com.zssq.disk.vo.DeleteFolderVO;
import com.zssq.disk.vo.DiskManListVO;
import com.zssq.disk.vo.EmptyFileVO;
import com.zssq.disk.vo.FileInfoVO;
import com.zssq.disk.vo.GetFolderVO;
import com.zssq.disk.vo.MoveVO;
import com.zssq.disk.vo.NewNameVO;
import com.zssq.disk.vo.OpenFolderListVO;
import com.zssq.disk.vo.QueryFileNameVO;
import com.zssq.disk.vo.RecoveryVO;
import com.zssq.disk.vo.ReductionVO;
import com.zssq.exceptions.BusinessException;
import com.zssq.model.CopyFileAllVO;
import com.zssq.model.CopyFileVO;
import com.zssq.model.CreatFolderVO;
import com.zssq.model.FileVO;
import com.zssq.model.MoveFileVO;
import com.zssq.model.RFileVO;
import com.zssq.model.ReturnMsg;
import com.zssq.model.RnameVO;
import com.zssq.pojo.Message;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.DiskFileService;
import com.zssq.service.ISysUserService;
import com.zssq.utils.DateUtils;
import com.zssq.utils.PageBean;
import com.zssq.utils.PropertiesUtil;

/**
 * 
 * @ClassName: DiskController  
 * @Description: 网盘
 * @author YDB  
 * @date 2017年4月17日  
 *
 */

@Controller
@RequestMapping("disk")
public class DiskController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());	

	@Autowired
	private DiskFileService diskFileService;
	
	
	@Autowired
	private ISysUserService userService;
	
	
	/**
	 * 
	 * @Title: getDiskMainList  
	 * @Description: 获取网盘主列表
	 * @param vo
	 * @return    参数  
	 * @return: ResultJSON    返回类型
	 * @throws BusinessException 
	 */
	@RequestMapping("/getDiskMainList")
	@ResponseBody
	public ResultJSON getDiskMainList(@RequireValid DiskManListVO vo) throws BusinessException{
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		JSONArray jsonList=new JSONArray();	
		JSONObject json=new JSONObject();
		try {
			
			//查询条件
			PageBean pageBean =diskFileService.getDiskMainList(vo.getFileName(),vo.getOrgCode(),Integer.parseInt(vo.getPageSize()),Integer.parseInt(vo.getPageNo()));
			List<DiskFile> list=pageBean.getRecordList();
				
			List<String> userCodeList=new ArrayList<>();
			
			for (int i = 0; i < list.size(); i++) {
				userCodeList.add(list.get(i).getUserCode());
				userCodeList.add(list.get(i).getEditUserCode());
			}
			
			Map<String, Object> userMap=userService.selectMapByCodes(userCodeList);

			//返回值封装
			for(int i=0;i<list.size();i++){
				DiskFile file=list.get(i);
				json.put("fileName",file.getFileName());
				json.put("suffix",file.getFileSuffix());
				json.put("size",file.getFileSize());
				
				SysUserInfo userInfo=(SysUserInfo)userMap.get(file.getEditUserCode());
				if(userInfo!=null ){
					json.put("userName", userInfo.getUserName());
				}else{
					json.put("userName", "");
				}
				
				json.put("fileType",file.getFileType());
				json.put("editTime",file.getEditTime());
				json.put("fileUrl",file.getFileUrl());
				json.put("fileCode",file.getFileCode());
				json.put("parentCode",file.getParentCode());
				json.put("userCode",file.getUserCode());
				json.put("suffix",file.getFileSuffix());
				
				jsonList.add(json);
				json=new JSONObject();
			}

			m = PropertiesUtil.getMessage("COMMON_200");
			
			json.put("list", jsonList);
			
			json.put("totalCount", pageBean.getTotalCount());
			resJson.setReturnCode(m.getCode());
			resJson.setReturnTip(m.getTip());
			resJson.setBody(JSONObject.toJSON(json));
			
		} catch (BusinessException e) {
			throw e;
			
		} catch (Exception e) {
			logger.error("获取网盘主列表-gl-getDiskMainList",e);
			
			throw new BusinessException().build("DISK_24001","获取列表");
		}
		
		return resJson;
		
	}
	
	
	
	
	
	/**
	 * 
	 * @Title: openFolderList  
	 * @Description: 打开文件夹
	 * @param vo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping("/openFolderList")
	@ResponseBody
	public ResultJSON openFolderList(@RequireValid OpenFolderListVO openVo) throws BusinessException{
		
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		JSONArray jsonList=new JSONArray();	
		JSONObject json=new JSONObject();
		
		try {
			FileVO vo=new FileVO();
			vo.setDelete(0);
			vo.setFileCode(openVo.getFileCode());
			
			//查询条件
			PageBean pageBean=diskFileService.OpentDiskFolderList(openVo.getFileCode(),Integer.parseInt(openVo.getPageSize()),Integer.parseInt(openVo.getPageNo()));				
			List<DiskFile> list=pageBean.getRecordList();

			
			List<String> userCodeList=new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				userCodeList.add(list.get(i).getUserCode());
			}
			
			Map<String, Object> userMap=userService.selectMapByCodes(userCodeList);

			//返回值封装
			for(int i=0;i<list.size();i++){
				DiskFile file=list.get(i);
				json.put("fileName",file.getFileName());
				json.put("suffix",file.getFileSuffix());
				json.put("size",file.getFileSize());
				
				SysUserInfo userInfo=(SysUserInfo)userMap.get(file.getUserCode());
				if(userInfo!=null){
					json.put("userName",userInfo.getUserName());
				}else{
					json.put("userName","");
				}
				
				json.put("fileType",file.getFileType());
				json.put("editTime",file.getEditTime());
				json.put("fileUrl",file.getFileUrl());
				json.put("fileCode",file.getFileCode());
				json.put("parentCode",file.getParentCode());
				json.put("userCode",file.getUserCode());
				json.put("suffix",file.getFileSuffix());
				jsonList.add(json);
				json=new JSONObject();
			}

			m = PropertiesUtil.getMessage("COMMON_200");
			
			json.put("list", jsonList);
			json.put("totalCount", pageBean.getTotalCount());

			resJson.setReturnCode(m.getCode());
			resJson.setReturnTip(m.getTip());
			resJson.setBody(JSONObject.toJSON(json));
        	
		} catch (BusinessException e) {
			throw e;
			
		} catch (Exception e) {
			logger.error("打开文件夹-gl-openFolderList:"+JSONObject.toJSONString(openVo),e);
			throw new BusinessException().build("DISK_24001", "打开");
		}
		
		return resJson;
		
	}
		
	/**
	 * 
	 * @Title: delFile  
	 * @Description: 删除文件
	 * @param vo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping("/delFile")
	@ResponseBody
	public ResultJSON delFile(@RequireValid DelFileVO vo) throws BusinessException{
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		JSONObject json=new JSONObject();

		try {
			
			 FileVO  filVo=new FileVO();
			 
			 filVo.setEditUserCode(vo.getUserCode());
			 filVo.setFileCode(vo.getFileCode());
			 ReturnMsg retMsg=diskFileService.delFile(filVo);
			
			 m = PropertiesUtil.getMessage("COMMON_200");
			 json.put("result",retMsg.isState()?0:1);
			 resJson.setReturnCode(m.getCode());
			 resJson.setReturnTip(m.getTip());
			 resJson.setBody(JSONObject.toJSON(json));
				
        	
		} catch (BusinessException e) {
			throw e;
			
		} catch (Exception e) {
			logger.error("删除文件-gl-delFile:"+JSONObject.toJSONString(vo),e);
			
			m = PropertiesUtil.getMessage("DISK_24002");
			throw new BusinessException(m.getCode(), m.getTip());
		}
		
		return resJson;
		
	}


	
	/**
	 * 
	 * @Title: getDiskFolderList  
	 * @Description: 获取文件夹列表
	 * @param vo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping("/getFolderList")
	@ResponseBody
	public ResultJSON getFolderList(@RequireValid GetFolderVO folderVo) throws BusinessException{
		
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		JSONArray jsonList=new JSONArray();	
		JSONObject json=new JSONObject();
		
		try {
			FileVO vo=new FileVO();
			vo.setFileCode(folderVo.getFileCode());
			vo.setUserCode(folderVo.getUserCode());
			vo.setOrgCode(folderVo.getOrgCode());
			//查询条件
			List<DiskFile> list=diskFileService.getFolderList(vo);				
			//返回值封装
			for(int i=0;i<list.size();i++){
				json=new JSONObject();
				
				DiskFile file=list.get(i);
				if(!file.getFileCode().equals(folderVo.getSeletedFileCode())){
					jsonList.add(JSONObject.toJSON(file));
				}
				
				
			}

			m = PropertiesUtil.getMessage("COMMON_200");
			
			json.put("list", jsonList);
			resJson.setReturnCode(m.getCode());
			resJson.setReturnTip(m.getTip());
			resJson.setBody(JSONObject.toJSON(json));
        	
		} catch (BusinessException e) {
			throw e;
			
		} catch (Exception e) {
			logger.error("获取文件夹列表-gl-getDiskFolderList:"+JSONObject.toJSONString(folderVo),e);
			m = PropertiesUtil.getMessage("DISK_24001");
			throw new BusinessException(m.getCode(), m.getTip());
		}
		
		return resJson;
		
	}	
	
	
	/**
	 * 
	 * @Title: moveFile  
	 * @Description: 移动文件
	 * @param vo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping("/moveFile")
	@ResponseBody
	public ResultJSON moveFile(@RequireValid MoveVO vo) throws BusinessException{
		
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		JSONObject json=new JSONObject();
		
		try {
			MoveFileVO filVo=new MoveFileVO();
			filVo.setFileCode(vo.getFileCode());
			filVo.setFileName(vo.getFileName());
			filVo.setFileSuffix(vo.getFileSuffix());
			filVo.setNewFolderCode(vo.getNewFolderCode());
			filVo.setUserCode(vo.getUserCode());
			filVo.setParentCode(vo.getParentCode());
			filVo.setEditTime(DateUtils.getTime());
			
			ReturnMsg retMsg=diskFileService.moveFile(filVo);
			
			if(retMsg.isState()){
				
				m = PropertiesUtil.getMessage("COMMON_200");
				json.put("result",retMsg.isState()?0:1);
				json.put("msg",retMsg.getMsg());
				resJson.setReturnCode(m.getCode());
				resJson.setReturnTip(m.getTip());
				resJson.setBody(JSONObject.toJSON(json));
				
			}else{
				
				throw new BusinessException();
			}
       	
		} catch (BusinessException e) {
			throw e;
			
		} catch (Exception e) {
			logger.error("移动文件-gl-moveFile:"+JSONObject.toJSONString(vo),e);
			m = PropertiesUtil.getMessage("DISK_24002");
			throw new BusinessException(m.getCode(), m.getTip());
		}
		
		return resJson;
	}	
	
	
	/**
	 * 
	 * @Title: copyFile
	 * @Description: 复制到
	 * @param vo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping("/copyFile")
	@ResponseBody
	public ResultJSON copyFile(@RequireValid CopyVO vo) throws BusinessException{
		
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		JSONObject json=new JSONObject();
		
		try {
			CopyFileVO fileVo=new CopyFileVO();
			
			fileVo.setCopyFileCode(vo.getCopyFolderCode());
			fileVo.setFileCode(vo.getFileCode());
			fileVo.setFileName(vo.getFileName());
			fileVo.setFileSuffix(vo.getFileSuffix());
			fileVo.setParentCode(vo.getParentCode());
			
			ReturnMsg retMsg=diskFileService.copyFile(fileVo);
			
			if(retMsg.isState()){
				
				m = PropertiesUtil.getMessage("COMMON_200");
				json.put("result",retMsg.isState()?0:1);
				json.put("msg",retMsg.getMsg());
				resJson.setReturnCode(m.getCode());
				resJson.setReturnTip(m.getTip());
				resJson.setBody(JSONObject.toJSON(json));
				
			}else{
				
				throw new BusinessException();
			}
       	
		} catch (BusinessException e) {
			throw e;
			
		} catch (Exception e) {
			logger.error("复制到-gl-copyFile："+JSONObject.toJSONString(vo),e);
			m = PropertiesUtil.getMessage("DISK_24002");
			throw new BusinessException(m.getCode(), m.getTip());
		}
		
		return resJson;
	}	
	
	
	

	/**
	 * 
	 * @Title: 
	 * @Description: 复制文件夹
	 * @param vo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	/*@RequestMapping("/copyFolder")
	@ResponseBody
	public ResultJSON copyFolder(@RequireValid CopyFoladerVO vo) throws BusinessException{
		
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		JSONObject json=new JSONObject();
		
		try {
			CopyFileAllVO fileVo=new CopyFileAllVO();
			fileVo.setFileCode(vo.getFileCode());
			fileVo.setParentCode(vo.getParentCode());
			fileVo.setUserCode(vo.getUserCode());
			
			ReturnMsg retMsg=diskFileService.copyFolder(fileVo);
			
			if(retMsg.isState()){
				
				m = PropertiesUtil.getMessage("COMMON_200");
				json.put("result",retMsg.isState()?0:1);
				json.put("msg",retMsg.getMsg());
				resJson.setReturnCode(m.getCode());
				resJson.setReturnTip(m.getTip());
				resJson.setBody(JSONObject.toJSON(json));
				
			}else{
				
				throw new BusinessException();
			}
       	
		} catch (BusinessException e) {
			throw e;
			
		} catch (Exception e) {
			m = PropertiesUtil.getMessage("DISK_24002");
			throw new BusinessException(m.getCode(), m.getTip());
		}
		
		return resJson;
	}	*/
	
	
	
	/**
	 * 
	 * @Title: 
	 * @Description: 重命名
	 * @param vo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping("/rname")
	@ResponseBody
	public ResultJSON Rname(@RequireValid NewNameVO vo) throws BusinessException{
		
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		JSONObject json=new JSONObject();
		
		try {
			RnameVO  fileVo=new RnameVO();
			fileVo.setFileCode(vo.getFileCode());
			fileVo.setNewName(vo.getNewName());
			fileVo.setUserCode(vo.getUserCode());
			fileVo.setSuffix(vo.getSuffix());
			fileVo.setEditUserCode(vo.getUserCode());
			fileVo.setParentCode(vo.getParentCode());
			ReturnMsg retMsg=diskFileService.rnameFile(fileVo);
			
			m = PropertiesUtil.getMessage("COMMON_200");
			
			json.put("result",retMsg.isState()?0:1);
			json.put("msg",retMsg.getMsg());
			resJson.setReturnCode(m.getCode());
			resJson.setReturnTip(m.getTip());
			resJson.setBody(JSONObject.toJSON(json));
			
		} catch (BusinessException e) {
			throw e;
			
		} catch (Exception e) {
			logger.error("重命名-gl-rname:"+JSONObject.toJSONString(vo), e);
			m = PropertiesUtil.getMessage("DISK_24002");
			throw new BusinessException(m.getCode(), m.getTip());
		}
		
		return resJson;
	}	
	
	
	
	
	
	/**
	 * 
	 * @Title: 
	 * @Description: 创建新文件夹
	 * @param vo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping("/creatFile")
	@ResponseBody
	public ResultJSON creatFile(@RequireValid CreateVO vo) throws BusinessException{
		
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		JSONObject json=new JSONObject();
		
		try {
			SysUserInfo userInfo=userService.selectByCode(vo.getUserCode());
			CreatFolderVO fileVo=new CreatFolderVO();
			fileVo.setNewFileName(vo.getNewFileName());
			fileVo.setOrgCode(userInfo.getManageOrgInfo().getSysOrgCode());
			fileVo.setParentCode(vo.getParentCode());
			fileVo.setUserCode(vo.getUserCode());
			
			ReturnMsg retMsg=diskFileService.creatFolder(fileVo);
				
			m = PropertiesUtil.getMessage("COMMON_200");
			json.put("result",retMsg.isState()?0:1);
			json.put("msg",retMsg.getMsg());
			resJson.setReturnCode(m.getCode());
			resJson.setReturnTip(m.getTip());
			resJson.setBody(JSONObject.toJSON(json));
				
       	
		} catch (BusinessException e) {
			throw e;
			
		} catch (Exception e) {
			logger.error("创建文件夹-gl-creatFile:"+JSONObject.toJSONString(vo),e);
			m = PropertiesUtil.getMessage("DISK_24002");
			throw new BusinessException(m.getCode(), m.getTip());
		}
		
		return resJson;
	}	
	
	
	
	/**
	 * 
	 * @Title: recoveryFileList  
	 * @Description: 回收站列表
	 * @param vo
	 * @return    参数  
	 * @return: ResultJSON    返回类型
	 * @throws BusinessException 
	 */
	@RequestMapping("/recoveryFileList")
	@ResponseBody
	public ResultJSON recoveryFileList(@RequireValid RecoveryVO vo) throws BusinessException{
		
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		JSONArray jsonList=new JSONArray();	
		JSONObject json=new JSONObject();
		
		try {
			
			//查询条件
			PageBean pageBean=diskFileService.getRecoveryFileList(vo.getOrgCode(),vo.getPageSize(),vo.getPageNo());
			List<DiskFile> list=pageBean.getRecordList();
			
			List<String> userCodeList=new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				userCodeList.add(list.get(i).getUserCode());
			}
			
			Map<String, Object> userMap=userService.selectMapByCodes(userCodeList);

			
			
			//返回值封装
			for(int i=0;i<list.size();i++){
				DiskFile file=list.get(i);
				json.put("fileName",file.getFileName());
				json.put("suffix",file.getFileSuffix());
				json.put("size",file.getFileSize());
				
				SysUserInfo userInfo=(SysUserInfo)userMap.get(file.getUserCode());
				json.put("userName",userInfo.getUserName());
				
				json.put("fileType",file.getFileType());
				json.put("editTime",file.getEditTime());
				json.put("fileUrl",file.getFileUrl());
				json.put("fileCode",file.getFileCode());
				json.put("parentCode",file.getParentCode());
				json.put("userCode",file.getUserCode());
				json.put("suffix",file.getFileSuffix());
				jsonList.add(json);
				json=new JSONObject();
			}

			m = PropertiesUtil.getMessage("COMMON_200");
			
			json.put("list", jsonList);
			json.put("totalCount",pageBean.getTotalCount());
			resJson.setReturnCode(m.getCode());
			resJson.setReturnTip(m.getTip());
			resJson.setBody(JSONObject.toJSON(json));
        	
		} catch (BusinessException e) {
			throw e;
			
		} catch (Exception e) {
			logger.error("回收站列表-gl-recoveryFileList："+JSONObject.toJSONString(vo), vo);
			m = PropertiesUtil.getMessage("DISK_24001");
			throw new BusinessException(m.getCode(), m.getTip());
		}
		
		return resJson;
		
	}
	

	/**
	 * 
	 * @Title: ReductionFile
	 * @Description: 还原文件
	 * @param vo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping("/reductionFile")
	@ResponseBody
	public ResultJSON ReductionFile(@RequireValid ReductionVO vo) throws BusinessException{
		
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		JSONObject json=new JSONObject();
		
		try {
			
			RFileVO fileVo=new RFileVO();
			
			fileVo.setFileCode(vo.getFileCode());
			fileVo.setOrgCode(vo.getOrgCode());
			fileVo.setEditUserCode(vo.getUserCode());
			
			ReturnMsg retMsg=diskFileService.ReductionFile(fileVo);
			
			if(retMsg.isState()){
				
				m = PropertiesUtil.getMessage("COMMON_200");
				json.put("result",retMsg.isState()?0:1);
				json.put("msg",retMsg.getMsg());
				resJson.setReturnCode(m.getCode());
				resJson.setReturnTip(m.getTip());
				resJson.setBody(JSONObject.toJSON(json));
				
			}else{
				
				throw new BusinessException();
			}
       	
		} catch (BusinessException e) {
			throw e;
			
		} catch (Exception e) {
			logger.error("还原文件-gl-ReductionFile:"+JSONObject.toJSONString(vo),e);
			m = PropertiesUtil.getMessage("DISK_24002");
			throw new BusinessException(m.getCode(), m.getTip());
		}
		
		return resJson;
	}	
	
	
	/**
	 * 
	 * @Title: copyFolder
	 * @Description: 复制文件夹到
	 * @param vo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping("/copyFolder")
	@ResponseBody
	public ResultJSON copyFileAll(@RequireValid CopyFoladerVO vo) throws BusinessException{
		
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		JSONObject json=new JSONObject();
		
		try {
			CopyFileAllVO folderVo=new CopyFileAllVO();
			
			folderVo.setFileName(vo.getFileName());
			folderVo.setNewParentCode(vo.getCopyFolderCode());
			folderVo.setSourceFileCode(vo.getFileCode());
			folderVo.setSourceParentCode(vo.getParentCode());
			folderVo.setUserCode(vo.getUserCode());
		
			
			ReturnMsg retMsg=diskFileService.copyFolder(folderVo);
			
			if(retMsg.isState()){
				
				m = PropertiesUtil.getMessage("COMMON_200");
				json.put("result",retMsg.isState()?0:1);
				json.put("msg",retMsg.getMsg());
				resJson.setReturnCode(m.getCode());
				resJson.setReturnTip(m.getTip());
				resJson.setBody(JSONObject.toJSON(json));
				
			}else{
				
				throw new BusinessException();
			}
       	
		} catch (BusinessException e) {
			throw e;
			
		} catch (Exception e) {
			logger.error("复制到-gl-copyFolder:"+JSONObject.toJSONString(vo),e);
			m = PropertiesUtil.getMessage("DISK_24002");
			throw new BusinessException(m.getCode(), m.getTip());
		}
		
		return resJson;
	}	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * @Title: deleteFolder  
	 * @Description: 删除文件夹
	 * @param vo
	 * @return    参数  
	 * @return: ResultJSON    返回类型
	 * @throws BusinessException 
	 */
	@RequestMapping("/deleteFolder")
	@ResponseBody
	public ResultJSON deleteFolder(@RequireValid DeleteFolderVO vo) throws BusinessException{
		
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		JSONObject json=new JSONObject();
		
		try {
			
			ReturnMsg retMsg=diskFileService.deleteFolder(vo.getFolderCode(),vo.getUserCode());
			
			m = PropertiesUtil.getMessage("COMMON_200");
			json.put("result",retMsg.isState()?0:1);
			resJson.setReturnCode(m.getCode());
			resJson.setReturnTip(m.getTip());
			resJson.setBody(JSONObject.toJSON(json));

		} catch (BusinessException e) {
			throw e;
			
		} catch (Exception e) {
			logger.error("删除文件夹-gl-deleteFolder:"+JSONObject.toJSONString(vo), e);
			throw new BusinessException().build("DISK_24001","删除文件");
		}
		
		return resJson;
	}
	
	
	
	/**
	 * 
	 * @Title: deleteFolder  
	 * @Description: 彻底删除文件夹
	 * @param vo
	 * @return    参数  
	 * @return: ResultJSON    返回类型
	 * @throws BusinessException 
	 */
	@RequestMapping("/deleteThoroughFolder")
	@ResponseBody
	public ResultJSON deleteThoroughFolder(@RequireValid DeleteFolderVO vo) throws BusinessException{
		
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		JSONObject json=new JSONObject();
		
		try {
			
			ReturnMsg retMsg=diskFileService.deleteThoroughFolder(vo.getFolderCode(),vo.getUserCode());
			
			m = PropertiesUtil.getMessage("COMMON_200");
			json.put("result",retMsg.isState()?0:1);
			resJson.setReturnCode(m.getCode());
			resJson.setReturnTip(m.getTip());
			resJson.setBody(JSONObject.toJSON(json));

		} catch (BusinessException e) {
			throw e;
			
		} catch (Exception e) {
			logger.error("彻底删除文件夹-gl-deleteThoroughFolder:"+JSONObject.toJSONString(vo),e);
			throw new BusinessException().build("DISK_24001","删除文件");
		}
		
		return resJson;
	}
	
	
	
	/**
	 * 
	 * @Title: updateReductionFolder
	 * @Description: 还原文件夹
	 * @param vo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping("/updateReductionFolder")
	@ResponseBody
	public ResultJSON UpdateReductionFolder(@RequireValid ReductionVO vo) throws BusinessException{
		
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		JSONObject json=new JSONObject();
		
		try {
			
			/*RFileVO fileVo=new RFileVO();
			
			fileVo.setFileCode(vo.getFileCode());
			fileVo.setOrgCode(vo.getOrgCode());
			fileVo.setEditUserCode(vo.getUserCode());*/
			
			ReturnMsg retMsg=diskFileService.updateReductionFolder(vo.getFileCode(),vo.getUserCode());
			
			if(retMsg.isState()){
				
				m = PropertiesUtil.getMessage("COMMON_200");
				json.put("result",retMsg.isState()?0:1);
				json.put("msg",retMsg.getMsg());
				resJson.setReturnCode(m.getCode());
				resJson.setReturnTip(m.getTip());
				resJson.setBody(JSONObject.toJSON(json));
				
			}else{
				
				throw new BusinessException();
			}
       	
		} catch (BusinessException e) {
			throw e;
			
		} catch (Exception e) {
			logger.error("还原文件夹-gl-updateReductionFolder:"+JSONObject.toJSONString(vo),e);
			
			m = PropertiesUtil.getMessage("DISK_24002");
			throw new BusinessException(m.getCode(), m.getTip());
		}
		
		return resJson;
	}	
	
	/**
	 * 
	 * @Title: getFileInfo 
	 * @Description: 获取文件详情信息
	 * @param vo
	 * @return    参数  
	 * @return: ResultJSON    返回类型
	 * @throws BusinessException 
	 */
	@RequestMapping("/getFileInfo")
	@ResponseBody
	public ResultJSON getFileInfo(@RequireValid FileInfoVO vo) throws BusinessException{
		
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		JSONArray jsonList=new JSONArray();	
		JSONObject json=new JSONObject();
		
		try {
			
			//查询条件
			DiskFile	file=diskFileService.getFileInfo(vo.getFileCode());
			
			SysUserInfo userInfo=userService.selectByCode(vo.getFileCode());

			//返回值封装
			json.put("fileName",file.getFileName());
			json.put("suffix",file.getFileSuffix());
			json.put("size",file.getFileSize());
			
			json.put("userName", userInfo.getUserName());
			json.put("fileType",file.getFileType());
			json.put("editTime",file.getEditTime());
			json.put("fileUrl",file.getFileUrl());
			json.put("fileCode",file.getFileCode());
			json.put("parentCode",file.getParentCode());
			json.put("userCode",file.getUserCode());
			json.put("suffix",file.getFileSuffix());
				
			m = PropertiesUtil.getMessage("COMMON_200");
			
			json.put("list", jsonList);
			resJson.setReturnCode(m.getCode());
			resJson.setReturnTip(m.getTip());
			resJson.setBody(JSONObject.toJSON(json));
        	
		} catch (BusinessException e) {
			throw e;
			
		} catch (Exception e) {
			logger.error("获取文件详情-gl-getFileInfo:"+JSONObject.toJSONString(vo), e);
			throw new BusinessException().build("DISK_24001","获取列表");
		}
		
		return resJson;
		
	}
	
	
	
	/**
	 * 
	 * @Title: queryFileName  
	 * @Description: 查询文件夹
	 * @param vo
	 * @return    参数  
	 * @return: ResultJSON    返回类型
	 * @throws BusinessException 
	 */
	@RequestMapping("/queryFileName")
	@ResponseBody
	public ResultJSON queryFileName(@RequireValid QueryFileNameVO vo) throws BusinessException{
		
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		JSONArray jsonList=new JSONArray();	
		JSONObject json=new JSONObject();
		
		try {
			
			//查询条件
			List<DiskFile> list=diskFileService.queryFileName(vo.getFileName(),vo.getOrgCode());
				
			List<String> userCodeList=new ArrayList<>();
			
			for (int i = 0; i < list.size(); i++) {
				userCodeList.add(list.get(i).getUserCode());
			}
			
			Map<String, Object> userMap=userService.selectMapByCodes(userCodeList);

			//返回值封装
			for(int i=0;i<list.size();i++){
				DiskFile file=list.get(i);
				json.put("fileName",file.getFileName());
				json.put("suffix",file.getFileSuffix());
				json.put("size",file.getFileSize());
				
				SysUserInfo userInfo=(SysUserInfo)userMap.get(file.getUserCode());
				json.put("userName", userInfo.getUserName());
				json.put("fileType",file.getFileType());
				json.put("editTime",file.getEditTime());
				json.put("fileUrl",file.getFileUrl());
				json.put("fileCode",file.getFileCode());
				json.put("parentCode",file.getParentCode());
				json.put("userCode",file.getUserCode());
				json.put("suffix",file.getFileSuffix());
				
				
				jsonList.add(json);
				json=new JSONObject();
			}

			m = PropertiesUtil.getMessage("COMMON_200");
			
			json.put("list", jsonList);
			resJson.setReturnCode(m.getCode());
			resJson.setReturnTip(m.getTip());
			resJson.setBody(JSONObject.toJSON(json));
        	
		} catch (BusinessException e) {
			throw e;
			
		} catch (Exception e) {
			logger.error("查询文件-gl-queryFileName："+JSONObject.toJSONString(vo), e);
			throw new BusinessException().build("DISK_24001","获取列表");
		}
		
		return resJson;
		
	}
	
	
	/**
	 * 
	 * @Title: emptyFile  
	 * @Description: 清空回收站
	 * @param vo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping("/emptyFile")
	@ResponseBody
	public ResultJSON emptyFile(@RequireValid EmptyFileVO vo) throws BusinessException{
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		JSONObject json=new JSONObject();

		try {
			
			ReturnMsg retMsg=diskFileService.emptyFile(vo.getOrgCode(),vo.getUserCode());
			
				
			m = PropertiesUtil.getMessage("COMMON_200");
			json.put("result",retMsg.isState()?0:1);
			resJson.setReturnCode(m.getCode());
			resJson.setReturnTip(m.getTip());
			resJson.setBody(JSONObject.toJSON(json));
			
        	
		} catch (BusinessException e) {
			throw e;
			
		} catch (Exception e) {
			logger.error("清空回收站-gl-emptyFile:"+JSONObject.toJSONString(vo),e);
			m = PropertiesUtil.getMessage("DISK_24002");
			throw new BusinessException(m.getCode(), m.getTip());
		}
		
		return resJson;
		
	}
	
	
	
}