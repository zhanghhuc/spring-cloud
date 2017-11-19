package com.zssq.fastdfs.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zssq.fastdfs.dao.FileDao;
import com.zssq.fastdfs.util.UUIDHelper;
import com.zssq.fastdfs.web.vo.FileInfoVo;

@Repository("FileDao")
public class FileDaoImpl implements FileDao{

	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	@Transactional
	@Override
	public int FirstAddFile(FileInfoVo file) {
		Long time=new Date().getTime();
		
		int ret=0;
		String code=UUIDHelper.getUUID();
		String sql="insert into file_table values (null,?,?,?,?,?,?,?)";
		Object[] param={code,file.getChunks(),file.getUid(),file.getFileName(),file.getFileGroupId(),0,time};
		 ret=jdbcTemplate.update(sql,param);
		
		String sql2="insert into file_subset VALUES (null,?,?,?,?)";
		Object[] param2={code,file.getChunk(),file.getUid(),time};
		ret=ret+jdbcTemplate.update(sql2,param2);
		return ret;
	}

	@Override
	public int getFileIndex(FileInfoVo file) {
		
		int index=-1;
		String sql="SELECT  file_index from file_subset where file_id=? ORDER BY file_index desc LIMIT 0,1";
		Object[] param={file.getUid()};
		try {
			List<Map<String,Object>> listMap=jdbcTemplate.queryForList(sql,param);
			if(listMap.size()!=0){
				index= Integer.parseInt(listMap.get(0).get("file_index").toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			index=-1;
		}
		return index;
	}

	@Override
	public int FileAppend(int index,String fileId) {
		int ret=0;
		String fileCode="";
		String sql="select file_code from file_table where file_id=?";
		Object[] param={fileId};

		List<Map<String, Object>> listMap=jdbcTemplate.queryForList(sql,param);
		//前端生成文件id重复
		if(listMap.size()>1){
			return 0;
		}
		
		try {
			fileCode=listMap.get(0).get("file_code").toString();
		} catch (Exception e) {
			return 0;
		} 
		
		String sql2="insert into file_subset values (null,?,?,?,?)";
		Object[] param2={fileCode,index,fileId,new Date().getTime()};
		ret=jdbcTemplate.update(sql2,param2);
		return ret;
	}

	@Override
	public int UpdateFileState(String fileId) {
		
		String sql="UPDATE file_table set file_state = 1 and file_id=? ";
		Object[] param={fileId};
		
		return jdbcTemplate.update(sql, param);
	}

	
}
