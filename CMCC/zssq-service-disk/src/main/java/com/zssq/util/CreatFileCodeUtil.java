package com.zssq.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zssq.dao.mapper.DiskFileMapper;
import com.zssq.shiro.mysecurity.UUIDHelper;

/**
 * 
 * @ClassName: CreatFileCodeUtil  
 * @Description: Code
 * @author YDB  
 * @date 2017年4月14日  
 *
 */
@Component
public class CreatFileCodeUtil {
	
	@Autowired
	private DiskFileMapper diskFileMapper;
	
	/**
	 * 
	 * @Title: getFileCode  
	 * @Description: 获取文件表中未存在的fileCode
	 * @return    参数  
	 * @return: String    返回类型
	 */
	public String getFileCode(){
	
		int state=1;
		while (true) {
			String code=UUIDHelper.getUUID();
			state=diskFileMapper.countCode(code);
			if(state==0){
				return code;
			}
			
		}
		
	}
	
	

}
