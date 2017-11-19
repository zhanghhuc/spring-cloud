package com.zssq.service;

import java.util.List;

import com.zssq.dao.pojo.HonorTeam;
import com.zssq.dao.pojo.TeamCourse;
import com.zssq.exceptions.BusinessException;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;

/**
 * @ClassName ITeamCourseService
 * @Description 班组推荐历程
 * @author JiPengChun
 * @date 2017年3月18日 上午11:10:05
 * @version 1.0
 * @since JDK 1.7
 */
public interface ITeamCourseService {

	/**
	 * @Function addTeamCourse
	 * @Description 添加班组推荐历程
	 * @param record
	 * @return
	 * @throws BusinessException
	 */
	public boolean addTeamCourse(TeamCourse record) throws BusinessException;

	
	/**
	 * @Function addHonorTeam
	 * @Description 设为百强班组
	 * @param teamCode 班组编码
	 * @param electCode 评选编码
	 * @param userCode 当前人
	 * @param orgCode 班组所属org
	 * @return
	 * @throws BusinessException
	 */
	public boolean addHonorTeam(String teamCode,String electCode,String userCode,String orgCode) throws BusinessException;
	
	
	/**
	 * @Function delHonorTeam
	 * @Description 移除百强班组
	 * @param teamCode 班组编码
	 * @param electCode 评选编码
	 * @return
	 * @throws BusinessException
	 */
	public boolean delHonorTeam(String teamCode,String electCode) throws BusinessException;
	
	/**
	 * @Function getTeamCourseList
	 * @Description 根据条件查询
	 * @param record
	 * @return
	 * @throws BusinessException
	 */
	public List<TeamCourse> selectTeamCourseByRecord(TeamCourse record) throws BusinessException;
	
	/**
	 * @Function selectTeamCourseList
	 * @Description 查询班组历程  带分页
	 * @param pageParam
	 * @param record
	 * @return
	 * @throws BusinessException
	 */
	public PageBean selectHonorTeamHis(PageParam pageParam,TeamCourse record) throws BusinessException;
	
	/**
	 * @Function selectLastHonorTeam
	 * @Description 查询百强班组(用于推送)
	 * @param electCode 评选编码
	 * @return
	 */
	public List<HonorTeam> selectLastHonorTeam(String electCode) throws BusinessException;
	
}
