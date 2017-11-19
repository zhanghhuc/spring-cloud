package com.zssq.fastdfs.dao;

import java.util.List;
import java.util.Map;


public interface SoonFileDao {
	
	public List<Map<String, Object>> CheckFile(String code);

	public void SaveFileCode(String code,String url);
	

}
