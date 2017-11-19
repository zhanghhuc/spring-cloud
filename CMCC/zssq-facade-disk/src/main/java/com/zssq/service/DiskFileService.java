package com.zssq.service;

import java.util.List;

import com.zssq.dao.pojo.DiskFile;
import com.zssq.exceptions.BusinessException;
import com.zssq.model.CopyFileAllVO;
import com.zssq.model.CopyFileVO;
import com.zssq.model.CreatFolderVO;
import com.zssq.model.FileVO;
import com.zssq.model.MoveFileVO;
import com.zssq.model.RFileVO;
import com.zssq.model.ReturnMsg;
import com.zssq.model.RnameVO;
import com.zssq.utils.PageBean;

/**
 * 
 * @ClassName: DiskFileService  
 * @Description: 呵呵  
 * @author YDB  
 * @date 2017年4月13日  
 *
 */
public interface DiskFileService {

	
	
	/**
	 * 
	 * @Title: getDiskMainList  
	 * @Description: 获取主列表
	 * @param orgCode
	 * @return    参数  
	 * @return: List<DiskFile>    返回类型
	 */
	public PageBean getDiskMainList(String fileName,String orgCode,int pageSize,int pageNo) throws BusinessException;
	
	
	
	/**
	 * 
	 * @Title: getDiskFolderList  
	 * @Description: 打开文件
	 * @param folderCode
	 * @param  parentCode 父级code
	 * @return    参数  
	 * @return: List<DiskFile>    返回类型
	 */
	public PageBean OpentDiskFolderList(String fileCode,int pageSize,int pageNo) throws BusinessException;
	
	
	/**
	 * 
	 * @Title: delFile  
	 * @Description: 删除文件
	 * @param folderCode
	 * @param parentCode
	 * @return    参数  
	 * @return: ReturnMsg    返回类型
	 */
	public ReturnMsg delFile(FileVO vo) throws BusinessException;
	
	
	/**
	 * 
	 * @Title: delFolder  
	 * @Description: 删除文件夹
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ReturnMsg    返回类型
	 */
	public ReturnMsg deleteFolder(String folderCode,String userCode) throws BusinessException;
	
	
	
	/**
	 * 
	 * @Title: getFolderList  
	 * @Description: 打开文件夹
	 * @param vo
	 * @return    参数  
	 * @return: List<DiskFile>    返回类型
	 */
	public List<DiskFile> getFolderList(FileVO vo) throws BusinessException;
	
	
	
	/**
	 * 
	 * @Title: moveFile  
	 * @Description: 移动文件
	 * @param vo
	 * @return    参数  
	 * @return: ReturnMsg    返回类型
	 */
	public ReturnMsg moveFile(MoveFileVO vo) throws BusinessException;
	
	
	
	/**
	 * 
	 * @Title: rnameFile  
	 * @Description: 重命名
	 * @param rname
	 * @return    参数  
	 * @return: ReturnMsg    返回类型
	 */
	
	public ReturnMsg rnameFile(RnameVO rname) throws BusinessException;
	
	
	
	/**
	 * 
	 * @Title: creatFile  
	 * @Description: 
	 * @param vo
	 * @return    参数  
	 * @return: ReturnMsg    返回类型
	 */
	public ReturnMsg creatFolder(CreatFolderVO vo) throws BusinessException;
	
	
	
	
	/**
	 * 
	 * @Title: getRecoveryFileList  
	 * @Description: 获取垃圾站
	 * @param orgCode
	 * @return    参数  
	 * @return: List<DiskFile>    返回类型
	 */
	public PageBean getRecoveryFileList(String orgCode,int pageSize,int pageNo) throws BusinessException;
	
	
	
	/**
	 * 
	 * @Title: ReductionFile  
	 * @Description: 还原文件
	 * @return   还原文件  
	 * @return: ReturnMsg    返回类型
	 */
	public ReturnMsg ReductionFile(RFileVO vo) throws BusinessException;
	
	
	/**
	 * 
	 * @Title: updateReductionFolder  
	 * @Description: 还原文件夹
	 * @return    参数  
	 * @return: ReturnMsg    返回类型
	 */
	public ReturnMsg updateReductionFolder(String folderCode,String userCode) throws BusinessException;
	
	
	
	/***
	 * 
	 * @Title: copyFile  
	 * @Description: 复制文件
	 * @return    参数  
	 * @return: ReturnMsg    返回类型
	 */
	
	public ReturnMsg copyFile(CopyFileVO vo) throws BusinessException;
	
	
	
	/**
	 * 
	 * @Title: copFileAll  
	 * @Description: 复制文件夹到
	 * @param vo
	 * @throws BusinessException    参数  
	 * @return: void    返回类型
	 */
	public ReturnMsg copyFolder(CopyFileAllVO vo) throws BusinessException;
	
	
	/**
	 * 
	 * @Title: upFile  
	 * @Description:  上传文件信息
	 * @param diskfile
	 * @return    参数  
	 * @return: ReturnMsg    返回类型
	 */
	public ReturnMsg addFile(DiskFile diskfile) throws BusinessException;
	
	/**
	 * 
	 * @Title: shieldFile  
	 * @Description: 举报文件
	 * @param fileCode
	 * @param type    参数  0正常，1举报
	 * @return: void    返回类型
	 */
	public void reportFile(String fileCode,Integer type,String userCode) throws BusinessException;
	
	
	
	/**
	 * 
	 * @Title: deleteThoroughFolder  
	 * @Description: 彻底删除文件或文件夹
	 * @param fileCode
	 * @param userCode
	 * @return    参数  
	 * @return: ReturnMsg    返回类型
	 */
	public ReturnMsg deleteThoroughFolder(String fileCode,String userCode) throws BusinessException;
	
	
	/**
	 * 
	 * @Title: getFileInfo  
	 * @Description: 获取文件详情
	 * @param fileCode
	 * @return    参数  
	 * @return: DiskFile    返回类型
	 */
	public DiskFile getFileInfo(String fileCode)throws BusinessException;
	
	
	
	/**
	 * 
	 * @Title: queryFileName  
	 * @Description: 根据条件进行查找
	 * @param fileName
	 * @param orgCode
	 * @return    参数  
	 * @return: List<DiskFile>    返回类型
	 */
	public List<DiskFile> queryFileName(String fileName,String orgCode)throws BusinessException;
	
	/**
	 * 
	 * @Title: emptyFile  
	 * @Description: 清空回收站
	 * @param orgCode
	 * @return    参数  
	 * @return: ReturnMsg    返回类型
	 */
	public ReturnMsg emptyFile(String orgCode,String userCode)throws BusinessException;


	/**
	 * 
	 * @Title: getIndexFile  
	 * @Description: 获取首页最新文件
	 * @param orgCode
	 * @param pageSize
	 * @return    参数  
	 * @return: List<DiskFile>    返回类型
	 */
	public List<DiskFile> getIndexFile(String orgCode, String pageSize);
	
	/**
	 * 
	 * @Title: sevenDaysDelete  
	 * @Description: 清空回收站十天的数据
	 * @return: void    返回类型
	 */
	public void tenDaysDelete();
}