package com.zssq.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zssq.dao.mapper.DiskFileMapper;
import com.zssq.dao.pojo.DiskFile;
import com.zssq.model.CheckFileNameVO;
import com.zssq.model.FileInfoVo;
import com.zssq.model.FileListAllVO;
import com.zssq.utils.DateUtils;

/**
 * 
 * @ClassName: CopyAllFileUtil  
 * @Description: 复制文件下所有目录  
 * @author YDB  
 * @date 2017年4月18日  
 *
 */

@Component
public class CopyAllFileUtil {

	
	@Autowired
	private DiskFileMapper diskFileMapper;
	
	@Autowired
	private CreatFileCodeUtil  uuid;
	
	//用来校验当前生成的uuid 是否唯一
	private Map<String, String> checkCodeMap=new HashMap<>();
	
	
	
	
	public List<DiskFile> copyNewFileList(String sourceFileCode,String fileName,String sourceParentCode,String newParentCode){
		
		FileInfoVo vo =new FileInfoVo();
		vo.setFileCode(sourceFileCode);
		vo.setParentCode(sourceParentCode);
		DiskFile file=diskFileMapper.getFileInfo(vo);
		String code="";
		
		//生成一个 唯一的fileCode
		while(true){
			code=uuid.getFileCode();
			if(checkCodeMap.get(code)==null){
				break;
			}
		}
		
		//校验文件名称
		CheckFileNameVO checkVo=new CheckFileNameVO();
		checkVo.setFileName(fileName);
		checkVo.setFolderCode(newParentCode);
		
		//校验文件名是否冲突
		if(this.checkeFileName(checkVo)){
			//文件名冲突 ,获取一个文件名称
			 fileName=getFileName(checkVo);
		}
		
		file.setFileName(fileName);
		file.setParentCode(newParentCode);
		file.setFileCode(code);
		file.setId(null);
		List<DiskFile> list=this.getFolderSubsetList(sourceFileCode,code);
		file.setEditTime(DateUtils.getTime());
		list.add(file);
		
		return list;
	}
	
	
	
	
	/**
	 * 
	 * @Title: getFolderSubsetList  
	 * @Description: 获得文件夹子集
	 * @param sourceFileCode 当前文件夹 filCode
	 * @param newParentCode	新的父级的fileCode
	 * @return    参数  
	 * @return: List<DiskFile>    返回类型
	 */
	public List<DiskFile> getFolderSubsetList(String sourceFileCode, String newParentCode){
		List<DiskFile> fileList=new ArrayList<>();
		FileListAllVO vo=new FileListAllVO();
		vo.setFileCode(sourceFileCode);
		
		List<DiskFile> list=diskFileMapper.getFileListAll(vo);
		
		if(list==null || list.size()==0){
			
			
			return fileList;
		}
		
		for(int i=0;i<list.size();i++){
			DiskFile file=list.get(i);
			String code="";
			
			//生成一个 唯一的fileCode
			while(true){
				code=uuid.getFileCode();
				if(checkCodeMap.get(code)==null){
					break;
				}
			}
	
			if(file.getFileType()==1){
				String temFileCode=file.getFileCode();
				List<DiskFile> tempList=this.getFolderSubsetList(temFileCode,code);
				//tempList.add(file);
				if(tempList!=null ||tempList.size()!=0 ){
					fileList.addAll(tempList);
				}
				
			}
			
			file.setParentCode(newParentCode);
			file.setFileCode(code);
			file.setId(null);
			file.setEditTime(DateUtils.getTime());
			checkCodeMap.put(code, "1");
			fileList.add(file);
			
		}
		
		return fileList;
		
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
	 * @Title: checkeFileName  
	 * @Description: 校验文件名字  
	 * @return: void    返回类型 true-已经存在  false-不存在
	 */
	private  boolean checkeFileName(CheckFileNameVO vo) {
		int state=diskFileMapper.checkFileName(vo);
		return state==0?false:true;
	}
	
}      