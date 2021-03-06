package com.zssq.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.constants.AuthConstants;
import com.zssq.dao.mapper.DiskFileMapper;
import com.zssq.dao.pojo.DiskFile;
import com.zssq.exceptions.BusinessException;
import com.zssq.model.CheckFileNameVO;
import com.zssq.model.CopyFileAllVO;
import com.zssq.model.CopyFileVO;
import com.zssq.model.CreatFolderVO;
import com.zssq.model.DelFolderModel;
import com.zssq.model.FileInfoVo;
import com.zssq.model.FileVO;
import com.zssq.model.GetIndexVO;
import com.zssq.model.GetManListVO;
import com.zssq.model.MoveFileVO;
import com.zssq.model.OpenFolderVO;
import com.zssq.model.QuerFileNameVO;
import com.zssq.model.RFileVO;
import com.zssq.model.RecoveryListVO;
import com.zssq.model.ReportFileVO;
import com.zssq.model.ReturnMsg;
import com.zssq.model.RnameVO;
import com.zssq.service.DiskFileService;
import com.zssq.util.CopyAllFileUtil;
import com.zssq.util.CreatFileCodeUtil;
import com.zssq.util.FileUtil;
import com.zssq.utils.DateUtils;
import com.zssq.utils.PageBean;


/**
 * 
 * @ClassName: DiskFileServiceImpl  
 * @Description: 呵呵  
 * @author YDB  
 * @date 2017年4月13日  
 *
 */

@Service("diskFileService")
public class DiskFileServiceImpl implements DiskFileService{
	private static Logger logger = Logger.getLogger(DiskFileServiceImpl.class);

	@Autowired
	private DiskFileMapper diskFileMapper;
	
	@Autowired
	private CreatFileCodeUtil creatFileCodeUtil;
	
	@Autowired
	private CopyAllFileUtil copyFileUtil;
	
	@Autowired
	private FileUtil fileUtil;
	
	/**
	 * 
	 * @Title: getDiskMainList  
	 * @Description: 获取主列表
	 * @param orgCode
	 * @return    参数  
	 * @return: List<DiskFile>    返回类型
	 */
	@Override
	public PageBean getDiskMainList(String fileName,String orgCode,int pageSize,int pageNo) throws BusinessException {
		PageBean pageBean=new PageBean();
		
		GetManListVO vo=new GetManListVO();
		vo.setOrgCode(orgCode);
		vo.setPageNo(pageNo*pageSize);
		vo.setPageSize(pageSize);
		vo.setFileName(fileName);
		List<DiskFile> list= diskFileMapper.getDiskMainList(vo);
		int count=diskFileMapper.getDiskMainCount(vo);
		
		pageBean.setTotalCount(count);
		pageBean.setRecordList(list);
		
		return pageBean;
	}
	
	
	/**
	 * 
	 * @Title: getDiskFolderList  
	 * @Description: 打开文件
	 * @param folderCode
	 * @param  parentCode 父级code
	 * @return    参数  
	 * @return: List<DiskFile>    返回类型
	 */
	@Override
	public PageBean OpentDiskFolderList(String fileCode,int pageSize,int pageNo) throws BusinessException{
		PageBean pageBean=new PageBean();
		
		OpenFolderVO vo=new OpenFolderVO();
		vo.setFileCode(fileCode);
		vo.setPageNo(pageNo*pageSize);
		vo.setPageSize(pageSize);
		
		List<DiskFile>	list=diskFileMapper.OpentDiskFolderList(vo);
		
		int total=diskFileMapper.OpentFolderCount(vo);
		
		pageBean.setRecordList(list);
		pageBean.setTotalCount(total);
		
		return pageBean;
		
	}


	
	
	/**
	 * 
	 * @Title: delFile  
	 * @Description: 删除文件
	 * @param folderCode
	 * @param parentCode
	 * @return    参数  
	 * @return: ReturnMsg    返回类型
	 * @throws BusinessException 
	 */
	@Override
	public ReturnMsg delFile(FileVO vo) throws BusinessException {
		
		ReturnMsg msg=new ReturnMsg();
		vo.setDelete(1);
		vo.setEditTime(DateUtils.getTime());
		int state=diskFileMapper.delFile(vo);
		msg.setState(state==1);
		msg.setMsg(state==1?"删除成功":"删除失败");
		
		return msg;
	}


