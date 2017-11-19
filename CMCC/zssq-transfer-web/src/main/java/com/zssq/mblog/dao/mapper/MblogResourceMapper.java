package com.zssq.mblog.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.mblog.pojo.MblogResource;

/**
 * 
    * @ClassName: MblogResourceMapper  
    * @Description: 微博资源Mapper  
    * @author Mr.B  
    * @date 2017年3月20日  
    *
 */
public interface MblogResourceMapper {
	
    /**
     * 
        * @Title: batchInsert  
        * @Description: 批量插入
        * @param list
    	* @return int    返回类型
     */
    int batchInsert(List<MblogResource> list);
    
    int selectImgCount();
    
    int selectVideoCount();
    
    int selectMusicCount();
    
    int insertIntoImg(Map<String,Object> paramsMap);
    
    int insertIntoVideo(Map<String,Object> paramsMap);
    
    int insertIntoMusic(Map<String,Object> paramsMap);
    
}