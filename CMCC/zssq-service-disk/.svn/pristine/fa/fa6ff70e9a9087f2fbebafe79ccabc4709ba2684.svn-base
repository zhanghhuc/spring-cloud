package com.zssq.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zssq.dao.mapper.DiskFileMapper;
import com.zssq.dao.pojo.DiskFile;
import com.zssq.model.FileListAllVO;

@Component
public class FileUtil {
	
	@Autowired
	private DiskFileMapper diskFileMapper;
	
	
	/**
	 * 
	 * @Title: getFolderCodeList  
	 * @Description: 获取文件夹下所有文件及其文件夹的code
	 * @param folderCode
	 * @return    参数  
	 * @return: List<String>    返回类型
	 */
	public List<String> getFolderCodeList(String folderCode){
		
		return this.FileCodeList(folderCode);
	}

	
	
	/**
	 * 
	 * @Title: list  
	 * @Description: 
	 * @param fileCode
	 * @return    参数  
	 * @return: List<String>    返回类型
	 */
	private List<String> FileCodeList(String fileCode){
		List<String> fileList=new ArrayList<>();
		FileListAllVO vo=new FileListAllVO();
		vo.setFileCode(fileCode);
		
		List<DiskFile> list=diskFileMapper.getFileListAll(vo);
		
		if(list==null || list.size()==0){
			
			return fileList;
		}
		
		for(int i=0;i<list.size();i++){
			DiskFile file=list.get(i);
	
			if(file.getFileType()==1){
				String temFileCode=file.getFileCode();
				List<String> tempList=this.FileCodeList(temFileCode);
				
				if(tempList!=null ||tempList.size()!=0 ){
					fileList.addAll(tempList);
				}
			}
			
			fileList.add(file.getFileCode());
			
		}
		
		return fileList;
	}
	
	
	
	
	/**
	 * 
	 * @Title: getFolderInfoList  
	 * @Description: 获取文件夹下所有文件及其文件夹的code
	 * @param folderCode
	 * @return    参数  
	 * @return: List<String>    返回类型
	 */
	public List<DiskFile> getFolderInfoList(String folderCode){
		return this.FileInfoList(folderCode);
	}
	
	/**
	 * 
	 * @Title: FileInfoList  
	 * @Description: 
	 * @param fileCode
	 * @return    参数  
	 * @return: List<String>    返回类型
	 */
	private List<DiskFile> FileInfoList(String fileCode){
		List<DiskFile> fileList=new ArrayList<>();
		FileListAllVO vo=new FileListAllVO();
		vo.setFileCode(fileCode);
		
		List<DiskFile> list=diskFileMapper.getFileListAll(vo);
		
		if(list==null || list.size()==0){
			
			return null;
		}
		
		for(int i=0;i<list.size();i++){
			DiskFile file=list.get(i);
			if(file.getFileType()==1){
				String temFileCode=file.getFileCode();
				List<DiskFile> tempList=this.FileInfoList(temFileCode);
				
				if(tempList!=null && tempList.size()!=0 ){
					fileList.addAll(tempList);
				}
			}
			
			fileList.add(file);
			
		}
		
		return fileList;
	}
	
	
	
	
	
	
	
}