	/**
	 * 
	 * @Title: getFolderList  
	 * @Description: 打开文件夹
	 * @param vo
	 * @return    参数  
	 * @return: List<DiskFile>    返回类型
	 */
	@Override
	public List<DiskFile> getFolderList(FileVO vo) throws BusinessException {
		
		return diskFileMapper.getFolderList(vo);
	}


	/**
	 * 
	 * @Title: moveFile  
	 * @Description: 移动文件
	 * @param vo
	 * @return    参数  
	 * @return: ReturnMsg    返回类型
	 */
	@Override
	public ReturnMsg moveFile(MoveFileVO vo) throws BusinessException{
		ReturnMsg msg=new ReturnMsg();
		
		
		int state=0;
		CheckFileNameVO checkVo=new CheckFileNameVO();
		
		checkVo.setFileName(vo.getFileName());
		checkVo.setFileSuffix(vo.getFileSuffix());
		checkVo.setFolderCode(vo.getNewFolderCode());
		
		//校验文件名是否冲突
		if(this.checkeFileName(checkVo)){
			//文件名冲突 ,获取一个文件名称
			String fileName=getFileName(checkVo);
			//修改文件名称
			vo.setFileName(fileName);
			state=diskFileMapper.moveFile(vo);

		}else{
			//直接修改文件
			vo.setFileName(null);
			state=diskFileMapper.moveFile(vo);
		}
		
		msg.setState(state==1);
		msg.setMsg(state==1?"操作成功":"操作失败");
		return msg;
	}

	
	
	/**
	 * 
	 * @Title: checkeFileName  
	 * @Description: 校验文件名字  
	 * @return: void    返回类型 true-已经存在  false-不存在
	 */
	private  boolean checkeFileName(CheckFileNameVO vo) {
		int state=diskFileMapper.checkFileName(vo);
		return state==0?false:true;
	}
	
	/**
	 * 
	 * @Title: getFileName  
	 * @Description: 根据源文件获取一个文件名称
	 * @param vo
	 * @return    参数  
	 * @return: String    返回类型
	 */
	private String getFileName(CheckFileNameVO vo){
		
		int i=1;
		int state;
		while(true){
			vo.setFileName( this.fileNameStyle(vo.getFileName())+"("+i+")");
			
			state=diskFileMapper.checkFileName(vo);
			if(state==0){
				break;
			}
			i++;
		}
		String fileName=this.fileNameStyle(vo.getFileName())+"("+i+")";
		return fileName;
	}
	
	
	/**
	 * 
	 * @Title: fileName  
	 * @Description: 源文件名称
	 * @return    参数  
	 * @return: String    返回类型
	 */
	
	private String fileNameStyle(String fileName){
		int  i=fileName.lastIndexOf("(");
		int  j=fileName.lastIndexOf(")");
		
		if(j!=fileName.length()-1 || i==-1){
			
			return fileName;
		}
		String str=fileName.substring(0,i);
		return str;
	}


	/**
	 * 
	 * @Title: rnameFile  
	 * @Description: 重命名
	 * @param rname
	 * @return    参数  
	 * @return: ReturnMsg    返回类型
	 */
	@Override
	public ReturnMsg rnameFile(RnameVO rname) throws BusinessException {
		ReturnMsg msg=new ReturnMsg();
		
		CheckFileNameVO checkVo=new CheckFileNameVO();
		checkVo.setFileName(rname.getNewName());
		checkVo.setFileSuffix(rname.getSuffix());
		checkVo.setFolderCode(rname.getParentCode());
		
		if(this.checkeFileName(checkVo)){
			msg.setMsg("文件名已存在");
			
			return msg;
		}
		rname.setEditTime(DateUtils.getTime());
		int state=diskFileMapper.rnameFile(rname);
		
		msg.setState(state==1);
		msg.setMsg(state==1?"操作成功":"操作失败");
		
		return msg;
	}


	
	/**
	 * 
	 * @Title: creatFile  
	 * @Description: 
	 * @param vo
	 * @return    参数  
	 * @return: ReturnMsg    返回类型
	 */
	@Override
	public ReturnMsg creatFolder(CreatFolderVO cf) throws BusinessException {
		DiskFile record=new DiskFile();
		CheckFileNameVO checkVo=new CheckFileNameVO();
		ReturnMsg msg=new ReturnMsg();
		
		checkVo.setFileName(cf.getNewFileName());
		checkVo.setFolderCode(cf.getParentCode());
		
		if(this.checkeFileName(checkVo)){
			msg.setMsg("文件名已存在");
			return msg;
		}
	
		String fileCode=creatFileCodeUtil.getFileCode();
		Long date=DateUtils.getTime();
		record.setCreatTime(date);
		record.setEditTime(date);
		record.setEditUserCode(cf.getUserCode());
		record.setFileName(cf.getNewFileName());
		record.setFileType(1);
		record.setIsDelete(0);
		record.setOrgCode(cf.getOrgCode());
		record.setParentCode(cf.getParentCode());
		record.setReportType(0);
		record.setUserCode(cf.getUserCode());
		record.setFileCode(fileCode);
		int state=diskFileMapper.insert(record);
		msg.setObj(fileCode);
		msg.setState(state==1);
		msg.setMsg(state==1?"操作成功":"操作失败");
		return msg;
	}



