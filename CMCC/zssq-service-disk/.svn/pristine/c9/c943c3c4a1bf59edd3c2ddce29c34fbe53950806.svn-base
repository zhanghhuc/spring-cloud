package com.zssq.dao.mapper;

import java.util.List;

import com.zssq.dao.pojo.DiskFile;
import com.zssq.model.CheckFileNameVO;
import com.zssq.model.DelFolderModel;
import com.zssq.model.FileInfoVo;
import com.zssq.model.FileListAllVO;
import com.zssq.model.FileVO;
import com.zssq.model.GetIndexVO;
import com.zssq.model.GetManListVO;
import com.zssq.model.MoveFileVO;
import com.zssq.model.OpenFolderVO;
import com.zssq.model.QuerFileNameVO;
import com.zssq.model.RFileVO;
import com.zssq.model.RecoveryListVO;
import com.zssq.model.ReportFileVO;
import com.zssq.model.RnameVO;

public interface DiskFileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DiskFile record);

    int insertSelective(DiskFile record);

    DiskFile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DiskFile record);

    int updateByPrimaryKey(DiskFile record);
    
    List<DiskFile> getDiskMainList(GetManListVO vo);
    
    int getDiskMainCount(GetManListVO vo);
    
    
    List<DiskFile> OpentDiskFolderList(OpenFolderVO vo);
    
    int OpentFolderCount(OpenFolderVO vo);
    
    
    int delFile(FileVO vo);
    
    /**
     * 
     * @Title: delFolder  
     * @Description: 删除文件夹
     * @return    参数  
     * @return: int    返回类型
     */
    int delFolder(DelFolderModel vo);
    
    
    List<DiskFile> getFolderList(FileVO vo);
    
    /**
     * 
     * @Title: checkFileName  
     * @Description: 验证文件名称
     * @return    参数  
     * @return: int    返回类型
     */
    int checkFileName(CheckFileNameVO vo);
    
    
	int moveFile(MoveFileVO vo);

	
	int rnameFile(RnameVO rname);

	int creatFolder(DiskFile record);
	
	/**
	 * 
	 * @Title: countCode  
	 * @Description: 获取code
	 * @param code
	 * @return    参数  
	 * @return: int    返回类型
	 */
	int countCode(String code);
	
	/**
	 * 
	 * @Title: getRecoveryFileList  
	 * @Description: 回收站列表
	 * @param orgCode    参数  
	 * @return: void    返回类型
	 */

	List<DiskFile> getRecoveryFileList(RecoveryListVO vo);
	
	int getRecoveryFileCount(RecoveryListVO vo);

	
	/**
	 * 
	 * @Title: rFile  
	 * @Description: 还原文件
	 * @param vo
	 * @return    参数  
	 * @return: int    返回类型
	 */
	int ReductionFile(RFileVO vo);
	
	/**
	 * @Title: getFileInfo  
	 * @Description: 获取文件详情（删除，举报的文件获取不到）
	 * @param vo
	 * @return    参数  
	 * @return: DiskFile    返回类型
	 */
	DiskFile getFileInfo(FileInfoVo vo);
	
	
	
	/**
	 * 
	 * @Title: getFileListAll  
	 * @Description: 获取所以文件
	 * @param vo
	 * @return    参数  
	 * @return: List<DiskFile>    返回类型
	 */
	List<DiskFile> getFileListAll(FileListAllVO vo);
	
	/**
	 * 
	 * @Title: batchAddFile  
	 * @Description: 批量添加文件
	 * @param list
	 * @return    参数  
	 * @return: int    返回类型
	 */
	int batchAddFile(List<DiskFile> list);
	
	/**
	 * 
	 * @Title: reportFileFile  
	 * @Description: 举报文件
	 * @param vo
	 * @return    参数  
	 * @return: int    返回类型
	 */
	int reportFileFile(ReportFileVO vo);
	
	
	/**
	 * 
	 * @Title: getFile  
	 * @Description:获取文件信息，删除的文件也能获取到，举报的获取不到
	 * @param fileCode
	 * @return    参数  
	 * @return: DiskFile    返回类型
	 */
	DiskFile getFile(String fileCode);
	
	
	/**
	 * 
	 * @Title: thoroughDeleteFile  
	 * @Description: 物理删除文件信息
	 * @param fileCode    参数  
	 * @return: void    返回类型
	 */
	int thoroughDeleteFile(String fileCode);	
	
	
	/**
	 * 
	 * @Title: thoroughDeleteFolder  
	 * @Description: 物理删除文件夹及其子集
	 * @param fileCode
	 * @return    参数  
	 * @return: int    返回类型
	 */
	int thoroughDeleteFolder(List<String> list);	
	
	
	/**
	 * 
	 * @Title: queryFileName  
	 * @Description: 根据条件进行查找
	 * @param fileName
	 * @param orgCode
	 * @return    参数  
	 * @return: List<DiskFile>    返回类型
	 */
	 List<DiskFile> queryFileName(QuerFileNameVO vo);
	
	 
	 /**
	  * 
	  * @Title: queryEmptyFile  
	  * @Description: 查看清空的文件 
	  * @param orgCode
	  * @return    参数  
	  * @return: List<DiskFile>    返回类型
	  */
	 List<DiskFile> queryEmptyFile(String orgCode);
	
	 
	 /**
	  * 
	  * @Title: emptyFile  
	  * @Description: 清空回收站
	  * @param orgCode
	  * @return    参数  
	  * @return: int    返回类型
	  */
	 int emptyFile(String orgCode);

	 /**
	  * 
	  * @Title: getIndexFile  
	  * @Description: 获取首页文件
	  * @param vo
	  * @return    参数  
	  * @return: List<DiskFile>    返回类型
	  */
	 List<DiskFile> getIndexFile(GetIndexVO vo);
	 
	 
	 /**
	  * 
	  * @Title: getSevenDaysDelete  
	  * @Description: 查询放入回收站七天的文件
	  * @param time
	  * @return    参数  
	  * @return: List<DiskFile>    返回类型
	  */
	 List<DiskFile> getTenDaysDelete(Long time);
	 
	 
	 /**
	  * 
	  * @Title: deleteSevenDaysFile  
	  * @Description: 删除十天的文件
	  * @param time    参数  
	  * @return: void    返回类型
	  */
	 void deleteTenDaysFile(Long time);
	 
}