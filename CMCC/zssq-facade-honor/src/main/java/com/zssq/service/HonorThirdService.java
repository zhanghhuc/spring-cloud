package com.zssq.service;

import java.util.List;

import com.zssq.dao.pojo.HonorAwardRecord;
import com.zssq.exceptions.BusinessException;

/**
 * @ClassName HonorThirdService
 * @Description 荣誉模块第三方服务接口
 * @author LiuYunLong
 * @date 2017年4月10日 上午10:08:35
 * @version 1.0
 * @since JDK 1.7
 */
public interface HonorThirdService {
	
	
	/**
	 * @Function getHonorCount
	 * @Description 对内查询被授予的荣誉数
	 * @param honoreeCode   被授予人Code
	 * @param tenantCode    租户Code
	 * @return int          荣誉数
	 * @throws BusinessException
	 */
	public int getHonorCount(String honoreeCode, String tenantCode) throws BusinessException;
	
	/**
	 * @Function updateHonorMessage
	 * @Description 用于举报 删除/撤销删除 荣誉信息
	 * @param subjectCode   被操作的主体Code
	 * @param subjectType   备操作的主体分类：2：评论，3：回复
	 * @param isDelete      是否删除：0：取消，1：是
	 * @return boolean      返回类型
	 * @throws BusinessException
	 */
	public boolean updateHonorMessage(String subjectCode,Byte subjectType,Byte isDelete,String userCode)throws BusinessException;
	
	/**
	 * @Function getHonorListForHonoree
	 * @Description 查询个人/班组获取的荣誉列表
	 * @param honoreeCode
	 * @param tenantCode
	 * @return
	 * @throws BusinessException
	 */
	public List<HonorAwardRecord> getHonorListForHonoree(String honoreeCode,String tenantCode)throws BusinessException;
	/**
	 * 班组归档-荣誉接口
	 * @Function sumupTeamHonor
	 * @Description 
	 * @param teamCode
	 * @param startNum
	 * @param endNum
	 * @return
	 * @throws BusinessException
	 */
	public List<HonorAwardRecord> sumupTeamHonor(String teamCode, int startNum, int endNum) throws BusinessException;
	

}