	/**
	 * 
	 * @Title: getRecoveryFileList  
	 * @Description: 获取垃圾站
	 * @param orgCode
	 * @return    参数  
	 * @return: List<DiskFile>    返回类型
	 */
	@Override
	public PageBean getRecoveryFileList(String orgCode,int pageSize,int pageNo) throws BusinessException {
		PageBean pageBean=new PageBean();
		
		RecoveryListVO vo=new RecoveryListVO();
		vo.setOrgCode(orgCode);
		vo.setPageNo(pageNo*pageSize);
		vo.setPageSize(pageSize);
		List<DiskFile> list=diskFileMapper.getRecoveryFileList(vo);
		int total=diskFileMapper.getRecoveryFileCount(vo);
		
		pageBean.setRecordList(list);
		pageBean.setTotalCount(total);
		
		return pageBean;
		
	}


	/**
	 * 
	 * @Title: rFile  
	 * @Description:  还原文件 
	 * @return    
	 * @return: ReturnMsg    返回类型
	 */
	@Override
	public ReturnMsg ReductionFile(RFileVO vo) throws BusinessException{
		ReturnMsg msg=new ReturnMsg();
		vo.setEditTime(DateUtils.getTime());
		int state =diskFileMapper.ReductionFile(vo);
		msg.setState(state==1);
		msg.setMsg(state==1?"操作成功":"操作失败");
		
		return msg;
	}


	/***
	 * 
	 * @Title: copyFile  
	 * @Description: 复制文件
	 * @return    参数  
	 * @return: ReturnMsg    返回类型
	 */
	@Override
	public ReturnMsg copyFile(CopyFileVO vo) throws BusinessException{
		ReturnMsg msg=new ReturnMsg();
		
		int state=0;
		//根据文件名字校验文件 目录
		CheckFileNameVO checkVo=new CheckFileNameVO();
		checkVo.setFileName(vo.getFileName());
		checkVo.setFileSuffix(vo.getFileSuffix());
		checkVo.setFolderCode(vo.getCopyFileCode());
		
		//查出文件详情
		FileInfoVo infoVo=new FileInfoVo();
		infoVo.setFileCode(vo.getFileCode());
		infoVo.setParentCode(vo.getParentCode());
		DiskFile fileInfo=diskFileMapper.getFileInfo(infoVo);
		fileInfo.setFileCode(null);
		fileInfo.setId(null);
		fileInfo.setFileCode(creatFileCodeUtil.getFileCode());
		fileInfo.setParentCode(vo.getCopyFileCode());
		if(this.checkeFileName(checkVo)){
			//文件名重复，获取一个新的文件夹名称
			String fileName=getFileName(checkVo);
			fileInfo.setFileName(fileName);
			fileInfo.setParentCode(vo.getCopyFileCode());
		}
		fileInfo.setEditTime(DateUtils.getTime());
		state=diskFileMapper.insert(fileInfo);		
		msg.setState(state!=0);
		msg.setMsg(state!=0?"操作成功":"操作失败");
		return msg;
	}

