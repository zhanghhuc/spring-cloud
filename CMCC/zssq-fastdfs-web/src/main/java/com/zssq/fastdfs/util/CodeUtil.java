package com.zssq.fastdfs.util;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CodeUtil {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	/**
	 * 获取soon表中的唯一业务code
	 * @return
	 */
	public String getSoonFileCode(){
		
		String uuid="";
		String countSql="select count(1) as count from file_soon where soon_code = ? ";
		while(true){
			uuid=UUIDHelper.getUUID();
			Object []  param={uuid};

			List<Map<String, Object>> list=jdbcTemplate.queryForList(countSql,param);
			if(list.get(0).get("count").toString().equals("0")){
				break;
			}
			
		}
		
		return uuid;
	}
	
	
	
}
