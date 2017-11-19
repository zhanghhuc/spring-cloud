package com.zssq.fastdfs.server.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.fastdfs.dao.FileDao;
import com.zssq.fastdfs.server.FileServer;
import com.zssq.fastdfs.util.UUIDHelper;
import com.zssq.fastdfs.web.vo.FileInfoVo;


@Service("fileServer")
public class FileServerImpl implements FileServer{

	@Autowired
	private FileDao  fileDao;
	
	@Transactional
	@Override
	public int FirstAddFile(FileInfoVo file) {
		return fileDao.FirstAddFile(file);
	}

	@Override
	public int getFileIndex(FileInfoVo file) {
		return fileDao.getFileIndex(file);
	}

	@Override
	public int FileAppend(int index,String fileId) {
		return fileDao.FileAppend(index, fileId);
	}

	@Override
	public int UpdateFileState(String fileId) {
		// TODO Auto-generated method stub
		return fileDao.UpdateFileState(fileId);
	}



}
