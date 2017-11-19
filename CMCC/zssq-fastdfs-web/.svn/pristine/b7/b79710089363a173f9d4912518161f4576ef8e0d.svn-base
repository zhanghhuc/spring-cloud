package com.zssq.fastdfs.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zssq.fastdfs.dao.SoonFileDao;
import com.zssq.fastdfs.model.CheckFileModel;
import com.zssq.fastdfs.util.CodeUtil;
import com.zssq.fastdfs.util.UUIDHelper;

@Repository
public class SoonFileDaoImpl implements SoonFileDao{

	@Autowired
	private  JdbcTemplate jdbcTemplate;
	
	@Autowired
	private CodeUtil codeUtil;
	
	@Override
	public List<Map<String, Object>> CheckFile(String code) {
		CheckFileModel model=new CheckFileModel();
		
		//String sql="select count(1) as count from file_soon where encrypt_code = ? ";
		String sql="select url  from file_soon where encrypt_code = ? ";
		
		Object []  param={code};
		List<Map<String, Object>> list=jdbcTemplate.queryForList(sql, param);
		
		return list;
	}

	/**
	 * 保存用户校验码
	 */
	@Override
	public void SaveFileCode(String code,String url) {
		//验证检验业务code
		String uuid=codeUtil.getSoonFileCode();
	/*	String countSql="select count(1) as count from file_soon where soon_code = ? ";
		while(true){
			uuid=UUIDHelper.getUUID();
			Object []  param={uuid};

			List<Map<String, Object>> list=jdbcTemplate.queryForList(countSql,param);
			if(list.get(0).get("count").toString().equals("0")){
				break;
			}
			
		}*/
		
		String sql="INSERT INTO file_soon (soon_code,url,encrypt_code,create_time) VALUE (?,?,?,?)";
		Object []  param={uuid,url,code,new Date().getTime()};

		jdbcTemplate.update(sql,param);
		
	}

}
