package com.zssq.fastdfs.server.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.fastdfs.dao.SoonFileDao;
import com.zssq.fastdfs.model.CheckFileModel;
import com.zssq.fastdfs.server.SoonFileService;

@Service("soonFileService")
public class SoonFileServiceImpl implements SoonFileService {

	@Autowired
	private SoonFileDao fileDao;
	
	
	/**
	 * 校验文件
	 * @param code
	 * @return
	 */
	@Override
	public CheckFileModel CheckFile(String code) {
		CheckFileModel model=new CheckFileModel();
		
		List<Map<String, Object>> list=fileDao.CheckFile(code);
		if(list==null||list.size()==0){
			model.setState(false);
		}else{
			model.setState(true);
			model.setUrl(list.get(0).get("url").toString());
		}
		
		return model;
	}

	/**
	 * 保存文件校验码
	 */
	@Override
	public void saveFileCode(String code,String url) {
		
		fileDao.SaveFileCode(code, url);
	}

}