	/**
	 * 
	 * @Title: upFile  
	 * @Description:  上传文件信息
	 * @param diskfile
	 * @return    参数  
	 * @return: ReturnMsg    返回类型
	 */
	@Override
	public ReturnMsg addFile(DiskFile diskfile) throws BusinessException{
		
		CheckFileNameVO checkVo=new CheckFileNameVO();
		ReturnMsg msg=new ReturnMsg();
		Long time=DateUtils.getTime();
		checkVo.setFileName(diskfile.getFileName());
		checkVo.setFolderCode(diskfile.getParentCode());
		checkVo.setFileSuffix(diskfile.getFileSuffix());
		 
		if(this.checkeFileName(checkVo)){
			String fileName=getFileName(checkVo);
			diskfile.setFileName(fileName);
		}
		diskfile.setFileType(2);
		diskfile.setTenantCode(AuthConstants.TENANT_CODE);
		diskfile.setEditTime(time);
		diskfile.setCreatTime(time);
		diskfile.setReportType(0);
		diskfile.setIsDelete(0);
		int state=diskFileMapper.insert(diskfile);
		msg.setState(state==1);
		msg.setMsg(state==1?"操作成功":"操作失败");
		return msg;
	}
	
	
	/**
	 *  
	 * @Title: copFileAll  
	 * @Description: 复制到
	 * @param vo
	 * @throws BusinessException    参数  
	 * @return: void    返回类型
	 */
	@Override
	public ReturnMsg copyFolder(CopyFileAllVO vo) throws BusinessException {
		ReturnMsg msg=new ReturnMsg();
		
		List<DiskFile> list=copyFileUtil.copyNewFileList(vo.getSourceFileCode(), 
				vo.getFileName(), vo.getSourceParentCode(), vo.getNewParentCode());
		
		int state=diskFileMapper.batchAddFile(list);
		msg.setState(state!=0);
		msg.setMsg(state!=0?"操作成功":"操作失败");
		return msg;
	}


	/**
	 * 
	 * @Title: shieldFile  
	 * @Description: 举报文件
	 * @param fileCode
	 * @param type    参数  
	 * @return: void    返回类型
	 */
	@Override
	public void reportFile(String fileCode, Integer type,String userCode) throws BusinessException {
		
		ReportFileVO vo=new ReportFileVO();
		
		vo.setEditTime(DateUtils.getTime());
		vo.setFileCode(fileCode);
		vo.setReportType(type+"");
		vo.setEditUserCode(userCode);
		diskFileMapper.reportFileFile(vo);
	}

	
	/**
	 * 
	 * @Title: delFolder  
	 * @Description: 删除文件夹
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ReturnMsg    返回类型
	 */
	@Override
	public ReturnMsg deleteFolder(String folderCode,String userCode) throws BusinessException {
		
		FileVO vo=new FileVO();
		ReturnMsg msg=new ReturnMsg();
		vo.setDelete(1);
		vo.setEditTime(DateUtils.getTime());
		vo.setFileCode(folderCode);
		int state=diskFileMapper.delFile(vo);
		List<String> codeList=fileUtil.getFolderCodeList(folderCode);
		if(codeList==null || codeList.size()==0){
			msg.setState(state==1);
			msg.setMsg(state==1?"删除成功":"删除失败");
			return msg;
			
		}
		DelFolderModel  model = new DelFolderModel();
		model.setList(codeList);
		model.setDelete(2+"");
		model.setEditTime(DateUtils.getTime()+"");
		model.setEditUserCode(userCode);
		
		diskFileMapper.delFolder(model);
		
		msg.setState(state==1);
		msg.setMsg(state==1?"删除成功":"删除失败");
		
		return msg;
	}

	
	/**
	 * 
	 * @Title: ReductionFolder  
	 * @Description: 还原文件夹
	 * @return    参数  
	 * @return: ReturnMsg    返回类型
	 */
	@Override
	public ReturnMsg updateReductionFolder(String folderCode,String userCode)throws BusinessException {
		
		FileVO vo=new FileVO();
		ReturnMsg msg=new ReturnMsg();
		vo.setDelete(0);
		vo.setEditTime(DateUtils.getTime());
		vo.setFileCode(folderCode);
		vo.setEditUserCode(userCode);
		int state=diskFileMapper.delFile(vo);
		List<String> codeList=fileUtil.getFolderCodeList(folderCode);
		
		DelFolderModel  model = new DelFolderModel();
		model.setList(codeList);
		model.setDelete(0+"");
		model.setEditTime(DateUtils.getTime()+"");
		model.setEditUserCode(userCode);
		
		diskFileMapper.delFolder(model);
		
		msg.setState(state==1);
		msg.setMsg(state==1?"还原成功":"还原失败");
		
		return msg;
	}


	
	/**
	 * 
	 * @Title: deleteThoroughFolder  
	 * @Description: 彻底删除文件或文件夹
	 * @param fileCode
	 * @param userCode
	 * @return    参数  
	 * @return: ReturnMsg    返回类型
	 */
	
