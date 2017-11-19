package com.zssq.dao.mapper;

import java.util.List;

import com.zssq.dao.pojo.UserLevelConfig;

/**
 * 
 * @ClassName: UserLevelConfigMapper  
 * @Description: 操作经验值等级配置表  user_level_config 
 * @author power  
 * @date 2017年4月28日  
 *
 */
public interface UserLevelConfigMapper {

	/**
	 * 
	 * @Title: selectByCode  
	 * @Description: 根据等级编号查询经验值等级配置信息
	 * @param @param levelCode 等级编号
	 * @param @return    参数  
	 * @return UserLevelConfig    经验值等级配置实体  
	 * @throws
	 */
	UserLevelConfig selectByCode(String levelCode);

	/**
	 * 
	 * @Title: selectMinLevelNo  
	 * @Description: 查询最小等级
	 * @param @return    参数  
	 * @return Byte    等级  
	 * @throws
	 */
	Byte selectMinLevelNo();
	
	/**
	 * 
	 * @Title: selectAll  
	 * @Description: 查询经验值等级配置信息列表，并按照等级升序 
	 * @param @return    参数  
	 * @return List<UserLevelConfig>    经验值等级配置列表
	 * @throws
	 */
	List<UserLevelConfig> selectAll();
	
	/**
	 * 
	 * @Title: selectMaxLevelConfig  
	 * @Description: 查询用户最高等级的配置信息
	 * @param @return    参数  
	 * @return UserLevelConfig    返回类型  
	 * @throws
	 */
	UserLevelConfig selectMaxLevelConfig();

}
