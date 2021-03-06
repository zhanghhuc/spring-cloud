package com.zssq.service;

import java.util.List;

import com.zssq.dao.pojo.TeamInfo;
import com.zssq.dao.pojo.TeamMember;
import com.zssq.dao.pojo.TeamMemberVo;
import com.zssq.exceptions.BusinessException;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;

/**
 * @ClassName ITeamMemberService
 * @Description 班组成员
 * @author JiPengChun
 * @date 2017年3月15日 下午5:44:44
 * @version 1.0
 * @since JDK 1.7
 */
public interface ITeamMemberService {

	/**
	 * @Function batchAdd
	 * @Description 批量插入组长/组员
	 * @param memList 班组成员对象
	 * @return
	 * @throws BusinessException
	 */
	public boolean batchAdd(List<TeamMember> memList) throws BusinessException;
	
	/**
	 * @Function batchAddAll
	 * @Description 批量插入组长和组员
	 * @param memList 成员集合(userCode与isLeader)
	 * @param teamCode 班组编码
	 * @return
	 * @throws BusinessException
	 */
	public List<TeamMemberVo> batchAddAll(List<TeamMemberVo> memList,String teamCode) throws BusinessException;

	/**
	 * @Function addTeamMember
	 * @Description 添加成员
	 * @param record
	 * @return
	 */
	public boolean addTeamMember(TeamMember record) throws BusinessException;

	/**
	 * @Function delTeamMember
	 * @Description 移除成员
	 * @param memberCode 班组成员唯一标识
	 * @return
	 */
	public boolean delTeamMember(String memberCode) throws BusinessException;
	
	/**
	 * @Function updateByRecord
	 * @Description 根据code更新
	 * @param record
	 * @return
	 * @throws BusinessException
	 */
	public boolean updateByRecord(TeamMember record) throws BusinessException;
	
	/**
	 * @Function getTeamMemList
	 * @Description 查询班组信息
	 * @param pageParam
	 * @param record
	 * @return
	 * @throws BusinessException
	 */
	public PageBean getTeamMemList(PageParam pageParam,TeamMember record) throws BusinessException;
	
	
	/**
	 * @Function selectTeamByUser
	 * @Description 根据userCode返回班组列表(该用户所在的班组集合)
	 * @param userCode
	 * @return
	 * @throws BusinessException
	 */
	public List<TeamInfo> selectTeamByUser(String userCode) throws BusinessException;
	
	
	/**
	 * @Function selectByTeamCode
	 * @Description 获取该班组下所有成员
	 * @param teamCode 班组编码
	 * @return
	 * @throws BusinessException
	 */
	public List<TeamMember> selectByTeamCode(String teamCode) throws BusinessException;
	
	
	/**
	 * @Function selectUserCodeByTeamCode
	 * @Description 根据班组code,查询班组成员code
	 * @param teamCode 班组编码
	 * @return
	 * @throws BusinessException
	 */
	public List<String> selectUserCodeByTeamCode(String teamCode) throws BusinessException;
	
	
	/**
	 * @Function selectTeamLeaders
	 * @Description 根据班组code查询班组长code
	 * @param teamCode 班组长编码
	 * @return
	 * @throws BusinessException
	 */
	public List<String> selectTeamLeaders(String teamCode) throws BusinessException;
	
	
	/**
	 * @Function isTeamLeader
	 * @Description 这个人是不是班组长
	 * @param teamCode 班组编码
	 * @param userCode 用户编码
	 * @return
	 * @throws BusinessException
	 */
	public boolean isTeamLeader(String teamCode,String userCode) throws BusinessException;
	
	
	/**
	 * @Function selectByCode
	 * @Description 根据班组成员唯一标识查询
	 * @param teamMemberCode 班组成员唯一标识
	 * @return
	 * @throws BusinessException
	 */
	public TeamMember selectByCode(String teamMemberCode) throws BusinessException;
	
	
	/**
	 * @Function selectByCodes
	 * @Description 根据班组成员唯一标识查询
	 * @param teamMemberCode 班组成员唯一标识集合
	 * @return
	 * @throws BusinessException
	 */
	public List<TeamMember> selectByCodes(List<String> teamMemberCode) throws BusinessException;
}