	@Override
	public ReturnMsg deleteThoroughFolder(String fileCode, String userCode)  throws BusinessException{
		ReturnMsg msg=new ReturnMsg();
		
		DiskFile fileInfo=diskFileMapper.getFile(fileCode);
		
		if(fileInfo==null){
			msg.setState(false);
			msg.setMsg("文件不存在，或者删除");
			return msg;
		}
		
		if(fileInfo.getFileType()==2){
			
			int state=diskFileMapper.thoroughDeleteFile(fileCode);
			msg.setState(state==1);
			msg.setMsg(state==0?"删除失败":"删除成功");
			
			logger.info("disk_删除文件_文件Code:"+fileCode+"_操作人Code_"+userCode+"----"+JSONObject.toJSON(fileInfo));
			
			return msg;
			
		}else{
			
			List<DiskFile> fileList=fileUtil.getFolderInfoList(fileCode);
			List<String> codeList=new ArrayList<>();
			if(codeList==null || codeList.size()==0){
				int state=diskFileMapper.thoroughDeleteFile(fileCode);
				msg.setState(state==1);
				msg.setMsg(state==0?"删除失败":"删除成功");
				
				return msg;
				
			}
			
			for (int i = 0; i < fileList.size(); i++) {
				codeList.add(fileList.get(i).getFileCode());
			}
			
			int state=diskFileMapper.thoroughDeleteFolder(codeList);
			msg.setState(state!=0);
			msg.setMsg(state==0?"删除失败":"删除成功");
			logger.info("disk_删除文件夹_文件夹信息:"+JSONObject.toJSON(fileInfo)+"_操作人Code_"+userCode+"---"+JSONArray.toJSONString(fileList));
		}
		
		return msg;
	}


	@Override
	public DiskFile getFileInfo(String fileCode)throws BusinessException {
		
		return diskFileMapper.getFile(fileCode);
	}

	
	/**
	 * 
	 * @Title: queryFileName  
	 * @Description: 根据条件进行查找
	 * @param fileName
	 * @param orgCode
	 * @return    参数  
	 * @return: List<DiskFile>    返回类型
	 */
	@Override
	public List<DiskFile> queryFileName(String fileName, String orgCode)throws BusinessException {
		
		QuerFileNameVO vo=new QuerFileNameVO();
		vo.setFileName(fileName);
		vo.setOrgCode(orgCode);
		return diskFileMapper.queryFileName(vo);
	}

	
	/**
	 * 清空回收站
	 */
	@Override
	public ReturnMsg emptyFile(String orgCode,String userCode)throws BusinessException {
		ReturnMsg msg=new ReturnMsg();
		List<DiskFile> list=diskFileMapper.queryEmptyFile(orgCode);
		
		logger.info("disk_清空回收站:时间_"+DateUtils.getTime()+"_操作人Code_"+userCode+"----"+JSONArray.toJSONString(list));
		
		//清空回收站
		diskFileMapper.emptyFile(orgCode);
		
		msg.setState(true);
		msg.setMsg("操作成功");
		
		return msg;
	}

	
	
	/**
	 * 
	 * @Title: getIndexFile  
	 * @Description: 获取首页最新文件
	 * @param orgCode
	 * @param pageSize
	 * @return    参数  
	 * @return: List<DiskFile>    返回类型
	 */
	@Override
	public List<DiskFile> getIndexFile(String orgCode, String pageSize) {
		
		GetIndexVO vo=new GetIndexVO();
		vo.setPageSize(Integer.parseInt(pageSize));
		vo.setOrgCode(orgCode);
		List<DiskFile> list=diskFileMapper.getIndexFile(vo);
		
		return list;
	}


	/**
	 * 
	 * @Title: sevenDaysDelete  
	 * @Description: 清空回收站十天的数据
	 * @return: void    返回类型
	 */
	@Override
	public void tenDaysDelete() {
		Long sysTime=DateUtils.getTime(); 
		//查询删除的数据
		List<DiskFile> fileList=diskFileMapper.getTenDaysDelete(sysTime-864000000);
		
		logger.info("disk_定时任务-清空10天的回收站:"+sysTime+"---"+JSONArray.toJSONString(fileList));
		
		diskFileMapper.deleteTenDaysFile(sysTime-864000000);
		
	}
	
 }